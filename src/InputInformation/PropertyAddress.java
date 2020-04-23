package InputInformation;

/**
 * Class that holds the address input by the user
 * @author tarek
 *
 */
public class PropertyAddress {
	public String Address; //Street address of property
	public String City; //City of property
	public String State; //State of property
	public int Zip; //Zip code of property
	
	/**
	 * Physical address of the property
	 * @param Address Street address of property
	 * @param City City of property
	 * @param State State of property
	 * @param Zip zip code of property
	 */
	public PropertyAddress(String Address, String City, String State, int Zip) {
		this.Address = Address;
		this.City = City;
		this.State = State;
		this.Zip = Zip;
	}
	
	// Getters
	public String getAddress() {
		return Address;
	}

	public String getCity() {
		return City;
	}

	public String getState() {
		return State;
	}

	public int getZip() {
		return Zip;
	}
}
