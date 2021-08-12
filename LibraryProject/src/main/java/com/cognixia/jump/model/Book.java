package com.cognixia.jump.model;

import java.sql.Date;

public class Book {

	private int isbn;
	private String title;
	private String descr;
	private boolean rented;
	private Date added_to_library;
	
	private Date checkedout;
	private Date due_date;
	private Date returned;
	

	public Book(int isbn, String title, String descr, boolean rented, Date added_to_library) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.descr = descr;
		this.rented = rented;
		this.added_to_library = added_to_library;
	}
	
	public Book(int isbn, String title, String descr, boolean rented, Date checkedout, Date due_date, Date returned) {
		this.isbn = isbn;
		this.title = title;
		this.descr = descr;
		this.rented = rented;
		this.setCheckedout(checkedout);
		this.due_date = due_date;
		this.returned = returned;
	}


	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public boolean isRented() {
		return rented;
	}
	public void setRented(boolean rented) {
		this.rented = rented;
	}
	public Date getAdded_to_library() {
		return added_to_library;
	}
	public void setAdded_to_library(Date added_to_library) {
		this.added_to_library = added_to_library;
	}
	
	public Date getCheckedout() {
		return checkedout;
	}

	public void setCheckedout(Date checkedout) {
		this.checkedout = checkedout;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getReturned() {
		return returned;
	}

	public void setReturned(Date returned) {
		this.returned = returned;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", descr=" + descr + ", rented=" + rented
				+ ", added_to_library=" + added_to_library + ", checkedout=" + checkedout + ", due_date=" + due_date
				+ ", returned=" + returned + "]";
	}

}
