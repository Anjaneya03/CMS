package com.Hexaware.CMS.Cli;

import java.sql.SQLException;
import java.util.Scanner;

import com.Hexaware.CMS.Entities.CustInfo;
import com.Hexaware.CMS.Entities.FoodDetails;
import com.Hexaware.CMS.Entities.OrderDetails;
import com.Hexaware.CMS.Entities.Vendor;
import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Persistence.OrderDb;

/* CliMain used as Client interface for java coading.
 * @author hexware
 */
public class CliMain {
    
    static Scanner sc=new Scanner(System.in);
/**
 * main method  used to display the option we had in the application.
 * @throws SQLException 
 */
    public static void main( String[] args ) throws SQLException
    {     
    	showLandingPage();
        
    }
    
    
    public static void showLandingPage() throws SQLException {
    Vendor vendorRecord = new Vendor();
    	CustInfo custInfo = new CustInfo();
      	System.out.println("Canteen Management System" );      
        System.out.println("Enter your choice....");
        System.out.println("1.Customer Login");
        System.out.println("2.Vendor Login");
        System.out.println("3.Customer Registration");
        System.out.println("4.Vendor Registration");
        System.out.println("5.Change Password");
        System.out.println("6.Exit");
        
        int choice=sc.nextInt();
        switch(choice){
            case 1:
            	custInfo = OrderDb.userLogin();
            	if(custInfo!=null && custInfo.getCustomerID()!=null) {
            		showMainPage(custInfo);
            	}else {
            		System.out.println("Customer not found");
       		        OrderDb.navigationBack();
       		       
            	}
                break;
            case 2:
            	vendorRecord=OrderDb.vendorLogin();
              	if(vendorRecord!=null && vendorRecord.getVendorName()!=null) {
                  	showMainPage(vendorRecord);
                  }else {
                  	System.out.println("Record Not found");
                  	OrderDb.navigationBack();
                  }
                break;
            case 3:
            	OrderDb.registerUser();    
            	
                break;
            case 4:
              OrderDb.registerVendor();  
              
            	break;
               
            case 5:
            	OrderDb.changePassword();
               
                break;
            case 6:
            	
                Runtime.getRuntime().halt(0);
                break;
            default:
                System.out.println("Choose option between 1 to 4");
        }
    }
    
    
 
    /**
     * Method for displaying conditional switch
     * @throws SQLException 
     */
    public static void showMainPage(CustInfo customerInfo) throws SQLException {
       	System.out.println( "\n" );
    	System.out.println( "----------------------------" );
    	System.out.println( "Canteen Management System" );
    	System.out.println( "----------------------------" );
    	System.out.println( "Hello  "+customerInfo.getCustomerName()+" !!");
    	System.out.println( "----------------------------" );
    	System.out.println( "Customer ID: "+customerInfo.getCustomerID() );
    	System.out.println( "----------------------------" );
    	System.out.println( "\n" );
        System.out.println("Enter your choice....");
        System.out.println("1.Show Menu");
        System.out.println("2.Placing Order");
        System.out.println("3.Order History");
        System.out.println("4.My Profile");
        System.out.println("5.Wallet Balance");
        System.out.println("6.Cancel Order");
        //System.out.println("7.Rating Order");
        //System.out.println("8.Calories Consumption");
        System.out.println("7.Logout");

        
        int choice=sc.nextInt();
        switch(choice){
            case 1:
                menuList();
                showMainPage(customerInfo);
                break;
            case 2:
                placeOrder(customerInfo);    
                break;
            case 3:
                showOrderHistory(customerInfo);
                break;
            case 4:
            	OrderFactory.CustomerProfile(customerInfo);
                //Runtime.getRuntime().halt(0);
                break;
            case 5:
            	OrderFactory.showWalletBalance(customerInfo);
            	break;
            case 6:
            	OrderFactory.cancelorder(customerInfo);
            	break;
            case 7:
            	CliMain.showLandingPage();
            	break;
            default:
                System.out.println("Choose option between 1 to 7");
        }
    	
    }
    public static void showMainPage(Vendor vendorRecord) throws SQLException {OrderDetails orderDetail = new OrderDetails();
	System.out.println("--------------------------------");
	System.out.println( "Canteen Management System" );
	System.out.println("--------------------------------");
	System.out.println( "VendorID : "+ vendorRecord.getVendorID());
	System.out.println( "Hello :"+ vendorRecord.getVendorName());
	System.out.println("--------------------------------");
	System.out.println("\n");
    System.out.println("Enter your choice....");
    System.out.println("1.Show Menu");
    //System.out.println("2.Placing Order");
    //System.out.println("3.Order History");
    System.out.println("2.Vendor Profile");
    //System.out.println("5.Wallet Balance");
    System.out.println("3.Accept/Reject Order");
    System.out.println("4.Accepted Orders");
    System.out.println("5.Logout");
    System.out.println("--------------------------------");
    
    int choice=sc.nextInt();
    switch(choice){
        case 1:
            menuList();
            showMainPage(vendorRecord);
            break;
        case 2:
        	OrderFactory.getvendorProfile(vendorRecord);
        	showMainPage(vendorRecord);
            break;
        case 3:
        	OrderFactory.showPendingOrders(vendorRecord);
        	//showLoginPage(v);
        	break;
        case 4:
        	OrderFactory.showAcceptedOrders(vendorRecord);
        	//OrderFactory.customerProfile(cs);
        	//showLoginPage(cs);
        	break;
        case 5:
        	OrderDb.navigationBack();
        	//OrderFactory.showWalletBalance(cs);
        	break;
        
        default:
            System.out.println("Choose among the options");
}
    	
    	
    }
    
  
    
  
    /**
     * this method  is to place food order.
     * @throws SQLException 
     */
       
        	public static void placeOrder(CustInfo customerInfo) throws SQLException{
                menuList();
                double totalAmt = 0;
                FoodDetails fi = new FoodDetails();
                System.out.println("------------------------");
                //System.out.println("Do you want to review the ratings");
                System.out.println("Do you want to place order ?");
                System.out.println("----------------------------------");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.println("----------------------------------");
                Scanner sc = new Scanner(System.in);
                OrderDetails od = new OrderDetails();
                if(sc.nextInt()==1) {
                	 System.out.println("----------------------------------");
                	 System.out.println("Enjoy Your Food...!");
                	 System.out.println("----------------------------------");
                	 System.out.println("Your wallet Balance is :"+ customerInfo.getWalletBalance());
                	 System.out.println("Choose your Food Option");
                	 System.out.println("----------------------------------");
                	 
                	 
                     System.out.println("Enter the Food No. to be placed");
                   	 int fid=sc.nextInt();
                   	 fi = OrderDb.getfoodDetails(fid);
                   	 if(fi!=null && fi.getFoodName()!=null) {
                   		 System.out.println("Food Item Name: "+ fi.getFoodName());
                   	 }
                   	 System.out.println("Enter the Food Quantity");
                        int  fquan=sc.nextInt();
                       
                        
                     //System.out.println("Total" + totalAmt);
                	 totalAmt = totalAmt + fquan*fi.getFoodPrice();
                	 
                     //System.out.println("Do you want to add more item");
                     //System.out.println("Press 1 for more");
                     System.out.println("Press 2 for placing order");
                     if(sc.nextInt()==2) {
                    	 System.out.println("Amount to be paid: " + totalAmt);
                    	 if(totalAmt>customerInfo.getWalletBalance()) {
                    		 System.out.println("Sorry You dont have enough balance. Your current wallent balance is : "+customerInfo.getWalletBalance());
                    		   OrderDb.navigationBackToMainPage(customerInfo);
                    	 }else {
                    		 System.out.println("Are you sure? :");
                    		 System.out.println("1.Yes");
                    		 System.out.println("2.No");
                    		 if(sc.nextInt()==1) {
                    			 od.setCustomerId(customerInfo.getCustomerID());
                    			 od.setFoodId(fi.getFoodId());
                    			 od.setOrderQuantity(Integer.valueOf((fquan)));
                    			 od.setOrderValue(totalAmt);
                    			 od.setOrderStatus("Placed");
                    			 OrderDb.placeOrder(od,customerInfo);
                    			
                    		 }
                    	 }
                     }
                     }
        	}
                
        	 public static double getTotalAmtEachItem() {
                 FoodDetails fi = new FoodDetails();
                 System.out.println("Enter the Food No. to be placed");
               	 int fid=sc.nextInt();
               	 fi = OrderDb.getfoodDetails(fid);
               	 if(fi!=null && fi.getFoodName()!=null) {
               		 System.out.println("Food Item Name: "+ fi.getFoodName());
               	 }
               	 System.out.println("Enter the Food Quantity");
                    int  fquan=sc.nextInt();
                    return (fquan*fi.getFoodPrice());
                }
        	 
        	 public static void showOrderHistory(CustInfo customerInfo) throws SQLException {
        		  OrderFactory.fetchOrderHistory(customerInfo);
        	 }
	/*

        System.out.println("Enter the Food id");
        int fid=sc.nextInt();
        System.out.println("Enter the Food Name");
        String fname=sc.next();
        System.out.println("Enter the Food Price");
        int fprice=sc.nextInt();
        System.out.println("Enter the Food Quantity");
        int  fquan=sc.nextInt();

        //Add other attributes to complete the functionality
        int r= OrderFactory.OrderFood(fid,fname,fprice,fquan);
        System.out.println(r+"   is inserted.....");
        }
/**
 * this method is to fetch Menu list.
 */
        public static void menuList(){
        Menu m[]=OrderFactory.fetchMenu();
        System.out.println("Food Id"+"    "+"Food Name"+"    "+"Food Price"); 
        for(int i=0;i<m.length;i++){
              System.out.println(m[i].getFoodId()+"       "+m[i].getFoodName()+"       "+m[i].getFoodPrice());
           }
    }
/**
 * this method is to acceptRejectOrder.
 */
 //public static String acceptRejectOrder(){


 /**
 * this method is for customerProfile.
 */
// public static Customer customerProfile(){}
/*public static CustInfo CustomerProfile() {
	CustInfo dis = new CustInfo();
	dis =OrderFactory.CustomerProfile();
	System.out.println(dis);}*/

/**
 * this method is for VendorProfile.
 */
 public static Vendor vendorProfile() {
Vendor dis = new Vendor();
dis= OrderFactory.vendorProfile();
return dis;
}
// public static Vendor vendorProfile(){}

/**
 * this method is for VendorOderHistory.
 */
// public static Vendor[] VendorOderHistory(){}
/*public static Vendor[] VendorOrderHistory() {
	Vendor dis =new Vendor();
	dis=OrderFactory.VendorHistory();
}
/* 
 * this method is for CustomerOrderHistory.
 */
 //public static CustInfo[] CustomerOrderHistory(){
//	CustInfo dis=new CustInfo();
	// dis=OrderFactory.CustomerOrderHistory();
	//return null;}
}
