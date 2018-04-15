package com.bankroute.user;

import java.util.Vector;

import com.bankroute.datatools.SQLInteraction;
import com.bankroute.tools.*;

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

		public Banker(int id, String address, String firstName,String lastName, String mail, String password, int role, SQLInteraction sqlInteraction, Vector<User> vectClients)  {
			super(id,address,firstName, lastName, mail, password, role, sqlInteraction);
			this.customers=vectClients;
		}

		@Override
		public
		Vector<User> getCustomersInCharge() {
			return customers;
		}
}
