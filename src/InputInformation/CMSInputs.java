package InputInformation;

/**
 * class that holds all the input information input by the User
 * @author tarek
 *
 */
public class CMSInputs {
	private PropertyAddress address; //property address
	private BorrowerType borrower; //borrower type
	private PropertyType property; //property type
	private LoanCovenant covenant; //loan covenant type
	private double vacancyRate; //vacancy rate of property
	private double marketRate; //vacancy rate of similar property in market
	private double loanAmount; //loan amount
	private double netWorth; //borrower's net worth
	private int ficoScore; //Boroower's fico score
	private double DSCRRatio; //DSCR ratio
	private double DERatio; //Debt to equity ratio
	private double currentRatio; //current ratio
	private double appraisedValue; //appraised value of property
	private RankingSystem locationScore; //location score of property
	private RankingSystem tenantQuality; //tenant quality of property
	private RankingSystem environmentalScore; //environmental site assessment score of property
	private int borrowerExperience; //number of years of borrower experience
	private RankingSystem paymentHistory; //payment history of borrower
	private RankingSystem propertyAge; //property age
	
	/**
	 * Get inputs from GUI for use in tool and store them in object
	 * @param address Property address
	 * @param borrower Borrower Type
	 * @param property Property Type
	 * @param covenant Loan Covenant Type
	 * @param vacancyRate Vacancy Rate of property
	 * @param marketRate vacancy rate of similar property in market
	 * @param loanAmount loan amount
	 * @param netWorth Borrower's net worth
	 * @param ficoScore borrower's FICO score
	 * @param DERatio DSCR Ratio
	 * @param appraisedValue appraised value of property
	 * @param locationScore location score of property
	 * @param tenantQuality tenant quality of property
	 * @param environmentalScore environmental site assessment score of property
	 * @param borrowerExperience number of years of borrowe experience
	 * @param paymentHistory payment history of borrower
	 * @param propertyAge property age rank
	 */
	public CMSInputs (PropertyAddress address, BorrowerType borrower, PropertyType property, LoanCovenant covenant, double vacancyRate, double marketRate, double loanAmount, 
			double netWorth, int ficoScore, double DSCRRatio, double DERatio, double currentRatio, double appraisedValue, RankingSystem locationScore, RankingSystem tenantQuality, 
			RankingSystem environmentalScore, int borrowerExperience, RankingSystem paymentHistory, RankingSystem propertyAge) {
		this.address = address;
		this.borrower = borrower;
		this.property = property;
		this.covenant = covenant;
		this.vacancyRate = vacancyRate;
		this.marketRate = marketRate;
		this.loanAmount = loanAmount;
		this.netWorth = netWorth;
		this.ficoScore = ficoScore;
		this.DSCRRatio = DSCRRatio;
		this.DERatio = DERatio;
		this.currentRatio = currentRatio;
		this.appraisedValue = appraisedValue;
		this.locationScore = locationScore;
		this.tenantQuality = tenantQuality;
		this.environmentalScore = environmentalScore;
		this.borrowerExperience = borrowerExperience;
		this.paymentHistory = paymentHistory;
		this.propertyAge = propertyAge;
	}

	//getters for use within tool functions
	public PropertyAddress getAddress() {
		return address;
	}

	public BorrowerType getBorrower() {
		return borrower;
	}

	public PropertyType getProperty() {
		return property;
	}

	public LoanCovenant getCovenant() {
		return covenant;
	}
	
	public double getVacancyRate() {
		return vacancyRate;
	}
	
	public double getMarketRate() {
		return marketRate;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public double getNetWorth() {
		return netWorth;
	}

	public int getFicoScore() {
		return ficoScore;
	}

	public RankingSystem getLocationScore() {
		return locationScore;
	}

	public RankingSystem getTenantQuality() {
		return tenantQuality;
	}

	public RankingSystem getEnvironmentalScore() {
		return environmentalScore;
	}

	public int getBorrowerExperience() {
		return borrowerExperience;
	}

	public RankingSystem getPaymentHistory() {
		return paymentHistory;
	}
	
	public RankingSystem getPropertyAge() {
		return propertyAge;
	}
	
	public double getDSCRRatio() {
		return DSCRRatio;
	}
	
	public double getCurrentRatio() {
		return currentRatio;
	}

	public double getAppraisedValue() {
		return appraisedValue;
	}
	
	public double getDERatio() {
		return DERatio;
	}


	
}
