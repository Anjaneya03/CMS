package com.Hexaware.CMS.Entities;

public class Vendor {

	private int vendorID;
	private String vendorName;
	private int vendorPhone;
	private String vendorMail;
	private String vendorSpec;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Vendor(int vendorID, String vendorName, int vendorPhone, String vendorMail, String vendorSpec) {
		super();
		this.vendorID = vendorID;
		this.vendorName = vendorName;
		this.vendorPhone = vendorPhone;
		this.vendorMail = vendorMail;
		this.vendorSpec = vendorSpec;
	}
	public Vendor() {
		// TODO Auto-generated constructor stub
	}
	public Vendor(int vendorID, String vendorName, int vendorPhone, String vendorMail, String vendorSpec,
			String password) {
		super();
		this.vendorID = vendorID;
		this.vendorName = vendorName;
		this.vendorPhone = vendorPhone;
		this.vendorMail = vendorMail;
		this.vendorSpec = vendorSpec;
		this.password = password;
	}
	public int getVendorID() {
		return vendorID;
	}
	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public int getVendorPhone() {
		return vendorPhone;
	}
	public void setVendorPhone(int vendorPhone) {
		this.vendorPhone = vendorPhone;
	}
	public String getVendorMail() {
		return vendorMail;
	}
	public void setVendorMail(String vendorMail) {
		this.vendorMail = vendorMail;
	}
	public String getVendorSpec() {
		return vendorSpec;
	}
	public void setVendorSpec(String vendorSpec) {
		this.vendorSpec = vendorSpec;
	}
	
	
	
}
