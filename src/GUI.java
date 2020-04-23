import java.util.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import GUITools.*;
import InputInformation.*;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

/**
 * Main class to run tool and build GUI
 * @author tarek
 *
 */
public class GUI {

	// INPUTS
	protected Shell shlCommercialMortgageScoring; //Shell Control (i.e. Application Window Object)
	private Label lblBorrower; //Label for Borrower Section
	// Property Type Combo Box
	private Label lblPropertyType;
	private Combo combPropertyType;
	// Borrower Type Combo Box
	private Label lblBorrowerType;
	private Combo combBorrowerType;
	// Loan Amount Text Box
	private Label lblLoanAmount;
	private Text txLoanAmount; 
	//Appraised value text box
	private Label lblAppraisedValue;
	private Text txAppraisedValue;
	//DSCR Ratio text box
	private Label lblDSCRRatio;
	private Text txDSCRRatio;
	//DE Ratio text box
	private Label lblDERatio;
	private Text txDERatio;
	// Borrower FICO Score Text Box
	private Label lblFICO;
	private Text txFICO; 
	// Borrower Net Worth
	private Label lblNetWorth;
	private Text txNetWorth; 
	// Loan Covenant Combo Box
	private Label lblCovenant;
	private Combo combCovenant;
	// Borrower Experience Text Box
	private Label lblBorrowerExp;
	private Text txBorrowerExp;
	// Current Ratio Text Box
	private Label lblCurrentRatio;
	private Text txCurrentRatio;
	// Vacancy rate Text Box
	private Label lblMarketRate;
	private Text txMarketRate;
	
	private Label lblLocation; //Label for location section
	// Address Text Box
	private Label lblAddress;
	private Text txAddress; 
	// City Text Box
	private Label lblCity;
	private Text txCity; 
	// State Combo Box
	private Label lblState;
	private Combo combState;
	// Zip Code Text Box
	private Label lblZip;
	private Text txZip; 
	// Vacancy rate Text Box
	private Label lblVacancy;
	private Text txVacancy;
	
	//Location Score Info
	private Group grpLocationScore;
	private RankingSystem rankLocScore;
	//Tenant Quality Info
	private Group grpTenantQuality;
	private RankingSystem rankTenantQuality;
	//Property Age Info
	private Group grpPropertyAge;
	private RankingSystem rankPropertyAge;
	//Environmental Site Info
	private Group grpEnvironmentalSite;
	private RankingSystem rankEnvironmentalSite;
	//Borrower Payment History Info
	private Group grpPaymentHistory;
	private RankingSystem rankPaymentHistory;
	
	
	
	//Output items
	private Button btnCalculate; //Button that runs calculator
	
	private String[] states; //String array of states
	private StringBuilder myErrorMessages; //Stores error message if input is incorrect
	private PropertyAddress inputAddress; //Creates address object
	private CMSInputs myInputs; //Creates input object
	
	
	//RUN PROGRAM

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI(); //Instantiate class
			window.open(); //open the window for viewing
		} catch (Exception e) {
			e.printStackTrace(); //return trace if error occurs
		}
	}
	
	/**
	 * Instantiate controls that need to be read from
	 * Tool reads from Text, Combo and Group objects, everything else is instantiated in different classes
	 */
	private void InstantiateControls() {
		
		//Instantiate Labels
		lblBorrower = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblPropertyType = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblBorrowerType = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblLoanAmount = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblAppraisedValue = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblDSCRRatio = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblDERatio = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblFICO = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblNetWorth = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblCovenant = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblBorrowerExp = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblLocation = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblAddress = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblCity = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblState = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblZip = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblVacancy = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblCurrentRatio = new Label(shlCommercialMortgageScoring, SWT.NONE);
		lblMarketRate = new Label(shlCommercialMortgageScoring, SWT.NONE);
		
		//Instantiate Text boxes
		txLoanAmount = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txAppraisedValue = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txDSCRRatio = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txDERatio = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txFICO = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txNetWorth = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txBorrowerExp = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txAddress = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txCity = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txZip = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txVacancy = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txCurrentRatio = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		txMarketRate = new Text(shlCommercialMortgageScoring, SWT.BORDER);
		
		//Instantiate Combo boxes
		combPropertyType = new Combo(shlCommercialMortgageScoring, SWT.READ_ONLY); //Read only means that the user can only pick from options available
		combBorrowerType = new Combo(shlCommercialMortgageScoring, SWT.READ_ONLY);
		combCovenant = new Combo(shlCommercialMortgageScoring, SWT.READ_ONLY);
		combState = new Combo(shlCommercialMortgageScoring, SWT.READ_ONLY);
		
		//Instantiate Groups
		grpLocationScore = new Group(shlCommercialMortgageScoring, SWT.NONE);
		grpTenantQuality = new Group(shlCommercialMortgageScoring, SWT.NONE);
		grpPropertyAge = new Group(shlCommercialMortgageScoring, SWT.NONE);
		grpEnvironmentalSite = new Group(shlCommercialMortgageScoring, SWT.NONE);
		grpPaymentHistory = new Group(shlCommercialMortgageScoring, SWT.NONE);
		
		
		}
	
	/**
	 * Event Handling in scenario where borrower type is changed
	 */
	private void borrowerTypeSelectionChanged() {
		txDERatio.setVisible(false); //Initially set to hidden
		lblDERatio.setVisible(false); //Initially set to hidden
		txCurrentRatio.setVisible(false); //Initially set to hidden
		lblCurrentRatio.setVisible(false); //Initially set to hidden
		//Event handler
		combBorrowerType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (combBorrowerType.getSelectionIndex()) { //cases when selection changes
				case 0: //Set as individual borrower
					txFICO.setVisible(true);
					lblFICO.setVisible(true);
					txNetWorth.setVisible(true);
					lblNetWorth.setVisible(true);
					txDERatio.setVisible(false);
					lblDERatio.setVisible(false);
					txCurrentRatio.setVisible(false);
					lblCurrentRatio.setVisible(false);
					break;
				case 1: //set as corporate borrower
					txFICO.setVisible(false);
					lblFICO.setVisible(false);
					txNetWorth.setVisible(false);
					lblNetWorth.setVisible(false);
					txDERatio.setVisible(true);
					lblDERatio.setVisible(true);
					txCurrentRatio.setVisible(true);
					lblCurrentRatio.setVisible(true);
				}
			}
		});
	}
	

	/**
	 * Open the window.
	 */
	public void open() {
		this.states = new USStates().getSortedStates(); //get an array of states for options
		createContents(); //Create the contents and populate fields
		this.borrowerTypeSelectionChanged();
		Display display = Display.getDefault(); //Display tool
		shlCommercialMortgageScoring.open(); //Open Shell
		shlCommercialMortgageScoring.layout(); //Set layout of Shell
		//Error handler in case Shell runs into issue
		while (!shlCommercialMortgageScoring.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Instantiate Calculate Button and add event handler for when clicked
	 */
	private void CreateCalculateButton() {
		btnCalculate = new Button(shlCommercialMortgageScoring, SWT.NONE); //Create button
		btnCalculate.setBackground(SWTResourceManager.getColor(73, 83, 93)); //Set the background colors
		btnCalculate.setForeground(SWTResourceManager.getColor(207, 210, 211)); //Set the text colors
		btnCalculate.setFont(SWTResourceManager.getFont("Calibri", 11, SWT.BOLD | SWT.ITALIC)); //Set the font type
		//Event handler for when button is clicked
		btnCalculate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ClickRunTool(); //Run tool if button is clicked
			}
		});
		btnCalculate.setBounds(10, 600, 600, 30); //Set bounds and location of buttons
		btnCalculate.setText("Calculate Score and Spread"); //Set text within button
	}
	
	/**
	 * Function to begin calculation process when Calculate Button is clicked
	 */
	private void ClickRunTool() {
		myErrorMessages = new StringBuilder(); //Instantiate Error message StringBuilder
		boolean flag = ReadAddress(); //Check if address is valid
		
		//Check if input placed within textboxes is valid
		flag &= GetDoubleInput(txLoanAmount, "Loan amount input incorrectly. Please input a double value (e.g. 70000)"); 
		flag &= GetDoubleInput(txVacancy, "Vacancy rate input incorrectly. Please input an appropriate value (e.g. 60)");
		flag &= GetDoubleInput(txDSCRRatio, "DSCR ratio input incorrectly. Please input an appropriate value (e.g. 1.50)");
		flag &= GetDoubleInput(txAppraisedValue, "Appraised value input incorrectly. Please input an appropriate value (e.g. 70000)");
		flag &= GetIntInput(txBorrowerExp, "Borrower Experience input incorrectly. Please input an appropriate number of years (e.g. 15)");
		flag &= GetDoubleInput(txMarketRate, "Market rate input incorrectly. Please input an appropriate value (e.g. 3.7)");
		if (combBorrowerType.getSelectionIndex() == 0) { //if Individual Borrower then check FICO score and Borrower net Worth
			flag &= GetIntInput(txFICO, "FICO score input incorrectly. Please input an appropriate value (e.g. 710)");
			flag &= GetDoubleInput(txNetWorth, "Net worth input incorrectly. Please input a double value (e.g. 70000)");
		}
		else if (combBorrowerType.getSelectionIndex() == 1) { //else if Corporate Borrower then Check DE Ratio and Current Ratio
			flag &= GetDoubleInput(txDERatio, "D/E ratio input incorrectly. Please input an appropriate value (e.g. 66.10)");
			flag &= GetDoubleInput(txCurrentRatio, "Current Ratio input incorrectly. Please input an appropriate value (e.g. 10.41)");
		}
		
		//Get the associated rank for each radio group 
		rankLocScore = GetRadioGroupResults(grpLocationScore, "Location Score");
		rankTenantQuality = GetRadioGroupResults(grpTenantQuality, "Tenant Quality");
		rankPropertyAge = GetRadioGroupResults(grpPropertyAge, "Property Age");
		rankEnvironmentalSite = GetRadioGroupResults(grpEnvironmentalSite, "Environmental Site Assessment");
		rankPaymentHistory = GetRadioGroupResults(grpPaymentHistory, "Payment History");
		
		/*
		 * If any of the inputs is incorrect, return the error message
		 * Otherwise, run the calculator
		 */
		if (flag) {
			CreateInputObject(); //Create and populate the input object
			InterestRateCalculator myCalc = new InterestRateCalculator(myInputs); //Run calculator tool
			/*
			 * Generate final report, for now it lives in an error message box, but will reported in better format later
			 */
			WriteToExcel excelReport = new WriteToExcel(myInputs, myCalc);
		}
		else {
			MessageDialog.openError(shlCommercialMortgageScoring, "Please fix incorrect inputs.", myErrorMessages.toString()); //return error message
		}
	}
	
	/**
	 * Create the shell object and set properties
	 */
	private void createShell() {
		shlCommercialMortgageScoring = new Shell(); //Instantiate main shell object
		shlCommercialMortgageScoring.setBackground(SWTResourceManager.getColor(50, 62, 72)); //Set shell background color
		shlCommercialMortgageScoring.setSize(650, 690); //Set size of the shell 
		shlCommercialMortgageScoring.setText("Commercial Mortgage Scoring System"); //Set name of the shell
	}
	
	
	/**
	 * Create and populate controls of the window.
	 */
	protected void createContents() {
		this.createShell(); //Create shell object
		this.InstantiateControls(); //Instantiate control objects
		CreateLabels myLabels = new CreateLabels(shlCommercialMortgageScoring); //Start creating labels
		myLabels.CreateLabel(lblBorrower, "Borrower Input", 10, 10, 200, 25); //Create borrower label
		String[] comboOptionsProperty = {"Multi-Family", "Retail", "Office", "Industrial", "Hospitality", "Other"}; //create Property type combo box options
		myLabels.CreateComboBox(lblPropertyType, combPropertyType, 10, 40, 110, 140, 25, 0, "Property Type:", 
				"Set the property type of the structure to build.", comboOptionsProperty); //Create Property Type Combo box
		String[] comboOptionsBorrower = {"Individual", "Corporate"}; //create Borrower type combo box options
		myLabels.CreateComboBox(lblBorrowerType, combBorrowerType, 270, 40, 110, 220, 25, 0, "Borrower Type:", 
				"Set the borrower type", comboOptionsBorrower); //Create Borrower Type Combo box
		myLabels.CreateTextBox(lblLoanAmount, txLoanAmount, 10, 70, 130, 120, 25, "Loan Amount ($):", "7000000", 
				"How much is the potential borrower asking for?", CreateLabels.textType.Double); //Create Loan amount text box
		myLabels.CreateTextBox(lblAppraisedValue, txAppraisedValue, 270, 70, 160, 170, 25, "Appraised Value ($):", "10000000", 
				"What is the appraised value of the property?", CreateLabels.textType.Double); //Create Appraised Value text box
		myLabels.CreateTextBox(lblFICO, txFICO, 10, 130, 130, 120, 25, "FICO Score:", "650", 
				"What is the borrower's FICO score?", CreateLabels.textType.Int); //Create FICO Score text box
		myLabels.CreateTextBox(lblCurrentRatio, txCurrentRatio, 10, 130, 130, 120, 25, "Current Ratio:", "10.41", 
				"What is the borrower's current ratio (ability to pay short term obligations)?", CreateLabels.textType.Double); //Create Current Ratio text box
		String[] comboOptionsCovenant = {"Federal/State full guarantee", "Corporate guarantee", "No guarantee"}; //create loan covenant combo box options
		myLabels.CreateComboBox(lblCovenant, combCovenant, 270, 100, 110, 220, 25, 0, "Loan Covenant:", 
				"Set the loan covenant type.", comboOptionsCovenant); //create Loan Covenant Combo Box
		myLabels.CreateTextBox(lblDSCRRatio, txDSCRRatio, 10, 100, 130, 120, 25, "DSCR:", "1.10", 
				"What is the Debt to Service Coverage Ratio (DSCR)?", CreateLabels.textType.Double); //Create DSCR Text Box
		myLabels.CreateTextBox(lblDERatio, txDERatio, 270, 130, 180, 150, 25, "D/E Ratio:", "66.10", 
				"What is the debt to equity ratio?", CreateLabels.textType.Double); //Create DE Ratio Text Box
		myLabels.CreateTextBox(lblBorrowerExp, txBorrowerExp, 10, 160, 200, 50, 25, "Borrower Experience (years):", "15", 
				"How many years has the borrower been in the industry?", CreateLabels.textType.Int); //Create borrower experience text box
		myLabels.CreateTextBox(lblNetWorth, txNetWorth, 270, 130, 180, 150, 25, "Borrower Net Worth ($):", "8000000", 
				"What is the net worth of the borrower?", CreateLabels.textType.Double); //Create Borrower Net Worth text box
		myLabels.CreateTextBox(lblMarketRate, txMarketRate, 270, 160, 180, 150, 25, "Market Vacancy Rate (%):", "3.7", 
				"Vacancy rate of same property type in the same market", CreateLabels.textType.Double); //Create Vacancy rate text box
		myLabels.CreateLabel(lblLocation, "Property Information", 10, 190, 200, 25); //Create property information Label
		myLabels.CreateTextBox(lblAddress, txAddress, 10, 220, 60, 530, 25, "Address:", "", 
				"Address of property", CreateLabels.textType.String); //Create Address text Box
		myLabels.CreateTextBox(lblCity, txCity, 10, 250, 60, 70, 25, "City:", "", 
				"City of property", CreateLabels.textType.String); //Create City Text Box
		myLabels.CreateComboBox(lblState, combState, 150, 250, 50, 60, 25, 0, "State:", 
				"State of the property", this.states); //Create State Combo Box
		myLabels.CreateTextBox(lblZip, txZip, 270, 250, 70, 60, 25, "Zip Code:", "", 
				"Zip code of property", CreateLabels.textType.Int); //Create Zip Code Text Box
		myLabels.CreateTextBox(lblVacancy, txVacancy, 410, 250, 120, 70, 25, "Vacancy Rate (%):", "2", 
				"Vacancy rate of property area (per rent roll or from appraisal)", CreateLabels.textType.Double); //Create Vacancy rate Text Box
		myLabels.CreateRadioGroup(grpLocationScore, 10, 290, 600, 50, 
				"Location neighborhood score:", RadioTips.LocationScoreTips(), false); //Create location Score radio group
		myLabels.CreateRadioGroup(grpTenantQuality, 10, 350, 600, 50, 
				"Tenant quality score:", RadioTips.TenantQualityScoreTips(), false); //Create Tenant Quality Radio Group
		myLabels.CreateRadioGroup(grpPropertyAge, 10, 410, 600, 50, 
				"Property age and condition:", RadioTips.PropertyAgeScoreTips(), false); //Create Property Age Radio group
		myLabels.CreateRadioGroup(grpEnvironmentalSite, 10, 470, 600, 50, 
				"Environmental site assessment score:", RadioTips.EnviroSiteScoreTips(), true); //Create Environmental Site Assessment radio group
		myLabels.CreateRadioGroup(grpPaymentHistory, 10, 530, 600, 50, 
				"Borrower's payment history:", RadioTips.BorrowerPaymentScoreTips(), false); //Create Payment History Radio group
		this.CreateCalculateButton(); //Create Calculate button
	}
	
	
	
	//READ IN INPUTS
	/**
	 * Read the address value inputs and check if input is valid
	 * @return boolean if input can be read properly
	 */
	private boolean ReadAddress() {
		String address = txAddress.getText(); //Get address text
		String city = txCity.getText(); //Get city text
		String state = combState.getText(); //Get state chosen
		if (address.isEmpty() || city.isEmpty() || state.isEmpty()) { //If any of the text options are empty return an error
			this.myErrorMessages.append("Please do not leave the address input empty. Please input appropriate address information.\n");
			return false;
		}
		int zip = 0;
		try {
			zip = Integer.parseInt(txZip.getText()); //try to extract zip code
			this.inputAddress = new PropertyAddress(address, city, state, zip); //instantiate address object if all input is correct
		}
		catch (Exception ex) {
			this.myErrorMessages.append("Zip code input incorrectly. Please input appropriate Zip code (e.g. 77046)\n"); //if zip code could not be parsed return error
			return false;
		}
		return true;
	}
	
	/**
	 * Read in textbox value as double
	 * @param inputText Textbox to read
	 * @param errorMessage Error message to display if needed
	 * @return
	 */
	private boolean GetDoubleInput(Text inputText, String errorMessage) {
		double inputValue = 0;
		try {
			inputValue = Double.parseDouble(inputText.getText()); //try extracting double value
		}
		catch (Exception ex) {
			this.myErrorMessages.append(errorMessage + "\n"); //return error if cannot parse
			return false;
		}
		return true;
	}
	
	/**
	 * Read in textbox value as int
	 * @param inputText Textbox to read
	 * @param errorMessage Error message to display if needed
	 * @return
	 */
	private boolean GetIntInput(Text inputText, String errorMessage) {
		int inputValue = 0;
		try {
			inputValue = Integer.parseInt(inputText.getText()); //try extracting int value
		}
		catch (Exception ex) {
			this.myErrorMessages.append(errorMessage + "\n"); //return error if cannot parse
			return false;
		}
		return true;
	}
	
	/**
	 * Get radio group results and return a ranking system
	 * @param radioGroup Radio group to read from
	 * @param name name of group for error message
	 * @return
	 */
	private RankingSystem GetRadioGroupResults(Group radioGroup, String name) {
		for (Control radio : radioGroup.getChildren()) { //get every control object within the radio group
			if (radio instanceof Button) { //if the control object is a button
				if (((Button) radio).getSelection()) { //check if it is selected
					String textValue = ((Button) radio).getText(); //if it is selected get the text value and return a rank
					/*
					 * higher the number the stronger the rank
					 */
					if (textValue.contains("5")) {
						return RankingSystem.Strong;
					}
					else if (textValue.contains("4")) {
						return RankingSystem.AboveAverage;
					}
					else if (textValue.contains("3")) {
						return RankingSystem.Average;
					}
					else if (textValue.contains("2")) {
						return RankingSystem.BelowAverage;
					}
					else if (textValue.contains("1")) {
						return RankingSystem.Weak;
					}
				}
			}
		}
		myErrorMessages.append("Please select an option in the group " + name + "\n" ); //if no button is chosen then add error message
		return null;
	}

	/**
	 * Create the input object and store all the input information within it
	 */
	private void CreateInputObject() {
		double loanAmount = Double.parseDouble(txLoanAmount.getText()); //read loan amount text
		double vacancyRate = Double.parseDouble(txVacancy.getText()); //read vacancy rate text
		double DSCRRatio = Double.parseDouble(txDSCRRatio.getText()); //read DSCR text
		double appraisedValue = Double.parseDouble(txAppraisedValue.getText()); //read appraised value text
		int borrowerExp = Integer.parseInt(txBorrowerExp.getText()); //read borrower experience text
		double marketRate = Double.parseDouble(txMarketRate.getText()); //read market rate experience text
		// Get inputs depending on the borrower type the user selected
		double netWorth = 0.0;
		int FICO = 0;
		double currentRatio = 0.0;
		double DERatio = 0.0;
		BorrowerType borrower = ReadComboBoxes.getBorrowerType(combBorrowerType.getSelectionIndex()); //get selected borrower type
		if (borrower == BorrowerType.Individual) {
			netWorth = Double.parseDouble(txNetWorth.getText()); //read net worth text
			FICO = Integer.parseInt(txFICO.getText()); //read FICO score text
		}
		else if (borrower == BorrowerType.Corporate) {
			currentRatio = Double.parseDouble(txCurrentRatio.getText());
			DERatio = Double.parseDouble(txDERatio.getText()); //read DE ratio text
		}
		
		PropertyType property = ReadComboBoxes.getPropertyType(combPropertyType.getSelectionIndex()); //get selected property type
		LoanCovenant covenant = ReadComboBoxes.getLoanCovenant(combCovenant.getSelectionIndex()); //get selected loan covenant
		this.myInputs = new CMSInputs(inputAddress, borrower, property, covenant, vacancyRate, marketRate, loanAmount, 
				netWorth, FICO, DSCRRatio, DERatio, currentRatio, appraisedValue, rankLocScore, rankTenantQuality, rankEnvironmentalSite, 
				borrowerExp, rankPaymentHistory, rankPropertyAge); //instantiate inputs object
	}
}
