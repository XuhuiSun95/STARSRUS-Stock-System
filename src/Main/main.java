package Main;

import Session.*;
import java.io.Console;
import Utility.Utility;
import java.sql.*;

public class main {

    public static void main(String[] args) {
        try {

            //  Register JDBC driver
            Class.forName(Utility.JDBC_DRIVER).newInstance();

            //  Open a connection
            System.out.println("Connecting to database...");
            Utility.connection = DriverManager.getConnection(Utility.HOST,Utility.USER,Utility.PWD);
            Utility.statement = Utility.connection.createStatement();

            // load Date
            Utility.load_date();

            //  Creat a session
            Session session = new Session();

            System.out.println("System ON");
            Console c = System.console();
            if (c == null) {
                System.err.println("No console.");
                System.exit(1);
            }

            System.out.println("login as : 1.Customer 2.Manager 3.Admin");
            String input = c.readLine("enter the number:");

            switch (input){
                case "1":   session = new Customer();
                            break;
                case "2":   session = new Manager();
                            break;
                case "3":   session = new Admin();
                            break;
                default:    System.out.println("invalid input");
                            System.exit(1);
            }

            session.login();

            while(!Utility.logout){
                session.single_round_process();
            }
            
            Utility.store_date();

            //  Clean-up environment
            Utility.connection.close();
        }
        catch (SQLException se) {
            //  Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {
            //  Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            //  finally block used to close resources
            try {
                if(Utility.statement!=null)
                    Utility.statement.close();
            }
            catch (SQLException se2) {
            }// nothing we can do
            try {
                if(Utility.connection!=null)
                    Utility.connection.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
