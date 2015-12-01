import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JButton;


public class test extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
	public test() {
		setLayout(null);
		setBounds(Variables.bound1, Variables.bound2, Variables.bound3,
				Variables.bound4);
		
		textField = new JTextField();
		textField.setToolTipText("Past verification code here");
		textField.setBounds(79, 278, 291, 37);
		add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnVerInstructions = new JTextPane();
		txtpnVerInstructions.setBackground(SystemColor.control);
		txtpnVerInstructions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnVerInstructions.setText("You will be directed to the Fitbit authorization page"
				+ " in your web browser. Login and obtain your verification code."
				+ " Copy-and-paste your code into the text field below.");
		txtpnVerInstructions.setBounds(79, 158, 291, 112);
		add(txtpnVerInstructions);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(136, 334, 178, 61);
		add(btnSubmit);

	}
}
