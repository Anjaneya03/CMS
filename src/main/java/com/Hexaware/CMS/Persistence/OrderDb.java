package com.Hexaware.CMS.Persistence;
import com.Hexaware.CMS.Cli.CliMain;
import com.Hexaware.CMS.Entities.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import com.Hexaware.CMS.Model.Menu;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author hexware
 */
public class OrderDb {
   static int i;
   
    public static int insertDb(int food_id,String food_name,int food_price,int fq,int foodTotal){        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123"); 
            PreparedStatement stmt=con.prepareStatement("INSERT INTO FoodDescription VALUES(?,?,?,?,?)");  
        stmt.setInt(1,food_id);  
        stmt.setString(2,food_name);  
        stmt.setInt(3,food_price);
        stmt.setInt(4,fq);   
        stmt.setInt(5,foodTotal);
        i=stmt.executeUpdate();  
        //System.out.println(i+" records inserted");  
        }catch(Exception e){ System.out.println(e);}  
                return i;
            }  
    public static Menu[] fetchfoodMenu() {
    Menu m[]=null;
    try{  
        Class.forName("com.mysql.cj.jdbc.Driver");  
       Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
        Statement stmt=con.createStatement();  
                
        ResultSet rs=stmt.executeQuery("SELECT * FROM FoodDescription");  
        ArrayList<Menu> list=new ArrayList<Menu>();          
        while(rs.next()) { 
        list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            m=new Menu[list.size()];
            m= list.toArray(m);
              } 
    }catch(Exception e){ System.out.println(e);}  
        
    return m;
          } 
       

      public static CustInfo customerProfileDb(String cust_id){
    	  CustInfo c= null;
    	  try {
    		  Class.forName("com.mysql.cj.jdbc.Driver");
    		  Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
    		  PreparedStatement pstmt = Con.prepareStatement("SELECT * FROM CustomerDetails WHERE cust_id=?");
    		  pstmt.setString(1, cust_id);
    		  ResultSet rs= pstmt.executeQuery("SELECT * FROM CustomerDetails WHERE cust_id=?");
    		  while(rs.next()) {
    			  int customerID=rs.getInt("cust_id");
    			  String customerName = rs.getString("cust_name");
    			  int customerPhone = rs.getInt("phone_no");
    			  String customerMail = rs.getString("Email");
    			  double walletBalance=rs.getDouble("wallet_balance");
    			  System.out.format("%s,%s,%s,%s,%s",customerID,customerName,customerPhone,customerMail,walletBalance);
    		  }Con.close();
    	  }
    	  catch(Exception e) {
    		  System.err.println("Got an exception!");
    		  System.err.println(e.getMessage());
    		
    	  }
    	  return c;
    	  }
    	  

      public static Vendor vendorProfileDB(){
    	   Vendor v= null;
    	   try {
     		  Class.forName("com.mysql.cj.jdbc.Driver");
     		  Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
     		  Statement stmt = Con.createStatement();
     		  ResultSet rs= stmt.executeQuery("SELECT * FROM Vendor");
     		  while(rs.next()) {
     			  int vendor_id=rs.getInt("vendor_id");
     			  String vendor_name = rs.getString("vendorName");
     			  int vendor_phone = rs.getInt("vendorPhone");
     			  String vendor_specilaisation = rs.getString("vendorSpecs");
     			  
     			  System.out.println(vendor_id+vendor_name+vendor_phone+vendor_specilaisation);
     		  }
     		 stmt.close();
     		 Con.close();
     		  
     	  }
     	  catch(Exception e) {
     		  System.err.println("Got an exception!");
     		  System.err.println(e.getMessage());
     		  System.err.println(e.getCause());
    	   
     	  }
    	   return v;
       }
       
       public static Boolean registerUser() {
    	   CustInfo customerInfo = new CustInfo();
    	   Scanner sc = new Scanner(System.in);
    	   System.out.println("Enter Customer Name");
    	   customerInfo.setCustomerName(sc.next());
    	   
    	   System.out.println("Enter Customer Id");
    	   customerInfo.setCustomerID(sc.next());
    	   
    	   System.out.println("Enter Customer Password");
    	   customerInfo.setPassword(sc.next());
    	   
    	   System.out.println("Enter Customer PhoneNumber");
    	   customerInfo.setCustomerPhone(sc.nextInt());
    	   
    	   System.out.println("Enter Customer Email");
    	   customerInfo.setCustomerMail(sc.next());
    	   
    	   System.out.println("Enter Customer WalletBalance");
    	   customerInfo.setWalletBalance(sc.nextDouble());
    	   
    	
    	   
                 try {
      			     Class.forName("com.mysql.cj.jdbc.Driver");
      	     	     Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
      		         //Statement stmt = Con.createStatement();
      		         PreparedStatement stmt=Con.prepareStatement("INSERT INTO CustomerDetails VALUES(?,?,?,?,?,?)");      
      		         // Execute a query
      		       
      		         stmt.setString(1,customerInfo.getCustomerID());
      		         stmt.setString(2,customerInfo.getCustomerName());
      		         stmt.setInt(3,customerInfo.getCustomerPhone());
      		         stmt.setString(4,customerInfo.getCustomerMail());
      		         stmt.setDouble(5,customerInfo.getWalletBalance());
      		         stmt.setString(6,customerInfo.getPassword());
      		         System.out.println("User registerd successfully....");          
      		        
      		         
      		         
      		          i=stmt.executeUpdate();

      		          if(i==1) {
      		        	 PreparedStatement stmt1=Con.prepareStatement("INSERT INTO login_details VALUES(?,?)");
      		        	stmt1.setString(1,customerInfo.getCustomerID());
      		        	stmt1.setString(2,customerInfo.getPassword());
      		        	i=stmt1.executeUpdate();
      		          }
      		        navigationBack();
                 }catch(Exception e){
      	    		  e.printStackTrace();
      	    	  }
                 
                 return true;
       }
      public static void registerVendor(){
    	 Vendor vendorDetail = new Vendor();
  	   Scanner sc = new Scanner(System.in);
  	   System.out.println("Enter Vendor Name");
  	 vendorDetail.setVendorName(sc.next());
  	   
  	   System.out.println("Enter Vendor Id(Integer only)");
  	 vendorDetail.setVendorID(sc.nextInt());
  	   
  	   System.out.println("Enter Vendor Password");
  	 vendorDetail.setPassword(sc.next());
  	   
  	   System.out.println("Enter Vendor PhoneNumber");
  	 vendorDetail.setVendorPhone(sc.nextInt());
  	   
  	 System.out.println("Enter Vendor Speciality");
  	 vendorDetail.setVendorSpec(sc.next());
  	   
  	   
  	
  	   
               try {
    			     Class.forName("com.mysql.cj.jdbc.Driver");
    	     	     Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
    		         //Statement stmt = Con.createStatement();
    		         PreparedStatement stmt=Con.prepareStatement("INSERT INTO Vendor VALUES(?,?,?,?,?)");      
    		         // Execute a query
    		       
    		         stmt.setInt(1,vendorDetail.getVendorID());
    		         stmt.setString(2,vendorDetail.getVendorName());
    		         stmt.setInt(3,vendorDetail.getVendorPhone());
    		         stmt.setString(4,vendorDetail.getVendorSpec());
    		         stmt.setString(5,vendorDetail.getPassword());
    		        
    		         i=stmt.executeUpdate();

    		          if(i==1) {
    		        	System.out.println("vendor registered Successfully !!!!!!");
    		          }
    		        navigationBack();
               }catch(Exception e){
    	    		  e.printStackTrace();
    	    	  }
               
    	 
    	 
    	 
    	 
    	 
     }
       
       public static void navigationBack() throws SQLException {
    	   Scanner sc = new Scanner(System.in);
    	   System.out.println("Press '0' to go to Previous menu");
		        
		        if((sc.next().equalsIgnoreCase(String.valueOf('0')))) {
		        	CliMain.showLandingPage();
		        }
       }
           		     public static void showWalletBalance(String cust_id) {
           	    	  try {
           	    		  Class.forName("com.msql.cj.jdbc.Driver");
           	    		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
           	    		  ResultSet resultS = null; 
           	    		  PreparedStatement stmt = con.prepareStatement("SELECT wallet_balance FROM customerDetails WHERE cust_id=?");
           	    		  stmt.setString(1, cust_id);
           	    		  resultS = stmt.executeQuery();
           	    		  
           	    		  
           	    	  }catch(Exception e){
           	    		  e.printStackTrace();
           	    	  }
           		    }
           	    	  
           		     
           		   public static void placeOrder(OrderDetails orderDetails,CustInfo customerInfo) throws SQLException {
           			   
           			 Connection con = null;
           			 
           			 Date d = new Date();
                       try {
            			     Class.forName("com.mysql.cj.jdbc.Driver");
            	     	     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
            		         //Statement stmt = Con.createStatement();
            		         PreparedStatement stmt=con.prepareStatement("INSERT INTO order_details(cust_id,food_iD,order_qunatity,order_value,order_status) VALUES(?,?,?,?,?)");      
            		         // Execute a query
            		       
            		         stmt.setString(1,orderDetails.getCustomerId());
            		         stmt.setInt(2,orderDetails.getFoodId());
            		         stmt.setInt(3,orderDetails.getOrderQuantity());
            		         //stmt.setDate(6,(java.sql.Date) d);
            		         //stmt.setDate(7, (java.sql.Date) d);
            		         stmt.setDouble(4, orderDetails.getOrderValue());
            		         stmt.setString(5,orderDetails.getOrderStatus());
            		         
            		        
            		          i=stmt.executeUpdate();
            		          
            		          if(i==1) {
            		        	  //System.out.println("Oder Placed");  
            		        	  OrderDb.updateWalletBalance(customerInfo,(customerInfo.getWalletBalance()- orderDetails.getOrderValue()));
            		          }
                       }catch(Exception ex) {
                    	   
                    	   System.out.println(ex);
                    	   
                       }
                       finally {
                    	   con.close();
                       }
           			   
           			   
           		   }
           	    	  public static void updateWalletBalance(CustInfo customerInfo,double walletBalance) {
           	     	  try {
         			     Class.forName("com.mysql.cj.jdbc.Driver");
         	     	     Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
         		         //Statement stmt = Con.createStatement();
         		         PreparedStatement stmt=Con.prepareStatement("UPDATE CustomerDetails SET wallet_balance=? WHERE cust_id=?");      
         		         // Execute a query
         		       
         		         stmt.setDouble(1,walletBalance);
         		         stmt.setString(2,customerInfo.getCustomerID());
         		         Scanner sc = new Scanner(System.in);
         		         i=stmt.executeUpdate();
         		         if(i==1) {
                		        System.out.println(" Your Order Placed Successfully ");
                		        
                		        navigationBackToMainPage(customerInfo);
           		        	  
           		          }
         	   }catch(Exception ex) {
         		   ex.printStackTrace();
         		   }
               }
           	   public static void navigationBackToMainPage(CustInfo customerInfo) throws SQLException{
           	    		
           	    	   Scanner sc = new Scanner(System.in);
           	    	   System.out.println("\nPress '0' to go to Previous menu");
           			        
           			        if((sc.next().equalsIgnoreCase(String.valueOf('0')))) {
           			        	CliMain.showMainPage(customerInfo);
           			        }
           	    		
           	    	}
           	    	
           	    	
           	    public static FoodDetails getfoodDetails(int food_id) {
             	  FoodDetails fi = new FoodDetails();
            	  try{  
                      Class.forName("com.mysql.cj.jdbc.Driver");  
                     Connection con=DriverManager.getConnection(  
                      "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
                      PreparedStatement stmt=con.prepareStatement("SELECT * FROM FoodDescription WHERE food_iD = ?"); 
                      stmt.setInt(1,food_id);        
                      ResultSet rs=stmt.executeQuery();
                      if(rs.next() && rs.getInt("food_iD")!=0) {
                    	  fi.setFoodId(rs.getInt(1));
                    	  fi.setFoodName(rs.getString(2));
                    	  fi.setFoodPrice(rs.getDouble(3));
                      }
                               
                     
                  }catch(Exception e){ System.out.println(e);}
            	  return fi;
              }
               
           	    
           	    
		// TODO Auto-generated method stub
		
	
	public static CustInfo userLogin() {
    	   CustInfo customerInfo =new CustInfo();
    	   ResultSet resultSet = null;
    	   Scanner sc = new Scanner(System.in);
    	   System.out.println("Please enter UserId");
           String userId=sc.next();
           System.out.println("Please enter Password");
           String password=sc.next();
           
           try {
        	   Class.forName("com.mysql.cj.jdbc.Driver");
       	     Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
  	         //Statement stmt = Con.createStatement();
  	         PreparedStatement pst=Con.prepareStatement("SELECT * from login_details WHERE User_Id=? AND Password = ?");  
  	         
  	         pst.setString(1,userId);
  	         pst.setString(2,password); 
  	         
  	       resultSet = pst.executeQuery();
  	       
  	       while(resultSet.next() && resultSet.getString(1)!=null) {
  	    	   
  	    	 //System.out.println(resultSet.getString(1));
  	    	 //System.out.println(resultSet.getString(2));
  	    	 customerInfo = OrderDb.getCustomerInfo(resultSet.getString(1),resultSet.getString(2));
  	    	   
  	       }
  	         
  	         // Execute a query
        	   
           }catch(Exception ex) {
        	   System.out.println(ex);
           }
           
           
    	   
    	   return customerInfo;
       }
       
       public static CustInfo getCustomerInfo(String customerId,String Password) {
    	   
    	   CustInfo customerInfo = new CustInfo();
    	   ResultSet resultSet = null;
    	   Scanner sc = new Scanner(System.in);
    	   
    	   
    	   try {
    		   Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
  	         //Statement stmt = Con.createStatement();
  	          PreparedStatement pst=Con.prepareStatement("SELECT * from CustomerDetails WHERE cust_id=? AND Password=?");
  	          
  	          pst.setString(1,customerId);
  	          pst.setString(2,Password);
 	         
 	       resultSet = pst.executeQuery();
 	       
 	       while(resultSet.next() && resultSet.getString(1)!=null) {
 	    	   
 	    	  System.out.println(resultSet.getString("cust_name"));
 	    	  System.out.println(resultSet.getString(2));
 	    	  customerInfo.setCustomerID(resultSet.getString("cust_id"));
 	    	 customerInfo.setCustomerName(resultSet.getString("cust_name"));
 	    	customerInfo.setCustomerPhone(resultSet.getInt("phone_no"));
 	    	customerInfo.setCustomerMail(resultSet.getString("Email"));
 	    	customerInfo.setWalletBalance(resultSet.getDouble("wallet_Balance"));
 	    	customerInfo.setPassword(resultSet.getString("password"));
 	    	   
 	    	// OrderDb.getCustomerInfo(resultSet.getString(1),resultSet.getString(2));
 	    	   
 	       }
 	       
 	       return customerInfo;
  	          
      	   
    		   
    	   }catch(SQLException ex) {
    		   System.out.println(ex);
    	   }
    	   
    	   return customerInfo;
       }
       
    	   
      
       
       
       
       public static void changePassword() throws SQLException {
    	   Scanner sc = new Scanner(System.in);
    	   CustInfo customerInfo = new CustInfo();
    	   System.out.println("----------------------");
    	   System.out.println("Change Password");
    	   System.out.println("----------------------");
    	   
    	   customerInfo = userLogin();
    	   
    	   if(customerInfo!=null && customerInfo.getCustomerID()!=null) {
    		   
    		   String oldPassword = customerInfo.getPassword();
    		   
    		   System.out.println("Please enter your new password"); 
    		   String newPassword = sc.next();
    		   
    		   if(oldPassword.equalsIgnoreCase(newPassword)) {
    			   
    			   System.out.println("Your new and old password can not be same");
    			   System.out.println("\n");
    			   System.out.println("Press '0' to go to previous menu");
      		        
      		        if((sc.next().equalsIgnoreCase(String.valueOf('0')))) {
      		        	CliMain.showLandingPage();
      		        }
 		        	  
    		   }else {
    			   
    			   try {
        			     Class.forName("com.mysql.cj.jdbc.Driver");
        	     	     Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
        		         //Statement stmt = Con.createStatement();
        		         PreparedStatement stmt=Con.prepareStatement("UPDATE CustomerDetails SET Password=? WHERE cust_id=?");      
        		         // Execute a query
        		       
        		         stmt.setString(1,newPassword);
        		         stmt.setString(2,customerInfo.getCustomerID());
        		         
        		         i=stmt.executeUpdate();
        		         if(i==1) {
          		        	 PreparedStatement stmt1=Con.prepareStatement("UPDATE login_details SET password=? WHERE User_Id=?");
          		        	stmt1.setString(1,newPassword);
          		        	stmt1.setString(2,customerInfo.getCustomerID());
          		        	i=stmt1.executeUpdate();

               		        System.out.println("Password changed Successfully ");
               		        System.out.println("Press '0' to go to  menu");
               		        
               		        if((sc.next().equalsIgnoreCase(String.valueOf('0')))) {
               		        	CliMain.showLandingPage();
               		        }
          		        	  
          		          }
    		   }catch(Exception ex) {
    			   
    			   System.out.println(ex);
    			   }
    		   }
    		   
    		   
    	   }
       }
       public static ArrayList<OrderDetails> fetchOrderHistory(CustInfo customerInfo) {
    	   ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
    	   try{  
    	        Class.forName("com.mysql.cj.jdbc.Driver");  
    	       Connection con=DriverManager.getConnection(  
    	        "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
    	       PreparedStatement pst=con.prepareStatement("SELECT * FROM order_details WHERE cust_id=?");
   	          
   	          pst.setString(1,customerInfo.getCustomerID());
  	         
   	           ResultSet rs = pst.executeQuery();
    	        
    	        while(rs.next()) { 
    	        	Date dt =new Date();
    	        	ZoneId defaultZoneId = ZoneId.systemDefault();
    	        	Instant instant1 = dt.toInstant();
    	        	
    	        	LocalDate localDate1 = instant1.atZone(defaultZoneId).toLocalDate();
    	        	
                    Instant instant2 = dt.toInstant();
    	        	
    	        	LocalDate localDate2 = instant1.atZone(defaultZoneId).toLocalDate();
    	        	
    	        	orderList.add(new OrderDetails(
    	        			rs.getInt(1),
    	        			rs.getInt(5),
    	        			localDate1,
    	        			localDate2,
    	        			rs.getInt(4),
    	        			rs.getString(3),
    	        			rs.getDouble(8),
    	        			rs.getString(9),
    	        			rs.getInt(2)));
    	          
    	              } 
    	    }catch(Exception e){ System.out.println(e);}  
    	   if(orderList.size()>0) {
    		   return orderList;
    	   }else {
    		   return null;
    	   }
       
       }


      
      // public static order[] customerOderHistoryDb(){}
      // public static Vendor[] vendorOderHistoryDb(){}
     
public static void cancelorder (CustInfo customerInfo,int orderId){
	   try{  
	        Class.forName("com.mysql.cj.jdbc.Driver");  
	       Connection con=DriverManager.getConnection(  
	        "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
	       PreparedStatement pst=con.prepareStatement(" DELETE FROM order_details WHERE cust_id=? AND order_id=?");
	          int deleteSuccess = 0;
	          pst.setString(1,customerInfo.getCustomerID());
	          pst.setInt(2,orderId);
	          deleteSuccess=pst.executeUpdate();
	          // ResultSet rs = pst.executeQuery();
	      	      if(deleteSuccess==1) {
			  System.out.println(" Your Order Cancelled Successfuly..!");
			  navigationBackToMainPage(customerInfo);
		  }else {
			  System.out.println("NO Record Found");
			  navigationBackToMainPage(customerInfo);
	    }
	      	      }
	      	      catch(Exception e){ System.out.println(e);}  
	 
	   }
  
public static Vendor vendorLogin() {
	  Vendor vendorRecord = new Vendor();
	  ResultSet resultS = null; 
	  Scanner sc = new Scanner(System.in);
	  System.out.println("Please Enter your Vendor ID");
	  String vendorId = sc.next();
	  System.out.println("Please enter your password");
	  String password = sc.next();
	  
	  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
		  
		  PreparedStatement pst = Con.prepareStatement("SELECT * FROM vendor WHERE vendor_iD = ? AND password = ?");
		  pst.setString(1, vendorId);
		  pst.setString(2, password);
		  resultS = pst.executeQuery();
		  while(resultS.next() && resultS.getString(1)!=null) {
			  //System.out.println(resultS.getString(1));
			  //System.out.println(resultS.getString(2));
			  
			  vendorRecord.setVendorID(resultS.getInt(1));
			  vendorRecord.setVendorName(resultS.getString(2));
			  vendorRecord.setVendorPhone(resultS.getInt(3));
			  vendorRecord.setVendorSpec(resultS.getString(4));
		  }
	  }catch(Exception ex) {
		  System.out.println(ex);
	  }
	  
	  
	  return vendorRecord;
}
 public static ArrayList<OrderDetails> showPendingOrders(Vendor vendorRecord){
	  ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
	   try{  
	        Class.forName("com.mysql.cj.jdbc.Driver");  
	       Connection con=DriverManager.getConnection(  
	        "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
	       PreparedStatement pst=con.prepareStatement("SELECT * FROM order_details WHERE order_status!='Accepted'");
	          
	          //pst.setString(1,vendorRecord.getVendorID());
	         
	           ResultSet rs = pst.executeQuery();
	        
	        while(rs.next()) { 
	        	Date dt =new Date();
	        	ZoneId defaultZoneId = ZoneId.systemDefault();
	        	Instant instant1 = dt.toInstant();
	        	
	        	LocalDate localDate1 = instant1.atZone(defaultZoneId).toLocalDate();
	        	
                Instant instant2 = dt.toInstant();
	        	
	        	LocalDate localDate2 = instant1.atZone(defaultZoneId).toLocalDate();
	        	
	        	orderList.add(new OrderDetails(
	        			rs.getInt(1),
	        			rs.getInt(5),
	        			localDate1,
	        			localDate2,
	        			rs.getInt(4),
	        			rs.getString(3),
	        			rs.getDouble(8),
	        			rs.getString(9),
	        			rs.getInt(2)));
	          
	              } 
	    }catch(Exception e){ System.out.println(e);}  
	   if(orderList.size()>0) {
		   return orderList;
	   }else {
		   return null;
	   }
}
 public static void acceptOrder(Vendor vendorRecord,int orderID) {
	  try {
		     Class.forName("com.mysql.cj.jdbc.Driver");
    	     Connection Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","Password123");
	         //Statement stmt = Con.createStatement();
	         PreparedStatement stmt=Con.prepareStatement("UPDATE order_details SET Vendor_iD=?,order_status='Accepted' WHERE order_id=?");      
	         // Execute a query
	       
	         stmt.setInt(1,vendorRecord.getVendorID());
	         stmt.setInt(2,orderID);
	         Scanner sc = new Scanner(System.in);
	         i=stmt.executeUpdate();
	         if(i==1) {
		        System.out.println(" Your Order Accepted Successfully ");
		        System.out.println("Press '0' to go to Previous menu");
	        
	        if((sc.next().equalsIgnoreCase(String.valueOf('0')))) {
	        	CliMain.showMainPage(vendorRecord);
	        }
		        
	        	  
	          }
  }catch(Exception ex) {
	   ex.printStackTrace();
	   }
 }
 public static ArrayList<OrderDetails> showAcceptedOrders(Vendor vendorRecord){
	  ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
 	   try{  
 	        Class.forName("com.mysql.cj.jdbc.Driver");  
 	       Connection con=DriverManager.getConnection(  
 	        "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
 	       PreparedStatement pst=con.prepareStatement("SELECT * FROM order_details WHERE order_status='Accepted' AND vendor_id = ?");
	          
	        pst.setInt(1,vendorRecord.getVendorID());
	         
	        ResultSet rs = pst.executeQuery();
 	        
 	        while(rs.next()) { 
 	        	Date dt =new Date();
 	        	ZoneId defaultZoneId = ZoneId.systemDefault();
 	        	Instant instant1 = dt.toInstant();
 	        	
 	        	LocalDate localDate1 = instant1.atZone(defaultZoneId).toLocalDate();
 	        	
                 Instant instant2 = dt.toInstant();
 	        	
 	        	LocalDate localDate2 = instant1.atZone(defaultZoneId).toLocalDate();
 	        	
 	        	orderList.add(new OrderDetails(
 	        			rs.getInt(1),
 	        			rs.getInt(5),
 	        			localDate1,
 	        			localDate2,
 	        			rs.getInt(4),
 	        			rs.getString(3),
 	        			rs.getDouble(8),
 	        			rs.getString(9),
 	        			rs.getInt(2)));
 	          
 	              } 
 	    }catch(Exception e){ System.out.println(e);}  
 	   if(orderList.size()>0) {
 		   return orderList;
 	   }else {
 		   return null;
 	   }
 }
}