package Session;

import Utility.Utility;
import java.sql.*;

class Manager extends Session{

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
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        Connection connection = Utility.connection;
        Statement statement = connection.createStatement();
        String Query = "SELECT *FROM Managers M WHERE M.username =" + username + "AND M.password = " + password;
        ResultSet resultSet = statement.executeQuery(Query);

        if (!resultSet.next() ) {
            return false;
        }

        return true;
    }

    // operation deltails
    public void add_interest(){

    }

    public void generate_monthly_statement(){

    }

    public void list_active_customers(){

    }

    public void generate_DTER(){

    }

    public void generate_customer_report(){

    }

    public void delete_transcation(){

    }
}
