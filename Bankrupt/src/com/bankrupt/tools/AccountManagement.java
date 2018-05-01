package com.bankrupt.tools;
import java.sql.SQLException;
import java.util.Vector;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

public interface AccountManagement {
	public void addUser(String firstName, String lastName, String mail, String address, String password, int role, int councillorId,SQLInteraction sqlInteraction);
	User deleteAccount(User usrToDelete);
	boolean addBankAccount(User customer, int account_type, int saving_type, SQLInteraction sqlInteraction) throws SQLException;
	BankAccount deleteBankAccount(BankAccount bankAccountToDelete);
	Vector<User> getUser(int role);
	Vector<User> getClientsFromBankerId(int id);
	int getCouncillorIdFromClientId(int id);
}