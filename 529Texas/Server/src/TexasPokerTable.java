import java.util.ArrayList;
import java.util.Random;
public class TexasPokerTable {
	int playNumber;
	int playingNumber;
	int whoseTurn = 0;//轮到谁了
	int whichTurn = 0;//第几轮了
	ArrayList<String> nameList = new ArrayList<String>();//人名列表
	ArrayList<PrivateCards> myPrivateCardsList = new ArrayList<PrivateCards>();//私有牌
	//ArrayList<Card> myPublicCards = new ArrayList<Card>();//公共牌
	ArrayList<PokerTypeResult> pokerTypeResultList = new ArrayList<PokerTypeResult>();//最大牌型列表，会被删除
	ArrayList<PokerTypeResult> inOrderPointsPokerTypeResultList = new ArrayList<PokerTypeResult>();//按分数排列
	ArrayList<PokerTypeResult> finalPokerTypeResultList = new ArrayList<PokerTypeResult>();//按牌型和分数排序后最大牌型列表
	Deal myDeal = new Deal();//创建发牌类
	Bet myBet = new Bet();//创建下注类
	Deliver myDeliver = new Deliver();
	

	ArrayList<PlayerState> playerList = new ArrayList<PlayerState>();//玩家状态
	int button;//庄家位
	int maxChipsNumber;//本轮中下了最大筹码的人，用于判断一轮下注结束
	int maxChips;//最大注码
	int smallBlind;
	int bigBlind;
	//int maxChipsLimit;//加注上限 05162336退役
	int minChipsLimit;//底注
	boolean thisTurnSomeoneAdd;//本轮中是否有人加注过,1为有人加注过
	int winNumber [];//胜者
	int winnerNumber = 1;//胜者人数
	int winnerMoney;//胜者能分到的钱
	int moneyChange [];//本局结束后金钱变化情况
	TexasTableEvent result;

	public TexasTableEvent call(TexasCommand command) {//平台逻辑调用call函数，通过参数和返回值互相传递消息
		if(command.who != whoseTurn) return result;
		result = new TexasTableEvent();
		if(command.whatHappen == TexasCommand.HAPPEN_EVERYONE_READY)//准备完毕，即将开始
		{
			//initialize(this.playNumber, command.nameList, command.allMoney, command.maxChipsLimit, command.minChipsLimit);
			initialize(this.playNumber, command.nameList, command.allMoney, command.minChipsLimit);
			result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_HAND_CARD;
			result.whoseTurn = this.whoseTurn;
			result.maxChips = maxChips;
			result.add = true;
			result.follow = this.thisTurnSomeoneAdd;
			result.check = !this.thisTurnSomeoneAdd;
			result.abort = false;
			if(maxChips < playerList.get(whoseTurn).allMoney) {
				result.addMoney = playerList.get(whoseTurn).allMoney;
			} else {
				this.playerList.get(whoseTurn).allin = true;
			}
			return result;
		}
		else if(command.whatHappen == TexasCommand.HAPPEN_SOMEONE_BET)
		{
			while(true)
			{
				whoseTurn = (whoseTurn + 1) % playNumber;//下一个操作的人
				if(playerList.get(whoseTurn).playing == true)
				{
					break;				
				}
			
			}
			
			//处理下注操作
			if(command.action == TexasCommand.ACTION_ADD_BET)
			{
				playerList.get(command.who).money = command.chips;
				if(playerList.get(command.who).money == playerList.get(command.who).allMoney)
				{
					playerList.get(command.who).allin = true;
				}
				myBet.bet(playerList.get(command.who).name, TexasCommand.ACTION_ADD_BET, command.chips, whichTurn, false);
				this.maxChipsNumber = command.who;
				this.maxChips = playerList.get(command.who).money;
				this.thisTurnSomeoneAdd = true;
			}
			else if(command.action == TexasCommand.ACTION_FOLLOW_BET)
			{
				playerList.get(command.who).money = command.chips;
				if(playerList.get(command.who).money == playerList.get(command.who).allMoney)
				{
					playerList.get(command.who).allin = true;
				}
				myBet.bet(playerList.get(command.who).name, TexasCommand.ACTION_FOLLOW_BET, command.chips, whichTurn, false);
			}
			else if(command.action == TexasCommand.ACTION_ABORT_BET)
			{
				playerList.get(command.who).playing = false;
				myBet.bet(playerList.get(command.who).name, TexasCommand.ACTION_ABORT_BET, 0, whichTurn, true);
				playingNumber = playingNumber - 1;
				if(playingNumber == 1)
				{
					result.whatHappen = TexasTableEvent.TABLESTATE_GAME_OVER;
					result.whoseTurn = this.whoseTurn;
					result.maxChips = maxChips;
					this.moneyChange = new int[this.playNumber];
					for(int i = 0; i < this.playNumber; i++)
					{
						this.moneyChange[i] = -1*this.playerList.get(i).money;
					}
					for(int i = 0; i < this.playNumber; i++)
					{
						if(this.playerList.get(i).playing == true)
						{
							for(int j = 0; j < this.playNumber; j++)
							{
								this.moneyChange[i] += this.playerList.get(j).money;
							}
							break;
						}
					}

					return result;
				}
				
			}
			else if(command.action == TexasCommand.ACTION_CHECK)
			{
				myBet.bet(playerList.get(command.who).name, TexasCommand.ACTION_CHECK, playerList.get(command.who).money, whichTurn, false);
			}
			else
			{
				
			}
			
			//判断一轮下注是否结束，要不要发牌
			if(whoseTurn == maxChipsNumber)
			{
				if(whichTurn == 0) 
				{
					whichTurn++;
					this.thisTurnSomeoneAdd = false;
					myDeal.dealPublic();
					result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_THREE_PUBLIC_CARD;
					result.whoseTurn = this.whoseTurn;
					result.maxChips = maxChips;
					
					result.add = true;
					result.follow = this.thisTurnSomeoneAdd;
					result.check = !this.thisTurnSomeoneAdd;
					result.abort = true;
					if(maxChips < playerList.get(whoseTurn).allMoney)
					{
						result.addMoney = playerList.get(whoseTurn).allMoney;
						result.followMoney = maxChips;
					}
					else
					{
						result.add = false;
						result.followMoney = playerList.get(whoseTurn).allMoney;
					}
					if(maxChipsNumber == whoseTurn)
					{
						result.abort = false;
					}
					if(playerList.get(whoseTurn).allin == true)
					{
						result.add = false;
						result.follow = false;
						result.check = true;//all in时，平台如何处理？
						result.abort = false;
					}
					
					return result;
				}
				else if(whichTurn == 1)
				{
					whichTurn++;
					this.thisTurnSomeoneAdd = false;
					myDeal.dealOneRound();
					result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_FIRST_EXTRA_PUBLIC_CARD;
					result.whoseTurn = this.whoseTurn;
					result.maxChips = maxChips;
					
					result.add = true;
					result.follow = this.thisTurnSomeoneAdd;
					result.check = !this.thisTurnSomeoneAdd;
					result.abort = true;
					if(maxChips < playerList.get(whoseTurn).allMoney)
					{
						result.addMoney = playerList.get(whoseTurn).allMoney;
						result.followMoney = maxChips;
					}
					else
					{
						result.add = false;
						result.followMoney = playerList.get(whoseTurn).allMoney;
					}
					if(maxChipsNumber == whoseTurn)
					{
						result.abort = false;
					}
					if(playerList.get(whoseTurn).allin == true)
					{
						result.add = false;
						result.follow = false;
						result.check = true;//all in时，平台如何处理？
						result.abort = false;
					}
					
					return result;
				}
				else if(whichTurn == 2)
				{
					whichTurn++;
					this.thisTurnSomeoneAdd = false;
					myDeal.dealOneRound();
					result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_SECOND_EXTRA_PUBLIC_CARD;
					result.whoseTurn = this.whoseTurn;
					result.maxChips = maxChips;
					
					result.add = true;
					result.follow = this.thisTurnSomeoneAdd;
					result.check = !this.thisTurnSomeoneAdd;
					result.abort = true;
					if(maxChips < playerList.get(whoseTurn).allMoney)
					{
						result.addMoney = playerList.get(whoseTurn).allMoney;
						result.followMoney = maxChips;
					}
					else
					{
						result.add = false;
						result.followMoney = playerList.get(whoseTurn).allMoney;
					}
					if(maxChipsNumber == whoseTurn)
					{
						result.abort = false;
					}
					if(playerList.get(whoseTurn).allin == true)
					{
						result.add = false;
						result.follow = false;
						result.check = true;//all in时，平台如何处理？
						result.abort = false;
					}
					
					return result;
				}
				else if(whichTurn == 3)
				{
					//结算
					compare();
					myDeliver.playerList = this.playerList;
					myDeliver.finalPokerTypeResultList = this.finalPokerTypeResultList;
					myDeliver.moneyChange =  new int[playNumber]; 
					for(int j = 0; j < playNumber; j++)
					{
						myDeliver.moneyChange[j] = 0;
					}
					
					myDeliver.deliverMoney();
					moneyChange = myDeliver.moneyChange;					
					result.whatHappen = TexasTableEvent.TABLESTATE_GAME_OVER;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
			}
			result.whatHappen = TexasTableEvent.TABLESTATE_NOTHING;
			result.whoseTurn = this.whoseTurn;
			result.maxChips = maxChips;
			result.add = true;
			result.follow = this.thisTurnSomeoneAdd;
			result.check = !this.thisTurnSomeoneAdd;
			result.abort = true;
			if(maxChips < playerList.get(whoseTurn).allMoney)
			{
				result.addMoney = playerList.get(whoseTurn).allMoney;
				result.followMoney = maxChips;
			}
			else
			{
				result.add = false;
				result.followMoney = playerList.get(whoseTurn).allMoney;
			}
			if(maxChipsNumber == whoseTurn)
			{
				result.abort = false;
			}
			if(playerList.get(whoseTurn).allin == true)
			{
				result.add = false;
				result.follow = false;
				result.check = true;
				result.abort = false;
			}
						
			return result;
		}
		else//此段用不到
		{
			return result;
		}
	}
	
	public TexasPokerTable(int playNumber)//创建时告知游戏人数
	{
		this.playNumber = playNumber;
		Random random = new Random();
		this.button = random.nextInt(playNumber);
		//this.button = 0;
	}
	
	//public void initialize(int playNumber, ArrayList<String> nameList, int [] allMoney, int maxChipsLimit, int minChipsLimit) {		
	public void initialize(int playNumber, ArrayList<String> nameList, int [] allMoney, int minChipsLimit) {
		myDeal.initPrivate(playNumber);	
		this.myPrivateCardsList = myDeal.privateCardsList;
		this.nameList = nameList;
		this.whoseTurn = this.button;
		this.maxChipsNumber = this.button;
		this.playingNumber = playNumber;
		this.thisTurnSomeoneAdd = false;
		//this.maxChipsLimit = maxChipsLimit;
		this.minChipsLimit = minChipsLimit;
		this.maxChips = minChipsLimit;
		for(int i = 0; i < playNumber; i++)
		{
			PlayerState player = new PlayerState(nameList.get(i), i, 0, true, allMoney[i], allMoney[i], false);
			playerList.add(player);
		}
		for(int i = 0; i < playNumber; i++)
		{
			myBet.bet(playerList.get(i).name, 5, minChipsLimit, whichTurn, false);
			playerList.get(i).money = minChipsLimit;
		}
	}
	
	public void compare()
	{
		this.winNumber = new int[this.playNumber];		
		int maxTypeNumber = 1;//拥有最大牌型的人数
		

		for(int i = 0; i < this.playNumber; i++)
		{
			if(playerList.get(i).playing == true)
			{
				ArrayList<Card> compareCards = new ArrayList<Card>();//公共牌+私有牌
				compareCards.addAll(this.myDeal.publicCards);
				compareCards.add(this.myPrivateCardsList.get(i).privateCards.get(0));
				compareCards.add(this.myPrivateCardsList.get(i).privateCards.get(1));
				/*
				for(int j = 0; j < compareCards.size(); j++)
				{
					System.out.println(compareCards.get(j).suit + "," + compareCards.get(j).face);
				}		
				System.out.println("\n");*/
				Win win = new Win();//创建判定类
				win.initialize(compareCards);
				win.pokerType();
				win.pokerTypeResult.whoseCards = i;
				win.pokerTypeResult.calculatePoints();
				pokerTypeResultList.add(win.pokerTypeResult);
			}
		}
		
		
		for(int i = this.playingNumber; i > 0; i--)
		{
			int maxPoints = 0;
			int maxPlayer = 0;
			for(int j = 0; j < i; j++)
			{				
				if(pokerTypeResultList.get(j).points > maxPoints)
				{
					maxPoints = pokerTypeResultList.get(j).points;
					maxPlayer = j;
				}				
			}
			inOrderPointsPokerTypeResultList.add(pokerTypeResultList.get(maxPlayer));		
			pokerTypeResultList.remove(maxPlayer);
		}
		
		for(int i = this.playingNumber; i > 0; i--)
		{
			int maxType = 10;
			int maxPlayer = 0;
			for(int j = 0; j < i; j++)
			{				
				if((inOrderPointsPokerTypeResultList.get(j)).type < maxType)
				{
					maxPlayer = j;
					maxType = inOrderPointsPokerTypeResultList.get(j).type;
				}				
			}
			finalPokerTypeResultList.add(inOrderPointsPokerTypeResultList.get(maxPlayer));
			inOrderPointsPokerTypeResultList.remove(maxPlayer);
		}
		
		System.out.println("牌组数量" + finalPokerTypeResultList.size());
		
		/*
		for(int i = this.playingNumber; i > 0; i--)
		{
			int maxType = 10;
			int maxPlayer = 0;
			for(int j = 0; j < i; j++)
			{				
				if((pokerTypeResultList.get(j)).type < maxType)
				{
					maxPlayer = j;
					maxType = pokerTypeResultList.get(j).type;
				}				
			}
			inOrderTypePokerTypeResultList.add(pokerTypeResultList.get(maxPlayer));
			pokerTypeResultList.remove(maxPlayer);
		}
		
		for(int i = 0; i < this.playingNumber - 1; i++)
		{
			if(inOrderTypePokerTypeResultList.get(i).type == inOrderTypePokerTypeResultList.get(i+1).type)
			{
				maxTypeNumber++;
			}
			else
			{
				break;
			}
		}
		
		

		for(int i = maxTypeNumber; i > 0; i--)
		{
			int maxPoints = 0;
			int maxPlayer = 0;
			for(int j = 0; j < i; j++)
			{				
				if(inOrderTypePokerTypeResultList.get(j).points > maxPoints)
				{
					maxPoints = inOrderTypePokerTypeResultList.get(j).points;
					maxPlayer = j;
				}				
			}
			finalPokerTypeResultList.add(inOrderTypePokerTypeResultList.get(maxPlayer));		
			inOrderTypePokerTypeResultList.remove(maxPlayer);
		}
		
		
		for(int i = 0; i < maxTypeNumber - 1; i++)
		{
			if(finalPokerTypeResultList.get(i).points == finalPokerTypeResultList.get(i+1).points)
			{
				winnerNumber++;
			}
			else
			{
				break;
			}
		}
		*/
		for(int i = 0; i < winnerNumber; i++)
		{
			winNumber[finalPokerTypeResultList.get(i).whoseCards] = 1;
		}
		
	}
	
	public TableInfo getTableInfo() {
		TableInfo tableInfo = new TableInfo();
		tableInfo.myPublicCards = this.myDeal.publicCards;
		tableInfo.betLog = myBet.betLog;
		tableInfo.playerList = this.playerList;
		return tableInfo;
	}
	

	//0504GameHelper need function
	public int getLastChips(int who){
		return playerList.get(who).money;
	}
	
	public ArrayList<Integer> getBets(){
		ArrayList<Integer> bets = new ArrayList<>();
		for(PlayerState ps : this.playerList){
			bets.add(ps.money);
		}
		return bets;
	}
	
	public ArrayList<BetInfo> getLogs(){
		return myBet.betLog;
	}
	
	public ArrayList<Integer> getPlayerStatus(){
		ArrayList<Integer> status = new ArrayList<>();
		for(PlayerState ps : this.playerList){
			if(ps.playing)
				status.add(ps.number);
		}
		return status;
	}
	
	public int[] getPlayerMoney() {
		int[] res = new int[playerList.size()];
		for(int i=0;i<playerList.size();i++) {
			res[i] = playerList.get(i).allMoney;
			//System.out.println("money: " + res[i]);
		}
		return res;
	}

	public ArrayList<Integer> getPlayerBet() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(PlayerState ps: playerList) {
			res.add(ps.money);
			//System.out.println("bet: " + ps.money);
		}
		return res;
	}
	
	public ArrayList<Card> getPrivateCards(Player player){
		int i = 0;
		for(PrivateCards pc:this.myDeal.privateCardsList){
			//System.out.println("牌桌：username is " + pc.username + ", name is " + player.name);
			if(nameList.get(i).equals(player.name)){
				return pc.privateCards;
			}
			i++;
		}
		return null;
	}

	public ArrayList<ArrayList<Card>> getAllPrivateCards() {
		ArrayList<ArrayList<Card>> res = new ArrayList<ArrayList<Card>>();
		int i = 0; 
		for(PrivateCards pc:this.myDeal.privateCardsList) {
			res.add(pc.privateCards);
		}
		return res;
	}
	
	public ArrayList<Card> getPublicCards(){
		return myDeal.publicCards;
	}
	
	public int[] getMoneyChange(){
		return this.moneyChange;
	}
}

