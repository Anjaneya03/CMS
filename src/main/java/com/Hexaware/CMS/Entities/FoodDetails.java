package com.Hexaware.CMS.Entities;

public class FoodDetails {
	private String foodName;
	private double foodPrice;
	private int foodId;
	
	
	public FoodDetails(String foodName, double foodPrice, int foodId) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodId = foodId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public FoodDetails() {
		// TODO Auto-generated constructor stub
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

}
