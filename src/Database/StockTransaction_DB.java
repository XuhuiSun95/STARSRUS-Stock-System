package Database;

import Utility.Utility;
import java.sql.*;


public class StockTransaction {
    public static void add_transaction(String date, String costumerTAXID, String actorID, double price, int shares, double profit){
        String UPDATE = "INSERT INTO StockTransaction " +
                        "VALUES(" + "'" + date + "'" + ","
                                + "'" + costumerTAXID + "'" + ","
                                + "'" + actorID + "'" + ","
                                + price + ","
                                + shares + ","
                                + profit ;

        Utility.sql_update(UPDATE);
    }

    public static void delete_transcation(){
        String UPDATE = "DELETE * " +
                        "FROM StockTranscations ";
        Utility.sql_update(UPDATE);
    }

    public static String get_transactions(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM StockTranscations " +
                        "WHERE T.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                String date = resultSet.getString("date");
                String costumerTAXID = resultSet.getString("costumerTAXID");
                String actorID = resultSet.getString("actorID");
                double price = resultSet.getDouble("price");
                int shares = resultSet.getInt("shares");
                double profit = resultSet.getDouble("profit");


                res += "Date: " + date;
                res += ", costumer TaxID: "  + costumerTAXID;
                res += ", stock symbol: " + actorID;
                res += ", price: " + (new Double(price)).toString();
                res += ", shares: " + (new Integer(shares)).toString();
                res += ", profit: " + (new Double(profit)).toString();
                res += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
