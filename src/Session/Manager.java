package Session;

class Manager extends Session{

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
