package com.bankroute.user;

import java.util.Vector;

import com.bankroute.datatools.SQLInteraction;

public class Customer extends User {
	protected int councillor;
	
	public Customer(int id, String address, String firstName,String lastName, String mail, String password, int role, int councillor) {
	super(id,address,firstName, lastName, mail, password, role);
	this.councillor=councillor;
	
	}
	
	public Customer() {
	}

	/**
	 * @return the councillor
	 */
	public int getCouncillor() {
		return councillor;
	}

	/**
	 * @param councillor the councillor to set
	 */
	public void setCouncillor(int councillor) {
		this.councillor = councillor;
	}

	@Override
	public
	Vector<User> getCustomersInCharge() {
		// TODO Auto-generated method stub
		return null;
	}
}
