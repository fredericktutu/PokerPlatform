import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class TexasEnvelope {
    ArrayList<String> logs;
    ArrayList<Card> publicCards;
    ArrayList<Card> myPrivateCards;
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

    int mySeat;

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




    public static TexasEnvelope json2TexasEnvelope(JSONObject obj) {
        TexasEnvelope te = new TexasEnvelope();
        te.myPlayerChip = TexasHttpUtils.jint2int(obj.get("myPlayerChip"));
        te.myPlayerAsset = TexasHttpUtils.jint2int(obj.get("myPlayerAsset"));
        te.end = (boolean) obj.get("end");
        te.ready = (boolean) obj.get("ready");
        te.add = (boolean) obj.get("add");
        te.follow = (boolean) obj.get("follow");
        te.check = (boolean) obj.get("check");
        te.abort = (boolean) obj.get("abort");
        te.addMin = TexasHttpUtils.jint2int(obj.get("addMin"));
        te.addMax = TexasHttpUtils.jint2int(obj.get("addMax"));
        te.followTo = TexasHttpUtils.jint2int(obj.get("followTo"));
        te.mySeat = TexasHttpUtils.jint2int(obj.get("mySeat"));

        te.logs = TexasHttpUtils.JSONArray2ArrayListString((JSONArray)obj.get("logs"));
        te.publicCards = TexasHttpUtils.JSONArray2ArrayListCard((JSONArray)obj.get("publicCards"));
        te.myPrivateCards = TexasHttpUtils.JSONArray2ArrayListCard((JSONArray)obj.get("myPrivateCards"));
        te.playerPrivateCards = TexasHttpUtils.JSONArray2PlayerPrivateCards((JSONArray)obj.get("playerPrivateCards"));
        te.playerMoney = TexasHttpUtils.JSONArray2ArrayListInteger((JSONArray)obj.get("playerMoney"));
        te.playerChip = TexasHttpUtils.JSONArray2ArrayListInteger((JSONArray)obj.get("playerChip"));
        te.moneyChange = TexasHttpUtils.JSONArray2ArrayListInteger((JSONArray)obj.get("moneyChange"));
        return te;
    }


}


