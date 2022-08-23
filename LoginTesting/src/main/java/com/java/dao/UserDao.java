package com.java.dao;

import com.java.entity.LoginRequest;
import com.java.entity.User;

public interface UserDao {
	
	boolean signUpUser(User user);
	
	User getUserByEmail(LoginRequest loginRequest);
	
	boolean updateUser(User user);

}
