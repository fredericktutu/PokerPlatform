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
	JLabel small_btn, close_btn,  lie2;// ��ɫ��|��
	//JTextField roomID;// �˺�
	//JTextField tableSize;//�ǳ�
	JPasswordField pass;// ����
	JPanel bgcolor;// ��
	JLabel  password_img, ku1, ku2, gou1, gou2;// ����ͼ
	JLabel auto_lgin, Remember_pwd, find_pwd, create;// �Զ���¼����ס���룬�һ����룬ע���˺ţ���¼
	static Point origin = new Point();// ���������ڿ��϶�����
	int a = 0, b = 0, c = 0, d = 0;// ������
	int f = 0, g = 0, h = 0, j = 0;// ���ơ�
	JLabel submit, ma;// ����
	String[] games, difficulty, modes, session, Num;
	JComboBox roomType, gameLevel, mode, tableSize;
	JLabel numLabel = new JLabel("����:");
	JLabel typeLabel = new JLabel("����:");
	JLabel modeLabel = new JLabel("ģʽ:");
	JLabel sessionLabel = new JLabel("�Ѷ�:");

	public CreateRoomFrame(String token, IHallController hallController) {
		System.out.println("�����������");
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
		//roomID = new JTextField();
		//tableSize = new JTextField();
		pass = new JPasswordField();
		//account_img = new JLabel(new ImageIcon("�ز�//poker.jpg"));
		//name_img = new JLabel(new ImageIcon("�ز�//poker.jpg"));
		password_img = new JLabel(new ImageIcon("�ز�//����.png"));
		//lie1 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		lie2 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		//lie3 = new JLabel(new ImageIcon("�ز�//ֱ��2.png"));
		bgcolor = new JPanel();
		//ku1 = new JLabel(new ImageIcon("�ز�//���.png"));
		//ku2 = new JLabel(new ImageIcon("�ز�//���.png"));
		//gou1 = new JLabel(new ImageIcon("�ز�//�Թ�.png"));
		//gou2 = new JLabel(new ImageIcon("�ز�//�Թ�.png"));
		//auto_lgin = new JLabel("�Զ���¼");
		//Remember_pwd = new JLabel("��ס����");
		//find_pwd = new JLabel("�һ�����");
		//sign_up = new JLabel("�����˺�>");
		create = new JLabel("����");
		submit = new JLabel();
	    games = new String[] {"�����˿�"};
	    Num = new String[] {"2","3","4","5"};
		difficulty = new String[] {"����","�е�","����"};
		modes = new String[] {"�˻�ģʽ", "����ģʽ"};
		session = new String[] {"����","�м�","�߼�"};
		roomType = new JComboBox(games);
	    gameLevel = new JComboBox(difficulty);
	    mode = new JComboBox(modes);
	    tableSize = new JComboBox(Num);
	    
		

//λ��
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
//����
		Title_img.setFont(new Font("΢���ź�", 1, 25));
		Title_img.setForeground(Color.white);
		small_btn.setBackground(new Color(0, 0, 0, 0.3f));
		close_btn.setBackground(new Color(0, 0, 0, 0.3f));
		bgcolor.setBackground(new Color(255, 255, 255));

		//roomID.setForeground(Color.gray);
		//roomID.setText("�����");
		//roomID.setOpaque(false);// ͸������
		//roomID.setBorder(null);// ȥ���߿�
		//roomID.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		
		//tableSize.setForeground(Color.gray);
		//tableSize.setText("������С");
		//tableSize.setOpaque(false);// ͸������
		//tableSize.setBorder(null);// ȥ���߿�
		//tableSize.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		numLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		typeLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		modeLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		sessionLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		pass.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ����������ʽ
		pass.setBorder(null);// ȥ���߿�
		pass.setOpaque(false);// ͸������
		pass.setForeground(Color.gray);
		pass.setText("����");
		pass.setEchoChar((char) 0);// ��������ʾ����

		mode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String choice = (String)mode.getSelectedItem();
				if(choice.equals("����ģʽ")) {
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
		
		roomType.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		gameLevel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		mode.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		tableSize.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		create.setFont(new Font("΢���ź�", 0, 15));
		create.setForeground(Color.white);

		submit.setBackground(new Color(5, 186, 251));
		submit.setOpaque(true);


//�¼�����
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
				int pnum = Integer.parseInt(number); //�������
	
				String gameType = (String)roomType.getSelectedItem(); //��Ϸ����
				String gameMode = (String)mode.getSelectedItem(); //��Ϸģʽ
				String levelType = (String)gameLevel.getSelectedItem(); //��Ϸ����
				String passWord = pass.getPassword().toString();
				RoomOption option = make_roomOption(pnum, passWord, gameType, gameMode, levelType);
				try {
					System.out.println("��������");
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

		this.addMouseMotionListener(new MouseMotionListener() {// �����϶��¼�
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
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
			this.dispose();
		} else if (e.getSource() == submit || e.getSource() == create) {
			create.setFont(new Font("΢���ź�", 0, 14));
	
		}
	}

	public void mouseReleased(MouseEvent e) {// ���ʱ
		if (e.getSource() == submit || e.getSource() == create) {
			create.setFont(new Font("΢���ź�", 0, 15));
		}
	}

	public void mouseEntered(MouseEvent e) {// ��ͣ
		if (e.getSource() == small_mig) {
			small_btn.setOpaque(true);
		} else if (e.getSource() == close_img) {
			close_btn.setOpaque(true);
		}  else if (e.getSource() == pass) {
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
		} else if (e.getSource() == pass) {
			if (b == 0) {
				lie2.setIcon(new javax.swing.ImageIcon("�ز�//ֱ��2.png"));
			}
		} else if (e.getSource() == find_pwd) {
			find_pwd.setForeground(new Color(170, 170, 170));
		} else if (e.getSource() == ma) {
			ma.setIcon(new javax.swing.ImageIcon("�ز�//��ά��.png"));
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
		if(gameType.equals("�����˿�")) {
			opt.gameType = RoomOption.GAME_TEXAS;
		} else {
			opt.gameType = RoomOption.GAME_OTHER;
		}

		switch(gameMode) {
			case "�˻�ģʽ":
				opt.modeType = RoomOption.MODE_SINGLE;
				switch(levelType) {
					case "����":
						opt.difficultyType = RoomOption.DIF_EASY;break;
					case "�е�":
						opt.difficultyType = RoomOption.DIF_MEDIUM;break;
					case "����":
						opt.difficultyType = RoomOption.DIF_HARD;break;
					default:
						break;
				}
				break;
			case "����ģʽ":
				opt.modeType = RoomOption.MODE_ONLINE;
				switch(levelType) {
					case "����":
						opt.levelType = RoomOption.LEVEL_PRIMARY;break;
					case "�м�":
						opt.levelType = RoomOption.LEVEL_MEDIUM;break;
					case "�߼�":
						opt.levelType = RoomOption.LEVEL_ADVANCE;break;
					default:
						break;
				}
				break;
		}
		return opt;
	}
}