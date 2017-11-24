package Database;

import Utility.Utility;
import java.sql.*;


public class InterestTransaction {
    public static void add_transaction(String date, String costumerTAXID, String managerTAXID, double interest, double balance){
        String UPDATE = "INSERT INTO InterestTransaction " +
                        "VALUES(" + "'" + date + "'" + ","
                                + "'" + costumerTAXID + "'" + ","
                                + "'" + managerTAXID + "'" + ","
                                + interest + ","
                                + balance;

        Utility.sql_update(UPDATE);
    }

    public static void delete_transcation(){
        String UPDATE = "DELETE * " +
                        "FROM InterestTranscations ";
        Utility.sql_update(UPDATE);
    }

    public static String get_transactions(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM InterestTranscations " +
                        "WHERE T.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                String date = resultSet.getString("date");
                String costumerTAXID = resultSet.getString("costumerTAXID");
                String managerTAXID = resultSet.getString("managerTAXID");
                double interest = resultSet.getDouble("interest");
                double balance = resultSet.getDouble("balance");


                res += "Date: " + date;
                res += ", costumer TaxID: "  + costumerTAXID;
                res += ", manager TaxID: " + managerTAXID;
                res += ", interest: " + (new Double(interest)).toString();
                res += ", balance: " + (new Double(balance)).toString();
                res += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
