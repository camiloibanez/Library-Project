package com.cognixia.jump.dao;
import java.util.List;
import com.cognixia.jump.model.Book;


public interface BookDao {
	public List<Book> getAllBooks();
	
	public Book getBookById(int id);
	
	public List<Book> getUserHistory(int id);
	
	public boolean addBook(Book book);
	
	public boolean deleteBook(int id);
	
	public boolean updateBook(Book book);
	

	public boolean rentBook(int isbn);
	
	public boolean returnBook(int isbn);

}
