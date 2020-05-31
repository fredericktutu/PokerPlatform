import java.util.ArrayList;
public class TexasCommand { //游戏助理类给牌桌类的命令
	//枚举值
	/*public ArrayList<String> nameList ;
	
	public static final int HAPPEN_EVERYONE_READY = 1; //每个人都准备好了
	public static final int HAPPEN_SOMEONE_BET = 2; //有人下注

	public static final int ACTION_ADD_BET = 1; //加注
	public static final int ACTION_FOLLOW_BET = 2; //跟注
    public static final int ACTION_ABORT_BET = 3; //弃牌
    public static final int ACTION_CHECK = 4; //让牌
	
	
	public int whatHappen; //发生了什么，是上面三个中的一个值

	public int who; //谁，list里面的顺序

	public int action; //动作

	public int chips;  //筹码数*/
	
	public static int HAPPEN_EVERYONE_READY = 1; //每个人都准备好了
	public static int HAPPEN_SOMEONE_BET = 2; //有人下注

	public static int ACTION_ADD_BET = 1; //加注
	public static int ACTION_FOLLOW_BET = 2; //跟注
    public static int ACTION_ABORT_BET = 3; //弃牌
    public static int ACTION_CHECK = 4; //让牌
	
	public int whatHappen; //发生了什么，是上面的一个值

	public int who; //谁

	public int action; //动作

	public int chips;  //筹码数
	
	//public int maxChipsLimit;//上限，05162338退役
	public int minChipsLimit;//底注，从Room/RoomMessage得到
	
	public ArrayList<String> nameList = new ArrayList<String>();
	public int [] allMoney; 
	
	

	//房间里每个人名字传进去

	/*public TexasCommand(int who, Player2TexasGameHelperCommand p2gCommand) {
		this.whatHappen = HAPPEN_SOMEONE_BET;
		this.who = who;
		this.action = p2gCommand.action;
		this.chips = p2gCommand.chips;
	}*/ //0516退役
	
	//command.nameList, command.allMoney, command.minChipsLimit
	//初始化
	
	public TexasCommand(boolean single,Player[] players,int minChipsLimitType){
		this.nameList = new ArrayList<String>();
		this.allMoney = new int[players.length];
		int i = 0;
		for(Player p:players){
			this.nameList.add(p.name);
			if(!single) {
				this.allMoney[i++] = p.money;
			} else {
				this.allMoney[i++] = 20000;
			}
		}
		this.minChipsLimit = (int) Math.pow(10, minChipsLimitType) *10;
		this.whatHappen = HAPPEN_EVERYONE_READY;
	}
	
	//正式游戏的时候传输
	public TexasCommand(int who, int action, int chips){
		this.whatHappen = HAPPEN_SOMEONE_BET;
		this.who = who;
		this.action = action;
		this.chips = chips;
	}
	
	
	
}