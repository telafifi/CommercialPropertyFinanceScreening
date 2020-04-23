package Borrower;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import InputInformation.RankingSystem;

class CorporateBorrowerScoringTest {
	
	private CorporateBorrowerScoring corp;
	
	@BeforeEach
	/**
	 * Create the Corporate Borrower Scoring object
	 * @throws Exception
	 */
	void setUp() throws Exception { //run before each test
		RankingSystem currentRatio = RankingSystem.Weak;
		RankingSystem DERatio = RankingSystem.Weak;
		RankingSystem experience = RankingSystem.Strong;
		RankingSystem payHistory = RankingSystem.Strong;
		corp = new CorporateBorrowerScoring(currentRatio, DERatio, experience, payHistory);
	}
	
	@AfterEach
	void tearDown() throws Exception { //throw an exception if one exists
	}
	
	@Test
	/**
	 * Run test to check if inputs give the correct score based on weights
	 */
	void testEncapsulateCorporateScoreCalc() {
		assertEquals(3.00, corp.encapsulateCorporateScoreCalc(), 0.01);
	}

}
