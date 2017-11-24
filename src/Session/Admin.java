package Session;

import Utility.Utility;
import java.sql.*;

public class Admin extends Session{

    // Override virtual function from super class
    @Override
    public void display_operations(){
        String options = "Please enter the number. Options:\n";
        options += "1: Open Market.\n";
        options += "2: Close Market.\n";
        options += "3: Set date.\n";
        options += "4: Set price.\n";

        System.out.println(options);
    }

    @Override
    public void process_operations(String request){
        switch (request) {
            case "1":   this.open_market();
                        break;
            case "2":   this.close_market();
                        break;
            case "3":   this.set_date();
                        break;
            case "4":   this.set_price();
                        break;
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        Connection connection = Utility.connection;
        Statement statement = connection.createStatement();
        String Query = "SELECT *FROM Admin A WHERE A.username =" + username + "AND A.password = " + password;
        ResultSet resultSet = statement.executeQuery(Query);

        if (!resultSet.next() ) {
            return false;
        }

        return true;
    }

    // operation deltails
    public void open_market(){
        Utility.open_market();
    }

    public void close_market(){
        Utility.close_market();
    }

    public void set_date(){

    }

    public void set_price(){

    }
}
