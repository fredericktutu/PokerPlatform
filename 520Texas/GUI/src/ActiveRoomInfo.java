import java.io.Serializable;

public class ActiveRoomInfo implements Serializable{
	public String roomID;
	public String roomType;
	public int roomNum;
	public int tableSize;
	public String gameLevel;
	public ActiveRoomInfo() {}

	public ActiveRoomInfo(String roomID,  String roomType, int roomNum, int tableSize, String gameLevel ) {
		// TODO Auto-generated constructor stub
		this.roomID = roomID;
		this.roomType = roomType;
		this.roomNum  = roomNum;
		this.tableSize = tableSize;
		this.gameLevel = gameLevel;
		
	}

}
