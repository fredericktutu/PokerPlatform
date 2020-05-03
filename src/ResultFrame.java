import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultFrame {

	private JFrame frame;
	public JPanel titlePanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public JLabel resultLabel = new JLabel("结果显示区");
	public JButton exitBtn = new JButton("退出");
	public JButton restartBtn = new JButton("重新开始");
	public JButton nextBtn = new JButton("下一轮");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultFrame window = new ResultFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ResultFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JPanel titlePanel = new JPanel();
		Dimension titlePanelDim = new Dimension(450, 120);
		titlePanel.setSize(titlePanelDim);
		titlePanel.setMaximumSize(titlePanelDim);
		titlePanel.setMinimumSize(titlePanelDim);
		titlePanel.setPreferredSize(titlePanelDim);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		titlePanel.add(resultLabel);
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		//JPanel centerPanel = new JPanel();
		centerPanel.setSize(500, 450);
		centerPanel.setLayout(new FlowLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		//JButton btn1 = new JButton("退出");
		//JButton btn2 = new JButton("重新开始");
		//JButton btn3 = new JButton("下一轮");
		
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final TexasPokerUIController window = new TexasPokerUIController();
					window.frame.setVisible(true);
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		
		centerPanel.add(exitBtn);
		centerPanel.add(restartBtn);
		centerPanel.add(nextBtn);
		
		
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
	}
	
	public void updateResult(String result) {
		resultLabel.setText(result);
		resultLabel.validate();
		resultLabel.revalidate();
	}
	
	

}
