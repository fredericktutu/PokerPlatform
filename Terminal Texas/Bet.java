
import java.util.ArrayList;

public class Bet{
	
	public ArrayList<BetInfo> betLog = new ArrayList<BetInfo>();//��ע��־������װ���е���ע��Ϣ��������BetInfoΪԪ�ص�ArrayList
	
	public void bet(String username, int money, int round, boolean quit){//��ע����
		BetInfo log = new BetInfo(username,money,round,quit);
		this.betLog.add(log);
	}
}
