package Database;

import Utility.Utility;
import java.sql.*;

public class AccountStock_DB{

    public static String get_shares_info(String taxID, String actorID){
        String QUERY =  "SELECT S.shares S.price" +
                        "FROM StockAccounts S " +
                        "WHERE S.TAXID = " + "'" + taxID + "'" + " " +
                        "AND S.actorID = " + "'" + actorID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                int shares = resultSet.getInt("shares");
                double price = resultSet.getDouble("price");

                res += "\t shares: " + (new Integer(shares)).toString() + ", " +
                        "bought price: " + (new Double(price)).toString() + "\n";
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static int get_shares(String taxID, String actorID, double price){
        String QUERY =  "SELECT S.shares " +
                        "FROM StockAccounts S " +
                        "WHERE S.TAXID = " + "'" + taxID + "'" + " " +
                        "AND S.actorID = " + "'" + actorID + "'" +
                        " AND price = " + (new Double(price)).toString();

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


    public static void add_shares(String taxID, int amount, String actorID, double price){
        String QUERY =  "SELECT * " +
                        "FROM StockAccounts " +
                        "WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'" + " AND price = " + (new Double(price)).toString();
        ResultSet resultSet = Utility.sql_query(QUERY);
        String UPDATE = "";
        try {
            if(resultSet.next()){
                int shares = resultSet.getInt("shares");
                if(shares == -amount) {
                    UPDATE = "DELETE from StockAccounts WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'" + " AND price = " + (new Double(price)).toString();
                    Utility.sql_update(UPDATE);
                }
                else {
                    int new_shares = shares + amount;
                    UPDATE = "UPDATE StockAccounts "
                            + "SET shares = " + String.valueOf(new_shares) + " "
                            + "WHERE actorID = " + "'" + actorID + "'" + " AND taxID = '" + taxID + "'";
                    Utility.sql_update(UPDATE);

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
