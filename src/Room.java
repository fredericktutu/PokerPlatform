import java.util.ArrayList;

public class Room { //房间类
	public String roomID;//房间号（not used yet）
	public String roomPassport;//房间密码（not used yet）
	public enum Type{FIVE_PERSON, EIGHT_PERSON};//房间类型(not used yet)
	public Type roomType;
    public ArrayList<Player> players; //玩家列表
    public TexasPokerTable table;
    
    public Room(String roomID, int roomType)//Room构造器
    {
        this.roomID = roomID;
        players = new ArrayList();
        if (roomType == 5) {
            this.roomType = Type.FIVE_PERSON;
            this.table = new TexasPokerTable(5);
        }
    	else {
            this.roomType = Type.EIGHT_PERSON;
            this.table = new TexasPokerTable(8);
        }
    		
    }
    
    public boolean addPlayer(Player p)
    {
        int maxnum;
        if(this.roomType == Type.FIVE_PERSON) maxnum = 5;
        else maxnum = 8;
        //System.out.println("服务器:房间人数" + players.size());
        if(this.players.size()>= maxnum)
        {
        	p.update("The room is full！");
        	return false;
        }else
        {
        	players.add(p);
        	return true;
        }
    }
    
    public void removePlayer(Player p)
    {
    	this.players.remove(p);
    }
    
}