package com.bankroute.user;

import com.bankroute.tools.*;

public class Banker extends User {
		public Banker() {
			this.operationManagement = new BankerOperation();
			this.accountManagement = new BankerManagement();
			this.role = 2;
		}
		
		public Banker(String address, String firstName,
				String lastName, String mail, String password, int role,
				AccountManagement accountManagement, OperationManagement operationManagement) {
			this.address = address;
			this.firstName = firstName;
			this.lastName = lastName;
			this.mail = mail;
			this.password = password;
			this.role = role;
			this.operationManagement = new BankerOperation();
			if(role == 2)
				this.accountManagement = new BankerManagement();
			else if(role == 3)
				this.accountManagement = new AdminManagement();
		}

}
