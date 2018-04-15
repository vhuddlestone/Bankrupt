package com.bankroute.tools;
import com.bankroute.bankaccount.*;

public interface OperationManagement {
	double receiveMoney();
	BankAccount transferMoney(double amount, BankAccount account);
	BankAccount internalTransfert(double amount, BankAccount account);
	String transactionLogs(String log);
}
