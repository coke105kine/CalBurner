import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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
	static JButton btnBack3 = new JButton("");
	
	
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
		if (source == btnBack3) {
			cardLayout.show(card, "setupFitbitFirst");
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
		
		//Logo Colors:
		//light blue: (133, 204, 241)
		//dark blue: (19, 96, 171)

		// *** Create Home Card ***
		JPanel homeCard = new JPanel(); // make cardHome JPanel
		homeCard.setLayout(null); // set card layout
		homeCard.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		homeCard.setBackground(Color.white);

		// * homeCard logo *
		JLabel label = new JLabel("");
		Image logo = new ImageIcon(main.class.getResource("/logo1.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(135, 6, 174, 148);
		homeCard.add(label);
		
		// * homeCard Buttons *
		btnFitbitAccountSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitAccountSetup.setBounds(135, 166, 178, 61);
		btnFitbitAccountSetup.setBackground(Color.white);
		btnFitbitAccountSetup.setOpaque(true);
		btnFitbitAccountSetup.setBorder(new LineBorder(new Color(133, 204, 241), 2));
		homeCard.add(btnFitbitAccountSetup);
		btnFitbitAccountSetup.addActionListener(AL);

		btnAmbientInterface.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAmbientInterface.setBounds(135, 239, 178, 61);
		btnAmbientInterface.setBackground(Color.white);
		btnAmbientInterface.setOpaque(true);
		btnAmbientInterface.setBorder(new LineBorder(new Color(133, 204, 241), 2));
		homeCard.add(btnAmbientInterface);
		btnAmbientInterface.addActionListener(AL);
		

		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnQuit.setBounds(135, 312, 178, 61);
		btnQuit.setBackground(Color.white);
		btnQuit.setOpaque(true);
		btnQuit.setBorder(new LineBorder(new Color(133, 204, 241), 2));
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
		
		// *** Create setupFitbitFirst Card ***
		JPanel setupFitbitFirst = new JPanel();
		setupFitbitFirst.setLayout(null); // set card layout
		setupFitbitFirst.setBorder(new EmptyBorder(5, 5, 5, 5));
		setupFitbitFirst.setBackground(Color.white);
		
		// * setupFitbitFirst Card logo *
		JLabel logolabel = new JLabel("");
		Image logo1 = new ImageIcon(main.class.getResource("/logo1.png")).getImage();
		logolabel.setIcon(new ImageIcon(logo1));
		logolabel.setBounds(135, 80, 174, 148);
		setupFitbitFirst.add(logolabel);
		
		JTextPane textArea2 = new JTextPane();
		textArea2.setText("You need to setup your Fitbit account first. "
				+ "Press the button to get to the Fitbit Setup page.");
		textArea2.setBackground(SystemColor.control);
		textArea2.setEditable(false);
		textArea2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea2.setBounds(70, 260, 320, 45);
		SimpleAttributeSet attribs = new SimpleAttributeSet();  
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
		textArea2.setParagraphAttributes(attribs,true);
		setupFitbitFirst.add(textArea2);
		
		// setupFitbitFirst buttons
		btnFitbitAccountSetup2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitAccountSetup2.setBounds(135, 330, 178, 61);
		btnFitbitAccountSetup2.setBackground(Color.white);
		btnFitbitAccountSetup2.setOpaque(true);
		btnFitbitAccountSetup2.setBorder(new LineBorder(new Color(133, 204, 241), 2));
		setupFitbitFirst.add(btnFitbitAccountSetup2);
		btnFitbitAccountSetup2.addActionListener(AL);
		
		btnBack.setBackground(SystemColor.control);
		Image backIcon = new ImageIcon(main.class.getResource("/backIcon_50x50.png")).getImage();
		btnBack.setIcon(new ImageIcon(backIcon));
		btnBack.setBounds(0, 0, 67, 58);
		setupFitbitFirst.add(btnBack);
		btnBack.addActionListener(AL);
		
		JLabel lblFit2 = new JLabel("Setup Fitbit First");
		lblFit2.setBackground(Color.WHITE);
		lblFit2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFit2.setBounds(74, 11, 120, 35);
		setupFitbitFirst.add(lblFit2);

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(133, 204, 241));
		panel2.setBounds(66, 0, 368, 58);
		setupFitbitFirst.add(panel2);
		
		
		// *** Create fitbitSetup Card ***
		JPanel fitbitSetupCard = new JPanel();
		fitbitSetupCard.setLayout(null); // set card layout
		fitbitSetupCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		fitbitSetupCard.setBackground(Color.white);
		
		// * fitbitSetupCard's logo *
		JLabel logolabel1 = new JLabel("");
		logolabel1.setIcon(new ImageIcon(logo));
		logolabel1.setBounds(135, 80, 174, 148);
		fitbitSetupCard.add(logolabel1);
		
		// * fitbitSetupCard's buttons *
		btnFitbitSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitbitSetup.setBounds(80, 290, 288, 50);
		btnFitbitSetup.setBackground(Color.white);
		btnFitbitSetup.setOpaque(true);
		btnFitbitSetup.setBorder(new LineBorder(new Color(133, 204, 241), 2));
		btnFitbitSetup.setVerticalAlignment(JButton.CENTER);
		fitbitSetupCard.add(btnFitbitSetup);
		btnFitbitSetup.addActionListener(AL);
		
		btnBack3.setBackground(SystemColor.control);
		Image backIcon3 = new ImageIcon(main.class.getResource("/backIcon_50x50.png")).getImage();
		btnBack3.setIcon(new ImageIcon(backIcon3));
		btnBack3.setBounds(0, 0, 67, 58);
		fitbitSetupCard.add(btnBack3);
		btnBack3.addActionListener(AL);

		JTextPane textArea = new JTextPane();
		textArea.setText("Clicking this button will open your web browser."
				+ " Make sure you have an internet connection.");
		textArea.setBackground(SystemColor.control);
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(70, 350, 318, 44); 
		textArea.setParagraphAttributes(attribs,true);
		fitbitSetupCard.add(textArea);

		JLabel lblFit = new JLabel("Fitbit Setup");
		lblFit.setBackground(Color.WHITE);
		lblFit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFit.setBounds(74, 11, 80, 35);
		fitbitSetupCard.add(lblFit);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(133, 204, 241));
		panel.setBounds(66, 0, 368, 58);
		fitbitSetupCard.add(panel);
		
		// *** Create fitbitVerification Card ***
		JPanel fitbitVerificationCard = new JPanel(); // make cardHome JPanel
		fitbitVerificationCard.setLayout(null);
		fitbitVerificationCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		fitbitVerificationCard.setBackground(Color.white);
		
		// * fitbitVerificationCard logo *
		JLabel logolabel2 = new JLabel("");
		logolabel2.setIcon(new ImageIcon(logo));
		logolabel2.setBounds(135, 80, 174, 148);
		fitbitVerificationCard.add(logolabel2);
		
		btnBack1.setBackground(SystemColor.control);
		Image backIcon1 = new ImageIcon(main.class.getResource("/backIcon_50x50.png")).getImage();
		btnBack1.setIcon(new ImageIcon(backIcon1));
		btnBack1.setBounds(0, 0, 67, 58);
		fitbitVerificationCard.add(btnBack1);
		btnBack1.addActionListener(AL);
		
		// initialize textfield before main so that it can be used outside of main
		textFieldVerification.setToolTipText("Paste verification code here");
		textFieldVerification.setBounds(79, 335, 291, 37);
		textFieldVerification.setBorder(new LineBorder(new Color(133, 204, 241), 2));
		fitbitVerificationCard.add(textFieldVerification);
		textFieldVerification.setColumns(10);
		
		// ***THIS TEXTPANE SHOULD PROBALBY HAVE CENTERED TEXT. NEED TO FIGURE OUT HOW!
		JTextPane txtpnVerInstructions = new JTextPane();
		txtpnVerInstructions.setBackground(SystemColor.control);
		txtpnVerInstructions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnVerInstructions.setText("You will be directed to the Fitbit authorization page"
				+ " in your web browser. Login and obtain your verification code."
				+ " Copy-and-paste your code into the text field below.");
		txtpnVerInstructions.setBounds(75, 250, 315, 80);
		txtpnVerInstructions.setEditable(false);
		txtpnVerInstructions.setParagraphAttributes(attribs,true);
		fitbitVerificationCard.add(txtpnVerInstructions);
		
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(136, 375, 178, 61);
		btnSubmit.setBackground(Color.white);
		btnSubmit.setOpaque(true);
		btnSubmit.setBorder(new LineBorder(new Color(133, 204, 241), 2));
		fitbitVerificationCard.add(btnSubmit);
		btnSubmit.addActionListener(AL);
		
		JLabel lblFit1 = new JLabel("Fitbit Verification");
		lblFit1.setBackground(Color.WHITE);
		lblFit1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFit1.setBounds(74, 11, 120, 35);
		fitbitVerificationCard.add(lblFit1);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(133, 204, 241));
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
