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
import javax.swing.border.EmptyBorder;

import Logic.Journey;
import Logic.KPS;
import Main.MainFrame;

public class CriticalRoutes extends JFrame implements ActionListener {

	private BufferedImage frameIcon;

	private KPSFrame frame;

	private JPanel mainPanel;
	private JPanel buttons;

	private JButton nextRoute;
	private JButton prevRoute;

	/*The labels for information*/
	private JLabel origin;
	private JLabel destination;
	private JLabel priority;
	private JLabel day;
	private JLabel title;
	private JLabel loss;

	/*Information for the labels*/
	private String dayString = "";
	private String originString ="";
	private String destinationString = "";
	private String priorityString = "";
	private String lossString = "";

	private KPS kpsObject;

	private ArrayList<Journey> journeys;
	private int journeyCount = 0; 

	public CriticalRoutes(KPSFrame frame) {

		this.frame = frame;
		this.kpsObject = frame.getKpsObject();
        this.setLocationRelativeTo(frame);


		this.setLayout(new BorderLayout());
		this.setTitle("Kelburn Postal Serivce Critical Routes");
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
		this.journeys = kpsObject.getCriticals(); //get latest routes every time it this GUI updates

		if(journeys.size() == 0) {
			JOptionPane.showMessageDialog(this, "There is currently no critical routes to view!", "No Critical Routes", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(journeys.size() != 0) {
				Journey currentJourney = journeys.get(journeyCount);
				dayString = currentJourney.getDow().toString();
				originString = currentJourney.getOrigin();
				destinationString = currentJourney.getDestination();
				priorityString = currentJourney.getPriority().toString();
				//lossString = Float.toString(currentJourney.getAverageLoss());
				lossString = String.format("%.2f", currentJourney.getAverageLoss()); //round to 2 dp
				
				setupJourneyLabels();
		}
		int tempJourneyCount = journeyCount + 1; //this is just for increasing 1 so first journey doesn't show as "Journey Number: 0"
		title.setText("<html><font size = 5 color=White>Journey Number: <font color = 'yellow'> "+tempJourneyCount+"</html>");
	}

	private void setupJourneyLabels() {
		day.setText("<html><font face = Lucida Sans Unicode size = 5 color=White>  Day :  <font color = 'yellow'> "+dayString+"</html>");
		origin.setText("<html><font face = Lucida Sans Unicode size = 5 color=White>  Origin :  <font color = 'yellow'> "+originString+"</html>");
		destination.setText("<html><font face = Lucida Sans Unicode size = 5 color=White>  Destination : <font color = 'yellow'> "+destinationString+"</html>");
		priority.setText("<html><font face = Lucida Sans Unicode size = 5 color=White>  Priority : <font color = 'yellow'> "+priorityString+"</html>");
		loss.setText("<html><font face = Lucida Sans Unicode size = 5 color=White>  Average Loss : <font color = 'yellow'> "+"$"+lossString+"</html>");

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
		title = new JLabel();
		loss = new JLabel();

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

		loss.setBackground(Color.BLACK);
		loss.setOpaque(true);
		loss.setBorder(new EmptyBorder(0,10,0,0));
		
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
		labelPanel.add(loss,c2);

		c.insets = new Insets(0,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(title,c);

		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(labelPanel, c);
	}

	private void setupButtons() {
		/*Initialize the layout and the insets*/
		buttons.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1);
		c.fill = GridBagConstraints.HORIZONTAL;

		/*Add the Next Route Button*/
		nextRoute = new JButton("Next Route");
		c.gridx = 0;
		c.gridy = 0;
		buttons.add(nextRoute,c);
		nextRoute.addActionListener(this);
		
		/*Add the Previous Route Button*/
		prevRoute = new JButton("Previous Route");
		c.gridx = 0;
		c.gridy = 1;
		buttons.add(prevRoute,c);
		prevRoute.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nextRoute) {
			if(journeyCount+1 == journeys.size()) { //make sure we don't increase past the size of journeys list
				return;
			}
			else {
				journeyCount++;
				updateGUI();
			}
		}
		else if(e.getSource() == prevRoute) {
			if(journeyCount ==0) { //check if journeyCount is 0 so that we don't get out of bounds exception
				return;
			}
			else {
				journeyCount--;
				updateGUI();
			}
		}
	}
	
}
