import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import InputInformation.BorrowerType;
import InputInformation.CMSInputs;
import InputInformation.LoanCovenant;
import InputInformation.PropertyAddress;
import InputInformation.PropertyType;
import InputInformation.RankingSystem;

/**
 * Calculator JUnit test for calculator
 * @author tarek
 *
 */
class InterestRateCalculatorTest {
	private InterestRateCalculator t;  //hold interest rate calculator class
	private CMSInputs c;  //hold input class
	
	@BeforeEach
	/**
	 * Create input object and calculator object
	 * @throws Exception
	 */
	void setUp() throws Exception { //run before each test
		PropertyAddress myProperty = new PropertyAddress("3033 Wilshire Blvd", "Los Angeles","CA", 90010);  //instantiate address
		this.c = new CMSInputs(myProperty,
				BorrowerType.Corporate,
				PropertyType.MultiFamily,
				LoanCovenant.CorporateGuarantee,
				3.0, 3.7,7000000.00,7000000.00,710,1.15,66.10, 10.41,10000000.00,
				RankingSystem.AboveAverage,RankingSystem.Strong,
				RankingSystem.Strong,20,RankingSystem.Average,RankingSystem.AboveAverage); //instantiate inputs
	
		this.t = new InterestRateCalculator(c); //create calculator class based on inputs
	}

	@AfterEach
	void tearDown() throws Exception { //throw an exception if one exists
	}

	@Test
	/**
	 * test to check property score
	 */
	void testCalculatePropertyScore() {
		t.CalculatePropertyScore(); //get property score
		assertEquals(3.9, t.getPropertyScore(), 0.01);  //compare
	}

	@Test
	/**
	 * test to check individual score
	 */
	void testCalculateIndividualBorrowerScore() {
		t.CalculateIndividualBorrowerScore(); //get individual score
		assertEquals(3.3, t.getBorrowerScore(), 0.01); //compare
	}

	@Test
	/**
	 * test to check corporate score
	 */
	void testCalculateCorporateBorrowerScore() {
		t.CalculateCorporateBorrowerScore(); //get corporate score
		assertEquals(3.4, t.getBorrowerScore(), 0.01); //compare
	}

	@Test
	/**
	 * test to check covenant score
	 */
	void testCalculateCovenantScore() {
		t.CalculateCovenantScore(); //get covenant score
		assertEquals(0.5, t.getCovenantScore(), 0.01); //compare
	}

	@Test
	/**
	 * test to check overall score
	 */
	void testCalculateOverallScore() {
		t.CalculateOverallScore(); //get overall score
		assertEquals(InterestRateCalculator.NextSteps.Review, t.getNextStep()); //compare  
	}

}
