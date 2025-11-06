package minmax;

import java.util.Scanner;

public class minmax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 20;
		int[] numbers = new int[count];
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter "+count+" integers: ");
		
		for (int x = 0; x<count; x++) {
			System.out.print("#"+(x+1)+"> ");
			numbers[x]=s.nextInt();
		}//end of for loop
		
		System.out.println("***********************************************************************");
		outputArray(numbers, count);
		System.out.println("***********************************************************************");
		
		s.close();
	}
	
	public static void outputArray(int[] stuff, int numElements) {
		for (int x=0;x<numElements-1;x++) {
			System.out.print(stuff[x]+", ");
		}//end of for loop
		System.out.println(stuff[numElements-1]);
		System.out.println("");		
		System.out.println("Minimum: "+findMin(stuff, numElements));
		System.out.println("Maximum: "+findMax(stuff, numElements));
		
		
		return;
	}//end of outputArray
	
	public static int findMax(int[] stuff, int numElements) {
		int max = -999999;
		for (int x=0;x<numElements;x++) {
			
			if (stuff[x]>max)
				max = stuff[x];
			
		}//end of for loop
		return max;
	}//end of findMax
	
	public static int findMin(int[] stuff, int numElements) {
		int min = 999999;
		for (int x=0;x<numElements;x++) {
			
			if (stuff[x]<min)
				min = stuff[x];
			
		}//end of for loop
		return min;
	}//end of findMin

}
