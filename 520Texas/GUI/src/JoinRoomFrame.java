import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class JoinRoomFrame extends JFrame implements MouseListener {
	String token;
	IHallController hallController;
	public static void main(String[] args) {

		new JoinRoomFrame("some what", null, "");

	}
	
	
	JLabel bacgrangd, small_mig, close_img, poker_img, Title_img, tu;// 
	JLabel small_btn, close_btn, lie1, lie2;// ��ɫ��|��
	JTextField roomID;// �˺�
	JPasswordField pass;// ����
	JPanel bgcolor;// ��
	JLabel account_img, password_img, ku1, ku2, gou1, gou2;// ����ͼ
	JLabel auto_lgin, Remember_pwd, find_pwd, sign_up, join;// �Զ���¼����ס���룬�һ����룬ע���˺ţ���¼
	static Point origin = new Point();// ���������ڿ��϶�����
	int a = 0, b = 0, c = 0, d = 0;// ������
	int f = 0, g = 0, h = 0, j = 0;// ���ơ�
	JLabel submit, ma;// ����

	public JoinRoomFrame(String token, IHallController hallController, String roomId) {
		//System.out.println("roomId is" + roomId);
		this.token = token;
		this.hallController = hallController;
//ʵ����
		bacgrangd = new JLabel(new ImageIcon("�ز�//timg.jpeg"));
		small_mig = new JLabel(new ImageIcon("�ز�//��С��.png"));
		close_img = new JLabel(new ImageIcon("�ز�//�ر�.png"));
		//poker_img = new JLabel(new ImageIcon("�ز�//poker.jpg"));
		Title_img = new JLabel("����");
		small_btn = new JLabel();
		close_btn = new JLabel();// ����
		//tu = new JLabel(new ImageIcon("�ز�//ͷ��.png"));
		roomID = new JTextField();
		pass = new JPasswordField();
		ImageIcon iconOrigin = new ImageIcon("�ز�//����1.png");
		Image image = iconOrigin.getImage();
		Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
		account_img = new JLabel(icon);
		password_img = new JLabel(new ImageIcon("�ز�//����.png"));
		lie1 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		lie2 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		bgcolor = new JPanel();
		//ku1 = new JLabel(new ImageIcon("�ز�//���.png"));
		//ku2 = new JLabel(new ImageIcon("�ز�//���.png"));
		//gou1 = new JLabel(new ImageIcon("�ز�//�Թ�.png"));
		//gou2 = new JLabel(new ImageIcon("�ز�//�Թ�.png"));
		//auto_lgin = new JLabel("�Զ���¼");
		//Remember_pwd = new JLabel("��ס����");
		//find_pwd = new JLabel("�һ�����");
		//sign_up = new JLabel("ע���˺�");
		join = new JLabel("����");
		submit = new JLabel();

//λ��
		bacgrangd.setBounds(-29, -123, 500, 250);
		small_mig.setBounds(364, 2, 32, 32);
		close_img.setBounds(396, 3, 32, 32);
		//poker_img.setBounds(10, 10, 45, 32);
		Title_img.setBounds(10, 5, 71, 47);
		small_btn.setBounds(361, 0, 35, 35);
		close_btn.setBounds(395, 0, 35, 35);
		//tu.setBounds(170, 80, 90, 85);
		roomID.setBounds(130, 160, 180, 40);
		pass.setBounds(130, 200, 180, 40);
		account_img.setBounds(100, 170, 20, 20);
		password_img.setBounds(100, 210, 20, 20);
		lie1.setBounds(100, 190, 240, 10);
		lie2.setBounds(100, 230, 240, 10);
		bgcolor.setBounds(0, 125, 500, 300);
		//ku1.setBounds(100, 250, 20, 20);
		//ku2.setBounds(190, 250, 20, 20);
		//gou1.setBounds(106, 255, 10, 10);
		//gou2.setBounds(196, 255, 10, 10);
		//auto_lgin.setBounds(125, 250, 80, 20);
		//Remember_pwd.setBounds(215, 250, 80, 20);
		//find_pwd.setBounds(288, 250, 80, 20);
		//sign_up.setBounds(15, 300, 80, 20);
		join.setBounds(206, 285, 80, 20);
		submit.setBounds(100, 280, 242, 35);
//����
		Title_img.setFont(new Font("΢���ź�", 1, 25));
		Title_img.setForeground(Color.white);
		small_btn.setBackground(new Color(0, 0, 0, 0.3f));
		close_btn.setBackground(new Color(0, 0, 0, 0.3f));
		bgcolor.setBackground(new Color(255, 255, 255));

		roomID.setForeground(Color.gray);
		//roomID.setText("�����");
		roomID.setText(roomId.equals("")? "�����": roomId);
		roomID.setOpaque(false);// ͸������
		roomID.setBorder(null);// ȥ���߿�
		roomID.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		pass.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		pass.setBorder(null);// ȥ���߿�

		pass.setOpaque(false);// ͸������
		pass.setForeground(Color.gray);
		pass.setText("����");
		pass.setEchoChar((char) 0);// ��������ʾ����

		//auto_lgin.setFont(new Font("΢���ź�", 0, 12));
		//Remember_pwd.setFont(new Font("΢���ź�", 0, 12));
		//find_pwd.setFont(new Font("΢���ź�", 0, 12));
		//sign_up.setFont(new Font("΢���ź�", 0, 12));
		join.setFont(new Font("΢���ź�", 0, 15));
		//auto_lgin.setForeground(new Color(170, 170, 170));
		//Remember_pwd.setForeground(new Color(170, 170, 170));
		//find_pwd.setForeground(new Color(170, 170, 170));
		//sign_up.setForeground(new Color(170, 170, 170));
		join.setForeground(Color.white);

		//gou1.setVisible(false);
		//gou2.setVisible(false);

		submit.setBackground(new Color(5, 186, 251));
		submit.setOpaque(true);

		//find_pwd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//sign_up.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//�¼�����
		small_mig.addMouseListener(this);
		close_img.addMouseListener(this);
		roomID.addMouseListener(this);
		pass.addMouseListener(this);
		//auto_lgin.addMouseListener(this);
		//Remember_pwd.addMouseListener(this);
		//find_pwd.addMouseListener(this);
		//sign_up.addMouseListener(this);
		//ku1.addMouseListener(this);
		//ku2.addMouseListener(this);
		submit.addMouseListener(this);
		this.addMouseListener(this);
		
	

		this.addMouseMotionListener(new MouseMotionListener() {// �����϶��¼�
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});

		
		
		roomID.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {// ʧȥ����
				ImageIcon iconOrigin = new ImageIcon("�ز�//����1.png");
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				account_img.setIcon(new javax.swing.ImageIcon(newImage));
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
				c = 0;
				if (roomID.getText().isEmpty()) {// �ж��Ƿ�Ϊ�գ�Ϊ������Ĭ����ʾ�
					roomID.setForeground(Color.gray);
					roomID.setText("�����");
				}
			}

			public void focusGained(FocusEvent e) {// �õ�����
				roomID.setForeground(Color.black);
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��3.png"));
				a = 1;
				c = 1;
				b = 0;
				ImageIcon iconOrigin = new ImageIcon("�ز�//����.png");
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				account_img.setIcon(new javax.swing.ImageIcon(newImage));
				if (roomID.getText().equals("�����")) {
					roomID.setText("");
				} else {
					roomID.setText(roomID.getText());
					roomID.selectAll();
				}
			}
		});

		pass.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {// ʧȥ����
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));// ʧȥ���㻻ͼƬ
				password_img.setIcon(new javax.swing.ImageIcon("�ز�//����.png"));
				d = 0;
				if (pass.getText().isEmpty()) {
					pass.setForeground(Color.gray);
					pass.setText("����");
					pass.setEchoChar((char) 0);// ��������ʾ����
				}
			}

			public void focusGained(FocusEvent e) {// �õ�����
				password_img.setIcon(new javax.swing.ImageIcon("�ز�//���� (1).png"));
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��3.png"));
				b = 1;
				a = 0;
				d = 1;
				pass.setForeground(Color.black);
				pass.setEchoChar('*');// ���û����뿴����
				if (pass.getText().equals("����")) {
					pass.setText("");
				} else {
					pass.setText(pass.getText());
				}
			}
		});

		getContentPane().setLayout(null);// ����

		getContentPane().add(small_mig);
		getContentPane().add(close_img);
		getContentPane().add(Title_img);
		//getContentPane().add(poker_img);
		getContentPane().add(small_btn);
		getContentPane().add(close_btn);
		//getContentPane().add(tu);
		getContentPane().add(lie1);
		getContentPane().add(lie2);
		getContentPane().add(roomID);
		getContentPane().add(pass);
		getContentPane().add(account_img);
		getContentPane().add(password_img);
		//getContentPane().add(gou1);
		//getContentPane().add(gou2);
		//getContentPane().add(ku1);
		//getContentPane().add(ku2);
		//getContentPane().add(auto_lgin);
		//getContentPane().add(Remember_pwd);
		//getContentPane().add(find_pwd);
		//getContentPane().add(sign_up);
		getContentPane().add(join);
		getContentPane().add(submit);
		getContentPane().add(bgcolor);
		getContentPane().add(bacgrangd);

		this.setSize(430, 330);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("�ز�\\͸����Ƭ.png"));// ����ͼ��
		this.setLocationRelativeTo(null);// ���־���
		this.setUndecorated(true);// ȥ����
		this.setFocusable(true);// ������Ȼ�ý���
		this.setBackground(new Color(255, 255, 255));// ������ɫ
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);// ���
		this.setVisible(true);// ��ʾ
	}


	public void mouseClicked(MouseEvent e) {// ������ָ�
	}


	//�����¼�
	
	public void mousePressed(MouseEvent e) {// �����
		if (e.getSource() == small_mig) {
			setExtendedState(JFrame.ICONIFIED);
		} else if (e.getSource() == this) {
			origin.x = e.getX();
			origin.y = e.getY();
		} else if (e.getSource() == close_img) {
			this.dispose();
		} else if (e.getSource() == ku1 || e.getSource() == auto_lgin) {
			if (f == 0) {
				gou1.setVisible(true);
				g = 1;
				f = 1;
			} else if (g == 1) {
				gou1.setVisible(false);
				f = 0;
				g = 0;
			}
		} else if (e.getSource() == ku2 || e.getSource() == Remember_pwd) {
			if (h == 0) {
				gou2.setVisible(true);
				j = 1;
				h = 1;
			} else if (j == 1) {
				gou2.setVisible(false);
				h = 0;
				j = 0;
			}
		} else if (e.getSource() == submit || e.getSource() == join) {
			join.setFont(new Font("΢���ź�", 0, 14));
			

			String roomId = roomID.getText();
			String passWord = new String(pass.getPassword());
			try {
				System.out.println("JoinRoomFrame�����뷿��");
				hallController.handler_search_room(token, roomId, passWord);
			}catch(RemoteException re) {
				re.printStackTrace();
			}
			dispose();
			
		}
	}

	public void mouseReleased(MouseEvent e) {// ���ʱ
		if (e.getSource() == submit || e.getSource() == join) {
			join.setFont(new Font("΢���ź�", 0, 15));
		}
	}

	public void mouseEntered(MouseEvent e) {// ��ͣ
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(true);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(true);
		} else if (e.getSource() == roomID) {
			if (a == 0 && c == 0) {
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��4.png"));
			}
		} else if (e.getSource() == pass) {
			if (b == 0 && d == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��4.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(Color.GRAY);
		}  else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("�ز�//��ά��2.png"));
		}
	}

	public void mouseExited(MouseEvent e) {// ��ͣ��
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(false);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(false);
		} else if (e.getSource() == roomID) {
			if (a == 0) {
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
			}
		} else if (e.getSource() == pass) {
			if (b == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(new Color(170, 170, 170));
		}  else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("�ز�//��ά��.png"));
		}

	}
}