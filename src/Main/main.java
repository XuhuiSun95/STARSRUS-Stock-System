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
            Session session = null;

            System.out.println("----------------------------");
            System.out.println("System ON");
            Console c = System.console();
            if (c == null) {
                System.err.println("No console.\n");
                System.exit(1);
            }

            System.out.println("login as : 1.Customer 2.Manager 3.Admin");
            System.out.println("4.Sign up as new customer");
            String input = c.readLine("enter the number:");

            switch (input){
                case "1":   session = new Customer();
                            break;
                case "2":   session = new Manager();
                            break;
                case "3":   session = new Admin();
                            break;
                case "4":   break;
                default:    System.out.println("invalid input\n");
                            System.exit(1);
            }

            if(session == null){
                if(!Utility.sign_up()){
                    System.out.println("System exit.\n");
                    System.exit(1);
                } else
                    session = new Customer();
            }

            session.login();

            while(!Utility.logout){
                System.out.println("=========================================");
                Utility.load_date();
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
