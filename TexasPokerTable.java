import java.util.ArrayList;
import java.util.Random;
public class TexasPokerTable {
	int playNumber;
	int playingNumber;
	int whoseTurn = 0;//�ֵ�˭��
	int whichTurn = 0;//�ڼ�����
	ArrayList<String> nameList = new ArrayList<String>();//�����б�
	ArrayList<PrivateCards> myPrivateCardsList = new ArrayList<PrivateCards>();//˽����
	ArrayList<Card> myPublicCards = new ArrayList<Card>();//������
	Deal myDeal = new Deal();//����������
	Bet myBet = new Bet();//������ע��
	ArrayList<PlayerState> playerList = new ArrayList<PlayerState>();//���״̬
	int button;//ׯ��λ
	int maxChipsNumber;//������������������ˣ������ж�һ����ע����
	int maxChips;//���ע��
	int smallBlind;
	int bigBlind;
	
	
	
	public TexasTableEvent call(TexasCommand command) {//ƽ̨�߼�����call������ͨ�������ͷ���ֵ���ഫ����Ϣ
		TexasTableEvent result = new TexasTableEvent();
		if(command.whatHappen == TexasCommand.HAPPEN_EVERYONE_READY)//׼����ϣ�������ʼ
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
				whoseTurn = (whoseTurn + 1) % playNumber;//��һ����������
				if(playerList.get(whoseTurn).playing == true)
				{
					break;				
				}
			
			}
			
			//������ע����
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
			
			//�ж�һ����ע�Ƿ������Ҫ��Ҫ����
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
					//����
					result.whatHappen = TexasTableEvent.TABLESTATE_GAME_OVER;
					result.whoseTurn = this.whoseTurn;
					return result;
				}
			}
			result.whatHappen = TexasTableEvent.TABLESTATE_NOTHING;
			result.whoseTurn = this.whoseTurn;
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

