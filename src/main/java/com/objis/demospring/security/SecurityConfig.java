package com.objis.demospring.security;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import com.objis.demospring.security.domaine.User;
import com.objis.demospring.security.repositories.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{

	//

	// ETAPE 1 : quel Encodeur ?

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	// ETAPE 2 : Faire implémenter l'entité User de l'interface userDetails (Fait)

	// TODO ETAPE 3 : Dao (+ methode findByUsername)

	// TODO ETAPE 4 : Service

	// Ici version simplifiée #EnDurPourTester
	/*
	 * @Bean public UserDetailsService userDetailsService(PasswordEncoder encoder) {
	 * List<UserDetails> usersList = new ArrayList<>(); usersList.add( new
	 * User("cedric", encoder.encode("password"))); usersList.add( new User("jules",
	 * encoder.encode("password"))); return new
	 * InMemoryUserDetailsManager(usersList); }
	 */

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo)
	{
		return username ->
		{
			User user = userRepo.findByUsername(username);
			if (user != null)
				return user;
			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		return http
				.authorizeRequests()
				.antMatchers("/index").hasRole("USER")
				.antMatchers("/", "/home", "/register", "/login").permitAll()
				.and()
				.build();
	}

}
