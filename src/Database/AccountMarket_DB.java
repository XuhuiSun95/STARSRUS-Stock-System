package Database;

import Utility.Utility;
import java.sql.*;

public class AccountMarket_DB{

    public static String get_market_account_id(String taxID){
        String QUERY =  "SELECT M.accountID" +
                        "FROM MarketAccounts M" +
                        "WHERE M.TAXID = " + taxID;
        ResultSet resultSet = Utility.sql_query(QUERY);

        return resultSet.getString("accountID");
    }

    public static double get_account_balance(String accountId){
        String QUERY =  "SELECT M.balance" +
                        "FROM MarketAccounts M" +
                        "WHERE M.accountID = " + accountID;
        ResultSet resultSet = Utility.sql_query(QUERY);

        return resultSet.getDouble("balance");
    }
}
