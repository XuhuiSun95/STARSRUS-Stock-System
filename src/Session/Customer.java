package Session;

import Utility.Utility;
import java.sql.*;
import Database.*;


public class Customer extends Session{
    private String marketAccountID;
    private String stockAccountID;

    // Override virtual function from super class
    @Override
    public void display_operations(){
        String options = "Please enter the number. Options:\n";
        options += "1: Deposit.\n";
        options += "2: Withdrawl.\n";
        options += "3: Buy.\n";
        options += "4: Sell.\n";
        options += "5: Show Balance.\n";
        options += "6: List Stock Info.\n";
        options += "7: List Moive Info. \n";

        System.out.println(options);
    }

    @Override
    public void process_operations(String request){
        switch (request) {
            case "1":   this.deposit();
                        break;
            case "2":   this.withdrawl();
                        break;
            case "3":   this.buy();
                        break;
            case "4":   this.sell();
                        break;
            case "5":   this.show_balance();
                        break;
            case "6":   this.list_actor_stock_info();
                        break;
            case "7":   this.list_movie_info();
                        break;
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        Connection connection = Utility.connection;
        Statement statement = null;
        try{
            // find the username and password pair entity and get the tax id
            String taxID = Customer_DB.get_tax_id(username, password);

            // if tax id does not exist
            if(taxID.equals(new String("-1"))){
                return false;
            }

            marketAccountID = AccountMarket_DB.get_market_account_id(taxID);
            stockAccountID = AccountStock_DB.get_stock_account_id(taxID);

            return true;
        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // operation deltails
    public void deposit(){

    }

    public void withdrawl(){

    }

    public void buy(){

    }

    public void sell(){

    }

    public void show_balance(){
        double balance = AccountMarket_DB.get_account_balance(marketAccountID);
        System.out.println("Current balance: " + (new Double(balance)).toString());
    }

    public void list_actor_stock_info(){
        String res = ActorStockInfo_DB.list_all();
        System.out.println("All Actor and Stock Info:\n" + res);
    }

    public void list_movie_info(){

    }
}
