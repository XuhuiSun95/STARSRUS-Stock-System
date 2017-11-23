package Session;

public class Admin extends Session{

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
    public void open_market(){

    }

    public void close_market(){

    }

    public void set_today(){

    }

    public void set_price(){

    }
}
