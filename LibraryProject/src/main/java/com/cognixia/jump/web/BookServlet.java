package com.cognixia.jump.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.LibrarianDaoImp;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

@WebServlet("/")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDao bookDao;
	private LibrarianDao librarianDao;
	private PatronDao patronDao;
	
	// TODO NOW: will have to store this in session
	private boolean isLibrarian;
	HttpSession session;

	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDaoImp();
		librarianDao = new LibrarianDaoImp();
		patronDao = new PatronDaoImp();
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
		PrintWriter out = response.getWriter();
		String loggedOutRedirect = "<script type=\"text/javascript\">" +
				"alert('You are not logged in');" + 
				"location='index.jsp';" +
				"</script>";
		
		// switch case to handle actions
		switch(action) {
			case "/signin":
				// attempt to sign in with credentials
				signInUser(request, response);
				break;
			case "/dashboard":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						forwardDispatcher(request, response, "dashboard.jsp");
					}					
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			
			case "/history":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// user's history as well as currently checked out books
						listUserHistory(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/booklist":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// list books in the library
						listAllBooks(request, response);	
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/checkout":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// checkouts a book
						checkoutBook(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/return":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// returns a book
						returnBook(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			
			// --- LIBRARIAN ONLY
			case "/newbook":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// add a new book
						goToNewBookForm(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/addbook":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// make update to db for books
						addNewBook(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/editbook":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// edit book information
						goToEditBookForm(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/updatebook":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// update book information
						updateBook(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			
			// TODO LATER: Bonus features
			case "/signup":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// add new patron
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/adduser":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						// make update to db for patron
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/accounts":

				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						listAccounts(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}
				break;
			case "/logout":
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						logOut(request, response);
					}
				} catch(Exception e) {
					out.print(loggedOutRedirect);
				}

				break;
			default:
				// redirect to home page
				try {
					if((boolean) session.getAttribute("isLoggedIn")) {
						forwardDispatcher(request, response, "dashboard.jsp");
					}
				} catch(Exception e) {
					response.sendRedirect("/LibraryProject/");
				}
				break;
			}
	}
	
	private void signInUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// grab values needed to verify user credentials
		String username = request.getParameter("username").trim();
		String pw = request.getParameter("pw").trim();
		isLibrarian = Boolean.parseBoolean(request.getParameter("isLibrarian"));
		
		// check if credentials were valid
		if (isLibrarian) {
			
			Librarian user = librarianDao.getLibrarianByCredentials(username, pw);
			
			if(user != null) {
				session = request.getSession();
				
				int librarian_id = user.getLibrarian_id();
				
				// Save user information in session
				session.setAttribute("librarian_id", librarian_id);
				session.setAttribute("username", username);
				session.setAttribute("isLibrarian", isLibrarian);
				session.setAttribute("isLoggedIn", true);
				
				// forward to 'dashboard.jsp'
				forwardDispatcher(request, response, "dashboard.jsp");

			} else {
				System.out.println("Incorrect username and password");
				response.sendRedirect("/");
			}
			
		} else {
			
			Patron user = patronDao.getPatronByCredentials(username, pw);
			
			if(user != null) {
				session = request.getSession();
				
				int patron_id = user.getPatron_id();
				String first_name = user.getFirst_name();
				String last_name = user.getLast_name();
				
				// Save user information in session
				session.setAttribute("patron_id", patron_id);
				session.setAttribute("first_name", first_name);
				session.setAttribute("last_name", last_name);
				session.setAttribute("username", username);
				session.setAttribute("isLibrarian", isLibrarian);
				session.setAttribute("isLoggedIn", true);
				
				// forward to 'dashboard.jsp'
				forwardDispatcher(request, response, "dashboard.jsp");
			
			} else {
				System.out.println("Incorrect username and password");
				response.sendRedirect("/");
			}
			
		 }
		
	}
	
	private void listUserHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve user data from session
//		int id = Integer.parseInt(request.getParameter("patron_id"));
		
		int id = (int) session.getAttribute("patron_id");
		
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
		forwardDispatcher(request, response, "library-book-list.jsp");
	}
	
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve isbn for the selected book
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		int id = (int)session.getAttribute("patron_id");
		
		// updated it in the db so that it's rented
		if (bookDao.rentBook(isbn, id)) {
			// success message
		}
		else {
			// error message
		}
		
		// refresh the page/list
		response.sendRedirect("booklist");
	}
	

	private void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// retrieve isbn for select book
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		int id =Integer.parseInt(request.getParameter("checkout_id"));
		
		// update book so that it has been returned
		if (bookDao.returnBook(isbn, id)) {
			// success message
		}
		else {
			// error message
		}
		
		// refresh page
		response.sendRedirect("history");
	}
	
	private void goToNewBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
