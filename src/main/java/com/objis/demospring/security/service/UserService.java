package com.objis.demospring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objis.demospring.security.domaine.User;
import com.objis.demospring.security.repositories.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepo;

	/**
	 * @param studentRepo
	 */
	public UserService(UserRepository userRepo)
	{
		this.userRepo = userRepo;
	}
	
	public void createUser(User user)
	{
		userRepo.save(user);
	}
}
