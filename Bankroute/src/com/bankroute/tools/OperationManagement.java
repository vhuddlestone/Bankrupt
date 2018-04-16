package com.bankroute.tools;
import com.bankroute.bankaccount.*;

public interface OperationManagement {
	boolean makeOperation(double amount, BankAccount bankAccountSender, int numberAccountReceiver);
}