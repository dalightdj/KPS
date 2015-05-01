package Main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import GUI.KPSFrame;

public class MainFrame extends JFrame implements ActionListener {

	private JLabel panel;
	
	private JButton login;
	
	private JTextField username;
	private JPasswordField password;

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private BufferedImage backgroundImage;
	
	public static final String ASSETS = "assets" + File.separatorChar;

	public MainFrame() {
		
		/*Initialize the background Image*/
		backgroundImage = load(ASSETS + "loginBackground2.png");
		ImageIcon icon = new ImageIcon(backgroundImage); 
		panel = new JLabel();
		panel.setIcon(icon);
		
		this.add(panel);
		
		/*Initialize the layout and the insets*/
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,1,5,1); //top, left, bottom, right padding (in that order)
		
		

		usernameLabel = new JLabel("Username: ");
		Font usernameFont = new Font("Tunga", Font.BOLD, 25);
		usernameLabel.setFont(usernameFont);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(usernameLabel,c);

		passwordLabel = new JLabel("Password: ");
		Font passwordFont = new Font("Tunga", Font.BOLD, 25);
		passwordLabel.setFont(passwordFont);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(passwordLabel,c);
		
		username = new JTextField(10);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(username,c);
		
		password = new JPasswordField(10);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(password,c);
		
		login = new JButton("login");
		c.gridx = 1;
		c.gridy = 2;
		panel.add(login,c);
		login.addActionListener(this);
		
		this.setPreferredSize(new Dimension(500,400));
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
		
	
	     public static void main(String[] args) {
	    	    JFrame.setDefaultLookAndFeelDecorated(true);
	    	    SwingUtilities.invokeLater(new Runnable() {
	    	      public void run() {
	    	        try {
	    	            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
	    	        	} catch (Exception e) {
	    	          System.out.println("Substance Graphite failed to initialize");
	    	        }
	    			new MainFrame();
	    	      }
	    	    });
	    	  }
	     
	     

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login) {
			if(username.getText().equals("admin") && password.getText().equals("admin")) {
				this.dispose();
				new KPSFrame();
			}
			else {
				JOptionPane.showMessageDialog(this,"Please re-enter your log in details","Invalid Login Details",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	 public static BufferedImage load(String base){
			String filename = base;
			if (!filename.endsWith(".png")){
				filename = filename.concat(".png");
			}
			try{
				//Loads the image
				File file = new File(filename);
				BufferedImage bufferedImage = ImageIO.read(file);
				return bufferedImage;
			}catch(IOException e){
				//Display an error and return a 1x1 Image
				System.err.println("Error loading file \""+filename+"\"\n");
				return null;
			}
	 }
	
}
