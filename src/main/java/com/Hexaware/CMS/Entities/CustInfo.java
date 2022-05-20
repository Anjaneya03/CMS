package com.Hexaware.CMS.Entities;

public class CustInfo {
	private String customerID;
	private String customerName;
	private int customerPhone;
	private String customerMail;
	private double walletBalance;
	private String password;
	
	public CustInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustInfo(String customerID, String customerName, int customerPhone, String customerMail,
			double walletBalance,String password) {
		
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerMail = customerMail;
		this.walletBalance = walletBalance;
		this.password = password;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(int customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
