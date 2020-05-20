import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
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

public class RegisterUI extends JFrame implements MouseListener {
	public static void main(String[] args) {

		new RegisterUI();

	}
	
	
	JLabel bacgrangd, small_mig, close_img, poker_img, Title_img, tu;// 
	JLabel small_btn, close_btn, lie1, lie2, lie3;// ��ɫ��|��
	JTextField user;// �˺�
	JTextField user_name;//�ǳ�
	JPasswordField pass;// ����
	JPanel bgcolor;// ��
	JLabel account_img, password_img, ku1, ku2, gou1, gou2,name_img;// ����ͼ
	JLabel auto_lgin, Remember_pwd, find_pwd, sign_up, login;// �Զ���¼����ס���룬�һ����룬ע���˺ţ���¼
	static Point origin = new Point();// ���������ڿ��϶�����
	int a = 0, b = 0, c = 0, d = 0;// ������
	int f = 0, g = 0, h = 0, j = 0;// ���ơ�
	JLabel submit, ma;// ����

	public RegisterUI() {

//ʵ����
		bacgrangd = new JLabel(new ImageIcon("�ز�//timg.gif"));
		small_mig = new JLabel(new ImageIcon("�ز�//��С��.png"));
		close_img = new JLabel(new ImageIcon("�ز�//�ر�.png"));
		poker_img = new JLabel(new ImageIcon("�ز�//poker.jpg"));
		Title_img = new JLabel("ע��");
		small_btn = new JLabel();
		close_btn = new JLabel();// ����
		//tu = new JLabel(new ImageIcon("�ز�//ͷ��.png"));
		user = new JTextField();
		user_name = new JTextField();
		pass = new JPasswordField();
		account_img = new JLabel(new ImageIcon("�ز�//poker.jpg"));
		name_img = new JLabel(new ImageIcon("�ز�//poker.jpg"));
		password_img = new JLabel(new ImageIcon("�ز�//����.png"));
		lie1 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		lie2 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		lie3 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		bgcolor = new JPanel();
		//ku1 = new JLabel(new ImageIcon("�ز�//���.png"));
		//ku2 = new JLabel(new ImageIcon("�ز�//���.png"));
		//gou1 = new JLabel(new ImageIcon("�ز�//�Թ�.png"));
		//gou2 = new JLabel(new ImageIcon("�ز�//�Թ�.png"));
		//auto_lgin = new JLabel("�Զ���¼");
		//Remember_pwd = new JLabel("��ס����");
		//find_pwd = new JLabel("�һ�����");
		sign_up = new JLabel("�����˺�>");
		login = new JLabel("ע��");
		submit = new JLabel();

//λ��
		bacgrangd.setBounds(-29, -123, 500, 250);
		small_mig.setBounds(364, 2, 32, 32);
		close_img.setBounds(396, 3, 32, 32);
		poker_img.setBounds(10, 10, 45, 32);
		Title_img.setBounds(50, 5, 71, 47);
		small_btn.setBounds(361, 0, 35, 35);
		close_btn.setBounds(395, 0, 35, 35);
		//tu.setBounds(170, 80, 90, 85);
		user.setBounds(130, 120, 180, 40);
		user_name.setBounds(130, 160, 180, 40);
		pass.setBounds(130, 200, 180, 40);
		name_img.setBounds(100, 170, 20, 20);
		account_img.setBounds(100, 130, 20, 20);
		password_img.setBounds(100, 210, 20, 20);
		lie1.setBounds(100, 190, 240, 10);
		lie3.setBounds(100, 150, 240, 10);
		lie2.setBounds(100, 230, 240, 10);
		bgcolor.setBounds(0, 125, 500, 300);
		//ku1.setBounds(100, 250, 20, 20);
		//ku2.setBounds(190, 250, 20, 20);
		//gou1.setBounds(106, 255, 10, 10);
		//gou2.setBounds(196, 255, 10, 10);
		//auto_lgin.setBounds(125, 250, 80, 20);
		//Remember_pwd.setBounds(215, 250, 80, 20);
		//find_pwd.setBounds(288, 250, 80, 20);
		sign_up.setBounds(15, 300, 80, 20);
		login.setBounds(206, 285, 80, 20);
		submit.setBounds(100, 280, 242, 35);
//����
		Title_img.setFont(new Font("΢���ź�", 1, 25));
		Title_img.setForeground(Color.white);
		small_btn.setBackground(new Color(0, 0, 0, 0.3f));
		close_btn.setBackground(new Color(0, 0, 0, 0.3f));
		bgcolor.setBackground(new Color(255, 255, 255));

		user.setForeground(Color.gray);
		user.setText("�˻�");
		user.setOpaque(false);// ͸������
		user.setBorder(null);// ȥ���߿�
		user.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		
		user_name.setForeground(Color.gray);
		user_name.setText("�ǳ�");
		user_name.setOpaque(false);// ͸������
		user_name.setBorder(null);// ȥ���߿�
		user_name.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		
		
		pass.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		pass.setBorder(null);// ȥ���߿�
		pass.setOpaque(false);// ͸������
		pass.setForeground(Color.gray);
		pass.setText("����");
		pass.setEchoChar((char) 0);// ��������ʾ����

		//auto_lgin.setFont(new Font("΢���ź�", 0, 12));
		//Remember_pwd.setFont(new Font("΢���ź�", 0, 12));
		//find_pwd.setFont(new Font("΢���ź�", 0, 12));
		sign_up.setFont(new Font("΢���ź�", 0, 12));
		login.setFont(new Font("΢���ź�", 0, 15));
		//auto_lgin.setForeground(new Color(170, 170, 170));
		//Remember_pwd.setForeground(new Color(170, 170, 170));
		//find_pwd.setForeground(new Color(170, 170, 170));
		sign_up.setForeground(new Color(170, 170, 170));
		login.setForeground(Color.white);

		//gou1.setVisible(false);
		//gou2.setVisible(false);

		submit.setBackground(new Color(5, 186, 251));
		submit.setOpaque(true);

		//find_pwd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sign_up.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//�¼�����
		small_mig.addMouseListener(this);
		close_img.addMouseListener(this);
		user.addMouseListener(this);
		user_name.addMouseListener(this);
		pass.addMouseListener(this);
		//auto_lgin.addMouseListener(this);
		//Remember_pwd.addMouseListener(this);
		//find_pwd.addMouseListener(this);
		sign_up.addMouseListener(this);
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

		sign_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       // you can open a new frame here as
		       // i have assumed you have declared "frame" as instance variable
		    	RegisterUI.this.dispose();
		    	new LoginUI();
		       
		    }  
		});
		
		user.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {// ʧȥ����
				account_img.setIcon(new javax.swing.ImageIcon("�ز�//qq (1).png"));
				lie3.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
				c = 0;
				if (user.getText().isEmpty()) {// �ж��Ƿ�Ϊ�գ�Ϊ������Ĭ����ʾ�
					user.setForeground(Color.gray);
					user.setText("�˻�");
				}
			}

			public void focusGained(FocusEvent e) {// �õ�����
				user.setForeground(Color.black);
				lie3.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��3.png"));
				a = 1;
				c = 1;
				b = 0;
				account_img.setIcon(new javax.swing.ImageIcon("�ز�//qq (2).png"));
				if (user.getText().equals("�˻�")) {
					user.setText("");
				} else {
					user.setText(user.getText());
					user.selectAll();
				}
			}
		});
		
		user_name.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {// ʧȥ����
				name_img.setIcon(new javax.swing.ImageIcon("�ز�//qq (1).png"));
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
				c = 0;
				if (user_name.getText().isEmpty()) {// �ж��Ƿ�Ϊ�գ�Ϊ������Ĭ����ʾ�
					user_name.setForeground(Color.gray);
					user_name.setText("�ǳ�");
				}
			}

			public void focusGained(FocusEvent e) {// �õ�����
				user_name.setForeground(Color.black);
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��3.png"));
				a = 1;
				c = 1;
				b = 0;
				name_img.setIcon(new javax.swing.ImageIcon("�ز�//qq (2).png"));
				if (user_name.getText().equals("�ǳ�")) {
					user_name.setText("");
				} else {
					user_name.setText(user_name.getText());
					user_name.selectAll();
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
		getContentPane().add(poker_img);
		getContentPane().add(small_btn);
		getContentPane().add(close_btn);
		//getContentPane().add(tu);
		getContentPane().add(lie1);
		getContentPane().add(lie2);
		getContentPane().add(lie3);
		getContentPane().add(user);
		getContentPane().add(user_name);
		getContentPane().add(pass);
		getContentPane().add(account_img);
		getContentPane().add(name_img);
		getContentPane().add(password_img);
		//getContentPane().add(gou1);
		//getContentPane().add(gou2);
		//getContentPane().add(ku1);
		//getContentPane().add(ku2);
		//getContentPane().add(auto_lgin);
		//getContentPane().add(Remember_pwd);
		//getContentPane().add(find_pwd);
		getContentPane().add(sign_up);
		getContentPane().add(login);
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

	public void mousePressed(MouseEvent e) {// �����
		if (e.getSource() == small_mig) {
			setExtendedState(JFrame.ICONIFIED);
		} else if (e.getSource() == this) {
			origin.x = e.getX();
			origin.y = e.getY();
		} else if (e.getSource() == close_img) {
			System.exit(0);
		} else if (e.getSource() == submit || e.getSource() == login) {
			login.setFont(new Font("΢���ź�", 0, 14));
			dispose();
			
			String names = user_name.getText();
			String users = user.getText();
			String password = new String(pass.getPassword());
			boolean succeedToRegister = false;
			try{
				String path = "//localhost:12200/Server";
				IServer s  = (IServer)Naming.lookup(path);
				succeedToRegister = s.service_register(users, names, password);
			} catch(Exception ee) {
				ee.printStackTrace();
			}

			if (succeedToRegister) {
				//ע��ɹ�
				JOptionPane.showMessageDialog(null, "ע��ɹ���");
				//RegisterUI.this.dispose();
				new LoginUI();
				
			} else {
				// ע��ʧ��
				//System.out.println("ע��ʧ��");
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ��˺��ѱ�ע��");
				//RegisterUI.this.dispose();
				new RegisterUI();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {// ���ʱ
		if (e.getSource() == submit || e.getSource() == login) {
			login.setFont(new Font("΢���ź�", 0, 15));
		}
	}

	public void mouseEntered(MouseEvent e) {// ��ͣ
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(true);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(true);
		} else if (e.getSource() == user) {
			if (a == 0 && c == 0) {
				lie3.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��4.png"));
			}
		} else if (e.getSource() == user_name) {
			if (a == 0 && c == 0) {
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��4.png"));
			}
		}else if (e.getSource() == pass) {
			if (b == 0 && d == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��4.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(Color.GRAY);
		} else if (e.getSource() == sign_up) {
			sign_up.setForeground(Color.GRAY);
		} else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("�ز�//��ά��2.png"));
		}
	}

	public void mouseExited(MouseEvent e) {// ��ͣ��
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(false);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(false);
		} else if (e.getSource() == user_name) {
			if (a == 0) {
				lie3.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
			}
		} else if (e.getSource() == user) {
			if (a == 0) {
				lie1.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
			}
		}else if (e.getSource() == pass) {
			if (b == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(new Color(170, 170, 170));
		} else if (e.getSource() == sign_up) {
			sign_up.setForeground(new Color(170, 170, 170));
		} else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("�ز�//��ά��.png"));
		}

	}
}