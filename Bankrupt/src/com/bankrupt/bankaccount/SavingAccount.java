package com.bankrupt.bankaccount;

import com.bankrupt.datatools.SQLInteraction;

public class SavingAccount extends BankAccount {
	private float interestRate;
	private int accountNumer;
	private String type;
	
	public SavingAccount(double balance, int accountNumber, int customerID, int accountType, String savingType, float interestRate) {
		super(balance, accountNumber, customerID, accountType);
		this.interestRate=interestRate;
		this.accountNumber=accountNumber;
		this.type=savingType;
	}
	
	public String toString() {
		return "Account Number: " + accountNumber + " balance: " + balance 
				+ " CustomerID: " + customerID + " accountType: " + accountType 
				+ " savingType: " + type + " interestRate: " 
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


}