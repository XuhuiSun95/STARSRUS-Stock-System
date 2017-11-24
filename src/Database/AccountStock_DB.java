package Database;

import Utility.Utility;
import java.sql.*;

public class AccountStock_DB{

    // public static String get_stock_account_id(String taxID){
    //     String QUERY =  "SELECT S.accountID" +
    //                     "FROM StockAccounts M" +
    //                     "WHERE S.TAXID = " + taxID;
    //
    //     ResultSet resultSet = Utility.sql_query(QUERY);
    //
    //     String res = "";
    //
    //     try{
    //         if(!resultSet.next()){
    //             System.err.println("No such stock account id.");
    //             System.exit(1);
    //         }
    //         res = resultSet.getString("accountID");
    //     } catch (Exception e){
    //         e.printStackTrace();
    //     }
    //
    //     return res;
    // }

    public static void add_shares(String taxID, String symbol, int amount){
        String QUERY =  "SELECT *" +
                        "FROM StockAccounts" +
                        "WHERE actorID = " + symbol;
        ResultSet resultSet = Utility.sql_query(QUERY);
        String UPDATE = "";
        if(resultSet.next()){
            UPDATE =    "UPDATE StockAccounts "
                        + "SET shares = shares +" + (new Integer(amount)).toString()
                        + "WHERE accountID = " + accountID;
        } else {
            UPDATE =    "INSERT INTO StockAccounts" +
                        "VALUES(" + taxID + "," + (new Integer(amount)).toString() +
                        "," + symbol + ")";
        }
        Utility.sql_update(UPDATE);
    }
}
