package com.java.entity;


import javax.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



public class User {

	private int id;

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "enter a valid email address")
	private String email;

	private String gender;

	@NotEmpty(message = "Password is required")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",message = "password must contain characters")
	private String password;
	
	private String profile;


	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public User() {
	}

	public User(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String gender, String password) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.password = password;
	}

	public User(int id, String name, String email, String gender, String password,String profile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.profile = profile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", gender=" + gender + ", password="
				+ password + ", profile=" + profile + "]";
	}


	

}
