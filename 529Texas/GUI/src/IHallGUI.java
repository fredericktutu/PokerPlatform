import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IHallGUI extends Remote{
    void update_display_rooms(ArrayList<ActiveRoomInfo> rooms) throws RemoteException;
    void update_message(String s) throws RemoteException;
    void update_switch_page(String roomId, String roomType) throws RemoteException;
}