package com.objis.demospring.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.objis.demospring.security.domaine.User;

public class RegistrationForm
{
	private String username;
	private String password;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public User toUser(PasswordEncoder passwordEncoder)
	{
		return new User(username, passwordEncoder.encode(password));
	}
}
