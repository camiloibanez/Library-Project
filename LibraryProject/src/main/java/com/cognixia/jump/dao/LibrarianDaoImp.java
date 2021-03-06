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
	private static String SELECT_LIBRARIAN_BY_CREDENTIALS = "select * from librarian where username = ? and password = ?";
	private static String INSERT_LIBRARIAN = "insert into librarian(username, password) values(?, ?)";
	private static String UPDATE_LIBRARIAN = "update librarian set username = ?, password = ? where librarian_id = ?";



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
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return librarian;
	}
	@Override
	public Librarian getLibrarianByCredentials(String username, String password) {
		Librarian librarian = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_LIBRARIAN_BY_CREDENTIALS)) {
			
			pstmt.setString(1, username);	
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("librarian_id");
				String pw = rs.getString("password");
				librarian = new Librarian(id, username, pw);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return librarian;
	}

	@Override
	public boolean updateLibrarian(Librarian librarian) {
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_LIBRARIAN)) {

			pstmt.setString(1, librarian.getUsername());
			pstmt.setString(2, librarian.getPassword());

			pstmt.setInt(3, librarian.getLibrarian_id());

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
	public boolean addLibrarian(Librarian librarian) {
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_LIBRARIAN)) {

			pstmt.setString(1, librarian.getUsername());
			pstmt.setString(2, librarian.getPassword());

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
