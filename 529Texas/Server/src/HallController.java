
public class HallController {
    Server server;
    HallHelper helper;

    public HallController(Server server, HallHelper helper){
        this.server = server;
        this.helper = helper;
    }
    //句柄
    public HallEvent handler_create_room(String token, RoomOption option){
        System.out.println("HallController:正在创建房间");
        Player p = server.decipher(token);
        if(p != null) {
            return helper.action_create_room(p, option);
        } 
        return null;
    }

    public HallEvent handler_enter_room(String token, String roomId, String passWord){
        Player p = server.decipher(token);
        if(p != null) {
            return helper.action_enter_room(p, roomId, passWord);
        } 
        return null;
    }

    public HallEvent handler_exit_room(String token) {
        
        Player p = server.decipher(token);
        if(p != null) {
            return helper.action_exit_room(p);
        }
        return null;
        
    }

    public HallEvent handler_display_rooms(String token) {
        Player p = server.decipher(token);
        if(p != null) {
            return helper.action_display_rooms(p);
            
        } 
        return null;
    }

    public HallEvent handler_search_room(String token, String roomId, String passWord) {
        Player p = server.decipher(token);
        System.out.println("HallController: search");
        if(p != null) {
            return helper.action_search_room(p, roomId, passWord);
        } 
        return null;
    }

    public HallEvent handler_match_room(String token) {
        return null;
    }


    /*
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

        }catch(Exception re) {
            re.printStackTrace();
        }

    }
    */


}