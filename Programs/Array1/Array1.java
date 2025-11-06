import java.util.Scanner;

public class Array1 {

	public static void main(String[] args) {
		int maxStudents = 200;
		int myMax = 0;
		int[] students = new int[maxStudents];
		
		Scanner s = new Scanner(System.in);
		
		boolean stillGoing = true;
		
		while ((myMax<maxStudents) && (stillGoing == true)) {
				System.out.print("Enter ID #"+myMax+": ");
				
				int temp = s.nextInt();
				if (temp !=0) {
					students[myMax] =temp;
					myMax++;
				}//end of for loop
				else {
					stillGoing = false;
				}//end of else loop
		}//end of while loop
		
		
		//Output
		for (int x=0;x<maxStudents;x++){
			System.out.println("ID #"+students[x]);
			
		}//end of for loop
		
		s.close();

	}

}
