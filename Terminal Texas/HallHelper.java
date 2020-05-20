
public class HallHelper { //��������
    public Hall hall;
    public HallHelper(Hall hall) {
        this.hall = hall;
    }
    
    protected TexasGameHelper assignTexasGameHelper(Room room) 
    {
        TexasGameHelper helper = new TexasGameHelper(room);
        
        return helper;
    }

    public void createRoom(Player creator) { //��������
        Room room = new Room("happydog", 5);//���踳��������roomID������ 
        room.addPlayer(creator);
        creator.update(creator.name + " successfully created room" + room.roomID + "(" + room.roomType + ")!");
        TexasGameHelper helper = assignTexasGameHelper(room);
        creator.setGameHelper(helper);//����ҷ�����Ϸ����     
        //����������robot
        boolean notFull = true;
        int i = 1;
        while(notFull)
        {
            Player robot = new Player(this);//����Player��Ҫ��ʼ��!!!
            robot.name = "robot" + i;
            i ++;
            robot.setGameHelper(helper);
            robot.setController(new TexasAIController(robot, helper));
            notFull = room.addPlayer(robot);
            if(notFull == true) {
                creator.update(robot.name + " successfully entered room " + room.roomID + "(" + room.roomType + ")!");
            }
        }
        //System.out.println("������:������������"+ room.players.size());
        hall.rooms.add(room);
        helper.maxPlayerNumber = room.players.size();
        helper.start_game();
    }

    public void exitRoom(Player p) { //�˳�����
        Room room = p.gameHelper.room;
        room.removePlayer(p);
        p.update(p.name + "left the room" + room.roomID + "!");
        //����ģʽ��������robotҲɾ��
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