import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardLayout_sourceCodePractice implements ActionListener{ //make class cardLayout implementing ActionListener
	static CardLayout cardLayout; //make static CardLayout
	static JPanel card = new JPanel(); // make a static CardLayout
	//static buttons
	static JButton next = new JButton("Next"); 
	static JButton quit = new JButton("Quit");
	static JButton back = new JButton("Back");
	
	public void actionPerformed(ActionEvent e){ //make a void actionPerformed
		Object source = e.getSource();
		if(source == next){
			cardLayout.show(card, "Card N");
		}
		if(source == back){
			cardLayout.show(card, "Card H");
		}
		if(source==quit){
			System.exit(0);
		}
	}
	
	public static void main(String[] args){
		JFrame frm = new JFrame(); //make new frame
		JPanel contentPane = (JPanel)frm.getContentPane(); //new conentPane panel type casing frm.getContentPane() with JPanel
		card.setLayout(cardLayout = new CardLayout()); //set layout of static panel to cardLayout name = to new CardLayout()
		ActionListener AL = new CardLayout_sourceCodePractice(); //present an ActionListener AL
		JPanel cardH = new JPanel(); //make cardHome JPanel
		cardH.setLayout(new GridLayout(2,1)); //set card layout
		JPanel labelP = new JPanel();
		JLabel label = new JLabel("This is card 1");
		labelP.add(label);
		cardH.add(labelP);
		JPanel bP = new JPanel(); // add button panel and button
		bP.add(next); bP.add(quit);
		next.addActionListener(AL);
		quit.addActionListener(AL);
		cardH.add(bP);
		
		JPanel cardN = new JPanel(); // make other necessary cards
		cardN.setLayout(new GridLayout(2,1));
		JPanel labelP2 = new JPanel(); //add stuff
		JLabel label2 = new JLabel("Next Card");
		labelP2.add(label2);
		cardN.add(labelP2);
		JPanel bP2 = new JPanel();
		bP2.add(back);
		back.addActionListener(AL);
		cardN.add(bP2);
		
		cardLayout.show(card, "Card H"); //set frm parameters
		//add all cards to static panel
		card.add("Card H", cardH); 
		card.add("Card N", cardN);
		contentPane.add(card); //set the contentPane to add static card panel
				
		frm.setVisible(true);
		frm.setResizable(true);
		frm.setSize(400, 100);
		frm.setLocation(580,380);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when X is pressed, program stops running
	}
	
}
