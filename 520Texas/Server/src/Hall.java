import java.util.ArrayList;
public class Hall {//游戏大厅
    public ArrayList<Room> rooms;
    public int maxRoomID = 0;
    public Hall() {
        rooms = new ArrayList<Room>();
    }
    public ArrayList<ActiveRoomInfo> allRoomInfo()
    {

    	int l = this.rooms.size();
    	ArrayList<ActiveRoomInfo> infos = new ArrayList<ActiveRoomInfo>();
    	for(int i = 0; i < l; i++)
    	{
    		Room r = this.rooms.get(i);
            ActiveRoomInfo info = new ActiveRoomInfo();
            info.roomID = r.RoomID + "";
            info.tableSize = r.playersInGame();
            switch(r.option.personType)// the maximum number of people in a room
            {
                case 2: info.roomNum = 2;
                break;
                case 3: info.roomNum = 3;
                break;
                case 4: info.roomNum = 4;
                break;
                case 5: info.roomNum = 5;
                break;
                default: info.roomNum = 5;
           }
           switch(r.option.gameType)
           {
                case 1: info.roomType = "TEXAS";
                break;
                case 2: info.roomType = "OTHER";
                break;
                default: info.roomType = "TEXAS";
           }
           switch(r.option.levelType)
           {
                case 1: info.gameLevel = "Primary";
                break;
                case 2: info.gameLevel = "Medium";
                break;
                case 3: info.gameLevel = "Advance";
                break;
                default: info.gameLevel = "Primary";
           }
            infos.add(info);
    	}
    	return infos;
    }
    

    public ActiveRoomInfo getRoomInfo(Room r) {
          ActiveRoomInfo info = new ActiveRoomInfo();
          info.roomID = r.RoomID ;
          info.tableSize = r.playersInGame();
          switch(r.option.personType)// the maximum number of people in a room
          {
               case 2: info.roomNum = 2;
               break;
               case 3: info.roomNum = 3;
               break;
               case 4: info.roomNum = 4;
               break;
               case 5: info.roomNum = 5;
               break;
               default: info.roomNum = 5;
          }
          switch(r.option.gameType)
          {
               case 1: info.roomType = "德州扑克";
               break;
               case 2: info.roomType = "OTHER";
               break;
               default: info.roomType = "OTHER";
          }
          switch(r.option.levelType)
          {
               case 1: info.gameLevel = "Primary";
               break;
               case 2: info.gameLevel = "Medium";
               break;
               case 3: info.gameLevel = "Advance";
               break;
               default: info.gameLevel = "Primary";
          }
          return info;
    }



    public ActiveRoomInfo getRoomInfo(String roomID)
    {
      ActiveRoomInfo info = new ActiveRoomInfo();
      System.out.println("to find :" + roomID);    
    	 for(int i = 0; i < this.rooms.size(); i++)
    	 {
          Room r = this.rooms.get(i);
          System.out.println("this id：" + r.RoomID);
    		if(r.RoomID.equals(roomID))
    		{
               info.roomID = roomID ;
               info.tableSize = r.playersInGame();
               switch(r.option.personType)// the maximum number of people in a room
               {
                    case 2: info.roomNum = 2;
                    break;
                    case 3: info.roomNum = 3;
                    break;
                    case 4: info.roomNum = 4;
                    break;
                    case 5: info.roomNum = 5;
                    break;
                    default: info.roomNum = 5;
               }
               switch(r.option.gameType)
               {
                    case 1: info.roomType = "德州扑克";
                    break;
                    case 2: info.roomType = "OTHER";
                    break;
                    default: info.roomType = "OTHER";
               }
               switch(r.option.levelType)
               {
                    case 1: info.gameLevel = "Primary";
                    break;
                    case 2: info.gameLevel = "Medium";
                    break;
                    case 3: info.gameLevel = "Advance";
                    break;
                    default: info.gameLevel = "Primary";
               }
               break;
          }
     }
     return info;
    
     }
}

