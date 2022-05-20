package com.Hexaware.CMS.Entities;

import java.sql.Date;
import java.time.LocalDate;

public class OrderDetails {
private int orderID;
private int orderQuantity;
private LocalDate ETA;
private LocalDate date_Time;
private int foodId;
private String customerId;
private double orderValue;
private String orderStatus;
private int decimal;
private int vendorId;

public int getVendorId() {
	return vendorId;
}
public void setVendorId(int vendorId) {
	this.vendorId = vendorId;
}
public OrderDetails(int orderID, int orderQuantity, LocalDate eTA, LocalDate date_Time, int foodId,
		String customerId, double orderValue, String orderStatus,int vendorId) {
	super();
	this.orderID = orderID;
	this.orderQuantity = orderQuantity;
	ETA = eTA;
	this.date_Time = date_Time;
	this.foodId = foodId;
	this.customerId = customerId;
	this.orderValue = orderValue;
	this.orderStatus = orderStatus;
	this.vendorId = vendorId;
}
public int getFoodId() {
	return foodId;
}
public void setFoodId(int foodId) {
	this.foodId = foodId;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}
public double getOrderValue() {
	return orderValue;
}
public void setOrderValue(double orderValue) {
	this.orderValue = orderValue;
}
public String getOrderStatus() {
	return orderStatus;
}
public void setOrderStatus(String orderStatus) {
	this.orderStatus = orderStatus;
}
public OrderDetails(int orderID, int orderQuantity, LocalDate eTA, LocalDate date_Time, int decimal) {
	super();
	this.orderID = orderID;
	this.orderQuantity = orderQuantity;
	ETA = eTA;
	this.date_Time = date_Time;
	this.decimal = decimal;
}
public OrderDetails() {
	// TODO Auto-generated constructor stub
}

public int getOrderID() {
	return orderID;
}
public void setOrderID(int orderID) {
	this.orderID = orderID;
}
public int getOrderQuantity() {
	return orderQuantity;
}
public void setOrderQuantity(int orderQuantity) {
	this.orderQuantity = orderQuantity;
}
public LocalDate getETA() {
	return ETA;
}
public void setETA(LocalDate eTA) {
	ETA = eTA;
}
public LocalDate getDate_Time() {
	return date_Time;
}
public void setDate_Time(LocalDate date_Time) {
	this.date_Time = date_Time;
}
public int getDecimal() {
	return decimal;
}
public void setDecimal(int decimal) {
	this.decimal = decimal;
}


}