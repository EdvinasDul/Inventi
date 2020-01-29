package com.edvinas.homework.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
	
	private int account_number;
	private double balance;
	
	// Constructor without parameters
	public Account() {
		
	}
	
	// Constructor with parameters
	public Account(int account_number, double balance) {
		this.account_number = account_number;
		this.balance = balance;
	}

	// Getters & Setters
	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
