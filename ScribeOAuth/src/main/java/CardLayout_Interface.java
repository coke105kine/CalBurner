import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CardLayout_Interface implements ActionListener {
	static CardLayout cardLayout; // make static CardLayout
	static JPanel card = new JPanel(); // make a static CardLayout
	public static String verCodeInput = "";
	public static boolean ambientSelected = false;
	public static boolean fitbitIsSetup = false;

	// textfield for fitbitVerificationPage
	static JTextField textFieldVerification = new JTextField();
	
	// String used for ambientImage
	public static String percentage = "";
	public static JLabel label2 = new JLabel("");
	
	// CREATE STATIC BUTTONS HERE
	// homeCard buttons
	static JButton btnFitbitAccountSetup = new JButton("Fitbit Account Setup");
	static JButton btnAmbientInterface = new JButton("Ambient Interface");
	static JButton btnQuit = new JButton("Quit");
	// fitbitSetupCard buttons
	static JButton btnFitbitSetup = new JButton("Set up your Fitbit device");
	// fitbitVerificationCard buttons
	static JButton btnSubmit = new JButton("Submit");
	// setupFitbitFirst Card's buttons
	static JButton btnFitbitAccountSetup2 = new JButton("Fitbit Account Setup");
	// general buttons
	static JButton btnBack = new JButton("");
	static JButton btnBack1 = new JButton("");
	static JButton btnBack2 = new JButton("");
	
	
	// actionPerformed handles actions for button clicks
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// homeCard buttons
		if (source == btnFitbitAccountSetup) {
			cardLayout.show(card, "fitbitSetupCard");
		}
		if (source == btnAmbientInterface){
			if (!fitbitIsSetup){
				cardLayout.show(card, "setupFitbitFirst");
			}
			else {
				ambientSelected = true;
				cardLayout.show(card, "ambientInterfaceCard");
			}
		}
		if (source == btnQuit){
			// put code to open viewProgress card once it's created
			System.exit(0);
		}
		
		// fitbitSetupCard buttons
		if (source == btnFitbitSetup){
			//This try/catch will run the data driver; data driver needs work
			try {
				DataDriver.startGrabData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			// Change to fitbitVerification Card
			cardLayout.show(card, "fitbitVerificationCard");
		}
		// fitbitVerificationCard buttons
		if (source == btnSubmit){
			verCodeInput = textFieldVerification.getText();
			DataDriver.sendVerification(verCodeInput);
			fitbitIsSetup = true;
			cardLayout.show(card, "homeCard");
		}
		// setupFitbitFirst Card's buttons
		if (source == btnFitbitAccountSetup2){
			cardLayout.show(card, "fitbitSetupCard");
		}
		// general buttons
		if (source == btnBack) {
			cardLayout.show(card, "homeCard");
		}
		if (source == btnBack1) {
			cardLayout.show(card, "fitbitSetupCard");
		}
		if (source == btnBack2) {
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

		// * homeCard logo *
		JLabel label = new JLabel("");
		Image logo = new ImageIcon(main.class.getResource("/logo1.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(135, 6, 174, 148);
		homeCard.add(label);
		
		// * homeCard Buttons *
		btnFitbitAccountSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitAccountSetup.setBounds(135, 166, 178, 61);
		homeCard.add(btnFitbitAccountSetup);
		btnFitbitAccountSetup.addActionListener(AL);

		btnAmbientInterface.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAmbientInterface.setBounds(135, 239, 178, 61);
		homeCard.add(btnAmbientInterface);
		btnAmbientInterface.addActionListener(AL);
		

		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnQuit.setBounds(135, 312, 178, 61);
		homeCard.add(btnQuit);
		btnQuit.addActionListener(AL);
		
		
		/* Remove AmbientDevie On/Off button
		JLabel lblAmbientDevice = new JLabel("Ambient Device");
		lblAmbientDevice.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmbientDevice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmbientDevice.setBounds(135, 385, 178, 29);
		homeCard.add(lblAmbientDevice);
		
		// Need to figure out how to add action to the radio button!!! ***
		JRadioButton rdbtnOn = new JRadioButton("On");
		rdbtnOn.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOn.setSelected(true);
		rdbtnOn.setBounds(87, 409, 86, 23);
		homeCard.add(rdbtnOn);

		JRadioButton rdbtnOff = new JRadioButton("Off");
		rdbtnOff.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOff.setBounds(260, 409, 86, 23);
		homeCard.add(rdbtnOff);

		ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnOn);
		bG.add(rdbtnOff);
		 */
		
		// *** Create setUpFitbitFirst Card ***
		JPanel setupFitbitFirst = new JPanel();
		setupFitbitFirst.setLayout(null); // set card layout
		setupFitbitFirst.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JTextArea textArea2 = new JTextArea(
				"You need to setup your Fitbit account first.\n"
				+ "Press the button to get to the Fitbit Setup page.");
		textArea2.setBackground(SystemColor.control);
		textArea2.setEditable(false);
		textArea2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea2.setBounds(70, 100, 309, 64);
		setupFitbitFirst.add(textArea2);
		
		// setupFitbitFirst buttons
		btnFitbitAccountSetup2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitAccountSetup2.setBounds(135, 166, 178, 61);
		setupFitbitFirst.add(btnFitbitAccountSetup2);
		btnFitbitAccountSetup2.addActionListener(AL);
		
		
		// *** Create fitbitSetup Card ***
		JPanel fitbitSetupCard = new JPanel();
		fitbitSetupCard.setLayout(null); // set card layout
		fitbitSetupCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// * fitbitSetupCard's buttons *
		btnFitbitSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitSetup.setBounds(74, 234, 288, 50);
		fitbitSetupCard.add(btnFitbitSetup);
		btnFitbitSetup.addActionListener(AL);
		
		btnBack.setBackground(SystemColor.control);
		Image backIcon = new ImageIcon(main.class.getResource("/backIcon_50x50.png")).getImage();
		btnBack.setIcon(new ImageIcon(backIcon));
		btnBack.setBounds(0, 0, 67, 58);
		fitbitSetupCard.add(btnBack);
		btnBack.addActionListener(AL);

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
		
		
		
		// *** Create fitbitVerification Card ***
		JPanel fitbitVerificationCard = new JPanel(); // make cardHome JPanel
		fitbitVerificationCard.setLayout(null);
		fitbitVerificationCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		btnBack1.setBackground(SystemColor.control);
		Image backIcon1 = new ImageIcon(main.class.getResource("/backIcon_50x50.png")).getImage();
		btnBack1.setIcon(new ImageIcon(backIcon1));
		btnBack1.setBounds(0, 0, 67, 58);
		fitbitVerificationCard.add(btnBack1);
		btnBack1.addActionListener(AL);
		
		// initialize texfield before main so that it can be used outside of main
		textFieldVerification.setToolTipText("Past verification code here");
		textFieldVerification.setBounds(79, 278, 291, 37);
		fitbitVerificationCard.add(textFieldVerification);
		textFieldVerification.setColumns(10);
		
		// ***THIS TEXTPANE SHOULD PROBALBY HAVE CENTERED TEXT. NEED TO FIGURE OUT HOW!
		JTextPane txtpnVerInstructions = new JTextPane();
		txtpnVerInstructions.setBackground(SystemColor.control);
		txtpnVerInstructions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnVerInstructions.setText("You will be directed to the Fitbit authorization page"
				+ " in your web browser. Login and obtain your verification code."
				+ " Copy-and-paste your code into the text field below.");
		txtpnVerInstructions.setBounds(79, 158, 291, 112);
		fitbitVerificationCard.add(txtpnVerInstructions);
		
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(136, 334, 178, 61);
		fitbitVerificationCard.add(btnSubmit);
		btnSubmit.addActionListener(AL);
		
		JLabel lblFit1 = new JLabel("Fitbit Verification");
		lblFit1.setBackground(Color.WHITE);
		lblFit1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFit1.setBounds(74, 11, 120, 35);
		fitbitVerificationCard.add(lblFit1);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.GRAY);
		panel1.setBounds(66, 0, 368, 58);
		fitbitVerificationCard.add(panel1);
		
		
		// *** Create AmbientInterface Card ***
		JPanel ambientInterfaceCard = new JPanel(); // make cardHome JPanel
		ambientInterfaceCard.setLayout(null); // set card layout
		ambientInterfaceCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		ambientInterfaceCard.setBackground(Color.black);
		

		//ambientImage = AmbientInterface.runInterface(90);
		// * ambient image *
		//JLabel label2 = new JLabel("");
		//Adjust so that it selects the image that corresponds to how much of goal is completed. 
		
		
		label2.setVerticalAlignment(JLabel.BOTTOM);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setBounds(0, 0, 450, 469);
		ambientInterfaceCard.add(label2);
		

		int timerTimeInMilliSeconds = 1000*5;
	    javax.swing.Timer timer = new javax.swing.Timer(timerTimeInMilliSeconds, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (percentage != ""){
	        		// System.out.println("percentage is set"); //used for testing
	        		// DataDriver.timer();
	        		String ambientImage = "/fire/fire" + percentage + ".jpeg"; 
		        	// System.out.println(ambientImage); // used for testing
		        	Image logo2 = new ImageIcon(main.class.getResource(ambientImage)).getImage();
		            label2.setIcon(new ImageIcon(logo2));
	        	}
	        	
	        	// Used for testing
	        	// System.out.println("TIMER WORKING");
	        }
	    });
		         
	    timer.start();    

		
	          
		
		// Back button for Ambience Interface
		btnBack2.setBackground(SystemColor.control);
		Image backIcon2 = new ImageIcon(main.class.getResource("/backIcon1.png")).getImage();
		btnBack2.setIcon(new ImageIcon(backIcon2));
		btnBack2.setBounds(0, 0, 50, 50);
		ambientInterfaceCard.add(btnBack2);
		btnBack2.addActionListener(AL);

		// Add cards to panel
		cardLayout.show(card, "homeCard"); // set frm parameters
		card.add("homeCard", homeCard);
		card.add("setupFitbitFirst", setupFitbitFirst);
		card.add("fitbitSetupCard", fitbitSetupCard);
		card.add("ambientInterfaceCard", ambientInterfaceCard);
		card.add("fitbitVerificationCard", fitbitVerificationCard);		
		card.add("ambientInterfaceCard", ambientInterfaceCard);

		contentPane.add(card); // set the contentPane to add static card panel

		// JFrame settings
		frm.setVisible(true);
		frm.setResizable(false);
		frm.setBounds(Variables.bound1, Variables.bound2, Variables.bound3,
				Variables.bound4);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when X is
															// pressed, program
															// stops running
	}
}
