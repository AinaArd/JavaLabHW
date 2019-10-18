package ru.itis.websockers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.websockers.forms.SignUpForm;
import ru.itis.websockers.models.User;
import ru.itis.websockers.repositories.UsersRepository;

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
                .passwordHash(passwordEncoder.encode(signUpForm.getPassword()))
                .build();
        usersRepository.save(newUser);
    }
}
