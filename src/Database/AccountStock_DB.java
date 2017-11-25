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
        String QUERY =  "SELECT S.shares " +
                        "FROM StockAccounts S " +
                        "WHERE S.TAXID = " + "'" + taxID + "'" + " " +
                        "AND S.actorID = " + "'" + actorID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        int res = -1;

        try{
            if(!resultSet.next()){
                return -1;
            }
            res = resultSet.getInt("shares");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static double get_avg(String taxID, String actorID){
        String QUERY =  "SELECT S.avg " +
                        "FROM StockAccounts S " +
                        "WHERE S.TAXID = " + "'" + taxID + "'" + " " +
                        "AND S.actorID = " + "'" + actorID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        double res = -1.0;

        try{
            if(!resultSet.next()){
                return -1.0;
            }
            res = resultSet.getDouble("avg");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static void add_shares(String taxID, int amount, String actorID, double price){
        String QUERY =  "SELECT * " +
                        "FROM StockAccounts " +
                        "WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'";
        ResultSet resultSet = Utility.sql_query(QUERY);
        String UPDATE = "";
        try {
            if(resultSet.next()){
                int shares = resultSet.getInt("shares");
                double avg = resultSet.getDouble("avg");
                if(shares == -amount) {
                    UPDATE = "DELETE from StockAccounts WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'";
                    Utility.sql_update(UPDATE);
                }
                else {
                    int new_shares = shares + amount;
                    if(amount>0){
                        double new_avg = (avg*shares + price*amount)/(new_shares);

                        UPDATE = "UPDATE StockAccounts "
                                + "SET avg = " + String.valueOf(new_avg) + ", shares = " + String.valueOf(new_shares) + " "
                                + "WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'";
                        Utility.sql_update(UPDATE);
                    } else {
                        UPDATE = "UPDATE StockAccounts "
                                + "SET shares = " + String.valueOf(new_shares) + " "
                                + "WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'";
                        Utility.sql_update(UPDATE);
                    }
                }
            } else {
                UPDATE =    "INSERT INTO StockAccounts " +
                            "VALUES('" + taxID + "'," + String.valueOf(amount) +
                            ",'" + actorID + "'," + price + ")";
                Utility.sql_update(UPDATE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
