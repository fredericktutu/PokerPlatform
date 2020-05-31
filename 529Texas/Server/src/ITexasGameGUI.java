import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ITexasGameGUI extends Remote{
    void updatePlayerPanels(int mySeat, int n) throws RemoteException; //start

    void updatePLayerLog(ArrayList<String> playerLog) throws RemoteException; //everytime
    void updatePublicCards(ArrayList<Card> publicCards) throws RemoteException; //everytime

    void updateMyPlayerName(String name) throws RemoteException; //start
    void updateMyPlayerAsset(int asset) throws RemoteException; //start
    void updateMyPlayerChip(int chip) throws RemoteException; //everytime
    void updateMyPlayerPrivateCards(ArrayList<Card> privateCards) throws RemoteException; //everytime
    void updateMyPlayerAction(boolean add, boolean follow, boolean check, boolean abort, int addMin, int addMax, int followTo) throws RemoteException; //everytime
    void updateMyPlayerReady(boolean isReady)throws RemoteException ; //room 
    void updateMyPlayerReadyAction(boolean ready) throws RemoteException; //room

    void updatePlayerName(int seat, String name) throws RemoteException; //start
    void updatePlayerAsset(int seat, int asset) throws RemoteException; //start
    void updatePlayerChip(int seat, int chip) throws RemoteException; //everytime
    void updatePlayerPrivateCards(int seat, ArrayList<Card> privateCards) throws RemoteException; //end game
    void updatePlayerPrivateCards(int seat) throws RemoteException;//everytime
    void updatePlayerReady(int seat, boolean isReady)throws RemoteException ; //room

    void updateGameResult(int[] moneyChange) throws RemoteException;

}