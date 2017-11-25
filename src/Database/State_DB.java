package Database;

import Utility.Utility;
import java.sql.*;

public static class State(){
    public static void close_market(){
        String UPDATE = "DELETE * " +
                        "FROM State ";
        Utility.sql_update(UPDATE);

        String UPDATE = "INSERT INTO State " +
                        "VALUES(" + "0" + ")";
        Utility.sql_update(UPDATE);
    }


    public static void open_market(){
        String UPDATE = "DELETE * " +
                        "FROM State ";
        Utility.sql_update(UPDATE);

        String UPDATE = "INSERT INTO State " +
                        "VALUES(" + "1" + ")";
        Utility.sql_update(UPDATE);
    }

    public static Boolean is_open(){
        String QUERY =  "SELECT * " +
                        "FROM Date ";
        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if(!resultSet.next()){
                System.out.println("current date not found!");
                System.exit(1);
            }

            if(resultSet.getBoolean("s")){
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
