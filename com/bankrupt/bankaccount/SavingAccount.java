package com.bankrupt.bankaccount;

import com.bankrupt.datatools.SQLInteraction;

public class SavingAccount extends BankAccount {
	private float interestRate;
	private int accountNumer;
	
	public SavingAccount(double balance, int accountNumber, int customerID, int accountType, String savingType, float interestRate) {
		super(balance, accountNumber, customerID, accountType);
		this.interestRate=interestRate;
		this.accountNumber=accountNumber;
		this.savingType=savingType;
	}
	
	public String toString() {
		return "Account Number: " + accountNumber + " balance: " + balance 
				+ " CustomerID: " + customerID + " accountType: " + accountType 
				+ " savingType: " + savingType + " interestRate: " 
				+ interestRate;
		}

	/**
	 * @return the interestRate
	 */
	public float getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the accountNumer
	 */
	public int getAccountNumer() {
		return accountNumer;
	}

	/**
	 * @param accountNumer the accountNumer to set
	 */
	public void setAccountNumer(int accountNumer) {
		this.accountNumer = accountNumer;
	}



}