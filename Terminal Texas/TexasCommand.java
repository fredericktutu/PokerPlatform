import java.util.ArrayList;
public class TexasCommand { //游戏助理类给牌桌类的命令
	//枚举值
	public ArrayList<String> nameList ;
	
	public static final int HAPPEN_EVERYONE_READY = 1; //每个人都准备好了
	public static final int HAPPEN_SOMEONE_BET = 2; //有人下注

	public static final int ACTION_ADD_BET = 1; //加注
	public static final int ACTION_FOLLOW_BET = 2; //跟注
    public static final int ACTION_ABORT_BET = 3; //弃牌
    public static final int ACTION_CHECK = 4; //让牌
	
	
	public int whatHappen; //发生了什么，是上面三个中的一个值

	public int who; //谁，list里面的顺序

	public int action; //动作

	public int chips;  //筹码数
	

	//房间里每个人名字传进去

	public TexasCommand(int who, Player2TexasGameHelperCommand p2gCommand) {
		this.whatHappen = 2;
		this.who = who;
		this.action = p2gCommand.action;
		this.chips = p2gCommand.chips;
	}
	
	public TexasCommand(ArrayList<Player> players){
		nameList = new ArrayList<String>();
		for(Player p:players){
			nameList.add(p.name);
		}
		this.whatHappen = 1;
	}
	
	
}