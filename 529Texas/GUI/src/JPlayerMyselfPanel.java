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

import org.json.simple.JSONObject;
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
	public JTextField chat = new JTextField("1");
	public JButton startBtn = new JButton("¿ªÊ¼");
	public JPanel middlePanel1 = new JPanel();
	public JPanel middlePanel2 = new JPanel();
	public JPanel facePanel = new JPanel();
	public JButton send = new JButton("·¢ËÍ");
	
	
	public int addMin;
	public int addMax;
	public int followTo;
	public String token;
	ConnectJob job;
	//public ITexasGameController controller;
	
	
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
		leftPanel.setOpaque(true);
		leftPanel.setBackground(new Color(9,120,0));
		usernameAndChip.setOpaque(true);
		usernameAndChip.setBackground(new Color(9,120,0));
		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
		final Dimension middlePanelDim = new Dimension(500, 70);
		middlePanel1.setSize(middlePanelDim);
		middlePanel1.setMinimumSize(middlePanelDim);
		middlePanel1.setMaximumSize(middlePanelDim);
		middlePanel1.setPreferredSize(middlePanelDim);
		middlePanel1.setLayout(new FlowLayout());
		/*
		final JLabel tipsLabel = new JLabel("×î´óÏÂ×¢ÖµXXX£»×îÐ¡ÏÂ×¢ÖµXXX");
		final Dimension tipsDim = new Dimension(445, 20);
		tipsLabel.setSize(tipsDim);
		tipsLabel.setMinimumSize(tipsDim);
		tipsLabel.setMaximumSize(tipsDim);
		tipsLabel.setPreferredSize(tipsDim);
		tipsLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 20));
		tipsLabel.setForeground(Color.WHITE);
		middlePanel1.add(tipsLabel);
		*/
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
					//System.out.println("ÆúÅÆ");
					disableAllAction();
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					String res =TexasHttpUtils.request("localhost:8888", "texas", "abort", obj.toString());
					System.out.println(res);
					JPlayerMyselfPanel.this.job.suspend = false;
					
					System.out.println(res);

				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		betBtn.addActionListener(new ActionListener() { //¼Ó×¢
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					//System.out.println("¼Ó×¢");
					disableAllAction();
					int addTo = 0;
					try {
						addTo = Integer.parseInt(betNum.getText());
					} catch (NumberFormatException ee) {
						addTo = 0;
					}
						
					if(addTo <= addMin) {
						addTo = addMin;
					} else if(addTo >= addMax) {
						addTo = addMax;
					}
					
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					obj.put("addto", addTo);
					String res =TexasHttpUtils.request("localhost:8888", "texas", "add", obj.toString());
					System.out.println(res);
					JPlayerMyselfPanel.this.job.suspend = false;

					
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
	
		checkBtn.addActionListener(new ActionListener() { //ÈÃÅÆ
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					disableAllAction();
				
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					String res =TexasHttpUtils.request("localhost:8888", "texas", "check", obj.toString());
					System.out.println(res);
					JPlayerMyselfPanel.this.job.suspend = false;

				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		
		callBtn.addActionListener(new ActionListener() { //¸ú×¢
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					System.out.println("¸ú×¢");
					disableAllAction();
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					obj.put("followto", followTo);
					String res =TexasHttpUtils.request("localhost:8888", "texas", "follow", obj.toString());
					System.out.println(res);
					JPlayerMyselfPanel.this.job.suspend = false;
					//System.out.println(res);


					
				} catch (final Exception ev) {
					ev.printStackTrace();
				}
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					//System.out.println("×¼±¸");
					startBtn.setEnabled(false);
					startBtn.setVisible(false);
					JSONObject obj = new JSONObject();
					obj.put("token", token);
					String res =TexasHttpUtils.request("localhost:8888", "texas", "ready", obj.toString());

					//System.out.println(res);
				}catch(final Exception ev) {
					ev.printStackTrace();
				}
			}
		}

		);
		
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		facePanel.setLayout(new FlowLayout());
		ImageIcon iconOrigin1 = new ImageIcon("./ËØ²Ä/face_1.jpg");
		Image image1 = iconOrigin1.getImage();
		Image newImage1 = image1.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(newImage1);
		JLabel label1 = new JLabel();
		label1.setIcon(icon1);
		ImageIcon iconOrigin2 = new ImageIcon("./ËØ²Ä/face_2.jpg");
		Image image2 = iconOrigin2.getImage();
		Image newImage2 = image2.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(newImage2);
		JLabel label2 = new JLabel();
		label2.setIcon(icon2);
		ImageIcon iconOrigin3 = new ImageIcon("./ËØ²Ä/face_3.jpg");
		Image image3 = iconOrigin3.getImage();
		Image newImage3 = image3.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon3 = new ImageIcon(newImage3);
		JLabel label3 = new JLabel();
		label3.setIcon(icon3);
		ImageIcon iconOrigin4 = new ImageIcon("./ËØ²Ä/face_4.jpg");
		Image image4 = iconOrigin4.getImage();
		Image newImage4 = image4.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon4 = new ImageIcon(newImage4);
		JLabel label4 = new JLabel();
		label4.setIcon(icon4);
		ImageIcon iconOrigin5 = new ImageIcon("./ËØ²Ä/face_5.jpg");
		Image image5 = iconOrigin5.getImage();
		Image newImage5 = image5.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon5 = new ImageIcon(newImage5);
		JLabel label5 = new JLabel();
		label5.setIcon(icon5);
		ImageIcon iconOrigin6 = new ImageIcon("./ËØ²Ä/face_6.jpg");
		Image image6 = iconOrigin6.getImage();
		Image newImage6 = image6.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon6 = new ImageIcon(newImage6);
		JLabel label6 = new JLabel();
		label6.setIcon(icon6);
		ImageIcon iconOrigin7 = new ImageIcon("./ËØ²Ä/face_7.jpg");
		Image image7 = iconOrigin7.getImage();
		Image newImage7 = image7.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon7 = new ImageIcon(newImage7);
		JLabel label7 = new JLabel();
		label7.setIcon(icon7);
		ImageIcon iconOrigin8 = new ImageIcon("./ËØ²Ä/face_8.jpg");
		Image image8 = iconOrigin8.getImage();
		Image newImage8 = image8.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon8 = new ImageIcon(newImage8);
		JLabel label8 = new JLabel();
		label8.setIcon(icon8);
		ImageIcon iconOrigin9 = new ImageIcon("./ËØ²Ä/face_9.jpg");
		Image image9 = iconOrigin9.getImage();
		Image newImage9 = image9.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon9 = new ImageIcon(newImage9);
		JLabel label9 = new JLabel();
		label9.setIcon(icon9);
		ImageIcon iconOrigin10 = new ImageIcon("./ËØ²Ä/face_10.jpg");
		Image image10 = iconOrigin10.getImage();
		Image newImage10 = image10.getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon10 = new ImageIcon(newImage10);
		JLabel label10 = new JLabel();
		label10.setIcon(icon10);
		facePanel.add(label1);
		facePanel.add(label2);
		facePanel.add(label3);
		facePanel.add(label4);
		facePanel.add(label5);
		Dimension dim = new Dimension(55, 10);
		JLabel label_1 = new JLabel("1",JLabel.CENTER);
		label_1.setSize(dim);
		label_1.setMaximumSize(dim);
		label_1.setMinimumSize(dim);
		label_1.setPreferredSize(dim);
		label_1.setForeground(Color.white);
		//label_1.setBorder(BorderFactory.createLineBorder(Color.RED));
		facePanel.add(label_1);
		JLabel label_2 = new JLabel("2",JLabel.CENTER);
		label_2.setSize(dim);
		label_2.setMaximumSize(dim);
		label_2.setMinimumSize(dim);
		label_2.setPreferredSize(dim);
		label_2.setForeground(Color.white);
		facePanel.add(label_2);
		JLabel label_3 = new JLabel("3",JLabel.CENTER);
		label_3.setSize(dim);
		label_3.setMaximumSize(dim);
		label_3.setMinimumSize(dim);
		label_3.setPreferredSize(dim);
		label_3.setForeground(Color.white);
		facePanel.add(label_3);
		JLabel label_4 = new JLabel("4",JLabel.CENTER);
		label_4.setSize(dim);
		label_4.setMaximumSize(dim);
		label_4.setMinimumSize(dim);
		label_4.setPreferredSize(dim);
		label_4.setForeground(Color.white);
		facePanel.add(label_4);
		JLabel label_5 = new JLabel("5",JLabel.CENTER);
		label_5.setSize(dim);
		label_5.setMaximumSize(dim);
		label_5.setMinimumSize(dim);
		label_5.setPreferredSize(dim);
		label_5.setForeground(Color.white);
		facePanel.add(label_1);
		facePanel.add(label_2);
		facePanel.add(label_3);
		facePanel.add(label_4);
		facePanel.add(label_5);
		facePanel.add(label6);
		facePanel.add(label7);
		facePanel.add(label8);
		facePanel.add(label9);
		facePanel.add(label10);
		JLabel label_6 = new JLabel("6",JLabel.CENTER);
		label_6.setSize(dim);
		label_6.setMaximumSize(dim);
		label_6.setMinimumSize(dim);
		label_6.setPreferredSize(dim);
		label_6.setForeground(Color.white);
		//label_1.setBorder(BorderFactory.createLineBorder(Color.RED));
		facePanel.add(label_6);
		JLabel label_7 = new JLabel("7",JLabel.CENTER);
		label_7.setSize(dim);
		label_7.setMaximumSize(dim);
		label_7.setMinimumSize(dim);
		label_7.setPreferredSize(dim);
		label_7.setForeground(Color.white);
		facePanel.add(label_7);
		JLabel label_8 = new JLabel("8",JLabel.CENTER);
		label_8.setSize(dim);
		label_8.setMaximumSize(dim);
		label_8.setMinimumSize(dim);
		label_8.setPreferredSize(dim);
		label_8.setForeground(Color.white);
		facePanel.add(label_8);
		JLabel label_9 = new JLabel("9",JLabel.CENTER);
		label_9.setSize(dim);
		label_9.setMaximumSize(dim);
		label_9.setMinimumSize(dim);
		label_9.setPreferredSize(dim);
		label_9.setForeground(Color.white);
		facePanel.add(label_9);
		JLabel label_10 = new JLabel("10",JLabel.CENTER);
		label_10.setSize(dim);
		label_10.setMaximumSize(dim);
		label_10.setMinimumSize(dim);
		label_10.setPreferredSize(dim);
		label_10.setForeground(Color.white);
		facePanel.add(label_10);
		Dimension chatDim = new Dimension(50, 20);
		chat.setSize(chatDim);
		chat.setMaximumSize(chatDim);
		chat.setMinimumSize(chatDim);
		chat.setPreferredSize(chatDim);
		facePanel.add(chat);
		facePanel.add(send);
		facePanel.setOpaque(true);
		facePanel.setBackground(new Color(9,120,0));
		rightPanel.add(facePanel, BorderLayout.NORTH);
		Dimension rightPanelDim = new Dimension(300, 200);
		rightPanel.setSize(rightPanelDim);
		rightPanel.setMaximumSize(rightPanelDim);
		rightPanel.setMinimumSize(rightPanelDim);
		rightPanel.setPreferredSize(rightPanelDim);
		rightPanel.setOpaque(true);
		rightPanel.setBackground(new Color(9,120,0));

		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					
				}catch(final Exception ev) {
					
				}
			}
		}

		);

		this.setLayout(new FlowLayout());
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
		betNum.setText(Integer.toString(addMin));
		betNum.setEnabled(add);
		betNum.setVisible(add);
		betBtn.setEnabled(add);
		betBtn.setVisible(add);
		callBtn.setEnabled(follow);
		callBtn.setVisible(follow);
		checkBtn.setEnabled(check);
		checkBtn.setVisible(check);
		foldBtn.setEnabled(abort);
		foldBtn.setVisible(abort);

	}

	public void updateReadyButton(boolean ready) {
		this.startBtn.setVisible(ready);
		this.startBtn.setEnabled(true);
	}
	public void updateReady(boolean isReady) {
		if(isReady) {
			usernameLabel.setForeground(Color.YELLOW);
		} else {
			usernameLabel.setForeground(Color.WHITE);
		}
		
	}

	public void disableAllAction() {
		betNum.setEnabled(false);
		betNum.setVisible(false);
		betBtn.setEnabled(false);
		betBtn.setVisible(false);
		callBtn.setEnabled(false);
		callBtn.setVisible(false);
		checkBtn.setEnabled(false);
		checkBtn.setVisible(false);
		foldBtn.setEnabled(false);
		foldBtn.setVisible(false);
	}

			

}

