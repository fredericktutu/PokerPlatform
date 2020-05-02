import java.util.ArrayList;
public class TexasGameHelper { //��Ϸ������
    public TexasPokerTable table;
    public Room room;//players����
	public ArrayList<String> playerReadyList;
	public int maxPlayerNumber;

	public void start_game() {
		for(Player p : room.players){
			p.controller.update("��׼��:", true);
		}
	}


    public void call(Player player, Player2TexasGameHelperCommand p2gCommand) { //ң�������ã����
		TexasCommand texasCommand;
		TexasTableEvent event;
        //if(�����˶�Ϊ1)��new TexasCommand(room.players);
		//table.call(TexasCommand)
		if(p2gCommand.whatHappen == Player2TexasGameHelperCommand.I_AM_READY) {
			if(playerReadyList.contains(player.name)) ;
			else{
				playerReadyList.add(player.name);
			}
			if(playerReadyList.size()==maxPlayerNumber){
				texasCommand = new TexasCommand(room.players);
				event = table.call(texasCommand);
				broadcast(event);
			} 
		}
		else if(p2gCommand.whatHappen == Player2TexasGameHelperCommand.I_BET) {
			if(this.playerReadyList.size() != maxPlayerNumber) return;
			else{
				texasCommand = new TexasCommand((int)room.players.indexOf(player), p2gCommand);
				event = table.call(texasCommand);
				broadcast(event);
			}
		}
    }
	
	public void broadcast(TexasTableEvent event){
		for(Player p : room.players){
			p.controller.update(eventToString(event, room.players.indexOf(p)),event.whoseTurn==room.players.indexOf(p));//����TexasTableEvent�ӿڽ��иı䣬Ŀ����ͨ��player��ö�Ӧplayer��������Ϣ
		}
	}
	

	
	public TexasGameHelper(Room room){
		this.room = room;
		this.table = room.table;
		//this.maxPlayerNumber = room.players.size();
		//ֻ�д�����һ����
		this.playerReadyList = new ArrayList<>();
	}
	public String eventToString(TexasTableEvent event, int play)
	{
		
		int length;
		BetInfo log;
		String result = "";
		switch(event.whatHappen)
	    {
			case 1 :
				result = result + "����˽����\n";
				
				for(int j = 0; j < 2; j++)
				{
					result = result + "��" + play + "λ��ң�����" + "��" + (j+1) + "������" + changeSuitToChinese(table.myDeal.privateCardsList.get(play).privateCards.get(j).suit) 
					+ (1 + table.myDeal.privateCardsList.get(play).privateCards.get(j).face) + "\n";
				}
				result = result + "\n";							
				return result;
	        case 2 :
	        	result = result + "�������Ź�����\n";
	        	for(int i = 0; i < 3; i++)
	    		{
	    			result = result + "��" + (i+1) + "�Ź�������" + changeSuitToChinese(table.myDeal.publicCards.get(i).suit) 
	    			+ (1 + table.myDeal.publicCards.get(i).face) + "\n";    			
	    		}
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "���" + log.username + "����";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "���" + log.username + "��ע" + log.money;
	        		return result;
	        	}
	        case 3 :       
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "���" + log.username + "����" + "\n" + "��Ϸ����";	        		
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "���" + log.username + "��ע" + log.money + "\n" + "��Ϸ����";
	        		return result;
	        	}
	        	
	        case 4 :
	        	result = result + "���˵����Ź�����\n";
	        	for(int i = 0; i < 4; i++)
	    		{
	    			result = result + "��" + (i+1) + "�Ź�������" + changeSuitToChinese(table.myDeal.publicCards.get(i).suit) 
	    			+ (1 + table.myDeal.publicCards.get(i).face) + "\n";    			
	    		}
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "���" + log.username + "����";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "���" + log.username + "��ע" + log.money;
	        		return result;
	        	}
	        case 5 :
	        	result = result + "���˵����Ź�����\n";
	        	for(int i = 0; i < 5; i++)
	    		{
	    			result = result + "��" + (i+1) + "�Ź�������" + changeSuitToChinese(table.myDeal.publicCards.get(i).suit) 
	    			+ (1 + table.myDeal.publicCards.get(i).face) + "\n";    			
	    		}
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit = true)
	        	{
	        		result = result + "���" + log.username + "����";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "���" + log.username + "��ע" + log.money;
	        		return result;
	        	}
	        case 6 : 
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "���" + log.username + "����";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "���" + log.username + "��ע" + log.money;
	        		return result;
	        	}
	        default :
	        	return result;
	    }
	}

	
	public String changeSuitToChinese(int suit)
	{
		String result;
		switch(suit)
		{
			 case 0 :
				result = "����";
				return result;
			 case 1 :
				result = "����";
				return result;
			 case 2 :
				 result = "÷��";
					return result;
			 case 3 :
				 result = "����";
					return result;
			 default :
				 result = "����";
					return result;
		}
	}

}
