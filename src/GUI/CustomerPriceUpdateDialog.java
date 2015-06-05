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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import travelGraph.Location;
import travelGraph.Path.DayOfWeek;
import travelGraph.TravelGraph.Priority;
import Logic.KPS;
import Main.MainFrame;

public class CustomerPriceUpdateDialog extends JDialog implements ActionListener {


	/*The two buttons submit and cancel*/
	private JButton submit;
	private JButton cancel;

	/*All the labels*/
	private JLabel destinationLabel;
	private JLabel fromLabel;
	private JLabel newWeightCostLabel;
	private JLabel newVolumeCostLabel;
	private JLabel priorityLabel;
	private JLabel dayLabel;

	/*The three panels on this Dialog*/
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel optionsPanel;
	private JPanel mainPanel;
	private JPanel underLyingPanel;

	/*All the options for the form*/
	private JComboBox destinationComboBox;
	private JComboBox fromComboBox;
	private JComboBox priorityComboBox;
	private JComboBox daysComboBox;

	private JTextField weightTextField;
	private JTextField volumeTextField;
	private JLabel weightLabelInfo;
	private JLabel volumeLabelInfo;

	private BufferedImage frameIcon;

	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();

	private KPSFrame frame;
	private KPS kpsObject;

	private ArrayList<String> origins;

	public CustomerPriceUpdateDialog(KPSFrame frame, KPS kpsObject) {
		super(frame,true);
		this.kpsObject = kpsObject;
		this.frame = frame;
		setResizable(false);
		setBounds(0, 0, 600, 330);
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


 		/*add an acitonListener to the origin combobox and make sure the destinations update according to currently selected origin*/
		destinationComboBox = new JComboBox();
		fromComboBox = new JComboBox();

 		origins = kpsObject.getJourneyOrigins();

		/*This is the panel with all the labels*/
		labelPanel = new JPanel();
		//labelPanel.setBorder(BorderFactory.createLineBorder(Color.red)); //just for checking the positioning, can remove later
		labelPanel.setPreferredSize(new Dimension(110,0));
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

		fromComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	updateDestinationComboBox(optionsPanel);
		    }
		});
		updateDestinationComboBox(optionsPanel);

 		/*Main panel that holds the options panel and the labels panel*/
 		mainPanel = new JPanel();
 		mainPanel.setLayout(new BorderLayout());
 		mainPanel.add(optionsPanel, BorderLayout.CENTER);
 		mainPanel.add(labelPanel, BorderLayout.WEST);
 		TitledBorder title = BorderFactory.createTitledBorder(null, "Customer Price Update", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.PLAIN, 15), Color.RED);
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

		fromComboBox.removeAllItems(); //remove all the current destinations

 		/*Update the destinations combo box with available paths from current origin*/
 		for(String s : origins) {
 			fromComboBox.addItem(s);
 		}
		c2.gridx = 0;
		c2.gridy = 2;
		op.add(fromComboBox,c2);

		destinationComboBox = new JComboBox();
		destinationComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 3;
		op.add(destinationComboBox,c2);

		String[] priorityList = {"Standard", "Air"};
		priorityComboBox = new JComboBox(priorityList);
		priorityComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 4;
		op.add(priorityComboBox,c2);

		weightTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 5;
		op.add(weightTextField,c2);

		weightLabelInfo = new JLabel("(Per Gram)");
		c2.gridx = 1;
		c2.gridy = 5;
		op.add(weightLabelInfo,c2);

		volumeTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 6;
		op.add(volumeTextField,c2);

		volumeLabelInfo = new JLabel("(Per Cubic Centimeter)");
		c2.gridx = 1;
		c2.gridy = 6;
		op.add(volumeLabelInfo,c2);

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

		fromLabel = new JLabel("Origin: ");
		c.gridx = 0;
		c.gridy = 1;
		labelPanel.add(fromLabel,c);

		destinationLabel = new JLabel("Destination: ");
		c.gridx = 0;
		c.gridy = 2;
		labelPanel.add(destinationLabel,c);


		priorityLabel = new JLabel("Priority: ");
		c.gridx = 0;
		c.gridy = 3;
		labelPanel.add(priorityLabel,c);

		newWeightCostLabel = new JLabel("Weight Cost: ");
		c.gridx = 0;
		c.gridy = 4;
		labelPanel.add(newWeightCostLabel,c);

		newVolumeCostLabel = new JLabel("Volume Cost: ");
		c.gridx = 0;
		c.gridy = 5;
		labelPanel.add(newVolumeCostLabel,c);

	}

	/**
	/**Find all possible paths from the currently selected origin and list those destinations in the destination combo box
	 * @param optionsPanel2
	 * 	@param c22
 	*/
	protected void updateDestinationComboBox(JPanel op) {
		/*Check what the currently selected destination is and create a location*/

 		String origin = (String) fromComboBox.getSelectedItem();
 		String destination =  (String) destinationComboBox.getSelectedItem();
 		Location dest = new Location(destination);

 		/*Check what the currently selected priority is and create an enum*/
 		String priority = (String) priorityComboBox.getSelectedItem();
 		Priority priorityEnum;

 		if(priority.equals("Air")) {
 			 priorityEnum = Priority.AIR;
 		}
 		else {
 			priorityEnum = Priority.STANDARD;
 		}

 		ArrayList<String> locs = kpsObject.getJourneyDestinations(origin, priorityEnum);

 		destinationComboBox.removeAllItems(); //remove all the current destinations

 		/*Update the destinations combo box with available paths from current origin*/
 		for(String s : locs) {
 	    	destinationComboBox.addItem(s);
 		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == submit) {
		     if (weightTextField.getText().equals("") && volumeTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(this,"Please enter all details","Insufficient Details",JOptionPane.ERROR_MESSAGE);
		     }
		     else {
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

		 		String destination =  (String) destinationComboBox.getSelectedItem();
		 		String origin = (String) fromComboBox.getSelectedItem();
		 		String dateString = (String) daysComboBox.getSelectedItem();

		 		/*Check to make sure it's an int*/
		 		float weight = Float.parseFloat(weightTextField.getText());
		 		float volume = Float.parseFloat(volumeTextField.getText());

		 		if(weight <= 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive integer for weight","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}
		 		if(volume <= 0) {
					JOptionPane.showMessageDialog(this,"Please enter a positive integer for volume","Incorrect Details",JOptionPane.ERROR_MESSAGE);
					return;
		 		}

		 		String priority = (String) priorityComboBox.getSelectedItem();

		 		Priority priorityEnum;
		 		DayOfWeek dayEnum;

		 		/*Check for the priority*/
		 		if(priority.equals("Air")) {
		 			 priorityEnum = Priority.AIR;
		 		}
		 		else {
		 			priorityEnum = Priority.STANDARD;
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

		 		kpsObject.priceUpdate(destination, origin, priorityEnum, weight, volume,dayEnum, true);
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
		//new CustomerPriceUpdateDialog(new KPSFrame());
	}
}
