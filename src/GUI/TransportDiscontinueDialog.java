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
import javax.swing.border.TitledBorder;

import travelGraph.Location;
import travelGraph.Path;
import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;
import travelGraph.TravelGraph.Priority;
import Logic.KPS;
import Logic.KPS.NothingToDeleteException;
import Main.MainFrame;

public class TransportDiscontinueDialog extends JDialog implements ActionListener {

	private KPSFrame frame;
	private KPS kpsObject;

	/*The two buttons submit and cancel*/
	private JButton submit;
	private JButton cancel;

	/*All the labels*/
	private JLabel companyLabel;
	private JLabel destinationLabel;
	private JLabel fromLabel;
	private JLabel typeLabel;
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
	private JComboBox companyComboBox;
	private JComboBox typeComboBox;
	private JComboBox daysComboBox;

	private BufferedImage frameIcon;

	private boolean failed = false;

	private ArrayList<String> origins;


	public TransportDiscontinueDialog(KPSFrame frame, KPS kpsObject) {
		super(frame,true);
		this.kpsObject = kpsObject;
		this.frame = frame;
		setResizable(false);
		setBounds(0, 0, 360, 280);
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
 		
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5,10,5,1); //top, left, bottom, right padding (in that order)
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.weighty = 1.0;
		


		
		/*This is the panel with all the labels*/
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(110,0));
		labelPanel.setLayout(new GridBagLayout());


 		/*This panel is for the buttons submit and cancel*/
 		buttonPanel = new JPanel();
 		buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING)); //sets buttons to bottom right of panel
 		buttonPanel.setPreferredSize(new Dimension(this.getWidth(),40));
 		buttonPanelSetup(buttonPanel); //method that will setup the two buttons
 		this.add(buttonPanel, BorderLayout.SOUTH);

 		/*This panel is for the form to fill out*/
 		optionsPanel = new JPanel();
 		optionsPanel.setPreferredSize(new Dimension(this.getWidth(),80));
 		optionsPanel.setLayout(new GridBagLayout());

 		setupListeners(labelPanel, optionsPanel, c, c2);
 		
 		/*Main panel that holds the options panel and the labels panel*/
 		mainPanel = new JPanel();
 		mainPanel.setLayout(new BorderLayout());
 		mainPanel.add(optionsPanel, BorderLayout.CENTER);
 		mainPanel.add(labelPanel, BorderLayout.WEST);
 		TitledBorder title = BorderFactory.createTitledBorder(null, "Discontinue Transport", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.PLAIN, 15), Color.RED);
 		mainPanel.setBorder(title);
 		underLyingPanel.add(mainPanel,BorderLayout.CENTER);
 		underLyingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20)); //this sets up the padding

 		/*Add the main panel to the underlying panel and make this dialog visible*/
		this.getRootPane().setDefaultButton(submit);
 		this.add(underLyingPanel,BorderLayout.CENTER);

		if(failed) {
			cancel.doClick();
			JOptionPane.showMessageDialog(this,"No Listings Made","Failed",JOptionPane.ERROR_MESSAGE);
		}
		else {
			this.setVisible(true);
		}
	}

	private void setupListeners(JPanel labelPanel2, JPanel optionsPanel2, GridBagConstraints c, GridBagConstraints c2) {
		
		String[] daysList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		daysComboBox = new JComboBox(daysList);
    	DayOfWeek dayEnum = findDay((String) daysComboBox.getSelectedItem());
    	
    	if(!(kpsObject.getCompanies(dayEnum).isEmpty())) {
    		fromComboBox = new JComboBox(kpsObject.getOriginsOfCompany(dayEnum, kpsObject.getCompanies(dayEnum).get(0)).toArray()); //get first item from the originsofcompany list
    		companyComboBox = new JComboBox(kpsObject.getCompanies(dayEnum).toArray());
    		
    		
     		String origin = (String) fromComboBox.getSelectedItem();
        	String companyString = (String) companyComboBox.getSelectedItem();
    		destinationComboBox = new JComboBox(kpsObject.getDestinationsOfOriginOfCompany(dayEnum,companyString, origin).toArray());

     		origins = kpsObject.getOriginsOfCompany(dayEnum,(String) companyComboBox.getSelectedItem());
     		
    		fromComboBox.addActionListener (new ActionListener () {
    		    public void actionPerformed(ActionEvent e) {
    		    	updateDestinationComboBox(optionsPanel);
    		    }
    		});
    		updateDestinationComboBox(optionsPanel);

    		companyComboBox.addActionListener (new ActionListener () {
    		    public void actionPerformed(ActionEvent e) {
    		    	updateOriginComboBox(optionsPanel);
    		    }
    		});
    		updateOriginComboBox(optionsPanel);
    		
    		daysComboBox.addActionListener (new ActionListener () {
    		    public void actionPerformed(ActionEvent e) {
    		    	updateCompanyComboBox(optionsPanel);
    		    }
    		});
    		updateCompanyComboBox(optionsPanel);
    		
    		setupLabels(labelPanel, c); //method that will setup all the labels
     		setupOptions(optionsPanel, c2);
    	}	
    	else {
     		failed = true;
    	}
	}

	private void updateCompanyComboBox(JPanel optionsPanel) {
 		ArrayList<String> comps;
    	String companyString = (String) companyComboBox.getSelectedItem();
    	DayOfWeek dayEnum = findDay((String) daysComboBox.getSelectedItem());

    	comps = kpsObject.getCompanies(dayEnum);


    	companyComboBox.removeAllItems(); //remove all the current destinations

		/*Update the destinations combo box with available paths from current origin*/
		for(String s : comps) {
			companyComboBox.addItem(s);
		}
	}
	
	private void updateOriginComboBox(JPanel optionsPanel) {
 		ArrayList<String> locs;
    	String companyString = (String) companyComboBox.getSelectedItem();
    	DayOfWeek dayEnum = findDay((String) daysComboBox.getSelectedItem());

		locs = kpsObject.getOriginsOfCompany(dayEnum, companyString);

		fromComboBox.removeAllItems(); //remove all the current destinations

		/*Update the destinations combo box with available paths from current origin*/
		for(String s : locs) {
			fromComboBox.addItem(s);
		}
	}
	
	/**
	 * returns the day of week as enum
	 * @param dateString
	 * @return enum DayOfWeek
	 */
	private DayOfWeek findDay(String dateString) {
 		DayOfWeek dayEnum;
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
 		return dayEnum;
	}
	/**
	/**Find all possible paths from the currently selected origin and list those destinations in the destination combo box
	 * @param optionsPanel2
	 * 	@param c22
 	*/
	protected void updateDestinationComboBox(JPanel op) {
		/*Check what the currently selected destination is and create a location*/

 		String origin = (String) fromComboBox.getSelectedItem();
    	String companyString = (String) companyComboBox.getSelectedItem();
    	DayOfWeek dayEnum = findDay((String) daysComboBox.getSelectedItem());

 		ArrayList<String> locs;
		locs = kpsObject.getDestinationsOfOriginOfCompany(dayEnum, companyString, origin);

		destinationComboBox.removeAllItems(); //remove all the current destinations

		/*Update the destinations combo box with available paths from current origin*/
		for(String s : locs) {
			destinationComboBox.addItem(s);
		}

	}


	/**
	 * This method adds all the options into the JComboBoxes
	 * and adds them to the panel
	 *
	 * @param op - the options panel that these JComboBoxes will be added onto
	 * @param c2 - the GridBagConstraints to use for positioning
	 */
	private void setupOptions(JPanel op, GridBagConstraints c2) {

		c2.gridx = 0;
		c2.gridy = 0;
		op.add(daysComboBox,c2);

		c2.gridx = 0;
		c2.gridy = 1;
		op.add(companyComboBox,c2);

		fromComboBox.removeAllItems(); //remove all the current origins
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


		String[] priorityList = {"Sea", "Land", "Air"};
		typeComboBox = new JComboBox(priorityList);
		typeComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 4;
		op.add(typeComboBox,c2);

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

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			
	    	String companyString = (String) companyComboBox.getSelectedItem();
	 		String destination =  (String) destinationComboBox.getSelectedItem();
	 		String origin = (String) fromComboBox.getSelectedItem();
	 		String typeString = (String) typeComboBox.getSelectedItem();
	 		
		     if (companyString == null || companyString.equals("")) {
					JOptionPane.showMessageDialog(this,"Please enter all details","Insufficient Details",JOptionPane.ERROR_MESSAGE);
					return;
		     }
	    	 /*Check that the input in the company text field is string only */
	    	 if(!companyString.matches("^[a-zA-Z]+$")) {
					JOptionPane.showMessageDialog(this,"Please enter a company name (letters only)","Insufficient Details",JOptionPane.ERROR_MESSAGE);
					return;
		     }
	    	 /*Check for origin, destination combo box*/
	    	 if(origin == null || origin.equals("")) {
					JOptionPane.showMessageDialog(this,"No origin Selected","Insufficient Details",JOptionPane.ERROR_MESSAGE);
					return;
	    	 }
	    	 if(destination == null || destination.equals("")) {
					JOptionPane.showMessageDialog(this,"No Destination Selected","Insufficient Details",JOptionPane.ERROR_MESSAGE);
					return;
	    	 }
		     else {
		    	 


		 		TransportType typeEnum;
		 		
		 		/*Check for day*/
		    	DayOfWeek dayEnum = findDay((String) daysComboBox.getSelectedItem());

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

		 		kpsObject.discontinueRoute(origin, destination, companyString, typeEnum, dayEnum, true);
		    	frame.updateGUI();
		    	this.dispose();
		     }
		}
		if(e.getSource() == cancel) {
			this.dispose();
		}
	}


}