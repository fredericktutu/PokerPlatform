import java.util.ArrayList;
public class TexasGameHelper { //游戏助理类
    public TexasPokerTable table;
    public Room room;//players不变
	public ArrayList<String> playerReadyList;
	public int maxPlayerNumber;

	public void start_game() {
		for(Player p : room.players){
			p.controller.update("请准备:", true);
		}
	}


    public void call(Player player, Player2TexasGameHelperCommand p2gCommand) { //遥控器调用，打包
		TexasCommand texasCommand;
		TexasTableEvent event;
        //if(所有人都为1)，new TexasCommand(room.players);
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
			p.controller.update(eventToString(event, room.players.indexOf(p)),event.whoseTurn==room.players.indexOf(p));//根据TexasTableEvent接口进行改变，目的是通过player获得对应player的牌桌信息
		}
	}
	

	
	public TexasGameHelper(Room room){
		this.room = room;
		this.table = room.table;
		//this.maxPlayerNumber = room.players.size();
		//只有创建者一个人
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
				result = result + "发了私有牌\n";
				
				for(int j = 0; j < 2; j++)
				{
					result = result + "第" + play + "位玩家，您的" + "第" + (j+1) + "张牌是" + changeSuitToChinese(table.myDeal.privateCardsList.get(play).privateCards.get(j).suit) 
					+ (1 + table.myDeal.privateCardsList.get(play).privateCards.get(j).face) + "\n";
				}
				result = result + "\n";							
				return result;
	        case 2 :
	        	result = result + "发了三张公共牌\n";
	        	for(int i = 0; i < 3; i++)
	    		{
	    			result = result + "第" + (i+1) + "张公共牌是" + changeSuitToChinese(table.myDeal.publicCards.get(i).suit) 
	    			+ (1 + table.myDeal.publicCards.get(i).face) + "\n";    			
	    		}
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "玩家" + log.username + "弃牌";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "玩家" + log.username + "下注" + log.money;
	        		return result;
	        	}
	        case 3 :       
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "玩家" + log.username + "弃牌" + "\n" + "游戏结束";	        		
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "玩家" + log.username + "下注" + log.money + "\n" + "游戏结束";
	        		return result;
	        	}
	        	
	        case 4 :
	        	result = result + "发了第四张公共牌\n";
	        	for(int i = 0; i < 4; i++)
	    		{
	    			result = result + "第" + (i+1) + "张公共牌是" + changeSuitToChinese(table.myDeal.publicCards.get(i).suit) 
	    			+ (1 + table.myDeal.publicCards.get(i).face) + "\n";    			
	    		}
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "玩家" + log.username + "弃牌";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "玩家" + log.username + "下注" + log.money;
	        		return result;
	        	}
	        case 5 :
	        	result = result + "发了第五张公共牌\n";
	        	for(int i = 0; i < 5; i++)
	    		{
	    			result = result + "第" + (i+1) + "张公共牌是" + changeSuitToChinese(table.myDeal.publicCards.get(i).suit) 
	    			+ (1 + table.myDeal.publicCards.get(i).face) + "\n";    			
	    		}
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit = true)
	        	{
	        		result = result + "玩家" + log.username + "弃牌";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "玩家" + log.username + "下注" + log.money;
	        		return result;
	        	}
	        case 6 : 
	        	length = table.myBet.betLog.size();
	        	log = table.myBet.betLog.get(length-1);
	        	if (log.quit == true)
	        	{
	        		result = result + "玩家" + log.username + "弃牌";
	        		return result;
	        	}
	        	else
	        	{
	        		result = result + "玩家" + log.username + "下注" + log.money;
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
				result = "黑桃";
				return result;
			 case 1 :
				result = "红桃";
				return result;
			 case 2 :
				 result = "梅花";
					return result;
			 case 3 :
				 result = "方块";
					return result;
			 default :
				 result = "黑桃";
					return result;
		}
	}

}
