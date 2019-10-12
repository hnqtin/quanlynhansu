package com.quanlynhansu.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ket noi voi DB
public class JDBCConnection {
	private static String url = "jdbc:mysql://localhost:3306/quanlynhansu";
	private static String username = "root";
	private static String password = "123456";

	public static Connection getConnection() {
		
		// Su dung lop Driver cua JDBC
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			 
			return connection;
			
		} catch (ClassNotFoundException e) {
		 
		} catch (SQLException e) {
			 
		}
		
		return null;
	}
}
