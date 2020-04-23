package DataLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that reads from Treasury yield input file
 * @author tarek
 *
 */
public class TreasuryYieldReader {
	
	/**
	 * read 5-year US bond yield from TreasuryYield.csv
	 * @return HashMap of daily 5-year bond yield in year 2019
	 * @throws ParseException 
	 */
	public static Map<String, Double> readTreasuryYield() {
		//Hashmap to hold data items
		Map<String, Double> treasuryYieldMap = new HashMap<String, Double> ();  
		
		File file = new File ("Data/TreasuryYield.csv"); //file to read
		try {
			Scanner fileReader = new Scanner(file); //scanner to read file
			//skip title row
			fileReader.nextLine();
			
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				//use ", " to split data and store them in the "lineComponents" array
				String[] lineComponents = line.split(",");
				
				// change the data type of date from String to Calendar
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
				Calendar calendar = Calendar.getInstance();// get the instance of calendar
				calendar.setTime(formatter.parse(lineComponents[0]));//set the Time of current calendar object
				double yield_5yr = Double.parseDouble(lineComponents[8]);
				
				// use the year, month and date as the key of treasuryYieldMap
				String calendarKey = calendar.get(Calendar.YEAR) + "," + calendar.get(Calendar.MONTH) 
				+ "," + calendar.get(Calendar.DATE);
				
				treasuryYieldMap.put(calendarKey, yield_5yr);
			}
			fileReader.close();
		} catch (FileNotFoundException e) { //file not found exception
			System.out.println("file not found!");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("parse calendar exception!");
			e.printStackTrace();
		}
		
		return treasuryYieldMap; //return list for use in project
	}
}
