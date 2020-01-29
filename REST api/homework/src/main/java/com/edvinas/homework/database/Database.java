package com.edvinas.homework.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import com.edvinas.homework.model.Account;
import com.edvinas.homework.model.BankStatement;

// Database class for SQL queries nad connection

public class Database {

	private final String host = "jdbc:mysql://localhost/inventi?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String user = "root";         // database username
	private final String pass = "";             // database password

	// ------------------  Connection to the database and query execution -----------------
	// Update/Create/Delete execution
    public void theQuery(String query){
        Connection con = null;
        Statement stmt = null;
        try{
            con = DriverManager.getConnection(host, user, pass);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Query Executed");
        }
        catch(Exception ex){
        	System.out.println(ex.getMessage());
        }
    }
    
    // Get Bank Statements
    public List<BankStatement> getBankStatementsById(int id) {

    	Connection con = null;
    	PreparedStatement stmt = null;
    	String query = "Select * FROM `statement` where account_number = '"+ id +"'";
    	List<BankStatement> temp = new ArrayList<>();
    	
    	try {
    		con = DriverManager.getConnection(host, user, pass);
            stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                BankStatement st = new BankStatement();
                st.setAccount_number(result.getInt("account_number"));
                st.setDate(result.getDate("date"));
                st.setBeneficiary(result.getString("beneficiary"));
                st.setComment(result.getString("comment"));
                st.setAmount(result.getDouble("amount"));
                st.setCurrency(result.getString("currency"));
                temp.add(st);
            }
    	 }catch (Exception e) {
    		System.out.println(e.getMessage());
		 }
         
         return temp;
    	
    }

    public double checkBalance(int account_number, Account acc) {
		List<BankStatement> list = getBankStatementsById(account_number);
		
		if(!list.isEmpty()) {
			double balance = acc.getBalance();
			for(BankStatement item : list)
				balance += item.getAmount();
			
			return balance;
		}
		
		return acc.getBalance();
		
	}
    
    // Get account by its ID
    public Account getAccountById(int account_number) {
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	String query = "Select * FROM `account` where account_number = '"+ account_number +"'";
    	Account temp = new Account();
    	
    	try {
    		con = DriverManager.getConnection(host, user, pass);
            stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                temp.setAccount_number(result.getInt("account_number"));
                temp.setBalance(result.getDouble("balance"));
            }
    	 }catch (Exception e) {
    		System.out.println(e.getMessage());
		 }
    	
    	// if balance equals 0 check for statements
    	if(temp.getBalance() == 0) {
    		double b = checkBalance(account_number, temp);
    		if(b != 0)
    			temp.setBalance(b);
    	}
    	
    	return temp;
    	
    }

    public List<Account> getAllAccounts(){
    	
    	Connection con = null;
    	PreparedStatement stmt = null;			
    	String query = "Select * FROM `account`";
    	List<Account> temp = new ArrayList<>();
    	
    	try {
    		con = DriverManager.getConnection(host, user, pass);
            stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                Account acc = new Account();
                acc.setAccount_number(result.getInt("account_number"));
                acc.setBalance(result.getDouble("balance"));
                temp.add(acc);
            }
    	 }catch (Exception e) {
    		System.out.println(e.getMessage());
		 }    	 
    	 
    	return temp;
    }
    
    public List<BankStatement> getStatementsByDate(int account_number, Date date_from, Date date_to){
    	
    	Connection con = null;
    	PreparedStatement stmt = null;			
    	String query = "Select * FROM `statement` WHERE account_number = '"+ account_number +"' and date >= '"+ date_from +"' and date <= '"+ date_to +"'";
    	List<BankStatement> temp = new ArrayList<>();
    	
    	try {
    		con = DriverManager.getConnection(host, user, pass);
            stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                BankStatement acc = new BankStatement();
                acc.setAccount_number(result.getInt("account_number"));
                acc.setDate(result.getDate("date"));
                acc.setAmount(result.getDouble("amount"));
                temp.add(acc);
            }
    	 }catch (Exception e) {
    		System.out.println(e.getMessage());
		 }    	 
    	 
    	return temp;
    }

    public void importStatement(BankStatement data) {
    	
    	Connection con = null;
    	PreparedStatement stmt = null;			
    	String query = "NSERT INTO statement (account_name, beneficiary, comment, amount, currency, date)" +
    			"VALUES ("+ data.getAccount_number() +", "+ data.getBeneficiary() +", "+ data.getComment() +", "+ data.getAmount() +", "
    					+ " "+ data.getCurrency() +", "+ data.getDate() +")";
    	
    	theQuery(query);
    	
    }
}
