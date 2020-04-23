﻿# Commercial Property Financing Application Screening Tool
Final Project for CIT 591 - Introduction to Software Development

The team developed a commercial property financing screening application for use by banks/lenders to evaluate the 
risks involved with a loan and calculate the interest rate on loans. This tool will extracts public data such as 
US Treasury Bond yield, US population by city/town and capitalization rate by property type. These data will then
be combined with loan specific information such as loan amount, location and Debt Service Coverage ratio (DSCR) 
to conduct a feasibility assessment on the loan application. 

## Prerequisites

Users need to install several Java apis plug-ins within Eclipse. Please see below for installation instructions.

## How to run the program

Open "GUI.java" file where the main method resides -> Run

## Installation

#### Adding plug-ins and apis:
    1. Please follow instructions in this video: https://www.youtube.com/watch?v=OHbSx2iVujE or follow the steps below.
    2. In Eclipse, click "Help" -> "Install New Software"
    3. In "Work with" box, type your eclipse version (ie. Juno http://download-eclipse.org/releases/Juno) 
    4. Click on "General Purpose Tools" triangle, select "Swing Designer", "SWT Designer, "SWT Designer Core","SWT Designer SWT_AWT Support" 
    5. Also select "WindowBuider Core", "WindowBuilder Core Documentation", "WindowBuilder Core UI" and "WindowBuilder GroupLayout Support"
    5. Click "Next" -> "Next" -> accept the user agreement -> click "Finish"
    7. Click "Yes" to restart eclipse to complete the installation

### NOTE: In case Eclipse can not find libraries on its own, please remove all referenced libraries eclipse references and add the following referenced libraries and external jar files in the folder: 

#### Adding referenced libraries:

    1. Right click on the project in eclipse. Click on Properties.
    2. Click on Java Build Path on the left side menu
    3. Click on Libraries
    4. Click on Add External JARs
    5. For Windows, navigate to the lib folder in this project and add all JAR files in the folder to the project
    6. For Mac, navigate to the lib_mac folder in this project and add all JAR files in the folder to the project

#### Adding external jar files:

    1. curvesapi-1.06
    2. xmlbeans-3.1.0
    3. commons-collections4-4.4
    4. activation-1.1.1
    5. commons-codec-1.13
    6. commons-math3-3.6.1
    7. jaxb-api-2.3.1
    8. jaxb-core-2.3.0.1
    9. jaxb-impl-2.3.2
    10. junit-4.12
    11. log4j-1.2.17
    12. SparseBitSet-1.2
    13. commons-compress-1.19
    14. commons-logging-1.2
    15. poi-4.1.2
    16. poi-examples-4.1.2
    17. poi-excelant-4.1.2
    18. poi-ooxml-4.1.2
    19. poi-ooxml-schemas-4.1.2
    20. poi-scratchpad-4.1.2
    21. swing2swt
    22. com.ibm.icu_64.2.0.v20190507-1337

## Public Data Files:

- TreasuryYield.csv file: 
	The treasury yield data includes the historical daily treasury yield of year 2019. 
	The tool will run based on the date used and find the associated date from 2019 to get a treasury yield value. 
	Typically this is done using dynamic data of treasury yield of the current day. We decided to use the 2019 data set 
	to allow for changing values based on date with a static data type. Realistically, a dynamic data type would be used 
	to obtain the treasury yield value.

- CapRatesFinal.csv file: 
	This file includes capitalization rates for all commercial property types in our project in all states in the US. 
	The cap rates are based on the CBRE 2019 semi-annual cap rates survey published in Jan.2020.
	The CBRE survey does not include cap rates for every city in every states, rather it focuses on the major cities.
	For cap rates that are not included in the CBRE survey, we've approximated the rates for the same property type in the nearby city/state.
	If the user enters a city which does not have a cap rate in the file, the cap rate of the property type of the state will be used. 
	Note the rates are in decimals/whole numbers. For example, 15 = 15%, 8.5 = 8.5%. 

## Running the tests

- JUnit test are located within the "InterestRateCalculatorTest", "Property/PropertyInfoTest", "Property/PropertyScoreTest", "Borrower/BorrowerInfoTest", "Borrower/CorporateBorrowerScoringTest", and "Borrower/IndividualBorrowerScoringTest" java files. 
- The methods in the aforementioned java files have been tested succesfully in the JUnit test
 

## Break down into end to end tests

- The team has tested a total of 25 different edge cases in the scoring excel template
- The 25 cases were ran indidivudally in the java screening tool. 
- The output of each screening tool java test were then compared to the results of the scoring excel template

## Contributing

- The team first discussed any changes the member wishes to make via email or communication through private channel 
on Slack, or any other method with the owners/other team members of this repository before making a change.
- After the changes/updates are pushed to the repository, inform the owner/other team member about it. 

## Authors

- Tarek El-Afifi
- Kristin Xie
- Yu Zhang
