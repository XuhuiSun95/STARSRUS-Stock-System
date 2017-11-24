package Session;

import Utility.Utility;
import java.sql.*;
import Database.*;

public class Manager extends Session{

    // Override virtual function from super class
    @Override
    public void display_operations(){
        String options = "Please enter the number. Options:\n";
        options += "1: Add Interest.\n";
        options += "2: Generate Mongthly Statement.\n";
        options += "3: List Active Customers.\n";
        options += "4: Generate Government Drug & Tax Evasion Report.\n";
        options += "5: Customer Report.\n";
        options += "6: Delete Transactions.\n";
        options += "7: Exit";

        System.out.println(options);
    }

    @Override
    public void process_operations(String request){
        switch (request) {
            case "1":   this.add_interest();
                        break;
            case "2":   this.generate_monthly_statement();
                        break;
            case "3":   this.list_active_customers();
                        break;
            case "4":   this.generate_DTER();
                        break;
            case "5":   this.generate_customer_report();
                        break;
            case "6":   this.delete_transcation();
                        break;
            case "7":   Utility.logout = true;
                        break;
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        String QUERY =  "SELECT * " +
                        "FROM Managers M " +
                        "WHERE M.username = " + "'" +  username+ "'" + " AND M.password = " +  "'" + password + "'";

        ResultSet resultSet = Utility.sql_query(QUERY);
        try{
            if (!resultSet.next() ) {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    // operation deltails
    public void add_interest(){

    }

    public void generate_monthly_statement(){
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String taxID = c.readLine("Please enter the tax ID of the customer:")

        String name = Customer_DB.get_name(taxID);
        String email = Customer_DB.get_email(taxID);

        if(name == "-1" || email == "-1"){
            System.out.println("No such tax ID!");
            return;
        }

        String marketTransactions = MarketTransaction_DB.get_transactions(taxID);
        String stockTransactions = StockTransaction_DB.get_transactions(taxID);
        String interestTransactions = InterestTransaction_DB.get_transactions(taxID);

        System.out.println("TaxID: " + taxID + " ,Name: " + name + " ,email: " + email + "\n");
        System.out.println("Transcations");
        System.out.println(marketTransactions);
        System.out.println(stockTransactions);
        System.out.println(interestTransactions);
    }

    public void list_active_customers(){
        String res = Customer_DB.list_active();

        System.out.println("Active Customers:\n" + res);
    }

    public void generate_DTER(){

    }

    public void generate_customer_report(){
        String res = Customer_DB.customer_report();

        System.out.println("Customer Report:\n" + res);
    }

    public void delete_transcation(){
        MarketTransaction_DB.delete_transcation();
        StockTransaction_DB.delete_transcation();
        InterestTransaction.delete_transcation();
    }
}
