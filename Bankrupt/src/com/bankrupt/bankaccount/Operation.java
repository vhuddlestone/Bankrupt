package com.bankrupt.bankaccount;

import java.util.Date;

public class Operation {
	
	int sender;
	int receiver;
	double amount;
	Date date;
	
	public Operation(int sender, int receiver, double amount, Date date)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.date = date;
	}

}
