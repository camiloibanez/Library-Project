package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.model.Book;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDao bookDao;
	
	// TODO NOW: will have to store this in session
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
				listUserHistory(request, response);
				break;
			case "/booklist":
				// list books in the library
				listAllBooks(request, response);
				break;
			case "/checkout":
				// checkouts a book
				checkoutBook(request, response);
				break;
			case "/return":
				// returns a book
				returnBook(request, response);
				break;
			
			// --- LIBRARIAN ONLY
			case "/newbook":
				// add a new book
				goToNewBookForm(request, response);
				break;
			case "/addbook":
				// make update to db for books
				addNewBook(request, response);
				break;
			case "/editbook":
				// edit book information
				goToEditBookForm(request, response);
				break;
			case "/updatebook":
				// update book information
				updateBook(request, response);
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

		librarian = Boolean.parseBoolean(request.getParameter("isLibrarian"));
		
		// TODO: Make suggestion to create a User class that Patron and Librarian inherits from
		
		// check if credentials were valid
//		if (librarian) {
//			
//		}
		
		
		// if valid user (patron/librarian)
		//if (isUser) {
			// retrieve user information depending on user type
			// add data to send
			// forward to 'dashboard.jsp'
		//}
		
		// Save user information in session
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("librarian", librarian);
		
		
		
	}
	
	private void listUserHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve user data from session
		int id = Integer.parseInt(request.getParameter("patron_id"));
		
		// retrieve user's book history
		List<Book> userHistory = bookDao.getUserHistory(id);
		
		// add data that is going to be sent through
		request.setAttribute("userHistory", userHistory);
		
		// redirect to JSP page
		forwardDispatcher(request, response, "book-history-list.jsp");
		
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
		if (bookDao.rentBook(isbn)) {
			// some sort of success message
		}
		else {
			// some sort of error message
		}
		
		
		// refresh the page/list -> either return to library book list or go to user history
	}
	
	private void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve isbn for select book
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		
		// update book so that it has been returned
		if (bookDao.returnBook(isbn)) {
			// success message
		}
		else {
			// error message
		}
		
		// refresh page
	}
	
	private void goToNewBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// passes along whether the user is a librarian or not
		// TODO NOW: Figure out how to do this through a session variable
		request.setAttribute("isLibrarian", librarian);
		
		// re-direct to the book form
		forwardDispatcher(request, response, "book-form.jsp");
	}
	
	private void addNewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve form data
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		String title = request.getParameter("title");
		String descr = request.getParameter("description");
		Date addDate = new Date(System.currentTimeMillis());
		
		Book newBook = new Book(isbn, title, descr, false, addDate);
		
		// add new book to db
		bookDao.addBook(newBook);
		
		// redirect to list
		response.sendRedirect("booklist");
		
	}
	
	private void goToEditBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		
		Book book = bookDao.getBookById(isbn);
		
		request.setAttribute("book", book);
		
		forwardDispatcher(request, response, "book-form.jsp");
		
	}
	
	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// grab information from the form
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		String title = request.getParameter("title").trim();
		String descr = request.getParameter("description").trim();
		
		// get current data for the book
		Book book = bookDao.getBookById(isbn);
		
		// Change the books title and description
		book.setTitle(title);
		book.setDescr(descr);
		
		// execute the update
		bookDao.updateBook(book);
		
		// redirect
		response.sendRedirect("booklist");
		
	}
	
	
	// helper function
	private void forwardDispatcher(HttpServletRequest request, HttpServletResponse response, String jsp) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}



}
