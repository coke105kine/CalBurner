
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;


public class FitbitSetup extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FitbitSetup frame = new FitbitSetup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FitbitSetup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Variables.bound1,Variables.bound2,Variables.bound3,Variables.bound4);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Set up your Fitbit device");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(74, 234, 288, 50);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea("Clicking this button will open your web browser.\n"
				+ "Make sure you have an internet connection.");
		textArea.setBackground(SystemColor.control);
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(74, 308, 309, 64);
		contentPane.add(textArea);
		
		JButton backBtn = new JButton("");
		backBtn.setAction(action);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backBtn.setBackground(SystemColor.control);
		Image backIcon = new ImageIcon(this.getClass().getResource("/backIcon_50x50.png")).getImage();
		backBtn.setIcon(new ImageIcon(backIcon));
		backBtn.setBounds(0, 0, 67, 58);
		contentPane.add(backBtn);
		
		JLabel lblFit = new JLabel("Fitbit Setup");
		lblFit.setBackground(Color.WHITE);
		lblFit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFit.setBounds(74, 11, 80, 35);
		contentPane.add(lblFit);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(66, 0, 368, 58);
		contentPane.add(panel);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
