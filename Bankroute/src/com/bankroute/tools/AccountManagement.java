package com.bankroute.tools;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.user.User;

public interface AccountManagement {
	User createAccount(int type);
	User deleteAccount(int id, Vector<User> liste);
	BankAccount createBankAccount(int type);
	BankAccount deleteBankAccount(int id, int type);
}
