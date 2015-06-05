package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import Logic.KPS;
import Main.MainFrame;

public class ButtonPanel extends JPanel implements ActionListener {

	private KPSFrame frame;

	private JButton mailDelivery;
	private JButton customerPriceUpdate;
	private JButton transportCostUpdate;
	private JButton transportDiscontinue;
	private JButton viewEvents;
	private JButton logout;

	private KPS kpsObject;

	public ButtonPanel(KPSFrame frame, KPS kpsObject) {
		this.kpsObject = kpsObject;
		this.frame = frame;
		this.setPreferredSize(new Dimension(150,0));
		this.setBorder(BorderFactory.createLoweredBevelBorder());//set border for this panel

		/*Initialize the layout and the insets*/
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;

		mailDelivery = new JButton("Create Mail Delivery");
		c.gridx = 0;
		c.gridy = 0;
		this.add(mailDelivery,c);
		mailDelivery.addActionListener(this);

		customerPriceUpdate = new JButton("Update Customer Price");
		c.gridx = 0;
		c.gridy = 1;
		this.add(customerPriceUpdate,c);
		customerPriceUpdate.addActionListener(this);

		transportCostUpdate = new JButton("Update Transport Price");
		c.gridx = 0;
		c.gridy = 2;
		this.add(transportCostUpdate,c);
		transportCostUpdate.addActionListener(this);

		transportDiscontinue = new JButton("Discontinue Transport");
		c.gridx = 0;
		c.gridy = 3;
		this.add(transportDiscontinue,c);
		transportDiscontinue.addActionListener(this);

		viewEvents = new JButton("View Event History");
		c.gridx = 0;
		c.gridy = 4;
		this.add(viewEvents,c);
		viewEvents.addActionListener(this);
		viewEvents.setEnabled(false);

		logout = new JButton("Logout");
		c.gridx = 0;
		c.gridy = 5;
		this.add(logout,c);
		logout.addActionListener(this);

		if(frame.getIsManager()) {
			viewEvents.setEnabled(true);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mailDelivery) {
			new MailDialog(frame, kpsObject);
		}
		if(e.getSource() == customerPriceUpdate) {
			new CustomerPriceUpdateDialog(frame, kpsObject);
		}
		if(e.getSource() == transportCostUpdate) {
			new TransportCostUpdateDialog(frame, kpsObject);
		}
		if(e.getSource() == transportDiscontinue) {
			new TransportDiscontinueDialog(frame, kpsObject);
		}
		if(e.getSource() == viewEvents) {
			new EventsFrame(frame);
		}
		if(e.getSource() == logout) {
			  frame.dispose();
		      String[] args = new String[] {""};
		      MainFrame.main(args);
		}
	}
}
