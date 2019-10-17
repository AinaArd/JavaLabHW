package ru.itis.jwtexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.jwtexample.forms.SignUpForm;
import ru.itis.jwtexample.models.User;
import ru.itis.jwtexample.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void signUp(SignUpForm signUpForm) {
        User newUser = User.builder()
                .login(signUpForm.getLogin())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();
        usersRepository.save(newUser);
    }
}
