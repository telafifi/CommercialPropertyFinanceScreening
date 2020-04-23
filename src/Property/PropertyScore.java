package Property;
import InputInformation.*;

/**
 * Class that calculates the property score
 * @author tarek
 *
 */
public class PropertyScore {
	private RankingSystem ltvScore; //P1 = 10% weight
	private RankingSystem dscr; //P2 == 15% weight
	private RankingSystem vacancy; //P3 = 15% weight
	private RankingSystem locationPop; //P4.1 = 10% weight
	private RankingSystem locationServ; //P4.2 = 15% weight
	private RankingSystem tenantQuality; //P5 = 15% weight
	private RankingSystem propertyCondition; //P6 = 15% weight
	private RankingSystem environmentAssess; //P7 = 5% weight
	private int[] propertyRanks = new int[8]; //rank array that holds chosen or calculated ranks
	private double[] propertyWeights = {0.1, 0.15, 0.15, 0.1, 0.15, 0.15, 0.15, 0.05}; //weights of each rank
	private double propertyScore; //final score
	
	/**
	 * Calculate the property's score based on input data
	 * @param ltvScore
	 * @param dscr
	 * @param vacancy
	 * @param locationPop
	 * @param locationServ
	 * @param tenantQuality
	 * @param propertyCondition
	 * @param environmentAssess
	 */
	public PropertyScore(RankingSystem ltvScore, RankingSystem dscr, RankingSystem vacancy, RankingSystem locationPop, RankingSystem locationServ,
			RankingSystem tenantQuality, RankingSystem propertyCondition, RankingSystem environmentAssess) {
		this.ltvScore = ltvScore;
		this.dscr = dscr;
		this.vacancy = vacancy;
		this.locationPop = locationPop;
		this.locationServ = locationServ;
		this.tenantQuality = tenantQuality;
		this.propertyCondition = propertyCondition;
		this.environmentAssess = environmentAssess;
		this.propertyScore = this.CalculatePropertyScore(); //calculate the score based on ranks
	}
	
	/**
	 * Calculate the property score based on rank inputs and weights
	 * @return
	 */
	private double CalculatePropertyScore() {
		//Store property rank values in array
		this.propertyRanks[0] = ReadRankingSystem.GetRankNumber(this.ltvScore);
		this.propertyRanks[1] = ReadRankingSystem.GetRankNumber(this.dscr);
		this.propertyRanks[2] = ReadRankingSystem.GetRankNumber(this.vacancy);
		this.propertyRanks[3] = ReadRankingSystem.GetRankNumber(this.locationPop);
		this.propertyRanks[4] = ReadRankingSystem.GetRankNumber(this.locationServ);
		this.propertyRanks[5] = ReadRankingSystem.GetRankNumber(this.tenantQuality);
		this.propertyRanks[6] = ReadRankingSystem.GetRankNumber(this.propertyCondition);
		this.propertyRanks[7] = ReadRankingSystem.GetRankNumber(this.environmentAssess);
		double score = 0;
		for (int i = 0; i < this.propertyRanks.length; i++) {
			score += (double)this.propertyRanks[i] * this.propertyWeights[i]; //get the weighted score of the property
		}
		return score;
	}
	
	/**
	 * Public method to encapsulate private method for JUnit tests
	 * @return
	 */
	public double encapsulatePropertyScoreCalc() {
		return this.CalculatePropertyScore();
	}
	
	//Getters
	public RankingSystem getLtvScore() {
		return ltvScore;
	}

	public RankingSystem getDscr() {
		return dscr;
	}

	public RankingSystem getVacancy() {
		return vacancy;
	}

	public RankingSystem getLocationPop() {
		return locationPop;
	}

	public RankingSystem getLocationServ() {
		return locationServ;
	}

	public RankingSystem getTenantQuality() {
		return tenantQuality;
	}

	public RankingSystem getPropertyCondition() {
		return propertyCondition;
	}

	public RankingSystem getEnvironmentAssess() {
		return environmentAssess;
	}

	public double getPropertyScore() {
		return propertyScore;
	}

	public int[] getPropertyRanks() {
		return propertyRanks;
	}

	public double[] getPropertyWeights() {
		return propertyWeights;
	}

}
