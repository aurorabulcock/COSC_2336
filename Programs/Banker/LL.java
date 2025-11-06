public class LL {

	private Client head = null;
	private Client tail = null;
	private Client current = null;
	private String fname;
	private String lname;
	private long account;
	private double balance;

	public void add(Client element) {

		if (head == null) {
			head = element;
			current = head;
			tail=head;

		}

		else {
			tail.setNext(element);
			tail = element;


		}
	}

	public boolean deleteClient(long account) {
		Client temp = head;
		Client prev = head;
		
		boolean found = false;
		boolean endOfList = false;
		if (head.getAccount() == account) {
			head = head.getNext();
			found = true;
		} else 
			temp = head.getNext();
		while (!found && !endOfList) {
			if (temp.getAccount()==account) {
				if (temp == tail);
				tail = prev;
			}
			prev.setNext(temp.getNext());
			temp = temp.getNext();
		}
		if (temp == null)
			endOfList = true;
		return found;
	}

public Client getFirst(){

	return head;
}

public Client getLast() {
	return tail;

}

public void resetCurrent()
{
	current = head;
}

public Client getNextElement() {
	Client c = current;
	if (current != null)
	{
		current=current.getNext();
	}
	else
	{
		return null;
	}
	return c;

}

public Client getNth(int index) {
	Client answer = null;
	current = head;
	for(int i=1;i<=index;i++) {
		answer = getNextElement();
	}
	return answer;
}

public String getFname() {
	return fname;
}

public String getLname() {
	return lname;
}

public long getAccount() {
	return account;
}

public double getBalance() {
	return balance;
}


}