package Database;

import Utility.Utility;
import java.sql.*;
import java.sql.ResultSet;
import javax.management.Query;

public class Customer_DB{

    public static String get_tax_id(String username, String password){
        String QUERY =  "SELECT * " +
                        "FROM Customers C" +
                        "WHERE C.username =" + username + "AND C.password = " + password;

        ResultSet resultSet = Utility.sql_query(QUERY);
        String res = "";
        try{
            if(!resultSet.next()){
                return "-1";
            }

            res =  resultSet.getString("TAXID");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }


}
