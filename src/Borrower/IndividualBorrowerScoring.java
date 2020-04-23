package Borrower;

import InputInformation.*;

/**
 * Class that calculates the individual borrower's score
 * @author tarek
 *
 */
public class IndividualBorrowerScoring {
	private RankingSystem netWorthRatio; // IB1 = 30% weight
	private RankingSystem borrowerExperience; // IB2 = 40% weight
	private RankingSystem ficoScore; // IB3 = 20% Weight
	private RankingSystem paymentHistory; // IB4 = 10% weight
	private double borrowerScore; //final borrower score
	private int[] borrowerRanks = new int[4]; //rank array that holds the chosen or calculated values
	private double[] borrowerWeights = {0.3, 0.4, 0.2, 0.1}; //weight array of each rank
	

	/**
	 * Calculate the individual borrower's score based on input data
	 * @param netWorthRatio
	 * @param borrowerExperience
	 * @param ficoScore
	 * @param paymentHistory
	 */
	public IndividualBorrowerScoring(RankingSystem netWorthRatio, RankingSystem borrowerExperience,
			RankingSystem ficoScore, RankingSystem paymentHistory) {
		this.netWorthRatio = netWorthRatio;
		this.borrowerExperience = borrowerExperience;
		this.ficoScore = ficoScore;
		this.paymentHistory = paymentHistory;
		this.borrowerScore = this.GetIndividualBorrowerScore(); //get score based on weighted ranks
	}

	/**
	 * Calculate the individual borrower score using weighted ranks
	 * 
	 * @return
	 */
	private double GetIndividualBorrowerScore() {
		this.borrowerRanks[0] = ReadRankingSystem.GetRankNumber(this.netWorthRatio);
		this.borrowerRanks[1] = ReadRankingSystem.GetRankNumber(this.borrowerExperience);
		this.borrowerRanks[2] = ReadRankingSystem.GetRankNumber(this.ficoScore);
		this.borrowerRanks[3] = ReadRankingSystem.GetRankNumber(this.paymentHistory);
		double score = 0;
		for (int i = 0; i < this.borrowerRanks.length; i++) {
			score += (double)this.borrowerRanks[i] * this.borrowerWeights[i]; //get the weighted score of the individual borrower
		}
		return score;
	}
	
	/**
	 * Public method to encapsulate private method for JUnit tests
	 * @return
	 */
	public double encapsulateIndividualScoreCalc() {
		return this.GetIndividualBorrowerScore();
	}

	// Getters
	public RankingSystem getNetWorthRatio() {
		return netWorthRatio;
	}

	public RankingSystem getBorrowerExperience() {
		return borrowerExperience;
	}

	public RankingSystem getFicoScore() {
		return ficoScore;
	}

	public RankingSystem getPaymentHistory() {
		return paymentHistory;
	}

	public double getBorrowerScore() {
		return borrowerScore;
	}
	
	public int[] getBorrowerRanks() {
		return borrowerRanks;
	}

	public double[] getBorrowerWeights() {
		return borrowerWeights;
	}


}
