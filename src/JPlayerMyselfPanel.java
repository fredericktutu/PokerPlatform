import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPlayerMyselfPanel extends JPanel {

	public JPlayerMyselfPanel() {
		// TODO Auto-generated constructor stub

		
		JPanel cardPanel = new JPanel();
		Dimension cardPanelDim = new Dimension(140, 40);
		cardPanel.setSize(cardPanelDim);
		cardPanel.setMaximumSize(cardPanelDim);
		cardPanel.setMinimumSize(cardPanelDim);
		cardPanel.setPreferredSize(cardPanelDim);
		cardPanel.add(new JLabel("玩家的牌"));
		
		JLabel usernameLabel = new JLabel("用户名");
		Dimension usernameLabelDim = new Dimension(70, 30);
		usernameLabel.setSize(usernameLabelDim);
		usernameLabel.setMaximumSize(usernameLabelDim);
		usernameLabel.setMinimumSize(usernameLabelDim);
		usernameLabel.setPreferredSize(usernameLabelDim);
		JLabel chipLabel = new JLabel("筹码");
		chipLabel.setSize(usernameLabelDim);
		
		JPanel usernameAndChip = new JPanel();
		usernameAndChip.setLayout(new FlowLayout());
		usernameAndChip.add(usernameLabel);
		usernameAndChip.add(chipLabel);
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.add(cardPanel);
		leftPanel.add(usernameAndChip);
		
		JPanel middlePanel = new JPanel();
		middlePanel.add(new JLabel("扑克操作区"));
		Dimension middlePanelDim = new Dimension(500, 70);
		middlePanel.setSize(middlePanelDim);
		middlePanel.setMinimumSize(middlePanelDim);
		middlePanel.setMaximumSize(middlePanelDim);
		middlePanel.setPreferredSize(middlePanelDim);
		
		
		JPanel rightPanel = new JPanel();
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

}
