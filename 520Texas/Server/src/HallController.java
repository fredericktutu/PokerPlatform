import java.rmi.server.UnicastRemoteObject;

import java.rmi.RemoteException;
public class HallController extends UnicastRemoteObject implements IHallController{
    Server server;
    HallHelper helper;

    public HallController(Server server, HallHelper helper)throws RemoteException {
        this.server = server;
        this.helper = helper;
    }
    //句柄
    public void handler_create_room(String token, RoomOption option)throws RemoteException {
        System.out.println("HallController:正在创建房间");
        Player p = server.decipher(token);
        if(p != null) {
            helper.action_create_room(p, option);
        } 
    }

    public void handler_enter_room(String token, String roomId, String passWord)throws RemoteException {
        Player p = server.decipher(token);
        if(p != null) {
            helper.action_enter_room(p, roomId, passWord);
        } 
    }

    public void handler_exit_room(String token)throws RemoteException {
        /*
        Player p = server.decipher(token);
        if(p != null) {
            helper.action_exit_room(p);
        }
        */ 
    }

    public void handler_display_rooms(String token)throws RemoteException {
        Player p = server.decipher(token);
        if(p != null) {
            helper.action_display_rooms(p);
        } 
    }

    public void handler_search_room(String token, String roomId, String passWord)throws RemoteException {
        Player p = server.decipher(token);
        System.out.println("HallController: search");
        if(p != null) {
            helper.action_search_room(p, roomId, passWord);
        } 
    }

    public void handler_match_room(String token)throws RemoteException {
    }

    
    public void update(Player p, HallEvent e) {
        //1 fail  2 enter 3 exit  4 display
        try {
            switch(e.whatHappen) {
                case 1:
                    System.out.println("HallController: fail");
                    p.hallGUI.update_message(e.failMsg);
                    break;
                case 2:
                    System.out.println("HallController: message");
                    p.hallGUI.update_message("进入房间成功...");
                    System.out.println(e.curRoomInfo.roomID +e.curRoomInfo.roomType);
                    p.hallGUI.update_switch_page(e.curRoomInfo.roomID, e.curRoomInfo.roomType);
                    break;
                case 3:
                    p.hallGUI.update_message("退出房间成功");
                    break;
                case 4:
                    System.out.println("HallController: display");
                    p.hallGUI.update_display_rooms(e.activeRoomInfos);
                    break;
            }

        }catch(RemoteException re) {
            re.printStackTrace();
        }

    }


}