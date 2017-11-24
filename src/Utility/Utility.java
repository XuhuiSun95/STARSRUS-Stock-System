package Utility;

import java.sql.*;;
import java.sql.ResultSet;
import java.time.LocalDate;

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

    public static LocalDate date;   // system date
    public static Boolean marketState; // open or closed
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

    public static void set_current_date(){
        date = LocalDate.now();
    }

    public static void set_date(LocalDate d){
        date = d;
    }
}
