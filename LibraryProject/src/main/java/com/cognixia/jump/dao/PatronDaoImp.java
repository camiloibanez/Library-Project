package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Patron;

public class PatronDaoImp implements PatronDao{

	public static final Connection conn = ConnectionManager.getConnection();	
	private static String SELECT_PATRON_BY_ID = "select * from patron where patron_id = ?";
	private static String SELECT_PATRON_BY_NAME = "select * from patron where username = ?";
	private static String INSERT_PATRON = "insert into patron(first_name, last_name, username, password, account_frozon) values(?, ?, ?, ?, ?)";
	private static String UPDATE_PATRON = "update patron set first_name = ?, last_name = ?, username = ?, password = ? where patron_id = ?";


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
				boolean frozon = rs.getBoolean("account_frozon");
				patron = new Patron(id, fname, lname, username, password, frozon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
	}
	@Override
	public Patron getPatronByUsername(String username) {
		Patron patron = null;
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_NAME)) {
			
			pstmt.setString(1, username);			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("patron_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String password = rs.getString("password");
				boolean frozon = rs.getBoolean("account_frozon");
				patron = new Patron(id, fname, lname, username, password, frozon);
			}
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
			pstmt.setBoolean(5, patron.isAccount_frozon());

			
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

}
