import java.util.Scanner;

public class BinaryTree {

	public static Node root = new Node(-1);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int x = 0;
		Scanner s = new Scanner(System.in);



		do {

			System.out.print("Please enter a positive integer for x (enter -1 to quit): ");
			x = s.nextInt();

			if (x>0) {
				if (root.value == -1) {
					root.value = x;
				} else {
					Node n = new Node(x);
					addNode(n, root);
				}
			}
			
		}while(x>=0);
		
		printTree(root);
		
		s.close();
	}

	public static void addNode(Node n, Node r) {
		if (n.value <= r.value) {
			if (r.lessThan != null) {
			addNode(n,r.lessThan);	
			}else {
			r.lessThan = n;	
			}	
		} else
			if (r.greaterThan != null) {
				addNode(n,r.greaterThan);	
				}else {
				r.greaterThan = n;	
				}
	}

	public static void printTree(Node r) {
		if (r.lessThan != null) {
			printTree(r.lessThan);
		}
		
		System.out.println(r.value);
		
		if (r.greaterThan != null) {
			printTree(r.greaterThan);
		}
	}
}
