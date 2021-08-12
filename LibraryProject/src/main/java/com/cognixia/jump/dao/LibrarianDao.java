package com.cognixia.jump.dao;

import com.cognixia.jump.model.Librarian;

public interface LibrarianDao {

	public Librarian getLibrarianById(int id);
	public Librarian getLibrarianByCredentials(String username, String password);
	public boolean updateLibrarian(Librarian librarian);
	public boolean addLibrarian(Librarian librarian);
}
