import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoginFrame {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		JPanel titlePanel = new JPanel();
		Dimension titlePanelDim = new Dimension(450, 120);
		titlePanel.setSize(titlePanelDim);
		titlePanel.setMaximumSize(titlePanelDim);
		titlePanel.setMinimumSize(titlePanelDim);
		titlePanel.setPreferredSize(titlePanelDim);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		titlePanel.add(new JLabel("德州扑克"  ));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setSize(500, 450);
		centerPanel.setLayout(new GridLayout(4, 2));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		centerPanel.add(new JLabel("UserName"));
		centerPanel.add(new JLabel("Dummy Username XXXX"));
		centerPanel.add(new JLabel("Number of Players"));
		centerPanel.add(new JLabel("XXXXX"));
		centerPanel.add(new JLabel("THEME"));
		centerPanel.add(new JLabel("Dummy THEME XXXX"));
		centerPanel.add(new JLabel("Difficulty"));
		centerPanel.add(new JLabel("Dummy Diffculty XXXXX"));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		JPanel startPanel = new JPanel();
		startPanel.setSize(500, 450);
		startPanel.setLayout(new FlowLayout());
		
		startPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		startPanel.add(new JButton("开始"));
		
		frame.getContentPane().add(startPanel, BorderLayout.SOUTH);
	
		
	}

	

}
