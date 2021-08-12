package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Patron;

public class PatronDaoImp implements PatronDao{

	public static final Connection conn = ConnectionManager.getConnection();	
	private static String SELECT_PATRON_BY_ID = "select * from patron where patron_id = ?";
	private static String SELECT_PATRON_BY_CREDENTIALS = "select * from patron where username = ? and password = ?";
	private static String INSERT_PATRON = "insert into patron(first_name, last_name, username, password, account_frozon) values(?, ?, ?, ?, ?)";
	private static String UPDATE_PATRON = "update patron set first_name = ?, last_name = ?, username = ?, password = ? where patron_id = ?";
	private static String LIST_PATRONS = "select * from patron";


	@Override
	public Patron getPatronById(int id) {
		Patron patron = null;
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_ID)) {
			
			pstmt.setInt(1, id);			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				patron = new Patron(id, fname, lname, username, password, frozen);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
	}
	@Override
	public Patron getPatronByCredentials(String username, String password) {
		Patron patron = null;
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_CREDENTIALS)) {
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("patron_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String pw = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				patron = new Patron(id, fname, lname, username, pw, frozen);

			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
	}

	@Override
	public boolean addPatron(Patron patron) {
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_PATRON)) {
			
			pstmt.setString(1, patron.getFirst_name());
			pstmt.setString(2, patron.getLast_name());
			pstmt.setString(3, patron.getUsername());
			pstmt.setString(4, patron.getPassword());
			pstmt.setBoolean(5, patron.isAccount_frozen());

			
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
	public boolean updatePatron(Patron patron) {
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_PATRON)) {

			pstmt.setString(1, patron.getFirst_name());
			pstmt.setString(2, patron.getLast_name());
			pstmt.setString(3, patron.getUsername());
			pstmt.setString(4, patron.getPassword());

			pstmt.setInt(5, patron.getPatron_id());

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
	public List<Patron> listPatrons() {
		
		List<Patron> patrons = new ArrayList<Patron>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(LIST_PATRONS)) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("patron_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				Patron patron = new Patron(id, fname, lname, username, password, frozen);
				
				patrons.add(patron);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patrons;
	}
}
