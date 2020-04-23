package GUITools;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;

/**
 * Class that adds information to control objects for a group with radio buttons
 * @author tarek
 *
 */
public class AddRadioGroup {
	private Group myGroup; //Group Control object
	private Label myLabel; //Label control object
	private Button[] radioArray; //list of buttons hosted in group
	private boolean ignoreIntermediates; //boolean to see whether to ignore options 2 and 4 of radio group
	
	
	/**
	 * Instantiate addition of group with radio buttons
	 * @param myGroup group in shell
	 * @param ignoreIntermediates check to see whether or not to ignore the intermediate radio buttons
	 */
	public AddRadioGroup(Group myGroup, boolean ignoreIntermediates) {
		this.myGroup = myGroup;
		this.radioArray = new Button[5]; //create 5 radio buttons
		this.ignoreIntermediates = ignoreIntermediates;
		this.CreateRadioGroupObjects(); //create the radio objects
		this.radioArray[2].setSelection(true); //Set default selection as Average
	}
	
	/**
	 * Create radio group buttons and set characteristics
	 */
	private void CreateRadioGroupObjects() {
		for (int i = 0; i < this.radioArray.length; i++) {
			this.radioArray[i] = new Button(this.myGroup, SWT.RADIO); //set as radio button
			CreateLabels.SetControlObjectCharacteristics(this.radioArray[i], true, false); //set style of button
		}
	}
	
	/**
	 * Set bounds of radio buttons
	 * @param radioBounds bounds of first button
	 */
	public void SetBounds(int[] radioBounds) {
		int[] radioWidths = {90, 150, 100 ,140, 90}; //widths of different radio buttons
		int leftOffset = 0; //set left offset
		for (int i = 0; i < radioArray.length; i++) {
			if (i == 0) { //for first button - set bounds
				this.radioArray[i].setBounds(radioBounds[0], radioBounds[1], radioWidths[i], radioBounds[3]);
				leftOffset = radioBounds[0] + radioWidths[i]; //update left offset
			}
			else { //afterwards - set bounds using this calculation
				this.radioArray[i].setBounds(leftOffset, radioBounds[1], radioWidths[i], radioBounds[3]);
				leftOffset += radioWidths[i]; //update left offset
			}
		}
		if (this.ignoreIntermediates) { //if ignoring intermediates set options 2 and 4 as hidden
			radioArray[1].setVisible(false);
			radioArray[3].setVisible(false);
		}
	}
	
	/**
	 * Set text and tool tip information to radio buttons
	 * @param radioToolTips
	 */
	public void SetTextInfo(String[] radioToolTips) {
		String[] radioStatements = {"5: Strong", "4: Above Average", "3: Average", "2: Below Average", "1: Weak"}; //set button statements
		for (int i = 0; i < radioArray.length; i++) { //set text and tooltip for each button
			this.radioArray[i].setText(radioStatements[i]);
			this.radioArray[i].setToolTipText(radioToolTips[i]);
		}
	}
	
	//Getters
	public Label getMyLabel() {
		return myLabel;
	}

	public Button[] getRadioArray() {
		return radioArray;
	}
	
	public Group getMyGroup() {
		return myGroup;
	}
}