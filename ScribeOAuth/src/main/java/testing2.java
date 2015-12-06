import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class testing2 extends JPanel {
	public testing2() {
		
		JLabel label = new JLabel();
		label.setBounds(135, 6, 174, 148);
		add(label);
		
		String percentage = "100";
		String ambientImage = "/fire/bfire" + percentage + ".jpeg"; 
		ambientImage = "congrats.jpg";
		Image logo2 = new ImageIcon(main.class.getResource(ambientImage)).getImage();
        label.setIcon(new ImageIcon(logo2));
	}


         


	}

