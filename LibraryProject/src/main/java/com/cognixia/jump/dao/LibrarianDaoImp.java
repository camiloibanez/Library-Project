package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Librarian;

public class LibrarianDaoImp implements LibrarianDao {

	public static final Connection conn = ConnectionManager.getConnection();	
	private static String SELECT_LIBRARIAN_BY_ID = "select * from librarian where librarian_id = ?";
	


	@Override
	public Librarian getLibrarianById(int id) {
		Librarian librarian = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_LIBRARIAN_BY_ID)) {
			
			pstmt.setInt(1, id);			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				librarian = new Librarian(id, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return librarian;
	}

	@Override
	public boolean updateLibrarian(Librarian librarian) {
		//bonus - not implemented yet
		return false;
	}

	@Override
	public boolean addLibrarian(Librarian librarian) {
		//bonus - not implemented yet
		return false;
	}

}
