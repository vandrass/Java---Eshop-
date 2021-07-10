package main;

import java.util.Scanner;

/**
 * Authors:
 *  - Ivan Goncharov : 336182001
 *  - Denis Tsymbalenko : 336943226
 *   - 44/5 -
 * */

public class Main {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Shop shop = new Shop("E-Shop");
        Customer customer = new Customer();;
        int flag = 1;
        String answers = null;
        String choose = null;

        /***************      Main Menu      ***************/
        do {
            System.out.println("Welcome to our "+ shop.getShopName()+" of products");
            do{

            System.out.print("Do you have an account(y/n)?");
            answers = s.nextLine().trim();
            }while(!answers.equals("y") && !answers.equals("n"));

                if (answers.equals("y")) {
                    customer = new Customer();
                    customer.login(shop);
                } else if (answers.equals("n")) {
                    shop.singUp();
                    customer = new Customer();
                    customer.login(shop);
                }



            if (customer.getUserName()!=null&&customer.getUserName().equals("admin") && customer.getPassword().equals("admin")) {
                System.out.println("Hello admin!");
                do {
                    System.out.println("====================================================================");
                    System.out.println("Press command number to choose action!");
                    System.out.println("1. Remove customer from database");
                    System.out.println("2. Remove cart");
                    System.out.println("3. Customers Online");
                    System.out.println("4. How many cart was occupied");
                    System.out.println("5. Report sum of money from sales per day");
                    System.out.println("6. Report quantity of products sold per day");
                    System.out.println("7. Show all customers");
                    System.out.println("8. Show all products");
                    System.out.println("9. LogOut");
                    System.out.println("====================================================================");

                    choose = s.nextLine();
                    switch (choose) {
                        case "1":
                            shop.getAllUsers();
                            shop.removeCustomer();
                            break;
                        case "2":
                            System.out.println(shop.getCarts());
                            shop.removeCart();
                            break;
                        case "3":
                            System.out.println("Online now: " + shop.customersOnLine());
                            break;
                        case "4":
                            System.out.println("Occuped carts number is: "+shop.occupiedCarts());
                            break;
                        case "5":
                            System.out.println("Total Sales Today: " + shop.salesTotalSumReport());
                            break;
                        case "6":
                            System.out.println("Total Sold Quantity: " + shop.salesQuantityReport());
                            break;
                        case "7":
                            System.out.println(shop.getCustomers());
                            break;
                        case "8":
                            System.out.println(shop.getProducts());

                            break;
                        case "9":
                            choose = "9";
                            System.out.println("Good bye admin!");
                            break;
                    }
                } while (choose != "9");

            } else if(customer.getUserName()!=null) {
                System.out.println("Hello " + customer.getUserName() + "!");
                do {

                    System.out.println("====================================================================");
                    System.out.println("Press command number to choose action!");
                    System.out.println("1. Log Out");
                    System.out.println("2. Change password");
                    System.out.println("3. Change Address");
                    System.out.println("4. Change Telephone");
                    System.out.println("5. Add product to cart");
                    System.out.println("6. Remove product from cart");
                    System.out.println("7. Show buying bill");
                    System.out.println("8. Payment");
                    System.out.println("9. Data of customer");
                    System.out.println("10. Delete all products");
                    System.out.println("11. Sum of all Sales");
                    System.out.println("12. Cost Of All Products Include Sale Price");
                    System.out.println("13. Show all products in cart");
                    System.out.println("====================================================================");


                    choose = s.nextLine();

                    switch (choose) {

                        case "1":
                            customer.logout(shop);
                            System.out.println("Good bye!");
                            choose="1";

                            break;
                        case "2":
                            customer.changePassword();
                            break;
                        case "3":
                            customer.changeAddress();
                            break;
                        case "4":
                            customer.changeTelephone();
                            break;
                        case "5":
                            System.out.println("Enter Id product :");
                            shop.getAllProducts();
                            int productId = s.nextInt();
                            s.nextLine();
                            System.out.println();
                            customer.addProductToCart(productId);
                            break;
                        case "6":
                            System.out.println("Enter Id product from :");
                            customer.removeProductFromCart(s.nextInt());
                            s.nextLine();
                            break;
                        case "7":
                            customer.getBill();
                            System.out.println("You saved on this buying: "+customer.cart.sumOfAllSales()+"$");
                            break;
                        case "8":
                            customer.payment(shop);
                            break;
                        case "9":
                            System.out.println(customer.customerData());
                            break;
                        case "10":
                            customer.cart.deleteAllProducts();
                            break;
                        case "11":
                            System.out.println(customer.cart.sumOfAllSales());
                            break;
                        case "12":
                            System.out.println(customer.cart.costOfAllProductsIncludeSalePrice());
                            break;
                        case "13":
                            System.out.println("Products in cart:");
                            System.out.println(customer.cart.showProductsInCurrentCart());
                            break;

                    }
                } while (choose != "1");



            }
            System.out.println("Do you want continue? n/y:\n");
            answers=s.nextLine().trim();
            if(answers.equals("n")) flag =-1;
        }while (flag!=-1);
    }
}



