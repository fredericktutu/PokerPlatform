import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPlayerMyselfPanel extends JPanel {
	
	
	
	public JPanel cardPanel = new JPanel(new FlowLayout());
	public JLabel usernameLabel = new JLabel("用户名");
	public JLabel chipLabel = new JLabel("筹码");
	public JPanel usernameAndChip = new JPanel();
	public JPanel leftPanel = new JPanel();
	public JPanel middlePanel = new JPanel();
	public JPanel rightPanel = new JPanel();
	public JLabel[][] privateCardLabels = new JLabel[5][];
	
	
	
	

	public JPlayerMyselfPanel() {
		// TODO Auto-generated constructor stub
		for (int suit = 0; suit < privateCardLabels.length; ++ suit){
			int numberOfCardsInThisSuit = suit < 4 ? 13 : 2;
			privateCardLabels[suit] = new JLabel[numberOfCardsInThisSuit];
			for (int face = 1; face <= numberOfCardsInThisSuit; ++ face){
				ImageIcon iconOrigin = new ImageIcon(
					"./image/card_" + 
					String.valueOf(suit) + "_" +
					String.valueOf(face) + ".png"
				);
				Image image = iconOrigin.getImage();
				Image newImage = image.getScaledInstance(93, 63, java.awt.Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(newImage);
				
				JLabel label = new JLabel();
				label.setIcon(icon);
				Dimension dim = new Dimension(63, 93);
				label.setSize(dim);
				label.setMaximumSize(dim);
				label.setMinimumSize(dim);
				label.setPreferredSize(dim);
				
				privateCardLabels[suit][face-1] = label;
			}
		}
	    
		
		Dimension cardPanelDim = new Dimension(300, 200);
		cardPanel.setSize(cardPanelDim);
		cardPanel.setMaximumSize(cardPanelDim);
		cardPanel.setMinimumSize(cardPanelDim);
		cardPanel.setPreferredSize(cardPanelDim);
		cardPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		
		
		Dimension usernameLabelDim = new Dimension(70, 30);
		usernameLabel.setSize(usernameLabelDim);
		usernameLabel.setMaximumSize(usernameLabelDim);
		usernameLabel.setMinimumSize(usernameLabelDim);
		usernameLabel.setPreferredSize(usernameLabelDim);
		//JLabel chipLabel = new JLabel("筹码");
		chipLabel.setSize(usernameLabelDim);
		
		//JPanel usernameAndChip = new JPanel();
		usernameAndChip.setLayout(new FlowLayout());
		usernameAndChip.add(usernameLabel);
		usernameAndChip.add(chipLabel);
		
		
		//JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		
		leftPanel.add(cardPanel);
		leftPanel.add(usernameAndChip);
		
		//JPanel middlePanel = new JPanel();
		middlePanel.add(new JLabel("扑克操作区"));
		Dimension middlePanelDim = new Dimension(500, 70);
		middlePanel.setSize(middlePanelDim);
		middlePanel.setMinimumSize(middlePanelDim);
		middlePanel.setMaximumSize(middlePanelDim);
		middlePanel.setPreferredSize(middlePanelDim);
		
		
		//JPanel rightPanel = new JPanel();
		rightPanel.add(new JLabel("聊天输入区"));
		Dimension rightPanelDim = new Dimension(140, 70);
		rightPanel.setSize(rightPanelDim);
		rightPanel.setMaximumSize(rightPanelDim);
		rightPanel.setMinimumSize(rightPanelDim);
		rightPanel.setPreferredSize(rightPanelDim);
		this.setLayout(new FlowLayout());
		
		this.add(leftPanel);
		this.add(middlePanel);
		this.add(rightPanel);
		

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

		this.updatePrivateCards(testPublicCards);
		

	}

	public JPlayerMyselfPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public JPlayerMyselfPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public JPlayerMyselfPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public void updatePrivateCards(final ArrayList<Card> privateCards) {
		cardPanel.removeAll();

		for (int i = 0; i < privateCards.size(); ++ i){
			int suit = privateCards.get(i).suit;
			int face = privateCards.get(i).face;
			privateCardLabels[suit][face-1].setBorder(BorderFactory.createLineBorder(Color.BLUE));
			cardPanel.add(privateCardLabels[suit][face-1]);
		}

		cardPanel.validate();
		cardPanel.revalidate();

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
			

}

