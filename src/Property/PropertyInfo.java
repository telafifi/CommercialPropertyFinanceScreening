package Property;
import InputInformation.*;

/**
 * Class that gets rank values for porperty scoring
 * @author tarek
 *
 */
public class PropertyInfo {
	
	/**
	 * Calculate LTV ratio and return ranking system
	 * @param loanValue value of loan providing
	 * @param appraisalValue appraisal value of property
	 * @return
	 */
	public static RankingSystem PropertyLTV(double loanValue, double appraisalValue) {
		double LTV = loanValue / appraisalValue * 100; //LTV calc as a percentage
		if (LTV <= 60) { //return strong rank if LTV less than 60
			return RankingSystem.Strong;
		}
		else if (LTV <= 65) { //return above average rank if LTV less than 65
			return RankingSystem.AboveAverage;
		}
		else if (LTV <= 70) { //return average rank if LTV less than 70
			return RankingSystem.Average;
		}
		else if (LTV < 75) { //return below average rank if LTV less than 75
			return RankingSystem.BelowAverage;
		}
		return RankingSystem.Weak; //else return weak rank
	}
	
	/**
	 * Calculate the difference between the property vacancy rate and
	 * the average market rate
	 * @param privateRate vacancy rate of property (from appraisal)
	 * @param marketRate vacancy rate of the market
	 * @return
	 */
	public static RankingSystem VacancyRate(double privateRate, double marketRate) {
		if (privateRate == 0) { //return a strong rank if the rate is zero
			return RankingSystem.Strong;
		}
		double difference = privateRate - marketRate; //difference between rates
		if (difference < 1) { //return strong rank if difference is less than 1%
			return RankingSystem.Strong;
		}
		else if (difference < 2) { //return above average rank if difference is less than 2%
			return RankingSystem.AboveAverage;
		}
		else if (difference < 3) { //return average rank if difference is less than 3%
			return RankingSystem.Average;
		}
		else if (difference < 4) { //return below average rank if difference is less than 4%
			return RankingSystem.BelowAverage;
		}
		return RankingSystem.Weak; //else return weak rank
	}
	
	/**
	 * Return a ranking system based on the size of the population
	 * @param population size of the population within the county
	 * @return
	 */
	public static RankingSystem PopulationScore(int population) {
		if (population > 2000000) { //return strong rank if population greater than 2 million
			return RankingSystem.Strong;
		}
		else if (population > 1500000) { //return above average rank if population greater than 1.5 million 
			return RankingSystem.AboveAverage;
		}
		else if (population > 1000000) { //return average rank if population greater than 1 million
			return RankingSystem.Average;
		}
		else if (population > 500000) { //return below average rank if population greater than half million
			return RankingSystem.BelowAverage;
		}
		return RankingSystem.Weak; //else return weak rank
	}
	
	/**
	 * Return a rank based on DSCR input
	 * @param DSCR
	 * @return
	 */
	public static RankingSystem DSCRRank(double DSCR) {
		if (DSCR > 1.5) { //return strong rank if DSCR greater than 1.5
			return RankingSystem.Strong;
		}
		else if (DSCR > 1.4) { //return above average rank if DSCR greater than 1.4
			return RankingSystem.AboveAverage;
		}
		else if (DSCR > 1.3) { //return average rank if DSCR greater than 1.3
			return RankingSystem.Average;
		}
		else if (DSCR > 1.2) { //return below average rank if DSCR greater than 1.2
			return RankingSystem.BelowAverage;
		}
		return RankingSystem.Weak; //else return weak rank
	}
}
