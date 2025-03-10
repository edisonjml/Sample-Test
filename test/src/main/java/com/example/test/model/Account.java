package com.example.test.model;

import java.math.*;

public class Account {
	
	private int accountNumber;
	private AccountType accountType;
	private BigDecimal availableBalance;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = AccountType.valueOf(accountType);
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	
	public enum AccountType {
		Savings,
		Checking
	}

}
