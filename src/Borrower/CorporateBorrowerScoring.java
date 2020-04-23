package Borrower;

import InputInformation.*;

/**
 * Class that calculates the corporate borrower's score
 * @author tarek
 *
 */
public class CorporateBorrowerScoring {
	private RankingSystem currentRatio; // CB1 = 25% weight
	private RankingSystem dERatio; // CB2 = 25% weight
	private RankingSystem borrowerExp; // CB3 = 40% weight
	private RankingSystem paymentHistory; // CB4 = 10% weight
	private double borrowerScore; //final borrower score
	private int[] borrowerRanks = new int[4]; //rank array that holds the chosen or calculated values
	private double[] borrowerWeights = {0.25, 0.25, 0.4, 0.1}; //weight array of each rank
	

	/**
	 * Calculate the corporate borrower's score based on input data
	 * @param currentRatio
	 * @param dERatio
	 * @param borrowerExp
	 * @param paymentHistory
	 */
	public CorporateBorrowerScoring(RankingSystem currentRatio, RankingSystem dERatio, RankingSystem borrowerExp,
			RankingSystem paymentHistory) {
		this.currentRatio = currentRatio;
		this.dERatio = dERatio;
		this.borrowerExp = borrowerExp;
		this.paymentHistory = paymentHistory;
		this.borrowerScore = this.GetCorporateBorrowerScore(); //get score based on weighted ranks
	}

	/**
	 * Calculate the corporate borrower score using weighted ranks
	 * @return Borrower score
	 */
	private double GetCorporateBorrowerScore() {
		this.borrowerRanks[0] = ReadRankingSystem.GetRankNumber(this.currentRatio);
		this.borrowerRanks[1] = ReadRankingSystem.GetRankNumber(this.dERatio);
		this.borrowerRanks[2] = ReadRankingSystem.GetRankNumber(this.borrowerExp);
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
	public double encapsulateCorporateScoreCalc() {
		return this.GetCorporateBorrowerScore();
	}

	// Getters
	public RankingSystem getCurrentRatio() {
		return currentRatio;
	}

	public RankingSystem getdERatio() {
		return dERatio;
	}

	public RankingSystem getBorrowerExp() {
		return borrowerExp;
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
