package Database;

import Utility.Utility;
import java.sql.*;
import java.lang.*;


public class StockTransaction_DB{
    public static void record_transaction(String date, String customerTAXID, String actorID, double price, int shares, double profit){
        String UPDATE = "INSERT INTO StockTransactions " +
                        "VALUES(" + "'" + date + "'" + ","
                                + "'" + customerTAXID + "'" + ","
                                + "'" + actorID + "'" + ","
                                + price + ","
                                + shares + ","
                                + profit + ")";

        Utility.sql_update(UPDATE);
    }

    public static void delete_transaction(){
        String UPDATE = "DELETE * " +
                        "FROM StockTransactions ";
        Utility.sql_update(UPDATE);
    }



    public static int get_total_shares(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM StockTransactions " +
                        "WHERE CustomerTAXID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        int sum = 0;
        try{
            while(resultSet.next()){
                int shares = resultSet.getInt("shares");

                sum += Math.abs(shares);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return sum;
    }

    public static double get_total_profit(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM StockTransactions " +
                        "WHERE CustomerTAXID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        double sum = 0;
        try{
            while(resultSet.next()){
                double profit = resultSet.getDouble("profit");

                sum += profit;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return sum;
    }

    public static String get_transactions(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM StockTransactions " +
                        "WHERE CustomerTAXID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                String date = resultSet.getString("date");
                String customerTAXID = resultSet.getString("customerTAXID");
                String actorID = resultSet.getString("actorID");
                double price = resultSet.getDouble("price");
                int shares = resultSet.getInt("shares");
                double profit = resultSet.getDouble("profit");


                res += "Date: " + date;
                res += ", Transaction type: " + ((shares>0) ? "Buy" : "Sell");
                res += ", customer TaxID: "  + customerTAXID;
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
