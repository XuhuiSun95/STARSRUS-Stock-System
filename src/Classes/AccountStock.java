package  Classes;


public class AccountStock{
    private String owner;
    private String aid;

	public AccountStock(String owner, String aid) {
		this.owner = owner;
		this.aid = aid;
	}


	@Override
	public String toString() {
		return "Customer:" + owner + ", ID=" + aid;
	}
}
