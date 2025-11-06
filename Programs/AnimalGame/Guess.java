import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Guess
{
	public static String outFile = "Animals.csv";

	public static void main(String[] args)
	{
		Tree t = new Tree();
		t.root.question = "Elephant?";
		boolean playing = true;
		Scanner s = new Scanner(System.in);
		node c = t.root;
		String Astr = "";
		char a = ' ';
		boolean leaf = false;
		String outString = "";
		System.out.println("ANIMAL GUESSING GAME\n");
		while(playing)
		{
			if (t.isLeaf(c))
			{
				leaf = true;
				outString = "Are you a/an " + c.question;
			} else {
				leaf = false;
				outString = c.question;
			}
			System.out.println(outString + " (Y/N/Q): ");
			Astr = s.next();
			a = Astr.charAt(0);
			switch(a)
			{
			case 'Y':
			case 'y':
				if (leaf)
				{
					System.out.println("I am so smart!! I bet I can beat you again ;)\n"
							+ "Do you want to play again? :");
					Astr = s.next();
					a = Astr.charAt(0);
					if((a == 'n') || (a == 'N'))
					{
						playing = false;
					}
				} else {
					c = c.yes;
				}
				break;
			case 'N':
			case 'n':
				if (leaf)
				{
					s.nextLine(); //clears buffer
					System.out.print("What is your animal?: ");
					String animal = s.nextLine();
					//s.nextLine(); <- clears buffer
					System.out.print("Enter a question that determines a " +animal 
							+ " from a " + c.question + ": " );
					String q = s.nextLine();
					//s.nextLine(); //clears buffer
					System.out.print("Is the answer for a " +
							animal + " yes or No? (y/n):");
					String yn = s.nextLine();
					node newAnimal = new node(animal);
					node oldAnimal = new node(c.question);
					c.question = q;
					if (yn.equalsIgnoreCase("y")){
						c.yes = newAnimal;
						c.no = oldAnimal;
					}
					else{
						c.no = newAnimal;
						c.yes = oldAnimal;
					}
					System.out.print("Play again? (Y/N): ");
					String pa = s.nextLine();
					if (pa.equalsIgnoreCase("n")){
						playing = false;
					}
					else{
						c = t.root;
					}
				} else{
					c = c.no;
				}
				break;
			case 'Q':
			case 'q':
				playing = false;
				exportSavedGame(t);
				break;
			default:
				System.out.println("Y/N/Q only!!");
				break;
			}
		}
		s.close();	
		exportSavedGame(t);
		System.out.println("Goodbye! Thanks for playing :)");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Tree importSavedGame() {
		Tree t = new Tree();
		ArrayList<node> a = new ArrayList();

		try
		{
			FileReader fr = new FileReader(outFile);
			BufferedReader br = new BufferedReader(fr);
			String splitBy = ",";
			String line = br.readLine();
			while(line != null)
			{
				String[] data = line.split(splitBy);
				node n = new node(data[1]);
				while (Integer.parseInt(data[0]) >= a.size())
				{
					a.add(null);
				}
				a.set(Integer.parseInt(data[0]), n);
				line = br.readLine();
			}
			br.close();
			buildTree(1,a.get(1),a);
			t.root = a.get(1);
		}
		catch (IOException e)
		{
			System.out.println("File read error: " + outFile);
		}
		//BUILD TREE
		return t;

	}//END OF IMPORT

	public static void buildTree(int idx, node n, ArrayList<node> list)
	{
		if(((idx * 2) + 1) <= list.size())
		{
			if(list.get((idx * 2)) != null)
			{
				n.yes = list.get(idx * 2);
				buildTree((idx * 2),n.yes,list);
			}
			if(list.get((idx * 2) + 1) != null)
			{
				n.no = list.get((idx * 2) + 1);
				buildTree(((idx * 2) + 1),n.no,list);
			}
		}
	}//END OF BUILD TREE

	public static void exportSavedGame(Tree t) {
		try {
			FileWriter fr = new FileWriter(outFile);
			writeTree(t.root, fr, 1);
			System.out.println("Exported File: "+outFile);
			fr.close();
		}catch (IOException e) {
			System.out.println("File error: "+outFile+"\n"+e.toString());
		}

	}

	public static void writeTree(node r, FileWriter fr, int i) {
		if (r.yes != null) {
			writeTree(r.yes,fr,i*2);
		}
		//System.out.println(r.value);
		try {
			String outLine = i+","+r.question+ "\n";
			fr.write(outLine);
			System.out.println(outLine);
		}catch (IOException e) {
			System.out.println("File oops!");
		}
		if (r.no != null) {
			writeTree(r.no,fr,(i*2)+1);
		}
	}

}