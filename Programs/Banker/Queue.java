
public class Queue {

	Client head = null;
	Client tail = null;
	Client current = null;
	
	public void add(Client c) {
		if (head==null) {
			head = c;
			tail = c;
			current =c;
		}
		else {
			tail.setNext(c);
			tail = c;
			//tail = tail.getNext();
		}
	}
	
	public Client peek() {
		
		return head;	
	}
	
	public Client serve() {
		if (head!=null) {
		current = head;
		head = head.getNext();
		if (head==null) {
		tail = null;
		}
			return current;
		}
		else {
			return null;
		}

	}
}
