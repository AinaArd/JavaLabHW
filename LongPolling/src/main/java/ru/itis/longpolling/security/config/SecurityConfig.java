package ru.itis.longpolling.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.itis.longpolling.security.filters.TokenAuthenticationFilter;
import ru.itis.longpolling.security.providers.TokenAuthenticationProvider;

@Configuration // говорим, что у нас конфигурационный класс
@EnableGlobalMethodSecurity(prePostEnabled = true) // включаем проверку безопасности через аннотации
@EnableWebSecurity // включаем безопасность
@ComponentScan("ru.itis.longpolling") // говорим, чтобы искал все компоненты в наших пакетах
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // подключем провайдер, который мы написали
    @Autowired
    private TokenAuthenticationProvider provider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // конфигурируем AuthenticationManager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // прикручиваем наш провайдер
        auth.authenticationProvider(provider);
    }
    // конфигурирем саму безопасность
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // отключаем csrf
        http.csrf().disable();
        http.cors().disable();
        // отключаем сессии
        http.sessionManagement().disable();
        // добавляем наш фильтр
        http.addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class);
        // говорим, что разрешаем Swagger
            http.authorizeRequests().antMatchers("/swagger-ui.html#/**").permitAll();
        http.authorizeRequests().antMatchers("/messages").hasAuthority("USER");

    }
}
