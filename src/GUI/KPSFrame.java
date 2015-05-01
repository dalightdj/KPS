package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.MainFrame;

public class KPSFrame extends JFrame {

	
	private ButtonPanel buttonsPanel;
	private JPanel guiInformation;
	
	private BufferedImage frameIcon;

	public KPSFrame() {
		
		/*Initialize the layout and the insets*/
		this.setLayout(new BorderLayout());
		this.setTitle("Kelburn Postal Serivce");

		/*Set the Frames icon*/
		frameIcon = MainFrame.load(MainFrame.ASSETS + "frameIcon2.png");
		ImageIcon icon = new ImageIcon(frameIcon); 
		this.setIconImage(icon.getImage());
		
		buttonsPanel = new ButtonPanel(this);
 		this.add(buttonsPanel, BorderLayout.WEST);
		
 		guiInformation = new JPanel();
 		guiInformation.setBorder(BorderFactory.createLineBorder(Color.red));
 		this.add(guiInformation,BorderLayout.CENTER);
 				
		this.setPreferredSize(new Dimension(1000,800));
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JOptionPane.showMessageDialog(this, "Welcome to Kelburn Postal Service!", "Welcome", JOptionPane.INFORMATION_MESSAGE); //Welcome the user (maybe remove this as it can be annoying)
	}
	
	/**
	 * This is just a test method to run it directly
	 * @param args
	 */
	public static void main(String[] args) {
		new KPSFrame();
	}
}
