package ru.itis.websockers.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.websockers.models.User;
import ru.itis.websockers.repositories.UsersRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		if (!usersRepository.findByLogin(login).isPresent()) {
			throw new UsernameNotFoundException("User not found with login: " + login);
		}

		User user = usersRepository.findByLogin(login).get();
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordHash(),
				new ArrayList<>());
	}
}