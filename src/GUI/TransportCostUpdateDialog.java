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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

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
	private JLabel newVolumeCostLabel;
	private JLabel dayOfDepartureLabel;
	private JLabel frequencyLabel;
	private JLabel durationLabel;

	
	/*The three panels on this Dialog*/
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel optionsPanel;
	private JPanel mainPanel;
	private JPanel underLyingPanel;
	
	/*All the options for the form*/
	private JComboBox destinationComboBox;
	private JComboBox fromComboBox;
	private JComboBox typeComboBox;
	private JTextField copmanyTextField;
	private JTextField weightTextField;
	private JTextField volumeTextField;
	private JTextField departureTextField;
	private JTextField frequencyTextField;
	private JTextField durationTextField;
	
	private JLabel weightLabelInfo;
	private JLabel volumeLabelInfo;
	
	private BufferedImage frameIcon;

	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	
	private KPSFrame frame;
	
	public TransportCostUpdateDialog(KPSFrame frame) {
		super(frame,true);
		this.frame = frame;
		setResizable(false);
		setBounds(0, 0, 500, 430);
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
 			
 		/*Main panel that holds the options panel and the labels panel*/
 		mainPanel = new JPanel();
 		mainPanel.setLayout(new BorderLayout());
 		mainPanel.add(optionsPanel, BorderLayout.CENTER);
 		mainPanel.add(labelPanel, BorderLayout.WEST);
 		TitledBorder title = BorderFactory.createTitledBorder(null, "Transport Price Update", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.PLAIN, 15), Color.RED);
 		mainPanel.setBorder(title);
 		underLyingPanel.add(mainPanel,BorderLayout.CENTER);
 		underLyingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20)); //this sets up the padding
 		
 		/*Add the main panel to the underlying panel and make this dialog visible*/
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
		copmanyTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 0;
		op.add(copmanyTextField,c2);
		
		String[] destinationList = {"Wellington", "Hamilton", "Auckland"};
		destinationComboBox = new JComboBox(destinationList);
		destinationComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 1;
		op.add(destinationComboBox,c2);
		
		String[] fromList = {"Auckland", "Hamilton", "Rotorua", "Palmerston North", "Wellington", "Christchurch", "Dunedin"};
		fromComboBox = new JComboBox(fromList);
		fromComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 2;
		op.add(fromComboBox,c2);
		
		String[] typeList = {"Sea", "Air", "Land"};
		typeComboBox = new JComboBox(typeList);
		typeComboBox.addActionListener(this);
		c2.gridx = 0;
		c2.gridy = 3;
		op.add(typeComboBox,c2);	
				
		weightTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 4;
		op.add(weightTextField,c2);
		
		weightLabelInfo = new JLabel("(Per Gram)");
		c2.gridx = 1;
		c2.gridy = 4;
		op.add(weightLabelInfo,c2);
				
		volumeTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 5;
		op.add(volumeTextField,c2);
		
		volumeLabelInfo = new JLabel("(Per Cubic Centimeter)");
		c2.gridx = 1;
		c2.gridy = 5;
		op.add(volumeLabelInfo,c2);
		
		departureTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 6;
		op.add(departureTextField,c2);
		
		frequencyTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 7;
		op.add(frequencyTextField,c2);
		
		durationTextField = new JTextField(10);
		c2.gridx = 0;
		c2.gridy = 8;
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

		companyLabel = new JLabel("Company: ");
		c.gridx = 0;
		c.gridy = 0;
		labelPanel.add(companyLabel,c);
		
		destinationLabel = new JLabel("Destination: ");
		c.gridx = 0;
		c.gridy = 1;
		labelPanel.add(destinationLabel,c);
		
		fromLabel = new JLabel("From: ");
		c.gridx = 0;
		c.gridy = 2;
		labelPanel.add(fromLabel,c);
		
		typeLabel = new JLabel("Type: ");
		c.gridx = 0;
		c.gridy = 3;
		labelPanel.add(typeLabel,c);	
		
		newWeightCostLabel = new JLabel("New Weight Cost: ");
		c.gridx = 0;
		c.gridy = 4;
		labelPanel.add(newWeightCostLabel,c);
		
		newVolumeCostLabel = new JLabel("New Volume Cost: ");
		c.gridx = 0;
		c.gridy = 5;
		labelPanel.add(newVolumeCostLabel,c);
	
		dayOfDepartureLabel = new JLabel("Day of Departure: ");
		c.gridx = 0;
		c.gridy = 6;
		labelPanel.add(dayOfDepartureLabel,c);
		
		frequencyLabel = new JLabel("Frequency: ");
		c.gridx = 0;
		c.gridy = 7;
		labelPanel.add(frequencyLabel,c);
		
		
		durationLabel = new JLabel("Duration of Trip: ");
		c.gridx = 0;
		c.gridy = 8;
		labelPanel.add(durationLabel,c);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * This is just for quickly testing the layout of this
	 * dialog without having to run the whole thing from log in screen.
	 * @param args
	 */
	public static void main(String[] args) {
		new TransportCostUpdateDialog(new KPSFrame());
	}
	
}
