
public class BetInfo{//单条下注信息，包含用户名和下注金额以及是在第几轮下的注,quit=0时表示不弃牌，反之弃牌
	public String username;
	public int money;
	public int round;
	public boolean quit;

	public BetInfo(String username, int money, int round, boolean quit){
		this.username = username;
		this.money = money;
		this.round = round;
		this.quit = quit;
	}
	
}