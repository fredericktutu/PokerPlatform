public class TexasGameHelper {
    public  TexasPokerTable table;
    public  Room room;
	
	//private static List<String> playerReady = new ArrayList<String>();
	
	
	public TexasTableEvent event;
	public TexasCommand command;
	public int who;
	//public int oldChips; //上一个玩家下的注
	public TexasMessage msg;
	
	//05042115
	public TexasPlayerController playerController;
	//public TexasAIController aiController;
	
	public int minChipsLimit;
	
	public TexasEnvelope[] playerEnvelopes;
	public PlayerInRoom[] playerInRooms;
	boolean isAllReady;

    //setter
    public void set_table(TexasPokerTable table) {
        this.table = table;
    }

    public void set_room(Room room) {
		this.room = room;
		this.playerEnvelopes = new TexasEnvelope[this.room.players.length];
		this.playerInRooms = new PlayerInRoom[this.room.players.length];
		this.isAllReady = false;
    }
	
	public void getWho(Player player){
		//this.who = room.players.indexOf(player);
		int i = 0;
		for(i = 0;player!=this.room.players[i];i++) ;
		this.who = i;
	}

	public int getPlayerIndex(Player player) {
		int i = 0;
		for(i = 0;player!=this.room.players[i];i++) ;
		return i;
	}
	



    //动作，被controller调用
    // 加注
    public void action_add_bet(Player player, int addTo){
		System.out.println("TexasGameHelper: " + player.name + "加注");
		//上一个玩家的注
		getWho(player);
		//this.oldChips = table.getLastChips((who-1)%room.players.length);
		
		//需与游戏逻辑讨论，05170051注
		//if(player.can_cut_money(addTo)){
			//System.out.println("TexasGameHelper: addTo:" + addTo);
			this.command = new TexasCommand(who,TexasCommand.ACTION_ADD_BET,addTo);
			this.event = table.call(this.command);
			System.out.println("TexasGameHelper: whatHappen:" + event.whatHappen + ", whoseTurn:" + event.whoseTurn);
			broadcast(this.event);
			//打包给message
		//}
		//else{
			//;//
		//}

    }  
    //跟注
    //public void action_follow_bet(Player player){
	public void action_follow_bet(Player player, int followTo) {
		System.out.println("TexasGameHelper: " + player.name + "跟注");
    //上一个玩家的注
		getWho(player);
		//this.oldChips = table.getLastChips((who-1)%room.players.length);
		//this.oldChips = followTo;
		this.command = new TexasCommand(who,TexasCommand.ACTION_FOLLOW_BET,followTo);
		this.event = table.call(this.command);
		System.out.println("TexasGameHelper: whatHappen:" + event.whatHappen + ", whoseTurn:" + event.whoseTurn);
		broadcast(this.event);

    }  
    //弃牌/盖牌
    public void action_abort_bet(Player player){
		System.out.println("TexasGameHelper: " + player.name + "弃牌");
		getWho(player);
		this.command = new TexasCommand(who,TexasCommand.ACTION_ABORT_BET,0);
		this.event = table.call(this.command);
		System.out.println("TexasGameHelper: whatHappen:" + event.whatHappen + ", whoseTurn:" + event.whoseTurn);
		broadcast(this.event);
    }   
    //让牌
    public void action_check(Player player) {
		System.out.println("TexasGameHelper: " + player.name + "让牌");
		getWho(player);
		//this.oldChips = table.getLastChips(who);
		this.command = new TexasCommand(who,TexasCommand.ACTION_CHECK,0);
		this.event = table.call(this.command);
		System.out.println("TexasGameHelper: whatHappen:" + event.whatHappen + ", whoseTurn:" + event.whoseTurn);
		broadcast(this.event);

    }
	//一个玩家准备好
	public void action_ready(Player player) {
		/*if(room.playerReady.contains(player.name)) ;//疑问：要不要返回给图形界面显示“您已准备”等信息
		else{
			room.playerReady.add(player.name);
			broadcast();
			if(playerReady.size()==room.players.size()){//Warning:此处为room里定义出的最大容量。
				this.command = new TexasCommand(room.players);
				this.event = table.call(this.command);
				broadcast(this.event);
			}
		}*/
		new Thread(new Runnable(){
			@Override
			public void run() {
				room.playerReady(player);
				action_room_change();
				if(room.gameOn) {
					System.out.println("游戏可以开始");
					action_all_ready();
				}
			}
        }).start();


    }

	
	//room发生改变（进入、退出、准备）
	
	public void action_room_change(){
	
		Player[] players = room.players;
		//System.out.println("TexasGameHelper: room change");
		for(int i = 0;i<players.length;i++){
			if(players[i]==null){
				playerInRooms[i]=null;
			}
			else{
				//System.out.println("TexasGameHelper:name" + players[i].name);
				playerInRooms[i]=new PlayerInRoom(players[i].name, room.playersReady[i]==1,players[i].money);
			}
		}
		
		/*
		for(int i = 0;i<players.length;i++){
			if(players[i]==null) continue;
			else{
				rmsg.yourseat = i;
				playerController.update(players[i],rmsg);
			}
		}
		*/
	}
	
	
	//所有人都准备好，游戏开始
	public void action_all_ready() {
		this.isAllReady = true;
		if(room.option.modeType==1) {
			//System.out.println("single");
			this.command = new TexasCommand(true,room.players, 1);
		}
		else {
			this.command = new TexasCommand(false, room.players, room.option.levelType);
		}

		this.minChipsLimit = command.minChipsLimit;
		//this.oldChips = this.minChipsLimit;
		this.event = table.call(this.command);
		broadcast(this.event);
	}
	public TexasEnvelope action_game_connect(Player p) {
		return this.playerEnvelopes[getPlayerIndex(p)];
	}
	public RoomMessage action_room_connect(Player p) {
		RoomMessage tmp = new RoomMessage();
		tmp.yourseat = getPlayerIndex(p);
		tmp.isAllReady = isAllReady;
		tmp.players = this.playerInRooms;
		return tmp;
	}
	// 未开始  -> RoomMessage  gameOn
	// 开始   -> TexasEnvelope whatHappen ==3
	// 未开始
	
	private int[] getPlayerMoney() {
		int[] playerMoney = new int[this.room.players.length];
		for(int i = 0;i<this.room.players.length;i++) {
			playerMoney[i] = this.room.players[i].money; 
		}
		return playerMoney;
	}
	
	private int getAddMin(int addMax, int maxChips) {
		int m = this.minChipsLimit;
		//return Math.min((maxChips/m+maxChips%m==0?0:1)*m,addMax); //>=
		return Math.min((maxChips/m + 1)*m,addMax); //>
	}
	
	private void kickPlayers(boolean isSingle) {
		if(isSingle) return ;
		else {
			
    		for(int i = 0;i<room.players.length;i++) {

    			//room.players[i].update_info(table.getMoneyChange()[i], table.getMoneyChange()[i]>0);
				room.players[i].update_money_local(table.getMoneyChange()[i]);
    			if(!room.players[i].is_human()) {
					room.players[i].playing = false;
					if(room.players[i].exit == true) {
						room.players[i].server.service_exit(room.players[i].token);
					}
					room.players[i].update_exit();
    				room.players[i]=null;
				}
				
    		}
		}
	}

	


    //给定一个event，我们要给不同的玩家分发message，message的格式件TexasMessage.java
    
    public void broadcast(TexasTableEvent event) {
        //for
		//  player 对应的 controller调用 update(message)

		TexasPokerTable newTable =null;
    	if(event.whatHappen==TexasTableEvent.TABLESTATE_GAME_OVER) { 
    		newTable = new TexasPokerTable(room.players.length);
			//取消所有人准备
			room.clearReady(); 
			room.gameOn =false;
			this.isAllReady = false;
    		kickPlayers(room.option.modeType==1);
    	}

		int i = 0;
		for(i = 0;i<room.players.length;i++){
			Player p = room.players[i];
			//System.out.println(p.name+"的消息");
			if(p==null) continue;
			int addMin = getAddMin((event.whoseTurn == i ? event.addMoney : 0),table.maxChips);
			int addMax = (event.whoseTurn == i ? event.addMoney : 0);
			int followTo = event.followMoney;
			//boolean flag = false;
			
			//System.out.println("TexasGameHelper: " + "addMin:" + addMin + "addMax:" +addMax + "followTo: " +followTo );
			this.msg = new TexasMessage(
					event.whatHappen,
					table.whichTurn+1,
					event.whoseTurn,
					event.whoseTurn == i,
					i,
					
					table.getLogs(),
					table.getPlayerStatus(),
					
					event.add,
					event.follow,
					event.check,
					event.abort,
					
					//table.getBets().get(i)<=table.maxChipsLimit,
					table.getPrivateCards(p),
					table.getPublicCards(),
					//table.maxChips,

					getAddMin((event.whoseTurn == i ? event.addMoney : 0),table.maxChips),
					(event.whoseTurn == i ? event.addMoney : 0),
					event.followMoney,
					
					table.getPlayerBet(),	
					table.getPlayerMoney(),
					table.getMoneyChange(),
					
					room.option.modeType==1,
					
					table.winNumber,
					table.getAllPrivateCards()
					);
			//System.out.println("TexasGameHelper:update");
			if(p.is_human())
				this.playerEnvelopes[i]=playerController.update(p,this.msg);
			else
				p.aiController.update(this.msg);
			//System.out.println("TexasGameHelper:end update");
		}
		
		if(event.whatHappen==TexasTableEvent.TABLESTATE_GAME_OVER) {
			table = newTable;
			action_room_change();
		}
		
    }
	
	//
	/*private void broadcast(){
		for(Player p:room.players.size()){
			controller.update(p,playerReady);
		}
	}*/

    
    //player.update_info(msg.moneyChange[msg.playerIndex], msg.moneyChange[msg.playerIndex]>0);

}