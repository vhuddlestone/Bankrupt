package com.bankroute.user;
import com.bankroute.tools.*;

/**
 * Ce code représente la classe abstraite User.
 * Elle définit les éléments qui seront utilisés par les classes filles Banker et Customer
 *
 */

public abstract class User {
	protected int id;
	protected String address;
	protected String firstName;
	protected String lastName;
	protected String mail;
	protected String password;
	protected int role;
	
	protected AccountManagement accountManagement = new CustomerAccountManagement();
	protected OperationManagement operationmanagement = new ClientOperation();	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
}
