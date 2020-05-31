import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class RoomMessage{

	public PlayerInRoom[] players; //当前玩家列表
	public int yourseat;//位置
	public boolean isAllReady;

	public  JSONObject toJSONObj() {
		JSONObject obj = new JSONObject();
		obj.put("yourseat", yourseat);
		obj.put("isAllReady", isAllReady);
		JSONArray arr = new JSONArray();
		for(int i=0;i<players.length;i++) {
			if(players[i] == null) {
				arr.add(i, null);
			} else {
				arr.add(i, players[i].toJSONObj());
			}
		}
		obj.put("players", arr);
		return obj;
		
	}

	public static RoomMessage json2RoomMessage(JSONObject obj) {
		RoomMessage msg = new RoomMessage();
		msg.yourseat = TexasHttpUtils.jint2int(obj.get("yourseat"));
		msg.isAllReady = (boolean)obj.get("isAllReady");
		JSONArray arr = (JSONArray) obj.get("players");
		msg.players = new PlayerInRoom[arr.size()];
		for(int i=0;i<arr.size();i++) {
			msg.players[i] = PlayerInRoom.JSON2PlayerInRoom((JSONObject)arr.get(i));
		}
		return msg;
	}
	
}

