package com.example.test.model;

import java.util.*;

public class Customer {
	
	private int customerNumber;
	private String customerName;
	private String customerMobile;
	private String customerEmail;
	private String address1;
	private String address2;
	private List<Account> accounts;
	
	public Customer() {}
	
	public Customer(int customerNumber
			, String customerName
			, String customerMobile
			, String customerEmail
			, String address1
			, String address2) {
		setCustomerNumber(customerNumber);
		setCustomerName(customerName);
		setCustomerEmail(customerEmail);
		setAddress1(address1);
		setAddress2(address2);
	}
	
	public Customer(int customerNumber
			, String customerName
			, String customerMobile
			, String customerEmail
			, String address1) {
		setCustomerNumber(customerNumber);
		setCustomerName(customerName);
		setCustomerEmail(customerEmail);
		setAddress1(address1);
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public void removeAccount(Account account) {
		this.accounts.remove(account);
	}

}
