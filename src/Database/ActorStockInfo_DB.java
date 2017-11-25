package Database;

import Utility.Utility;
import java.sql.*;
import Database.StockHistory_DB;

public class ActorStockInfo_DB{
    public static String list_all(){
        String QUERY =  "SELECT * " +
                        "FROM ActorsStockInfo";

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

                Statement saved = Utility.statement;
                Utility.statement = Utility.connection.createStatement();

                String history = StockHistory_DB.get_history(id);

                Utility.statement.close();
                Utility.statement = saved;


                res += "Acotr id: " + id;
                res += ", Price: " + price;
                res += ", Actor name: " + name;
                res += ", Date of Birth: " + dob;
                res += ", Moive Title: " + title;
                res += ", Role: " + role;
                res += ", Year: " + year;
                res += ", Contract:" + contract;
                res += "\n";
                res += history;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static double get_price(String actorID){
        String QUERY =  "SELECT A.currentPrice " +
                        "FROM ActorsStockInfo A " +
                        "WHERE A.actorID = " + "'" + actorID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);
        double res = -1.0;
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

    public static void update_price(String actorID, double price){
        String UPDATE = "UPDATE ActorsStockInfo "
                        + "SET CURRENTPRICE = " + (new Double(price)).toString() + " "
                        + "WHERE ACTORID = " + "'" + actorID + "'";
        Utility.sql_update(UPDATE);
    }
}
