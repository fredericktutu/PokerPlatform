
public class PlayerState {
	String name;
	int number;
	int money;//下了多少钱
	int allMoney;//带了多少钱
	int leftMoney;//还能下多少钱
	boolean allin;
	boolean playing;//还在玩吗
	
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
