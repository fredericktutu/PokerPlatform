import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import java.rmi.Naming;

public class CreateRoomFrame extends JFrame implements MouseListener {
	String token;
	IHallController hallController;
	public static void main(String[] args) {

		new CreateRoomFrame("some what", null);

	}
	
	
	JLabel bacgrangd, small_mig, close_img, poker_img, Title_img, tu;// 
	JLabel small_btn, close_btn,  lie2;// 暗色块|线
	//JTextField roomID;// 账号
	//JTextField tableSize;//昵称
	JPasswordField pass;// 密码
	JPanel bgcolor;// 白
	JLabel  password_img, ku1, ku2, gou1, gou2;// 缩略图
	JLabel auto_lgin, Remember_pwd, find_pwd, create;// 自动登录，记住密码，找回密码，注册账号，登录
	static Point origin = new Point();// 变量，用于可拖动窗体
	int a = 0, b = 0, c = 0, d = 0;// 控制线
	int f = 0, g = 0, h = 0, j = 0;// 控制√
	JLabel submit, ma;// 背景
	String[] games, difficulty, modes, session, Num;
	JComboBox roomType, gameLevel, mode, tableSize;
	JLabel numLabel = new JLabel("人数:");
	JLabel typeLabel = new JLabel("类型:");
	JLabel modeLabel = new JLabel("模式:");
	JLabel sessionLabel = new JLabel("难度:");

	public CreateRoomFrame(String token, IHallController hallController) {
		System.out.println("创建房间界面");
		this.token = token;
		this.hallController = hallController;
//实例化
		bacgrangd = new JLabel(new ImageIcon("素材//timg.jpeg"));
		small_mig = new JLabel(new ImageIcon("素材//最小化.png"));
		close_img = new JLabel(new ImageIcon("素材//关闭.png"));
		//poker_img = new JLabel(new ImageIcon("素材//poker.jpg"));
		Title_img = new JLabel("创建");
		small_btn = new JLabel();
		close_btn = new JLabel();// 暗调
		//tu = new JLabel(new ImageIcon("素材//头像.png"));
		//roomID = new JTextField();
		//tableSize = new JTextField();
		pass = new JPasswordField();
		//account_img = new JLabel(new ImageIcon("素材//poker.jpg"));
		//name_img = new JLabel(new ImageIcon("素材//poker.jpg"));
		password_img = new JLabel(new ImageIcon("素材//密码.png"));
		//lie1 = new JLabel(new ImageIcon("素材//直线2.png"));
		lie2 = new JLabel(new ImageIcon("素材//直线2.png"));
		//lie3 = new JLabel(new ImageIcon("素材//直线2.png"));
		bgcolor = new JPanel();
		//ku1 = new JLabel(new ImageIcon("素材//框框.png"));
		//ku2 = new JLabel(new ImageIcon("素材//框框.png"));
		//gou1 = new JLabel(new ImageIcon("素材//对勾.png"));
		//gou2 = new JLabel(new ImageIcon("素材//对勾.png"));
		//auto_lgin = new JLabel("自动登录");
		//Remember_pwd = new JLabel("记住密码");
		//find_pwd = new JLabel("找回密码");
		//sign_up = new JLabel("已有账号>");
		create = new JLabel("创建");
		submit = new JLabel();
	    games = new String[] {"德州扑克"};
	    Num = new String[] {"2","3","4","5"};
		difficulty = new String[] {"容易","中等","困难"};
		modes = new String[] {"人机模式", "联机模式"};
		session = new String[] {"初级","中级","高级"};
		roomType = new JComboBox(games);
	    gameLevel = new JComboBox(difficulty);
	    mode = new JComboBox(modes);
	    tableSize = new JComboBox(Num);
	    
		

//位置
		bacgrangd.setBounds(-29, -123, 500, 250);
		small_mig.setBounds(364, 2, 32, 32);
		close_img.setBounds(396, 3, 32, 32);
		//poker_img.setBounds(10, 10, 45, 32);
		Title_img.setBounds(10, 5, 71, 47);
		small_btn.setBounds(361, 0, 35, 35);
		close_btn.setBounds(395, 0, 35, 35);
		//tu.setBounds(170, 80, 90, 85);
		//roomID.setBounds(130, 120, 180, 40);
		tableSize.setBounds(160, 160, 180, 40);
		pass.setBounds(130, 320, 180, 40);
		roomType.setBounds(160, 200, 180, 40);
		gameLevel.setBounds(160, 280, 180, 40);
		mode.setBounds(160, 240, 180, 40);
		//name_img.setBounds(100, 170, 20, 20);
		//account_img.setBounds(100, 130, 20, 20);
		password_img.setBounds(100, 330, 20, 20);
		//lie1.setBounds(100, 190, 240, 10);
		//lie3.setBounds(100, 150, 240, 10);
		lie2.setBounds(100, 350, 240, 10);
		bgcolor.setBounds(0, 125, 500, 300);
		//ku1.setBounds(100, 250, 20, 20);
		//ku2.setBounds(190, 250, 20, 20);
		//gou1.setBounds(106, 255, 10, 10);
		//gou2.setBounds(196, 255, 10, 10);
		//auto_lgin.setBounds(125, 250, 80, 20);
		//Remember_pwd.setBounds(215, 250, 80, 20);
		//find_pwd.setBounds(288, 250, 80, 20);
		//sign_up.setBounds(15, 300, 80, 20);
		create.setBounds(206, 375, 80, 20);
		submit.setBounds(100, 370, 242, 35);
		numLabel.setBounds(100, 160, 60, 40);
		typeLabel.setBounds(100, 200, 60, 40);
		modeLabel.setBounds(100, 240, 60, 40);
		sessionLabel.setBounds(100, 280, 60, 40);
//属性
		Title_img.setFont(new Font("微软雅黑", 1, 25));
		Title_img.setForeground(Color.white);
		small_btn.setBackground(new Color(0, 0, 0, 0.3f));
		close_btn.setBackground(new Color(0, 0, 0, 0.3f));
		bgcolor.setBackground(new Color(255, 255, 255));

		//roomID.setForeground(Color.gray);
		//roomID.setText("房间号");
		//roomID.setOpaque(false);// 透明背景
		//roomID.setBorder(null);// 去掉边框
		//roomID.setFont(new Font("微软雅黑", Font.PLAIN, 16));// 框内文字样式
		
		//tableSize.setForeground(Color.gray);
		//tableSize.setText("牌桌大小");
		//tableSize.setOpaque(false);// 透明背景
		//tableSize.setBorder(null);// 去掉边框
		//tableSize.setFont(new Font("微软雅黑", Font.PLAIN, 16));// 框内文字样式
		numLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		typeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		modeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		sessionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		pass.setFont(new Font("微软雅黑", Font.PLAIN, 16));// 框内文字样式
		pass.setBorder(null);// 去掉边框
		pass.setOpaque(false);// 透明背景
		pass.setForeground(Color.gray);
		pass.setText("密码");
		pass.setEchoChar((char) 0);// 让密码显示出来

		mode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String choice = (String)mode.getSelectedItem();
				if(choice.equals("联机模式")) {
					gameLevel.removeAllItems();
				    for(String s:session){
				        gameLevel.addItem(s);
				    }	
				}else {
					gameLevel.removeAllItems();
				    for(String s:difficulty){
				        gameLevel.addItem(s);
				    }		
				}	
				}	
		});
		
		roomType.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		gameLevel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mode.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		tableSize.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		create.setFont(new Font("微软雅黑", 0, 15));
		create.setForeground(Color.white);

		submit.setBackground(new Color(5, 186, 251));
		submit.setOpaque(true);


//事件区域
		small_mig.addMouseListener(this);
		close_img.addMouseListener(this);
		//roomID.addMouseListener(this);
		//tableSize.addMouseListener(this);
		pass.addMouseListener(this);
		//auto_lgin.addMouseListener(this);
		//Remember_pwd.addMouseListener(this);
		//find_pwd.addMouseListener(this);
		//sign_up.addMouseListener(this);
		//ku1.addMouseListener(this);
		//ku2.addMouseListener(this);
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String number = (String) tableSize.getSelectedItem();
				int pnum = Integer.parseInt(number); //玩家人数
	
				String gameType = (String)roomType.getSelectedItem(); //游戏类型
				String gameMode = (String)mode.getSelectedItem(); //游戏模式
				String levelType = (String)gameLevel.getSelectedItem(); //游戏级别
				String passWord = pass.getPassword().toString();
				RoomOption option = make_roomOption(pnum, passWord, gameType, gameMode, levelType);
				try {
					System.out.println("创建房间");
					CreateRoomFrame.this.dispose();
					hallController.handler_create_room(token, option);
					//CreateRoomFrame.this.dispose();
					//final TexasPokerFrame window = new TexasPokerFrame();
					//window.frame.setVisible(true);
					//window.updatePlayerPanels(i);
					//window.myselfPanel.updateName(name);
					
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		this.addMouseListener(this);

		this.addMouseMotionListener(new MouseMotionListener() {// 窗体拖动事件
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
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

		
		getContentPane().setLayout(null);
		//this.setLayout(new BorderLayout());
		getContentPane().add(small_mig);
		getContentPane().add(close_img);
		getContentPane().add(Title_img);
		//getContentPane().add(poker_img);
		getContentPane().add(small_btn);
		getContentPane().add(close_btn);
		//getContentPane().add(tu);
		//getContentPane().add(lie1);
		getContentPane().add(lie2);
		//getContentPane().add(lie3);
		//getContentPane().add(roomID);
		getContentPane().add(tableSize);
		getContentPane().add(numLabel);
		getContentPane().add(pass);
		//getContentPane().add(account_img);
		//getContentPane().add(name_img);
		getContentPane().add(password_img);
		getContentPane().add(roomType);
		getContentPane().add(typeLabel);
		getContentPane().add(mode);
		getContentPane().add(modeLabel);
		getContentPane().add(gameLevel);
		getContentPane().add(sessionLabel);
		//getContentPane().add(gou1);
		//getContentPane().add(gou2);
		//getContentPane().add(ku1);
		//getContentPane().add(ku2);
		//getContentPane().add(auto_lgin);
		//getContentPane().add(Remember_pwd);
		//getContentPane().add(find_pwd);
		//getContentPane().add(sign_up);
		getContentPane().add(create);
		getContentPane().add(submit);
		getContentPane().add(bgcolor);
		getContentPane().add(bacgrangd);
				
		//this.setLayout(new BorderLayout());
		this.setSize(430, 425);
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

	public void mousePressed(MouseEvent e) {// 点击后
		if (e.getSource() == small_mig) {
			setExtendedState(JFrame.ICONIFIED);
		} else if (e.getSource() == this) {
			origin.x = e.getX();
			origin.y = e.getY();
		} else if (e.getSource() == close_img) {
			this.dispose();
		} else if (e.getSource() == submit || e.getSource() == create) {
			create.setFont(new Font("微软雅黑", 0, 14));
	
		}
	}

	public void mouseReleased(MouseEvent e) {// 点击时
		if (e.getSource() == submit || e.getSource() == create) {
			create.setFont(new Font("微软雅黑", 0, 15));
		}
	}

	public void mouseEntered(MouseEvent e) {// 悬停
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(true);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(true);
		}  else if (e.getSource() == pass) {
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
		} else if (e.getSource() == pass) {
			if (b == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("素材//直线2.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(new Color(170, 170, 170));
		} else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("素材//二维码.png"));
		}

	}

	public RoomOption make_roomOption(int pnum, String passWord, String gameType, String gameMode, String levelType) {
		RoomOption opt = new RoomOption();
		opt.roomPassport = passWord;
		switch(pnum) {
			case 2: 
				opt.personType = RoomOption.TWO_PERSON;break;
			case 3: 
				opt.personType = RoomOption.THREE_PERSON;break;
			case 4:
				opt.personType = RoomOption.FOUR_PERSON;break;
			case 5:
				opt.personType = RoomOption.FIVE_PERSON;break;
			default: 
				break;
		}
		if(gameType.equals("德州扑克")) {
			opt.gameType = RoomOption.GAME_TEXAS;
		} else {
			opt.gameType = RoomOption.GAME_OTHER;
		}

		switch(gameMode) {
			case "人机模式":
				opt.modeType = RoomOption.MODE_SINGLE;
				switch(levelType) {
					case "容易":
						opt.difficultyType = RoomOption.DIF_EASY;break;
					case "中等":
						opt.difficultyType = RoomOption.DIF_MEDIUM;break;
					case "困难":
						opt.difficultyType = RoomOption.DIF_HARD;break;
					default:
						break;
				}
				break;
			case "联机模式":
				opt.modeType = RoomOption.MODE_ONLINE;
				switch(levelType) {
					case "初级":
						opt.levelType = RoomOption.LEVEL_PRIMARY;break;
					case "中级":
						opt.levelType = RoomOption.LEVEL_MEDIUM;break;
					case "高级":
						opt.levelType = RoomOption.LEVEL_ADVANCE;break;
					default:
						break;
				}
				break;
		}
		return opt;
	}
}