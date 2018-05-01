package com.bankrupt.user;

import java.util.Vector;

import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.tools.*;

public class Banker extends User {
		
		protected Vector<User> customers = new Vector<User>();
	
		/**
		 * @return the customers
		 */
		public Vector<User> getCustomers() {
			return customers;
		}

		/**
		 * @param customers the customers to set
		 */
		public void setCustomers(Vector<User> customers) {
			this.customers = customers;
		}

		public Banker(int id, String address, String firstName,String lastName, String mail, String password, int role, Vector<User> vectClients)  {
			super(id,address,firstName, lastName, mail, password, role);
			this.customers=vectClients;
		}

		@Override
		public
		Vector<User> getCustomersInCharge() {
			return customers;
		}
}