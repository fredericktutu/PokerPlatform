import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RuleFrame {

	public JFrame frame;
	public JLabel label1 = new JLabel();
	public JLabel label2 = new JLabel();
	public JLabel label3 = new JLabel();
	public JLabel label4 = new JLabel();
	public JLabel nameLabel = new JLabel();
	public JPanel scrollablePanel = new JPanel();
	public JScrollPane scroller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RuleFrame window = new RuleFrame();
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
	public RuleFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
		scrollablePanel.setSize(630,400);
		
		ImageIcon iconOrigin = new ImageIcon("./�ز�/�õ�Ƭ1.JPG");
		Image image = iconOrigin.getImage();
		Image newImage = image.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
		label1.setIcon(icon);
		
		ImageIcon iconOrigin2 = new ImageIcon("./�ز�/�õ�Ƭ2.JPG");
		Image image2 = iconOrigin2.getImage();
		Image newImage2 = image2.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(newImage2);
		label2.setIcon(icon2);
		
		ImageIcon iconOrigin3 = new ImageIcon("./�ز�/�õ�Ƭ3.JPG");
		Image image3 = iconOrigin3.getImage();
		Image newImage3 = image3.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon3 = new ImageIcon(newImage3);
		label3.setIcon(icon3);
		
		ImageIcon iconOrigin4 = new ImageIcon("./�ز�/�õ�Ƭ4.JPG");
		Image image4 = iconOrigin4.getImage();
		Image newImage4 = image4.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon4 = new ImageIcon(newImage4);
		label4.setIcon(icon4);
		
		nameLabel.setText("<html>������Ա��<br>��˿���(�߼�/���ݿ�/����)���²��ġ�ë���¡������<br>��˿���(�����˿��߼�)�������ϡ�Ī����<br>AI�����㷨��ƣ�������<br>ͼ�ν������:��ǧǧ��������</html>");
		nameLabel.setFont(new Font("��Բ", Font.PLAIN, 24));
		Dimension dim = new Dimension(600, 200);
		nameLabel.setSize(dim);
		nameLabel.setMaximumSize(dim);
		nameLabel.setMinimumSize(dim);
		nameLabel.setPreferredSize(dim);
		
		
		scrollablePanel.add(label1);
		scrollablePanel.add(label2);
		scrollablePanel.add(label3);
		scrollablePanel.add(label4);
		scrollablePanel.add(nameLabel);
		scroller = new JScrollPane(scrollablePanel);
		
		frame.getContentPane().add(scroller);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			   @Override
			   public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    frame.dispose();
			   }
			  });
	}

}
