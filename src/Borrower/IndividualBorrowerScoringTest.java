package Borrower;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import InputInformation.RankingSystem;


class IndividualBorrowerScoringTest {
	private IndividualBorrowerScoring ind;
	
	@BeforeEach
	/**
	 * Create individual borrower scoring object
	 * @throws Exception
	 */
	void setUp() throws Exception { //run before each test
		RankingSystem netWorth = RankingSystem.Strong;
		RankingSystem experience = RankingSystem.Weak;
		RankingSystem FICO = RankingSystem.AboveAverage;
		RankingSystem payHistory = RankingSystem.Average;
		ind = new IndividualBorrowerScoring(netWorth, experience, FICO, payHistory);
	}
	
	@AfterEach
	void tearDown() throws Exception { //throw an exception if one exists
	}
	
	@Test
	/**
	 * Run test to check if inputs give the correct score based on weights
	 */
	void testEncapsulateIndividualScoreCalc() {
		assertEquals(3.00, ind.encapsulateIndividualScoreCalc(), 0.01);
	}

}
