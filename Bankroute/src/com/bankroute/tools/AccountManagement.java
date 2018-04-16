package com.bankroute.tools;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.user.User;

public interface AccountManagement {
	User createAccount(String address, String firstName, String lastName, String mail, String password, int role);
	User deleteAccount(User usrToDelete);
	BankAccount createBankAccount(int customerID, int accountType);
	BankAccount deleteBankAccount(BankAccount bankAccountToDelete);
	Vector<User> getUser(int role);
}