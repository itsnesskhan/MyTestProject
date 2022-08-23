package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.java.entity.LoginRequest;
import com.java.entity.User;

public class UserDaoImpl implements UserDao {
	
	private Connection connection;
	

	public UserDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}


	@Override
	public User getUserByEmail(LoginRequest loginRequest) {
		User user = null;
		String query ="select * from myuser where email = ? and password = ?";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, loginRequest.getEmail());
			prepareStatement.setString(2, loginRequest.getPassword());
			ResultSet set = prepareStatement.executeQuery();
			
			while (set.next()) {
				user = new User();
				user.setId(set.getInt("id"));
				user.setName(set.getString("name"));
				user.setEmail(set.getString("email"));
				user.setGender(set.getString("gender"));
				user.setPassword(set.getString("password"));
				user.setProfile(set.getString("profile"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public boolean signUpUser(User user) {
		boolean flag = false;
		String query = "insert into myuser(name, email, gender, password) values(?,?,?,?)";
		try {
			PreparedStatement psmt = connection.prepareStatement(query);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getEmail());
			psmt.setString(3, user.getGender());
			psmt.setString(4, user.getPassword());
			psmt.setString(5, user.getProfile());
			
			psmt.executeUpdate();
			flag = true;
			
		}
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("user with "+user.getEmail()+" already exist");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean updateUser(User user) {
		boolean flag = false;
		try {
			String query ="update myuser set name =?, email=?, gender=?, profile =? where id =?";
			PreparedStatement psmt = connection.prepareStatement(query);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getEmail());
			psmt.setString(3, user.getGender());
			psmt.setString(4, user.getProfile());
			psmt.setInt(5, user.getId());
			psmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	


}
