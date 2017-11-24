package Main;

import Session.*;
import Utility.Utility;

public class main {

    Session session = new Session();

    public static void main(String[] args) {
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
            case "3":   session = new Customer();
                        break;
            default:    System.out.println("invalid input");
                        System.exit(1);
        }

        session.login();

        while(true){
            session.display_operations();
            input = c.readLine("enter the number:");
            session.process(intput);
        }

        return 0;
    }
}
