package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    private String shopName;
    protected ArrayList<Customer> customers = new ArrayList<>(100); // array collection of users
    protected ArrayList<Cart> carts = new ArrayList<>(100);// array collection of carts
    protected ArrayList<Product> products = new ArrayList<>(1000);// array collection of products

    /** constructor*/
    public Shop(String name) {
        shopName = name;
    }

    public void singUp(){
        Scanner in = new Scanner(System.in);
        Customer newUser = new Customer();
        System.out.println("\n*Registration* \nEnter user name: ");
        newUser.setUserName(in.nextLine().trim());

        System.out.println("Enter Password: ");
        newUser.setPassword(in.nextLine().trim());

        System.out.println("Enter Address: ");
        newUser.setAddress(in.nextLine().trim());

        System.out.println("Enter Telephone: ");
        newUser.setTelephone(in.nextLine());

        DataBaseConnector Db = new DataBaseConnector();
        Db.singUp(newUser);

        customers.add(newUser);

    }
    /**   ДОПИСАТЬ КОД */
    public void removeCustomer(){
        Scanner in = new Scanner(System.in);
        DataBaseConnector db = new DataBaseConnector();
        String userName = null;

        System.out.println("Enter customer name for removing:");
        userName = in.nextLine().trim();
        if(db.removeUser(userName)){
            for (Customer customer:this.customers){
                if (customer.getUserName().equals(userName)){
                customers.remove(customer);
                customers.trimToSize();}
            }
            System.out.println("User was removed!");
        }else System.out.println("User not exist");




    }

    /**
     * add cart to carts array
     * */
    public void addCart(){
        if(carts.size()<Const.ARRAY_LENGTH) {
            carts.add(new Cart());
        }else System.out.println("maximum carts");
    }
    public void removeCart(){
        int id;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter cart id:");
        id = in.nextInt();
        for (Cart cart:carts){
            if(cart.getCartId()==id){
                carts.remove(cart);
                carts.trimToSize();
                return;
            }
        }

    }

    /** how many customers shopping now*/
    public int customersOnLine(){
            int i = 0;
            for(Customer customer:customers){
                if(customer.isStatus()) i++;
            }
            return i;
    }

    /** how myny cart was occupied*/
    public int occupiedCarts(){
        int i = 0;
        for(Cart cart:carts){
            if(cart.getCartClientName()!=null) i++;
        }
        return i;
    }


    /** report sum of money from sales per day*/
    public double salesTotalSumReport(){
        double sum = 0;
        for(Product product:products){
            sum+=product.productPrice-(product.productPrice*product.productSale);
        }
        return sum;
    }

    /** report products quantity were sold per day*/
    public int salesQuantityReport(){
        return products.size();
    }


    public StringBuilder getCustomers() {
        StringBuilder sb = new StringBuilder();
        customers.forEach(customer ->   {
            sb.append(customer.getUserName());
            sb.append(" ");
            sb.append(customer.getPassword());
            sb.append(" ");
            sb.append(customer.getAddress());
            sb.append(" ");
            sb.append(customer.getTelephone());
            sb.append(" ");
            sb.append(customer.isStatus());
            sb.append("\n");

        });
        System.out.println();
        return sb;
    }
    public StringBuilder getProducts() {
        StringBuilder sb = new StringBuilder();
        products.forEach(product ->   {
            sb.append(product.getProductId());
            sb.append(" ");
            sb.append(product.getProductName());
            sb.append(" ");
            sb.append(product.getProductPrice());
            sb.append("$ ");
            sb.append(product.getProductSale());
            sb.append("%");
            sb.append("\n");

        });
        System.out.println();
        return sb;
    }

    public StringBuilder getCarts() {
        StringBuilder sb = new StringBuilder();
        carts.forEach(cart ->   {
            sb.append(cart.getCartId());
            sb.append(" ");
            sb.append(cart.getProductCounter());
            sb.append(" ");
            sb.append(cart.getCartClientName());
            sb.append("\n");

        });
        System.out.println();
        return sb;
    }

    public String getShopName() {
        return shopName;
    }

    public void getAllProducts(){
        DataBaseConnector db = new DataBaseConnector();
        db.getProducts();
    }
    public void getAllUsers(){
        DataBaseConnector db = new DataBaseConnector();
        db.getUsers();
    }
}
