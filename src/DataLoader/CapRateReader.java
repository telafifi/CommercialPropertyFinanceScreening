package DataLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that reads cap rates from an input file
 * @author tarek
 *
 */
public class CapRateReader {

	/**
	 * read cap rate data from CapRate.csv and return a list of data objects
	 * @return a HashMap of cap rate of different property type in different location
	 */
	public static Map<String, Double> readCapRate() {
		//new hash map to hold line items
		Map<String, Double> capRateMap = new HashMap<String, Double> ();  
		File file = new File ("Data/CapRatesFinal.csv"); //file location
		
		try {
			Scanner fileReader = new Scanner(file); //new file to read
			
			//skip title row
			fileReader.nextLine();
			
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine(); //get row
				//use ", " to split data and store them in the "lineComponents" array
				String[] lineComponents = line.split(","); 
				
				String propertyType = lineComponents[0].replace("-", "").toLowerCase();;
				String state = lineComponents[1].toLowerCase();
				String city = lineComponents[2].toLowerCase();
				
				double capRate = Double.parseDouble(lineComponents[3]); //read cap rate
				
				// put the key (propertyTpye,state,city) and value in the capRateMap
				capRateMap.put(propertyType + "," + state + "," + city, capRate);
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) { //file not found error
			System.out.println("file not found!");
			e.printStackTrace();
		}
		
		return capRateMap; //return list for use in project
	}
}
