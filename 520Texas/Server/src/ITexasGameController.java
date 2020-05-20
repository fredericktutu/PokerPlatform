import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITexasGameController extends Remote{
    void handler_add_bet(String token, int addTo) throws RemoteException;
    void handler_follow_bet(String token, int followTo)throws RemoteException;
    void handler_abort_bet(String token) throws RemoteException;
    void handler_check(String token)throws RemoteException ;
    void handler_ready(String token)throws RemoteException;



}