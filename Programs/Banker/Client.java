
public class Client {
	
	private String fname;
	private String lname;
	private long account;
	private double balance;
	private Client next = null;
	
	public Client(long a, String fn, String ln, double b) {
		
		fname = fn;
		lname = ln;
		account = a;
		balance = b;
	}

	public Client getNext() {
		return next;
	}


	public void setNext(Client next) {
		this.next = next;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
