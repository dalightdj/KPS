package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logic.KPS;
import Main.MainFrame;

public class KPSFrame extends JFrame {


	private ButtonPanel buttonsPanel;
	private JPanel guiInformation;

	private BufferedImage frameIcon;

	/*Labels for the GUI information*/
	private JLabel title;
	private JLabel totalRevenue;
	private JLabel totalExpenditure;
	private JLabel netProfit;
	private JLabel totalNumberOfEvents;
	private JLabel amountOfMail;
	private JLabel criticalRoutes;

	/*Labels that contain the values*/
	private JLabel totalRevenueValue;
	private JLabel totalExpenditureValue;
	private JLabel totalNumberOfEventsValue;
	private JLabel amountOfMailValue;
	private JLabel criticalRoutesValue;

	private KPS kpsObject;

	private double totalRevenueDouble;
	private double totalExpenditureDouble;
	private int totalNumberOfEventsDouble;
	private int totalAmountOfMailDouble;

	private boolean isManager;

	public KPSFrame(boolean isManager) {

		/*Center this frame on monitor*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2-200, dim.height/2-this.getSize().height/2-200);

		this.isManager = isManager;
		/*Create new KPS Object and update GUI*/
		kpsObject = new KPS();

		/*Initialize the layout and the insets*/
		this.setLayout(new BorderLayout());
		this.setTitle("Kelburn Postal Serivce");

		/*Set the Frames icon*/
		frameIcon = MainFrame.load(MainFrame.ASSETS + "frameIcon2.png");
		ImageIcon icon = new ImageIcon(frameIcon);
		this.setIconImage(icon.getImage());

		buttonsPanel = new ButtonPanel(this, kpsObject);
 		this.add(buttonsPanel, BorderLayout.WEST);

 		guiInformation = new JPanel();
 		guiInformation.setBackground(Color.BLACK);
 		guiInformation.setLayout(new GridBagLayout());
 		guiInformation.setBorder(BorderFactory.createLoweredBevelBorder());
 		this.add(guiInformation,BorderLayout.CENTER);

 		setupGuiInformationLabels(); //initialise the labels
 		updateGUI(); //update the labels

		this.setPreferredSize(new Dimension(800,400));
		buttonsPanel.setPreferredSize(new Dimension(220,this.getHeight()));
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	private void setupGuiInformationLabels() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,10,30,0); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;

		title = new JLabel("<html><b><u><font size = 5 color=WHITE>KELBURN POSTAL SERVICE</b></u></html>");

		totalRevenue = new JLabel();
		totalExpenditure = new JLabel();
		netProfit = new JLabel();
		totalNumberOfEvents = new JLabel();
		amountOfMail = new JLabel();
		criticalRoutes = new JLabel();

		//criticalRoutes.setBorder(BorderFactory.createLineBorder(Color.red));

		c.weightx = 1.0;


		c.gridx = 0;
		c.gridy = 1;
		guiInformation.add(totalRevenue,c);

		c.gridx = 0;
		c.gridy = 2;
		guiInformation.add(totalExpenditure,c);

		c.gridx = 0;
		c.gridy = 3;
		guiInformation.add(netProfit,c);
		
		c.gridx = 0;
		c.gridy = 4;
		guiInformation.add(totalNumberOfEvents,c);

		c.gridx = 0;
		c.gridy = 5;
		guiInformation.add(amountOfMail,c);

		c.gridx = 0;
		c.gridy = 6;
		guiInformation.add(criticalRoutes,c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,-140,30,70); //top, left, bottom, right padding (in that order)
		guiInformation.add(title,c);

	}





	public void updateGUI() {
		totalRevenueDouble = kpsObject.getRevenue();
		totalExpenditureDouble = kpsObject.getExpenses();
		totalNumberOfEventsDouble = kpsObject.getNoEvents();
		totalAmountOfMailDouble = kpsObject.getNoDeliveries();

		String revenueString = String.valueOf(totalRevenueDouble);
		String expenditureString = String.valueOf(totalExpenditureDouble);
		String numOfEventsString = String.valueOf(totalNumberOfEventsDouble);
		String numOfMailsString = String.valueOf(totalAmountOfMailDouble);
		String netProfitString = String.valueOf(totalRevenueDouble - totalExpenditureDouble);
		
		totalRevenue.setText("<html><b><font size = 5 color=White>Total Revenue:  <font color = 'yellow'>$ "+revenueString+"</b></html>");
		totalExpenditure.setText("<html><b><font size = 5 color=White>Total Expenditure: <font color = 'yellow'>$ "+expenditureString+"</b></html>");
		netProfit.setText("<html><b><font size = 5 color=White>Net Profit/(Loss): <font color = 'yellow'>$ "+netProfitString+"</b></html>");
		totalNumberOfEvents.setText("<html><b><font size = 5 color=White>Total Number of Events: <font color = 'yellow'> "+numOfEventsString+"</b></html>");
		amountOfMail.setText("<html><b><font size = 5 color=White>Amount of Mail: <font color = 'yellow'> "+numOfMailsString+"</b></html>");

			//criticalRoutes = new JLabel("<html><b><font size = 5 color=White>Critical Routes: <font color = 'yellow'>$ "+revenueString+"</b></html>");
	}



	public KPS getKpsObject() {
		return this.kpsObject;
	}

	public boolean getIsManager() {
		return this.isManager;
	}

	/**
	 * This is just a test method to run it directly
	 * @param args
	 */
	public static void main(String[] args) {
		new KPSFrame(true);
	}
}
