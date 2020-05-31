import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class HallHelper {
    public Hall hall;
    public HallController controller;
    public TexasPlayerController playerController;
    

    public HallEvent action_create_room(Player player, RoomOption roomOption) {
        if(player.playing == true) {
            HallEvent m = new HallEvent();
            m.whatHappen = HallEvent.EVENT_FAILED;
            m.activeRoomInfos = null;
            //m.curRoomInfo = this.hall.getRoomInfo(room.RoomID);//the last one is the new one
            m.curRoomInfo = null;
            m.failMsg = "您正在游戏，请等待游戏结束！";
            return m;
        }

        Room room = new Room(roomOption);
        room.RoomID = Integer.toString(this.hall.maxRoomID + 1);
        this.hall.maxRoomID += 1;
        this.hall.rooms.add(room);//add this room to the hall
        
        System.out.println("加入玩家:" + player);
        room.addPlayer(player); //房间加入人
        if(roomOption.modeType == RoomOption.MODE_SINGLE) {
            System.out.println("创建单机房间!");
        	for(int i=1;i<roomOption.personType;i++) {
                Player p = new Player("机器人" + i, "", 10000);
                TexasAIController aiController = new TexasAIController(p, roomOption.difficultyType);
                p.aiController= aiController; //双向绑定
                room.addPlayer(p);
                room.playerReady(p);
                System.out.println("加入玩家:" + p);

            }

        }else{
            //dont need
        }
        
        if(roomOption.gameType == RoomOption.GAME_TEXAS) {
            TexasPokerTable table = new TexasPokerTable(roomOption.personType);
            TexasGameHelper texasGameHelper = new TexasGameHelper();
            texasGameHelper.set_room(room);
            texasGameHelper.table = table;
            texasGameHelper.playerController = playerController;
            room.texasGameHelper = texasGameHelper;
            //player.texasGameHelper = texasGameHelper;
            for(Player p: room.players) {
                if(p != null) {
                    p.texasGameHelper = texasGameHelper;
                }
            }
            texasGameHelper.action_room_change();
        }
        

        HallEvent m = new HallEvent();//Message print
        m.whatHappen = HallEvent.EVENT_ENTER_ROOM;
        m.activeRoomInfos = null;
        //m.curRoomInfo = this.hall.getRoomInfo(room.RoomID);//the last one is the new one
        m.curRoomInfo = this.hall.getRoomInfo(room);
        m.failMsg = "";
        //System.out.println("HallHelper:报告创建消息");
        //this.controller.update(player, m);

        player.hallHelper = this;
        return m;
    }

    public HallEvent action_enter_room(Player player, String roomID, String password) {
        if(player.playing == true) {
            HallEvent m = new HallEvent();
            m.whatHappen = HallEvent.EVENT_FAILED;
            m.activeRoomInfos = HallHelper.this.hall.allRoomInfo();
            //m.curRoomInfo = this.hall.getRoomInfo(room.RoomID);//the last one is the new one
            m.curRoomInfo = null;
            m.failMsg = "您正在游戏，请等待游戏结束！";
            return m;
        }
        System.out.println("HallHelper: roomID is:" + roomID + ", password is" + password);
        for(int i = 0; i < HallHelper.this.hall.rooms.size(); i++)
        {
            Room r = HallHelper.this.hall.rooms.get(i);
            if(r.RoomID.equals(roomID)&& !password.equals(r.password)) 
            {
                System.out.println("r.password is:" + r.password);
                HallEvent m = new HallEvent();
                m.activeRoomInfos = HallHelper.this.hall.allRoomInfo();
                m.whatHappen = 1;
                m.curRoomInfo = null;
                m.failMsg = "无法加入房间"+ roomID + ",密码错误";
                return m;
            } else if(r.RoomID.equals(roomID) && password.equals(r.password)) {
                HallEvent m = new HallEvent();
                boolean done = false;
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        m.activeRoomInfos = HallHelper.this.hall.allRoomInfo();
                        boolean addRes = r.addPlayer(player);
                        if(addRes) {
                            System.out.println("HallHelper: 加入成功");
                            player.texasGameHelper = r.texasGameHelper;
                            r.texasGameHelper.action_room_change();
                            m.curRoomInfo = HallHelper.this.hall.getRoomInfo(roomID);
                            m.failMsg = "";
                            m.whatHappen = 2;
                            System.out.println("HallHelper: message创建成功");
                            return;
                            
                        } else {
                            
                            m.curRoomInfo = HallHelper.this.hall.getRoomInfo(roomID);
                            m.failMsg = "无法加入房间 " + roomID+ ",房间已满";
                            m.whatHappen = 1;
                            return;
                        }
                    }
                }).start();
                while(m.whatHappen == 0) {System.out.println("loop");}
                System.out.println("HallHelper: 准备返回message");
                return m;

            }   
        } 
        HallEvent m = new HallEvent();
        m.activeRoomInfos = HallHelper.this.hall.allRoomInfo();
        m.whatHappen = 1;
        m.curRoomInfo = null;
        m.failMsg = "无法加入房间 "+ roomID + ",房间不存在";
        return m;
    }
 

    
    public HallEvent action_exit_room(Player p) {
        final Room room = p.texasGameHelper.room;
        String roomID = room.RoomID;
        new Thread(new Runnable(){
			@Override
			public void run() {
                room.removePlayer(p);

                if(room.option.modeType == RoomOption.MODE_ONLINE) {
                    System.out.println("HallHelper: playersInGame" + room.playersInGame());
                    if(room.playersInGame() == 0) {
                        hall.rooms.remove(room);
                    }
                } else {
                
                    hall.rooms.remove(room);
                    //p.texasGameHelper = null;
                }
			}
        }).start();
        

        
        HallEvent m = new HallEvent();
        m.whatHappen = 3;
        m.curRoomInfo = this.hall.getRoomInfo(roomID);
        //m.activeRoomInfos = this.hall.allRoomInfo();
        m.activeRoomInfos = null;
        m.failMsg = "";
        //this.controller.update(p, m);
        return m;
    }

    

    public HallEvent action_display_rooms(Player player) {//print all the rooms' information

        HallEvent m = new HallEvent();
        m.whatHappen = 4;
        m.activeRoomInfos = this.hall.allRoomInfo();
        //m.activeRoomInfos = get_test_info();  //care:因为测试环境没有现成的房间，我自己创建了一个房间列表
        m.curRoomInfo = null;
        m.failMsg = "";
        //this.controller.update(player, m);
        return m;
    }

    public HallEvent action_search_room(Player player, String roomID, String password) {
        System.out.println("HallHelper: search room");
        return this.action_enter_room(player, roomID, password);
    }

    public ArrayList<ActiveRoomInfo> get_test_info() {
        ActiveRoomInfo room1 = new ActiveRoomInfo("001", "德州扑克", 5, 7, "困难");
		ActiveRoomInfo room2 = new ActiveRoomInfo("002", "德州扑克", 2, 5, "容易");
		ActiveRoomInfo room3 = new ActiveRoomInfo("003", "德州扑克", 5, 5, "中等");
		ActiveRoomInfo room4 = new ActiveRoomInfo("001", "德州扑克", 5, 7, "困难");
		ActiveRoomInfo room5 = new ActiveRoomInfo("002", "德州扑克", 2, 5, "容易");
		ActiveRoomInfo room6 = new ActiveRoomInfo("003", "德州扑克", 5, 5, "中等");
		ArrayList<ActiveRoomInfo> rooms = new ArrayList<ActiveRoomInfo>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);
		rooms.add(room6);
		for (int i = 0; i < 15; ++ i){
			rooms.add(new ActiveRoomInfo(String.valueOf(i), "德州扑克", 5, 7, "中等"));

        }
        return rooms;
    }
}