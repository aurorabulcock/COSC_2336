import java.time.Duration;
import java.time.Instant;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FinalizedAlgorithms {

	public static long comparisons = 0;
	public static long swaps = 0;	
	
	public static void main(String[] args)
	{
		
//BEGINNING INITIALIZATION
		//int size = 10;
		int maxSize = 1000000;
		int[] numlist = new int[maxSize];
		String inputFile = "sortme1000000.txt";
		
		numlist = initialize(inputFile,maxSize);
		
		for (int x = 10; x <=1000000; x*=10)
		{
			swaps = 0;
			comparisons = 0;
			System.out.println("\n**************************\nSorting first " + x + " elements.\n**************************");
			//printlist(numlist,x);
			Instant start = Instant.now();	
			
//SORTING ALGORITHMS
			//bubblesort(numlist,x);
			//selectionSort(numlist,x);
			insertionSort(numlist,x);
			//mergeSort(numlist,x);
			//quickSort(numlist,0,x-1);
			//heapSort(numlist,x);

			Instant finish = Instant.now();
			long elapsed = (long)Duration.between(start, finish).toMillis();
			
//PRINT RESULTS
			//System.out.println("\nComparisons: " + comparisons + "\nSwaps: " + swaps);

			System.out.printf("FINAL LIST: %d Milliseconds\n**************************\n**************************\n",elapsed);		
			//printlist(numlist,x);
		}		
	}
	
//HEAP SORT	
	public static void heapSort(int arr[],int N)
	{
		swaps = 0;
		comparisons = 0;

		// Build heap (rearrange array)
		for (int i = N / 2 - 1; i >= 0; i--)
			heapify(arr, N, i);

		// One by one extract an element from heap
		for (int i = N - 1; i > 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			swaps++;
			

			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	public static void heapify(int arr[], int N, int i)
	{
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		comparisons++;
		if (l < N && arr[l] > arr[largest])
			largest = l;

		// If right child is larger than largest so far
		comparisons++;
		if (r < N && arr[r] > arr[largest])
			largest = r;

		// If largest is not root
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			swaps++;
			// Recursively heapify the affected sub-tree
			heapify(arr, N, largest);
		}
	}
	
//QUICK SORT	
	private static void quickSort(int[] array, int start, int end) {
		
		if(end <= start) return; //base case
		
		int pivot = partition(array, start, end);
		quickSort(array, start, pivot - 1);
		quickSort(array, pivot + 1, end);		
		
		
	}
	
	private static int partition(int[] array, int start, int end) {
		
		int pivot = array[end];
		int i = start - 1;
		
		for(int j = start; j <= end; j++) {
			comparisons++;
			if(array[j] < pivot) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				swaps++;
			}
		}
		i++;
		int temp = array[i];
		array[i] = array[end];
		array[end] = temp;
		swaps++;
		
		return i;
	}

	public static int[] initialize(String infile, int maxSize)
	{
		int[] numbers = new int[maxSize];
		
		try
		{
			FileReader fr = new FileReader(infile);
			BufferedReader br = new BufferedReader(fr);
			
			for (int x = 0; x < maxSize; x++)
			{
				numbers[x] = Integer.parseInt(br.readLine());
			}
		}
		catch (IOException e)
		{
			System.out.println("File Error: " + infile);
		}
		
		return numbers;
	}

//INSERTION SORT
	private static void insertionSort(int[] array, int count) 
	{		
		long comparisons = 0;
		long swaps = 0;	
		
		for(int i = 1; i < count; i++) {
			int temp = array[i];
			int j = i - 1;
			
			while(j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				swaps++;
				j--;
				comparisons++;
			}
			swaps++;
			array[j + 1] = temp;
		}
		System.out.println("\nComparisons: " + comparisons + "\nSwaps: " + swaps);
		
	}	
	
	
//SELECTION SORT
	private static void selectionSort(int[] array, int count) {
		
		long comparisons = 0;
		long swaps = 0;		
		
		for(int i = 0; i < count - 1; i++) {
			int min = i;
			for(int j = i + 1; j < count; j++) {
				comparisons++;
				if(array[min] > array[j]) {
					min = j;
				}
			}
			
			int temp = array[i];
			array[i] = array[min];
			array[min] = temp;
			swaps++;
		}
		System.out.println("\nComparisons: " + comparisons + "\nSwaps: " + swaps);

		
	}

//BUBBLE SORT
	public static int[] bubblesort(int[] numlist, int count)
	{ 
		long comparisons = 0;
		long swaps = 0;
		
		int temp = 0;
		for (int i = 0; i < count - 1;i++)
		{
			for (int j = 0; j < ((count - 1) - i);j++)
			{
				comparisons++;
				if (numlist[j] > numlist[j+1])
				{
					swaps++;
					temp = numlist[j+1];
					numlist[j+1] = numlist[j];
					numlist[j] = temp;
				}
			}

		}
		System.out.println("\nComparisons: " + comparisons + "\nSwaps: " + swaps);
		
		return numlist;
	}
	
	public static void printlist(int[] numlist, int count)
	{
		for (int i = 0; i < count; i++)
		{
			System.out.print(numlist[i] + " ");			
		}
		System.out.println("");
	}
	
	private static void mergeSort(int[] array, int count)
	{
		int[] choppedArray = new int[count];
		for (int i = 0; i < count; i++)
		{
			choppedArray[i] = array[i];
		}
		swaps = 0;
		comparisons = 0;
		mergeSort(choppedArray);
		System.out.println("\nComparisons: " + comparisons + "\nSwaps: " + swaps);

	}

//MERGE SORT
	private static void mergeSort(int[] array) {
		
		int length = array.length;
		if (length <= 1) return; //base case
		
		int middle = length / 2;
		int[] leftArray = new int[middle];
		int[] rightArray = new int[length - middle];
		
		int i = 0; //left array
		int j = 0; //right array
		
		for(; i < length; i++) {
			if(i < middle) {
				leftArray[i] = array[i];
			}
			else {
				rightArray[j] = array[i];
				j++;
			}
		}
		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, array);
	}
	
	private static void merge(int[] leftArray, int[] rightArray, int[] array) {
		
		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;
		int i = 0, l = 0, r = 0; //indices
		
		//check the conditions for merging
		while(l < leftSize && r < rightSize) {
			comparisons++;
			if(leftArray[l] < rightArray[r]) {
				array[i] = leftArray[l];
				i++;
				l++;
			}
			else {
				array[i] = rightArray[r];
				swaps++;
				i++;
				r++;
			}
		}
		while(l < leftSize) {
			array[i] = leftArray[l];
			i++;
			l++;
		}
		while(r < rightSize) {
			array[i] = rightArray[r];
			i++;
			r++;
		}
	}

}

