package ru.itis.jwtexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.jwtexample.repositories.UsersRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${token.expired}")
    private Integer expiredSecondsForToken;


}
