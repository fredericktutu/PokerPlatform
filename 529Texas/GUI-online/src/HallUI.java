import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseMotionAdapter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;
class HallUI extends JFrame {
	private String token;
	private String name;
	//private HallGUI gui;

	private JPanel contentPane;
	private final JLabel lblNewLabel_1 = new JLabel("");
	public JLabel roomIDTitle = new JLabel("房间号");
	public JLabel roomTypeTitle = new JLabel("房间类型");
	public JLabel roomNumTitle = new JLabel("当前人数");
	public JLabel tableSizeTitle = new JLabel("房间容量");
	public JLabel gameLevelTitle = new JLabel("场次");
	public JLabel choiceTitle = new JLabel("操作");
	public JPanel scrollablePanel = new JPanel();
	public JScrollPane scroller;
	
	public TexasPokerFrame texasWindow = null;
	public boolean inGame;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		ActiveRoomInfo room1 = new ActiveRoomInfo("001", "德州扑克", 5, 7, "困难");
		ActiveRoomInfo room2 = new ActiveRoomInfo("002", "德州扑克", 2, 5, "容易");
		//ActiveRoomInfo room3 = new ActiveRoomInfo("003", "德州扑克", 5, 5, "中等");
		//ActiveRoomInfo room4 = new ActiveRoomInfo("001", "德州扑克", 5, 7, "困难");
		//ActiveRoomInfo room5 = new ActiveRoomInfo("002", "德州扑克", 2, 5, "容易");
		//ActiveRoomInfo room6 = new ActiveRoomInfo("003", "德州扑克", 5, 5, "中等");
		ArrayList<ActiveRoomInfo> rooms = new ArrayList<ActiveRoomInfo>();
		rooms.add(room1);
		rooms.add(room2);
		//rooms.add(room3);
		//rooms.add(room4);
		//rooms.add(room5);
		//rooms.add(room6);
		//for (int i = 0; i < 15; ++ i){
		//	rooms.add(new ActiveRoomInfo(String.valueOf(i), "德州扑克", 5, 7, "中等"));

		//}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final HallUI window = new HallUI("","");
					window.setVisible(true);
					window.update_display_rooms(rooms);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HallUI(String token, String name){
		this.token = token;
		this.name = name;
		initialize();
		/*
		try {
			this.gui = new HallGUI(token, this);
		}catch(RemoteException e) {
			;
		}
		*/
		
	}

	/**
	 * Initialize the contents of the frame. This is Graphical
	 */
	private void initialize() {
		setUndecorated(false);
		setTitle("游戏大厅");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					System.out.println("退出大厅");
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					String res =TexasHttpUtils.request("localhost:8888", "server", "exit", obj.toString());
					System.out.println(res);
					HallUI.this.dispose();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

		setBounds(100, 100, 992, 632);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel useinfo = new JLabel("用户信息");
		useinfo.setFont(new Font("幼圆", Font.PLAIN, 24));
		useinfo.setBounds(822, 28, 112, 46);
		contentPane.add(useinfo);
		
		Point origin = new Point();
		
		JLabel quit = new JLabel("");
		quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quit.setIcon(new ImageIcon("素材//退出_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quit.setIcon(new ImageIcon("素材//退出.png"));
			}
		});
		quit.setIcon(new ImageIcon("素材//退出.png"));
		quit.setBackground(Color.WHITE);
		quit.setForeground(Color.BLACK);
		quit.setBounds(892, 555, 42, 30);
		contentPane.add(quit);
		
		JLabel create = new JLabel("");
		create.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				create.setIcon(new ImageIcon("素材//创建游戏_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				create.setIcon(new ImageIcon("素材//创建游戏_2.png"));
			}
		});
		create.setIcon(new ImageIcon("素材//创建游戏_2.png"));
		create.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
				 //HallUI.this.dispose();
				 if(!HallUI.this.inGame) {
					new CreateRoomFrame(token, HallUI.this);
				 }
		    	 
		    }  
		});
		create.setBounds(59, 522, 149, 61);
		contentPane.add(create);
		
		JLabel join = new JLabel("New label");
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				join.setIcon(new ImageIcon("素材//加入游戏_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				join.setIcon(new ImageIcon("素材//加入游戏.png"));
			}
		});
		join.setIcon(new ImageIcon("素材//加入游戏.png"));
		join.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  

				 //HallUI.this.dispose();
				 if(HallUI.this.texasWindow == null) {
					new JoinRoomFrame(token,"" , HallUI.this);
				 }
		    	 
		    }  
		});
		join.setBounds(246, 522, 149, 59);
		contentPane.add(join);
		
		JLabel match = new JLabel("New label");
		match.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				match.setIcon(new ImageIcon("素材//匹配游戏_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				match.setIcon(new ImageIcon("素材//匹配游戏.png"));
			}
		});
		match.setIcon(new ImageIcon("素材//匹配游戏.png"));
		match.setBounds(444, 522, 149, 61);
		contentPane.add(match);
		
		JLabel refresh  = new JLabel("New label");
		ImageIcon iconOrigin = new ImageIcon("素材//refresh.png");
		Image image = iconOrigin.getImage();
		Image newImage = image.getScaledInstance(49, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
		ImageIcon iconOrigin1 = new ImageIcon("素材//refresh_pressed.png");
		Image image1 = iconOrigin1.getImage();
		Image newImage1 = image1.getScaledInstance(49, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(newImage1);
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				refresh.setIcon(icon1);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				refresh.setIcon(icon);
			}
		});
		refresh.setIcon(icon);
		refresh.setBounds(644, 540, 49, 35);
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("展示房间");
				JSONObject obj = new JSONObject();
				obj.put("token", token);
				String res =TexasHttpUtils.request("localhost:8888", "hall", "display", obj.toString());
				//System.out.println(res);
				TexasHttpUtils.updateByJEvent(res, HallUI.this);
				/*
				try{
					//gui.hallController.handler_display_rooms(token);
				}catch(RemoteException re) {
					;
				}
				*/
				
			}
			
		});
		contentPane.add(refresh);

		JLabel assistant  = new JLabel("New label");
		ImageIcon iconOrigin2 = new ImageIcon("素材//help1.png");
		Image image2 = iconOrigin2.getImage();
		Image newImage2 = image2.getScaledInstance(49, 49, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(newImage2);
		ImageIcon iconOrigin3 = new ImageIcon("素材//help.png");
		Image image3 = iconOrigin3.getImage();
		Image newImage3 = image3.getScaledInstance(49, 49, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon3 = new ImageIcon(newImage3);
		assistant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				assistant.setIcon(icon3);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				assistant.setIcon(icon2);
			}
		});
		assistant.setIcon(icon2);
		assistant.setBounds(750, 530, 49, 49);
		assistant.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
//		    	 HALL.this.dispose();
		    	final RuleFrame window = new RuleFrame();
				window.frame.setVisible(true);
		    }  
		});
		contentPane.add(assistant);
		
		JLabel label = new JLabel("用户名");
		label.setFont(new Font("幼圆", Font.PLAIN, 15));
		label.setBounds(807, 84, 58, 15);
		contentPane.add(label);
		/*
		JLabel score = new JLabel("积分");
		score.setFont(new Font("幼圆", Font.PLAIN, 15));
		score.setBounds(807, 120, 58, 15);
		contentPane.add(score);
		*/

		JLabel username_text = new JLabel(this.name);
		username_text.setFont(new Font("幼圆", Font.PLAIN, 15));
		username_text.setBounds(876, 84, 58, 15);
		contentPane.add(username_text);
		
		//JLabel score_text = new JLabel("New label");
		//score_text.setFont(new Font("幼圆", Font.PLAIN, 15));
		//score_text.setBounds(876, 120, 58, 15);
		//contentPane.add(score_text);
	
		scroller = new JScrollPane(scrollablePanel);
		scroller.setToolTipText("");
		scroller.setBounds(31, 28, 756, 484);
		contentPane.add(scroller);


		
		
	}
	

	public void update_message(String s) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JOptionPane.showMessageDialog(null, s);
				
			}
		});
	}

	public void update_switch_page(String roomId, String roomType)  {
		if(roomType.equals("德州扑克")) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						HallUI.this.inGame = true;
						HallUI.this.texasWindow = new TexasPokerFrame(token,HallUI.this, roomId);
						HallUI.this.setVisible(false);
						texasWindow.frame.setVisible(true);
						
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}


	public void update_display_rooms(ArrayList<ActiveRoomInfo> rooms) {
		scrollablePanel.removeAll();
		scrollablePanel.repaint();
		this.scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
		final JPanel header = new JPanel(new FlowLayout());
		final Dimension itemDim = new Dimension(756, 50);
		final Dimension dim = new Dimension(100, 20);
		header.setSize(itemDim);
		header.setMaximumSize(itemDim);
		header.setMinimumSize(itemDim);
		header.setPreferredSize(itemDim);
		roomIDTitle.setSize(dim);
		roomIDTitle.setMaximumSize(dim);
		roomIDTitle.setMinimumSize(dim);
		roomIDTitle.setPreferredSize(dim);
		roomTypeTitle.setSize(dim);
		roomTypeTitle.setMaximumSize(dim);
		roomTypeTitle.setMinimumSize(dim);
		roomTypeTitle.setPreferredSize(dim);
		roomNumTitle.setSize(dim);
		roomNumTitle.setMaximumSize(dim);
		roomNumTitle.setMinimumSize(dim);
		roomNumTitle.setPreferredSize(dim);
		tableSizeTitle.setSize(dim);
		tableSizeTitle.setMaximumSize(dim);
		tableSizeTitle.setMinimumSize(dim);
		tableSizeTitle.setPreferredSize(dim);
		gameLevelTitle.setSize(dim);
		gameLevelTitle.setMaximumSize(dim);
		gameLevelTitle.setMinimumSize(dim);
		gameLevelTitle.setPreferredSize(dim);
		choiceTitle.setSize(dim);
		choiceTitle.setMaximumSize(dim);
		choiceTitle.setMinimumSize(dim);
		choiceTitle.setPreferredSize(dim);
		header.add(roomIDTitle);
		header.add(roomTypeTitle); 
		header.add(roomNumTitle);
		header.add(tableSizeTitle);
		header.add(gameLevelTitle);
		header.add(choiceTitle);
		this.scrollablePanel.add(header);
		
		for(int i = 0; i < rooms.size(); ++i) {
			final String roomID = rooms.get(i).roomID;
			final String roomType = rooms.get(i).roomType;
			final String roomNum = String.valueOf(rooms.get(i).roomNum);
			final String tableSize = String.valueOf(rooms.get(i).tableSize);
			final String gameLevel = rooms.get(i).gameLevel;
			final JPanel item = new JPanel(new FlowLayout());
			item.setSize(itemDim);
			item.setMaximumSize(itemDim);
			item.setMinimumSize(itemDim);
			item.setPreferredSize(itemDim);
			final JLabel roomIDLabel = new JLabel(roomID);
			roomIDLabel.setSize(dim);
			roomIDLabel.setMaximumSize(dim);
			roomIDLabel.setMinimumSize(dim);
			roomIDLabel.setPreferredSize(dim);
			item.add(roomIDLabel);
			final JLabel roomTypeLabel = new JLabel(roomType);
			roomTypeLabel.setSize(dim);
			roomTypeLabel.setMaximumSize(dim);
			roomTypeLabel.setMinimumSize(dim);
			roomTypeLabel.setPreferredSize(dim);
			item.add(roomTypeLabel);
			final JLabel roomNumLabel = new JLabel(roomNum);
			roomNumLabel.setSize(dim);
			roomNumLabel.setMaximumSize(dim);
			roomNumLabel.setMinimumSize(dim);
			roomNumLabel.setPreferredSize(dim);
			item.add(roomNumLabel);
			final JLabel tableSizeLabel = new JLabel(tableSize);
			tableSizeLabel.setSize(dim);
			tableSizeLabel.setMaximumSize(dim);
			tableSizeLabel.setMinimumSize(dim);
			tableSizeLabel.setPreferredSize(dim);
			item.add(tableSizeLabel);
			final JLabel gameLevelLabel = new JLabel(gameLevel);
			gameLevelLabel.setSize(dim);
			gameLevelLabel.setMaximumSize(dim);
			gameLevelLabel.setMinimumSize(dim);
			gameLevelLabel.setPreferredSize(dim);
			item.add(gameLevelLabel);
			final JLabel choiceLabel = new JLabel();
			choiceLabel.setSize(dim);
			choiceLabel.setMaximumSize(dim);
			choiceLabel.setMinimumSize(dim);
			choiceLabel.setPreferredSize(dim);
			final JButton joinButton = new JButton("加入");
			joinButton.setSize(dim);
			joinButton.setMaximumSize(dim);
			joinButton.setMinimumSize(dim);
			joinButton.setPreferredSize(dim);
			choiceLabel.add(joinButton);
			joinButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					try {
						 new JoinRoom(token,  HallUI.this, roomID);
						
					} catch (final Exception ev) {
						ev.printStackTrace();
					}
				}
			});
			item.add(choiceLabel);
			this.scrollablePanel.add(item);
			
			
		}
		
		this.scrollablePanel.validate();
		this.scrollablePanel.revalidate();
		this.scroller.validate();
		this.scroller.revalidate();
		
	}

	public void backFromGame() {
		
		this.inGame = false;
		this.texasWindow = null;
		this.setVisible(true);
	}
	

}
