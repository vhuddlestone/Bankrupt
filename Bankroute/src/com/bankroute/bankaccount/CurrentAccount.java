package com.bankroute.bankaccount;

import com.bankroute.datatools.SQLInteraction;

public class CurrentAccount extends BankAccount {
	
	public CurrentAccount(double balance, int accountNumber, int customerID, int accountType, SQLInteraction sqlInteraction)
	{
		super(balance,accountNumber,customerID,accountType,sqlInteraction);
	}
	
}