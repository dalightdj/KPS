package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

	/*Labels for the GUI information*/
	private JLabel totalRevenue;
	private JLabel totalExpenditure;
	private JLabel totalNumberOfEvents;
	private JLabel amountOfMail;
	private JLabel averageDeliveryTime;
	private JLabel criticalRoutes;

	/*Labels that contain the values*/
	private JLabel totalRevenueValue;
	private JLabel totalExpenditureValue;
	private JLabel totalNumberOfEventsValue;
	private JLabel amountOfMailValue;
	private JLabel averageDeliveryTimeValue;
	private JLabel criticalRoutesValue;

	
	
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
 		guiInformation.setLayout(new GridBagLayout());
 		guiInformation.setBorder(BorderFactory.createLineBorder(Color.red));
 		this.add(guiInformation,BorderLayout.CENTER);
 			
 		setupGuiInformationLabels();
 		
		this.setPreferredSize(new Dimension(700,600));
		buttonsPanel.setPreferredSize(new Dimension(220,this.getHeight()));
		this.setResizable(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JOptionPane.showMessageDialog(this, "Welcome to Kelburn Postal Service!", "Welcome", JOptionPane.INFORMATION_MESSAGE); //Welcome the user (maybe remove this as it can be annoying)
	}
	
	private void setupGuiInformationLabels() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,-50,30,50); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
	
		
		totalRevenue = new JLabel("<html><b><font size = 4 color=White>Total Revenue:</b></html>");
		
		totalExpenditure = new JLabel("<html><b><font size = 4 color=White>Total Expenditure:</b></html>");

		totalNumberOfEvents = new JLabel("<html><b><font size = 4 color=White>Total Number of Events:</b></html>");

		amountOfMail = new JLabel("<html><b><font size = 4 color=White>Amount of Mail:</b></html>");
		
		averageDeliveryTime = new JLabel("<html><b><font size = 4 color=White>Average Delivery Time:</b><</html>");
		
		criticalRoutes = new JLabel("<html><b><font size = 4 color=White>Critical Routes:</b></html>");
		//criticalRoutes.setBorder(BorderFactory.createLineBorder(Color.red));			
		
		c.weightx = 1.0;
		
		c.gridx = 0;
		c.gridy = 0;
		guiInformation.add(totalRevenue,c);
		
		c.gridx = 0;
		c.gridy = 1;
		guiInformation.add(totalExpenditure,c);
		
		c.gridx = 0;
		c.gridy = 2;
		guiInformation.add(totalNumberOfEvents,c);
		
		c.gridx = 0;
		c.gridy = 3;
		guiInformation.add(amountOfMail,c);
		
		c.gridx = 0;
		c.gridy = 4;
		guiInformation.add(averageDeliveryTime,c);
		
		c.gridx = 0;
		c.gridy = 5;
		guiInformation.add(criticalRoutes,c);
				
	}

	/**
	 * This is just a test method to run it directly
	 * @param args
	 */
	public static void main(String[] args) {
		new KPSFrame();
	}
}
