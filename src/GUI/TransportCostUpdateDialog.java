package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;
import travelGraph.TravelGraph.Priority;
import Logic.KPS;
import Main.MainFrame;

public class TransportCostUpdateDialog extends JDialog implements ActionListener {

	/*The two buttons submit and cancel*/
	private JButton submit;
	private JButton cancel;

	/*All the labels*/
	private JLabel companyLabel;
	private JLabel destinationLabel;
	private JLabel fromLabel;
	private JLabel typeLabel;
	private JLabel newWeightCostLabel;
	private JLabel maxwWeightLabel;
	private JLabel newVolumeCostLabel;
	private JLabel maxVolumeLabel;
	//private JLabel dayOfDepartureLabel;
	private JLabel frequencyLabel;
	private JLabel durationLabel;
	private JLabel dayLabel;


	/*The three panels on this Dialog*/
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel optionsPanel;
	private JPanel mainPanel;
	private JPanel underLyingPanel;

	/*All the options for the form*/
	private JComboBox daysComboBox;
	private JTextField destinatinTextField;
	private JTextField originTextField;
	private JComboBox typeComboBox;
	private JTextField copmanyTextField;
	private JTextField weightTextField;
	private JTextField maxWeightTextField;
	private JTextField maxVolumeTextField;
	private JTextField volumeTextField;
	//private JTextField departureTextField;
	private JTextField frequencyTextField;
	private JTextField durationTextField;

	private JLabel weightLabelInfo;
	private JLabel maxWeightLabelInfo;
	private JLabel volumeLabelInfo;
	private JLabel durationLabelInfo; //per hours
	private JLabel freqLabelInfo; //hours between flight
	private JLabel gramLabel;
	private JLabel cubicLabel;

	private BufferedImage frameIcon;

	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();

	private KPSFrame frame;
	private KPS kpsObject;

	public TransportCostUpdateDialog(KPSFrame frame, KPS kpsObject) {
		super(frame,true);
		this.kpsObject = kpsObject;
		this.frame = frame;
		setResizable(false);
		setBounds(0, 0, 600, 500);
        this.setLocationRelativeTo(frame); //sets position relative to the whole window

		/*Initialize the layout and the insets*/
		this.setLayout(new BorderLayout());

		/*Initialize the underlying panel and layout*/
        underLyingPanel = new JPanel();
		underLyingPanel.setLayout(new BorderLayout());

		/*Set the dialog's icon*/
		frameIcon = MainFrame.load(MainFrame.ASSETS + "frameIcon2.png");
		ImageIcon icon = new ImageIcon(frameIcon);
		this.setIconImage(icon.getImage());


		/*This is the panel with all the labels*/
		labelPanel = new JPanel();
		//labelPanel.setBorder(BorderFactory.createLineBorder(Color.red)); //just for checking the positioning, can remove later
		labelPanel.setPreferredSize(new Dimension(150,0));
		labelPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		setupLabels(labelPanel, c); //method that will setup all the labels
 		//this.add(labelPanel, BorderLayout.WEST);

 		/*This panel is for the buttons submit and cancel*/
 		buttonPanel = new JPanel();
 		buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING)); //sets buttons to bottom right of panel
 		//buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
 		buttonPanel.setPreferredSize(new Dimension(this.getWidth(),40));
 		buttonPanelSetup(buttonPanel); //method that will setup the two buttons
 		this.add(buttonPanel, BorderLayout.SOUTH);

 		/*This panel is for the form to fill out*/
 		optionsPanel = new JPanel();
 		//optionsPanel.setBorder(BorderFactory.createLineBorder(Color.green)); //just for checking the positioning, can remove later
 		optionsPanel.setPreferredSize(new Dimension(this.getWidth(),80));
 		optionsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5,10,5,1); //top, left, bottom, right padding (in that order)
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.weightx = 1.0;
		c2.weighty = 1.0;
 		setupOptions(optionsPanel, c2);
 		//this.add(optionsPanel, BorderLayout.CENTER);

 		/*Main panel that holds the options panel and the labels panel*/
 		mainPanel = new JPanel();
 		mainPanel.setLayout(new BorderLayout());
 		mainPanel.add(optionsPanel, BorderLayout.CENTER);
 		mainPanel.add(labelPanel, BorderLayout.WEST);
 		TitledBorder title = BorderFactory.createTitledBorder(null, "Transport Cost Update", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.PLAIN, 15), Color.RED);
 		mainPanel.setBorder(title);
 		underLyingPanel.add(mainPanel,BorderLayout.CENTER);
 		underLyingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20)); //this sets up the padding

 		/*Add the main panel to the underlying panel and make this dialog visible*/
		this.getRootPane().setDefaultButton(submit);
 		this.add(underLyingPanel,BorderLayout.CENTER);
		this.setVisible(true);
	}


	/**
	 * This method adds all the options into the JComboBoxes
	 * and adds them to the panel
	 *
	 * @param op - the options panel that these JComboBoxes will be added onto
	 * @param c2 - the GridBagConstraints to use for positioning
	 */
	private void setupOptions(JPanel op, GridBagConstraints c2) {
		String[] daysList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		daysComboBox = new JComboBox(daysList);
		daysComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 0;
		op.add(daysComboBox,c2);

		copmanyTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 1;
		op.add(copmanyTextField,c2);

		originTextField = new JTextField();
		originTextField.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 2;
		op.add(originTextField,c2);

		destinatinTextField = new JTextField(10);
		destinatinTextField.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 3;
		op.add(destinatinTextField,c2);

		String[] typeList = {"Sea", "Air", "Land"};
		typeComboBox = new JComboBox(typeList);
		typeComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 4;
		op.add(typeComboBox,c2);

		weightTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 5;
		op.add(weightTextField,c2);

		maxWeightTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 6;
		op.add(maxWeightTextField,c2);

		weightLabelInfo = new JLabel("(Per Gram)");
		c2.gridx = 1;
		c2.gridy = 5;
		op.add(weightLabelInfo,c2);

		volumeTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 7;
		op.add(volumeTextField,c2);

		maxVolumeTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 8;
		op.add(maxVolumeTextField,c2);

		volumeLabelInfo = new JLabel("(Per Cubic Centimeter)");
		c2.gridx = 1;
		c2.gridy = 7;
		op.add(volumeLabelInfo,c2);

		durationLabelInfo = new JLabel("(Hours Between Flights)");
		c2.gridx = 1;
		c2.gridy = 9;
		op.add(durationLabelInfo,c2);

		freqLabelInfo = new JLabel("(Hours)");
		c2.gridx = 1;
		c2.gridy = 10;
		op.add(freqLabelInfo,c2);

		gramLabel = new JLabel("(Grams)");
		c2.gridx = 1;
		c2.gridy = 6;
		op.add(gramLabel,c2);

		cubicLabel = new JLabel("(Cubic Centimeters)");
		c2.gridx = 1;
		c2.gridy = 8;
		op.add(cubicLabel,c2);

		//departureTextField = new JTextField(10);
		//c2.gridx = 0;
		//c2.gridy = 9;
		//op.add(departureTextField,c2);

		frequencyTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 9;
		op.add(frequencyTextField,c2);

		durationTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 10;
		op.add(durationTextField,c2);

	}

	/**
	 * This method adds the two buttons 'submit' and 'cancel'
	 * onto the buttonPanel
	 *
	 * @param bp - the ButtonsPanel that buttons will be placed on
	 */
	private void buttonPanelSetup(JPanel bp) {
		submit = new JButton("Submit");
		bp.add(submit);
		submit.addActionListener(this);

		cancel = new JButton("Cancel");
		bp.add(cancel);
		cancel.addActionListener(this);
	}


	/**
	 * Add the labels onto the labelPanel
	 *
	 * @param labelPanel - The panel that these labels will be placed on
	 * @param c - The GridBagConstraints to use for positioning
	 */
	private void setupLabels(JPanel labelPanel, GridBagConstraints c) {
		dayLabel = new JLabel("Day: ");
		c.gridx = 0;
		c.gridy = 0;
		labelPanel.add(dayLabel,c);

		companyLabel = new JLabel("Company: ");
		c.gridx = 0;
		c.gridy = 1;
		labelPanel.add(companyLabel,c);

		fromLabel = new JLabel("Origin: ");
		c.gridx = 0;
		c.gridy = 2;
		labelPanel.add(fromLabel,c);

		destinationLabel = new JLabel("Destination: ");
		c.gridx = 0;
		c.gridy = 3;
		labelPanel.add(destinationLabel,c);


		typeLabel = new JLabel("Type: ");
		c.gridx = 0;
		c.gridy = 4;
		labelPanel.add(typeLabel,c);

		newWeightCostLabel = new JLabel("Weight Cost: ");
		c.gridx = 0;
		c.gridy = 5;
		labelPanel.add(newWeightCostLabel,c);

		maxwWeightLabel = new JLabel("Max Weight: ");
		c.gridx = 0;
		c.gridy = 6;
		labelPanel.add(maxwWeightLabel,c);

		newVolumeCostLabel = new JLabel("Volume Cost: ");
		c.gridx = 0;
		c.gridy = 7;
		labelPanel.add(newVolumeCostLabel,c);

		maxVolumeLabel = new JLabel("Max Volume: ");
		c.gridx = 0;
		c.gridy = 8;
		labelPanel.add(maxVolumeLabel,c);

		//dayOfDepartureLabel = new JLabel("Day of Departure: ");
		//c.gridx = 0;
		//c.gridy = 9;
		//labelPanel.add(dayOfDepartureLabel,c);

		frequencyLabel = new JLabel("Frequency: ");
		c.gridx = 0;
		c.gridy = 9;
		labelPanel.add(frequencyLabel,c);

		durationLabel = new JLabel("Duration of Trip: ");
		c.gridx = 0;
		c.gridy = 10;
		labelPanel.add(durationLabel,c);

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == submit) {
		     if (weightTextField.getText().equals("") && volumeTextField.getText().equals("") && maxWeightTextField.getText().equals("")
		    		 && maxVolumeTextField.getText().equals("") && frequencyTextField.getText().equals("") && durationTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(this,"Please enter all details","Insufficient Details",JOptionPane.ERROR_MESSAGE);
		     }
		     else {
		    	 /*Check that the input in the company, origin, and, destination text fields is string only */
		    	 if(!copmanyTextField.getText().matches("^[a-zA-Z]+$")) {
						JOptionPane.showMessageDialog(this,"Please enter a company name (letters only)","Insufficient Details",JOptionPane.ERROR_MESSAGE);
						return;
			     }
		    	 if(!originTextField.getText().matches("^[a-zA-Z]+$")) {
						JOptionPane.showMessageDialog(this,"Please enter an origin (letters only)","Insufficient Details",JOptionPane.ERROR_MESSAGE);
						return;
			     }
		    	 if(!destinatinTextField.getText().matches("^[a-zA-Z]+$")) {
						JOptionPane.showMessageDialog(this,"Please enter a destination (letters only)","Insufficient Details",JOptionPane.ERROR_MESSAGE);
						return;
			     }

		    	 /* Parse the textfields to make sure only integers have been put in*/
		    	 try {
		    	     Float.parseFloat(weightTextField.getText());
		    	}
		    	catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(this,"Please enter an integer for weight","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	try {
		    		Float.parseFloat(volumeTextField.getText());
		    	}
		    	catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(this,"Please enter an integer for volume","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	try {
		    	     Integer.parseInt(maxWeightTextField.getText());
		    	}
		    	catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(this,"Please enter an integer for max weight","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	try {
		    	     Integer.parseInt(maxVolumeTextField.getText());
		    	}
		    	catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(this,"Please enter an integer for max volume","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	try {
		    	     Integer.parseInt(frequencyTextField.getText());
		    	}
		    	catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(this,"Please enter an integer for frequency","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	try {
		    	     Integer.parseInt(durationTextField.getText());
		    	}
		    	catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(this,"Please enter an integer for duration","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	String companyString = copmanyTextField.getText();
		    	String destination =  destinatinTextField.getText();
		 		String origin = originTextField.getText();

		 		String dateString = (String) daysComboBox.getSelectedItem();


		 		/*Check to make sure it's an int*/
		 		float weight = Float.parseFloat(weightTextField.getText());
		 		float volume = Float.parseFloat(volumeTextField.getText());
		 		int maxWeight = Integer.parseInt(maxWeightTextField.getText());
		 		int maxVolume = Integer.parseInt(maxVolumeTextField.getText());
		 		int freq = Integer.parseInt(frequencyTextField.getText());
		 		int dur = Integer.parseInt(durationTextField.getText());

		 		if(weight < 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive number for weight","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		if(volume < 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive number for volume","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		if(maxWeight <= 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive integer for max weight","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		if(maxVolume <= 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive integer for max volume","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		if(freq <= 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive integer for frequency","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		if(dur <= 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive integer for duration","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		String typeString = (String) typeComboBox.getSelectedItem();

		 		TransportType typeEnum;
		 		DayOfWeek dayEnum;

		 		/*Check for the priority*/
		 		if(typeString.equals("Air")) {
		 			typeEnum = TransportType.AIR;
		 		}
		 		else if(typeString.equals("Land")) {
		 			typeEnum = TransportType.LAND;
		 		}
		 		else {
		 			typeEnum = TransportType.SEA;
		 		}

		 		/*Check for the day*/
		 		if(dateString.equals("Monday")) {
		 			dayEnum = DayOfWeek.MONDAY;
		 		}
		 		else if(dateString.equals("Tuesday")) {
		 			dayEnum = DayOfWeek.TUESDAY;
		 		}
		 		else if(dateString.equals("Wednesday")) {
		 			dayEnum = DayOfWeek.WEDNESDAY;
		 		}
		 		else if(dateString.equals("Thursday")) {
		 			dayEnum = DayOfWeek.THURSDAY;
		 		}
		 		else if(dateString.equals("Friday")) {
		 			dayEnum = DayOfWeek.FRIDAY;
		 		}
		 		else if(dateString.equals("Saturday")) {
		 			dayEnum = DayOfWeek.SATURDAY;
		 		}
		 		else {
		 			dayEnum = DayOfWeek.SUNDAY;
		 		}

		 		kpsObject.costUpdate(companyString, destination, origin, typeEnum, dayEnum, weight, volume, maxWeight, maxVolume, dur, freq, true);
		    	frame.updateGUI();
		 		this.dispose();
		     }

		}
		if(e.getSource() == cancel) {
			this.dispose();
		}
	}

	/**
	 * This is just for quickly testing the layout of this
	 * dialog without having to run the whole thing from log in screen.
	 * @param args
	 */
	public static void main(String[] args) {
		//new TransportCostUpdateDialog(new KPSFrame());
	}

}
