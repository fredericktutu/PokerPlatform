import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPlayerMyselfPanel extends JPanel {

	public JPanel cardPanel = new JPanel(new FlowLayout());
	public JLabel usernameLabel = new JLabel("ÓÃ»§Ãû");
	public JLabel chipTextLabel = new JLabel("ÒÑÏÂ×¢");
	public JLabel chipLabel = new JLabel("");
	public JLabel assetTextLabel = new JLabel("×Ü×Ê½ð");
	public JLabel assetLabel = new JLabel("");
	public JPanel usernameAndChip = new JPanel();
	public JPanel leftPanel = new JPanel();
	public JPanel middlePanel = new JPanel();
	public JPanel rightPanel = new JPanel();
	//public JLabel[][] privateCardLabels = new JLabel[5][];
	public JLabel[][] privateCardLabels = new JLabel[4][13];
	public JButton foldBtn = new JButton("ÆúÅÆ");
	public JButton betBtn = new JButton("ÏÂ×¢");
	public JButton checkBtn = new JButton("ÈÃÅÆ");
	public JButton callBtn = new JButton("¸ú×¢");
	public JTextField betNum = new JTextField("0");
	//public JTextField raiseNum = new JTextField("0");
	public JTextField chat = new JTextField("ÁÄÌìÊäÈëÇø");
	public JButton startBtn = new JButton("¿ªÊ¼");
	public JPanel middlePanel1 = new JPanel();
	public JPanel middlePanel2 = new JPanel();
	
	
	public int addMin;
	public int addMax;
	public int followTo;
	public String token;
	public ITexasGameController controller;
	
	
	public JPlayerMyselfPanel() {
		// TODO Auto-generated constructor stub
		for (int suit = 0; suit < privateCardLabels.length; ++ suit){
			//int numberOfCardsInThisSuit = suit < 4 ? 13 : 2;
			final int numberOfCardsInThisSuit = 13;
			privateCardLabels[suit] = new JLabel[numberOfCardsInThisSuit];
			for (int face = 0; face <= numberOfCardsInThisSuit ;  ++face){
				final ImageIcon iconOrigin = new ImageIcon(
					"./ËØ²Ä/card_" + 
					String.valueOf(suit) + "_" +
					String.valueOf(face % 13 ) + ".png"
				);
				final Image image = iconOrigin.getImage();
				final Image newImage = image.getScaledInstance(117, 117, java.awt.Image.SCALE_SMOOTH);
				final ImageIcon icon = new ImageIcon(newImage);
				
				final JLabel label = new JLabel();
				label.setIcon(icon);
				final Dimension dim = new Dimension(117, 117);
				label.setSize(dim);
				label.setMaximumSize(dim);
				label.setMinimumSize(dim);
				label.setPreferredSize(dim);
				
				privateCardLabels[suit][face % 13] = label;
			}
		}
		final Dimension betNumDim = new Dimension(50, 25);
		betNum.setSize(betNumDim);
		betNum.setMaximumSize(betNumDim);
		betNum.setMinimumSize(betNumDim);
		betNum.setPreferredSize(betNumDim);
		//betNum.setBorder(BorderFactory.createLineBorder(Color.RED));
		/*
		Dimension raiseNumDim = new Dimension(50, 25);
		raiseNum.setSize(raiseNumDim);
		raiseNum.setMaximumSize(raiseNumDim);
		raiseNum.setMinimumSize(raiseNumDim);
		raiseNum.setPreferredSize(raiseNumDim);
		*/
		//raiseNum.setBorder(BorderFactory.createLineBorder(Color.RED));
		
	
		
		final Dimension cardPanelDim = new Dimension(300, 150);
		cardPanel.setSize(cardPanelDim);
		cardPanel.setMaximumSize(cardPanelDim);
		cardPanel.setMinimumSize(cardPanelDim);
		cardPanel.setPreferredSize(cardPanelDim);
		//cardPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		
		
		final Dimension usernameLabelDim = new Dimension(70, 30);
		usernameLabel.setSize(usernameLabelDim);
		usernameLabel.setMaximumSize(usernameLabelDim);
		usernameLabel.setMinimumSize(usernameLabelDim);
		usernameLabel.setPreferredSize(usernameLabelDim);
		chipLabel.setSize(usernameLabelDim);
		usernameLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		usernameLabel.setForeground(Color.WHITE);
		chipLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		chipLabel.setForeground(Color.WHITE);
		assetLabel.setSize(usernameLabelDim);//
		assetLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));//
		assetLabel.setForeground(Color.WHITE);//
		
		chipTextLabel.setSize(usernameLabelDim);
		chipTextLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		chipTextLabel.setForeground(Color.WHITE);
		assetTextLabel.setSize(usernameLabelDim);//
		assetTextLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));//
		assetTextLabel.setForeground(Color.WHITE);//

		
		usernameAndChip.setLayout(new FlowLayout());
		usernameAndChip.add(usernameLabel);
		usernameAndChip.add(chipTextLabel);
		usernameAndChip.add(chipLabel);
		usernameAndChip.add(assetTextLabel);
		usernameAndChip.add(assetLabel);//
		
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		
		leftPanel.add(cardPanel);
		leftPanel.add(usernameAndChip);
		cardPanel.setOpaque(true);
		cardPanel.setBackground(new Color(9,120,0));
		usernameAndChip.setOpaque(true);
		usernameAndChip.setBackground(new Color(9,120,0));
		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
		final Dimension middlePanelDim = new Dimension(500, 70);
		middlePanel1.setSize(middlePanelDim);
		middlePanel1.setMinimumSize(middlePanelDim);
		middlePanel1.setMaximumSize(middlePanelDim);
		middlePanel1.setPreferredSize(middlePanelDim);
		middlePanel1.setLayout(new FlowLayout());
		middlePanel1.add(betNum);
		middlePanel1.add(betBtn);
		//middlePanel.add(raiseNum);
		middlePanel1.add(checkBtn);
		middlePanel1.add(callBtn);
		middlePanel1.add(foldBtn);
		middlePanel.add(middlePanel1, BorderLayout.NORTH);
		middlePanel2.add(startBtn);
		middlePanel.add(middlePanel2, BorderLayout.SOUTH);
		middlePanel1.setOpaque(true);
		middlePanel1.setBackground(new Color(9,120,0));
		middlePanel2.setOpaque(true);
		middlePanel2.setBackground(new Color(9,120,0));
		betNum.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));
		betBtn.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));
		//raiseNum.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));
		checkBtn.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));
		callBtn.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));
		foldBtn.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));
		startBtn.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 15));

		middlePanel.setOpaque(true);
		middlePanel.setBackground(new Color(9,120,0));
		
		final String number1 = betNum.getText().trim();
		final int betNumber = Integer.parseInt(number1);
		//String number2 = raiseNum.getText().trim();
		//int raiseNumber = Integer.parseInt(number2);
		foldBtn.addActionListener(new ActionListener() { //ÆúÅÆ
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					controller.handler_abort_bet(token);
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		betBtn.addActionListener(new ActionListener() { //¼Ó×¢
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					int addTo = Integer.parseInt(betNum.getText());
					if(addTo <= addMin) {
						controller.handler_add_bet(token, addMin);
					} else if(addTo >= addMax) {
						controller.handler_add_bet(token, addMax);
					} else {
						controller.handler_add_bet(token, addTo);
					}
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
	
		checkBtn.addActionListener(new ActionListener() { //ÈÃÅÆ
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					controller.handler_check(token);
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		
		callBtn.addActionListener(new ActionListener() { //¸ú×¢
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					controller.handler_follow_bet(token, followTo);
					
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					controller.handler_ready(token);
				}catch(final Exception ev) {
					ev.printStackTrace();
				}
			}
		}

		);
		
		rightPanel.add(chat);
		final Dimension rightPanelDim = new Dimension(300, 150);
		rightPanel.setSize(rightPanelDim);
		rightPanel.setMaximumSize(rightPanelDim);
		rightPanel.setMinimumSize(rightPanelDim);
		rightPanel.setPreferredSize(rightPanelDim);
		final Dimension chatDim = new Dimension(300, 150);
		chat.setSize(chatDim);
		chat.setMaximumSize(chatDim);
		chat.setMinimumSize(chatDim);
		chat.setPreferredSize(chatDim);
		this.setLayout(new FlowLayout());
		//rightPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		rightPanel.setOpaque(true);
		rightPanel.setBackground(new Color(9,120,0));
		
		this.add(leftPanel);
		this.add(middlePanel);
		this.add(rightPanel);
		

		/** test update public cards */
		final Card card1 = new Card(2, 0); 
		final Card card2 = new Card(2, 4);
		
		//Card card5 = new Card(4, 1);
		final ArrayList<Card> testPublicCards = new ArrayList<Card>();
		testPublicCards.add(card1);
		testPublicCards.add(card2);
		
		//testPublicCards.add(card5);

		this.updatePrivateCards(testPublicCards);
		

	}

	public JPlayerMyselfPanel(final LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public JPlayerMyselfPanel(final boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public JPlayerMyselfPanel(final LayoutManager layout, final boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public void updatePrivateCards(final ArrayList<Card> privateCards) {
		cardPanel.removeAll();

		for (int i = 0; i < privateCards.size(); ++ i){
			final int suit = privateCards.get(i).suit;
			final int face = privateCards.get(i).face;
			privateCardLabels[suit][face % 13].setBorder(BorderFactory.createLineBorder(Color.BLUE));
			cardPanel.add(privateCardLabels[suit][face % 13]);
		}

		cardPanel.validate();
		cardPanel.revalidate();

	}
	
	public void updateChip(final int chipNum) {   //¸üÐÂ³ïÂë
		chipLabel.setText(String.valueOf(chipNum));
		chipLabel.validate();
		chipLabel.revalidate();
	}
	public void updateAsset(final int asset) {
		assetLabel.setText(String.valueOf(asset));
		assetLabel.validate();
		assetLabel.revalidate();
	}
	
	public void updateName(final String name) {   //¸üÐÂ×Ô¼ºµÄÃû×Ö
		usernameLabel.setText(name);
		usernameLabel.validate();
		usernameLabel.revalidate();
	}

	public void updateButton(final boolean add, final boolean follow, final boolean check, final boolean abort, int addMin, int addMax, int followTo) {
		this.addMin = addMin;
		this.addMax = addMax;
		this.followTo = followTo;
		betNum.setVisible(add);
		betBtn.setVisible(add);
		callBtn.setVisible(follow);
		checkBtn.setVisible(check);
		foldBtn.setVisible(abort);

	}

	public void updateReadyButton(boolean ready) {
		this.startBtn.setVisible(ready);
	}
	public void updateReady(boolean isReady) {
		if(isReady) {
			usernameLabel.setForeground(Color.YELLOW);
		} else {
			usernameLabel.setForeground(Color.WHITE);
		}
		
	}

			

}

