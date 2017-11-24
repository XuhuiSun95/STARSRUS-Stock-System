package Database;

import Utility.Utility;
import java.sql.*;

public class ActorStockInfo_DB{
    public static String list_all(){
        String QUERY =  "SELECT * " +
                        "FROM Customers C";

        ResultSet resultSet = sql_query(QUERY);

        String res = "";
        // while(resultSet.next()){
        //
        // }
    }
}
