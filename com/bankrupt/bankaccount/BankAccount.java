package com.bankrupt.bankaccount;

import com.bankrupt.datatools.SQLInteraction;

public abstract class BankAccount {
	public double balance;
	public int accountNumber;
	public int customerID;
	public int accountType;
	public String savingType;
	
	public BankAccount(double balance, int accountNumber, int customerID, int accountType)
	{
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.customerID = customerID;
		this.accountType = accountType;
		this.savingType = "0";
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * @return the type
	 */
	public String getSavingType() {
		return savingType;
	}

	/**
	 * @param type the type to set
	 */
	public void setSavingType(String savingType) {
		this.savingType = savingType;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	

	/**
	 * @return the accountType
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
		
}