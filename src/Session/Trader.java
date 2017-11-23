package Session;

public class Trader extends Session{
    private String marketAccountID;
    private String stockAccountID;

    // Override virtual function from super class
    @Override
    public void display_operations(){

    }

    @Override
    public void process_operations(String request){

    }

    @Override
    public Boolean verify_login(String username, String password){
        return false;
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
