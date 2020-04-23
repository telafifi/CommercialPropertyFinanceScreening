package Borrower;

import InputInformation.*;

/**
 * Class that holds ranks for the borrowers
 * @author tarek
 *
 */
public class BorrowerInfo {

	/**
	 * Get the rank associated with FICO score
	 * @param FICO Individual's FICO score
	 * @return ranking system
	 */
	public static RankingSystem FICORank(int FICO) {
		if (FICO > 750) {
			return RankingSystem.Strong; //strong if fico score is over 750
		} else if (FICO >= 700) {
			return RankingSystem.AboveAverage; //above average if score is above 700
		} else if (FICO >= 650) {
			return RankingSystem.Average; //average if score is above 650
		} else if (FICO > 600) {
			return RankingSystem.BelowAverage; //below average if score is above 600
		}
		return RankingSystem.Weak; //else is weak
	}

	/**
	 * Get the rank associated with the borrower's experience
	 * @param experience number of years of experience of the borrower
	 * @return ranking system
	 */
	public static RankingSystem ExperienceRank(int experience) {
		if (experience > 20) {
			return RankingSystem.Strong; //strong if more than 20 years experience
		} else if (experience > 15) {
			return RankingSystem.AboveAverage; //above average if over 15 years
		} else if (experience > 10) {
			return RankingSystem.Average; //average if over 10 years
		} else if (experience > 5) {
			return RankingSystem.BelowAverage; //below average if over 5 years
		}
		return RankingSystem.Weak; //else is weak
	}
	
	/**
	 * Get the rank associated with the net worth of the borrower and the loan amount
	 * @param netWorth Borrower net worth
	 * @param loanAmount Loan amount requested
	 * @return ranking system
	 */
	public static RankingSystem NetWorthRank(double netWorth, double loanAmount) {
		double ratio = netWorth / loanAmount; //calculate the net worth ratio
		if (ratio > 2) {
			return RankingSystem.Strong; //strong if over 2
		}
		else if (ratio > 1.5) {
			return RankingSystem.AboveAverage; //above average if over 1.5
		}
		else if (ratio > 1) {
			return RankingSystem.Average; //average if over 1
		}
		else if (ratio > 0.5) {
			return RankingSystem.BelowAverage; //below average if over 0.5
		}
		return RankingSystem.Weak; //else weak
	}
	
	/**
	 * Get the rank associated with the current ratio (current assets / current liabilities)
	 * @param currentRatio current assets / current liabilities
	 * @return ranking system
	 */
	public static RankingSystem CurrentRatioRank(double currentRatio) { 
		if (currentRatio > 2.5) {
			return RankingSystem.Strong; //strong if over 2.5
		}
		else if (currentRatio > 2) {
			return RankingSystem.AboveAverage; //above average if over 2
		}
		else if (currentRatio > 1.5) {
			return RankingSystem.Average; //average if over 1.5
		}
		else if (currentRatio > 1) {
			return RankingSystem.BelowAverage; //below average if over 1
		}
		return RankingSystem.Weak; //else is weak
	}
	
	/**
	 * Get the rank associate with the debt to equity ratio
	 * @param DE Debt to equity ratio
	 * @return ranking system
	 */
	public static RankingSystem DERatioRank(double DE) {
		if (DE < 1) {
			return RankingSystem.Strong; //strong if below 1
		}
		else if (DE <= 1.25) {
			return RankingSystem.AboveAverage; //above average if below 1.25
		}
		else if (DE <= 1.5) {
			return RankingSystem.Average; //average if below 1.5
		}
		else if (DE <= 1.75) {
			return RankingSystem.BelowAverage; //below average if below 1.75
		}
		return RankingSystem.Weak; //else is weak
	}

}
