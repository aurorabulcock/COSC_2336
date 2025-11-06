
public class Tree {
	node root = new node("");

	public boolean isLeaf(node n)
	{
		if((n.no == null) && (n.yes == null))
		{
			return true;
		} else
		{
			return false;
		}
	}
}
