import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


//import javafx.event.ActionEvent;

public class ConnectJob implements ActionListener {
	public boolean croom = true;
	public boolean suspend = false;
	int count =0;
	TexasPokerFrame frame;
	String token;
	JSONParser parser = new JSONParser();

	public ConnectJob(String token,TexasPokerFrame frame) {
		this.frame = frame;
		this.token = token;
	}
	
	public void updateRoom(RoomMessage msg) {
		System.out.println("更新房间");
        if(frame.mySeat == -1) {
			frame.updatePlayerPanels(msg.yourseat, msg.players.length);
			frame.updateMyPlayerName(msg.players[msg.yourseat].name);
			frame.updateMyPlayerAsset(msg.players[msg.yourseat].money);
			frame.updateMyPlayerChip(0);
		}

		for(int i=0;i<msg.players.length;i++) {
			if(i == msg.yourseat) { //自己
				if(msg.players[i].isReady) {
					frame.updateMyPlayerReady(true);
					frame.updateMyPlayerReadyAction(false);
				} else {
					frame.updateMyPlayerReady(false);
					frame.updateMyPlayerReadyAction(true);
				}
			} else { //别人
				if(msg.players[i] == null) continue;
				//System.out.println("ConnectJob: i is" + i);
				frame.updatePlayerName(i, msg.players[i].name);
				frame.updatePlayerAsset(i, msg.players[i].money);
				frame.updatePlayerChip(i, 0);
				frame.updatePlayerReady(i, msg.players[i].isReady);
			}

		}
		if(msg.isAllReady) {
			this.croom = false;
		}
	}


	public void updateGame(TexasEnvelope te) {


		frame.updatePlayerLog(te.logs);
		frame.updatePublicCards(te.publicCards);
		frame.updateMyPlayerAsset(te.myPlayerAsset);
		frame.updateMyPlayerChip(te.myPlayerChip); 
		frame.updateMyPlayerPrivateCards(te.myPrivateCards);
		frame.updateMyPlayerAction(te.add, te.follow, te.check, te.abort, te.addMin, te.addMax, te.followTo);
		frame.updateMyPlayerReadyAction(te.ready);
		for(int i=0;i<te.playerMoney.size();i++) {
			if(i == te.mySeat)continue;
			frame.updatePlayerAsset(i, te.playerMoney.get(i));
			frame.updatePlayerChip(i, te.playerChip.get(i));
			frame.updatePlayerPrivateCards(i);
		}
		if(te.add||te.follow||te.check||te.abort) {
			this.suspend = true;
		}
		if(te.end) {
			frame.updateMyPlayerAsset(te.myPlayerAsset);
			frame.updateMyPlayerAction(te.add, te.follow, te.check, te.abort, te.addMin, te.addMax, te.followTo);
			frame.updateMyPlayerReadyAction(te.ready);
			for(int i=0;i<te.playerMoney.size();i++) {
				if(i == te.mySeat)continue;
				frame.updatePlayerPrivateCards(i,te.playerPrivateCards.get(i));
			}

			int[] moneyChange = new int[te.moneyChange.size()];
			for(int i=0;i<te.moneyChange.size();i++) {
				moneyChange[i] = te.moneyChange.get(i).intValue();
			}
			frame.updateGameResult(moneyChange);
			this.croom = true;
		}

	}


	public void actionPerformed(ActionEvent e) {
		try {
			if(suspend) return;
			if(croom) {
				JSONObject obj = new JSONObject();
				obj.put("token", token);
				String res =TexasHttpUtils.request("localhost:8888", "texas", "croom", obj.toString());
				
				JSONObject rmsgobj = (JSONObject)parser.parse(res);
				RoomMessage msg = RoomMessage.json2RoomMessage(rmsgobj);
				updateRoom(msg);
	
			} else {
				JSONObject obj = new JSONObject();
				obj.put("token", token);
				String res =TexasHttpUtils.request("localhost:8888", "texas", "cgame", obj.toString());
	
				JSONObject teobj = (JSONObject)parser.parse(res);
				TexasEnvelope te = TexasEnvelope.json2TexasEnvelope(teobj);
				updateGame(te);
			}

		}catch(Exception ee) {
			ee.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		/*
		Timer timmer = new Timer(1000, new ConnectJob());
		timmer.start(); 
		JOptionPane.showMessageDialog(null, "hi");
		*/
		
	}




}