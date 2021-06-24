package com.objis.demospring.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
	@GetMapping(path = {"/", "/home"})
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/403")
	public String error403()
	{
		return "error/403";
	}
}
