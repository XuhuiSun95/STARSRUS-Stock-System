package Utility;

import java.sql.*;;
import java.sql.ResultSet;
import Database.Date_DB;
import Session.Manager;

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
        int pass = Integer.parseInt(d.substring(4,6))-Integer.parseInt(date.substring(4,6));
        if(pass>1){
            System.out.println("cannot fast forward more than 1 month");
            return;
        }else if(pass==1){
            int year = Integer.parseInt(date.substring(0,4));
            int month = Integer.parseInt(date.substring(4,6));

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
            date = date.substring(0,6) + String.valueOf(daysInMonth);
            store_date();
            Manager M = new Manager();
            M.add_interest();
            M.delete_transaction();
        }
        date = d;
        store_date();
    }

    public static void load_date(){
        date = Date_DB.load_date();
    }

    public static void store_date(){
        Date_DB.store_date(date);
    }

}
