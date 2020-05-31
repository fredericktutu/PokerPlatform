import java.util.ArrayList;
import java.util.Random;
public class TexasPokerTable {
	int playNumber;
	int playingNumber;
	int whoseTurn = 0;//�ֵ�˭��
	int whichTurn = 0;//�ڼ�����
	ArrayList<String> nameList = new ArrayList<String>();//�����б�
	ArrayList<PrivateCards> myPrivateCardsList = new ArrayList<PrivateCards>();//˽����
	//ArrayList<Card> myPublicCards = new ArrayList<Card>();//������
	ArrayList<PokerTypeResult> pokerTypeResultList = new ArrayList<PokerTypeResult>();//��������б��ᱻɾ��
	ArrayList<PokerTypeResult> inOrderPointsPokerTypeResultList = new ArrayList<PokerTypeResult>();//����������
	ArrayList<PokerTypeResult> finalPokerTypeResultList = new ArrayList<PokerTypeResult>();//�����ͺͷ����������������б�
	Deal myDeal = new Deal();//����������
	Bet myBet = new Bet();//������ע��
	Deliver myDeliver = new Deliver();
	

	ArrayList<PlayerState> playerList = new ArrayList<PlayerState>();//���״̬
	int button;//ׯ��λ
	int maxChipsNumber;//������������������ˣ������ж�һ����ע����
	int maxChips;//���ע��
	int smallBlind;
	int bigBlind;
	//int maxChipsLimit;//��ע���� 05162336����
	int minChipsLimit;//��ע
	boolean thisTurnSomeoneAdd;//�������Ƿ����˼�ע��,1Ϊ���˼�ע��
	int winNumber [];//ʤ��
	int winnerNumber = 1;//ʤ������
	int winnerMoney;//ʤ���ֵܷ���Ǯ
	int moneyChange [];//���ֽ������Ǯ�仯���
	TexasTableEvent result;

	public TexasTableEvent call(TexasCommand command) {//ƽ̨�߼�����call������ͨ�������ͷ���ֵ���ഫ����Ϣ
		if(command.who != whoseTurn) return result;
		result = new TexasTableEvent();
		if(command.whatHappen == TexasCommand.HAPPEN_EVERYONE_READY)//׼����ϣ�������ʼ
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
				whoseTurn = (whoseTurn + 1) % playNumber;//��һ����������
				if(playerList.get(whoseTurn).playing == true)
				{
					break;				
				}
			
			}
			
			//������ע����
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
			
			//�ж�һ����ע�Ƿ������Ҫ��Ҫ����
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
						result.check = true;//all inʱ��ƽ̨��δ���
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
						result.check = true;//all inʱ��ƽ̨��δ���
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
						result.check = true;//all inʱ��ƽ̨��δ���
						result.abort = false;
					}
					
					return result;
				}
				else if(whichTurn == 3)
				{
					//����
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
		else//�˶��ò���
		{
			return result;
		}
	}
	
	public TexasPokerTable(int playNumber)//����ʱ��֪��Ϸ����
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
		int maxTypeNumber = 1;//ӵ��������͵�����
		

		for(int i = 0; i < this.playNumber; i++)
		{
			if(playerList.get(i).playing == true)
			{
				ArrayList<Card> compareCards = new ArrayList<Card>();//������+˽����
				compareCards.addAll(this.myDeal.publicCards);
				compareCards.add(this.myPrivateCardsList.get(i).privateCards.get(0));
				compareCards.add(this.myPrivateCardsList.get(i).privateCards.get(1));
				/*
				for(int j = 0; j < compareCards.size(); j++)
				{
					System.out.println(compareCards.get(j).suit + "," + compareCards.get(j).face);
				}		
				System.out.println("\n");*/
				Win win = new Win();//�����ж���
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
		
		System.out.println("��������" + finalPokerTypeResultList.size());
		
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
			//System.out.println("������username is " + pc.username + ", name is " + player.name);
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

