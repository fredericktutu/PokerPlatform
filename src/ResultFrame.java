import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultFrame {

	private JFrame frame;

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
		
		JPanel titlePanel = new JPanel();
		Dimension titlePanelDim = new Dimension(450, 120);
		titlePanel.setSize(titlePanelDim);
		titlePanel.setMaximumSize(titlePanelDim);
		titlePanel.setMinimumSize(titlePanelDim);
		titlePanel.setPreferredSize(titlePanelDim);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		titlePanel.add(new JLabel("结果显示区"));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setSize(500, 450);
		centerPanel.setLayout(new FlowLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		JButton btn1 = new JButton("退出");
		
		JButton btn2 = new JButton("重新开始");
		JButton btn3 = new JButton("下一轮");
		
		
		centerPanel.add(btn1);
		centerPanel.add(btn2);
		centerPanel.add(btn3);
		
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
	}

}
