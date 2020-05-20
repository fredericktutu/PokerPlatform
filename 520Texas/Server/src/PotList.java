import java.util.ArrayList;
public class PotList{

	/*
		���ص�List������װ�����ɸ�pot
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
			//�����л��ܲ��뱾���ص��˼��뽱��
				if(playerListCopy.get(i).money >= findMin(playerListCopy) && playerListCopy.get(i).playing)
					// ��������һ���Ǯ���һ�û�����ƣ��ͼ����������������������
					{pot.addPlayer(playerListCopy.get(i).number);
					System.out.printf("\n���%d ���뵱ǰ����Ľ���", i);
					}
			}
			


			for(int i = 0 ; i < playerListCopy.size(); i++ ){ 
			//������Ӧ�ü��뱾���ص�Ǯ���뽱��
				if(playerListCopy.get(i).money >= findMin(playerListCopy))
					{pot.addMoney(findMin(playerListCopy));
					System.out.printf("\n���%d ���뽱��", findMin(playerListCopy));}
			}
			

			System.out.printf("\n��ǰ��pot������%dԪ", pot.potMoney);
			
			
			

			int min = findMin(playerListCopy);

			for(int i = 0 ; i < playerListCopy.size(); i++ ){ 
			//�������˵�Ǯ��ȥmin�����������һ������
				
				playerListCopy.get(i).money = playerListCopy.get(i).money - min;
				
				if(playerListCopy.get(i).money < 0) //�����и�����Ǯ�����б�����������money��set��0
					playerListCopy.get(i).money = 0;
			}
			
			for(int i = 0 ; i < playerListCopy.size(); i++ ) {
				System.out.printf("\n�������ν��ع��죬��%d����ҵ�ʣ����ע���У�", i);
				System.out.printf("%d",playerListCopy.get(i).money);
				
			}

			this.potList.add(pot);
			System.out.println("\n��ǰ���ع������");
		}


	}

	public boolean hasMoney(ArrayList<PlayerState> playerList){ //�жϻ���û������д���0�Ľ��
		int i = playerList.size();
		while(i > 0){
			if(playerList.get(i-1).money > 0)
				return true;
			else
				i = i - 1;
		}
		return false;
	}


	public int findMin(ArrayList<PlayerState> playerList){//�ҵ���С����עֵ
		int min = 0; 
		for(int i = 0 ; i < playerList.size(); i++){ //���ҵ�һ����Ϊ0�Ľ��ֵ
			if(playerList.get(i).money > 0)
				min = playerList.get(i).money;
		}

		for(int i = 0 ; i < playerList.size(); i++){ //����������������С
			if(playerList.get(i).money > 0 && playerList.get(i).money <= min )
				min = playerList.get(i).money;
		}

		return min;

	}

}

