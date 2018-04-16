package com.bankroute.user;

import com.bankroute.datatools.SQLInteraction;

public class Customer extends User {
	protected int counselor;
	
	public Customer(int id, String address, String firstName,
			String lastName, String mail, String password, int role, SQLInteraction sqlInteraction, int counsellor) {
		super(id,address,firstName, lastName, mail, password, role, sqlInteraction);
		this.counselor=counsellor;
	}
}