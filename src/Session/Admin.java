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
        String QUERY = "SELECT * FROM Admin A WHERE A.username = " + "'" + username + "'" + " AND A.password = " + "'" + password + "'";
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

        for(int i = 0; i < date.length(); i++){
            if(!Character.isDigit(date.charAt(i))){
                System.out.println("invalid input !");
                return;
            }
        }

        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6,8));
        int old_year = Integer.parseInt(Utility.date.substring(0,4));
        int old_month = Integer.parseInt(Utility.date.substring(4,6));
        int old_day = Integer.parseInt(Utility.date.substring(6,8));

        // check validity
        if(month > 12 || month < 1){
            System.out.println("invalid input !");
            return;
        }

        int daysInMonth;
        switch (month) {
            case 1: // fall through
            case 3: // fall through
            case 5: // fall through
            case 7: // fall through
            case 8: // fall through
            case 10: // fall through
            case 12:
                daysInMonth = 31;
                break;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
            default:
                // returns 30 even for nonexistant months
                daysInMonth = 30;
        }
        if(day < 1 || day > daysInMonth){
            System.out.println("invalid input !");
            return;
        }

        if(year <= old_year){
            if(month <= old_month){
                if(day <= old_day){
                    System.out.println("invalid input !");
                    return;
                }
            }
        }

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
