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

public class TexasPoker implements UIController {

	private JFrame frame;
	public JPanel panel1;
	public JPanel panel2;
	public JPanel panel3;
	public JPanel westpanel;
	public JPanel eastpanel;
	public JPanel centerpanel;
	public JLabel stopwatch;
	public JLabel pubcard;
	public JLabel playerlog;
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasPoker window = new TexasPoker();
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
	public TexasPoker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		stopwatch = new JLabel("秒表");
		pubcard = new JLabel("公共牌");
		playerlog = new JLabel("日志");
		
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Border blackline = BorderFactory.createLineBorder(Color.black);
		
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		westPanel.setSize(200, 450);
		westPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		westPanel.add(new JPlayerPanel());
		westPanel.add(new JPlayerPanel());
		westPanel.add(new JPlayerPanel());
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		
		
		JPanel centerPanel = new JPanel();
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
		
		
		

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setSize(200, 450);
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		eastPanel.add(new JPlayerPanel());
		eastPanel.add(new JPlayerPanel());
		eastPanel.add(new JPlayerPanel());
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		
		JPanel southPanel = new JPanel();
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
