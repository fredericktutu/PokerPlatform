import java.util.ArrayList;
public class PotList{

	/*
		奖池的List，里面装有若干个pot
	*/

	ArrayList<Pot> potList = new ArrayList<Pot>();

	public void buildPotList(ArrayList<PlayerState> playerList){

		ArrayList<PlayerState> playerListCopy = new ArrayList<PlayerState>();
		for(int i = 0 ; i < playerList.size(); i++ )
		{ 						
			PlayerState player = new PlayerState(
					playerList.get(i).name,
					playerList.get(i).number,
					playerList.get(i).money,
					playerList.get(i).playing,
					playerList.get(i).allMoney,
					playerList.get(i).leftMoney,
					playerList.get(i).allin);
			playerListCopy.add(player);
			
		}
		
		for(int i = 0 ; i < playerList.size(); i++ )
		{ 						
			//System.out.println(playerListCopy.get(i).money);
			
		}
		
		//System.arraycopy(playerList,0,playerListCopy,0,playerList.size());
		while(hasMoney(playerListCopy)){
			Pot pot = new Pot();


			for(int i = 0 ; i < playerListCopy.size(); i++ ){ 
			//把所有还能参与本奖池的人加入奖池
				if(playerListCopy.get(i).money >= findMin(playerListCopy) && playerListCopy.get(i).playing)
					// 如果这个玩家还有钱，且还没有弃牌，就加入现在正建立的这个奖池
					{pot.addPlayer(playerListCopy.get(i).number);
					System.out.printf("\n玩家%d 加入当前构造的奖池", i);
					}
			}
			


			for(int i = 0 ; i < playerListCopy.size(); i++ ){ 
			//把所有应该加入本奖池的钱加入奖池
				if(playerListCopy.get(i).money >= findMin(playerListCopy))
					{pot.addMoney(findMin(playerListCopy));
					System.out.printf("\n金额%d 加入奖池", findMin(playerListCopy));}
			}
			

			System.out.printf("\n当前的pot里面有%d元", pot.potMoney);
			
			
			

			int min = findMin(playerListCopy);

			for(int i = 0 ; i < playerListCopy.size(); i++ ){ 
			//把所有人的钱扣去min，方便计算下一个奖池
				
				playerListCopy.get(i).money = playerListCopy.get(i).money - min;
				
				if(playerListCopy.get(i).money < 0) //不能有负数的钱，所有被减到负数的money都set成0
					playerListCopy.get(i).money = 0;
			}
			
			for(int i = 0 ; i < playerListCopy.size(); i++ ) {
				System.out.printf("\n经过本次奖池构造，第%d号玩家的剩余下注金还有：", i);
				System.out.printf("%d",playerListCopy.get(i).money);
				
			}

			this.potList.add(pot);
			System.out.println("\n当前奖池构造完成");
		}


	}

	public boolean hasMoney(ArrayList<PlayerState> playerList){ //判断还有没有玩家有大于0的金额
		int i = playerList.size();
		while(i > 0){
			if(playerList.get(i-1).money > 0)
				return true;
			else
				i = i - 1;
		}
		return false;
	}


	public int findMin(ArrayList<PlayerState> playerList){//找到最小的下注值
		int min = 0; 
		for(int i = 0 ; i < playerList.size(); i++){ //先找到一个不为0的金额值
			if(playerList.get(i).money > 0)
				min = playerList.get(i).money;
		}

		for(int i = 0 ; i < playerList.size(); i++){ //再在正数里面找最小
			if(playerList.get(i).money > 0 && playerList.get(i).money <= min )
				min = playerList.get(i).money;
		}

		return min;

	}

}

