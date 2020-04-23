import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.awt.Desktop;
import InputInformation.*;
/**
 * Class to write output to Excel
 * @author tarek
 *
 */
public class WriteToExcel {
	private CMSInputs myInputs; //Class that holds input information
	private InterestRateCalculator myResult; //Class that holds calculated results
	private XSSFWorkbook wb; //Excel workbook to manipulate
	private XSSFSheet sh; //Excel sheet to manipulate
	private FileInputStream fis; //input file stream to read template
	private FileOutputStream fos; //output file stream to produce final file
	private String outputFile = "CFS_Results.xlsx"; //name of results file
	private CellStyle myStyle; //Cell style for emphasizing rank
	private String OverallSh = "Overall Score"; //name of overall score sheet
	private String PropertySh = "Property"; //name of property rank sheet
	private String IndividualSh = "Individual Borrower"; //name of individual borrower sheet
	private String CorporateSh = "Corporate Borrower"; //name of corporate borrower sheet
	
	/**
	 * Constructor to build excel report
	 * @param myInputs 
	 * @param myResult
	 */
	public WriteToExcel(CMSInputs myInputs, InterestRateCalculator myResult) {
		this.myInputs = myInputs; //get inputs information
		this.myResult = myResult; //get result information
		this.BringReportTemplate(); //run report
	}
	
	/**
	 * Get report template file and create workbook element
	 */
	private void BringReportTemplate() {
		try {
			fis = new FileInputStream("ReportBase/ReportFramework.xlsx"); //get report template
			try {
				wb = XSSFWorkbookFactory.createWorkbook(fis); //create workbook using template
				this.myStyle = this.CreateCellStyle(); //create a cell style to emphasize the chosen rank
				this.FillOverallScoreSheet(); //Create overall score sheet
				this.FillPropertySheet(); //create property rank sheet
				this.FillBorrowerSheet(); //create borrower rank sheet
				this.CreateNewResultFile(); //Create output file
			} catch (IOException e) {
				System.out.println("Coud not find report template file. Please make sure that the ReportBase\\\\ReportFramework.xlsx exists!");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Coud not find report template file. Please make sure that the ReportBase\\\\ReportFramework.xlsx exists!");
		}
	}
	
	/**
	 * Create the results file using all input and calculated information and open it
	 */
	private void CreateNewResultFile() {
		try {
			fos = new FileOutputStream(outputFile); //write the file out
			wb.write(fos); //write the file
			fos.flush(); //flush
			fos.close(); //close stream
			fis.close(); //close input stream
			System.out.println("See Excel report for results!");
			File f = new File(outputFile); //create new file
			Desktop desktop = Desktop.getDesktop(); //find a desktop
			desktop.open(f); //open the file
		} catch (Exception e) {
			System.out.println("Could not write output file! Please make sure to close the output file from a previous run if you have it open!");
		}
	}
	
	/**
	 * Fill the overall score sheet
	 */
	private void FillOverallScoreSheet() {
		sh = wb.getSheet(this.OverallSh); //get the sheet to manipulate
		//Get values to fill sheet with
		String propertyType = this.myInputs.getProperty().toString(); //get property type
		double propertyScore = this.roundToTwoDecimals(this.myResult.getPropertyScore()); //get property score
		double weightedPropertyScore = this.roundToTwoDecimals(0.75 * propertyScore); //get weighted property score
		String borrowerType = this.setBorrowerInformation(this.myInputs.getBorrower()); //get borrower type
		double borrowerScore = this.roundToTwoDecimals(this.myResult.getBorrowerScore()); //get borrower score
		double weightedBorrowerScore = this.roundToTwoDecimals(0.25 * borrowerScore);  //get weighted borrower score
		String covenantType = this.getCovenantString(this.myInputs.getCovenant()); //get loan covenant
		double covenantScore = this.roundToTwoDecimals(this.myResult.getCovenantScore()); //get covenant score
		double overallScore = this.roundToTwoDecimals(this.myResult.getOverallScore()); //get overall score
		String nextStep = this.getNextSteps(this.myResult.getNextStep()); //get next step
		double interestRate = this.roundToTwoDecimals(this.myResult.getInterestRate()); //get interest rate
		double treasureBond = this.myResult.getTreasuryBond(); //get treasury bond
		double spread = this.myResult.getSpread(); //get spread
		
		//Set values in spreadsheet
		this.SetCellValue(4, 0, propertyType);
		this.SetCellValue(4, 2, propertyScore);
		this.SetCellValue(4, 3, weightedPropertyScore);
		
		this.SetCellValue(8, 0, borrowerType);
		this.SetCellValue(8, 2, borrowerScore);
		this.SetCellValue(8, 3, weightedBorrowerScore);
		
		this.SetCellValue(11, 0, covenantType);
		this.SetCellValue(11, 2, covenantScore);
		this.SetCellValue(11, 3, covenantScore);
		
		this.SetCellValue(13, 3, overallScore);
		this.SetCellValue(15, 2, nextStep);

		this.SetCellValue(18, 2, treasureBond + "%");
		if (this.myResult.getNextStep() == InterestRateCalculator.NextSteps.Review) {
			this.SetCellValue(17, 2, interestRate + "%");
			this.SetCellValue(19, 2, spread + "%");
		}
		else {
			this.SetCellValue(17, 2, "Pricing not applicable");
			this.SetCellValue(19, 2, nextStep);
		}
	}
	
	/**
	 * Fill Property Score sheet
	 */
	private void FillPropertySheet() {
		sh = wb.getSheet(this.PropertySh); //get sheet to manipulate
		double[] weights = this.myResult.getPropertyWeights(); //get the weights of each score
		int[] ranks = this.myResult.getPropertyRanks(); //get the ranks associated with each score
		this.PopulateRankSheets(weights, ranks, this.myResult.getPropertyScore()); //populate the sheet
	}
	
	private void FillBorrowerSheet() {
		sh = wb.getSheet(this.IndividualSh); //get sheet to manipulate
		if (this.myInputs.getBorrower() == BorrowerType.Corporate) { //if corporate borrower
			sh = wb.getSheet(this.CorporateSh); //get the corporate borrower sheet instead
		}
		double[] weights = this.myResult.getBorrowerWeights(); //get the weights of each score
		int[] ranks = this.myResult.getBorrowerRanks(); //get the ranks associated with each score
		this.PopulateRankSheets(weights, ranks, this.myResult.getBorrowerScore()); //populate the sheet
	}
	
	/**
	 * Populate the rank sheets
	 * @param weights array holding the weights of each rank
	 * @param ranks array holding the values of each rank
	 * @param score final score
	 */
	private void PopulateRankSheets(double[] weights, int[] ranks, double score) {
		for (int i = 0; i < weights.length; i++) {
			this.SetCellStyle(i + 3, 2 + (5 - ranks[i]), this.myStyle); //set style to chosen rank
			this.SetCellValue(i + 3, 7, ranks[i]); //populate rank value
			this.SetCellValue(i + 3, 9, ranks[i] * weights[i]); //populate weighted rank value
		}
		this.SetCellValue(weights.length + 3, 9, this.roundToTwoDecimals(score)); //populate total score of sheet
	}
	
	/**
	 * Round input value to two decimal spaces
	 * @param input
	 * @return
	 */
	private double roundToTwoDecimals(double input) {
		return Math.round(input * 100.0) / 100.0; //get two decimal places
	}
	
	/**
	 * Get the borrower string and hide the other borrower type sheet in excel file
	 * @param borrower
	 * @return
	 */
	private String setBorrowerInformation(BorrowerType borrower) {
		String borrowerString = "Corporate Borrower"; //set sheet to corporate borrower
		String hiddenSheet = this.IndividualSh; //hide individual sheet
		if (borrower == BorrowerType.Individual) { //else do the opposite
			borrowerString = "Individual Borrower";
			hiddenSheet = this.CorporateSh;
		}
		int val = wb.getSheetIndex(hiddenSheet); //get the sheet index of sheet to hide
		wb.setSheetHidden(val, true); //hide that sheet
		return borrowerString; //return borrower string
	}
	
	/**
	 * Get the loan covenant string 
	 * @param covenant
	 * @return
	 */
	private String getCovenantString(LoanCovenant covenant) {
		//return string based on chosen loan covenant rank
		if (covenant == LoanCovenant.FederalFullGuarantee) { 
			return "Federal/State/Municipal guarantees full loan amount";
		}
		else if (covenant == LoanCovenant.CorporateGuarantee) {
			return "Corporate Guarantee";
		}
		return "No Guarantee";
	}
	
	/**
	 * Get the step associated with the next step
	 * @param next
	 * @return
	 */
	private String getNextSteps(InterestRateCalculator.NextSteps next) {
		//return string based on calculated next step
		if (next == InterestRateCalculator.NextSteps.Review) {
			return "Credit Manager Review";
		}
		return "Initial Application Rejected";
	}
	
	/**
	 * Create style of cell to emphasize
	 * @return
	 */
	private XSSFCellStyle  CreateCellStyle() {
		XSSFCellStyle  style = wb.createCellStyle(); //create new style
	    XSSFFont defaultFont = wb.createFont(); //create new font
	    defaultFont.setBold(true); //set font to bold
	    defaultFont.setItalic(true); //set font to italic
	    //set style borders
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBorderTop(BorderStyle.THIN);
	    style.setBorderRight(BorderStyle.THIN);
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setWrapText(true); //wrap text in cell
	    style.setFont(defaultFont); //set font
	    style.setAlignment(HorizontalAlignment.CENTER); //set horizontal alignment to center
	    style.setVerticalAlignment(VerticalAlignment.CENTER); //set vertical alignment to center
	    return style; //return style
	}
	
	/**
	 * Set the cell value if input is a string
	 * @param row
	 * @param col
	 * @param text
	 */
	private void SetCellValue(int row, int col, String text) {
		sh.getRow(row).getCell(col).setCellValue(text);
	}
	
	/**
	 * Set the cell value if input is a double
	 * @param row
	 * @param col
	 * @param val
	 */
	private void SetCellValue(int row, int col, double val) {
		sh.getRow(row).getCell(col).setCellValue(val);
	}
	
	/**
	 * set the cell style
	 * @param row
	 * @param col
	 * @param style
	 */
	private void SetCellStyle(int row, int col, CellStyle style) {
		sh.getRow(row).getCell(col).setCellStyle(style);
	}
	
	
}
