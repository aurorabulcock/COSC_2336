
import java.io.FileWriter;
import java.io.IOException;



public class NumberGenerator {
 public static int size = 1000000;
 public static int[] numbers = new int[size];
 public static int min = 1;
 public static int max = 1000000000;
 
 public static void main(String[] args) {
	 
	 for (int x=0;x<size;x++) {
		 numbers[x]=getRand(min,max);
	 }

		 writeList(numbers,size);

	 
	 //numbers=bsort(numbers);
	 //System.out.println("FINAL LIST: ");
	 //printList(numbers);
	 
	 
 }
 public static void writeList(int[] numbers, int size) {
	 try {	
		FileWriter fr = new FileWriter("sortme"+size+".txt");
		
		for (int x = 0; x< size; x++) {
				fr.write(numbers[x]+"\n");
			}
		System.out.println("File sortme"+size+".txt created");
		fr.close();
		} catch(IOException e) {
			System.out.println("File error");
		}
	}
 
 public static int getRand(int min, int max) {
	 if (min >= max) {
		 System.out.println("Range error: min greater than max");
		 return -1;
	 }
	 int r = (int)(Math.random() * ((max-min)+1))+min;
	 return r;
 }
	
}
