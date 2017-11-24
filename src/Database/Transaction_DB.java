package Database;

import Utility.Utility;
import java.sql.*;


// transaction type:    1 : deposit / 2: withdrawl
//                      3 : buy / 4 : sell
//                      5 : accrue interest

public class Transaction {
    public static void add_transaction(String date, Int transactionType, String costumerTAXID, String managerTAXID, double money, double profit){
        String UPDATE = "";
    }

    public static void delete_transcation(){
        String UPDATE = "DELETE * " +
                        "FROM Transcations ";
        Utility.sql_update(UPDATE);
    }

    public static String get_transactions(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM Transcations " +
                        "WHERE T.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        String res = "";

        try{
            while(resultSet.next()){
                String date = resultSet.getString("date");
                String type = transaction_type(resultSet.getInt("transactionType"));
                String costumerTAXID = resultSet.getString("costumerTAXID");
                String managerTAXID = resultSet.getString("managerTAXID");
                double money = resultSet.getDouble("money");
                double profit = resultSet.getDouble("profit");


                res += "Date: " + date;
                res += ",Transaction Type: " + type;
                res += ", costumer TaxID: "  + costumerTAXID;
                if(managerTAXID != "-1"){
                    res += ", manager TaxID: " + managerTAXID;
                }
                res += ", money: " + (new Double(money)).toString();
                if(profit != -1.0){
                    res += ", profit: " + (new Double(profit)).toString();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
