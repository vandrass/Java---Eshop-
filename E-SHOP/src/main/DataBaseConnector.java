package main;

import java.sql.*;
import java.util.ArrayList;


public class DataBaseConnector extends Configs {
    Connection dbConnection;


    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dpPass);
        return dbConnection;
    }






        /** method to adding a new customer to Data Base*/
    public void singUp(Customer customer){
        String insert = "INSERT INTO " +Const.USER_TABLE +" ("+Const.USER_NAME+","+Const.USER_PASS+","+
                Const.USER_ADDRESS+","+Const.USER_TEL+") "+"VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,customer.getUserName());
            prSt.setString(2,customer.getPassword());
            prSt.setString(3,customer.getAddress());
            prSt.setString(4,customer.getTelephone());


            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /****************************************/

    /** This method remove user fom system(data base).
     * Method argument  userName - name of user, that we will want delete
     * return true or false
     * */
    public boolean removeUser(String userName){
        int rs =0;
        String insert = "DELETE FROM " + Const.USER_TABLE+ " WHERE " + Const.USER_NAME +" = '"+userName+"'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeUpdate();
            if(rs>0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /******      get all products from data base to products array in shop class   ***/
    public boolean addProd(ArrayList<Product> products,int productId){
        ResultSet rs = null;
        String insert = "SELECT * FROM " +Const.PRODUCT_TABLE + " WHERE "+Const.PRODUCT_ID + " = '" + productId + "'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeQuery();
            while (rs.next()){
                products.add(new Product(
                        rs.getString(Const.PRODUCT_NAME),
                        rs.getInt(Const.PRODUCT_ID),
                        rs.getDouble(Const.PRODUCT_PRICE),
                        rs.getDouble(Const.PRODUCT_SALE)
                ));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    /************************************************************************/


    /** search customer for login*/
    public boolean getUser(Customer customer){
        ResultSet rs=null;
        String insert = "SELECT * FROM "+Const.USER_TABLE+" WHERE "+Const.USER_NAME+"='"+customer.getUserName()+ "' AND "+Const.USER_PASS+"='"+customer.getPassword()+"'" ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeQuery();
            while (rs.next()){
                customer.setAddress(rs.getString(Const.USER_ADDRESS));
                customer.setTelephone(rs.getString(Const.USER_TEL));
                customer.setStatus(true);
                return true;
            }


        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }return false;
    }
    /********************************************/


        /** Method for change users password*/
    public boolean changePass(String oldPass,String newPass, String userName){
        int rs=0;
        String insert = "UPDATE "+Const.USER_TABLE + " SET " + Const.USER_PASS + "='" + newPass + "'  WHERE " +  Const.USER_NAME + " ='" + userName +"' AND "+Const.USER_PASS+" = '"+oldPass+"'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeUpdate();
            if(rs==0) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**************************************************/



    /******* Address change method *************/
    public boolean changeAddress(String newAddress,String userName){
        int rs = 0;
        String insert = "UPDATE "+Const.USER_TABLE + " SET " + Const.USER_ADDRESS + "='" + newAddress + "'  WHERE " +  Const.USER_NAME + " ='" + userName +"'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeUpdate();
            if(rs==0) return false;
            else  return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  false;
    }

    /******* Telephone change method *************/
    public boolean changeTel(String tel,String userName) {
        int rs=0;
        String insert = "UPDATE "+Const.USER_TABLE + " SET " + Const.USER_TEL + "='" + tel + "'  WHERE " +  Const.USER_NAME + " ='" + userName +"'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeUpdate();
            if(rs==0) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
    /******************************************/



     /*****  changing product price method ****/
    public boolean changePrice(double price, int productId){
        int rs=0;
        String insert = "UPDATE "+Const.PRODUCT_TABLE + " SET " + Const.PRODUCT_PRICE + "='" + price + "'  WHERE " +  Const.PRODUCT_ID + " ='" + productId +"'";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeUpdate();
            if(rs==0) return false;
            else return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*****  changing product sale method ****/
    public boolean changeSale(double sale, int productId){
        int rs=0;
        String insert = "UPDATE "+Const.PRODUCT_TABLE + " SET " + Const.PRODUCT_SALE + "='" + sale + "'  WHERE " +  Const.PRODUCT_ID + " ='" + productId +"'";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeUpdate();
            if(rs==0) return false;
            else return true;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }





    /*********** get all users from data base ***********/
    public void getUsers(){
        ResultSet rs = null;
        String insert = "SELECT * FROM "+Const.USER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeQuery();
            while (rs.next()){

                System.out.printf(("%-20s : %-20s : %-30s : %-11s\n"),
                        rs.getString(Const.USER_NAME),
                        rs.getString(Const.USER_PASS),
                        rs.getString(Const.USER_ADDRESS),
                        rs.getString(Const.USER_TEL)
                );
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /******      get all products from data base    ***/
    public void getProducts(){
        ResultSet rs = null;
        String insert = "SELECT * FROM " +Const.PRODUCT_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            rs = prSt.executeQuery();
            while (rs.next()){
                System.out.printf(("%-15s : %10s : %10.2f $ : %10.2f %% \n"),
                        rs.getString(Const.PRODUCT_NAME),
                        rs.getInt(Const.PRODUCT_ID),
                        rs.getDouble(Const.PRODUCT_PRICE),
                        rs.getDouble(Const.PRODUCT_SALE)*100
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /************************************************************************/

    private boolean arrayIsOver(int size, int maxSize){
        if(size>=maxSize){
            System.out.println("maximum array length is: "+Const.ARRAY_LENGTH);
            return true;
        }else return false;
    }
}

