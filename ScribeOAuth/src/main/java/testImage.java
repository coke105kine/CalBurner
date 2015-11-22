import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class testImage extends JPanel {

	/**
	 * Create the panel.
	 */
	public testImage() {
		
		JLabel lblNewLabel = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(logo));
		add(lblNewLabel);
		

	}

}
