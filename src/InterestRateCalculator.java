import InputInformation.*;
import Property.*;
import Borrower.*;
import DataLoader.CapRateReader;
import DataLoader.PopulationReader;
import DataLoader.TreasuryYieldReader;

import java.util.*;

/**
 * Class to run through calculations and generate report
 * @author tarek
 *
 */
public class InterestRateCalculator {
	private CMSInputs myInput; //Object that holds all input information
	private double propertyScore; //double holding the property score
	private double borrowerScore; //double holding the borrower score
	private double covenantScore; //double holding the covenant score
	private double overallScore; //double holding the overall score
	private NextSteps nextStep; //state next step to use
	private double spread; //double holding spread
	private double interestRate; //double holding the issued interest rate
	private double treasuryBond; //double holding the treasury bond
	private int[] propertyRanks; //array of ranks for property scores
	private double[] propertyWeights; //array of weights for property scores
	private int[] borrowerRanks; //array of ranks for borrower scores
	private double[] borrowerWeights; //array of weights for borrower scores
	private static Map<String, Integer> populationMap = PopulationReader.readPopulation();//reading population data
	private static USStates us = new USStates();// creating an object for USStates class
	private static HashMap<String, String> states = us.getStates();// HashMap of states and their abbreviations
	private static Map<String,Double> capRateMap = CapRateReader.readCapRate();// reading cap rates data
	private static Map<String, Double> treasuryYieldMap = TreasuryYieldReader.readTreasuryYield();//reading treasury yields data
	
	/**
	 * Constructor that takes in input information and runs calculations
	 * @param myInput Input information
	 */
	public InterestRateCalculator(CMSInputs myInput) {
		this.myInput = myInput; //Store input information
		this.CalculatePropertyScore(); //Calculate property information
		if (this.myInput.getBorrower() == BorrowerType.Individual) { //if individual borrower, calculate their score
			this.CalculateIndividualBorrowerScore();
		}
		else {
			this.CalculateCorporateBorrowerScore(); //otherwise calculate the corporate borrower score
		}
		this.CalculateCovenantScore(); //calculate covenant score
		this.CalculateOverallScore(); //calculate overall score
		this.CalculateInterestRate(); //calculate issued interest rate
	}
	
	/**
	 * Use input information and collected data to calculate the property score
	 * Made public to allow for JUnit Test
	 */
	public void CalculatePropertyScore() {
		int population = this.ReadPopulation();
		RankingSystem ltvScore = PropertyInfo.PropertyLTV(this.myInput.getLoanAmount(), this.myInput.getAppraisedValue()); //get loan to value rank
		RankingSystem vacancy = PropertyInfo.VacancyRate(this.myInput.getVacancyRate(), this.myInput.getMarketRate()); //get vacancy rank
		RankingSystem popRank = PropertyInfo.PopulationScore(population); //get population rank
		RankingSystem dscrRank = PropertyInfo.DSCRRank(this.myInput.getDSCRRatio()); //get DSCR ratio rank
		PropertyScore myPropertyScore = new PropertyScore(ltvScore, dscrRank, vacancy, popRank,  
				this.myInput.getLocationScore(), this.myInput.getTenantQuality(), this.myInput.getPropertyAge(), 
				this.myInput.getEnvironmentalScore()); //Instantiate property score class
		this.propertyScore =  myPropertyScore.getPropertyScore(); //Get Property Score
		this.propertyRanks = myPropertyScore.getPropertyRanks(); //get rank array
		this.propertyWeights = myPropertyScore.getPropertyWeights(); //get weight array
	}
	
	/**
	 * Take in input data and retrieve population value
	 * @return
	 */
	private int ReadPopulation() {
		// reading population data from Population.csv file
		String city = this.myInput.getAddress().City.toLowerCase();//get city
		String state = states.get(this.myInput.getAddress().State).toLowerCase();// converting the name of state to its abbreviation
		int population = 1;//set the number of population as 1 if can not find the matching population in the data file
		if (populationMap.get(city + "," + state) != null) {
			//get the value of population from PopulationMap
			population = populationMap.get(city + "," + state);
		}
		return population;
	}
	
	/**
	 * Use input information and collected data to calculate the individual borrower's score
	 * Made public to allow for JUnit Test
	 */
	public void CalculateIndividualBorrowerScore() {
		RankingSystem fico = BorrowerInfo.FICORank(this.myInput.getFicoScore()); //get FICO score rank
		RankingSystem experience = BorrowerInfo.ExperienceRank(this.myInput.getBorrowerExperience()); //get Experience Rank
		RankingSystem netWorth = BorrowerInfo.NetWorthRank(this.myInput.getNetWorth(), this.myInput.getLoanAmount()); //get Net Worth Rank
		IndividualBorrowerScoring myBorrower = new IndividualBorrowerScoring(netWorth, experience, fico, this.myInput.getPaymentHistory());
		this.borrowerScore = myBorrower.getBorrowerScore(); //instantiate individual borrower score and return double
		this.borrowerRanks = myBorrower.getBorrowerRanks(); //get rank array
		this.borrowerWeights = myBorrower.getBorrowerWeights(); //get weight array
	}
	
	/**
	 * Use input information and collected data to calculate the corporate borrower's score
	 * Made public to allow for JUnit test
	 */
	public void CalculateCorporateBorrowerScore() { 
		RankingSystem currentRatioRank = BorrowerInfo.CurrentRatioRank(this.myInput.getCurrentRatio()); //get current ratio rank
		RankingSystem dERank = BorrowerInfo.DERatioRank(this.myInput.getDERatio()); //get DE ratio rank
		RankingSystem experience = BorrowerInfo.ExperienceRank(this.myInput.getBorrowerExperience()); //get experience rank
		CorporateBorrowerScoring myBorrower = new CorporateBorrowerScoring(currentRatioRank, dERank, experience, this.myInput.getPaymentHistory());
		this.borrowerScore = myBorrower.getBorrowerScore(); //instantiate corporate borrower score and return double
		this.borrowerRanks = myBorrower.getBorrowerRanks(); //get rank array
		this.borrowerWeights = myBorrower.getBorrowerWeights(); //get weight array
	}
	
	/**
	 * Use input to calculate the covenant score
	 * Made public to allow for JUnit test
	 */
	public void CalculateCovenantScore() {
		if (this.myInput.getCovenant() == LoanCovenant.FederalFullGuarantee) { //set to 1 if federal guarantee
			this.covenantScore = 1.0;
		}
		else if (this.myInput.getCovenant() == LoanCovenant.CorporateGuarantee) { //set to 0.5 if corporate guarantee
			this.covenantScore = 0.5;
		}
		else {
			this.covenantScore = 0.0; //set to 0 otherwise
		}
	}
	
	/**
	 * Calculate overall score and get next step
	 * Made public to allow for JUnit test
	 */
	public void CalculateOverallScore() {
		this.overallScore = 0.75 * this.propertyScore + 0.25 * this.borrowerScore + this.covenantScore; //calculate weighted overall score
		if (this.overallScore > 3.2) { //return a next step of review if overall score is over 3.2
			this.nextStep = NextSteps.Review;
		}
		else { //otherwise reject the application
			this.nextStep = NextSteps.Rejected;
		}
	}
	
	/**
	 * Calculate interest rate and spread
	 */
	public void CalculateInterestRate() {
		this.GetTreasuryYield();
		//this.treasuryBond = 0.43;
		double capRate = this.GetCapRate();
		if (this.nextStep == NextSteps.Review) { //if application is under review
			if (this.overallScore < 3.6) { //spread calculation if overall score is greater than 3.6
				//this.spread = treasuryBond + capRate;
				this.spread = capRate;
			}
			else { //calculation if overall score is less than 3.6
				this.spread = capRate - 0.25;
			}
			this.interestRate = treasuryBond + this.spread; //total interest rate calculation
		}
	}
	
	/**
	 * Take in input information and return cap rate from data readers
	 * @return
	 */
	private double GetCapRate() {
		// reading cap rate data from CapRatesFinal.csv
		String city = this.myInput.getAddress().City.toLowerCase();//get city and convert to lowerCase
		String state = this.myInput.getAddress().State.toLowerCase();//get state and convert to lowerCase
		String propertyType = this.myInput.getProperty().toString().toLowerCase();//get property type
		// get the value of cap rate from capRateMap
		double capRate = 0;
		if (capRateMap.get(propertyType + "," + state + "," + city) != null) { //if city exists with an associated value
			capRate = capRateMap.get(propertyType + "," + state + "," + city); //get value of city
		} else { //else
			capRate = capRateMap.get(propertyType + "," + state + ","); //get value of state
		}
		return capRate;
	}
	
	/**
	 * Take in date the tool was run and get the reasury bond value from data reader
	 */
	private void GetTreasuryYield() {
		// reading treasury yields data from TreasuryYiled.csv file
		
		Calendar calendar = Calendar.getInstance();//get current date
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = 2019;
		//calendar.add(Calendar.YEAR, -1);// set the year to 2019
        String calendarKey = year + "," + month + "," + day;// set the key of hashmap
        
		if (treasuryYieldMap.get(calendarKey) != null) {
			this.treasuryBond = treasuryYieldMap.get(calendarKey);// get the value of yield from the hashmap
		} else {  
			if (day <= 15) { //if the day does not exist and the date is less than 15 then keep adding until one is found
				while (treasuryYieldMap.get(calendarKey) == null) {
					day++;
					calendarKey = year + "," + month + "," + day;// set the key of hashmap
				}
			}
			else { //otherwise subtract
				while (treasuryYieldMap.get(calendarKey) == null) {
					day--;
					calendarKey = year + "," + month + "," + day;// set the key of hashmap
				}
			}
			this.treasuryBond = treasuryYieldMap.get(calendarKey);
		}
	}
	
	/**
	 * Return a report - currently an error message box...will format to convert to actual report later
	 * @return
	 */
	public String GetFinalReport() {
		ArrayList<String> outputList = new ArrayList<String>(); //hold output lines
		outputList.add(" Overall Score = " + this.overallScore); //first statement
		if (this.nextStep == NextSteps.Review) { //if application is under review then add the following lines
			outputList.add("Next Steps: Credit Manager Review"); 
			outputList.add("Suggested interest rate = " + this.interestRate);
			outputList.add("5Y US Treasury Bond = " + this.treasuryBond);
			outputList.add("Spread = " + this.spread);
		}
		else {
			outputList.add("Next Steps: Initial Application Rejected!"); //otherwise state that application is rejected
		}
		String output = "";
		for (String line : outputList) {
			output += line + "\n"; //output lines in array list as string to place in error message box
		}
		return output;
	}
	
	/**
	 * Next step options to see whether or not to accept or reject application
	 * @author tarek
	 *
	 */
	public enum NextSteps{
		Review,
		Rejected
	}
	
	//Getters
	public CMSInputs getMyInput() {
		return myInput;
	}

	public double getPropertyScore() {
		return propertyScore;
	}

	public double getBorrowerScore() {
		return borrowerScore;
	}

	public double getCovenantScore() {
		return covenantScore;
	}

	public double getOverallScore() {
		return overallScore;
	}

	public NextSteps getNextStep() {
		return nextStep;
	}

	public double getSpread() {
		return spread;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public double getTreasuryBond() {
		return treasuryBond;
	}

	public static Map<String, Integer> getPopulationMap() {
		return populationMap;
	}

	public static USStates getUs() {
		return us;
	}

	public static HashMap<String, String> getStates() {
		return states;
	}

	public static Map<String, Double> getCapRateMap() {
		return capRateMap;
	}

	public static Map<String, Double> getTreasuryYieldMap() {
		return treasuryYieldMap;
	}
	
	public int[] getPropertyRanks() {
		return propertyRanks;
	}

	public double[] getPropertyWeights() {
		return propertyWeights;
	}

	public int[] getBorrowerRanks() {
		return borrowerRanks;
	}

	public double[] getBorrowerWeights() {
		return borrowerWeights;
	}

}
