package Property;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import Property.PropertyInfo;
import InputInformation.RankingSystem;

class PropertyInfoTest {

	@AfterEach
	void tearDown() throws Exception { //throw an exception if one exists
	}
	
	@Test
	/**
	 * test to check LTV rank based on input
	 */
	void testPropertyLTV() {
		double loanValue = 900000;
		double appraisalValue = 3000000;
		assertEquals(RankingSystem.Strong, PropertyInfo.PropertyLTV(loanValue, appraisalValue));
	}

	@Test
	/**
	 * test to check vacancy rate rank based on input
	 */
	void testVacancyRate() {
		double privateRate = 5.2;
		double marketRate = 3.1;
		assertEquals(RankingSystem.Average, PropertyInfo.VacancyRate(privateRate, marketRate));
	}

	@Test
	/**
	 * test to check population score rank based on input
	 */
	void testPopulationScore() {
		assertEquals(RankingSystem.BelowAverage, PropertyInfo.PopulationScore(1000000));
	}

	@Test
	/**
	 * test to check DSCR Rank based on input
	 */
	void testDSCRRank() {
		assertEquals(RankingSystem.AboveAverage, PropertyInfo.DSCRRank(1.5));
	}

}
