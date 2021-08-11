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
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return book;
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
	
	@Override
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
	
	@Override
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
	
}
