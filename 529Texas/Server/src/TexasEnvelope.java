import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class TexasEnvelope {
    ArrayList<String> logs;
    ArrayList<Card> publicCards;
    ArrayList<Card> myPrivateCards;
    public int mySeat;
    int myPlayerChip;
    int myPlayerAsset;
    
    boolean end;

    boolean ready;

    boolean add;
    boolean follow;
    boolean check;
    boolean abort;

    int addMin;
    int addMax;
    int followTo;

    ArrayList<Integer> playerMoney;
    ArrayList<Integer> playerChip;
    ArrayList<ArrayList<Card>> playerPrivateCards;

    ArrayList<Integer> moneyChange;
    
    public TexasEnvelope() {
        end = false;
        add = false;
        follow = false;
        check = false;
        abort = false;
        ready = false;
        addMin = 0;
        addMax = 0;
        followTo = 0;
    }

    public JSONObject toJSONObj() {
        JSONObject obj = new JSONObject();
        obj.put("myPlayerChip", myPlayerChip);
        obj.put("myPlayerAsset", myPlayerAsset);
        obj.put("end", end);
        obj.put("ready", ready);
        obj.put("add", add);
        obj.put("follow", follow);
        obj.put("check", check);
        obj.put("abort", abort);
        obj.put("addMin", addMin);
        obj.put("addMax", addMax);
        obj.put("followTo", followTo);
        obj.put("mySeat", mySeat);

        obj.put("logs", ServerUtils.arrayListString2JSONArray(logs));
        obj.put("publicCards", ServerUtils.arrayListCard2JSONAraay(publicCards));
        obj.put("myPrivateCards", ServerUtils.arrayListCard2JSONAraay(myPrivateCards));
        obj.put("playerPrivateCards", ServerUtils.playerPrivateCards2JSONArray(playerPrivateCards));
        obj.put("playerMoney", ServerUtils.arrayListInteger2JSONArray(playerMoney));
        obj.put("playerChip", ServerUtils.arrayListInteger2JSONArray(playerChip));
        obj.put("moneyChange", ServerUtils.arrayListInteger2JSONArray(moneyChange));

        return obj;
    }





}


