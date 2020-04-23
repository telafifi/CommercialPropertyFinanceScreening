package Borrower;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import InputInformation.RankingSystem;

class BorrowerInfoTest {
	
	@AfterEach
	void tearDown() throws Exception { //throw an exception if one exists
	}
	
	@Test
	/**
	 * Test to check FICO rank based on input
	 */
	void testFICORank() {
		assertEquals(RankingSystem.Average, BorrowerInfo.FICORank(650));  //Test Different FICO score ranks
	}

	@Test
	/**
	 * test to check experience rank based on input
	 */
	void testExperienceRank() {
		assertEquals(RankingSystem.Average, BorrowerInfo.ExperienceRank(15));  //Test Different Borrower Experience ranks
	}

	@Test
	/**
	 * test to check net worth rank based on input
	 */
	void testNetWorthRank() {
		double netWorth = 1000000; //borrower net worth
		double loanAmount = 2000000; //borrower loan amount
		assertEquals(RankingSystem.Weak, BorrowerInfo.NetWorthRank(netWorth, loanAmount));  //Test Different Net Worth ranks
	}

	@Test
	/**
	 * test to check current ratio rank based on input
	 */
	void testCurrentRatioRank() {
		assertEquals(RankingSystem.AboveAverage, BorrowerInfo.CurrentRatioRank(2.5));  //Test Different Current Ratio score ranks
	}

	@Test
	/**
	 * test to check DERatio rank based on input
	 */
	void testDERatioRank() {
		assertEquals(RankingSystem.BelowAverage, BorrowerInfo.DERatioRank(1.75));  //Test Different DERatio ranks
	}

}
