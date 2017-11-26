package Database;

import Utility.Utility;
import java.sql.*;

public class State_DB{
    public static void close_market(){
        String UPDATE = "DELETE " +
                        "FROM State ";
        Utility.sql_update(UPDATE);

        UPDATE = "INSERT INTO State " +
                        "VALUES(" + "0" + ")";
        Utility.sql_update(UPDATE);
    }


    public static void open_market(){
        String UPDATE = "DELETE " +
                        "FROM State ";
        Utility.sql_update(UPDATE);

        UPDATE = "INSERT INTO State " +
                        "VALUES(" + "1" + ")";
        Utility.sql_update(UPDATE);
    }

    public static Boolean is_open(){
        String QUERY =  "SELECT * " +
                        "FROM State";
        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if(!resultSet.next()){
                System.out.println("current date not found!");
                System.exit(1);
            }

            if(resultSet.getInt("s")==1){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
