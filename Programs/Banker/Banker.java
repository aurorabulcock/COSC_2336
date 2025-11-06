import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Banker{
	public static Queue q = new Queue();
	public static Scanner s = new Scanner(System.in);
	public static LL l = new LL();
	public static String fileName = "clients.csv";
	public static String outFile = "clients2.csv";

	public static void main(String[] args ){
		boolean running = true;
		int choice = 0;

		importFile(l, fileName);

		choice = showQueueMenu();
		do {
			switch (choice){
			case 1://ADD
				System.out.println("Adding");
				add();
				break;
			case 2://PEEK
				System.out.println("Peeking");
				peek();
				break;
			case 3://SERVE
				System.out.println("Serving");
				serve();
				break;
			case 4://QUIT
				System.out.println("Quitting");
				running = quit();
				break;
			default://OOPS
				System.out.println("Oops! That was an invalid choice, please try again");
				break;
			}
			if (running)
			{
				choice = showQueueMenu();
			}
		} while (running);
		s.close();
	}//END OF MAIN

	//FIRST MENU FOR QUEUE	
	public static int showQueueMenu()
	{
		int choice = 0;
		while ((choice < 1) || (choice > 4))
		{
			System.out.println("***** MENU *****");
			System.out.println("	1. ADD");
			System.out.println("	2. PEEK");
			System.out.println("	3. SERVE");
			System.out.println("	4. QUIT");
			System.out.println("*****************");
			System.out.print("	CHOICE: ");
			choice = s.nextInt();
		}
		return choice;
	}//END OF QUEUE MENU

	//OPTIONS FOR QUEUE MENU
	public static void add(){
		String fn = "";
		String ln = "";
		long acct = 0;
		double bal = 0.0;
		s.nextLine(); //clear buffer
		System.out.print(" New Client in Queue:\n");
		System.out.print(" First Name: ");
		fn = s.nextLine();
		System.out.print(" Last Name: ");
		ln = s.nextLine();
		System.out.print(" Account Number: ");
		acct = s.nextLong();
		Client c = new Client(acct,ln,fn,bal);
		q.add(c);
	}//END OF ADD

	public static void peek(){
		Client c = q.peek();
		if (c != null)
			System.out.printf("%s %s is next in line\n\n",c.getFname(),c.getLname());
		else
			System.out.println("Queue is empty\n\n");

	}//END OF PEEK

	public static boolean quit(){
		boolean running = true;
		if (q.peek() == null) {
			running = false;
			export(outFile,l);
		}
		return running;
	}//END OF QUIT

	public static void serve(){
		int choice = 0;


		//VERIFICATION
		Client c = q.serve();
		System.out.println("Serving " + c.getFname() + " " + c.getLname());

		Client currentClient = findClient(c.getAccount());
		if (currentClient != null) {

			if (currentClient.getFname().equalsIgnoreCase(c.getFname()) && 
					currentClient.getLname().equalsIgnoreCase(c.getLname())) {

				choice = serveMenu();
				boolean serving = true;
				do {
					switch (choice)
					{
					case 1://DEPOSIT
						System.out.println("DEPOSIT");
						deposit(currentClient);
						break;
					case 2://WITHDRAW
						System.out.println("WITHDRAW");
						withdraw(currentClient);
						break;
					case 3://BALANCE
						System.out.println("BALANCE");
						balance(currentClient);
						break;
					case 4://CLOSE ACCOUNT
						System.out.println("CLOSE ACCOUNT");
						close(currentClient);
						break;
					case 5://QUIT
						System.out.println("QUIT");
						serving = false;
						break;
					default://OOPS
						System.out.println("Invalid choice");
						break;
					}
					if (serving)
						choice = serveMenu();

				} while (serving);

			}else 
				System.out.println("Hmmm, we can't find the client you're looking for. Please try again.");

		}

	}//END OF SERVE

	//MENU FOR SERVING CLIENTS
	public static int serveMenu()
	{
		int choice = 0;
		while ((choice < 1) || (choice > 5))
		{
			System.out.println("******   MENU   ******");
			System.out.println("*** 1. DEPOSIT  ******");
			System.out.println("*** 2. WITHDRAW  *****");
			System.out.println("*** 3. VIEW BALANCE  *");
			System.out.println("	4. CLOSE ACCOUNT");	
			System.out.println("*** 5. QUIT       ***");
			System.out.println("***************");
			System.out.println("****** CHOICE: ");
			choice = s.nextInt();
		}
		return choice;
	}//END OF SERVE MENU

	//SERVE MENU OPTIONS

	public static void balance(Client c) {
		System.out.println("Your current balance is: " +c.getBalance());
	}//END OF BALANCE

	public static void deposit (Client c) {
		System.out.print("Please enter the amount you wish to deposit: $");
		double d = s.nextDouble();
		c.setBalance(c.getBalance() + d);
		System.out.printf("Your new balance is: $%1.2f\n" + c.getBalance());
	}//END OF DEPOSIT

	public static void withdraw (Client c) {
		System.out.print("Please enter the amount you wish to withdraw: $");
		double w = s.nextDouble();

		if((c.getBalance() -w) < 0) {
			System.out.println("The amount entered exceeds the balance. Please try again.");
		}else {
			c.setBalance(c.getBalance() - w);
			System.out.println("Your new balance is: $%1.2f\n"+c.getBalance());
		}
	}//END OF WITHDRAW

	public static void close (Client c) {

		String yesorno = null;
		System.out.println("Are you sure you want to close your account? Y/N: ");
		yesorno = s.next();

		if (yesorno.equalsIgnoreCase("y"))
			delete(c);
		else 
			return; 
	}//END OF CLOSE

	public static void delete(Client c)
	{
		Client currentClient = l.getFirst();
		Client previousClient = l.getFirst();

		while ((currentClient != null) && (currentClient.getAccount() != c.getAccount())){
			previousClient = currentClient;
			currentClient = currentClient.getNext();	
		}
		previousClient.setNext(currentClient.getNext());
	}//END OF DELETE

	//CLIENT FUNCTIONS

	public static Client findClient(long acct) {
		Client c = l.getFirst(); //c stands for currentClient
		while((c != null) && (c.getAccount() != acct)) {
			c = c.getNext();
		}
		if (c == null)
		{
			System.out.println("Account not found\n");
		}
		return c;
	}//END OF FIND CLIENT


	public static Client findPreviousClient(long acct) {
		Client c = l.getFirst(); //c stands for currentClient
		Client p = null; //p stands for previousClient

		while (c.getAccount() != acct) {
			p = c;
			c = c.getNext();

		}
		return p;
	}//END OF FIND PREVIOUS CLIENT

	//IMPORT AND EXPORT

	public static void importFile(LL ll, String fileName) {
		try {
			long count = 0;
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);


			String newLine = br.readLine();  //ignore first line of headers
			newLine = br.readLine();
			String parseBy = ",";

			while (newLine != null){
				count++;
				String[] data = newLine.split(parseBy);
				Client c = new Client(Long.parseLong(data[0]),data[2],data[1],
						Double.parseDouble(data[3]));
				ll.add(c);

				newLine = br.readLine();
			} 
			System.out.printf("File: %s\n%d lines read\n",fileName,count);
			br.close(); 

		} catch (IOException e){
			System.out.println("File Error\n" + e);
		}
	}//END OF IMPORT

	private static void export(String outFile, LL ll) {
		int outCount =0;
		try {
			System.out.println("\nExporting to File...");
			FileWriter writeToFile = new FileWriter(outFile, false);
			Client c = ll.getFirst();
			while (c != null) {
				System.out.println("Exporting " + c.getAccount());
				String outString = String.format("%d,%s,%s,%1.2f\n",c.getAccount(),
						c.getFname(),c.getLname(),c.getBalance());
				writeToFile.write(outString);
				c = c.getNext();
				outCount++;
			}
			System.out.println("Wrote "+outCount+" Clients\nDone!\n");
			writeToFile.close();

		}catch (IOException e) {
			System.out.println(""+outFile+"ERROR\n");
			e.printStackTrace();
		}

	}//END OF EXPORT

}//END OF BANKER









