package com.bankroute.tools;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.user.User;

public class CustomerAccountManagement implements AccountManagement {

	@Override
	public User createAccount(String address, String firstName, String lastName, String mail, String password,
			int role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteAccount(User usrToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount createBankAccount(int customerID, int accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount deleteBankAccount(BankAccount bankAccountToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<User> getUser(int role) {
		// TODO Auto-generated method stub
		return null;
	}



}
