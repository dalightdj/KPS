package Main;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
	private JLabel lockLabel;
	private JPanel lockPanel;
	
	private JButton login;
	
	private JTextField username;
	private JPasswordField password;

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private BufferedImage backgroundImage;
	private BufferedImage lockImage;

	
	
	
	public static final String ASSETS = "assets" + File.separatorChar;

	public MainFrame() {
		this.setLayout(new BorderLayout());
		
		
		/*Initialize the lock Image*/
		lockImage = load(ASSETS + "lock.png");
		ImageIcon lockIcon = new ImageIcon(lockImage); 
		lockLabel = new JLabel();
		lockLabel.setIcon(lockIcon);
		lockPanel = new JPanel();
	
		/*Initialize a layeredPane that will store the login form and the lock image*/
		JLayeredPane layeredPane = new JLayeredPane();

		/*Initialize the background Image*/
		backgroundImage = load(ASSETS + "silverBackground.png");
		ImageIcon icon = new ImageIcon(backgroundImage); 
		panel = new JLabel();
		panel.setIcon(icon);
		panel.setBounds(0,0,500,400);
 		//panel.setBorder(BorderFactory.createLineBorder(Color.red)); //for testing purposes
		layeredPane.add(panel,new Integer(0),0);
		
		/*Declare and initialise a new mini map border panel and set the layering position on top */
		lockPanel = new JPanel();
		lockPanel.setBounds(20,140,150,150);
		lockPanel.setOpaque(false);
		lockPanel.add(lockLabel);
 		//lockPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW)); //for testing purposes
		layeredPane.add(lockPanel,new Integer(1),0);
		
		
		this.add(layeredPane);
		
		/*Initialize the layout and the insets*/
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,-50,30,50); //top, left, bottom, right padding (in that order)
		
		JLabel LOGIN = new JLabel("LOGIN");
		Font LOGINFONT = new Font("Tunga", Font.BOLD, 25);
		LOGIN.setFont(LOGINFONT);
		LOGIN.setForeground(Color.BLACK);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(LOGIN,c);
		
		c.insets = new Insets(0,-50,0,50); //top, left, bottom, right padding (in that order)
		usernameLabel = new JLabel("Username: ");
		Font usernameFont = new Font("Tunga", Font.BOLD, 15);
		usernameLabel.setFont(usernameFont);
		usernameLabel.setForeground(Color.BLACK);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(usernameLabel,c);

		passwordLabel = new JLabel("Password: ");
		Font passwordFont = new Font("Tunga", Font.BOLD, 15);
		passwordLabel.setFont(passwordFont);
		passwordLabel.setForeground(Color.BLACK);
		c.gridx = 1;
		c.gridy = 3;
		panel.add(passwordLabel,c);
		
		c.insets = new Insets(0,1,0,1); //top, left, bottom, right padding (in that order)
		username = new JTextField(15);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(username,c);
		
		password = new JPasswordField(15);
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0,1,10,1); //top, left, bottom, right padding (in that order)
		panel.add(password,c);
		
		login = new JButton("login");
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(0,100,3,1); 
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
