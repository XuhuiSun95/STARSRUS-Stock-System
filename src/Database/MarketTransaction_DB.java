package Database;

import Utility.Utility;
import java.sql.*;


public class MarketTransaction_DB {
    public static void record_transaction(String date, String customerTAXID, double amount, double balance){
        String UPDATE = "INSERT INTO MarketTransactions " +
                        "VALUES(" + "'" + date + "'" + ","
                                + "'" + customerTAXID + "'" + ","
                                + "'" + amount + "'" + ","
                                + balance + ")";

        Utility.sql_update(UPDATE);
    }

    public static void delete_transaction(){
        String UPDATE = "DELETE " +
                        "FROM MarketTransactions ";
        Utility.sql_update(UPDATE);
    }

    public static String get_transactions(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM MarketTransactions " +
                        "WHERE CustomerTAXID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                String date = resultSet.getString("date");
                String customerTAXID = resultSet.getString("customerTAXID");
                double money = resultSet.getDouble("amount");
                double balance = resultSet.getDouble("balance");


                res += "Date: " + date;
                res += ", Transaction type: " + ((money>0) ? "Deposit" : "Withdrawal");
                res += ", customer TaxID: "  + customerTAXID;
                res += ", amount: " + (new Double(money)).toString();
                res += ", balance: " + (new Double(balance)).toString();
                res += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static String get_average_balance(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM MarketTransactions " +
                        "WHERE CustomerTAXID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            double balance = AccountMarket_DB.get_balance(taxID);
            while(resultSet.next()){
                String date = resultSet.getString("date");
                double money = resultSet.getDouble("amount");
                double balance = resultSet.getDouble("balance");

                int month = Integer.parseInt(date.substring(4,6));
                int day = Integer.parseInt(date.substring(6,8));
                

            } 

        } catch(Exception e){
            e.printStackTrace();
        }
        
        return res;
}
