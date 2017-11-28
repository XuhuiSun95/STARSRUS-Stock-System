package Database;

import Utility.Utility;
import java.sql.*;
import java.sql.ResultSet;

public class Customer_DB{

    public static void active(String taxID){
        String UPDATE = "UPDATE Customers " +
                        "SET active = true " +
                        "WHERE taxID = " + "'" + taxID + "'";
        Utility.sql_update(UPDATE);
    }

    public static Boolean is_active(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if(resultSet.next()){
                if(resultSet.getBoolean("active")){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    public static Boolean username_exist(String username){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.username = " + "'" + username + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean taxid_exist(String taxid){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.taxID = " + "'" + taxid + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean ssn_exist(String ssn){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.ssn = " + "'" + ssn + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);

        try{
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void insert_new_user(String name, String username, String password, String address, String state, String phone, String email, String taxid, String ssn){
        String UPDATE = "INSERT INTO Customers(name, username, password, address, state, phone, email, taxid, ssn, active) VALUES(" +
                        "'" + name + "','" + username + "','" + password + "','" + address + "','" + state + "','" + phone + "','" +
                        email + "','" + taxid + "','" + ssn + "'," + String.valueOf(0) + ")";

        Utility.sql_update(UPDATE);

    }

    public static String get_tax_id(String username, String password){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.username = " + "'" + username + "'" + " AND C.password = " + "'" + password + "'";

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

    public static String get_email(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);
        String res = "";
        try{
            if(!resultSet.next()){
                return "-1";
            }

            res =  resultSet.getString("email");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static String get_name(String taxID){
        String QUERY =  "SELECT * " +
                        "FROM Customers C " +
                        "WHERE C.taxID = " + "'" + taxID + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);
        String res = "";
        try{
            if(!resultSet.next()){
                return "-1";
            }

            res =  resultSet.getString("name");
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static String list_active(){
        String QUERY =  "SELECT * " +
                        "FROM Customers C ";

        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String taxID = resultSet.getString("TAXID");

                Statement saved = Utility.statement;
                Utility.statement = Utility.connection.createStatement();

                int total = StockTransaction_DB.get_total_shares(taxID);

                Utility.statement.close();
                Utility.statement = saved;

                if(total >= 1000)
                    res += "Username: " + username + " TAXID: " + taxID + "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static String customer_report(){
        String QUERY =  "SElECT * " +
                        "FROM Customers";
        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String taxID = resultSet.getString("TAXID");

                Statement saved = Utility.statement;
                Utility.statement = Utility.connection.createStatement();

                String marketAccountID = AccountMarket_DB.get_market_account_id(taxID);
                double b = AccountMarket_DB.get_account_balance(marketAccountID);
                String balance = (new Double(b)).toString();
                // String stockAccountID = AccountStock_DB.get_stock_account_id(taxID);
                Utility.statement.close();
                Utility.statement = saved;

                res += "Username: " + username + " ,TAXID: " + taxID
                        + " ,Market Account ID: " + marketAccountID
                        // + " Stock Account ID: " + stockAccountID
                        + " ,balance: " + balance + "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }



    public static String get_DTER_list(){
        String QUERY =  "SElECT * " +
                        "FROM Customers";
        ResultSet resultSet= Utility.sql_query(QUERY);
        String res = "";
        try{
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String taxID = resultSet.getString("TAXID");

                Statement saved = Utility.statement;
                Utility.statement = Utility.connection.createStatement();

                String marketAccountID = AccountMarket_DB.get_market_account_id(taxID);
                double profit = StockTransaction_DB.get_total_profit(taxID);
                profit += InterestTransaction_DB.get_interest(taxID);

                Utility.statement.close();
                Utility.statement = saved;

                if(profit >= 10000){
                res += "Username: " + username + " ,TAXID: " + taxID
                        + " ,Market Account ID: " + marketAccountID
                        + " ,Profit: " + profit + "\n";
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
