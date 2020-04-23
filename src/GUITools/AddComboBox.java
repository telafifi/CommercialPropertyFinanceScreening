package GUITools;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

/**
 * Class that adds information to control objects for a combo box
 * @author tarek
 *
 */
public class AddComboBox {
	private Label myLabel; //Label Control object
	private Combo myCombo; //Combo box label Control object
	
	

	/**
	 * Instantiate class to add combo box and label
	 * @param myLabel
	 * @param myCombo
	 */
	public AddComboBox(Label myLabel, Combo myCombo) {
		this.myLabel = myLabel; 
		this.myCombo = myCombo;
	}
	
	/**
	 * Set the bounds of the items
	 * @param labelBounds the bounds of the label
	 * @param comboBounds the bounds of the combo box
	 */
	public void SetBounds(int[] labelBounds, int[] comboBounds) {
		this.myLabel.setBounds(labelBounds[0],labelBounds[1],labelBounds[2],labelBounds[3]);
		this.myCombo.setBounds(comboBounds[0], comboBounds[1], comboBounds[2], comboBounds[3]);
	}
	
	/**
	 * Set the information for the combo box and label
	 * @param comboOptions array of options to show for the combo box
	 * @param labelText what the label says
	 * @param toolTip tip for input when hovering over combo box
	 * @param selectedIndex selected combo box item on load
	 */
	public void SetComboInfo(String[] comboOptions, String labelText, String toolTip, int selectedIndex) {
		this.myLabel.setText(labelText);
		this.myCombo.setItems(comboOptions);
		this.myCombo.setToolTipText(toolTip);
		this.myCombo.select(selectedIndex);
	}
	

	//Getters
	public Label getMyLabel() {
		return myLabel;
	}

	public Combo getMyCombo() {
		return myCombo;
	}
}
