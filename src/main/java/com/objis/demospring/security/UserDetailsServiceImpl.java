package com.objis.demospring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.objis.demospring.security.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserDetails userDetails = userRepo.findByUsername(username);
		
		return userDetails;
	}
}
