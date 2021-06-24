package com.objis.demospring.security;

import javax.sql.DataSource;

import com.objis.demospring.security.domaine.User;
import com.objis.demospring.security.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private DataSource dataSource;

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
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, true " + "from user where username=?")
            .authoritiesByUsernameQuery("select username, role from user where username=?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }
	

	/*@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		return http
				.authorizeRequests()
				.antMatchers("/index").hasRole("USER")
				.antMatchers("/", "/home", "/register", "/login").permitAll()
				.and()
				.build();
	}*/
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/css/*", "/js/*", "/images/*", "/register").permitAll()
                .antMatchers("/administrator").hasAuthority("ADMIN")
                .antMatchers("/professor").hasAuthority("PROF")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .permitAll()
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/home")
                .permitAll();
    }
}
