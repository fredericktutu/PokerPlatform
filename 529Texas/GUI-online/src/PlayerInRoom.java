import org.json.simple.JSONObject;

public class PlayerInRoom{
	public String name;
	public boolean isReady;
	public int money;
	
	public PlayerInRoom(String name, boolean isReady, int money){
		this.name = name;
		this.isReady = isReady;
		this.money = money;
	}
	public PlayerInRoom() {}

	public JSONObject toJSONObj() {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("isReady", isReady);
		obj.put("money", money);
		return obj;
	}

	public static PlayerInRoom JSON2PlayerInRoom(JSONObject obj) {
		if(obj == null) return null;
		PlayerInRoom pir = new PlayerInRoom();
		pir.isReady = (boolean)obj.get("isReady");
		pir.name = (String) obj.get("name");
		pir.money = TexasHttpUtils.jint2int(obj.get("money"));
		return pir;
	}
}