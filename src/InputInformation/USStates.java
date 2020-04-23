package InputInformation;
import java.util.*;

/**
 * Class that populates states combo box and holds state Hashmap
 * @author tarek
 *
 */
public class USStates {
	private HashMap<String, String> states;
	private String[] sortedStates;
	
	/**
	 * Get sorted list of states
	 * @return
	 */
	public String[] getSortedStates() {
		return sortedStates;
	}

	/**
	 * Return a HashMap of states and state names
	 * @return
	 */
	public HashMap<String, String> getStates() {
		return states;
	}

	/**
	 * Get state hash map
	 */
	public USStates() {
		states = new HashMap<String, String>(); //open new hashmap
		this.AddStateList(); //Add all states
		this.CreateStateListStringArray(); //create string array
	}
	
	/**
	 * Convert the HashMap to a string array for use in input
	 */
	private void CreateStateListStringArray() {
		ArrayList<String> stateList = new ArrayList<>(states.keySet()); //get list of states
		Collections.sort(stateList); //sort them alphabetically
		this.sortedStates = new String[51]; //create a string array
		int i = 0;
		for (String state : stateList) {
			this.sortedStates[i] = state;
			i++;
		}
	}
	
	/**
	 * Add list of states to hashmap
	 */
	private void AddStateList() {
		states.put("AL", "Alabama");
		states.put("AK", "Alaska");
		states.put("AZ", "Arizona");
		states.put("AR", "Arkansas");
		states.put("CA", "California");
		states.put("CO", "Colorado");
		states.put("CT", "Connecticut");
		states.put("DE", "Delaware");
		states.put("DC", "District Of Columbia");
		states.put("FL", "Florida");
		states.put("GA", "Georgia");
		states.put("HI", "Hawaii");
		states.put("ID", "Idaho");
		states.put("IL", "Illinois");
		states.put("IN", "Indiana");
		states.put("IA", "Iowa");
		states.put("KS", "Kansas");
		states.put("KY", "Kentucky");
		states.put("LA", "Louisiana");
		states.put("ME", "Maine");
		states.put("MD", "Maryland");
		states.put("MA", "Massachusetts");
		states.put("MI", "Michigan");
		states.put("MN", "Minnesota");
		states.put("MS", "Mississippi");
		states.put("MO", "Missouri");
		states.put("MT", "Montana");
		states.put("NE", "Nebraska");
		states.put("NV", "Nevada");
		states.put("NH", "New Hampshire");
		states.put("NJ", "New Jersey");
		states.put("NM", "New Mexico");
		states.put("NY", "New York");
		states.put("NC", "North Carolina");
		states.put("ND", "North Dakota");
		states.put("OH", "Ohio");
		states.put("OK", "Oklahoma");
		states.put("OR", "Oregon");
		states.put("PA", "Pennsylvania");
		states.put("RI", "Rhode Island");
		states.put("SC", "South Carolina");
		states.put("SD", "South Dakota");
		states.put("TN", "Tennessee");
		states.put("TX", "Texas");
		states.put("UT", "Utah");
		states.put("VT", "Vermont");
		states.put("VA", "Virginia");
		states.put("WA", "Washington");
		states.put("WV", "West Virginia");
		states.put("WI", "Wisconsin");
		states.put("WY", "Wyoming");
	}
}
