package Database;

import Utility.Utility;
import java.sql.*;
import java.sql.ResultSet;
import javax.management.Query;

public class Customer_DB{

    public static String get_tax_id(String username, String password){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.username = " + "'" + username + "'" + " AND C.password = " + "'" + password + "'";

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
                        "FROM Customers C " +
                        "WHERE C.active = TRUE";

        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String taxID = resultSet.getString("TAXID");

                res += "Username: " + username + " TAXID: " + taxID + "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static String customer_report(){
        String QUERY =  "SElECT * " +
                        "FROM Customers";
        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String taxID = resultSet.getString("TAXID");

                Statement saved = Utility.statement;
                Utility.statement = Utility.connection.createStatement();
                String marketAccountID = AccountMarket_DB.get_market_account_id(taxID);
                double b = AccountMarket_DB.get_account_balance(marketAccountID);
                String balance = (new Double(b)).toString();
                // String stockAccountID = AccountStock_DB.get_stock_account_id(taxID);
                Utility.statement.close();
                Utility.statement = saved;

                res += "Username: " + username + " ,TAXID: " + taxID
                        + " ,Market Account ID: " + marketAccountID
                        // + " Stock Account ID: " + stockAccountID
                        + " ,balance: " + balance + "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

}
