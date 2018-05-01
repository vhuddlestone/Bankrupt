package com.bankrupt.tools;
import com.bankrupt.bankaccount.*;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.User;

public interface OperationManagement {
	public boolean makeOperation(User currentUser, double amount, int numberAccountSender, int numberAccountReceiver, SQLInteraction sqlInteraction);
	public BankAccount findBankAccount(int customerID, int accountType, int savingType ,SQLInteraction sqlInteraction);
}