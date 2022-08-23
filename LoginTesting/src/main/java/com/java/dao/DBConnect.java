package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	private static Connection con;
	
	public static Connection connect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mywork";
			String username ="root";
			String password = "root";
			
			con = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
