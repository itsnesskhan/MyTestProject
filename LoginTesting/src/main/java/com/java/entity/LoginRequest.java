package com.java.entity;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

	@NotEmpty(message = "usernmae is required")
	private String email;
	@NotEmpty(message = "password is required")
	private String password;
	
	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
