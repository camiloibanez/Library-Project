package com.cognixia.jump.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	// connection is null at the start
	private static Connection connection;
	
	// establishes connection
	private static void makeConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		
		// load in credentials from a properties file
		Properties properties = new Properties();
		
		properties.load(new FileInputStream("resources/config.properties"));
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		// register the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// make the connection
		connection = DriverManager.getConnection(url, username, password);
	}
	
	// used to grab connection
	// propagate the exception because the user doesn't need to know the details, just need to know that the connection couldn't
	// be made
	public static Connection getConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		
		if (connection == null) {
			makeConnection();
		}
		
		return connection;
	}
	
}
