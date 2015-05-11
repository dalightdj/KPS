package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {
	
	private KPSFrame frame;
	
	private JButton mailDelivery;
	private JButton customerPriceUpdate;
	private JButton transportCostUpdate;
	private JButton transportDiscontinue;

	public ButtonPanel(KPSFrame frame) {
		this.frame = frame;
		this.setPreferredSize(new Dimension(150,0));
		this.setBorder(BorderFactory.createLineBorder(Color.black)); //just for checking the positioning, can remove later
		
		/*Initialize the layout and the insets*/
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		c.fill = GridBagConstraints.HORIZONTAL;

		mailDelivery = new JButton("Mail Delivery");
		c.gridx = 0;
		c.gridy = 0;
		this.add(mailDelivery,c);
		mailDelivery.addActionListener(this);
		
		customerPriceUpdate = new JButton("Customer Price Update");
		c.gridx = 0;
		c.gridy = 1;
		this.add(customerPriceUpdate,c);
		customerPriceUpdate.addActionListener(this);
		
		transportCostUpdate = new JButton("Transport Cost Update");
		c.gridx = 0;
		c.gridy = 2;
		this.add(transportCostUpdate,c);
		transportCostUpdate.addActionListener(this);
		
		transportDiscontinue = new JButton("Transport Discontinued");
		c.gridx = 0;
		c.gridy = 3;
		this.add(transportDiscontinue,c);
		transportDiscontinue.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mailDelivery) {
			new MailDialog(frame);
		}
		if(e.getSource() == customerPriceUpdate) {
			new PriceUpdateDialog(frame);
		}
	}
}
