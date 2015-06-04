package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logic.KPS;
import Main.MainFrame;

public class EventsFrame extends JFrame implements ActionListener {
	
	private BufferedImage frameIcon;

	private KPSFrame frame;
	
	private JPanel mainPanel;
	private JPanel buttons;
	
	private JButton nextEvent;
	private JButton prevEvent;
	
	/*The labels for information*/
	private JLabel origin;
	private JLabel destination;
	private JLabel priority;
	private JLabel day;
	private JLabel weight;
	private JLabel volume;

	private KPS kpsObject;
	
	public EventsFrame(KPSFrame frame) {
		
		this.frame = frame;
		this.kpsObject = frame.getKpsObject();
		
		this.setLayout(new BorderLayout());
		this.setTitle("Kelburn Postal Serivce Event History");
 		this.setPreferredSize(new Dimension(600,400));

		
		/*Set the Frames icon*/
		frameIcon = MainFrame.load(MainFrame.ASSETS + "frameIcon2.png");
		ImageIcon icon = new ImageIcon(frameIcon);
		this.setIconImage(icon.getImage());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
 		this.add(mainPanel,BorderLayout.CENTER);
		
		buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
		buttons.setBorder(BorderFactory.createLineBorder(Color.black));
 		this.add(buttons,BorderLayout.WEST);
 		
 		/*Set up the buttons for the buttons panel*/
 		setupButtons();
		buttons.setPreferredSize(new Dimension(120,this.getHeight()));

		/*Set up the labels for the event information*/
		setupLabels();
		updateGUI();
		
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
 		
	}

	private void updateGUI() {


		//TODO: retrieve values using kpsObject (initialised in constructor) from events data xml or what ever the methods are in KPS class
		//String dayString = String.valueOf(totalRevenueDouble);
		//String originString = String.valueOf(totalExpenditureDouble);
		//String destinationString = String.valueOf(totalNumberOfEventsDouble);
		//String priorityString = String.valueOf(totalAmountOfMailDouble);
		//String weightString = String.valueOf(totalAmountOfMailDouble);
		//String volumeString = String.valueOf(totalAmountOfMailDouble);

		//TODO: Comment this out once the values have been retrieved above
		//day.setText("<html><b><font size = 5 color=White>Date :  <font color = 'yellow'> "+dayString+"</b></html>");
		//origin.setText("<html><b><font size = 5 color=White>Origin :  <font color = 'yellow'>$ "+originString+"</b></html>");
		//destination.setText("<html><b><font size = 5 color=White>Destination : <font color = 'yellow'>$ "+destinationString+"</b></html>");
		//priority.setText("<html><b><font size = 5 color=White>Priority : <font color = 'yellow'> "+priorityString+"</b></html>");
		//weight.setText("<html><b><font size = 5 color=White>Weight : <font color = 'yellow'> "+weightString+"</b></html>");
		//volume.setText("<html><b><font size = 5 color=White>Volume : <font color = 'yellow'> "+volumeString+"</b></html>");

		//TODO:REMOVE all of this once the two top TODO's are completed
		day.setText("<html><b><font size = 5 color=White>Date :  </b></html>");
		origin.setText("<html><b><font size = 5 color=White>Origin :  </b></html>");
		destination.setText("<html><b><font size = 5 color=White>Destination :</b></html>");
		priority.setText("<html><b><font size = 5 color=White>Priority : </b></html>");
		weight.setText("<html><b><font size = 5 color=White>Weight : </b></html>");
		volume.setText("<html><b><font size = 5 color=White>Volume : </b></html>");
		
	}

	private void setupLabels() {
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(new GridBagLayout());
		c.insets = new Insets(0,10,30,0); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
		
		origin = new JLabel();
		destination = new JLabel();
		priority = new JLabel();
		day = new JLabel();
		weight = new JLabel();
		volume = new JLabel();

		c.weightx = 1.0;

		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(day,c);

		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(origin,c);

		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(destination,c);

		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(priority,c);

		c.gridx = 0;
		c.gridy = 5;
		mainPanel.add(weight,c);
		
		c.gridx = 0;
		c.gridy = 6;
		mainPanel.add(volume,c);
		
	}

	private void setupButtons() {
		/*Initialize the layout and the insets*/
		buttons.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
		
		nextEvent = new JButton("Next Event");
		c.gridx = 0;
		c.gridy = 0;
		buttons.add(nextEvent,c);
		nextEvent.addActionListener(this);
		
		prevEvent = new JButton("Previous Event");
		c.gridx = 0;
		c.gridy = 1;
		buttons.add(prevEvent,c);
		prevEvent.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}
