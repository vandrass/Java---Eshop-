package main;

import java.util.ArrayList;


public class Cart {
	
	private int cartId = 0;   
	private static int CartIdCount = 1000;   //variable for auto increment
    private ArrayList<Product> products = new ArrayList<>();         //array collection products (0-100) elements
    private int productCounter = 0;
    private String cartClientName;


    public Cart() {
        this(null);
    }
    public Cart(String cartClientName)
    {
        cartId = CartIdCount++;
        this.cartClientName = cartClientName;
    }

    /**
     * Validation productCounter -> if prod array from 0-100
     * */
    private boolean prodCountValidation(int productCounter)
    {
        if (productCounter >= 0 && productCounter < 100) 
            return true;
        return false;    
    }
    

    
    /**
     * function addProduct -> function add product in array products
     * param: obj -> object from class Product
     * */
    public void addProduct(int productId){
        DataBaseConnector db = new DataBaseConnector();//connection to data base
        if(db.addProd(this.products,productId)){
            this.products.trimToSize();
            productCounter++;
            System.out.println("Product Added");
        }else System.out.println("Item out of stock");

       //products.add(new Product());
    }
    /**
     * func deleteProduct -> function delete product from array products
     *  param: obj -> object from class Product
     * */
    public void deleteProduct(int productId){
        for (Product product:this.products) {
            if (product.getProductId()==(productId)) {
                this.products.remove(product);
                this.products.trimToSize();
                System.out.println("item removed");
                productCounter--;
                return;
            };
        }
        System.out.println("item NOT removed");
      }
    /**func deleteAllProducts -> function delete all products from array products
     *param: void
     */
    public void deleteAllProducts(){

        products.removeAll(products);
        productCounter=0;
        }

    
        /**function percentOfAllSalesInCartProducts -> function for count percent of all sales in all products in cart
         * param: void
         * Return -> res -> sum of all sales
         * */
         public double sumOfAllSales() {
            double res =0;
            for(Product product:products){
                res += product.getProductPrice()*product.getProductSale();
            }
            return res;
        
        
        }
    /**function costOfAllCartProducts -> function for count ->  (sum) of  all products in cart include sales
     * param: void
     * Return -> sum -> sum of all prices
     * */
    public double costOfAllProductsIncludeSalePrice() {
        double sum =0;
        for(Product product:products){
            sum += product.getProductPrice();
        }
        sum -= sumOfAllSales();  //sum of all prices -= sum of all sales
        return sum;
    }


    /** adding pyed products to shop products array*/
    public void addProductsToShopArray(ArrayList<Product> products){
        for(Product product:this.products) {
            products.add(product);
        }
    }

    /** Getters and Setters */
    public int getProductCounter() {
        return productCounter;
    }

    public int getCartId() {
        return cartId;
    }

    public String getCartClientName() {
        return cartClientName;
    }

    public void setCartClientName(String cartClientName) {
        this.cartClientName = cartClientName;
    }
    /**************************************/



    /**  function toString -> output */
    @Override
    public String toString(){
        return  "Count of products in cart =  " + productCounter
                + "\nCart ID = " + cartId + "\nClient cart  Name = " + cartClientName + "\n" ;
    }

    /**  show all products in cart */
    public StringBuilder showProductsInCurrentCart() {
        StringBuilder sb = new StringBuilder();
        for(Product product:products )  {
            sb.append(product.getProductId());
            sb.append(" ");
            sb.append(product.getProductName());
            sb.append(" ");
            sb.append(product.getProductPrice());
            sb.append("$ ");
            sb.append(product.getProductSale()*100);
            sb.append("%");
            sb.append("\n");

        }
        System.out.println();
        return sb;
    }
}
        

