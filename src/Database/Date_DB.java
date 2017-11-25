package Database;

import Utility.Utility;
import java.sql.*;
import java.sql.ResultSet;


public class Date_DB{

    public static String load_date(){
        String QUERY =  "SELECT * " +
                        "FROM Date ";
        ResultSet resultSet = Utility.sql_query(QUERY);

        String date = "";

        try{
            if(!resultSet.next()){
                System.out.println("current date not found!");
                System.exit(1);
            }

            date = resultSet.getString("d");
        } catch (Exception e){
            e.printStackTrace();
        }

        return date;
    }

    public static void store_date(String date){
        String UPDATE = "DELETE " +
                        "FROM Date ";
        Utility.sql_update(UPDATE);

        UPDATE = "INSERT INTO Date " +
                 "VALUES(" + "'" + date + "'" + ")";
        Utility.sql_update(UPDATE);
    }

}
