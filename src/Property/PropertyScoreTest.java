package Property;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import InputInformation.RankingSystem;

class PropertyScoreTest {
	private PropertyScore p; //hold input information

	@BeforeEach
	/**
	 * Create property scoring object
	 * @throws Exception
	 */
	void setUp() throws Exception { //run before each test
		RankingSystem ltvScore = RankingSystem.AboveAverage;
		RankingSystem DSCR = RankingSystem.Weak;
		RankingSystem vacancyRate = RankingSystem.Strong;
		RankingSystem locationPop = RankingSystem.Weak;
		RankingSystem locationServ = RankingSystem.AboveAverage;
		RankingSystem tenantQuality = RankingSystem.AboveAverage;
		RankingSystem propertyCond = RankingSystem.AboveAverage;
		RankingSystem envAssess = RankingSystem.Strong;
		p = new PropertyScore(ltvScore, DSCR, vacancyRate, locationPop, locationServ, tenantQuality, propertyCond, envAssess);
	}
	
	@AfterEach
	void tearDown() throws Exception { //throw an exception if one exists
	}

	@Test
	/**
	 * Run test to check if inputs give the correct score based on weights
	 */
	void testEncapsulatePropertyScoreCalc() {
		assertEquals(3.45, p.encapsulatePropertyScoreCalc(), 0.01);
	}

}
