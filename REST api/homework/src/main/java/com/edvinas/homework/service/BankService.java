package com.edvinas.homework.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.edvinas.homework.database.Database;
import com.edvinas.homework.model.Account;
import com.edvinas.homework.model.BankStatement;

// class for any actions with received data

public class BankService {
	
	Database database = new Database();
	
	// Get all existing accounts
	public List<Account> getAllAccounts(){
		List<Integer> list = new ArrayList<>();
		return database.getAllAccounts();
		
		/*acc.forEach(i -> {
			list.add(i.getAccount_number());
		});
		
		return list;*/
	}
	
	// Get account by id
	public Account getAccountById(int account_number) {		
		
		return database.getAccountById(account_number);
	}
	
	// get all statements by id
	public List<BankStatement> getAllStatements(int account_number){
		
		return database.getBankStatementsById(account_number);
	}
	
	public String getAccountBalance(int account_number, Date date_from, Date date_to) {

		double balance = 0;
		
		List<BankStatement> list = database.getStatementsByDate(account_number, date_from, date_to);
		
		// Java 8 feature - foreach
		//list.forEach(i -> System.out.println(i.getDate() + "  " + i.getAmount()));
		
		// If there are no statements jump to else and return balance
		if(!list.isEmpty()) {
			for(BankStatement item : list)
				balance += item.getAmount();
			
			return String.valueOf(balance);
		}
		else { 
			Account acc = database.getAccountById(account_number);
			return String.valueOf(acc.getBalance());
		}
	}
	
	public void importStatement(BankStatement data) {
		
		database.importStatement(data);
	}
	
}
