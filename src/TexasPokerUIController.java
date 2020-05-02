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
	
	ImageIcon clubs1 = new ImageIcon("./image/clubs1.png");
	ImageIcon clubs2 = new ImageIcon("./image/clubs2.png");
	ImageIcon clubs3 = new ImageIcon("./image/clubs3.png");
	ImageIcon clubs4 = new ImageIcon("./image/clubs4.png");
	ImageIcon clubs5 = new ImageIcon("./image/clubs5.png");
	ImageIcon clubs6 = new ImageIcon("./image/clubs6.png");
	ImageIcon clubs7 = new ImageIcon("./image/clubs7.png");
	ImageIcon clubs8 = new ImageIcon("./image/clubs8.png");
	ImageIcon clubs9 = new ImageIcon("./image/clubs9.png");
	ImageIcon clubs10 = new ImageIcon("./image/clubs10.png");
	ImageIcon clubs11 = new ImageIcon("./image/clubs11.png");
	ImageIcon clubs12 = new ImageIcon("./image/clubs12.png");
	ImageIcon clubs13 = new ImageIcon("./image/clubs13.png");
	ImageIcon diamonds1 = new ImageIcon("./image/diamonds1.png");
	ImageIcon diamonds2 = new ImageIcon("./image/diamonds2.png");
	ImageIcon diamonds3 = new ImageIcon("./image/diamonds3.png");
	ImageIcon diamonds4 = new ImageIcon("./image/diamonds4.png");
	ImageIcon diamonds5 = new ImageIcon("./image/diamonds5.png");
	ImageIcon diamonds6 = new ImageIcon("./image/diamonds6.png");
	ImageIcon diamonds7 = new ImageIcon("./image/diamonds7.png");
	ImageIcon diamonds8 = new ImageIcon("./image/diamonds8.png");
	ImageIcon diamonds9 = new ImageIcon("./image/diamonds9.png");
	ImageIcon diamonds10 = new ImageIcon("./image/diamonds10.png");
	ImageIcon diamonds11 = new ImageIcon("./image/diamonds11.png");
	ImageIcon diamonds12 = new ImageIcon("./image/diamonds12.png");
	ImageIcon diamonds13 = new ImageIcon("./image/diamonds13.png");
	ImageIcon spades1 = new ImageIcon("./image/spades1.png");
	ImageIcon spades2 = new ImageIcon("./image/spades2.png");
	ImageIcon spades3 = new ImageIcon("./image/spades3.png");
	ImageIcon spades4 = new ImageIcon("./image/spades4.png");
	ImageIcon spades5 = new ImageIcon("./image/spades5.png");
	ImageIcon spades6 = new ImageIcon("./image/spades6.png");
	ImageIcon spades7 = new ImageIcon("./image/spades7.png");
	ImageIcon spades8 = new ImageIcon("./image/spades8.png");
	ImageIcon spades9 = new ImageIcon("./image/spades9.png");
	ImageIcon spades10 = new ImageIcon("./image/spades10.png");
	ImageIcon spades11 = new ImageIcon("./image/spades11.png");
	ImageIcon spades12 = new ImageIcon("./image/spades12.png");
	ImageIcon spades13 = new ImageIcon("./image/spades13.png");
	ImageIcon hearts1 = new ImageIcon("./image/hearts1.png");
	ImageIcon hearts2 = new ImageIcon("./image/hearts2.png");
	ImageIcon hearts3 = new ImageIcon("./image/hearts3.png");
	ImageIcon hearts4 = new ImageIcon("./image/hearts4.png");
	ImageIcon hearts5 = new ImageIcon("./image/hearts5.png");
	ImageIcon hearts6 = new ImageIcon("./image/hearts6.png");
	ImageIcon hearts7 = new ImageIcon("./image/hearts7.png");
	ImageIcon hearts8 = new ImageIcon("./image/hearts8.png");
	ImageIcon hearts9 = new ImageIcon("./image/hearts9.png");
	ImageIcon hearts10 = new ImageIcon("./image/hearts10.png");
	ImageIcon hearts11 = new ImageIcon("./image/hearts11.png");
	ImageIcon hearts12 = new ImageIcon("./image/hearts12.png");
	ImageIcon hearts13 = new ImageIcon("./image/hearts13.png");
	
	
	
	
	
	

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
	
	public void addPublicCard(Card card) {
		// card找对应图片
		// 用对应的图片构造JLabel
		//将构造的JLabel加到pubCards ArrayList
		//panelPubCard.removeAll
		//panelPubCard 添加所有ArrayList里的JLabel
		//panelPubCard.validate()
		
		
		// card找对应图片
		ImageIcon card1 =;
		ImageIcon card2 =;
		ImageIcon card3 =;
		JLabel cradLabel1 = new JLabel();
		JLabel cradLabel2 = new JLabel();
		JLabel cradLabel3 = new JLabel();
		cradLabel1.setIcon(card1);
		cradLabel2.setIcon(card2);
		cradLabel3.setIcon(card3);
		pubCards.add(cradLabel1);
		pubCards.add(cradLabel2);
		pubCards.add(cradLabel3);
		panelPubCard.setLayout(new FlowLayout());
		panelPubCard.add(pubCards.get(0));
		panelPubCard.add(pubCards.get(1));
		panelPubCard.add(pubCards.get(2));
		//panelPubCard.removeAll
		for(int i = 0; i< pubCards.size(); i++) {
			pubCards.remove(i);
		}
		
		//panelPubCard 添加所有ArrayList里的JLabel
		
		for(int i = 0; i< pubCards.size(); i++) {
			panelPubCard.add(pubCards.get(i));
		}
		
		//panelPubCard.validate()
		
	}
	
	

}
