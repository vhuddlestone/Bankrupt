package com.bankroute.bankaccount;

import com.bankroute.datatools.SQLInteraction;

public abstract class BankAccount {
	public double balance;
	public int accountNumber;
	public int customerID;
	public int accountType;
	public SQLInteraction sqlInteraction;
	
	public BankAccount(double balance, int accountNumber, int customerID, int accountType, SQLInteraction sqlInteraction)
	{
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.customerID = customerID;
		this.accountType = accountType;
		this.sqlInteraction = sqlInteraction;
	}
	
}