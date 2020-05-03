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
import java.awt.Dimension;

public class TexasPokerUIController implements UIController {

	public final JFrame frame = new JFrame();
	public JPanel panelStopwatch = new JPanel();
	public JPanel panelPubCard = new JPanel();
	public JPanel panelPlayerLog = new JPanel();
	public JPanel westPanel = new JPanel();
	public JPanel eastPanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public JLabel stopwatch = new JLabel("秒表");
	public JLabel[][] publicCardLabels = new JLabel[5][];
	public JLabel playerLog = new JLabel("日志");
	public JPanel southPanel = new JPanel();
	public JPlayerMyselfPanel myselfPanel = new JPlayerMyselfPanel();

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final TexasPokerUIController window = new TexasPokerUIController();
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
	public TexasPokerUIController() {
		/** Initialize Card Icons */
		for (int suit = 0; suit < publicCardLabels.length; ++ suit){
			int numberOfCardsInThisSuit = suit < 4 ? 13 : 2;
			publicCardLabels[suit] = new JLabel[numberOfCardsInThisSuit];
			for (int face = 1; face <= numberOfCardsInThisSuit; ++ face){
				ImageIcon iconOrigin = new ImageIcon(
					"./image/card_" + 
					String.valueOf(suit) + "_" +
					String.valueOf(face) + ".png"
				);
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(123, 84, java.awt.Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(newImage);
				
				JLabel label = new JLabel();
				label.setIcon(icon);
				Dimension dim = new Dimension(84, 123);
				label.setSize(dim);
				label.setMaximumSize(dim);
				label.setMinimumSize(dim);
				label.setPreferredSize(dim);
				
				publicCardLabels[suit][face-1] = label;
			}
		}

		initialize();


		/** test update public cards */
		Card card1 = new Card(0, 1);
		Card card2 = new Card(1, 1);
		Card card3 = new Card(2, 1);
		Card card4 = new Card(3, 1);
		Card card5 = new Card(4, 1);
		ArrayList<Card> testPublicCards = new ArrayList<Card>();
		testPublicCards.add(card1);
		testPublicCards.add(card2);
		testPublicCards.add(card3);
		testPublicCards.add(card4);
		testPublicCards.add(card5);

		this.updatePublicCards(testPublicCards);
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Border blackline = BorderFactory.createLineBorder(Color.black);

		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		westPanel.setSize(200, 450);
		westPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	
		frame.getContentPane().add(westPanel, BorderLayout.WEST);

		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setSize(500, 450);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

		panelStopwatch.add(stopwatch);
		centerPanel.add(panelStopwatch);

		panelPubCard.setLayout(new FlowLayout());
		centerPanel.add(panelPubCard);

		panelPlayerLog.add(playerLog);
		centerPanel.add(panelPlayerLog);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setSize(200, 450);
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		frame.getContentPane().add(eastPanel, BorderLayout.EAST);

		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		southPanel.setSize(50, 45);
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		southPanel.add(myselfPanel);
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

	}

	@Override
	public void update(final String s) {
		// TODO Auto-generated method stub
		playerLog.setText(s);

	}

	@Override
	public void update(final String s, final boolean isYourTurn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final boolean enterGame) {
		// TODO Auto-generated method stub

	}


	@Override
	public void updatePublicCards(final ArrayList<Card> publicCards) {
		panelPubCard.removeAll();

		for (int i = 0; i < publicCards.size(); ++ i){
			int suit = publicCards.get(i).suit;
			int face = publicCards.get(i).face;
			panelPubCard.add(publicCardLabels[suit][face-1]);
		}

		panelPubCard.validate();
		panelPubCard.revalidate();

	}
	
	
	

}
