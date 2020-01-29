
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edvinas
 */
public class Data {
    
    // Entity class variables
	private int account_number;
	private String date;
	private String beneficiary;
	private String comment;
	private double amount;
	private String currency;
	
	// Constructor without parameters
	public Data() {
		
	}
	
	// Constructor
	public Data(int account_number, String date, String beneficiary, String comment, 
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
        
    /**
     *
     */
    @Override
    public String toString(){
        return String.format("Account_number: " + account_number + " Beneficiary: " + beneficiary + " Comment: " + comment + " Amount: " + amount + 
                " Currency: " + currency + " Date: " + date); 
        //return String.format("%-8d\t%-30s\t%-35s\t%-15.2f\t%-10s%30s", account_number, beneficiary, comment, amount, currency, date); 
    }
    
}
