
public class TexasTableEvent {
	// 枚举值
	public static final int TABLESTATE_DELIVER_HAND_CARD = 1; // 发手牌
	public static final int TABLESTATE_DELIVER_THREE_PUBLIC_CARD = 2; // 发公共牌
	public static final int TABLESTATE_GAME_OVER = 3; // 一局游戏结束
	public static final int TABLESTATE_DELIVER_FIRST_EXTRA_PUBLIC_CARD = 4; // 发了第一张额外的公共牌
	public static final int TABLESTATE_DELIVER_SECOND_EXTRA_PUBLIC_CARD = 5; // 发了第二张额外公共牌
	public static final int TABLESTATE_NOTHING = 6;
	public int whatHappen; // 当前发生了什么，是上面个中的一个
	public int whoseTurn; // 轮到谁了，牌桌上第几个人


}