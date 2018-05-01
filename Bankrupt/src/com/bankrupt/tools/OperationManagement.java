package com.bankrupt.tools;
import java.util.Vector;

import com.bankrupt.bankaccount.*;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.User;

public interface OperationManagement {
	public boolean makeInternalOperation(User currentUser, double amount, int numberAccountSender, int numberAccountReceiver, SQLInteraction sqlInteraction);
	public boolean makeExternalOperation(User currentUser, double amount, int numberAccountSender, int numberAccountReceiver, SQLInteraction sqlInteraction);
	public BankAccount findBankAccount(int customerID, int accountType, int savingType ,SQLInteraction sqlInteraction);
	public Vector<Operation> getOperationsOfAccount(int accountId,SQLInteraction sqlInteraction);
}