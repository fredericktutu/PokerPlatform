import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class JPlayerPanel extends JPanel {
	public JPanel usernameAndChip = new JPanel();
	public JPanel profilePanel = new JPanel();
	public JLabel usernameLabel = new JLabel("ÓÃ»§Ãû");
	public JLabel chipTextLabel = new JLabel("ÒÑÏÂ×¢");
	public JLabel chipLabel = new JLabel("");
	public JLabel assetTextLabel = new JLabel("×Ü×Ê½ð");
	public JLabel assetLabel = new JLabel("");
	public JPanel cardPanel = new JPanel(new FlowLayout());
	public JLabel[][] privateCardLabels = new JLabel[4][13];
	public JLabel playerWordsLabel;
	public JLabel[] back = new JLabel[2];

	
	public JPlayerPanel() {
		
		for (int suit = 0; suit < privateCardLabels.length; ++ suit){
			//int numberOfCardsInThisSuit = suit < 4 ? 13 : 2;
			int numberOfCardsInThisSuit = 13;
			privateCardLabels[suit] = new JLabel[numberOfCardsInThisSuit];
			for (int face = 0; face <= numberOfCardsInThisSuit ;  ++face){
				ImageIcon iconOrigin = new ImageIcon(
					"./ËØ²Ä/card_" + 
					String.valueOf(suit) + "_" +
					String.valueOf(face % 13 ) + ".png"
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
				
				privateCardLabels[suit][face % 13] = label;
			}
		}
		ImageIcon iconOrigin = new ImageIcon(
			"./ËØ²Ä/back.png"
		);
		Image image = iconOrigin.getImage();
		Image newImage = image.getScaledInstance(117, 117, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
	
		Dimension dim = new Dimension(117, 117);
		JLabel back0 = new JLabel();
		back0.setIcon(icon);
		back0.setSize(dim);
		back0.setMaximumSize(dim);
		back0.setMinimumSize(dim);
		back0.setPreferredSize(dim);

		JLabel back1 = new JLabel();
		back1.setIcon(icon);
		back1.setSize(dim);
		back1.setMaximumSize(dim);
		back1.setMinimumSize(dim);
		back1.setPreferredSize(dim);
		back[0] = back0;
		back[1] = back1;

		//JLabel portrait = new JLabel();
		//ImageIcon por = new ImageIcon("./ËØ²Ä/portrait.png");
		//por.setImage(por.getImage().getScaledInstance(45,50 ,Image.SCALE_DEFAULT ));
		//portrait.setIcon(por);
		//portrait.setSize(45,45);
		Dimension profileDimension = new Dimension(50, 50);
		profilePanel.setSize(profileDimension);
		profilePanel.setMinimumSize(profileDimension);
		profilePanel.setMaximumSize(profileDimension);
		profilePanel.setPreferredSize(profileDimension);
		//profilePanel.add(portrait);
		profilePanel.setRequestFocusEnabled(false);
		//profilePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		profilePanel.setOpaque(true);
		profilePanel.setBackground(new Color(9,120,0));
		
		Dimension usernameLabelDim = new Dimension(250, 20);
		usernameLabel.setSize(usernameLabelDim);
		usernameLabel.setMaximumSize(usernameLabelDim);
		usernameLabel.setMinimumSize(usernameLabelDim);
		usernameLabel.setPreferredSize(usernameLabelDim);
		chipLabel.setSize(150, 20);
		//usernameLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
		//chipLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
		usernameLabel.setOpaque(true);
		usernameLabel.setBackground(new Color(9,120,0));
		chipLabel.setOpaque(true);
		chipLabel.setBackground(new Color(9,120,0));
		usernameLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		usernameLabel.setForeground(Color.WHITE);
		chipLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		chipLabel.setForeground(Color.WHITE);

		assetLabel.setSize(150,20);
		assetLabel.setOpaque(true);
		assetLabel.setBackground(new Color(9,120,0));
		assetLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		assetLabel.setForeground(Color.WHITE);
		
		//chipTextLabel.setSize(usernameLabelDim);
		chipTextLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		chipTextLabel.setForeground(Color.WHITE);
		//assetTextLabel.setSize(usernameLabelDim);//
		assetTextLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));//
		assetTextLabel.setForeground(Color.WHITE);//
		
		playerWordsLabel = new JLabel("");
		//playerWordsLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		//playerWordsLabel.setForeground(Color.WHITE);
		Dimension playerWordsLabelDim = new Dimension(450, 80);
		playerWordsLabel.setSize(playerWordsLabelDim);
		playerWordsLabel.setMaximumSize(playerWordsLabelDim);
		playerWordsLabel.setMinimumSize(playerWordsLabelDim);
		playerWordsLabel.setPreferredSize(playerWordsLabelDim);
		playerWordsLabel.setBorder(BorderFactory.createLineBorder(Color.white));
		playerWordsLabel.setOpaque(true);
		playerWordsLabel.setBackground(new Color(9,120,0));
		
		usernameAndChip.setLayout(new FlowLayout());
		usernameAndChip.add(usernameLabel);
		usernameAndChip.add(chipTextLabel);
		usernameAndChip.add(chipLabel);
		usernameAndChip.add(assetTextLabel);
		usernameAndChip.add(assetLabel);
		usernameAndChip.setOpaque(true);
		usernameAndChip.setBackground(new Color(9,120,0));
		
		
		Dimension cardPanelDim = new Dimension(450, 130);
		cardPanel.setSize(cardPanelDim);
		cardPanel.setMaximumSize(cardPanelDim);
		cardPanel.setMinimumSize(cardPanelDim);
		cardPanel.setPreferredSize(cardPanelDim);
		//cardPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		cardPanel.setOpaque(true);
		cardPanel.setBackground(new Color(9,120,0));
		/*
		JLabel back1 = new JLabel();
		ImageIcon img = new ImageIcon("./ËØ²Ä/back.png");		
		img.setImage(img.getImage().getScaledInstance(117, 117,Image.SCALE_DEFAULT ));
		back1.setIcon(img);
		back1.setSize(117,117);
		JLabel back2 = new JLabel();
		back2.setIcon(img);
		back2.setSize(117,117);
		this.back1 = back1;
		this.back2 = back2;
		cardPanel.add(back1);
		cardPanel.add(back2);
		*/

		


		updatePrivateCards();
		/** test update public cards */
		/*
		Card card1 = new Card(2, 0); 
		Card card2 = new Card(3, 0);
		ArrayList<Card> testPublicCards = new ArrayList<Card>();
		testPublicCards.add(card1);
		testPublicCards.add(card2);
		this.updatePrivateCards(testPublicCards);
		*/
		this.updateFace(5);
		this.updateFace(3);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.add(usernameAndChip, BorderLayout.NORTH);
		rightPanel.add(cardPanel, BorderLayout.CENTER);
		rightPanel.setOpaque(true);
		rightPanel.setBackground(new Color(9,120,0));
		
		//JLabel playerWordsLabel = new JLabel("Íæ¼ÒÎÄ×ÖÓïÑÔ");
		//playerWordsLabel.setSize(180, 30);
		
		Dimension playerPanelDim = new Dimension(300, 270);
		
		this.setLayout(new BorderLayout());
		this.setSize(playerPanelDim);
		this.setMaximumSize(playerPanelDim);
		this.setMinimumSize(playerPanelDim);
		this.setPreferredSize(playerPanelDim);
		
		this.add(profilePanel, BorderLayout.WEST);
//		this.add(usernameLabel);
//		this.add(chipLabel);
//		this.add(cardPanel);
		this.add(rightPanel, BorderLayout.CENTER);
    	this.add(playerWordsLabel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	public void updatePrivateCards() {
		cardPanel.removeAll();
		cardPanel.repaint();
		//System.out.println(back[0]);
		//System.out.println("JPlayerPanel: ·­Ãæ");
		back[0].setBorder(BorderFactory.createLineBorder(Color.BLUE));
		back[1].setBorder(BorderFactory.createLineBorder(Color.BLUE));
		cardPanel.add(back[0]);
		cardPanel.add(back[1]);


		cardPanel.validate();
		cardPanel.revalidate();
	}

	public void updatePrivateCards(final ArrayList<Card> privateCards) {
		cardPanel.removeAll();
		cardPanel.repaint();
		//System.out.println("JPlayerPanel: Õ¹Ê¾¶ÔÊÖÅÆ");
		for (int i = 0; i < privateCards.size(); ++ i){
			int suit = privateCards.get(i).suit;
			int face = privateCards.get(i).face;
			privateCardLabels[suit][face % 13].setBorder(BorderFactory.createLineBorder(Color.BLUE));
			cardPanel.add(privateCardLabels[suit][face % 13]);
		}
		
		cardPanel.validate();
		cardPanel.revalidate();

	}
	public void updateAsset(final int asset) {
		assetLabel.setText(String.valueOf(asset));
		assetLabel.validate();
		assetLabel.revalidate();
	}
	
	
	public void updateChip(int chipNum) {
		chipLabel.setText(String.valueOf(chipNum));
		chipLabel.validate();
		chipLabel.revalidate();
	}

	public void updateName(String name) {
		usernameLabel.setText(name);
		usernameLabel.validate();
		usernameLabel.revalidate();
	}

	public void updateReady(boolean isReady) {
		if(isReady) {
			usernameLabel.setForeground(Color.YELLOW);
		} else {
			usernameLabel.setForeground(Color.WHITE);
		}
		
	}

	public void updateFace(int num) {
		playerWordsLabel.removeAll();
		playerWordsLabel.repaint();
		ImageIcon iconOrigin = new ImageIcon("./ËØ²Ä/face_" + String.valueOf(num) + ".jpg");
		Image image = iconOrigin.getImage();
		Image newImage = image.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
		playerWordsLabel.setIcon(icon);
		playerWordsLabel.validate();
		playerWordsLabel.revalidate();
	}
}
