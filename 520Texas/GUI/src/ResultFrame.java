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
	public JLabel resultLabel = new JLabel("��Ϸ�����ʾ��");
	public JButton exitBtn = new JButton("�˳�");
	public JButton restartBtn = new JButton("���¿�ʼ?");
	public JButton nextBtn = new JButton("��һ��");

	public String token;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultFrame window = new ResultFrame("");
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
	public ResultFrame(String token) {
		this.token = token;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension titlePanelDim = new Dimension(450, 120);
		titlePanel.setSize(titlePanelDim);
		titlePanel.setMaximumSize(titlePanelDim);
		titlePanel.setMinimumSize(titlePanelDim);
		titlePanel.setPreferredSize(titlePanelDim);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		titlePanel.add(resultLabel);
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
	
		centerPanel.setSize(500, 450);
		centerPanel.setLayout(new FlowLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		
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

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final TexasPokerFrame window = new TexasPokerFrame(token, null, null);
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
