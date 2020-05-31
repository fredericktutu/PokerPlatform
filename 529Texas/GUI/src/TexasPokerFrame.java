import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import javafx.scene.control.ScrollBar;

import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import org.json.simple.JSONObject;


import javax.swing.Timer;
import java.awt.event.*;

public class TexasPokerFrame  {
	public final JFrame frame = new JFrame();
	public JPanel panelStopwatch = new JPanel();
	public JPanel panelPubCard = new JPanel();
	//	public JPanel panelPlayerLog = new JPanel();
	public JPanel westPanel = new JPanel();
	public JPanel eastPanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public JLabel stopwatch = new JLabel("√Î±Ì");
	//public JLabel[][] publicCardLabels = new JLabel[5][];
	public JLabel[][] publicCardLabels = new JLabel[4][13];
	//	public JLabel playerLogLabel = new JLabel("");
	public JPanel southPanel = new JPanel();
	public JPlayerMyselfPanel myselfPanel = new JPlayerMyselfPanel();
	public  ArrayList<JPlayerPanel> player = new ArrayList<JPlayerPanel>();
	public ArrayList<Integer> playerSeats = new ArrayList<Integer>(); 
	public JPanel scrollablePanel = new JPanel();
	public JScrollPane scroller;
	
	public int addMin;
	public int addMax;
	public TexasGameGUI gui;
	public String token;
	//public IHallController hallController;
	public int mySeat = -1;
	public HallUI parent;

	public Timer timer;
	public ConnectJob job;

	/*
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final TexasPokerFrame window = new TexasPokerFrame("" ,  null);
					window.frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TexasPokerFrame(String token, HallUI parent) {
		this.token = token;
		//this.hallController = hallController;
		this.parent = parent;
		/** Initialize Card I
		 * cons */
		for (int suit = 0; suit < publicCardLabels.length; ++ suit){
			//int numberOfCardsInThisSuit = suit < 4 ? 13 : 2;
			int numberOfCardsInThisSuit = 13;
			publicCardLabels[suit] = new JLabel[numberOfCardsInThisSuit];
			for (int face = 0; face <= numberOfCardsInThisSuit ; face++){
				ImageIcon iconOrigin = new ImageIcon(
					"./Àÿ≤ƒ/card_" + 
					String.valueOf(suit) + "_" +
					String.valueOf(face % 13) + ".png"
				);
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(117, 117, java.awt.Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(newImage);
				
				JLabel label = new JLabel();
				label.setIcon(icon);
				Dimension dim = new Dimension(117, 117);
				label.setSize(dim);
				label.setMaximumSize(dim);
				label.setMinimumSize(dim);
				label.setPreferredSize(dim);
				
				publicCardLabels[suit][face % 13] = label;
			}
		}

		initialize();
		updateMyPlayerAction(false, false, false, false, 0, 0, 0);
		updateMyPlayerReadyAction(false);
		//¥¥Ω®“ª∏ˆ‘∂≥ÃΩ”ø⁄µƒgui
		/*
		try{
			this.gui = new TexasGameGUI(token, this);
		}catch(RemoteException re) {
			re.printStackTrace();
		}
		*/
		
		myselfPanel.token = token;
		//myselfPanel.controller = gui.texasGameController;

		
		/** test update public cards */
		/*
		Card card1 = new Card(1, 4);  
		Card card2 = new Card(2, 4);  
		Card card3 = new Card(3, 4);   
		Card card4 = new Card(3, 7);  
		Card card5 = new Card(2, 1);
		ArrayList<Card> testPublicCards = new ArrayList<Card>();
		testPublicCards.add(card1);
		testPublicCards.add(card2);
		testPublicCards.add(card3);
		testPublicCards.add(card4);
		testPublicCards.add(card5);

		updatePlayerPanels(1,4);
		getPlayerPanel(0).updateName("¬ﬁ…Ò");//0∫≈
		getPlayerPanel(2).updateName("¿Ó‘Û”Í"); //2∫≈
		getPlayerPanel(3).updateName("—Óº—¿÷"); //3∫≈

		this.updatePublicCards(testPublicCards);
		ArrayList<String> playerLog = new ArrayList<String>();
		for (int i = 0; i < 20; ++ i){
			playerLog.add(new String( String.valueOf(i)+"Ãı»’÷æ" ));
		}
		this.updatePlayerLog(playerLog);
		myselfPanel.updateButton(true, true, true, true, 0, 0, 0);
		myselfPanel.updateName("’¬≤©Œƒ");
		myselfPanel.updateChip(1000000);
		myselfPanel.updateAsset(1000000);
		updateMyPlayerReady(false);
		*/
	
		/**end test */

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					frame.setVisible(false);
					System.out.println("ÕÀ≥ˆ∑øº‰");
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					String res =TexasHttpUtils.request("localhost:8888", "hall", "exit", obj.toString());
					System.out.println(res);
					TexasHttpUtils.updateByJEvent(res, TexasPokerFrame.this.parent);
					TexasPokerFrame.this.timer.stop();
					frame.dispose();
					
					TexasPokerFrame.this.parent.backFromGame();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			});
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//		Point origin = new Point();
		frame.setTitle("≈∆◊¿");
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(false);

		// Border blackline = BorderFactory.createLineBorder(Color.black);

		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		westPanel.setSize(350, 650);
		//eastPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

		frame.getContentPane().add(westPanel, BorderLayout.WEST);

		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setSize(600, 650);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.white));

		panelStopwatch.add(stopwatch);
		centerPanel.add(panelStopwatch);
		//		stopwatch.setSize(600, 50);
		//		panelStopwatch.setSize(600, 50);
		//		panelPubCard.setSize(600,400 );
		//		scroller.setSize(600,200 );
		Dimension cardDim = new Dimension(600, 250);
		panelPubCard.setSize(cardDim);
		panelPubCard.setMaximumSize(cardDim);
		panelPubCard.setMinimumSize(cardDim);
		panelPubCard.setPreferredSize(cardDim);
		//		scrollablePanel.setSize(600, 50);
		stopwatch.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 20));
		stopwatch.setForeground(Color.WHITE);
		//		playerLogLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 20));
		//		playerLogLabel.setForeground(Color.WHITE);
		Dimension watchDim = new Dimension(600, 30);
		panelStopwatch.setSize(watchDim);
		panelStopwatch.setMaximumSize(watchDim);
		panelStopwatch.setMinimumSize(watchDim);
		panelStopwatch.setPreferredSize(watchDim);

		panelPubCard.setLayout(new FlowLayout());
		centerPanel.add(panelPubCard);

		//		panelPlayerLog.add(playerLogLabel);
		//		centerPanel.add(panelPlayerLog);


		scroller = new JScrollPane(scrollablePanel);
	
		centerPanel.add(scroller);
		//panelPlayerLog.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		//panelStopwatch.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setSize(350, 650);
		//eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		frame.getContentPane().add(eastPanel, BorderLayout.EAST);

		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		southPanel.setSize(1200, 150);
		southPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		southPanel.add(myselfPanel);
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

		panelPubCard.setOpaque(true);
		panelPubCard.setBackground(new Color(4,95,0) );
		panelStopwatch.setOpaque(true);
		panelStopwatch.setBackground(new Color(4,95,0) );
		scrollablePanel.setOpaque(true);
		scrollablePanel.setBackground(new Color(4,95,0) );
		myselfPanel.setOpaque(true);
		myselfPanel.setBackground(new Color(43,151,0) );
		eastPanel.setOpaque(true);
		eastPanel.setBackground(new Color(43,151,0) );
		westPanel.setOpaque(true);
		westPanel.setBackground(new Color(43,151,0) );
		centerPanel.setOpaque(true);
		centerPanel.setBackground(new Color(4,95,0) );
		

		this.job = new ConnectJob(token, this);
		this.timer = new Timer(2000, job);
		timer.start();
		myselfPanel.job = job;
 	/*	
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2_1_1.setIcon(new ImageIcon("Àÿ≤ƒ//close_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2_1_1.setIcon(new ImageIcon("Àÿ≤ƒ//close_1.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_2_1_1.setIcon(new ImageIcon("Àÿ≤ƒ//close_1.png"));
		lblNewLabel_2_1_1.setBounds(1175, -2, 26, 30);
		frame.getContentPane().add(lblNewLabel_2_1_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2_1.setIcon(new ImageIcon("Àÿ≤ƒ//fullscreen_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2_1.setIcon(new ImageIcon("Àÿ≤ƒ//fullscreen.png"));
			}
		});
		lblNewLabel_2_1.setIcon(new ImageIcon("Àÿ≤ƒ//fullscreen.png"));
		lblNewLabel_2_1.setBounds(1147, -2, 26, 30);
		frame.getContentPane().add(lblNewLabel_2_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_2.setIcon(new ImageIcon("Àÿ≤ƒ//small_pressed.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("Àÿ≤ƒ//small.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("Àÿ≤ƒ//small.png"));
		lblNewLabel_2.setBounds(1119, -2, 26, 30);
		frame.getContentPane().add(lblNewLabel_2, BorderLayout.NORTH);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		lblNewLabel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("Àÿ≤ƒ//title.png"));
		lblNewLabel_1.setBounds(-53, -18, 1353, 46);
		frame.getContentPane().add(lblNewLabel_1, BorderLayout.NORTH);
		
*/
	}

	
	
	public void updatePublicCards(final ArrayList<Card> publicCards) {
		panelPubCard.removeAll();
		panelPubCard.repaint();
		for (int i = 0; i < publicCards.size(); ++ i){
			int suit = publicCards.get(i).suit;
			int face = publicCards.get(i).face;
			panelPubCard.add(publicCardLabels[suit][face % 13]);
		}

		panelPubCard.validate();
		panelPubCard.revalidate();

	}
	public void updatePlayerLog(ArrayList<String> playerLog) {
		scrollablePanel.removeAll();
		scrollablePanel.repaint();
		this.scrollablePanel.setLayout(new GridLayout(playerLog.size(), 1, 10, 10));
		for(int i = 0; i < playerLog.size(); ++i) {
			String logs = playerLog.get(i);
			JLabel playerLogLabel = new JLabel();
			playerLogLabel.setText(logs);
			this.scrollablePanel.add(playerLogLabel);
			playerLogLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 20));
			playerLogLabel.setForeground(Color.WHITE);
		}
//		playerLogLabel.setText(playerLog);
//		playerLogLabel.validate();
//		playerLogLabel.revalidate();
		

		
		
		this.scroller.validate();
		this.scroller.revalidate();

		this.scrollablePanel.validate();
		this.scrollablePanel.revalidate();

		JScrollBar bar =  scroller.getVerticalScrollBar();
		
		bar.setValue(bar.getMaximum());
		bar.validate();
		bar.revalidate();

	}
	
	public  JPlayerPanel getPlayerPanel(int seat) {
		//System.out.println("getPlayerPanel: ◊˘Œª" + seat);
		return player.get(playerSeats.indexOf(seat));
	}

	public void updatePlayerPanels(int mySeat, int n) { 
		this.mySeat = mySeat;
		int j;
		for(int i=1;i<n;i++) { 
			j = (mySeat + i) % n; //µ⁄i∏ˆŒª÷√µƒ–Ú∫≈
			playerSeats.add(j);

			JPlayerPanel p = new JPlayerPanel();
			JLabel portrait = new JLabel();
			ImageIcon por = new ImageIcon("./Àÿ≤ƒ/" + 
					String.valueOf(i)+".png");
			por.setImage(por.getImage().getScaledInstance(45,50 ,Image.SCALE_DEFAULT ));
			portrait.setIcon(por);
			portrait.setSize(45,45);
			p.profilePanel.add(portrait);
			player.add(p);
		}
		switch(n - 1) {
			case 1:
				this.eastPanel.add(player.get(0));
				break;	
			case 2:
				this.eastPanel.add(player.get(1));
				this.eastPanel.add(player.get(0));
				break;
			case 3:
				this.eastPanel.add(player.get(1));
				this.eastPanel.add(player.get(0));
				this.westPanel.add(player.get(2));
				break;
			case 4:
				this.eastPanel.add(player.get(1));
				this.eastPanel.add(player.get(0));
				this.westPanel.add(player.get(3));
				this.westPanel.add(player.get(2));
				break;
		}
		return;
	
	}

	public void updateMyPlayerName(String name) { //∏¸∏ƒ√˚◊÷
		myselfPanel.updateName(name);
	}
	public void updateMyPlayerAsset(int asset) { //◊‘º∫◊ ≤˙
		myselfPanel.updateAsset(asset);
	}
	public void updateMyPlayerChip(int chip) { //◊‘º∫œ¬◊¢
		myselfPanel.updateChip(chip);
	}
	public void updateMyPlayerPrivateCards(ArrayList<Card> privateCards) {
		myselfPanel.updatePrivateCards(privateCards);
	}
	public void updateMyPlayerAction(boolean add, boolean follow, boolean check, boolean abort, int addMin, int addMax, int followTo) {
		//System.out.println("TexasPokerFrame: addMin "+ addMin + " addMax " + addMax + " followTo" + followTo);
		myselfPanel.updateButton(add, follow, check, abort, addMin, addMax, followTo);
	}
	public void updateMyPlayerReadyAction(boolean ready) {
		myselfPanel.updateReadyButton(ready);
	}

	public void updateMyPlayerReady(boolean isReady) {
		myselfPanel.updateReady(isReady);
	}


	public void updatePlayerName(int seat, String name) {
		getPlayerPanel(seat).updateName(name);
	}
	public void updatePlayerAsset(int seat, int asset) {
		getPlayerPanel(seat).updateAsset(asset);
	}
	public void updatePlayerChip(int seat, int chip) {
		getPlayerPanel(seat).updateChip(chip);
	}
	public void updatePlayerPrivateCards(int seat, ArrayList<Card> privateCards) {
		getPlayerPanel(seat).updatePrivateCards(privateCards);
	}
	public void updatePlayerPrivateCards(int seat) {
		getPlayerPanel(seat).updatePrivateCards();
	}
	public void updatePlayerReady(int seat, boolean isReady) {
		getPlayerPanel(seat).updateReady(isReady);
	}

	public void updateGameResult(int[] moneyChange){
		String s = "";
		for(int i=0;i<moneyChange.length;i++) {
			String winOrLose;
			if(moneyChange[i] > 0) {
				winOrLose = "  §¿˚£¨ªÒµ√ ";
			} else {
				winOrLose = "  ß∞‹£¨ø˜À ";
			}
			if(i == mySeat) {
				s += myselfPanel.usernameLabel.getText() + winOrLose + Math.abs(moneyChange[i])+ " ”Œœ∑±“" + "\n";
			} else {
				s += getPlayerPanel(i).usernameLabel.getText() + winOrLose + Math.abs(moneyChange[i]) +" ”Œœ∑±“" + "\n";
			}
		}
		final String ss = s;
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Œ¢»Ìœ∏∫⁄", Font.PLAIN, 15)));
				JOptionPane.showMessageDialog(null, ss);
			}
		});
	}
	

}
