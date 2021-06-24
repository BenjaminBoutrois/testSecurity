package com.objis.demospring.security.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.objis.demospring.security.RegistrationForm;
import com.objis.demospring.security.repositories.UserRepository;

@Controller
@RequestMapping("/login")
public class ConnectionController
{
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	public ConnectionController(UserRepository userRepo, PasswordEncoder passwordEncoder)
	{
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String registerForm()
	{
		return "login";
	}

	@PostMapping
	public String processRegistration(RegistrationForm form)
	{
		userRepo.save(form.toUser(passwordEncoder));
		return "redirect:/login";
	}
}
