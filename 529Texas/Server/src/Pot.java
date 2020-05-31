import java.util.ArrayList;
public class Pot{

	/*
		单个奖池，里面包含参与瓜分该奖池的玩家编号List与奖池里的总钱数
	*/

	ArrayList<Integer> potPlayer = new ArrayList<Integer>(); //里面是所有玩家的玩家编号
	int potMoney = 0; //本奖池含有的总钱数

	public void addPlayer(int playerNumber){ //向奖池参与玩家中加入某编号代表的玩家
		this.potPlayer.add(playerNumber);
	}

	public void addMoney(int money){ //设置奖池金额
		this.potMoney = this.potMoney + money;
	}

}