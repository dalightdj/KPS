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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Logger.CPUEvent;
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
	private JLabel maxWeightLabel;
	private JLabel maxVolumeLabel;
	private JLabel freqLabel;
	private JLabel durationLabel;
	private JLabel companylabel;

	/*Information for the labels*/
	private String dayString = "";
	private String originString ="";
	private String destinationString = "";
	private String priorityString = "";
	private String weightString = "";
	private String volumeString = "";
	private String typeString = "";
	private String maxWeightString = "";
	private String maxVolumeString = "";
	private String freqString = "";
	private String durationString = "";
	private String companyString = "";



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
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setBackground(new Color(25,25,25));
 		this.add(mainPanel,BorderLayout.CENTER);

		buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
		buttons.setBorder(BorderFactory.createLoweredBevelBorder());
 		this.add(buttons,BorderLayout.WEST);

 		/*Set up the buttons for the buttons panel*/
 		setupButtons();
		buttons.setPreferredSize(new Dimension(150,this.getHeight()));


		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		/*Set up the labels for the event information*/
		setupLabels();
		updateGUI();

	}

	private void updateGUI() {
		this.events = kpsObject.getEvents(); //get latest events every time it this GUI updates

		if(events.size() == 0) {
			JOptionPane.showMessageDialog(this, "No Events to View!", "No Events", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if(events.size() != 0) {
			if(events.get(eventCount) instanceof MDEvent) {
				MDEvent currentEvent =  (MDEvent) events.get(eventCount);
				typeString = "Mail Delivery";
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
				typeString = "Tranport Cost Update";
				dayString = currentEvent.getDow().toString();
				originString = currentEvent.getOrigin();
				destinationString = currentEvent.getDestination();
				priorityString = currentEvent.getType().toString();
				weightString = String.valueOf(currentEvent.getWeightCost());
				volumeString = String.valueOf(currentEvent.getVolumeCost());
				maxWeightString = String.valueOf(currentEvent.getMaxWeight());
				maxVolumeString = String.valueOf(currentEvent.getMaxVolume());
				freqString = String.valueOf(currentEvent.getMaxVolume());
				durationString = String.valueOf(currentEvent.getDuration());
				setupTCULabels();
			}
			else if(events.get(eventCount) instanceof TDEvent) {
				TDEvent currentEvent = (TDEvent) events.get(eventCount);
				typeString = "Transport Discontinued";
				dayString = currentEvent.getDow().toString();
				originString = currentEvent.getOrigin();
				destinationString = currentEvent.getDestination();
				priorityString = currentEvent.getType().toString();
				companyString = currentEvent.getCompany();


				setupTDLabels();
			}
			else if(events.get(eventCount) instanceof CPUEvent) {
				CPUEvent currentEvent = (CPUEvent) events.get(eventCount);
				typeString = "Customer Price Update";
				dayString = currentEvent.getDow().toString();
				originString = currentEvent.getOrigin();
				destinationString = currentEvent.getDestination();
				priorityString = currentEvent.getPriority().toString();
				weightString = String.valueOf(currentEvent.getWeightPrice());
				volumeString = String.valueOf(currentEvent.getVolumePrice());
				setupCPULabels();
			}
		}


		int tempEventCount = eventCount + 1; //this is just for increasing 1 so first event doesn't show as "Event Number: 0"
		title.setText("<html><b><font size = 5 color=BLACK>Event Number: <font color = 'yellow'> "+tempEventCount+"</b></html>");
		typeTitle.setText("<html><b><font size = 5 color=BLACK>Event Type:<font color = yellow size = 5> "+typeString+"</b></html>");


	}

	private void setupTDLabels() {
		day.setText("<html><b><font size = 5 color=White>  Date :  <font color = 'yellow'> "+dayString+"</b></html>");
		origin.setText("<html><b><font size = 5 color=White>  Origin :  <font color = 'yellow'> "+originString+"</b></html>");
		destination.setText("<html><b><font size = 5 color=White>  Destination : <font color = 'yellow'> "+destinationString+"</b></html>");
		priority.setText("<html><b><font size = 5 color=White>  Priority : <font color = 'yellow'> "+priorityString+"</b></html>");
		companylabel.setText("<html><b><font size = 5 color=White>  Company : <font color = 'yellow'> "+companyString+"</b></html>");

		maxWeightLabel.setVisible(false);
		maxVolumeLabel.setVisible(false);
		freqLabel.setVisible(false);
		durationLabel.setVisible(false);
		volume.setVisible(false);
		weight.setVisible(false);
		companylabel.setVisible(true);
	}

	private void setupTCULabels() {
		day.setText("<html><b><font size = 5 color=White>  Date :  <font color = 'yellow'> "+dayString+"</b></html>");
		origin.setText("<html><b><font size = 5 color=White>  Origin :  <font color = 'yellow'> "+originString+"</b></html>");
		destination.setText("<html><b><font size = 5 color=White>  Destination : <font color = 'yellow'> "+destinationString+"</b></html>");
		priority.setText("<html><b><font size = 5 color=White>  Priority : <font color = 'yellow'> "+priorityString+"</b></html>");
		weight.setText("<html><b><font size = 5 color=White>  Weight : <font color = 'yellow'> "+weightString+"</b></html>");
		volume.setText("<html><b><font size = 5 color=White>  Volume : <font color = 'yellow'> "+volumeString+"</b></html>");

		maxWeightLabel.setText("<html><b><font size = 5 color=White>  Max Weight : <font color = 'yellow'> "+maxWeightString+"</b></html>");
		maxVolumeLabel.setText("<html><b><font size = 5 color=White>  Ma Volume : <font color = 'yellow'> "+maxVolumeString+"</b></html>");
		freqLabel.setText("<html><b><font size = 5 color=White>  Frequency : <font color = 'yellow'> "+freqString+"</b></html>");
		durationLabel.setText("<html><b><font size = 5 color=White>  Duration : <font color = 'yellow'> "+durationString+"</b></html>");

		/*Make these 4 labels visible for this event*/
		maxWeightLabel.setVisible(true);
		maxVolumeLabel.setVisible(true);
		freqLabel.setVisible(true);
		durationLabel.setVisible(true);
		companylabel.setVisible(false);

	}

	private void setupCPULabels() {
		day.setText("<html><b><font size = 5 color=White>  Date :  <font color = 'yellow'> "+dayString+"</b></html>");
		origin.setText("<html><b><font size = 5 color=White>  Origin :  <font color = 'yellow'> "+originString+"</b></html>");
		destination.setText("<html><b><font size = 5 color=White>  Destination : <font color = 'yellow'> "+destinationString+"</b></html>");
		priority.setText("<html><b><font size = 5 color=White>  Priority : <font color = 'yellow'> "+priorityString+"</b></html>");
		weight.setText("<html><b><font size = 5 color=White>  Weight : <font color = 'yellow'> "+weightString+"</b></html>");
		volume.setText("<html><b><font size = 5 color=White>  Volume : <font color = 'yellow'> "+volumeString+"</b></html>");

		/*Make these 4 labels invisible for this event*/
		maxWeightLabel.setVisible(false);
		maxVolumeLabel.setVisible(false);
		freqLabel.setVisible(false);
		durationLabel.setVisible(false);
		companylabel.setVisible(false);

	}

	private void setupMDLabels() {
		day.setText("<html><b><font size = 5 color=White>  Date :  <font color = 'yellow'> "+dayString+"</b></html>");
		origin.setText("<html><b><font size = 5 color=White>  Origin :  <font color = 'yellow'> "+originString+"</b></html>");
		destination.setText("<html><b><font size = 5 color=White>  Destination : <font color = 'yellow'> "+destinationString+"</b></html>");
		priority.setText("<html><b><font size = 5 color=White>  Priority : <font color = 'yellow'> "+priorityString+"</b></html>");
		weight.setText("<html><b><font size = 5 color=White>  Weight : <font color = 'yellow'> "+weightString+"</b></html>");
		volume.setText("<html><b><font size = 5 color=White>  Volume : <font color = 'yellow'> "+volumeString+"</b></html>");

		/*Make these 4 labels invisible for this event*/
		maxWeightLabel.setVisible(false);
		maxVolumeLabel.setVisible(false);
		freqLabel.setVisible(false);
		durationLabel.setVisible(false);
		companylabel.setVisible(false);

	}

	private void setupLabels() {
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(new GridBagLayout());
		c.insets = new Insets(0,10,0,10); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;

		/*Initialize all labels*/
		origin = new JLabel();
		destination = new JLabel();
		priority = new JLabel();
		day = new JLabel();
		weight = new JLabel();
		volume = new JLabel();
		title = new JLabel();
		typeTitle = new JLabel();
		maxWeightLabel = new JLabel();
		maxVolumeLabel = new JLabel();
		freqLabel = new JLabel();
		durationLabel = new JLabel();
		companylabel = new JLabel();

		day.setBackground(Color.BLACK);
		day.setOpaque(true);
		day.setBorder(new EmptyBorder(0,10,0,0));

		origin.setBackground(new Color(36, 36, 36));
		origin.setOpaque(true);
		origin.setBorder(new EmptyBorder(0,10,0,0));

		destination.setBackground(Color.BLACK);
		destination.setOpaque(true);
		destination.setBorder(new EmptyBorder(0,10,0,0));

		priority.setBackground(new Color(36, 36, 36));
		priority.setOpaque(true);
		priority.setBorder(new EmptyBorder(0,10,0,0));

		weight.setBackground(Color.BLACK);
		weight.setOpaque(true);
		weight.setBorder(new EmptyBorder(0,10,0,0));

		volume.setBackground(new Color(36, 36, 36));
		volume.setOpaque(true);
		volume.setBorder(new EmptyBorder(0,10,0,0));

		maxWeightLabel.setBackground(Color.BLACK);
		maxWeightLabel.setOpaque(true);
		maxWeightLabel.setBorder(new EmptyBorder(0,10,0,0));
		maxWeightLabel.setVisible(false);

		maxVolumeLabel.setBackground(new Color(36, 36, 36));
		maxVolumeLabel.setOpaque(true);
		maxVolumeLabel.setBorder(new EmptyBorder(0,10,0,0));
		maxVolumeLabel.setVisible(false);

		freqLabel.setBackground(Color.BLACK);
		freqLabel.setOpaque(true);
		freqLabel.setBorder(new EmptyBorder(0,10,0,0));
		freqLabel.setVisible(false);

		durationLabel.setBackground(new Color(36, 36, 36));
		durationLabel.setOpaque(true);
		durationLabel.setBorder(new EmptyBorder(0,10,0,0));
		durationLabel.setVisible(false);

		companylabel.setBackground(Color.BLACK);
		companylabel.setOpaque(true);
		companylabel.setBorder(new EmptyBorder(0,10,0,0));
		companylabel.setVisible(false);

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		labelPanel.setLayout(new GridBagLayout());
		labelPanel.setBorder(BorderFactory.createLoweredBevelBorder());



		c2.insets = new Insets(0,0,0,0); //top, left, bottom, right padding (in that order)
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.weightx = 1;

		c2.gridx = 0;
		c2.gridy = 2;
		labelPanel.add(day,c2);

		c2.gridx = 0;
		c2.gridy = 3;
		labelPanel.add(origin,c2);

		c2.gridx = 0;
		c2.gridy = 4;
		labelPanel.add(destination,c2);

		c2.gridx = 0;
		c2.gridy = 5;
		labelPanel.add(priority,c2);

		c2.gridx = 0;
		c2.gridy = 6;
		labelPanel.add(weight,c2);

		c2.gridx = 0;
		c2.gridy = 7;
		labelPanel.add(volume,c2);

		c2.gridx = 0;
		c2.gridy = 8;
		labelPanel.add(maxWeightLabel,c2);

		c2.gridx = 0;
		c2.gridy = 9;
		labelPanel.add(maxVolumeLabel,c2);

		c2.gridx = 0;
		c2.gridy = 10;
		labelPanel.add(freqLabel,c2);

		c2.gridx = 0;
		c2.gridy = 11;
		labelPanel.add(durationLabel,c2);

		c2.gridx = 0;
		c2.gridy = 11;
		labelPanel.add(companylabel,c2);

		c.insets = new Insets(0,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(title,c);

		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(typeTitle,c);

		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(labelPanel, c);
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
			if(eventCount+1 == events.size()) { //make sure we don't increase past the size of events list
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
