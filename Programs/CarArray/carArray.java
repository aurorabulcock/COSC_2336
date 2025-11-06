
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class carArray {
	public static void main(String[] args)
			throws IOException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int numCars = 50;
		car[] collection = new car[numCars];
		
		//***************************
		//Read in cars from file
		//***************************
		
		String dataFile = "cars-1.csv";
		FileReader fr = new FileReader("cars-1.csv");
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		String splitBy = ",";
		int carCount = 0;
		while((((line = br.readLine()) != null)) && (carCount < numCars))
		{
			String[] data = line.split(splitBy);
			collection[carCount] = new car();
			collection[carCount].NewCar(data);
			carCount++;
		}//end of while

		//*****************************
		//Menu
		//*****************************
		boolean running = true;
		int ccar = 0; //current car
		int menu1 = 0;
		while(running)
		{
			System.out.println("\n*************************");
			System.out.printf("* CURRENT CAR# %2d/%2d *\n",ccar + 1,carCount);
			System.out.println("* Choose an option: *");
			System.out.println("*************************");
			System.out.println("* 1. Change car *");
			System.out.println("* 2. Accelerate *");
			System.out.println("* 3. Brake *");
			System.out.println("* 4. Current Speed *");
			System.out.println("* 5. Delete Car *");
			System.out.println("* 6. Add Car *");
			System.out.println("* 7. List Cars *");
			System.out.println("* 8. Quit *");
			System.out.println("*************************");
			System.out.print(" Choice:");

			int choice = 0;
			menu1 = s.nextInt();
			s.nextLine();

			switch(menu1)
			{

			case 1: //choose new car
				do
				{
					System.out.println("\n*************************");
					System.out.printf("* Select a new car <=%2d *\n",carCount);
					System.out.println("*************************");
					System.out.print(" Choice:");
					choice = s.nextInt();
					s.nextLine();
				}//end of do loop
				while ((choice > carCount) || (choice < 0));
				ccar = choice-1;
				break;

			case 2: //accelerate
				do
				{
					System.out.println("\n*************************");
					System.out.printf("* Amount to speed up *\n",carCount);
					System.out.println("*************************");
					System.out.print(" MPH:");
					choice = s.nextInt();
					s.nextLine();
				}//end of do loop
				while (!((choice > 0) && (choice < car.MAXSPEED)));
				collection[ccar].accelerate(choice);
				break;

			case 3: //brake
				do
				{
					System.out.println("\n*************************");
					System.out.printf("* Amount to slow down *\n",carCount);
					System.out.println("*************************");
					System.out.print(" MPH:");
					choice = s.nextInt();
					s.nextLine();
				}//end of do loop
				while (!((choice > 0) && (choice < car.MAXSPEED)));
				collection[ccar].brake(choice);
				break;

			case 4: //speed
				System.out.println("\n*************************");
				System.out.printf("* Current Speed Car %2d *\n",ccar
						+ 1);
				System.out.println("*************************");
				System.out.printf(" MPH:%1.1f\n",collection[ccar].speed);
				break;

			case 5: //Delete
				System.out.println("\n*************************");
				System.out.printf("* Delete Current Car %2d *\n",ccar
						+ 1);
				System.out.println("*************************");
				System.out.printf(" %s\t%s\t%s\n",
						collection[ccar].VIN,collection[ccar].plateState,collection[ccar].plate);
				System.out.print("--ARE YOU SURE?--(Y/N)> ");
				String verify = s.nextLine();
				if(verify.contains("Y") || verify.contains("y"))
				{
					// add delete code here
					for (int d = ccar; d<carCount-1;d++) {
						collection[d]=collection[d+1];
					}//end of for loop
					carCount--;
					System.out.println("***Done***");
				}//end of if loop
				break;

			case 6: //Add
				System.out.println("\n*************************");
				System.out.printf("* Add Car *\n");
				System.out.println("*************************");

				// Add code here
				String[] data = new String[3];
				System.out.print("Car "+(carCount+1)+" VIN: ");
				data[0] = s.nextLine();

				System.out.print("Car "+(carCount+1)+" Plate State: ");
				data[1] = s.nextLine();

				System.out.print("Car "+(carCount+1)+" Plate: ");
				data[2] = s.nextLine();

				collection[carCount]=new car();
				collection[carCount].NewCar(data);
				carCount++;
				System.out.println("***Done***");

				break;

			case 7: //List
				System.out.println("\n*************************");
				System.out.printf("* List Cars *\n");
				System.out.println("*************************");
				for (int i = 0; i< carCount;i++)
				{
					System.out.printf(" * %d:\t%s\t%s\t%s\n",(i +
							1),collection[i].VIN,collection[i].plateState,collection[i].plate);
				}//end of for loop
				break;

			case 8: //quit
				running = false;
				// Add code to update file here
				export(dataFile,collection,carCount);
				break;
			default:
				break;
			}//end of switch
		}//end of while loop
		s.close();
		System.out.println(" Goodbye");
	}

	private static void export(String outFile, car[] cars, int carCount) {
		// TODO Auto-generated method stub
		int outCount =0;
		try {
			System.out.println("\nExporting to File...");
			FileWriter writeToFile = new FileWriter(outFile, false);
			for (int c = 0;c<carCount;c++) {
				System.out.println("Exporting " + c);
				String outString = String.format("%s,%s,%s\n",cars[c].VIN, cars[c].plateState,cars[c].plate);
				writeToFile.write(outString);
				outCount++;
			}//end of for loop
			System.out.println("Wrote "+outCount+" Cars\nDone!\n");
			writeToFile.close();

		}//end of try
		catch (IOException e) {
			System.out.println(""+outFile+"ERROR\n");
			e.printStackTrace();
		}//end of catch
	}//end of export

}