package Database;

import Utility.Utility;
import java.sql.*;

public class ActorStockInfo_DB{
    public static String list_all(){
        String QUERY =  "SELECT * " +
                        "FROM Customers C";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";
        try{
            while(resultSet.next()){
                String id = resultSet.getString("ACTORID");
                Double price = resultSet.getDouble("CURRENTPRICE");
                String name = resultSet.getString("NAME");
                String dob = resultSet.getString("DOB");
                String title = resultSet.getString("MovieTitle");
                String role = resultSet.getString("Role");
                String year = resultSet.getString("Year");
                Double contract = resultSet.getDouble("Contract");

                res += "Acotr id: " + id + " Price: " + price
                        + " Actor name: " + name + " Date of Birth: "
                        + dob + " Moive Title: " + title + " Role: "
                        + role + " Year: " + year + " Contract:"
                        + contract + "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static double get_price(String symbol){
        String QUERY =  "SELECT A.currentPrice" +
                        "FROM ActorStockInfo A" +
                        "WHERE A.actorID = " + symbol;

        ResultSet resultSet = Utility.sql_query(QUERY);
        double res = 0;
        try{
            if(!resultSet.next()){
                return -1.0;
            }

            res = resultSet.getDouble("currentPrice");
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static update_price(String symbol, double price){
        String UPDATE = "UPDATE MarketAccounts "
                        + "SET CURRENTPRICE =" (new Double(price)).toString()
                        + "WHERE ACTORID = " + symbol;
        Utility.sql_update(UPDATE);
    }
}
