package Session;

import java.io.Console;

public class Session{

    // virtual method in super class
    public void display_operations(){
        System.err.println("Invalid instance.");
        System.exit(1);
    }

    // virtual method in super class
    public void process_operations(String request){
        System.err.println("Invalid instance.");
        System.exit(1);
    }

    // virtual method in super class
    public Boolean verify_login(String username, String password){
        System.err.println("Invalid instance.");
        System.exit(1);
        return false;
    }

    // single round request process. will be presented in a while loop in the main.
    public void single_round_process(){
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        this.display_operations();
        String request = c.readLine("Enter your request:");
        this.process_operations(request);
    }

    //
    public void login(){
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        // ask for username and passwor
        String username = c.readLine("Enter your username: ");
        char[] pwd = c.readPassword("Enter your password: ");
        String password = new String(pwd);

        // exit the program, if the login is invalid
        if(!this.verify_login(username, password)){
            System.out.println("Invalid username/password \n system exit");
            System.exit(1);
        } else {
            System.out.println("login as:" + username);
        }
        // successfully login, continue the seesion
    }
}
