public class LL {

	private Card head = null;
	private Card tail = null;
	private Card current = null;

	public void add(Card element) {

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

public Card getFirst(){

	return head;
}

public Card getLast() {
	return tail;

}

public void resetCurrent()
{
	current = head;
}

public Card getNextElement() {
	Card c = current;
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

public Card getNth(int index) {
	Card answer = null;
	current = head;
	for(int i=1;i<=index;i++) {
		answer = getNextElement();
	}
	return answer;
}

}