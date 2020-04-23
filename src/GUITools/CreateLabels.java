package GUITools;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Control;

/**
 * Class that populates all objects in GUI
 * @author tarek
 *
 */
public class CreateLabels {
	
	private Shell shlCommercialMortgageScoring; //bring in shell object
	
	/**
	 * Instantiate label creation class
	 * @param shlCommercialMortgageScoring
	 */
	public CreateLabels(Shell shlCommercialMortgageScoring) {
		this.shlCommercialMortgageScoring = shlCommercialMortgageScoring; //set private shell
	}
		
		/**
		 * Create bolded label object
		 * @param lbl label object
		 * @param text string to place in object
		 * @param minX x coordinate
		 * @param minY y coordinate
		 * @param width width of label
		 * @param height height of label
		 */
		public void CreateLabel(Label lbl, String text, int minX, int minY, int width, int height) {
			CreateLabels.SetControlObjectCharacteristics(lbl, true, true); //set label font and colors
			lbl.setBounds(minX, minY, width, height); //set bounds of label
			lbl.setText(text); //set text stored in label
		}
		
		/**
		 * Set control object fonts and colors
		 * @param obj Control object to update
		 * @param isLabel True if the object is a label or a group
		 * @param isMajor True if it needs to be bolded (title text)
		 */
		public static void SetControlObjectCharacteristics(Control obj, boolean isLabel, boolean isMajor) {
			obj.setBackground(SWTResourceManager.getColor(255, 255, 255)); //set background color to white by default
			obj.setForeground(SWTResourceManager.getColor(50, 62, 72)); //set text color to dark navy by default
			if (isLabel) { //flip defualt colors if true
				obj.setBackground(SWTResourceManager.getColor(50, 62, 72)); 
				obj.setForeground(SWTResourceManager.getColor(255, 255, 255));
			}
			if (isMajor) { //create larger bolded text if true
				obj.setFont(SWTResourceManager.getFont("Calibri", 11, SWT.BOLD | SWT.ITALIC));
			}
			else {
				obj.setFont(SWTResourceManager.getFont("Calibri", 10, SWT.None));
			}
		}
		
		/**
		 * Create textbox object and label
		 * @param lbl Label object
		 * @param txt Text object
		 * @param minX x-coordinate
		 * @param minY y-coordinate
		 * @param labelWidth width of label
		 * @param textWidth width of text
		 * @param height height of objects
		 * @param labelText text to store in label
		 * @param initialText initial text to store in text box
		 * @param toolTip tooltip to add when hovering over text box
		 * @param type type of input
		 */
		public void CreateTextBox(Label lbl, Text txt, int minX, int minY, int labelWidth, int textWidth, int height, String labelText, 
				String initialText, String toolTip, textType type) {
			CreateLabels.SetControlObjectCharacteristics(lbl, true, false); //set label characteristics
			CreateLabels.SetControlObjectCharacteristics(txt, false, false); //set text box characteristics
			int[] labelBounds = {minX, minY, labelWidth, height}; //label bounds in shell
			int[] textBounds = {minX + labelWidth, minY, textWidth, height}; //text box bounds in shell
			if (type != textType.String) { //if the text is not meant to be a string
				boolean isInt = false; 
				if (type == textType.Int) { //check if the text is meant to be an integer
					isInt = true;
				}
				OnlyAllowNumericalText(txt, isInt); //add event handler for typing in textbox to limit what can be added
			}
			AddTextBox textBox = new AddTextBox(lbl, txt); //Create add text box object
			textBox.SetBounds(labelBounds, textBounds); //set bounds to text and label
			textBox.SetTextInfo(initialText, labelText, toolTip); //set label info
		}
		
		/**
		 * Create Combo box object and label
		 * @param lbl Label object
		 * @param comb Combo object
		 * @param minX x-coordinate
		 * @param minY y-coordinate
		 * @param labelWidth width of label
		 * @param combWidth width of combo box
		 * @param height height of objects
		 * @param selectedIndex default selected index
		 * @param labelText text to write in label
		 * @param toolTip tooltip when hovering over text box
		 * @param comboOptions //options to store in combo box
		 */
		public void CreateComboBox(Label lbl, Combo comb, int minX, int minY, int labelWidth, int combWidth, int height, int selectedIndex, 
				String labelText, String toolTip, String[] comboOptions) {
			CreateLabels.SetControlObjectCharacteristics(lbl, true, false); //set label characteristics
			CreateLabels.SetControlObjectCharacteristics(comb, false, false); //create combo box characteristics
			int[] labelBounds = {minX, minY, labelWidth, height}; //label bounds in shell
			int[] comboBounds = {minX + labelWidth, minY, combWidth, height}; //combo bounds in shell
			AddComboBox comboBox = new AddComboBox(lbl, comb); //create add combo object
			comboBox.SetBounds(labelBounds, comboBounds); //set bounds for label and combo
			comboBox.SetComboInfo(comboOptions, labelText, toolTip, selectedIndex); //set combo info
		}
		
		/**
		 * Create Radio group with labels
		 * @param grp group object
		 * @param lbl label object
		 * @param minX x-coordinate
		 * @param minY y-coordinate
		 * @param grpWidth group width
		 * @param grpHeight group height
		 * @param labelText text to store in label
		 * @param radioToolTips 5 tool tips for each radio button
		 * @param ignoreIntermediates boolean to ignore options 2 and 4
		 */
		public void CreateRadioGroup(Group grp, int minX, int minY,
				int grpWidth, int grpHeight, String labelText, String[] radioToolTips, boolean ignoreIntermediates) {
			grp.setText(labelText); //set group label
			CreateLabels.SetControlObjectCharacteristics(grp, true, true); //set group characteristics
			grp.setBounds(minX, minY, grpWidth, grpHeight); //set group bounds
			int buttonHeight = 20; //standard top of button
			if (this.isMac()) {
				buttonHeight = 0; //if it is a mac OS then set that button height to zero
			}
			int[] radioBounds = {10, buttonHeight, 120, 28}; //set initial radio button bounds
			AddRadioGroup radioGroup = new AddRadioGroup(grp, ignoreIntermediates); //create add radio group object
			radioGroup.SetBounds(radioBounds); //set bounds for radio buttons
			radioGroup.SetTextInfo(radioToolTips); //set tooltips to radio buttons
		}
		
		/**
		 * Check if the operating system in use is a Mac
		 * Mac OS require a different top dimension to the radio button to see them within the group
		 * @return
		 */
		private boolean isMac() {
			String OS = System.getProperty("os.name").toLowerCase(); //get operating system of group
			if (OS.indexOf("mac") >= 0) { //check if the operating system is a mac
				return true; //return true
			}
			return false; //otherwise return false
		}
		
		
		
		/**
		 * Type of text box input allowed
		 * @author tarek
		 *
		 */
		public enum textType{
			String,
			Int,
			Double
		}
		
		/**
		 * Limit characters allowed to be typed in text boxes meant to store double or int values
		 * @param txBox text box to limit
		 * @param isInt true if is integer
		 */
		private void OnlyAllowNumericalText (Text txBox, boolean isInt) {
			txBox.addKeyListener(new KeyAdapter() { //add event handler for when user types in text box
				@Override
				public void keyPressed(KeyEvent e) {
					char typedChar = e.character; //get character user types
					int keyCode = e.keyCode; //get code of character typed
					/*
					 * KeyCode = 8 is backspace
					 * KeyCode = 32 is space
					 */
					if (isInt) { //if the text box is supposed to hold an integer
						if (!(Character.isDigit(typedChar) || keyCode == 8)) { //only allow for backspace or digits being typed
							e.doit = false;
						}
					}
					else {
						if (!(Character.isDigit(typedChar) || keyCode == 8 || typedChar == '.')) { //only allow for backspace, digits or deminal point being typed
							e.doit = false;
						}
					}
				}
			});
		}
}
