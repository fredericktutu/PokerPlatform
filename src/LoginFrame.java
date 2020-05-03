import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame {

	public JFrame frame = new JFrame();
	public JPanel titlePanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public JPanel startPanel = new JPanel();
	public JButton startButton = new JButton("开始");
	public JLabel userName = new JLabel("UserName");
	public JLabel playerNum = new JLabel("Number of Players");
	public JLabel theme = new JLabel("THEME");
	public JLabel difficulty = new JLabel("Difficulty");
	public JTextField userNameField = new JTextField();
	public JTextField playerNumField = new JTextField("2");
	public JTextField themeField = new JTextField();
	public JTextField difficultyField = new JTextField();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		//JPanel titlePanel = new JPanel();
		Dimension titlePanelDim = new Dimension(450, 120);
		titlePanel.setSize(titlePanelDim);
		titlePanel.setMaximumSize(titlePanelDim);
		titlePanel.setMinimumSize(titlePanelDim);
		titlePanel.setPreferredSize(titlePanelDim);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		titlePanel.add(new JLabel("德州扑克"  ));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		//JPanel centerPanel = new JPanel();
		centerPanel.setSize(200,60);
		centerPanel.setLayout(new GridLayout(4, 2));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		Dimension fieldDim = new Dimension(100,20);
		userNameField.setSize(fieldDim);
		userNameField.setMaximumSize(fieldDim);
		userNameField.setMinimumSize(fieldDim);
		userNameField.setPreferredSize(fieldDim);
		playerNumField.setSize(fieldDim);
		playerNumField.setMaximumSize(fieldDim);
		playerNumField.setMinimumSize(fieldDim);
		playerNumField.setPreferredSize(fieldDim);
		themeField.setSize(fieldDim);
		themeField.setMaximumSize(fieldDim);
		themeField.setMinimumSize(fieldDim);
		themeField.setPreferredSize(fieldDim);
		difficultyField.setSize(fieldDim);
		difficultyField.setMaximumSize(fieldDim);
		difficultyField.setMinimumSize(fieldDim);
		difficultyField.setPreferredSize(fieldDim);
		
		
		centerPanel.add(userName);
		centerPanel.add(userNameField);
		centerPanel.add(playerNum);
		centerPanel.add(playerNumField);
		centerPanel.add(theme);
		centerPanel.add(themeField);
		centerPanel.add(difficulty);
		centerPanel.add(difficultyField);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		//JPanel startPanel = new JPanel();
		startPanel.setSize(500, 450);
		startPanel.setLayout(new FlowLayout());
		startPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				String number = playerNumField.getText().trim();
				int i = Integer.parseInt(number);
				String name = userNameField.getText().trim();
				
				try {
					final TexasPokerUIController window = new TexasPokerUIController();
					window.frame.setVisible(true);
					int leftNum = (int)Math.ceil(((double)i-1)/2);
					int rightNum = (i-1)/2;
					for(int j = 0; j < leftNum; j++) {
						window.westPanel.add(new JPlayerPanel());
					}
					for (int j = 0; j < rightNum; ++ j) {
						window.eastPanel.add(new JPlayerPanel());					
					}
					window.myselfPanel.updateName(name);
					//get thevalue of textfield ; string => int .getText()
					//(/int-1)/2+1, 
					//window.westpane
					//window.eastpanel
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		startPanel.add(startButton);
		
		frame.getContentPane().add(startPanel, BorderLayout.SOUTH);
	
		
	}

	

}
