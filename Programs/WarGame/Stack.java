
public class Stack
{

	private Card head = null;

	public void push(Card c)
	{
		if(head != null)
		{
			c.setNext(head); 
		}
		head = c;
	}

	public Card pop()
	{
		Card p = head;
		if(head != null)
		{
			head = head.getNext();
		}
		return p;
	}
	
	public boolean notEmpty()
	{
		if(head == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean empty()
	{
		if(head == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
