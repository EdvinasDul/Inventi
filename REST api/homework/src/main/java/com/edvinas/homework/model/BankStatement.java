package com.edvinas.homework.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

// Entity class

@XmlRootElement
public class BankStatement {

	// Entity class variables
	private int account_number;
	private Date date;
	private String beneficiary;
	private String comment;
	private double amount;
	private String currency;
	
	// Constructor without parameters
	public BankStatement() {
		
	}
	
	// Constructor
	public BankStatement(int account_number, Date date, String beneficiary, String comment, 
			double amount, String currency) {
		this.account_number = account_number;
		this.date = date;
		this.beneficiary = beneficiary;
		this.comment = comment;
		this.amount = amount;
		this.currency = currency;
	}
	
	// Getters & Setters
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
