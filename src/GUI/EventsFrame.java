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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logger.Event;
import Logger.MDEvent;
import Logger.TCUEvent;
import Logger.TDEvent;
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
	private JLabel title;
	private JLabel typeTitle;

	/*Information for the labels*/
	private String dayString = "";
	private String originString ="";
	private String destinationString = "";
	private String priorityString = "";
	private String weightString = "";
	private String volumeString = "";
	
	private KPS kpsObject;
	
	private ArrayList<Event> events;
	private int eventCount = 0; //keep track of which event we're on (in the list events)
	
	public EventsFrame(KPSFrame frame) {
		
		this.frame = frame;
		this.kpsObject = frame.getKpsObject();
        this.setLocationRelativeTo(frame); //sets position relative to the frame 

		
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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
 		
	}

	private void updateGUI() {
		this.events = kpsObject.getEvents(); //get latest events every time it this GUI updates
		
		
		if(events.get(eventCount) instanceof MDEvent) {
			
			MDEvent currentEvent =  (MDEvent) events.get(eventCount);
			dayString = currentEvent.getDate();
			originString = currentEvent.getOrigin();
			destinationString = currentEvent.getDestination();
			priorityString = currentEvent.getPriority().toString();
			weightString = String.valueOf(currentEvent.getWeight());
			volumeString = String.valueOf(currentEvent.getVolume());
			setupMDLabels();
		}
		else if(events.get(eventCount) instanceof TCUEvent) {
			TCUEvent currentEvent = (TCUEvent) events.get(eventCount);
		}
		if(events.get(eventCount) instanceof TDEvent) {
			TDEvent currentEvent = (TDEvent) events.get(eventCount);
		}
		
		//TODO: retrieve values using kpsObject (initialised in constructor) from events data xml or what ever the methods are in KPS class

		//typeTitle.setText("<html><b><u><font size = 5 color=BLACK>Event Number: <font color = 'yellow'> asd</b></u></html>");

		title.setText("<html><b><font size = 5 color=BLACK>Event Number: <font color = 'yellow'> "+eventCount+"</b></html>");
		typeTitle.setText("<html><b><font size = 5 color=BLACK>Event Type:<font color = yellow size = 5> asd</b></html>");

		//TODO: Comment this out once the values have been retrieved above

	}

	private void setupMDLabels() {
		day.setText("<html><b><font size = 5 color=White>Date :  <font color = 'yellow'> "+dayString+"</b></html>");
		origin.setText("<html><b><font size = 5 color=White>Origin :  <font color = 'yellow'> "+originString+"</b></html>");
		destination.setText("<html><b><font size = 5 color=White>Destination : <font color = 'yellow'> "+destinationString+"</b></html>");
		priority.setText("<html><b><font size = 5 color=White>Priority : <font color = 'yellow'> "+priorityString+"</b></html>");
		weight.setText("<html><b><font size = 5 color=White>Weight : <font color = 'yellow'> "+weightString+"</b></html>");
		volume.setText("<html><b><font size = 5 color=White>Volume : <font color = 'yellow'> "+volumeString+"</b></html>");
	}

	private void setupLabels() {
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(new GridBagLayout());
		c.insets = new Insets(0,10,10,0); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
		
		/*Initialize all labels*/
		origin = new JLabel();
		destination = new JLabel();
		priority = new JLabel();
		day = new JLabel();
		weight = new JLabel();
		volume = new JLabel();
		title = new JLabel();
		typeTitle = new JLabel();

		c.weightx = 1.0;
		
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(day,c);

		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(origin,c);

		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(destination,c);

		c.gridx = 0;
		c.gridy = 5;
		mainPanel.add(priority,c);

		c.gridx = 0;
		c.gridy = 6;
		mainPanel.add(weight,c);
		
		c.gridx = 0;
		c.gridy = 7;
		mainPanel.add(volume,c);
		
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(title,c);
		
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(typeTitle,c);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nextEvent) {
			if(++eventCount == events.size()) { //make sure we don't increase past the size of events list
				//System.out.println("attempting to go out of events list size"); //testing
				return;
			}
			else {
				eventCount++;
				updateGUI();
			}
		}
		else if(e.getSource() == prevEvent) {
			if(eventCount ==0) { //check if eventCount is 0 so that we don't get out of bounds exception
				return;
			}
			else {
				//System.out.println("attempting to go below events list size"); //testing
				eventCount--;
				updateGUI();
			}
		}
	}
	
}
