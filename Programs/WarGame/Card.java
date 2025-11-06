
public class Card {
	private String number = "";
	private char suit = ' ';
	private int value = 0;
	private Card next = null;
	
	public Card() {
		
	}
	
	public Card(String number, char suit, int value) {
		this.number = number;
		this.suit = suit;
		this.value = value;
	}

	public void setNext(Card n)
	{
	next = n;
	}
	public Card getNext()
	{
	return next;
	}

	public String getNumber() {
		return number;
	}


	public char getSuit() {
		return suit;
	}


	public int getValue() {
		return value;
	}
	
}
