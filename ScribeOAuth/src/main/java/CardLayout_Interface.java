import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CardLayout_Interface implements ActionListener {
	static CardLayout cardLayout; // make static CardLayout
	static JPanel card = new JPanel(); // make a static CardLayout

	
	// CREATE STATIC BUTTONS HERE
	// homeCard buttons
	static JButton btnAccountSettings = new JButton("Account Settings");
	static JButton btnFitnessGoals = new JButton("Fitness Goals");
	static JButton btnViewProgress = new JButton("View Progress");
	// fitbitSetupCard buttons
	static JButton btnFitbitSetup = new JButton("Set up your Fitbit device");
	// general buttons
	static JButton btnBack = new JButton("");
	
	
	// actionPerformed handles actions for button clicks
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// homeCard buttons
		if (source == btnAccountSettings) {
			cardLayout.show(card, "fitbitSetupCard");
		}
		if (source == btnFitnessGoals){
			//put code to open fitnessGoals card once it's created
		}
		if (source == btnViewProgress){
			// put code to open viewProgress card once it's created
		}
		// fitbitSetupCard buttons
		if (source == btnFitbitSetup){
			// run main.java code
		}
		// general buttons
		if (source == btnBack) {
			cardLayout.show(card, "homeCard");
		}
	}

	public static void main(String[] args) {
		JFrame frm = new JFrame(); // make new frame
		JPanel contentPane = (JPanel) frm.getContentPane(); // new conentPane
															// panel type casing
															// frm.getContentPane()
															// with JPanel
		card.setLayout(cardLayout = new CardLayout()); // set layout of static
														// panel to cardLayout
														// name = to new
														// CardLayout()
		ActionListener AL = new CardLayout_Interface(); // present an
														// ActionListener AL

		
		// CREATE CARDS HERE

		// *** Create Home Card ***
		JPanel homeCard = new JPanel(); // make cardHome JPanel
		homeCard.setLayout(null); // set card layout
		homeCard.setBorder(new EmptyBorder(5, 5, 5, 5));

		// * homeCard Buttons *
		btnAccountSettings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAccountSettings.setBounds(135, 113, 178, 61);
		homeCard.add(btnAccountSettings);
		btnAccountSettings.addActionListener(AL);

		btnFitnessGoals.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitnessGoals.setBounds(135, 185, 178, 61);
		homeCard.add(btnFitnessGoals);
		btnFitnessGoals.addActionListener(AL);
		
		btnViewProgress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewProgress.setBounds(135, 257, 178, 61);
		homeCard.add(btnViewProgress);
		btnViewProgress.addActionListener(AL);
		
		JLabel lblAmbientDevice = new JLabel("Ambient Device");
		lblAmbientDevice.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmbientDevice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmbientDevice.setBounds(135, 346, 178, 29);
		homeCard.add(lblAmbientDevice);
		
		// Need to figure out how to add action to the radio button!!! ***
		JRadioButton rdbtnOn = new JRadioButton("On");
		rdbtnOn.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOn.setSelected(true);
		rdbtnOn.setBounds(87, 382, 86, 23);
		homeCard.add(rdbtnOn);

		JRadioButton rdbtnOff = new JRadioButton("Off");
		rdbtnOff.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOff.setBounds(260, 382, 86, 23);
		homeCard.add(rdbtnOff);

		ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnOn);
		bG.add(rdbtnOff);

		
		// *** Create fitbitSetup Card ***
		JPanel fitbitSetupCard = new JPanel();
		fitbitSetupCard.setLayout(null); // set card layout
		fitbitSetupCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnFitbitSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitSetup.setBounds(74, 234, 288, 50);
		fitbitSetupCard.add(btnFitbitSetup);

		JTextArea textArea = new JTextArea(
				"Clicking this button will open your web browser.\n"
						+ "Make sure you have an internet connection.");
		textArea.setBackground(SystemColor.control);
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(74, 308, 309, 64);
		fitbitSetupCard.add(textArea);

		JLabel lblFit = new JLabel("Fitbit Setup");
		lblFit.setBackground(Color.WHITE);
		lblFit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFit.setBounds(74, 11, 80, 35);
		fitbitSetupCard.add(lblFit);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(66, 0, 368, 58);
		fitbitSetupCard.add(panel);

		// Fitbit Setup Buttons
		btnBack.setBackground(SystemColor.control);
		// Image backIcon = new ImageIcon("/backIcon_50x50.png").getImage();
		// btnBack.setIcon(new ImageIcon(backIcon));
		btnBack.setBounds(0, 0, 67, 58);
		fitbitSetupCard.add(btnBack);
		btnBack.addActionListener(AL);

		// Add cards to panel
		cardLayout.show(card, "homeCard"); // set frm parameters
		card.add("homeCard", homeCard);
		card.add("fitbitSetupCard", fitbitSetupCard);
		contentPane.add(card); // set the contentPane to add static card panel

		// JFrame settings
		frm.setVisible(true);
		frm.setResizable(true);
		frm.setBounds(Variables.bound1, Variables.bound2, Variables.bound3,
				Variables.bound4);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when X is
															// pressed, program
															// stops running
	}
}
