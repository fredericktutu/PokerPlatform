import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;

public class TexasPoker {

	private JFrame frame;

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
		centerPanel.setSize(500, 450);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		centerPanel.add(new JLabel("公共牌，玩家操作，秒表"));
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

}
