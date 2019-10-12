package ru.itis.longpolling.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.longpolling.models.Token;
import ru.itis.longpolling.repositories.TokensRepository;
import ru.itis.longpolling.security.auth.TokenAuthentication;
import ru.itis.longpolling.security.details.UserDetailsImpl;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService service;

    @Autowired
    private TokensRepository tokensRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // делаем явное преобразование для работы с TokenAuthentication
        TokenAuthentication tokenAuthentication
                = (TokenAuthentication) authentication;
        Optional<Token> tokenCandidate = tokensRepository.findByValue(authentication.getName());
        if(tokenCandidate.isPresent()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) service.loadUserByUsername(tokenAuthentication.getName());
            tokenAuthentication.setUserDetails(userDetails);
            // говорим, что с ним все окей
            tokenAuthentication.setAuthenticated(true);
        }
//        // загружаем данные безопасности пользователя из UserDetailsService
//        // по токену достали пользователя из БД
//        UserDetailsImpl userDetails = (UserDetailsImpl) service.loadUserByUsername(tokenAuthentication.getName());
//        // если данные пришли
//        if (userDetails != null && userDetails.getCurrentToken().isNotExpired()) {
//            // в данный объект аутентификации кладем пользователя
//            tokenAuthentication.setUserDetails(userDetails);
//            // говорим, что с ним все окей
//            tokenAuthentication.setAuthenticated(true);
//        } else {
//            throw new BadCredentialsException("Incorrect Token");
//        }
        // возвращаем объект SecurityContext-у
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}