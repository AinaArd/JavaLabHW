package ru.itis.tests;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Main {
    private static UsersRepository usersRepository;

    @Autowired
    public Main(UsersRepository usersRepository) {
        Main.usersRepository = usersRepository;
    }

    public static void main(String[] args) {
//        List<User> users = usersRepository.findAll();

        Optional<User> user = usersRepository.findById(Long.valueOf(1));

        System.out.println(user);
    }
}
