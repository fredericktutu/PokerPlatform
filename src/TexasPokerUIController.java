import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;

public class TexasPokerUIController implements UIController {

	private JFrame frame = new JFrame();
	public JPanel panelStopwatch = new JPanel();
	public JPanel panelPubCard = new JPanel();
	public JPanel panelPlayerLog = new JPanel();
	public JPanel westPanel = new JPanel();
	public JPanel eastPanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public JLabel stopwatch = new JLabel("秒表");
	public ArrayList<JLabel> pubCards  = new ArrayList<JLabel>();
	public JLabel playerLog = new JLabel("日志");
	public JPanel southPanel = new JPanel();
	public ImageIcon[][] cardImages = new ImageIcon[4][13];
	public ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();
	
	
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasPokerUIController window = new TexasPokerUIController();
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
	public TexasPokerUIController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Border blackline = BorderFactory.createLineBorder(Color.black);
		
		
		
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		westPanel.setSize(200, 450);
		westPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		westPanel.add(new JPlayerPanel());
		westPanel.add(new JPlayerPanel());
		westPanel.add(new JPlayerPanel());
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		
		
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setSize(500, 450);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		centerPanel.add(panelStopwatch);
		centerPanel.add(panelPubCard);
		centerPanel.add(panelPlayerLog);
		panelStopwatch.add(stopwatch);
		//panelPubCard.add(pubCards);
		panelPubCard.setLayout(new FlowLayout());
		for(int i = 0; i< pubCards.size(); i++) {
			panelPubCard.add(pubCards.get(i));
		}
		
		
		panelPlayerLog.add(playerLog);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
		

		
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setSize(200, 450);
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		eastPanel.add(new JPlayerPanel());
		eastPanel.add(new JPlayerPanel());
		eastPanel.add(new JPlayerPanel());
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		
		
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		southPanel.setSize(50, 45);
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		southPanel.add(new JPlayerMyselfPanel());
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		
	}

	@Override
	public void update(String s) {
		// TODO Auto-generated method stub
		playerLog.setText(s);

	}

	@Override
	public void update(String s, boolean isYourTurn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(boolean enterGame) {
		// TODO Auto-generated method stub
		
	}
	
	public void addPublicCard1(Card card) {
		// card找对应图片
		// 用对应的图片构造JLabel
		//将构造的JLabel加到pubCards ArrayList
		//panelPubCard.removeAll
		//panelPubCard 添加所有ArrayList里的JLabel
		//panelPubCard.validate()
		
		
		// card找对应图片
		
		
		for(int i = 0; i < 4; i++){
			 for(int j = 1; i <= 13; j++){
			  cardImages[i][j-1] = new ImageIcon("./image/card_" + String.valueOf(i) + "_" + String.valueOf(j) + ".png");
			}
		}
		
		ImageIcon image = cardImages[card.suit][card.face-1];
		JLabel label = new JLabel();
		label.setIcon(image);
		pubCards.add(label);
		panelPubCard.removeAll();
		
		
		//panelPubCard 添加所有ArrayList里的JLabel
		
		for(int i = 0; i< pubCards.size(); i++) {
			panelPubCard.add(pubCards.get(i));
		}
		
		//panelPubCard.validate()
		panelPubCard.validate();
		
	}
	
	public void addPublicCard2(ArrayList<Card> publicCards) {
		for(int i = 0; i < 4; i++){
			 for(int j = 1; i <= 13; j++){
			  cardImages[i][j-1] = new ImageIcon("./image/card_" + String.valueOf(i) + "_" + String.valueOf(j) + ".png");
			}
		}
		
		panelPubCard.removeAll();

		 for (int i = 0; i < publicCards.size(); ++ i){
		  //ImageIcon image = cardImages[card.suit][card.face-1];
		  //JLabel label = new JLabel();  
		 //label.setIcon(image);
		  //panelPubCard.add(label);
			 panelPubCard.add(cardLabels[card.suit][card.face-1]);
		 }
		panelPubCard.validate();

		
		
		
	}
	
	

}
