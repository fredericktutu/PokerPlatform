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
	JLabel small_btn, close_btn, lie1, lie2;// 暗色块|线
	JTextField roomID;// 账号
	JPasswordField pass;// 密码
	JPanel bgcolor;// 白
	JLabel account_img, password_img, ku1, ku2, gou1, gou2;// 缩略图
	JLabel auto_lgin, Remember_pwd, find_pwd, sign_up, join;// 自动登录，记住密码，找回密码，注册账号，登录
	static Point origin = new Point();// 变量，用于可拖动窗体
	int a = 0, b = 0, c = 0, d = 0;// 控制线
	int f = 0, g = 0, h = 0, j = 0;// 控制√
	JLabel submit, ma;// 背景

	public JoinRoomFrame(String token, IHallController hallController, String roomId) {
		//System.out.println("roomId is" + roomId);
		this.token = token;
		this.hallController = hallController;
//实例化
		bacgrangd = new JLabel(new ImageIcon("素材//timg.jpeg"));
		small_mig = new JLabel(new ImageIcon("素材//最小化.png"));
		close_img = new JLabel(new ImageIcon("素材//关闭.png"));
		//poker_img = new JLabel(new ImageIcon("素材//poker.jpg"));
		Title_img = new JLabel("加入");
		small_btn = new JLabel();
		close_btn = new JLabel();// 暗调
		//tu = new JLabel(new ImageIcon("素材//头像.png"));
		roomID = new JTextField();
		pass = new JPasswordField();
		ImageIcon iconOrigin = new ImageIcon("素材//房间1.png");
		Image image = iconOrigin.getImage();
		Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
		account_img = new JLabel(icon);
		password_img = new JLabel(new ImageIcon("素材//密码.png"));
		lie1 = new JLabel(new ImageIcon("素材//直线2.png"));
		lie2 = new JLabel(new ImageIcon("素材//直线2.png"));
		bgcolor = new JPanel();
		//ku1 = new JLabel(new ImageIcon("素材//框框.png"));
		//ku2 = new JLabel(new ImageIcon("素材//框框.png"));
		//gou1 = new JLabel(new ImageIcon("素材//对勾.png"));
		//gou2 = new JLabel(new ImageIcon("素材//对勾.png"));
		//auto_lgin = new JLabel("自动登录");
		//Remember_pwd = new JLabel("记住密码");
		//find_pwd = new JLabel("找回密码");
		//sign_up = new JLabel("注册账号");
		join = new JLabel("加入");
		submit = new JLabel();

//位置
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
//属性
		Title_img.setFont(new Font("微软雅黑", 1, 25));
		Title_img.setForeground(Color.white);
		small_btn.setBackground(new Color(0, 0, 0, 0.3f));
		close_btn.setBackground(new Color(0, 0, 0, 0.3f));
		bgcolor.setBackground(new Color(255, 255, 255));

		roomID.setForeground(Color.gray);
		//roomID.setText("房间号");
		roomID.setText(roomId.equals("")? "房间号": roomId);
		roomID.setOpaque(false);// 透明背景
		roomID.setBorder(null);// 去掉边框
		roomID.setFont(new Font("微软雅黑", Font.PLAIN, 16));// 框内文字样式
		pass.setFont(new Font("微软雅黑", Font.PLAIN, 16));// 框内文字样式
		pass.setBorder(null);// 去掉边框

		pass.setOpaque(false);// 透明背景
		pass.setForeground(Color.gray);
		pass.setText("密码");
		pass.setEchoChar((char) 0);// 让密码显示出来

		//auto_lgin.setFont(new Font("微软雅黑", 0, 12));
		//Remember_pwd.setFont(new Font("微软雅黑", 0, 12));
		//find_pwd.setFont(new Font("微软雅黑", 0, 12));
		//sign_up.setFont(new Font("微软雅黑", 0, 12));
		join.setFont(new Font("微软雅黑", 0, 15));
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

//事件区域
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
		
	

		this.addMouseMotionListener(new MouseMotionListener() {// 窗体拖动事件
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});

		
		
		roomID.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {// 失去焦点
				ImageIcon iconOrigin = new ImageIcon("素材//房间1.png");
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				account_img.setIcon(new javax.swing.ImageIcon(newImage));
				lie1.setIcon(new javax.swing.ImageIcon("素材//直线2.png"));
				c = 0;
				if (roomID.getText().isEmpty()) {// 判断是否为空（为了设置默认提示语）
					roomID.setForeground(Color.gray);
					roomID.setText("房间号");
				}
			}

			public void focusGained(FocusEvent e) {// 得到焦点
				roomID.setForeground(Color.black);
				lie1.setIcon(new javax.swing.ImageIcon("素材//直线3.png"));
				a = 1;
				c = 1;
				b = 0;
				ImageIcon iconOrigin = new ImageIcon("素材//房间.png");
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				account_img.setIcon(new javax.swing.ImageIcon(newImage));
				if (roomID.getText().equals("房间号")) {
					roomID.setText("");
				} else {
					roomID.setText(roomID.getText());
					roomID.selectAll();
				}
			}
		});

		pass.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {// 失去焦点
				lie2.setIcon(new javax.swing.ImageIcon("素材//直线2.png"));// 失去焦点换图片
				password_img.setIcon(new javax.swing.ImageIcon("素材//密码.png"));
				d = 0;
				if (pass.getText().isEmpty()) {
					pass.setForeground(Color.gray);
					pass.setText("密码");
					pass.setEchoChar((char) 0);// 让密码显示出来
				}
			}

			public void focusGained(FocusEvent e) {// 得到焦点
				password_img.setIcon(new javax.swing.ImageIcon("素材//密码 (1).png"));
				lie2.setIcon(new javax.swing.ImageIcon("素材//直线3.png"));
				b = 1;
				a = 0;
				d = 1;
				pass.setForeground(Color.black);
				pass.setEchoChar('*');// 让用户输入看不见
				if (pass.getText().equals("密码")) {
					pass.setText("");
				} else {
					pass.setText(pass.getText());
				}
			}
		});

		getContentPane().setLayout(null);// 布局

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
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("素材\\透明照片.png"));// 窗体图标
		this.setLocationRelativeTo(null);// 保持居中
		this.setUndecorated(true);// 去顶部
		this.setFocusable(true);// 面板首先获得焦点
		this.setBackground(new Color(255, 255, 255));// 背景颜色
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);// 最顶层
		this.setVisible(true);// 显示
	}


	public void mouseClicked(MouseEvent e) {// 点击不恢复
	}


	//加入事件
	
	public void mousePressed(MouseEvent e) {// 点击后
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
			join.setFont(new Font("微软雅黑", 0, 14));
			

			String roomId = roomID.getText();
			String passWord = new String(pass.getPassword());
			try {
				System.out.println("JoinRoomFrame：加入房间");
				hallController.handler_search_room(token, roomId, passWord);
			}catch(RemoteException re) {
				re.printStackTrace();
			}
			dispose();
			
		}
	}

	public void mouseReleased(MouseEvent e) {// 点击时
		if (e.getSource() == submit || e.getSource() == join) {
			join.setFont(new Font("微软雅黑", 0, 15));
		}
	}

	public void mouseEntered(MouseEvent e) {// 悬停
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(true);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(true);
		} else if (e.getSource() == roomID) {
			if (a == 0 && c == 0) {
				lie1.setIcon(new javax.swing.ImageIcon("素材//直线4.png"));
			}
		} else if (e.getSource() == pass) {
			if (b == 0 && d == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("素材//直线4.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(Color.GRAY);
		}  else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("素材//二维码2.png"));
		}
	}

	public void mouseExited(MouseEvent e) {// 悬停后
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(false);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(false);
		} else if (e.getSource() == roomID) {
			if (a == 0) {
				lie1.setIcon(new javax.swing.ImageIcon("素材//直线2.png"));
			}
		} else if (e.getSource() == pass) {
			if (b == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("素材//直线2.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(new Color(170, 170, 170));
		}  else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("素材//二维码.png"));
		}

	}
}