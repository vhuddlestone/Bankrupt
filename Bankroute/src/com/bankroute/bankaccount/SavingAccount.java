package com.bankroute.bankaccount;

import com.bankroute.datatools.SQLInteraction;

public class SavingAccount extends BankAccount {
	public SavingAccount(double balance, int accountNumber, int customerID, int accountType,
			SQLInteraction sqlInteraction) {
		super(balance, accountNumber, customerID, accountType, sqlInteraction);
		// TODO Auto-generated constructor stub
	}

	float interestRate;
}
