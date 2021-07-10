package main;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * fields:
 * userName - customer nick name for enter to system
 * password - password for enter to system
 * address - delivery address
 * telephone - telephone number
 * cart - shopping cart, object
 * status - indicate if customer online
 *
 */
public class Customer {

    private String userName;
    private String password;
    private String address;
    private String telephone;
    protected Cart cart;
    private boolean status;



    /** Constructors */
    public Customer() {

        this(null,null,null,null,false);
    }
    public Customer(String userName, String password, String address, String telephone) {
        setUserName(userName);
        setPassword(password);
        setAddress(address);
        setTelephone(telephone);
        setStatus(false);
        cart = new Cart();
    }


   public Customer(String userName, String password, String address, String telephone,boolean status) {
        setUserName(userName);
        setPassword(password);
        setAddress(address);
        setTelephone(telephone);
        setStatus(status);
        cart = new Cart();
    }
    /**
     * ArrayList<Customer> customers - array with users in system
     * */
    public void login(Shop shop){

        DataBaseConnector db = new DataBaseConnector();//connection to data base
        Scanner in = new Scanner(System.in);

        System.out.println("\n*Login* \nEnter user name: ");
        this.setUserName(in.nextLine().trim());

            System.out.println("Enter Password: ");
            this.setPassword(in.nextLine().trim());


        /** 1.this block check if login and password exsist in data base and pull data to this object,
         *  else get message and exit from method
         * and add to ArrayList customers if customer not exist in array
         * 2.if customer exist in ArrayList - status will change to true(online)
         * 3.if customer already online(status true) - get massage and exit from method
         * */
        if(db.getUser(this)){
        for (Customer customer:shop.customers) {
            if(this.getUserName().equals(customer.getUserName())){

                if(customer.isStatus()==true){
                    System.out.println("Logged User");
                    return;
                    }
                shop.customers.set(shop.customers.indexOf(customer),this);// return login
                this.takeCart(shop.carts);
                return;
                }
            }
            shop.customers.add(this);// adding customer to shop from db
            shop.addCart(); // adding cart to shop by first login
            this.takeCart(shop.carts);
        }else {
            this.setUserName(null);
            this.setPassword(null);
            this.cart=null;
            System.out.println("User not Founded");
        }

    }

    public void logout(Shop shop){
        setStatus(false);
        for(Cart cart:shop.carts){
            if(userName==cart.getCartClientName()){
                this.returnCart(cart);
            }
        }

    }


    /** method for password changing
     * input old and new password
     * check in data base if password is right and user exist
     * and will change the password
     * return true if password changed
     * return false if action failed
     * */
    public boolean changePassword(){
        String  oldPass, newPass;
        DataBaseConnector db = new DataBaseConnector();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Your Password: ");
        oldPass=in.nextLine().trim();
        System.out.println("Enter NEW Password: ");
        newPass=in.nextLine().trim();
        if(db.changePass(oldPass, newPass,getUserName())&&newPass!=""){
            setPassword(newPass);
            System.out.println("Password was changed Successfully!");
            return true;
        }else {
            System.out.println("Password not changed!");
            return false;
        }

    }

    public boolean changeAddress(){
        String address;
        DataBaseConnector db = new DataBaseConnector();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a new Address");
        address = in.nextLine().trim();
        if(db.changeAddress(address, getUserName())&&address!=""){
            setAddress(address);
            System.out.println("Address was changed!");
            return true;
        }else {
            System.out.println("Address was not changed!!!!");
            return false;
        }

    }


    /**** */
    public boolean changeTelephone(){
        String telephone;
        DataBaseConnector db = new DataBaseConnector();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a new Telephone");
        telephone = in.nextLine().trim();
        if(db.changeTel(telephone,getUserName())&&telephone!=""){
            setTelephone(telephone);
            System.out.println("Telephone was changed");
            return true;
        }else {
            System.out.println("Telephone was not changed!!!!");
            return false;
        }


    }

    /** takeCart() method:
     * this method take free cart after login
     * */
    private void takeCart(ArrayList<Cart> carts){
        for(Cart cart:carts){
            if(cart.getCartClientName()==null);
            setCart(cart);
            System.out.println("Cart Added successfully");
            return;
        }
        System.out.println("All carts are reserve");

    }

    /**
     * returnCart() method:
     * this method return cart after logout
     * */
    private void returnCart(Cart cart){
        cart.setCartClientName(null);
        cart.deleteAllProducts();
    }
    /**
     *  adding product into cart by productId
     * */
    public void addProductToCart(int productId){
        cart.addProduct(productId);
    }
    /**
     * remove product from cart by productId
     * */
    public void removeProductFromCart(int productId){
        cart.deleteProduct(productId);
    }

    public void getBill(){
        System.out.println(cart.showProductsInCurrentCart());
        System.out.println("Total Sum is:\n"+(cart.costOfAllProductsIncludeSalePrice()+cart.sumOfAllSales())+" - "+cart.sumOfAllSales()+ " = " + cart.costOfAllProductsIncludeSalePrice()+"$");
    }


    /**
     * method check out of all products
     * */
    public void payment(Shop shop){
        String accept=null;
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to pay? y/n ");
        accept = in.nextLine();
        if(accept.equals("y")){
            cart.addProductsToShopArray(shop.products);
            cart.deleteAllProducts();
            System.out.println("payment was successfully");
            return;
        }
        System.out.println("not payed!");

    }
    /**     Getters and Setters  ****/
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean isStatus() {
        return status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public void setAddress(String address) {
        this.address = address;

    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        this.cart.setCartClientName(userName);
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    /**************************************/


    public StringBuilder customerData() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserName: ");
        sb.append(userName);
        sb.append(" Password: ");
        sb.append(" Address: ");
        sb.append(address);
        sb.append(" Telephone: ");
        sb.append(telephone);
        sb.append(" Status: ");
        sb.append(status);
        sb.append("\n");
        return sb;

    }
}
