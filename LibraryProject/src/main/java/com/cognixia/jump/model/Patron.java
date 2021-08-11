package com.cognixia.jump.model;

public class Patron {

	private int patron_id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private boolean account_frozon;
	
	public Patron(int patron_id, String first_name, String last_name, String username, String password,
			boolean account_frozon) {
		super();
		this.patron_id = patron_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.account_frozon = account_frozon;
	}

	public int getPatron_id() {
		return patron_id;
	}
	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAccount_frozon() {
		return account_frozon;
	}
	public void setAccount_frozon(boolean account_frozon) {
		this.account_frozon = account_frozon;
	}

	@Override
	public String toString() {
		return "Patron [patron_id=" + patron_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", username=" + username + ", password=" + password + ", account_frozon=" + account_frozon + "]";
	}
	

}
