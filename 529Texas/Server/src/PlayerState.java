
public class PlayerState {
	String name;
	int number;
	int money;//���˶���Ǯ
	int allMoney;//���˶���Ǯ
	int leftMoney;//�����¶���Ǯ
	boolean allin;
	boolean playing;//��������
	
	public PlayerState(String name, int number, int money, boolean playing, int allMoney, int leftMoney, boolean allin)
	{
		this.name = name;
		this.number = number;
		this.money = money;
		this.playing = playing;
		this.allMoney = allMoney;
		this.leftMoney = leftMoney;
		this.allin = allin;
	}
}
