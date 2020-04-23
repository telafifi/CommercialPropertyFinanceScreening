package InputInformation;

/**
 * Class that returns a value for input combo boxes
 * @author tarek
 *
 */
public class ReadComboBoxes {
	
	/**
	 * Read the Loan covenant selected by user and return proper option
	 * @param selectedIndex Index selected by user
	 * @return
	 */
	public static LoanCovenant getLoanCovenant(int selectedIndex) {
		LoanCovenant covenant = LoanCovenant.FederalFullGuarantee;
		switch (selectedIndex) {
		case 0:
			covenant = LoanCovenant.FederalFullGuarantee;
			break;
		case 1:
			covenant = LoanCovenant.CorporateGuarantee;
			break;
		case 2:
			covenant = LoanCovenant.NoGuarantee;
			break;
		}
		return covenant;
	}
	
	/**
	 * Read the property Type selected by user and return proper option
	 * @param selectedIndex Index selected by user
	 * @return
	 */
	public static PropertyType getPropertyType(int selectedIndex) {
		PropertyType property = PropertyType.MultiFamily;
		switch (selectedIndex) {
		case 0:
			property = PropertyType.MultiFamily;
			break;
		case 1:
			property = PropertyType.Retail;
			break;
		case 2:
			property = PropertyType.Office;
			break;
		case 3:
			property = PropertyType.Industrial;
			break;
		case 4:
			property = PropertyType.Hospitality;
			break;
		case 5:
			property = PropertyType.Other;
			break;
		}
		return property;
	}
	
	/**
	 * Read the borrower type selected by user and return proper option
	 * @param selectedIndex Index selected by user
	 * @return
	 */
	public static BorrowerType getBorrowerType(int selectedIndex) {
		BorrowerType borrower = BorrowerType.Individual;
		switch (selectedIndex) {
		case 0:
			borrower = BorrowerType.Individual;
			break;
		case 1:
			borrower = BorrowerType.Corporate;
			break;
		}
		return borrower;
	}
}
