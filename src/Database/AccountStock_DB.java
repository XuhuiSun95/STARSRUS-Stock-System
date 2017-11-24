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

    public static int get_shares(String taxID, String actorID){
        String QUERY =  "SELECT S.shares" +
                        "FROM StockAccounts S" +
                        "WHERE S.TAXID = " + taxID +
                        "AND S.actorID = " + actorID;

        ResultSet resultSet = Utility.sql_query(QUERY);

        int res = "";

        try{
            if(!resultSet.next()){
                return -1;
            }
            res = resultSet.getInteger("shares");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static void add_shares(String taxID, String actorID, int amount){
        String QUERY =  "SELECT *" +
                        "FROM StockAccounts" +
                        "WHERE actorID = " + actorID;
        ResultSet resultSet = Utility.sql_query(QUERY);
        String UPDATE = "";
        if(resultSet.next()){
            UPDATE =    "UPDATE StockAccounts "
                        + "SET shares = shares" + (new Integer(amount)).toString()
                        + "WHERE taxID = " + taxID;
        } else {
            UPDATE =    "INSERT INTO StockAccounts" +
                        "VALUES(" + taxID + "," + (new Integer(amount)).toString() +
                        "," + actorID + ")";
        }
        Utility.sql_update(UPDATE);
    }
}
