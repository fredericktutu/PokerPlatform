import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class JPlayerPanel extends JPanel {
	
	public JPlayerPanel() {
		JPanel profilePanel = new JPanel();
		JLabel portrait = new JLabel();
		ImageIcon por = new ImageIcon("./image/portrait.png");
		por.setImage(por.getImage().getScaledInstance(45,50 ,Image.SCALE_DEFAULT ));
		portrait.setIcon(por);
		portrait.setSize(45,45);
		Dimension profileDimension = new Dimension(40, 70);
		profilePanel.setSize(profileDimension);
		profilePanel.setMinimumSize(profileDimension);
		profilePanel.setMaximumSize(profileDimension);
		profilePanel.setPreferredSize(profileDimension);
		profilePanel.add(portrait);
		profilePanel.setRequestFocusEnabled(false);

		
		JLabel usernameLabel = new JLabel("用户名");
		Dimension usernameLabelDim = new Dimension(70, 30);
		usernameLabel.setSize(usernameLabelDim);
		usernameLabel.setMaximumSize(usernameLabelDim);
		usernameLabel.setMinimumSize(usernameLabelDim);
		usernameLabel.setPreferredSize(usernameLabelDim);
		JLabel chipLabel = new JLabel("筹码");
		chipLabel.setSize(70, 30);
		
		JPanel usernameAndChip = new JPanel();
		usernameAndChip.setLayout(new FlowLayout());
		usernameAndChip.add(usernameLabel);
		usernameAndChip.add(chipLabel);
		
		JPanel cardPanel = new JPanel(new FlowLayout());
		JLabel card1 = new JLabel();
		ImageIcon img = new ImageIcon("./image/back.png");
		img.setImage(img.getImage().getScaledInstance(40,200 ,Image.SCALE_DEFAULT ));
		card1.setIcon(img);
		card1.setSize(40,200);
		JLabel card2 = new JLabel();
		card2.setIcon(img);
		card2.setSize(40,200);
		Dimension cardPanelDim = new Dimension(140, 120);
		cardPanel.setSize(cardPanelDim);
		cardPanel.setMaximumSize(cardPanelDim);
		cardPanel.setMinimumSize(cardPanelDim);
		cardPanel.setPreferredSize(cardPanelDim);
		cardPanel.add(card1);
		cardPanel.add(card2);
		
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.add(usernameAndChip);
		rightPanel.add(cardPanel);
		
		JLabel playerWordsLabel = new JLabel("玩家文字语言");
		playerWordsLabel.setSize(180, 30);
		
		this.setLayout(new BorderLayout());
		this.setSize(200, 100);
		
		this.add(profilePanel, BorderLayout.WEST);
//		this.add(usernameLabel);
//		this.add(chipLabel);
//		this.add(cardPanel);
		this.add(rightPanel, BorderLayout.CENTER);
		this.add(playerWordsLabel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}
