import java.rmi.RemoteException;
import java.util.ArrayList;

import java.rmi.server.UnicastRemoteObject;



public class TexasPlayerController extends UnicastRemoteObject implements ITexasGameController{
    public Server server;
    TexasGameHelper helper;
    //��ע
    public TexasPlayerController() throws RemoteException{
        return;
    }
    public void handler_add_bet(String token, int addTo) throws RemoteException{
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_add_bet(player, addTo);
    }
    //��ע
    public void handler_follow_bet(String token, int followTo)throws RemoteException {  
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_follow_bet(player, followTo);
    }
    //����/����
    public void handler_abort_bet(String token) throws RemoteException{  
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_abort_bet(player);
    }
    //����
    public void handler_check(String token)throws RemoteException {  
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_check(player);
    }

    //׼����
    public void handler_ready(String token)throws RemoteException {
        Player player = server.decipher(token);
        helper = player.texasGameHelper;
        helper.action_ready(player);
    }
    
    /*
    ���²�������TexasGameHelper����
    �����������������жϷ����Ĳ�ͬ�¼�������gui�಻ͬ�ĸ��·����������Ǹ��¶����
    */

    public String betInfoString(BetInfo bet) {
        switch(bet.action) {
			case 1:
				return "��" + (bet.round + 1)  + "��: "  + bet.username + " ��ע��" + bet.money;
			case 2:
				return "��" + (bet.round + 1)  + "��: "  + bet.username + " ��ע��" + bet.money;
			case 3:
				return "��" + (bet.round + 1)  + "��: "  + bet.username + " ����";
			case 4:
				return "��" + (bet.round + 1)  + "��: "  + bet.username + " ����";
		}
		return "";
    }
    
    public void update(Player player,TexasMessage msg) {
        try {
            ITexasGameGUI gui = player.texasGameGUI;
        
            ArrayList<String> logs = new ArrayList<String>();
            for(BetInfo log :msg.logs) {
                logs.add(betInfoString(log));
            }
            
            gui.updatePLayerLog(logs);
            gui.updatePublicCards(msg.public_cards);
            gui.updateMyPlayerAsset(msg.playerMoney[msg.playerIndex]);
            gui.updateMyPlayerChip(msg.bets.get(msg.playerIndex)); 
            gui.updateMyPlayerPrivateCards(msg.private_cards);
            if(msg.isYourTurn) {
                System.out.println(msg.playerIndex +"is your turn");
                System.out.println("TexasPlayerController: addMin " + msg.addMin + " addMax " + msg.addMax + " followTo " +msg.followTo);
                gui.updateMyPlayerAction(msg.add, msg.follow, msg.check, msg.abort, msg.addMin, msg.addMax, msg.followTo);
            } else {
                System.out.println(msg.playerIndex +"not your turn");
                gui.updateMyPlayerAction(false, false, false, false, 0, 0, 0);
            }

            for(int i=0;i<msg.playerMoney.length;i++) {
                if(i == msg.playerIndex)continue;
                gui.updatePlayerAsset(i, msg.playerMoney[i]);
                gui.updatePlayerChip(i, msg.bets.get(i));
                gui.updatePlayerPrivateCards(i);
            }
            
    
            if(msg.whatHappen == 3) {            
                gui.updateMyPlayerAsset(player.money);
                gui.updateMyPlayerAction(false, false, false, false, 0, 0, 0);
                gui.updateMyPlayerReadyAction(true);
                for(int i=0;i<msg.playerMoney.length;i++) {
                    if(i == msg.playerIndex)continue;
                    gui.updatePlayerPrivateCards(i, msg.allCards.get(i));
                }
                gui.updateGameResult(msg.moneyChange);


                //gui.updatePlayerPrivateCards();
            }
        }catch(RemoteException re) {
            re.printStackTrace();
        }

    }

    public void update(Player player, RoomMessage msg) {
        try {
            ITexasGameGUI gui = player.texasGameGUI;
            if(gui == null) { //�п��ܻ�û��
                return;
            }
            for(int i=0;i<msg.players.length;i++) {
                if(i == msg.yourseat) { //�Լ�
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
                } else { //����
                    if(msg.players[i] == null) continue;
                    gui.updatePlayerName(i, msg.players[i].name);
                    gui.updatePlayerAsset(i, msg.players[i].money);
                    gui.updatePlayerChip(i, 0);
                    gui.updatePlayerReady(i, msg.players[i].isReady);
                }
    
            }
        }catch(RemoteException re) {
            re.printStackTrace();
        }

    }


}