
public class Player2TexasGameHelperCommand { // 玩家类 传给 游戏助理类命令
	public static int I_AM_READY = 1; // 我准备好了
	public static int I_BET = 2; // 我有一些关于德州扑克游戏的操作WW	
	public static int ACTION_ADD_BET = 1; // 加注
	public static int ACTION_FOLLOW_BET = 2; // 跟注
	public static int ACTION_ABORT_BET = 3; // 弃牌
	public static int ACTION_CHECK = 4; // 让牌

	public int whatHappen; // 发生了什么，是上面三个中的一个值

	public int action; // 动作

	public int chips; // 筹码数
}