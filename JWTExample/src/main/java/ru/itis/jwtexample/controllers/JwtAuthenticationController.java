package ru.itis.jwtexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.jwtexample.forms.LoginForm;
import ru.itis.jwtexample.forms.SignUpForm;
import ru.itis.jwtexample.models.JwtResponse;
import ru.itis.jwtexample.security.details.JwtUserDetailsService;
import ru.itis.jwtexample.services.UserService;
import ru.itis.jwtexample.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
    private UserService userService;

	@PostMapping(value = "/login")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm loginForm) throws Exception {

		authenticate(loginForm.getLogin(), loginForm.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getLogin());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/register")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> saveUser(@RequestBody SignUpForm signUpForm) throws Exception {
        userService.signUp(signUpForm);
		return ResponseEntity.ok().build();
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}