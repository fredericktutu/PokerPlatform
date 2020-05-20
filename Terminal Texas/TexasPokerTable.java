import java.util.ArrayList;
import java.util.Random;
public class TexasPokerTable {
	int playNumber;
	int playingNumber;
	int whoseTurn = 0;//轮到谁了
	int whichTurn = 0;//第几轮了
	ArrayList<String> nameList = new ArrayList<String>();//人名列表
	ArrayList<PrivateCards> myPrivateCardsList = new ArrayList<PrivateCards>();//私有牌
	ArrayList<Card> myPublicCards = new ArrayList<Card>();//公共牌
	Deal myDeal = new Deal();//创建发牌类
	Bet myBet = new Bet();//创建下注类
	ArrayList<PlayerState> playerList = new ArrayList<PlayerState>();//玩家状态
	int button;//庄家位
	int maxChipsNumber;//本轮中下了最大筹码的人，用于判断一轮下注结束
	int maxChips;//最大注码
	int smallBlind;
	int bigBlind;
	
	
	
	public TexasTableEvent call(TexasCommand command) {//平台逻辑调用call函数，通过参数和返回值互相传递消息
		TexasTableEvent result = new TexasTableEvent();
		if(command.whatHappen == TexasCommand.HAPPEN_EVERYONE_READY)//准备完毕，即将开始
		{
			initialize(this.playNumber, command.nameList);
			result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_HAND_CARD;
			result.whoseTurn = this.whoseTurn;
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
				playerList.get(command.who).money += command.chips;
				myBet.bet(playerList.get(command.who).name, command.chips, whichTurn, false);
				this.maxChipsNumber = command.who;
				this.maxChips = playerList.get(command.who).money;
			}
			else if(command.action == TexasCommand.ACTION_FOLLOW_BET)
			{
				playerList.get(command.who).money += command.chips;
				myBet.bet(playerList.get(command.who).name, command.chips, whichTurn, false);
			}
			else if(command.action == TexasCommand.ACTION_ABORT_BET)
			{
				playerList.get(command.who).playing = false;
				myBet.bet(playerList.get(command.who).name, 0, whichTurn, true);
				playingNumber--;
				if(playingNumber == 1)
				{
					result.whatHappen = TexasTableEvent.TABLESTATE_GAME_OVER;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
			}
			else if(command.action == TexasCommand.ACTION_CHECK)
			{
				myBet.bet(playerList.get(command.who).name, 0, whichTurn, true);
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
					myDeal.dealPublic();
					result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_THREE_PUBLIC_CARD;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
				else if(whichTurn == 1)
				{
					whichTurn++;
					myDeal.dealOneRound();
					result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_FIRST_EXTRA_PUBLIC_CARD;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
				else if(whichTurn == 2)
				{
					whichTurn++;
					myDeal.dealOneRound();
					result.whatHappen = TexasTableEvent.TABLESTATE_DELIVER_SECOND_EXTRA_PUBLIC_CARD;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
				else if(whichTurn == 3)
				{
					//结算
					result.whatHappen = TexasTableEvent.TABLESTATE_GAME_OVER;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
			}
			result.whatHappen = TexasTableEvent.TABLESTATE_NOTHING;
			result.whoseTurn = this.whoseTurn;
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
	}
	
	public boolean initialize(int playNumber, ArrayList<String> nameList) {		
		myDeal.initPrivate(playNumber);	
		this.myPrivateCardsList = myDeal.privateCardsList;
		this.nameList = nameList;
		this.whoseTurn = this.button;
		this.maxChipsNumber = this.button;
		this.playingNumber = playNumber;
		for(int i = 0; i < playNumber; i++)
		{
			PlayerState player = new PlayerState(nameList.get(i), i, 0, true);
			playerList.add(player);
		}
		return true;
	}
	
	public TableInfo getTableInfo() {
		TableInfo tableInfo = new TableInfo();
		tableInfo.myPublicCards = this.myDeal.publicCards;
		tableInfo.betLog = myBet.betLog;
		tableInfo.playerList = this.playerList;
		return tableInfo;
	}
}

