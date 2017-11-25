package Database;

import Utility.Utility;
import java.sql.*;

public class AccountMarket_DB{

    public static void insert_new_account(String taxID){
        String QUERY = "SELECT accountid FROM MarketAccounts ORDER BY accountid DESC";
        ResultSet rs = Utility.sql_query(QUERY);

        String new_id = "001";
        try{
            if(rs.next()){
                String id = rs.getString("accountid");
                new_id = String.format("%03d", (Integer.valueOf(id) + 1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        String UPDATE = "INSERT INTO MarketAccounts(taxid,accountid,balance) VALUE('" + taxID +
                        "','" + new_id + "'," + String.valueOf(0) + ")";
        Utility.sql_update(UPDATE);
    }

    public static String get_market_account_id(String taxID){
        String QUERY =  "SELECT M.accountID " +
                        "FROM MarketAccounts M " +
                        "WHERE M.TAXID = " + "'" + taxID + "'" ;
        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            if(!resultSet.next()){
                System.err.println("No such taxID.\n");
                System.exit(1);
            }
            res = resultSet.getString("accountID");
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static double get_account_balance(String accountId){
        String QUERY =  "SELECT M.balance " +
                        "FROM MarketAccounts M " +
                        "WHERE M.accountID = " + "'" + accountId + "'";
        ResultSet resultSet = Utility.sql_query(QUERY);

        double res = 0;

        try{
            if(!resultSet.next()){
                System.err.println("No such market account id.\n");
                System.exit(1);
            }
            res = resultSet.getDouble("balance");
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }


    public static void add_balance(String accountID, double amount){
        String UPDATE = "UPDATE MarketAccounts "
                        + "SET balance = balance + " + (new Double(amount)).toString() + " "
                        + "WHERE accountID = " + accountID;
        Utility.sql_update(UPDATE);
    }
}
