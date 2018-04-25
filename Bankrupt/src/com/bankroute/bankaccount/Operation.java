package com.bankroute.bankaccount;

import java.util.Date;

/**
 * 
 * Objet opérations 
 */
public class Operation {
	private int sender;
	private int receiver;
	private double amount;
	private Date date;
	
	public Operation(int sender, int receiver, double amount, Date date) {
		this.sender=sender;
		this.receiver=receiver;
		this.amount=amount;
		this.date=date;
	}
	
	/**
	 * @return the sender
	 */
	public int getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}
	/**
	 * @return the receiver
	 */
	public int getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
