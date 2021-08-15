package com.cognixia.jump.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String username = "root";
	private static final String password = "root";
	
	// connection is null at the start
	private static Connection connection;
	
	// establishes connection
	private static void makeConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		
		
		// register the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// make the connection
		connection = DriverManager.getConnection(url, username, password);
	}
	
	// used to grab connection
	// propagate the exception because the user doesn't need to know the details, just need to know that the connection couldn't
	// be made
	public static Connection getConnection() {
		
		try {
			if (connection == null) {
				makeConnection();
			}
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
