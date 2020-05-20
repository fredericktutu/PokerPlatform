
import java.util.ArrayList;

public class Bet{
	
	public ArrayList<BetInfo> betLog = new ArrayList<BetInfo>();//下注日志，里面装所有的下注信息。包含以BetInfo为元素的ArrayList
	
	public void bet(String username, int action, int money, int round, boolean quit){//下注方法
		BetInfo log = new BetInfo(username,action,money,round,quit);
		this.betLog.add(log);
	}
}
