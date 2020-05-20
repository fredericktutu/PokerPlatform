
public class HallHelper { //大厅助理
    public Hall hall;
    public HallHelper(Hall hall) {
        this.hall = hall;
    }
    
    protected TexasGameHelper assignTexasGameHelper(Room room) 
    {
        TexasGameHelper helper = new TexasGameHelper(room);
        
        return helper;
    }

    public void createRoom(Player creator) { //创建房间
        Room room = new Room("happydog", 5);//假设赋予这样的roomID和类型 
        room.addPlayer(creator);
        creator.update(creator.name + " successfully created room" + room.roomID + "(" + room.roomType + ")!");
        TexasGameHelper helper = assignTexasGameHelper(room);
        creator.setGameHelper(helper);//给玩家分配游戏助理     
        //给房间添满robot
        boolean notFull = true;
        int i = 1;
        while(notFull)
        {
            Player robot = new Player(this);//这里Player需要初始化!!!
            robot.name = "robot" + i;
            i ++;
            robot.setGameHelper(helper);
            robot.setController(new TexasAIController(robot, helper));
            notFull = room.addPlayer(robot);
            if(notFull == true) {
                creator.update(robot.name + " successfully entered room " + room.roomID + "(" + room.roomType + ")!");
            }
        }
        //System.out.println("服务器:牌桌的人数是"+ room.players.size());
        hall.rooms.add(room);
        helper.maxPlayerNumber = room.players.size();
        helper.start_game();
    }

    public void exitRoom(Player p) { //退出房间
        Room room = p.gameHelper.room;
        room.removePlayer(p);
        p.update(p.name + "left the room" + room.roomID + "!");
        //单人模式，将所有robot也删除
        while(room.players.size() != 0)
        {
        	int last = room.players.size()-1;
        	Player robot = room.players.get(last);
        	room.players.remove(last);
            p.update(robot.name + "left the room" + room.roomID + "!");
            robot = null;
        }
        hall.rooms.remove(room);
        room = null;
        p.gameHelper = null;
    }

    public void servePlayer(Player p) {
        p.update(true);
    }
}