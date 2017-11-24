package Database;

import Utility.Utility;
import java.sql.*;
import java.sql.ResultSet;
import javax.management.Query;

public class Customer_DB{

    public static String get_tax_id(String username, String password){
        String QUERY =  "SELECT * " +
                        "FROM Customers C" +
                        "WHERE C.username =" + username + "AND C.password = " + password;

        ResultSet resultSet = Utility.sql_query(QUERY);
        String res = "";
        try{
            if(!resultSet.next()){
                return "-1";
            }

            res =  resultSet.getString("TAXID");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static String list_active(){
        String QUERY =  "SELECT * " +
                        "FROM Customers C" +
                        "WHERE C.active = TRUE";

        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("userame");
                String taxID = resultSet.getString("TAXID");

                res += "Username: " + username + " TAXID: " + TAXID + "\n"
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static String customer_report(){
        String Query =  "SElECT * " +
                        "FROM Customers C";
        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("userame");
                String taxID = resultSet.getString("TAXID");

                String marketAccountID = AccountMarket_DB.get_market_account_id(taxID);
                String balance = AccountMarket_DB.get_account_balance(taxID);
                String stockAccountID = AccountStock_DB.get_stock_account_id(taxID);

                res += "Username: " + username + " TAXID: " + TAXID
                        + " Market Account ID: " + marketAccountID
                        + " Stock Account ID: " + stockAccountID
                        + " balance: " + balance + "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

}
