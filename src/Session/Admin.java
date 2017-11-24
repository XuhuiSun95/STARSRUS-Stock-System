package Session;

import Utility.Utility;
import java.io.Console;
import java.sql.*;
import Database.*;

public class Admin extends Session{

    // Override virtual function from super class
    @Override
    public void display_operations(){
        String options = "Please enter the number. Options:\n";
        options += "1: Open Market.\n";
        options += "2: Close Market.\n";
        options += "3: Set date.\n";
        options += "4: Set price.\n";
        options += "5: Exit";

        System.out.println(options);
    }

    @Override
    public void process_operations(String request){
        switch (request) {
            case "1":   this.open_market();
                        break;
            case "2":   this.close_market();
                        break;
            case "3":   this.set_date();
                        break;
            case "4":   this.set_price();
                        break;
            case "5":   Utility.logout = true;
                        break;
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        String QUERY = "SELECT * FROM Admin A WHERE A.username =" + " ' " + username + " ' " + "AND A.password = " + " ' " + password + " ' ";
        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if (!resultSet.next() ) {
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    // operation deltails
    public void open_market(){
        Utility.open_market();
    }

    public void close_market(){
        Utility.close_market();
    }

    public void set_date(){
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String date = c.readLine("Please enter the date in following form\n YYYYMMDD:");

        if(date.length() != 8){
            System.out.println("invalid input !");
            return;
        }

        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6,8));

        // check validity
        if(month > 12 || month < 1){
            System.out.println("invalid input !");
            return;
        }

        if(month )

        if(month )

        if(month )

        Utility.set_date(date);
    }

    public void set_price(){
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String symbol = c.readLine("Please enter the stock symbol:");
        String temp = c.readLine("Please enter the price");
        double price = Double.parseDouble(temp);

        ActorStockInfo_DB.update_price(temp, price);
    }
}
