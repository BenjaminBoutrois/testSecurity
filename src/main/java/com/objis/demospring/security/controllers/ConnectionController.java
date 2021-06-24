package com.objis.demospring.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConnectionController
{
	@GetMapping("/login")
	public String registerForm()
	{
		return "login";
	}
}