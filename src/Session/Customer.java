package Session;

import Utility.Utility;
import java.sql.*;
import Database.*;
import java.io.Console;

public class Customer extends Session{
    private String marketAccountID;
    // private String stockAccountID;
    private String taxID;

    // Override virtual function from super class
    @Override
    public void display_operations(){
        String options = "Please enter the number. Options:\n";
        options += "1: Deposit.\n";
        options += "2: Withdrawl.\n";
        options += "3: Buy.\n";
        options += "4: Sell.\n";
        options += "5: Show Balance.\n";
        options += "6: Show Transaction history.\n";
        options += "7: List Stock Info.\n";
        options += "8: List Moive Info. \n";
        options += "9: Exit\n";

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
            case "6":   this.show_transaction();
                        break;
            case "7":   this.list_actor_stock_info();
                        break;
            case "8":   this.list_movie_info();
                        break;
            case "9":   Utility.logout = true;
                        break;
            default:    System.out.println("Wrong input, please try again");
        }
    }

    @Override
    public Boolean verify_login(String username, String password){
        Connection connection = Utility.connection;
        Statement statement = null;
        try{
            // find the username and password pair entity and get the tax id
            taxID = Customer_DB.get_tax_id(username, password);

            // if tax id does not exist
            if(taxID.equals(new String("-1"))){
                return false;
            }

            marketAccountID = AccountMarket_DB.get_market_account_id(taxID);
            // stockAccountID = AccountStock_DB.get_stock_account_id(taxID);

            return true;
        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // operation deltails
    public void deposit(){
        if(Utility.marketState == false){
            System.out.println("Market Closed");
            return;
        }

        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String amount = c.readLine("Please enter the amount:");

        AccountMarket_DB.add_balance(marketAccountID, Double.parseDouble(amount));

        double balance = AccountMarket_DB.get_account_balance(marketAccountID);
        MarketTransaction_DB.record_transaction(Utility.date, taxID, Double.parseDouble(amount), balance);
    }

    public void withdrawl(){
        if(Utility.marketState == false){
            System.out.println("Market Closed");
            return;
        }

        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String temp = c.readLine("Please enter the amount:");
        double amount = Double.parseDouble(temp);
        double balance = AccountMarket_DB.get_account_balance(marketAccountID);

        if(balance < amount){
            System.out.println("Request denied! Not enough balance!\n");
            return;
        }

        AccountMarket_DB.add_balance(marketAccountID, -amount);

        balance = AccountMarket_DB.get_account_balance(marketAccountID);
        MarketTransaction_DB.record_transaction(Utility.date, taxID, -amount, balance);
    }

    public void buy(){
        if(Utility.marketState == false){
            System.out.println("Market Closed");
            return;
        }

        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String actorID = c.readLine("Stock you want to buy:");
        if(actorID.length() != 3){
            System.out.println("Invalid Stock symbol\n");
            return;
        }

        double price = ActorStockInfo_DB.get_price(actorID);
        if(price == -1.0){
            System.out.println("No such stock!\n");
            return;
        }

        String temp = c.readLine("Amount you want to buy:");
        int amount = Integer.parseInt(temp);

        double spent = amount * price + 20;

        double balance = AccountMarket_DB.get_account_balance(marketAccountID);
        if(spent > balance){
            System.out.println("Request denied! Not enough balance\n");
            return;
        }

        AccountStock_DB.add_shares(taxID, amount, actorID, price);
        AccountMarket_DB.add_balance(marketAccountID, -spent);

        StockTransaction_DB.record_transaction(Utility.date, taxID, actorID, price, amount, 0);
    }


    public void sell(){
        if(Utility.marketState == false){
            System.out.println("Market Closed");
            return;
        }

        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String actorID = c.readLine("Stock you want to buy:");
        if(actorID.length() != 3){
            System.out.println("Invalid Stock symbol\n");
            return;
        }

        int shares = AccountStock_DB.get_shares(taxID, actorID);
        if(shares == -1){
            System.out.println("No such stock!\n");
            return;
        }

        double price = ActorStockInfo_DB.get_price(actorID);
        if(price == -1.0){
            System.out.println("No such stock!\n");
            return;
        }

        String temp = c.readLine("Amount you want to sell:");
        int amount = Integer.parseInt(temp);

        if(amount > shares){
            System.out.println("Not enough shares\n");
            return;
        }

        double avg = AccountStock_DB.get_avg(taxID, actorID);

        AccountStock_DB.add_shares(taxID, -amount, actorID, price);
        AccountMarket_DB.add_balance(marketAccountID, price*amount-20);

        double profit = price*amount - avg*amount - 20;
        System.out.print("price:");
        System.out.println(price);
        System.out.print("avg:");
        System.out.println(avg);
        System.out.print("profit:");
        System.out.println(profit);



        StockTransaction_DB.record_transaction(Utility.date, taxID, actorID, price, -amount, profit);
    }

    public void show_balance(){
        double balance = AccountMarket_DB.get_account_balance(marketAccountID);
        System.out.println("Current balance: " + (new Double(balance)).toString());
    }

    public void show_transaction(){
        String marketTransactions = MarketTransaction_DB.get_transactions(taxID);
        String stockTransactions = StockTransaction_DB.get_transactions(taxID);
        String interestTransactions = InterestTransaction_DB.get_transactions(taxID);
        System.out.println("Transactions:");
        System.out.println(marketTransactions);
        System.out.println(stockTransactions);
        System.out.println(interestTransactions);
    }

    public void list_actor_stock_info(){
        String res = ActorStockInfo_DB.list_all();
        System.out.println("All Actor and Stock Info:\n" + res);
    }

    public void list_movie_info(){

    }
}
