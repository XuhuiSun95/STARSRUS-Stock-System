package Database;

import Utility.Utility;
import java.sql.*;

public class StockHistory_DB{
    public static void record_history(String date, String actorID, double price){
        String UPDATE = "INSERT INTO StocksHistory " +
                        "VALUES(" + "'" + date + "'" + ","
                        + "'" + actorID + "'" + ","
                        + price + ")";
        Utility.sql_update(UPDATE);
    }


    public static String get_history(String actorID){
        String QUERY =  "SELECT * " +
                        "FROM StocksHistory " +
                        "WHERE actorID = " + "'" + actorID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String history = "";
        try{
            while(resultSet.next()){
                String date = resultSet.getString("Date");
                double price = resultSet.getDouble("price");

                history += "\t" + "Date: " + date;
                history += ", Stock Symbol: " + actorID;
                history += ", Closing Price: " + (new Double(price)).toString();
                history += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return history;
    }
}
