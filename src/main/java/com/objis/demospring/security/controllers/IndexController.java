package com.objis.demospring.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/administrator")
	public String administrator()
	{
		return "administrator";
	}
	
	@GetMapping("/professor")
	public String professor()
	{
		return "professor";
	}
}
