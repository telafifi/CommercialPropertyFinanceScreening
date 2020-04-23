package DataLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that reads population information from file
 * @author tarek
 *
 */
public class PopulationReader {
	
	/**
	 * read population estimate of 2018 of US cities from Population.csv
	 * @return a HashMap of population of US cities
	 */
	public static Map<String, Integer> readPopulation(){
		//new hash map to hold line items
		Map<String, Integer> populationMap = new HashMap<String, Integer>(); 
		
		File file = new File ("Data/Population.csv"); //file location
		
		try {
			Scanner fileReader = new Scanner(file); //scanner to read file
			//skip title row
			fileReader.nextLine();
			
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine(); //get each row
				//use ", " to split data and store them in the "lineComponents" array
				String[] lineComponents = line.split(",");
				
				// use substring() method to remove " " sign in the string of city and state
				String city = lineComponents[1].substring(1).replace(" city", "").toLowerCase();
				String state = lineComponents[2].substring(1,lineComponents[2].length() - 1).toLowerCase();
				int population = Integer.parseInt(lineComponents[13]);
				
				// put the key (city and state) and value in the populationMap 
				populationMap.put(city + "," + state, population);
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) { //file not found exception
			System.out.println("file not found!");
			e.printStackTrace();
		}
		
		return populationMap; //return list for use in project
	}
	
}
