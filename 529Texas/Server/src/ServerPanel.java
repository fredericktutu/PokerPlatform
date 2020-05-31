import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerPanel {
	Server server;
	MyHttpService httpService;
	public JFrame frame;
	public JLabel label = new JLabel();
	public JLabel label1 = new JLabel();
	public JButton button = new JButton();
	public JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerPanel window = new ServerPanel();
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
	public ServerPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setLayout(null);
		label.setText("�ѹر�");
		label.setBounds(215, 130, 100, 100);
		label.setFont(new Font("��Բ", Font.PLAIN, 24));
		button.setText("����");
		button.setFont(new Font("��Բ", Font.PLAIN, 24));
		button.setBounds(200, 200, 100, 60);
		panel.add(label);
		panel.add(button);
		frame.getContentPane().add(panel);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String choice = (String)button.getText();
					if(choice.equals("����")) {
						label.setText("�ѿ���");
						button.setText("�ر�");
						try {
							server = new Server();
							server.service_deploy();//1
							httpService = new MyHttpService(server);
							
						} catch (Exception ee) {
							ee.printStackTrace();
						}

					}else if(choice.equals("�ر�")){
						label.setText("�ѹر�");
						button.setText("����");
						server = null;
						httpService = null;
					}				
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		
		
	}

}
