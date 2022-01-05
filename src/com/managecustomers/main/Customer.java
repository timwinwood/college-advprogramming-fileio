package com.managecustomers.main;

/**
 * Product - a class to represent a Customer
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
public class Customer {
	
	// instance variables
	private int id;
	private String fname;
	private String lname;
	private String country;
	private String email;

	/**
	 * Customer constructor
	 * 
	 * @param id the customer id
	 * @param fname the customer first name
	 * @param lname the customer last name
	 * @param country the customer country
	 * @param email the customer email
	 */
	public Customer(int id, String fname, String lname, String country, String email) {
		
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.country = country;
		this.email = email;
		
	}
	
	/**
	 * @return the customer id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the customer id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the customer first name
	 */
	public String getFname() {
		return fname;
	}
	
	/**
	 * @param fname the customer first name
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * @return the customer last name
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the customer last name
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/**
	 * @return the customer country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the customer country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return the customer email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the customer email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return string representation of the customer
	 */
	@Override
	public String toString() {
		return id + "," + fname + "," + lname + "," + country + "," + email;
	}

}
