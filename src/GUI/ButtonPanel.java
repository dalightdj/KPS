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
	
	public ButtonPanel(KPSFrame frame) {
		this.frame = frame;
		this.setPreferredSize(new Dimension(150,0));
		this.setBorder(BorderFactory.createLineBorder(Color.black)); //just for checking the positioning, can remove later
		
		/*Initialize the layout and the insets*/
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		
		mailDelivery = new JButton("Mail Delivery");
		c.gridx = 0;
		c.gridy = 0;
		this.add(mailDelivery,c);
		mailDelivery.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mailDelivery) {
			new MailDialog(frame);
		}
	}
}
