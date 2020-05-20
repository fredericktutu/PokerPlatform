import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;



public class TexasGameGUI extends UnicastRemoteObject implements ITexasGameGUI {
    public TexasPokerFrame ui;
    public IServer server;
    public ITexasGameController texasGameController;

    public TexasGameGUI(String token, TexasPokerFrame ui)throws RemoteException {
        this.ui = ui;
        try{  
			//rmi 连接server
			String server_path = "//localhost:12200/Server";
            this.server  = (IServer)Naming.lookup(server_path);

			//rmi 连接hallController
			String texasGameController_path = "//localhost:12200/TexasGameController";
			this.texasGameController = (ITexasGameController)Naming.lookup(texasGameController_path);
			System.out.println("已连接服务器rmi");
		} catch(Exception es) {
			es.printStackTrace();
		}
		
		try {
            
			//LocateRegistry.createRegistry(12211);
			String rmi_address = "//localhost:12211/TexasGameGUI";
			Naming.bind(rmi_address, this);

			if(server.service_bind_texas(token, rmi_address)) System.out.println("服务器已绑定图形界面rmi");
		} catch(Exception er) {
			er.printStackTrace();
		}
    }

    public void updatePlayerPanels(int mySeat, int n) throws RemoteException {
        ui.updatePlayerPanels(mySeat, n);
    }

    public void updatePLayerLog(ArrayList<String> playerLog) throws RemoteException{
        ui.updatePlayerLog(playerLog);
    }
    public void updatePublicCards(ArrayList<Card> publicCards) throws RemoteException {
        ui.updatePublicCards(publicCards);
    }

    public void updateMyPlayerName(String name) throws RemoteException {
        ui.updateMyPlayerName(name);
    }
    public void updateMyPlayerAsset(int asset) throws RemoteException{
        ui.updateMyPlayerAsset(asset);
    }
    public void updateMyPlayerChip(int chip) throws RemoteException {
        ui.updateMyPlayerChip(chip);
    }
    public void updateMyPlayerPrivateCards(ArrayList<Card> privateCards) throws RemoteException {
        ui.updateMyPlayerPrivateCards(privateCards);
    }
    public void updateMyPlayerAction(boolean add, boolean follow, boolean check, boolean abort, int addMin, int addMax, int followTo) throws RemoteException{
        ui.updateMyPlayerAction(add, follow, check, abort, addMin, addMax, followTo);
    }
    public void updateMyPlayerReady(boolean isReady) throws RemoteException {
        ui.updateMyPlayerReady(isReady);
    }
    public void updateMyPlayerReadyAction(boolean ready) throws RemoteException  {
        ui.updateMyPlayerReadyAction(ready);
    }

    public void updatePlayerName(int seat, String name) throws RemoteException {
        ui.updatePlayerName(seat, name);
    }
    public void updatePlayerAsset(int seat, int asset) throws RemoteException {
        ui.updatePlayerAsset(seat, asset);
    }
    public void updatePlayerChip(int seat, int chip) throws RemoteException {
        ui.updatePlayerChip(seat, chip);
    }
    public void updatePlayerPrivateCards(int seat, ArrayList<Card> privateCards) throws RemoteException {
        ui.updatePlayerPrivateCards(seat, privateCards);
    }

    public void updatePlayerPrivateCards(int seat) {
        ui.updatePlayerPrivateCards(seat);
    }
    public void updatePlayerReady(int seat, boolean isReady) throws RemoteException {
        ui.updatePlayerReady(seat, isReady);
    }

    public void updateGameResult(int[] moneyChange) throws RemoteException {
    	ui.updateGameResult(moneyChange);
    }

}