package com.online.DiagnosticCenter.Razorpay;

public class OrderResponse {

	String secretKey;
	String razorpayOrderId;



	String paymentId;
	String applicationFee;
	String secretId;
	String pgName;

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getApplicationFee() {
		return applicationFee;
	}



	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

}
