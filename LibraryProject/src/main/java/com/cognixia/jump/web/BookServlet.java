package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.model.Book;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDao bookDao;
	private boolean librarian;

	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDaoImp();
	}

	public void destroy() {
		
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the ending url to determine what action to perform
		String action = request.getServletPath();
		
		// switch case to handle actions
		switch(action) {
			case "/signin":
				// attempt to sign in with credentials
				break;
			
			case "/history":
				// user's history as well as currently checked out books
				break;
			case "/booklist":
				// list books in the library
				listAllBooks(request, response);
				break;
			case "/checkout":
				// checkouts a book
				break;
			case "/return":
				// returns a book
				break;
			
			// --- LIBRARIAN ONLY
			case "/newbook":
				// add a new book
				break;
			case "/addbook":
				// make update to db for books
				break;
			case "/updatebook":
				// update book information
				break;
			
			// TODO LATER: Bonus features
			case "/signup":
				// add new patron
				break;
			case "/adduser":
				// make update to db for patron
				break;
				
			default:
				// redirect to home page
				response.sendRedirect("/");
				break;
			}
	}
	
	private void signInUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// grab values needed to verify user credentials
		String username = request.getParameter("username");
		String pw = request.getParameter("pw");
		boolean isLibrarian = Boolean.parseBoolean(request.getParameter("isLibarian"));
		
		// check if credentials were valid
		boolean isUser;	// = libraryDao.verifyUser(username, pw, isLibrarian);
		
		// if valid user (patron/librarian)
		//if (isUser) {
			// retrieve user information depending on user type
			// add data to send
			// forward to 'dashboard.jsp'
		//}
		
		
		
	}
	
	private void listAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get all the books in the library
		List<Book> allBooks = bookDao.getAllBooks();
		
		// add data that's going to be sent through request object
		request.setAttribute("allBooks", allBooks);
		
		// redirect to the JSP page and send data that was just pulled
		forwardDispatcher(request, response, "book-list.jsp");
	}
	
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve isbn for the selected book
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		
		// updated it in the db so that it's rented
		
		
		// refresh the page/list
	}
	
	// helper function
	private void forwardDispatcher(HttpServletRequest request, HttpServletResponse response, String jsp) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}



}
