package Classes;

public class AccountMarket {
    private String owner;
    private String aid;
    private Boolean active;
    private double balance;

    public AccountMarket(String owner, String aid, Boolean active, double balance) {
        this.owner = owner;
        this.aid = aid;
        this.active = active;
        this.balance = balance;
    }

	@Override
	public String toString() {
		return "Customer:" + owner + ", ID=" + aid + ", Status=" + (active ? "Active" : "Inactive" ) + "Balance:" + balance;
	}

}
