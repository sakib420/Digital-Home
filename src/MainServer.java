import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

//import MainClient.JButtonHandler;

public class MainServer extends JFrame 
{
	//private static final long serialVersionUID = 1L;
	ServerSocket server;
	Socket socket;
	int port = 11111;
	ObjectInputStream input;
	ObjectOutputStream output;
	// For GUI
	JTextArea textArea;
	JTextArea area;
	JTextField inputField;
	JButton button = new JButton("SEND");
	
//  Fields for Password Panel
	private PanelBackground passwordPanel;
	private Image passwordPanelBackgroundImage;
	private JLabel userIdLabel,passwordLabel,welcomeLabel;
	private JTextField userIdField;
	private JPasswordField passwordField;
	private JButton logInButton;
	
//  Fields for Lock Button
	private JButton lockButton,lockUnlockButton;
	private JLabel unLockLabel;
	private Icon lockIcon,unLockIcon,lockButtonIcon;
	
	
	//   Fields for Temperature Button
	private JButton temperatureButton,increaseDecreaseButton;
	private JLabel decreaseLabel;
	private Icon heatIncreaseIcon,heatDecreaseIcon,temperatureButtonIcon;
	
	
// Fields for Light Button
	private JButton lightButton,lightOnOffButton;
	private JLabel offLabel;
	private Icon lightOnIcon,lightOffIcon,lightButtonIcon;
	
	
//  Fields for Camera Button
	private JButton cameraButton,recordingButton,recordingButton2;
    private Image cartoonBackgroundImageWihtLight,cartoonBackgroundImageWihtoutLight,
    cartoonImage;
	//private Icon cameraButtonIcon1,cameraButtonIcon2;
	MovingClassInServer movingPanel,movingPanel1;
    int frequency=0;
    
    
//  Fields for MessageButton
    private JButton messageButton,sendMeasageButton,showButton;
    private Image carImage,carBackgroundImage;
	//private Icon messageButtonIcon1,messageButtonIcon2;
	MovingCarClassInServer messagePanel;
	
	

//  Fields for mainPanel(JFrame) Background
	private PanelBackground mainPanel;
	private Image panelBackgroundImage;
	
	
//  Fields for mainPanel(JFrame) Background 
	
	private JPanel barPanel,mainPanel1,mainPanel2,mainPanel3,
	               mainPanel4,mainPanel5,mainPanel6,chatPanel;
	
	
	public static void main(String[] args) {
		new MainServer();
	}

	public MainServer() {

		super("");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		Image iconImage = new ImageIcon("C:\\Running Program\\Back.JPG").getImage();
		setIconImage(iconImage);
		
		setLookAndFill();
		setTitle("Digital Home-Server.............(By Abdul Aziz Sorkar and Shadman Sakib)");
		textArea = new JTextArea();
		inputField = new JTextField("Enter Message Here");
	
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		
        inputField.setToolTipText("Enter Server Message Here");
       
        chatPanel.add(inputField,BorderLayout.NORTH);
		
		textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setToolTipText("Message Output Area");
	    chatPanel.add(new JScrollPane(textArea),BorderLayout.CENTER);
	    guiComponent();
	    
	    
	    
		inputField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String message = inputField.getText();
				sendMessage(message);
			}
		});
		
		chatPanel.setBounds(10, 160, 230, 200);
        chatPanel.setVisible(false);
		mainPanel.add(chatPanel);
		

		setVisible(true);

		try {
			server = new ServerSocket(port);
			System.out.println("Server start at: " + InetAddress.getLocalHost()
					+ " Port: " + port);
			socket = server.accept();
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());

			processInput();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public void processInput() {
		while (true) {
			//JOptionPane.showMessageDialog(null, "message");
			//System.out.println("ServerClient");
			try {
				String str = input.readUTF();
				
				if(str.equals("lockButton")||str.equals("temperatureButton")||
						str.equals("lightButton")||str.equals("cameraButton")||str.equals("messageButton")||
						str.equals("Lock")||str.equals("Unlock")||str.equals("Increase Temperature")||
						str.equals("Decrease Temperature")||str.equals("Turn On Light")||
						str.equals("Turn Off Light")||str.equals("Stop Recording")||str.equals("Record Video")||
						str.equals("Send Message")||str.equals("Reset"))
					;
				else
				textArea.append("Client >> "+str+"\n");
				out(str);
			} catch (Exception e) {

			}
		}
	}

	public void sendMessage(String message)
	{	
		if(message.equals("lockButton")||message.equals("temperatureButton")||
		message.equals("lightButton")||message.equals("cameraButton")||message.equals("messageButton")||
		message.equals("Lock")||message.equals("Unlock")||message.equals("Increase Temperature")||
		message.equals("Decrease Temperature")||message.equals("Turn On Light")||
		message.equals("Turn Off Light")||message.equals("Stop Recording")||message.equals("Record Video")||
		message.equals("Send Message")||message.equals("Reset"))
			;
		
		else
		{
			textArea.append("Server >> "+message + "\n");
			
			inputField.setText("");
		}
		
		//area.setText("");
		try {
			output.writeUTF(message);
			output.flush();
		} catch (Exception e) {
		}
	}
	
	
	public void guiComponent()
	{
		
		//super("GuiComponent");
		
		setLayout(null);
		
		
		/*
	    
	    Addition  Password Panel Which is used to Control 
	    the security of the Project
	    
	 */
		passwordPanelBackgroundImage = new ImageIcon("C:\\Running Program\\Back.JPG").getImage();
		passwordPanel = new PanelBackground(passwordPanelBackgroundImage);
		passwordPanel.setBounds(0, 0,passwordPanelBackgroundImage.getWidth(null),
				passwordPanelBackgroundImage.getHeight(null));
		
		passwordPanel.setLayout(null);
		passwordPanel.setVisible(true);
		passwordPanel.setBackground(Color.BLUE);
		add(passwordPanel);
		welcomeLabel = new JLabel("Welcome to Server Application");
		welcomeLabel.setFont(new Font("Serif",Font.PLAIN,60));
		welcomeLabel.setBounds(180, 50, 1200, 100);
		welcomeLabel.setForeground(Color.ORANGE);
		passwordPanel.add(welcomeLabel);
		
		JPanel passPanel = new JPanel();
		passPanel.setLayout(new FlowLayout());
		passPanel.setBounds(300, 200, 500, 350);
		//passPanel.setBackground(Color.BLUE);
		passwordPanel.add(passPanel);
		
		
		userIdLabel = new JLabel("User ID    :");
		userIdLabel.setFont(new Font("Serif",Font.BOLD,30));
		userIdField = new JTextField(20);
		userIdField.setPreferredSize(new Dimension(70,30));
	
		passPanel.add(userIdLabel);
		passPanel.add(userIdField);
		
		passwordLabel = new JLabel("Password :");
		passwordLabel.setFont(new Font("Serif",Font.BOLD,30));
		passwordField = new JPasswordField(20);
		passwordField.setPreferredSize(new Dimension(70,30));
	
		passPanel.add(passwordLabel);
		passPanel.add(passwordField);
		
		logInButton = new JButton("Log In");
		logInButton.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
		logInButton.setPreferredSize(new Dimension(150,50));
		passPanel.add(logInButton);
		
		logInButton.addActionListener(new ActionListener() 
	    {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent event) 
			{
			     if(userIdField.getText().equals("k") && passwordField.getText().equals("1"))
			     {
			    	 mainPanel.setVisible(true);
			    	 passwordPanel.setVisible(false);
			    	 
			    	 passwordField.setText("");
			     }
			     
			     else
			     {
			    	 JOptionPane.showMessageDialog(null,"Wrong User ID Or Password\n\n           Try Again",
			    			 null,JOptionPane.ERROR_MESSAGE);
			     }
				
			}
		});
		
		

		/*
	    
	    Addition of Main Panel Image to develop it's beauty
	    
	 */
		
        panelBackgroundImage = new ImageIcon("C:\\Running Program\\Back.JPG").getImage();
		
		mainPanel = new PanelBackground(panelBackgroundImage);
		
		mainPanel.setBounds(0, 0,panelBackgroundImage.getWidth(null),
				panelBackgroundImage.getHeight(null));
		
//		mainPanel.setPreferredSize(new Dimension(panelBackgroundImage.getWidth(null),
//				panelBackgroundImage.getHeight(null)));
        mainPanel.setLayout(null);
        mainPanel.setVisible(false);
		add(mainPanel);
		
		
		/*
	    
	    Addition  Bar Panel to Which is used to Control 
	    the Project
	    
	 */
	JPanel bp = new JPanel();
	
	bp.setBackground(Color.WHITE);
	bp.setBounds(0, -10, 350, 38);
	//bp.setFont(new Font("Serif", Font.ITALIC, 50));
	bp.setBackground(Color.PINK);
	mainPanel.add(bp);
	
	JMenuItem exitItem = new JMenuItem("Exit");
	exitItem.setMnemonic('x');
	//fileMenu.add(exitItem);
	exitItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
				    System.exit(0);
				}
			}
			);
	
	JMenuBar bar = new JMenuBar();

	bp.add(bar);
	bar.add(exitItem);
	exitItem.setFont(new Font("Serif", Font.BOLD, 15));
	
	JMenuItem aboutItem = new JMenuItem("About");
	aboutItem.setMnemonic('A');
	aboutItem.setFont(new Font("Serif", Font.BOLD, 17));
	
	bar.add(aboutItem);
	
	aboutItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(null, 
		    				"This is a project of digital home system or smart home\nsystem." + 
		    				" It works through Internet, Bluetooth or Lan Connection.\n"+
		    				"You can control your home such as light ON-OFF or Door OPEN-CLOSE.\n"+
		    				"by computer  throuh internet from any where in the  world\n"+
		    				"No hardware Part is implemented Here. It is only \n"+
		    				"the simulation of digital home system" + 
		    				"\n\nThis Softwere Part is Developed by Abdul Aziz Sorkar" +
		    				" and Shadman Shakib\n\n" + "_______Department of CSE,KUET.",
		    				"About",JOptionPane.PLAIN_MESSAGE);
				}
			}
			);
	
	
	
	JMenuItem helpItem = new JMenuItem("Help");
	helpItem.setMnemonic('H');
	helpItem.setFont(new Font("Serif", Font.BOLD, 15));
	bar.add(helpItem);
	
	helpItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(null, 
	    					"Firsly, you have to login through User ID and Password.\n"+
	    					"Then, select what operation you want to perform from \n"+ 
	    					"the Bar panel Under the Description of the House\n" +
	    					"For example, Select 'Lock' Button to unlock or lock\n"+
	    					" the door, then Click in Unlock Button to open and\n"+
	    					"lock Button to close the door and so on............. \n\n"+
	    					"You can also communicate with Client by sending message\n"+
	    					"Via specified message Panel"+
	    					"\n\n............Thank you\n"
	    							,"Help",JOptionPane.PLAIN_MESSAGE);
				}
			}
			);
	
	JMenuItem logoutItem = new JMenuItem("Logout");
	logoutItem.setMnemonic('L');
	logoutItem.setFont(new Font("Serif", Font.BOLD, 15));
	bar.add(logoutItem);
	
	logoutItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					mainPanel.setVisible(false);
					passwordPanel.setVisible(true);
				}
			}
			);


		
		
		inputField.addMouseListener(new MouseListener() 
	    {
	    	public void mouseClicked(MouseEvent arg0) 
	    	{
				inputField.setText("");
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
				
		});
		
		
		JLabel label =  new JLabel("Description Of the House is Here");
		label.setBounds(430, 5, 400, 20);
		label.setFont(new Font("Serif",Font.BOLD,20));
		mainPanel.add(label);
		
//		JMenu menu = new JMenu("Help");
//		menu.setFont(new Font("Serif",Font.BOLD,15));
//		menu.setBounds(0, 0,60,20);
//		mainPanel.add(menu);
		
		/*
		      Addition of Lock Button of the House,
		      which is used to open the Door and 
		      Close it.............
		 */
		lockIcon = new ImageIcon(getClass().getResource("Lock.PNG"));
		unLockIcon = new ImageIcon(getClass().getResource("Lock2.PNG"));
		lockButtonIcon = new ImageIcon(getClass().getResource("lockButton.PNG"));
		
		barPanel = new JPanel();
		barPanel.setBackground(Color.ORANGE);
		
		lockButton = new JButton();
		
		lockButton.setIcon(new ImageIcon(getClass().getResource("lockButtonIcon.JPG")));
		lockButton.setPreferredSize(new Dimension(65,65));
		barPanel.add(lockButton);
		barPanel.setBounds(250, 30, 700, 80);
		mainPanel.add(barPanel);
		
		mainPanel1 = new JPanel();
		mainPanel1.setBackground(Color.MAGENTA);
		mainPanel1.setBounds(250, 120, 700, 500);
		mainPanel.add(mainPanel1);
		
		
		//lockLabel = new JLabel();
		unLockLabel = new JLabel();
		
		unLockLabel.setIcon(unLockIcon);
		unLockLabel.setLocation(255, 125);
		
		//mainPanel1.add(lockLabel);
		mainPanel1.add(unLockLabel);
		
	    lockUnlockButton = new JButton("Unlock");
	    lockUnlockButton.setPreferredSize(new Dimension(90,30));
	    lockUnlockButton.setBackground(Color.PINK);
	    lockUnlockButton.setFont(new Font("Serif",Font.BOLD,14));
		mainPanel1.add(lockUnlockButton);
		
		mainPanel1.setVisible(true);
		
		JButtonHandler handler = new JButtonHandler();
		lockButton.addActionListener(handler);
		lockUnlockButton.addActionListener(handler);
		
		
		
		
		/*
	      Addition of Temperature Button of the Home,
	      which is used to Increase and decrease the
	      internal temperature of the House
	      Close it.............
	 */
		
		
		heatIncreaseIcon = new ImageIcon(getClass().getResource("heatUp.PNG"));
		heatDecreaseIcon = new ImageIcon(getClass().getResource("heatDown.PNG"));
		temperatureButtonIcon = new ImageIcon(getClass().getResource("heatButton2.PNG"));
		
		temperatureButton = new JButton();
		
		temperatureButton.setIcon(temperatureButtonIcon);
		temperatureButton.setPreferredSize(new Dimension(65,65));
		barPanel.add(temperatureButton);
	
		mainPanel2 = new JPanel();
		mainPanel2.setBackground(Color.MAGENTA);
		mainPanel2.setBounds(250, 120, 700, 500);
		mainPanel.add(mainPanel2);
		
		decreaseLabel = new JLabel();
		
		decreaseLabel.setOpaque(false);
		decreaseLabel.setIcon(heatDecreaseIcon);
		decreaseLabel.setLocation(255, 125);
		
		mainPanel2.add(decreaseLabel);
		
	    increaseDecreaseButton = new JButton("Increase Temperature");
	    increaseDecreaseButton.setPreferredSize(new Dimension(170,50));
	    increaseDecreaseButton.setBackground(Color.PINK);
	    increaseDecreaseButton.setFont(new Font("Serif",Font.BOLD,14));
	    
		mainPanel2.add(increaseDecreaseButton);
		
		mainPanel2.setVisible(false);
		
		temperatureButton.addActionListener(handler);
		increaseDecreaseButton.addActionListener(handler);
		
		
		/*
	      Addition of Lock Button of the House,
	      which is used to open the Door and 
	      Close it.............
	 */
		
		
		lightOnIcon = new ImageIcon(getClass().getResource("lightOn.PNG"));
		lightOffIcon = new ImageIcon(getClass().getResource("lightOff.PNG"));
		lightButtonIcon = new ImageIcon(getClass().getResource("lightButton1.JPG"));
		
		lightButton = new JButton();
		
		lightButton.setIcon(lightButtonIcon);
		lightButton.setPreferredSize(new Dimension(65,65));
		barPanel.add(lightButton);
	
		mainPanel3 = new JPanel();
		mainPanel3.setBackground(Color.MAGENTA);
		mainPanel3.setBounds(250, 120, 700, 500);
		mainPanel.add(mainPanel3);
		
		offLabel = new JLabel();
		
		offLabel.setOpaque(false);
		offLabel.setIcon(lightOffIcon);
		offLabel.setLocation(255, 125);
		
		mainPanel3.add(offLabel);
		
	    lightOnOffButton = new JButton("Turn On Light");
	    lightOnOffButton.setPreferredSize(new Dimension(170,50));
	    lightOnOffButton.setBackground(Color.PINK);
	    lightOnOffButton.setFont(new Font("Serif",Font.BOLD,14));
	    
		mainPanel3.add(lightOnOffButton);
		
		mainPanel3.setVisible(false);
		
		lightButton.addActionListener(handler);
		lightOnOffButton.addActionListener(handler);
		
		
		
		
		/*
	      Addition of CC Camera Button of the House,
	      which is used to Record Video of the internal 
	      function of any room.............
	 */
		
		cartoonImage = new ImageIcon("C:\\Running Program\\Batman.PNG").getImage();
		cartoonBackgroundImageWihtoutLight = new ImageIcon("C:\\Running Program\\withoutLight.JPG").getImage();
		cartoonBackgroundImageWihtLight = new ImageIcon("C:\\Running Program\\withLight.JPG").getImage();
		
		cameraButton = new JButton();
		cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
		cameraButton.setPreferredSize(new Dimension(65,65));
		barPanel.add(cameraButton);
		
		
		
		mainPanel4 = new JPanel();
		mainPanel4.setBackground(Color.MAGENTA);
		mainPanel4.setBounds(250, 120, 700, 500);
		mainPanel.add(mainPanel4);
		
		recordingButton = new JButton("Record Video");
	    recordingButton.setPreferredSize(new Dimension(120,50));
	    recordingButton.setBackground(Color.PINK);
		recordingButton.setFont(new Font("Serif",Font.BOLD,14));
		
	    movingPanel = new MovingClassInServer(cartoonBackgroundImageWihtoutLight, cartoonImage);
	    movingPanel.setPreferredSize(new Dimension(cartoonBackgroundImageWihtoutLight.getWidth(null),
	    		cartoonBackgroundImageWihtoutLight.getHeight(null)));
	    
	    mainPanel4.add(movingPanel);	    
	    cameraButton.addActionListener(handler);
		recordingButton.addActionListener(handler);
		mainPanel4.add(recordingButton);
		mainPanel4.setVisible(false);
		
		
		
		mainPanel5 = new JPanel();
		mainPanel5.setBackground(Color.MAGENTA);
		mainPanel5.setBounds(250, 120, 700, 500);
		mainPanel.add(mainPanel5);
		
		recordingButton2 = new JButton("Stop Recording");
	    recordingButton2.setPreferredSize(new Dimension(120,50));
	    recordingButton2.setBackground(Color.PINK);
		recordingButton2.setFont(new Font("Serif",Font.BOLD,14));
	   
	    movingPanel1 = new MovingClassInServer(cartoonBackgroundImageWihtLight, cartoonImage);
	    movingPanel1.setPreferredSize(new Dimension(cartoonBackgroundImageWihtLight.getWidth(null),
	    		cartoonBackgroundImageWihtLight.getHeight(null)));
	   
	    mainPanel5.add(movingPanel1);
	    mainPanel5.add(recordingButton2);


		//cameraButton.addActionListener(handler);
		recordingButton2.addActionListener(handler);
		mainPanel5.setVisible(false);
		
		
		
		
		/*
	      Addition of message Button of the House,
	      which is used to send message to Client
	      when Children Come From School.............
	 */
		
		carImage = new ImageIcon("C:\\Running Program\\messageCar.PNG").getImage();
		carBackgroundImage = new ImageIcon("C:\\Running Program\\messageBackground.PNG").getImage();
        
		messageButton = new JButton();
		messageButton.setIcon(new ImageIcon(getClass().getResource("messageButtonIcon2.PNG")));
		messageButton.setPreferredSize(new Dimension(65,65));
		barPanel.add(messageButton);
		
		mainPanel6 = new JPanel();
		mainPanel6.setBackground(Color.MAGENTA);
		mainPanel6.setBounds(250, 120, 700, 500);
		mainPanel.add(mainPanel6);
		
		sendMeasageButton = new JButton("Send Message");
	    sendMeasageButton.setPreferredSize(new Dimension(120,50));
	    sendMeasageButton.setBackground(Color.PINK);
	    sendMeasageButton.setFont(new Font("Serif",Font.BOLD,14));
	    
	    messagePanel = new MovingCarClassInServer(carBackgroundImage, carImage);
	    messagePanel.setPreferredSize(new Dimension(carBackgroundImage.getWidth(null),
	    		carBackgroundImage.getHeight(null)));
	    
	    mainPanel6.add(messagePanel); 
	    mainPanel6.add(sendMeasageButton);
	    messagePanel.setLayout(null);
	    JLabel mLabel = new JLabel("School boy has come and Message Sent");
	    mLabel.setBounds(100, 30, 400, 100);
	    mLabel.setFont(new Font("serif",Font.BOLD,20));
	    messagePanel.add(mLabel);
	    
	    messageButton.addActionListener(handler);
	    sendMeasageButton.addActionListener(handler);
	    
	    mainPanel6.setVisible(false);
	    
	    
	    /*
	      Addition of Message Button of the House,
	      which is used to Show Message and help to communicate 
	      with the client and server..........
	 */
	    
	    showButton = new JButton("Click Here to Send Message");
	    showButton.setBounds(10, 120, 230, 35);
	    showButton.setFont(new Font("Serif",Font.BOLD,14));
	    mainPanel.add(showButton);
	    
	    showButton.addActionListener(handler);
	}
	
	private class JButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			/*
		      Action of Message Button of the House,
		      which is used to Show Message and help to communicate 
		      with the client and server..........
		 */
			if(event.getActionCommand().equals("Click Here to Send Message"))
			{
				showButton.setText("Again Click to Hide");
				chatPanel.setVisible(true);
				mainPanel.add(chatPanel);
			}
			
			else if(event.getActionCommand().equals("Again Click to Hide"))
			{
				showButton.setText("Click Here to Send Message");
				chatPanel.setVisible(false);
			}
			
			
			
			/*
			 Action for Sending Message from the House.........
			 */
			if(event.getSource() == messageButton)
			{
				String message = "messageButton";
			    sendMessage(message);
				
				
			    messageButton.setIcon(new ImageIcon(getClass().getResource("messageButtonIcon1.PNG")));
			    cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
				temperatureButton.setIcon(temperatureButtonIcon);
				lightButton.setIcon(lightButtonIcon);
				lockButton.setIcon(lockButtonIcon);
				
				mainPanel1.setVisible(false);
				mainPanel2.setVisible(false);
				mainPanel3.setVisible(false);
				mainPanel4.setVisible(false);
				mainPanel5.setVisible(false);
				
				mainPanel6.setVisible(true);
//				if(messagePanel.x==-330)
//				messagePanel.timer.stop();
//				
			}
			
			if(event.getActionCommand().equals("Send Message"))
			{
				String message = event.getActionCommand();
			    sendMessage(message);
				sendMeasageButton.setText("Reset");
				messagePanel.x = -329;
				messagePanel.timer.start();
				
			}
			
			if(event.getActionCommand().equals("Reset"))
			{
				String message = event.getActionCommand();
			    sendMessage(message);
			    
				sendMeasageButton.setText("Send Message");
				messagePanel.x = -330;
				//messagePanel.y = 100;
				messagePanel.timer.start();
				
			}
			
			
			
			/*
			 Action for CC Camera of the House.........
			 */
			
			
			if(event.getSource() == cameraButton)
			{
		        String message = "cameraButton";
		        sendMessage(message);
		        
				cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon2.PNG")));
				temperatureButton.setIcon(temperatureButtonIcon);
				lightButton.setIcon(lightButtonIcon);
				lockButton.setIcon(lockButtonIcon);
				messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
				
				mainPanel1.setVisible(false);
				mainPanel2.setVisible(false);
				mainPanel3.setVisible(false);
				mainPanel6.setVisible(false);
				
				if(frequency%2==0)
				{
					 mainPanel4.setVisible(true);
					
				}
				   
				else
				{
					mainPanel5.setVisible(true);
					
				}
				    
				//frequency++;
			}
			if(event.getActionCommand().equals("Record Video"))
			{
				String message = event.getActionCommand();
		        sendMessage(message);
		        
				mainPanel5.setVisible(true);
				movingPanel1.timer.start();
				recordingButton.setText("Stop Recording");
				
				mainPanel4.setVisible(false);
				mainPanel1.setVisible(false);
				mainPanel2.setVisible(false);
				mainPanel3.setVisible(false);
				mainPanel6.setVisible(false);
				
				frequency = 1;
				
				
			}
			
			if(event.getActionCommand().equals("Stop Recording"))
			{
				String message = event.getActionCommand();
		        sendMessage(message);
		        
				mainPanel4.setVisible(true);
				recordingButton.setText("Record Video");
				movingPanel1.timer.stop();
				
				mainPanel1.setVisible(false);
				mainPanel2.setVisible(false);
				mainPanel3.setVisible(false);
				mainPanel5.setVisible(false);
				mainPanel6.setVisible(false);
				
				frequency = 0;
			}
			
			
			
			
			/*
			 Action for closing and opening 
			 the door of the House.........
			 */
			if(event.getSource()== lockButton)
			{
				String message = "lockButton";
				sendMessage(message);
				lockButton.setIcon(new ImageIcon(getClass().getResource("lockButtonIcon.JPG")));
				temperatureButton.setIcon(temperatureButtonIcon);
				lightButton.setIcon(lightButtonIcon);
				cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
				messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
				
				mainPanel1.setVisible(true);
				
				mainPanel2.setVisible(false);
				mainPanel3.setVisible(false);
				mainPanel4.setVisible(false);
				mainPanel5.setVisible(false);
				mainPanel6.setVisible(false);
				
			}
			
			if(event.getActionCommand().equals("Unlock"))
			{
				String message = event.getActionCommand();
				sendMessage(message);
				unLockLabel.setIcon(lockIcon);
				lockUnlockButton.setText("Lock");
				
			}
			
			if(event.getActionCommand().equals("Lock"))
			{
				String message = event.getActionCommand();
				sendMessage(message);
				unLockLabel.setIcon(unLockIcon);
				lockUnlockButton.setText("Unlock");
			}
			
			
			/*
			       Action for increasing and decreasing
			       internal temperature of the House...
			 */
			if(event.getSource()== temperatureButton)
			{
				String message = "temperatureButton";
				sendMessage(message);
				
				temperatureButton.setIcon(new ImageIcon(getClass().getResource("heatButton1.PNG")));
				lockButton.setIcon(new ImageIcon(getClass().getResource("lockButton.PNG")));
				lightButton.setIcon(lightButtonIcon);
				cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
				messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
				
				mainPanel2.setVisible(true);
				
				mainPanel1.setVisible(false);
				mainPanel3.setVisible(false);
				mainPanel4.setVisible(false);
				mainPanel5.setVisible(false);
				mainPanel6.setVisible(false);
				
			}
			
			if(event.getActionCommand().equals("Increase Temperature"))
			{
				String message = event.getActionCommand();
				sendMessage(message);
				
				decreaseLabel.setIcon(heatIncreaseIcon);
				increaseDecreaseButton.setText("Decrease Temperature");
			}
			if(event.getActionCommand().equals("Decrease Temperature"))
			{
				String message = event.getActionCommand();
				sendMessage(message);
				
				decreaseLabel.setIcon(heatDecreaseIcon);
				increaseDecreaseButton.setText("Increase Temperature");
			}
			
			
			
			/*
			       Action for Switching light ON and OFF 
			       of a Room of the House...............
			 */
			if(event.getSource()== lightButton)
			{
				String message = "lightButton";
				sendMessage(message);
				
				lightButton.setIcon(new ImageIcon(getClass().getResource("lightButton2.JPG")));
				temperatureButton.setIcon(temperatureButtonIcon);
				lockButton.setIcon(new ImageIcon(getClass().getResource("lockButton.PNG")));
				cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
				messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
				
				mainPanel3.setVisible(true);
				
				mainPanel1.setVisible(false);
				mainPanel2.setVisible(false);
				mainPanel4.setVisible(false);
				mainPanel5.setVisible(false);
				mainPanel6.setVisible(false);
				
			}
			if(event.getActionCommand().equals("Turn On Light"))
			{
				String message = event.getActionCommand();
				sendMessage(message);
				
				offLabel.setIcon(lightOnIcon);
				lightOnOffButton.setText("Turn Off Light");
			}
			
			if(event.getActionCommand().equals("Turn Off Light"))
			{
				String message = event.getActionCommand();
				sendMessage(message);
				
				offLabel.setIcon(lightOffIcon);
				lightOnOffButton.setText("Turn On Light");
			}
			
		}
		
	}
	
	
	
	
	private void out(String str)
	{
		
		/*
		 Action for Sending Message from the House.........
		 */
		if(str.equals("messageButton"))
		{
//			String message = "cameraButton";
//		    sendMessage(message);
			
			
		    messageButton.setIcon(new ImageIcon(getClass().getResource("messageButtonIcon1.PNG")));
		    cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
			temperatureButton.setIcon(temperatureButtonIcon);
			lightButton.setIcon(lightButtonIcon);
			lockButton.setIcon(lockButtonIcon);
			
			mainPanel1.setVisible(false);
			mainPanel2.setVisible(false);
			mainPanel3.setVisible(false);
			mainPanel4.setVisible(false);
			mainPanel5.setVisible(false);
			
			mainPanel6.setVisible(true);
//			if(messagePanel.x==-330)
//			messagePanel.timer.stop();
//			
		}
		
		if(str.equals("Send Message"))
		{
			sendMeasageButton.setText("Reset");
			messagePanel.x = -329;
			messagePanel.timer.start();
			
		}
		
		if(str.equals("Reset"))
		{
			sendMeasageButton.setText("Send Message");
			messagePanel.x = -330;
			//messagePanel.y = 100;
			messagePanel.timer.start();
			
		}
		

		
		
		
		/*
		 Action for CC Camera of the House.........
		 */
		
		
		if(str.equals("cameraButton"))
		{
//	        String message = "cameraButton";
//	        sendMessage(message);
	        
			cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon2.PNG")));
			temperatureButton.setIcon(temperatureButtonIcon);
			lightButton.setIcon(lightButtonIcon);
			lockButton.setIcon(lightButtonIcon);
			messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
			
			
			mainPanel1.setVisible(false);
			mainPanel2.setVisible(false);
			mainPanel3.setVisible(false);
			mainPanel6.setVisible(false);
			if(frequency==0)
			mainPanel4.setVisible(true);
			else
				
			mainPanel5.setVisible(true);
			//frequency++;
		}
		if(str.equals("Record Video"))
		{
//			String message = event.getActionCommand();
//	        sendMessage(message);
	        
			mainPanel5.setVisible(true);
			movingPanel1.timer.start();
			recordingButton.setText("Stop Recording");
			
			mainPanel4.setVisible(false);
			mainPanel1.setVisible(false);
			mainPanel2.setVisible(false);
			mainPanel3.setVisible(false);
			mainPanel6.setVisible(false);
			
			frequency = 1;
		}
		
		if(str.equals("Stop Recording"))
		{
//			String message = event.getActionCommand();
//	        sendMessage(message);
	        
			mainPanel4.setVisible(true);
			recordingButton.setText("Record Video");
			movingPanel1.timer.stop();
			
			mainPanel1.setVisible(false);
			mainPanel2.setVisible(false);
			mainPanel3.setVisible(false);
			mainPanel5.setVisible(false);
			mainPanel6.setVisible(false);
			
			frequency = 0;
		}
		
		
		
		
		if(str.equals("lockButton"))
		{
//			String message = "lockButton";
//			sendMessage(message);
			lockButton.setIcon(new ImageIcon(getClass().getResource("lockButtonIcon.JPG")));
			temperatureButton.setIcon(temperatureButtonIcon);
			lightButton.setIcon(lightButtonIcon);
			cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
			messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
			
			
			mainPanel1.setVisible(true);
			
			mainPanel2.setVisible(false);
			mainPanel3.setVisible(false);
			mainPanel4.setVisible(false);
			mainPanel5.setVisible(false);
			mainPanel6.setVisible(false);
			
		}
		if(str.equals("Unlock"))
		{
//			String message = event.getActionCommand();
//			sendMessage(message);
			unLockLabel.setIcon(lockIcon);
			lockUnlockButton.setText("Lock");
			
		}
		
		if(str.equals("Lock"))
		{
//			String message = event.getActionCommand();
//			sendMessage(message);
			unLockLabel.setIcon(unLockIcon);
			lockUnlockButton.setText("Unlock");
		}
		
		
		/*
		       Action for increasing and decreasing
		       internal temperature of the House...
		 */
		if(str.equals("temperatureButton"))
		{
//			String message = "temperatureButton";
//			sendMessage(message);
			
			temperatureButton.setIcon(new ImageIcon(getClass().getResource("heatButton1.PNG")));
			lockButton.setIcon(new ImageIcon(getClass().getResource("lockButton.PNG")));
			lightButton.setIcon(lightButtonIcon);
			cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
			messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
			
			
			mainPanel1.setVisible(false);
			
			mainPanel2.setVisible(true);
			
			mainPanel3.setVisible(false);
			mainPanel4.setVisible(false);
			mainPanel5.setVisible(false);
			mainPanel6.setVisible(false);
			
		}
		
		if(str.equals("Increase Temperature"))
		{
//			String message = event.getActionCommand();
//			sendMessage(message);
//			
			decreaseLabel.setIcon(heatIncreaseIcon);
			increaseDecreaseButton.setText("Decrease Temperature");
		}
		if(str.equals("Decrease Temperature"))
		{
//			String message = event.getActionCommand();
//			sendMessage(message);
			
			decreaseLabel.setIcon(heatDecreaseIcon);
			increaseDecreaseButton.setText("Increase Temperature");
		}
		
		
		
		/*
		       Action for Switching light ON and OFF 
		       of a Room of the House...............
		 */
		if(str.equals("lightButton"))
		{
//			String message = "lightButton";
//			sendMessage(message);
			
			lightButton.setIcon(new ImageIcon(getClass().getResource("lightButton2.JPG")));
			temperatureButton.setIcon(temperatureButtonIcon);
			lockButton.setIcon(new ImageIcon(getClass().getResource("lockButton.PNG")));
			cameraButton.setIcon(new ImageIcon(getClass().getResource("cameraButtonIcon1.PNG")));
			messageButton.setIcon(new ImageIcon(getClass().getResource("MessageButtonIcon2.PNG")));
			
			
			mainPanel1.setVisible(false);
			mainPanel2.setVisible(false);
			
			mainPanel3.setVisible(true);
			
			mainPanel4.setVisible(false);
			mainPanel5.setVisible(false);
			mainPanel6.setVisible(false);
			
		}
		if(str.equals("Turn On Light"))
		{
//			String message = event.getActionCommand();
//			sendMessage(message);
			
			offLabel.setIcon(lightOnIcon);
			lightOnOffButton.setText("Turn Off Light");
		}
		
		if(str.equals("Turn Off Light"))
		{
//			String message = event.getActionCommand();
//			sendMessage(message);
			
			offLabel.setIcon(lightOffIcon);
			lightOnOffButton.setText("Turn On Light");
		}
	}
	
	public void setLookAndFill() 
	{
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
