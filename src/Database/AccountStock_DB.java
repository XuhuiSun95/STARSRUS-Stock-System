package Database;

import Utility.Utility;
import java.sql.*;

public class AccountStock_DB{

    public static String get_stock_account_id(String taxID){
        String QUERY =  "SELECT S.accountID" +
                        "FROM StockAccounts M" +
                        "WHERE S.TAXID = " + taxID;

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            res = resultSet.getString("accountID");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
