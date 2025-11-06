import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//things to do:
//1.  findMin(category,value)
//2.  clean up data (remove -99 values form list)

public class WorldTemps 
{

	public static void main(String[] args) 
	{
		LL tempList = new LL();
		String fileName = "city_temperature.csv";		
		//String fileName = "WorldTemps/city_temperature.csv";		
		//String fileName = "src/WorldTemps/city_temperature.csv";		

		//Import file to linked list
		importFile(tempList, fileName);

		reading min = findMin(tempList);
		System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		reading max = findMax(tempList);
		System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());



		//find max temp in Texas
		max = findMax(tempList, "State", "Texas");
		System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

		//find min temp in Texas
		min = findMin(tempList, "State", "Texas");
		System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		//OTHER CATEGORIES
		
		//State of New York
		
			//find max temp in New York
			max = findMax(tempList, "State", "New York");
			System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

			//find min temp in New York
			min = findMin(tempList, "State", "New York");
			System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		//Country of France
		
				//find max temp in France
				max = findMax(tempList, "Country", "France");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in France
				min = findMin(tempList, "Country", "France");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		//Country of Mexico
				
				//find max temp in Mexico
				max = findMax(tempList, "Country", "Mexico");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in Mexico
				min = findMin(tempList, "Country", "Mexico");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		//State of Arizona
				
				//find max temp in Arizona
				max = findMax(tempList, "State", "Arizona");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in Arizona
				min = findMin(tempList, "State", "Arizona");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());
		
		//Year 2003
				
				//find max temp in 2003
				max = findMax(tempList, "Year", "2003");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in 2003
				min = findMin(tempList, "Year", "2003");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		//Year 2010
				
				//find max temp in 2010
				max = findMax(tempList, "Year", "2010");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in 2010
				min = findMin(tempList, "Year", "2010");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

		//Country of Norway
				
				//find max temp in Norway
				max = findMax(tempList, "Country", "Norway");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in New York
				min = findMin(tempList, "Country", "Norway");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());
				
		//State of Florida
				
				//find max temp in Florida
				max = findMax(tempList, "State", "Florida");
				System.out.printf("Max Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", max.getAvgTemperature(), max.getMonth(), max.getDay(), max.getYear(), max.getCity(), max.getState(), max.getCountry());

				//find min temp in Florida
				min = findMin(tempList, "State", "Florida");
				System.out.printf("Min Temp: %1.2f on %d-%d-%d in %S, %s, %s\n", min.getAvgTemperature(), min.getMonth(), min.getDay(), min.getYear(), min.getCity(), min.getState(), min.getCountry());

	}

	public static void importFile(LL ll, String fileName)
	{
		try 
		{
			long count = 0;
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);



			String newLine = br.readLine();  //ignore first line of headers
			newLine = br.readLine();
			String parseBy = ",";

			while (newLine != null)
			{
				count++;
				String[] data = newLine.split(parseBy);
				reading r = new reading(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Double.parseDouble(data[7]));
				ll.add(r);


				newLine = br.readLine();
			}	
			System.out.printf("File: %s\n%d lines read\n",fileName,count);
			br.close();			
		} catch (IOException e)
		{
			System.out.println("File Error\n" + e);
		}

	}

	public static reading findMax(LL ll)
	{
		//find overall max average temp
		reading maxReading = ll.getFirst();
		ll.resetCurrent();

		reading current = ll.getNextElement();
		while (current != null)
		{
			if ( current.getAvgTemperature() > maxReading.getAvgTemperature())
			{
				maxReading = current;
			}
			current = ll.getNextElement();
		}


		return maxReading;
	}


	public static reading findMax(LL ll, String category, String value)
	{
		//category types:     "State","Country","Year","City","Month","Region"
		int key = 0;

		if (category.equalsIgnoreCase("Region"))
		{
			key = 1;
		} else if (category.equalsIgnoreCase("Country"))
		{
			key = 2;
		} else if (category.equalsIgnoreCase("State"))
		{
			key = 3;
		} else if (category.equalsIgnoreCase("City"))
		{
			key = 4;
		} else if (category.equalsIgnoreCase("Month"))
		{
			key = 5;
		} else if (category.equalsIgnoreCase("Year"))
		{
			key = 6;
		} 

		System.out.printf("\nLooking for max average temp where %s = %s\n", category,value);

		reading maxReading = new reading("","","","",0,0,0,-99);
		ll.resetCurrent();
		reading current = ll.getFirst();

		//reading current = ll.getNextElement();
		while (current != null)
		{
			if ( current.getAvgTemperature() > maxReading.getAvgTemperature())
			{	
				switch (key)
				{
				case 1:
					if (current.getRegion().equalsIgnoreCase(value))
					{
						maxReading = current;							
					}
					break;
				case 2:
					if (current.getCountry().equalsIgnoreCase(value))
					{
						maxReading = current;							
					}
					break;
				case 3:
					if (current.getState().equalsIgnoreCase(value))
					{
						maxReading = current;							
					}
					break;
				case 4:
					if (current.getCity().equalsIgnoreCase(value))
					{
						maxReading = current;							
					}
					break;
				case 5:
					if (current.getMonth() == Integer.parseInt(value))
					{
						maxReading = current;							
					}
					break;
				case 6:
					if (current.getYear() == Integer.parseInt(value))
					{
						maxReading = current;							
					}
					break;
				}



			}
			current = ll.getNextElement();
		}


		return maxReading;
	}	
	
	public static reading findMin(LL ll)
	{
		//find overall min average temp
		reading minReading = ll.getFirst();
		if (minReading.getAvgTemperature() == -99)
		{
			minReading.setAvgTemperature(999);
		}
		ll.resetCurrent();

		reading current = ll.getNextElement();
		while (current != null)
		{
			if ((current.getAvgTemperature() != -99) && ( current.getAvgTemperature() < minReading.getAvgTemperature()))
			{
				minReading = current;
			}
			current = ll.getNextElement();
		}


		return minReading;
	}

	public static reading findMin(LL ll, String category, String value)
	{
		//category types:     "State","Country","Year","City","Month","Region"
		int key = 0;

		if (category.equalsIgnoreCase("Region"))
		{
			key = 1;
		} else if (category.equalsIgnoreCase("Country"))
		{
			key = 2;
		} else if (category.equalsIgnoreCase("State"))
		{
			key = 3;
		} else if (category.equalsIgnoreCase("City"))
		{
			key = 4;
		} else if (category.equalsIgnoreCase("Month"))
		{
			key = 5;
		} else if (category.equalsIgnoreCase("Year"))
		{
			key = 6;
		} 

		System.out.printf("\nLooking for min average temp where %s = %s\n", category,value);

		reading minReading = new reading("","","","",0,0,0,99);
		ll.resetCurrent();
		reading current = ll.getFirst();

		//reading current = ll.getNextElement();
		while (current != null)
		{
			if ((current.getAvgTemperature() != -99) && ( current.getAvgTemperature() < minReading.getAvgTemperature()))
			{	
				switch (key)
				{
				case 1:
					if (current.getRegion().equalsIgnoreCase(value))
					{
						minReading = current;							
					}
					break;
				case 2:
					if (current.getCountry().equalsIgnoreCase(value))
					{
						minReading = current;							
					}
					break;
				case 3:
					if (current.getState().equalsIgnoreCase(value))
					{
						minReading = current;							
					}
					break;
				case 4:
					if (current.getCity().equalsIgnoreCase(value))
					{
						minReading = current;							
					}
					break;
				case 5:
					if (current.getMonth() == Integer.parseInt(value))
					{
						minReading = current;							
					}
					break;
				case 6:
					if (current.getYear() == Integer.parseInt(value))
					{
						minReading = current;							
					}
					break;
				}



			}
			current = ll.getNextElement();
		}


		return minReading;

	}
}
