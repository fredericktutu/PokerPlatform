import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHallController extends Remote{
    public void handler_create_room(String token, RoomOption option)throws RemoteException; //d
    public void handler_enter_room(String token, String roomId, String passWord)throws RemoteException;
    public void handler_exit_room(String token)throws RemoteException;
    public void handler_display_rooms(String token)throws RemoteException;
    public void handler_search_room(String token, String roomId, String passWord)throws RemoteException;
    public void handler_match_room(String token)throws RemoteException;
}