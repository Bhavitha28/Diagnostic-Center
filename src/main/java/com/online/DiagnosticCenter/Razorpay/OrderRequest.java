package com.online.DiagnosticCenter.Razorpay;

import java.math.BigInteger;

public class OrderRequest {
	
	String name;

	String phoneNumber;
	BigInteger amount;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

}
