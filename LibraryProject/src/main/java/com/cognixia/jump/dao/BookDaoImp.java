package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;

public class BookDaoImp implements BookDao{
	
	public static final Connection conn = ConnectionManager.getConnection();	
	private static String SELECT_ALL_BOOKS = "select * from book";
	private static String SELECT_BOOK_BY_ID = "select * from book where isbn = ?";
	private static String SELECT_BOOK_HISTORY = "SELECT * FROM book_checkout AS bkc INNER JOIN book AS bk ON bkc.isbn = bk.isbn WHERE bkc.patron_id = ?;";
	private static String INSERT_BOOK = "insert into book(isbn, title, descr, rented, added_to_library) values(?, ?, ?, ?, ?)";
	private static String DELETE_BOOK = "delete from book where isbn = ?";
	private static String UPDATE_BOOK = "update book set title = ?, descr = ? where isbn = ?";
	private static String RENT_BOOK = "update book set rented = ? where isbn = ?";
	
	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = new ArrayList<Book>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOOKS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next()) {				
				int isbn = rs.getInt("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				Boolean rented = rs.getBoolean("rented");
				Date added_to_library = rs.getDate("added_to_library");
				
				allBooks.add(new Book(isbn, title, descr, rented, added_to_library));				
			}			
			
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return allBooks;
	}
	@Override
	public Book getBookById(int isbn) {
		Book book = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_BY_ID)) {
			
			pstmt.setInt(1, isbn);			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				Boolean rented = rs.getBoolean("rented");
				Date added_to_library = rs.getDate("added_to_library");
				book = new Book(isbn, title, descr, rented, added_to_library);
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return book;
	}
	
	@Override
	public List<Book> getUserHistory(int id) {
		List<Book> userHistory = new ArrayList<Book>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_HISTORY)) {
			
			pstmt.setInt(1,  id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int isbn = rs.getInt("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				boolean rented = rs.getBoolean("rented");
				Date checkedout = rs.getDate("checkedout");
				Date dueDate = rs.getDate("due_date");
				Date returned = rs.getDate("returned");
				
				userHistory.add(new Book(isbn, title, descr, rented, checkedout, dueDate, returned));
			}
			
			rs.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userHistory;
	}
	
	@Override
	public boolean addBook(Book book) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_BOOK)) {
			
			pstmt.setInt(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getDescr());
			pstmt.setBoolean(4, book.isRented());
			pstmt.setDate(5, book.getAdded_to_library());
			
			// at least one row added
			if(pstmt.executeUpdate() > 0) {
				return true;
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	@Override
	public boolean deleteBook(int id) {

		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOK)) {

			pstmt.setInt(1, id);
			// at least one row deleted
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return false;
	}
	@Override
	public boolean updateBook(Book book) {
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK)) {

			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getDescr());
			
			pstmt.setInt(3, book.getIsbn());

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	public boolean rentBook(int isbn) {
		try (PreparedStatement pstmt = conn.prepareStatement(RENT_BOOK)) {

			pstmt.setBoolean(1, true);		
			pstmt.setInt(2, isbn);

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	public boolean returnBook(int isbn) {
		try (PreparedStatement pstmt = conn.prepareStatement(RENT_BOOK)) {

			pstmt.setBoolean(1, false);		
			pstmt.setInt(2, isbn);

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	public static void main(String[] args) {
		BookDao dao = new BookDaoImp();
		List<Book> books = dao.getAllBooks();
		
		for(Book b: books) {
			System.out.println(b);
		}
		
		Book handmaid = new Book(1480560103, "The Handmaid's Tale", "Dystopian feminist fiction", false, new Date(System.currentTimeMillis()) );
		boolean added = dao.addBook(handmaid);
		System.out.println(added);
		boolean returned = dao.returnBook(1480560103);
		System.out.println(returned);
		boolean rent = dao.rentBook(1480560103);
		System.out.println(rent);
		boolean deleted = dao.deleteBook(1480560103);
		System.out.println(deleted);
	}
	
}
