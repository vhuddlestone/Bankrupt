package com.bankrupt.tools;
import com.bankrupt.bankaccount.*;
import com.bankrupt.datatools.SQLInteraction;

public interface OperationManagement {
	public boolean makeOperation(double amount, int numberAccountSender, int numberAccountReceiver, SQLInteraction sqlInteraction);
	public BankAccount findBankAccount(int customerID, int accountType, SQLInteraction sqlInteraction);
}