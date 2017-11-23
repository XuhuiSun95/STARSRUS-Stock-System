package Utility;

import java.time.LocalDate;

public class Utility{
    public static final int OPENTIME = 9;    // time for opening the market
    public static final int CLOSETIME = 17;  // time for closing the market


    // parameters for connecting to the MySQL server
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String HOST = "";
    public static final String USER = "";
    public static final String PWD = "";

    public static LocalDate date;   // system date
    public static Boolean marketState; // open or closed

    public static void open_market(){
        marketState = true;
    }

    public static void close_market(){
        marketState = false;
    }

    public static Boolean market_is_open(){
        return marketState;
    }

    public static void set_current_date(){
        date = LocalDate.now();
    }

    public static void set_date(LocalDate d){
        date = d;
    }
}
