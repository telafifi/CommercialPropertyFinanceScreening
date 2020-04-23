package GUITools;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

/**
 * Class that adds information to control objects for a text box
 * @author tarek
 *
 */
public class AddTextBox {
	private Text myText; //text box control object
	private Label myLabel; //label control object
	
	/**
	 * Instantiate text box object
	 * @param myLabel label control object
	 * @param myText text control object
	 */
	public AddTextBox(Label myLabel, Text myText) {
		this.myLabel = myLabel;
		this.myText = myText;
	}
	
	/**
	 * Set bounds to control objects
	 * @param labelBounds bounds of label object
	 * @param textBounds bounds of text object
	 */
	public void SetBounds(int[] labelBounds, int[] textBounds) {
		this.myLabel.setBounds(labelBounds[0],labelBounds[1],labelBounds[2],labelBounds[3]); //set label bounds
		this.myText.setBounds(textBounds[0], textBounds[1], textBounds[2], textBounds[3]); //set text box bounds
	}
	
	/**
	 * Set text and tooltips to control objects
	 * @param initialText
	 * @param labelText
	 * @param toolTip
	 */
	public void SetTextInfo(String initialText, String labelText, String toolTip) {
		this.myLabel.setText(labelText); //set label text
		this.myText.setText(initialText); //set initial text to text box
		this.myText.setToolTipText(toolTip); //set tool tip to text box
	}
	
	//Getters
	public Text getMyText() {
		return myText;
	}

	public Label getMyLabel() {
		return myLabel;
	}
}
