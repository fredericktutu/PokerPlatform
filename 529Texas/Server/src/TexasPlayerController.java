
import java.util.ArrayList;





public class TexasPlayerController{
    public Server server;
    TexasGameHelper helper;
    //加注
    public TexasPlayerController() {
        return;
    }
    public void handler_add_bet(String token, int addTo) {
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_add_bet(player, addTo);
    }
    //跟注
    public void handler_follow_bet(String token, int followTo) {  
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_follow_bet(player, followTo);
    }
    //弃牌/盖牌
    public void handler_abort_bet(String token) {  
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_abort_bet(player);
    }
    //让牌
    public void handler_check(String token) {  
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_check(player);
    }

    //准备好
    public void handler_ready(String token) {
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_ready(player);
    }

    public RoomMessage handler_croom(String token) {
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        return helper.action_room_connect(player);
    }
    
    public TexasEnvelope handler_cgame(String token) {
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        return helper.action_game_connect(player);
    }
    /* 
    更新操作，由TexasGameHelper调用
    而本控制器，根据判断发生的不同事件，调用gui类不同的更新方法（可能是更新多个）
    */

    public String betInfoString(BetInfo bet) {
        switch(bet.action) {
			case 1:
				return "第" + (bet.round + 1)  + "轮: "  + bet.username + " 加注至" + bet.money;
			case 2:
				return "第" + (bet.round + 1)  + "轮: "  + bet.username + " 跟注至" + bet.money;
			case 3:
				return "第" + (bet.round + 1)  + "轮: "  + bet.username + " 弃牌";
			case 4:
				return "第" + (bet.round + 1)  + "轮: "  + bet.username + " 让牌";
		}
		return "";
    }
    
    public TexasEnvelope update(Player player,TexasMessage msg) {
        TexasEnvelope te = new TexasEnvelope();
        
        //ITexasGameGUI gui = player.texasGameGUI;
    
        ArrayList<String> logs = new ArrayList<String>();
        for(BetInfo log :msg.logs) {
            logs.add(betInfoString(log));
        }

        te.logs = logs;
        te.publicCards = msg.public_cards;
        te.myPlayerAsset = msg.playerMoney[msg.playerIndex];
        te.myPlayerChip = msg.bets.get(msg.playerIndex);
        te.myPrivateCards = msg.private_cards;
        te.mySeat = msg.playerIndex;
        
        if(msg.isYourTurn) {
            //gui.updateMyPlayerAction(msg.add, msg.follow, msg.check, msg.abort, msg.addMin, msg.addMax, msg.followTo);
            te.add = msg.add;
            te.follow = msg.follow;
            te.check = msg.check;
            te.abort = msg.abort;
            te.addMin = msg.addMin;
            te.addMax = msg.addMax;
            te.followTo = msg.followTo;
        }

        ArrayList<Integer> playerMoney = new ArrayList<Integer>();
        ArrayList<Integer> playerChip = new ArrayList<Integer>();
        
        for(int i=0;i<msg.playerMoney.length;i++) {
            playerMoney.add(msg.playerMoney[i]);
            playerChip.add(msg.bets.get(i));
        }
        te.playerMoney = playerMoney;
        te.playerChip = playerChip;
        
        ArrayList<ArrayList<Card>> playerPrivateCards = new ArrayList<ArrayList<Card>>();
        ArrayList<Integer> moneyChange = new ArrayList<Integer>();
        if(msg.whatHappen == 3) {    
            te.end = true;        
            te.myPlayerAsset = player.money;
            te.ready = true;
            for(int i=0;i<msg.playerMoney.length;i++) {
                playerPrivateCards.add(msg.allCards.get(i));
                moneyChange.add(msg.moneyChange[i]);
            }
            te.playerPrivateCards = playerPrivateCards;
            te.moneyChange = moneyChange;
            //gui.updatePlayerPrivateCards();
        }
        return te;
    }
    /*
    public void update(Player player, RoomMessage msg) {
 
        //ITexasGameGUI gui = player.texasGameGUI;
        if(gui == null) { //有可能还没绑定
            return;
        }
        for(int i=0;i<msg.players.length;i++) {
            if(i == msg.yourseat) { //自己
                gui.updateMyPlayerName(msg.players[i].name);
                gui.updateMyPlayerAsset(msg.players[i].money);
                gui.updateMyPlayerChip(0);
                if(msg.players[i].isReady) {
                    gui.updateMyPlayerReady(true);
                    gui.updateMyPlayerReadyAction(false);
                } else {
                    gui.updateMyPlayerReady(false);
                    gui.updateMyPlayerReadyAction(true);
                }
            } else { //别人
                if(msg.players[i] == null) continue;
                gui.updatePlayerName(i, msg.players[i].name);
                gui.updatePlayerAsset(i, msg.players[i].money);
                gui.updatePlayerChip(i, 0);
                gui.updatePlayerReady(i, msg.players[i].isReady);
            }

        }
        
        


    }
    */


}