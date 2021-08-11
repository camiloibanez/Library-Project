package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Declare Dao here
	// private DaoClass daoName;

	public void init(ServletConfig config) throws ServletException {
		// Initialize Dao here
		// DaoClass daoName = new DaoClass();
	}

	public void destroy() {
		
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// switch case to handle actions
	}



}
