package com.cognixia.jump.dao;

import com.cognixia.jump.model.Patron;

public interface PatronDao {
	
	public Patron getPatronById(int id);
	public Patron getPatronByUsername(String username);
	public boolean addPatron(Patron patron);
	public boolean updatePatron(Patron patron);
	
}
