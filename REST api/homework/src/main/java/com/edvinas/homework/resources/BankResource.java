package com.edvinas.homework.resources;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.edvinas.homework.model.Account;
import com.edvinas.homework.model.BankStatement;
import com.edvinas.homework.service.BankService;
import com.mysql.fabric.Response;

@Path("/statements")
public class BankResource {
	
	BankService accountService = new BankService();
	
	@GET
	@Path("/accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAllAccounts(){
		
		return accountService.getAllAccounts();
	}
	
	// When you want to get balance without dates
	@GET
	@Path("/{id}")
	public String getAccountBalance(@PathParam("id") int id){
		//String info;
		
		Account tt = accountService.getAccountById(id);
		
		return String.valueOf(tt.getBalance());
	}
	
	// Get all statements for certain user
	@GET
	@Path("/{id}/statements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankStatement> getAllStatementsById(@PathParam("id") int id){
		
		return accountService.getAllStatements(id);
	}
	
	// Get account balance by dates
	@GET
	@Path("/{id}/{date_from}/{date_to}")
	public String getAccountBalanceByDates(@PathParam("id") int id, @PathParam("date_from") Date date_from, @PathParam("date_to") Date date_to) {
		
		return accountService.getAccountBalance(id, date_from, date_to);
		
	}
	
	
	
	@POST
	@Path("/post?account_name={id}&beneficiary={beneficiary}&comment={comment}&amount={amount}&currency={currency}&date={date}")	
	public void importStatement(@PathParam("id") int id, @PathParam("beneficiary") String beneficiary, @PathParam("comment") String comment,
			@PathParam("amount") double amount, @PathParam("currency") String currency, @PathParam("date") Date date) {
		
		BankStatement statement = new BankStatement(id, date, beneficiary, comment, amount, currency);
		
		accountService.importStatement(statement);
		
	}
	
}
