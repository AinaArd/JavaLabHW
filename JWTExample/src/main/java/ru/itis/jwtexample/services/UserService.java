package ru.itis.jwtexample.services;

import org.springframework.security.core.Authentication;
import ru.itis.jwtexample.forms.SignUpForm;

public interface UserService {
    void signUp(SignUpForm signUpForm);

}
