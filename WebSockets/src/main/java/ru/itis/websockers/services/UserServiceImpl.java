package ru.itis.websockers.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.websockers.forms.SignUpForm;
import ru.itis.websockers.models.Role;
import ru.itis.websockers.models.User;
import ru.itis.websockers.repositories.UsersRepository;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
//        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());
        User user = User.builder()
                .login(signUpForm.getLogin())
//                .passwordHash(hashPassword)
                .role(Role.USER)
                .tokens(new ArrayList<>())
                .build();
        usersRepository.save(user);
    }

//    @Override
//    public User getCurrentUser(Authentication authentication) {
//        return ((UserDetailsImpl)authentication.getPrincipal()).getUser();
//    }
}
