package com.objis.demospring.security.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.objis.demospring.security.RegistrationForm;
import com.objis.demospring.security.repositories.UserRepository;

@Controller
public class RegistrationController
{
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder)
	{
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/register")
	public String registerForm()
	{
		return "registration";
	}

	@PostMapping("/register")
	public String processRegistration(RegistrationForm form)
	{
		userRepo.save(form.toUser(passwordEncoder));
		return "redirect:/";
	}
}
