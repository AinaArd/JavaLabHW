package ru.itis.websockers.services;

import ru.itis.websockers.forms.SignUpForm;
import ru.itis.websockers.models.User;


public interface UserService {
    void signUp(SignUpForm signUpForm);

//    User getCurrentUser(Authentication authentication);
}
