package Database;

import Utility.Utility;
import java.sql.*;

public class AccountMarket_DB{

    public static String get_market_account_id(String taxID){
        String QUERY =  "SELECT M.accountID" +
                        "FROM MarketAccounts M" +
                        "WHERE M.TAXID = " + taxID;
        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            res = resultSet.getString("accountID");
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static double get_account_balance(String accountId){
        String QUERY =  "SELECT M.balance" +
                        "FROM MarketAccounts M" +
                        "WHERE M.accountID = " + accountId;
        ResultSet resultSet = Utility.sql_query(QUERY);

        double res = 0;

        try{
            res = resultSet.getDouble("balance");
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return res;
    }
}
