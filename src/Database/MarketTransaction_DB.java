package Database;

import Utility.Utility;
import java.sql.*;


public class MarketTransaction {
    public static void record_transaction(String date, String costumerTAXID, double amount, double balance){
        String UPDATE = "INSERT INTO MarketTransaction " +
                        "VALUES(" + "'" + date + "'" + ","
                                + "'" + costumerTAXID + "'" + ","
                                + "'" + amount + "'" + ","
                                + balance;

        Utility.sql_update(UPDATE);
    }

    public static void delete_transcation(){
        String UPDATE = "DELETE * " +
                        "FROM MarketTranscations ";
        Utility.sql_update(UPDATE);
    }

    public static String get_transactions(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM MarketTranscations " +
                        "WHERE T.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                String date = resultSet.getString("date");
                String costumerTAXID = resultSet.getString("costumerTAXID");
                double money = resultSet.getDouble("amount");
                double balance = resultSet.getDouble("balance");


                res += "Date: " + date;
                res += ", costumer TaxID: "  + costumerTAXID;
                res += ", amount: " + (new Double(money)).toString();
                res += ", balance: " + (new Double(balance)).toString();
                res += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
