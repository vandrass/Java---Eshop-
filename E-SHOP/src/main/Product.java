package main;


import java.util.Scanner;

public class Product {
	
	    private String productName;
	    private  int productId;
	    protected double productPrice;
	    protected double productSale;
	    
	/**   CONSTRUCTORS  **/
	    public Product()
	    {  
			this(null,0,0,0);
	    }

	    public Product(String productName,int productId, double productPrice, double productSale)
	    {
	        setProductName(productName);
	        setProductId(productId);
	        setProductPrice(productPrice);
	        setProductSale(productSale);
	    }

	/**
	 *method change a product price in data base and if its successfully,
	 *  method will change in products array too
	 *  return true - success or false - failure
	 * **/
	    public boolean changePrice(){
	    	double newPrice;
	    	DataBaseConnector db = new DataBaseConnector();
			Scanner in =new Scanner(System.in);
			System.out.println("Enter a New price ");
			newPrice = in.nextDouble();
			if(db.changePrice(newPrice,getProductId())&&newPrice!=0){
				setProductPrice(newPrice);
				System.out.println("Price was changed Successfully!");
				return true;
			}else{
				System.out.println("Price was NOT changed!");
				return false;
			}
	}

	/**
	 *method change a product sale percent in data base and if its successfully,
	 *  method will change in products array too
	 *  sale - need enter by percent (0-100)
	 *  return true - success or false - failure
	 * **/
		public boolean changeSale(){
			double newSale;
			DataBaseConnector db = new DataBaseConnector();
			Scanner in =new Scanner(System.in);
			System.out.println("Enter a New sale in percent(%) ");
			newSale = in.nextDouble()/100;
			if(db.changePrice(newSale,getProductId())&&newSale>=0){
				setProductSale(newSale);
				System.out.println("sale % was changed Successfully!");
				return true;
			}else{
				System.out.println("Sale % was NOT changed!");
				return false;
			}
	}


	   /**SETTERS and GETTERS*/
	    public void setProductName(String productName){

	    	this.productName = productName;
	    }
	    public void setProductPrice(double productPrice){
	        if(productPrice > 0)	//check that price of prod > 0   ==============================try catch if you want here 
	            this.productPrice = productPrice;
	    }
	    public void setProductSale(double productSale){
	        if (productSale >= 0 && productSale < 1)  //check that sale between [0(include => no sale) - 1 (not include)] -> percent % 
	            this.productSale = productSale;		//==============================try catch if you want here
	    }
	    public void setProductId(int productId){

	    	this.productId = productId;
		}
	    public String getProductName(){

	    	return this.productName;
	    }
	    public double getProductPrice(){

	    	return this.productPrice;
	    }
	    public double getProductSale(){

	    	return this.productSale;
	    }
	    public int getProductId(){

	    	return this.productId;
	    }
	    /********************************/

	    //function toString -> output
	    @Override
	    public String toString(){
	        return "Product name = " + productName + "\nProduct ID = " + productId + "\nProduct price = " + productPrice + "\nProduct Sale = " + productSale + "\n";
	    }
	    
	    
	    
	}

