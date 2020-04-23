package InputInformation;

/**
 * Class that holds tool tips for the radio buttons
 * @author tarek
 *
 */
public class RadioTips {
	/**
	 * Score tips for Location Score Radio Boxes
	 * @return
	 */
	public static String[] LocationScoreTips() {
		String[] radioToolTips = {
				"Immediate vicinity to public transport. Neighborhood is well\n established and in "
				+ "high demand.",
				"Located in urban/suburban middle income area with good access \nto public transit "
				+ "and usual amenities. Neighborhood has been completely built up and is in demand.",
				"Located in an older, established area of town where new developments \nare replacing "
				+ "older commercial properties.",
				"Located in developed but run-down area of the city/town where the amenities \nlook less-maintained. "
				+ "Local businesses are moving away, leaving higher vacancies than usual.",
				"Located in suburban areas with the neighborhood just being built up. \n"
				+ "Some amenities such as retain plaza and parks are on going."
		};
		return radioToolTips;
	}
	
	/**
	 * Score tips for the Tenant Quality Radio Boxes
	 * @return
	 */
	public static String[] TenantQualityScoreTips() {
		String[] radioToolTips = {
				"Tenant mix is diverse. Tenant quality is excellent. 75% of leases are more than \n18 months longer than loan term.",
				"Tenant mix of national and regional businesses. Over half the leases are more than \n18 months longer than the loan term.",
				"Tenant mix somewhat divese but small local businesses heavily rely on one large anchor \ntenant. Over half of the leases are more than 12 months"
				+ " longer than the loan term",
				"Tenant mix of small local businesses only operate in the neaghborhood with \nsome leases less than "
				+ "12 months longer than the loan term.",
				"Single Tenant."
		};
		return radioToolTips;
	}
	
	/**
	 * Score tips for the Property Age Radio Boxes
	 * @return
	 */
	public static String[] PropertyAgeScoreTips() {
		String[] radioToolTips = {
				"Property is modern, well maintained and readily adaptable to a wide range of uses. \nProperty improvements less than 5 years",
				"Property is well maintained and adaptable to alternate uses with minimal costs. \nProperty improvements between 5 and 10 years ago.",
				"Property is adequately maintained with limited deferred maintenances. \nProperty improvements between 10 and 15 years ago.",
				"Property is overall functioning but inadequately maintained and need some non-essential repairs. \nProperty improvements between 15 and 20 years ago.",
				"Older poorly maintained buildings in need of major repairs. \nNo property improvement/upgrades for 20 years."
		};
		return radioToolTips;
	}
	
	/**
	 * Score tips for the Environmental Assessment Radio Boxes
	 * @return
	 */
	public static String[] EnviroSiteScoreTips() {
		String[] radioToolTips = {
				"Phase I ESA report provided with no environmental issue identified",
				"",
				"Phase I ESA report provided with no significant environmental issue identified \nbut Phase II ESA is recommended.",
				"",
				"Phase II ESA provided with significant environmental issue identified and clean-up \ncost is significant."
		};
		return radioToolTips;
	}
	
	/**
	 * Score tips for the Borrower Payment Radio Boxes
	 * @return
	 */
	public static String[] BorrowerPaymentScoreTips() {
		String[] radioToolTips = {
				"Existing borrower: No missed payment since loan origination.",
				"Existing borrower: 1 missed payment in the past 24 months.",
				"New borrower: No payment history OR Existing borrower: 2 missed payments \nin the past 24 months",
				"Existing borrower: 3 missed payments in the past 24 months.",
				"Existing borrower: more than 3 missed payments in the past 24 months. \nDefaulted on the loan."
		};
		return radioToolTips;
	}
}
