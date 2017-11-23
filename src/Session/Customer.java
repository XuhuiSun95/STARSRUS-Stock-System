package Session;

import Utility.Utility;
import java.sql.*;


public class Customer extends Session{
    private String marketAccountID;
    private String stockAccountID;

    // Override virtual function from super class
    @Override
    public void display_operations(){
        String options = "Please enter the number. Options:\n";
        options += "1: Deposit.\n";
        options += "2: Withdrawl.\n";
        options += "3: Buy.\n";
        options += "4: Sell.\n";
        options += "5: Show Balance.\n";
        options += "6: List Stock Info.\n";
        options += "7: List Moive Info. \n";

        System.out.println(options);
    }

    @Override
    public void process_operations(String request){
        switch (request) {
            case "1":   this.deposit();
                        break;
            case "2":   this.withdrawl();
                        break;
            case "3":   this.buy();
                        break;
            case "4":   this.sell();
                        break;
            case "5":   this.show_balance();
                        break;
            case "6":   this.list_stock_info();
                        break;
            case "7":   this.list_movie_info();
                        break;
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        Connection connection = Utility.connection;
        Statement statement = connection.createStatement();
        String Query = "SELECT *FROM Customers C WHERE C.username =" + username + "AND C.password = " + password;
        ResultSet resultSet = statement.executeQuery(Query);

        if (!resultSet.next() ) {
            return false;
        }

        return true;
    }

    // operation deltails
    public void deposit(){

    }

    public void withdrawl(){

    }

    public void buy(){

    }

    public void sell(){

    }

    public void show_balance(){

    }

    public void list_stock_info(){

    }

    public void list_movie_info(){

    }
}
