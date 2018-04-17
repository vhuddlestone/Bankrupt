package com.bankroute.user;
import java.util.Vector;

import com.bankroute.datatools.SQLInteraction;
import com.bankroute.tools.*;

/**
 * Ce code représente la classe abstraite User.
 * Elle définit les éléments qui seront utilisés par les classes filles Banker et Customer
 * @author R.GASQUET // V.HUDDLESTONE // V. GRUIT
 */

public abstract class User {
	protected int id;
	protected String address;
	protected String firstName;
	protected String lastName;
	protected String mail;
	protected String password;
	protected int role;
	protected AccountManagement accountManagement;
	protected OperationManagement operationManagement;
	protected SQLInteraction sqlInteraction;
	
	public User() {
		this.address = "XXX";
		this.firstName = "XXX";
		this.lastName = "XXX";
		this.mail = "XXX";
		this.password = "XXX";
		this.role = 1;
	}
	
	public User(int id, String address, String firstName,
			String lastName, String mail, String password, int role, SQLInteraction sqlInteraction) {
		
		this.id=id;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.role = role;
		this.sqlInteraction=sqlInteraction;
		this.accountManagement = new CustomerAccountManagement(sqlInteraction);
		//this.operationManagement = new CustomerOperation();
		
	}
	
	public abstract Vector<User> getCustomersInCharge();
	/**
	 * @return
	 */
	
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
	public SQLInteraction getSQLInstance() {
		return this.sqlInteraction;
	}
	
	@Override
	public String toString(){
		return "User: "+firstName + " "+lastName+" | role:"+role;
		
	}
	
}
