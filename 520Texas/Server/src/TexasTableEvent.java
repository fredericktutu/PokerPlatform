
public class TexasTableEvent {//游戏逻辑返回给平台逻辑的返回值
	//枚举值
	public static int TABLESTATE_DELIVER_HAND_CARD = 1; //发手牌
	public static int TABLESTATE_DELIVER_THREE_PUBLIC_CARD = 2; //发公共牌
	public static int TABLESTATE_GAME_OVER = 3; //一局游戏结束
	public static int TABLESTATE_DELIVER_FIRST_EXTRA_PUBLIC_CARD = 4; //发了第一张额外的公共牌
	public static int TABLESTATE_DELIVER_SECOND_EXTRA_PUBLIC_CARD =5; //发了第二张额外公共牌
	public static int TABLESTATE_NOTHING = 6;//没有发生上述事件
	
	
	public int whatHappen; //当前发生了什么，是上面个中的一个
	public int whoseTurn;  //轮到谁了，牌桌上第几个人
	
	public boolean add;
	public boolean follow;
	public boolean check;
	public boolean abort;
	
	public int addMoney;//add为true时才有用，最大能加到多少，也就是自己带的钱总数
	public int followMoney;//follow为true时才有用，跟注需要跟到多少，如果钱不够要all in
	
	public int maxChips;//当前最大注码，用于图形界面给出跟注选项
	
	
}

