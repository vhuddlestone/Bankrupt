package com.bankrupt.bankaccount;

import com.bankrupt.datatools.SQLInteraction;

public class CurrentAccount extends BankAccount {
	
	private int accountNumer;
	private double creditCardNumer;
	private int authorizedOverdraft;
	
	public CurrentAccount(double balance, int accountNumber, int customerID, int accountType, double creditCardNumber, int authorizedOverdraft)
	{
		super(balance,accountNumber,customerID,accountType);
		this.accountNumber=accountNumber;
		this.creditCardNumer=creditCardNumber;
		this.authorizedOverdraft=authorizedOverdraft;
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
	 * @return the creditCardNumer
	 */
	public double getCreditCardNumer() {
		return creditCardNumer;
	}

	/**
	 * @param creditCardNumer the creditCardNumer to set
	 */
	public void setCreditCardNumer(double creditCardNumer) {
		this.creditCardNumer = creditCardNumer;
	}

	/**
	 * @return the authorizedOverdraft
	 */
	public int getAuthorizedOverdraft() {
		return authorizedOverdraft;
	}

	/**
	 * @param authorizedOverdraft the authorizedOverdraft to set
	 */
	public void setAuthorizedOverdraft(int authorizedOverdraft) {
		this.authorizedOverdraft = authorizedOverdraft;
	}
	
	
	
}