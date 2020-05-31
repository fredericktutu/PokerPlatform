import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


public class Card{
	public int suit; //»¨É«
	public int face; //ÅÆÃæ
	
	public Card(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}

	public Card() {
	}
	
	public void assginCard(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}

	public JSONObject toJSONObj() {
		JSONObject obj = new JSONObject();
		obj.put("suit", suit);
		obj.put("face", face);
		return obj;
	}

	public static Card json2Card(JSONObject obj) {
		Card c = new Card();
		c.face =  ServerUtils.jint2int(obj.get("face"));
		c.suit = ServerUtils.jint2int(obj.get("suit"));

		return c;
	}
}
