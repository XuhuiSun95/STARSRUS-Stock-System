package Database;

import Utility.Utility;
import java.sql.*;
import Database.*;


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

    public static double get_average_balance(String taxID){
        double new_balance = AccountMarket_DB.get_account_balance(AccountMarket_DB.get_market_account_id(taxID));
        
        String QUERY =  "SELECT * " +
                        "FROM MarketTransactions " +
                        "WHERE CustomerTAXID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        double total_balance = 0;

        try{
            int old_day = 1;
            int total_day = Integer.parseInt(Utility.date.substring(6,8));
            while(resultSet.next()){
                String date = resultSet.getString("date");
                double money = resultSet.getDouble("amount");
                double balance = resultSet.getDouble("balance");


                int day = Integer.parseInt(date.substring(6,8));
                total_balance += (day-old_day)*(balance-money);
                old_day = day;
            }
            if(old_day == 1)
                total_balance +=  total_day*new_balance;
            else
                total_balance += (total_day-old_day+1)*new_balance;

            total_balance /= total_day;

        } catch(Exception e){
            e.printStackTrace();
        }
        
        return total_balance;
    }
}
