package com.binarysoftware.burnaby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	static Connection con=null;

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

	public static Connection getConnection() throws SQLException{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","roshan","2310r231");
		return con;
	}
}
