import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;

public class TexasPokerUIController implements UIController {

	private JFrame frame = new JFrame();
	public JPanel panel1 = new JPanel();
	public JPanel panel2 = new JPanel();
	public JPanel panel3 = new JPanel();
	public JPanel westPanel = new JPanel();
	public JPanel eastPanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public JLabel stopwatch = new JLabel("秒表");
	public JLabel pubcard  = new JLabel("公共牌");
	public JLabel playerlog = new JLabel("日志");
	public JPanel southPanel = new JPanel();
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasPokerUIController window = new TexasPokerUIController();
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
	public TexasPokerUIController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Border blackline = BorderFactory.createLineBorder(Color.black);
		
		
		
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		westPanel.setSize(200, 450);
		westPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		westPanel.add(new JPlayerPanel());
		westPanel.add(new JPlayerPanel());
		westPanel.add(new JPlayerPanel());
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		
		
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setSize(500, 450);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		centerPanel.add(panel1);
		centerPanel.add(panel2);
		centerPanel.add(panel3);
		panel1.add(stopwatch);
		panel2.add(pubcard);
		panel3.add(playerlog);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
		

		
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setSize(200, 450);
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		eastPanel.add(new JPlayerPanel());
		eastPanel.add(new JPlayerPanel());
		eastPanel.add(new JPlayerPanel());
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		
		
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		southPanel.setSize(50, 45);
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		southPanel.add(new JPlayerMyselfPanel());
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		
	}

	@Override
	public void update(String s) {
		// TODO Auto-generated method stub
		playerlog.setText(s);

	}

	@Override
	public void update(String s, boolean isYourTurn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(boolean enterGame) {
		// TODO Auto-generated method stub
		
	}

}
