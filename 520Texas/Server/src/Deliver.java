import java.util.ArrayList;


public class Deliver {
	int moneyChange [];//���ֽ������Ǯ�仯���
	ArrayList<PlayerState> playerList = new ArrayList<PlayerState>();//���״̬
	ArrayList<PokerTypeResult> finalPokerTypeResultList = new ArrayList<PokerTypeResult>();//�����ͺͷ�ֵ�������������б�
	PotList myPotList = new PotList();
	
	
	public void deliverMoney()
	{
		myPotList.buildPotList(playerList);
		for(int i = 0; i < myPotList.potList.size(); i++)
		{	
			ArrayList<PokerTypeResult> hasQualification = new ArrayList<PokerTypeResult>();//�����ͺͷ�ֵ�������������б�
			int winnerNumber = 1;//ʤ������
			int money;
			for(int j = 0; j < finalPokerTypeResultList.size(); j++)
			{				
				if(myPotList.potList.get(i).potPlayer.contains(finalPokerTypeResultList.get(j).whoseCards))
				{
					hasQualification.add(finalPokerTypeResultList.get(j));
				}
			}

			for(int j = 0; j < hasQualification.size() - 1; j++)
			{
				if((hasQualification.get(j).type == hasQualification.get(j+1).type) &&
						(hasQualification.get(j).points == hasQualification.get(j+1).points))
				{
					winnerNumber++;
				}
				else
				{
					break;
				}
			}
			money = myPotList.potList.get(i).potMoney / winnerNumber;
			for(int j = 0; j < winnerNumber; j++)
			{
				moneyChange[hasQualification.get(j).whoseCards] += money;
			}			
		}
				
		for(int i = 0; i < this.playerList.size(); i++)
		{
			moneyChange[i] -= this.playerList.get(i).money;
		}
		
	}
}
