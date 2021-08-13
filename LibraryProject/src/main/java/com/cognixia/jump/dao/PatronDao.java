package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Patron;

public interface PatronDao {
	
	public Patron getPatronById(int id);
	public Patron getPatronByCredentials(String username, String password);
	public boolean addPatron(Patron patron);
	public boolean updatePatron(Patron patron);
	public List<Patron> listPatrons();
	public boolean unfreezePatron(int id);
	public boolean freezePatron(int id);
	
}
