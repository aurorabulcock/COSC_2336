import java.time.Duration;
import java.time.Instant;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BubbleModified
{

	public static void main(String[] args)
	{
		//int size = 10;
		int maxSize = 1000000;
		int[] numlist = new int[maxSize];
		String inputFile = "sortme1000000.txt";
		numlist = initialize(inputFile,maxSize);
		
		for (int x = 10; x<=1000000;x*=10) {
			System.out.println("\nSorting first "+x+" elements");
		//printlist(numlist,x);
		Instant start = Instant.now();		
		bubblesort(numlist,x);
		Instant finish = Instant.now();
		long elapsed = (long)Duration.between(start, finish).toMillis();
		System.out.printf("FINAL LIST: %d Milliseconds\n",elapsed);		
		//printlist(numlist,x);
		}
		// TODO Auto-generated method stub

	}
	public static int[] initialize(String inFile, int maxSize) {
		int[] numbers = new int[maxSize];
		try {
			FileReader fr = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fr);
			for (int i = 0; i< maxSize; i++) {
				numbers[i]=Integer.parseInt(br.readLine());
			}
		} catch (IOException e) {
			System.out.println("File error: "+inFile);
		}
		return numbers;
	}

	public static int[] bubblesort(int[] numlist, int count)
	{ 
		long comparisons = 0;
		long swaps = 0;
		boolean swap;
		
		int temp = 0;
		for (int i = 0; i < count - 1;i++)
		{
			swap = false;
			for (int j = 0; j < ((count - 1) - i);j++)
			{
				comparisons++;
				if (numlist[j] > numlist[j+1])
				{
					swaps++;
					temp = numlist[j+1];
					numlist[j+1] = numlist[j];
					numlist[j] = temp;
					swap = true;
				}
			}
			if (!swap) {
				break;
			}
			//printlist(numlist);

		}
		System.out.println("Comparisons: " + comparisons + "\nSwaps: " + swaps);
		
		return numlist;
	}
	
	public static void printlist(int[] numlist)
	{
		for (int i = 0; i < numlist.length; i++)
		{
			System.out.print(numlist[i] + " ");			
		}
		System.out.println("");
	}
}
