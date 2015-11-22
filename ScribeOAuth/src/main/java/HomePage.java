
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;


public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public HomePage() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Variables.bound1,Variables.bound2,Variables.bound3,Variables.bound4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAccountSettings = new JButton("Account Settings");
		btnAccountSettings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAccountSettings.setBounds(135, 113, 178, 61);
		btnAccountSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		
		JButton btnFitnessGoals = new JButton("Fitness Goals");
		btnFitnessGoals.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFitnessGoals.setBounds(135, 185, 178, 61);
		contentPane.add(btnFitnessGoals);
		contentPane.add(btnAccountSettings);
		
		JButton btnViewProgress = new JButton("View Progress");
		btnViewProgress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewProgress.setBounds(135, 257, 178, 61);
		contentPane.add(btnViewProgress);
		
		JRadioButton rdbtnOn = new JRadioButton("On");
		rdbtnOn.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOn.setSelected(true);
		rdbtnOn.setBounds(87, 382, 86, 23);
		contentPane.add(rdbtnOn);
		
		JRadioButton rdbtnOff = new JRadioButton("Off");
		rdbtnOff.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOff.setBounds(260, 382, 86, 23);
		contentPane.add(rdbtnOff);
		
		ButtonGroup bG = new ButtonGroup();
	     bG.add(rdbtnOn);
	     bG.add(rdbtnOff);
	     
	     JLabel lblAmbientDevice = new JLabel("Ambient Device");
	     lblAmbientDevice.setHorizontalAlignment(SwingConstants.CENTER);
	     lblAmbientDevice.setFont(new Font("Tahoma", Font.PLAIN, 15));
	     lblAmbientDevice.setBounds(135, 346, 178, 29);
	     contentPane.add(lblAmbientDevice);
		
		
	}
}
