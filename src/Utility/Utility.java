package Utility;

import java.sql.*;;
import java.sql.ResultSet;
import Database.Date_DB;
import java.io.Console;

public class Utility{
    public static final int OPENTIME = 9;    // time for opening the market
    public static final int CLOSETIME = 17;  // time for closing the market


    // parameters for connecting to the MySQL server
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String HOST = "jdbc:mysql://localhost/xuhui_sunDB";
    public static final String USER = "root";
    public static final String PWD = "950802cherry";

    public static Connection connection;
    public static Statement statement;

    public static String date;   // system date
    public static Boolean marketState; // open or closed

    public static double interestRate = 0.03;

    public static Boolean logout = false; // logout flag

    public static ResultSet sql_query(String QUERY){
        Connection connection = Utility.connection;
        Statement statement = Utility.statement;
        ResultSet resultSet = null;
        try{
            // find the username and password pair entity
            resultSet =  statement.executeQuery(QUERY);
            return resultSet;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        //finally {
        //    try {
        //        if(statement != null)
        //            statement.close();
        //    } catch(Exception e){
        //        e.printStackTrace();
        //    }
        //}
        return resultSet;
    }

    public static void sql_update(String UPDATE){
        Connection connection = Utility.connection;
        Statement statement = null;
        try{
            // find the username and password pair entity
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE);
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(statement != null)
                    statement.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void open_market(){
        marketState = true;
    }

    public static void close_market(){
        marketState = false;
    }

    public static Boolean market_is_open(){
        return marketState;
    }

    // public static void set_current_date(){
    //     date = LocalDate.now();
    // }

    public static void set_date(String d){
        date = d;
        store_date();
    }

    public static void load_date(){
        date = Date_DB.load_date();
    }

    public static void store_date(){
        Date_DB.store_date(date);
    }



    public static Boolean sign_up(){
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String username = c.readLine("Username: ");

        if(Customer_DB.username_exist(username)){
            System.out.println("Username already exist");
            return false;
        }

        String username = c.readLine("Password: ");
        String taxID = c.readLine("TaxID: ");
        String address = c.readLine("Address: ");
        String STATE = c.readLine("STATE: ");
        String phone = c.readLine("Phone: ");
        String email = c.readLine("Email: ");
        String ssn = c.readLine("ssn: ");

    }

}
