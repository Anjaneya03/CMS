package com.Hexaware.CMS.Factory;

import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Persistence.OrderDb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.Hexaware.CMS.Cli.CliMain;
import com.Hexaware.CMS.Entities.*;
/**
 * OrderFactory class used to fetch and insert data to database.
 * @author hexware
 */
public class OrderFactory {
    
    public static int OrderFood(int fid,String fname,int fprice,int fquan){
        int foodTotal=fquan*fprice;
       int result= OrderDb.insertDb(fid,fname,fprice,fquan,foodTotal);
       return result;
    }

    public static Menu[] fetchMenu(){
        Menu menu[]=OrderDb.fetchfoodMenu();
        return menu;
    }

    public static void CustomerProfile(CustInfo customerInfo) throws SQLException{
    	//OrderDb.customerProfileDb(CustomerInfo);
    	 System.out.println("Customer Profile"); 
    	 System.out.println("-------------------------------------------------------------------"); 
    	 System.out.println("CustomerId"+"     "+"Name"+"     "+"Phone"+"     "+"Wallet Balance");
    	 System.out.println("-------------------------------------------------------------------"); 
    	 System.out.println(customerInfo.getCustomerID()+"        "+customerInfo.getCustomerName()+"      "+customerInfo.getCustomerPhone()+"      "+customerInfo.getWalletBalance()); 
    
    	 OrderDb.navigationBackToMainPage(customerInfo);
   }
    
    public static void fetchOrderHistory(CustInfo customerInfo) throws SQLException {
    	
    	ArrayList<OrderDetails> odrList = OrderDb.fetchOrderHistory(customerInfo);
    	System.out.println("Order Id"+"    "+"VendorID"+"    "+"CustomerId"+"     "+"FoodId"+"    "+"OrderQuantity"+"    "+"OrderValue"+"    "+"OrderStatus"); 
        for(OrderDetails orderDetail : odrList){
              System.out.println(orderDetail.getOrderID()+"               "+orderDetail.getVendorId()+"          "+orderDetail.getCustomerId()+"          "+orderDetail.getFoodId()+"          "+orderDetail.getOrderQuantity()+"           "+orderDetail.getOrderValue()+"           "+orderDetail.getOrderStatus());
           }
        
        OrderDb.navigationBackToMainPage(customerInfo);
    	
    }
    public static void showWalletBalance(CustInfo customerInfo) throws SQLException {
    	
    	System.out.println("---------------------------------------------------------");
    	System.out.println("Hi "+customerInfo.getCustomerName()+" !!!!");	
    	System.out.println("Your Available wallet balance is: "+customerInfo.getWalletBalance());
    	System.out.println("---------------------------------------------------------");	
       OrderDb.navigationBackToMainPage(customerInfo);
    	
    }
    // public static Vendor vendorProfile(){}
    // public static Order[] customerOrderHistory(){}
    // public static Order[] vendorOrderHistory(){}
    // public static String acceptRejectOrder(){}

  
    
	public static Vendor vendorProfile() {
		// TODO Auto-generated method stub
		Vendor v=OrderDb.vendorProfileDB();
		return v;
	}

	public static Vendor VendorHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	public static CustInfo CustomerOrderHistory() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void cancelorder (CustInfo customerInfo) {
		Scanner sc=new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("Enter Order ID");
		System.out.println("------------------------------------");
		OrderDb.cancelorder(customerInfo,sc.nextInt());
	}
	public static void getvendorProfile(Vendor vendorRecord){
    	//Customer customerInfo = OrderDb.customerProfileDb();
    	System.out.println("Vendor Profile");
    	System.out.println("---------------------------------------------------------------------");
    	System.out.println("VendorID"+"      "+"Name"+"      "+"Phone"+"      "+"Vendor Spec");
    	System.out.println("---------------------------------------------------------------------");
    	System.out.println(vendorRecord.getVendorID()+"       "+vendorRecord.getVendorName()+"       "+vendorRecord.getVendorPhone()+"        "+vendorRecord.getVendorSpec());
    	System.out.println("---------------------------------------------------------------------");
    }
	
	
	public static void showPendingOrders(Vendor vendorRecord) throws SQLException {
    	ArrayList<OrderDetails> odrList = OrderDb.showPendingOrders(vendorRecord);
    	System.out.println("Order Id"+"    "+"VendorID"+"    "+"CustomerId"+"     "+"FoodId"+"    "+"OrderQuantity"+"    "+"OrderValue"+"    "+"OrderStatus"); 
    	if(odrList!=null && odrList.size()>0) {
    	for(OrderDetails orderDetail : odrList){
              System.out.println(orderDetail.getOrderID()+"               "+orderDetail.getVendorId()+"          "+orderDetail.getCustomerId()+"          "+orderDetail.getFoodId()+"          "+orderDetail.getOrderQuantity()+"           "+orderDetail.getOrderValue()+"           "+orderDetail.getOrderStatus());
           }
        System.out.println("-------------------------------------");
        System.out.println("Enter Order ID to accept or reject");
        Scanner sc = new Scanner(System.in);
        int orderID = sc.nextInt();
        acceptOrReject(vendorRecord,orderID);
    	}else {
    		System.out.println("No Data Available");
    	}
    	navigateBacktoMainPageVendor(vendorRecord);
       // System.out.println("-------------------------------------");
        //CliMain.showLoginPage(ci);
    }
	public static void acceptOrReject(Vendor vendorRecord,int orderID) throws SQLException{
    	System.out.println("Please Select operation to be performed:");
    	System.out.println("1.Accept");
    	System.out.println("2.Reject");
    	Scanner sc = new Scanner(System.in);
    	if(sc.nextInt()==1) {
    		OrderDb.acceptOrder(vendorRecord,orderID);
    	}else {
    		
    		navigateBacktoMainPageVendor(vendorRecord);
    	}
   
    	
    }
	
	 public static void navigateBacktoMainPageVendor(Vendor vendorRecord) throws SQLException {
		    Scanner sc = new Scanner(System.in);
			   System.out.println("\nPress '0' to go to privious menu");
			        
			        if((sc.next().equalsIgnoreCase(String.valueOf('0')))) {
			        	CliMain.showMainPage(vendorRecord);
			        	}
			        }
	 public static void showAcceptedOrders(Vendor vendorRecord) throws SQLException {
	    	
	    	ArrayList<OrderDetails> odrList = OrderDb.showAcceptedOrders(vendorRecord);
	    	if(odrList!=null && odrList.size()>0) {
	    		System.out.println("Order Id"+"    "+"VendorID"+"    "+"CustomerId"+"     "+"FoodId"+"    "+"OrderQuantity"+"    "+"OrderValue"+"    "+"OrderStatus"); 
	            
	        	for(OrderDetails orderDetail : odrList){
	                  System.out.println(orderDetail.getOrderID()+"               "+orderDetail.getVendorId()+"          "+orderDetail.getCustomerId()+"          "+orderDetail.getFoodId()+"          "+orderDetail.getOrderQuantity()+"           "+orderDetail.getOrderValue()+"           "+orderDetail.getOrderStatus());
	               }
	    	}else {
	    		System.out.println("No Data Available");
	    	}

    		navigateBacktoMainPageVendor(vendorRecord);
	      
	    }
}
