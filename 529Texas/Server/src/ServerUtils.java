import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;



public class ServerUtils {
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static JSONObject ARI2Json(ActiveRoomInfo info) {
        JSONObject obj = new JSONObject();

        obj.put("roomId", info.roomID);
        obj.put("roomType", info.roomType);
        obj.put("roomNum", info.roomNum);
        obj.put("tableSize", info.tableSize);
        obj.put("gameLevel", info.gameLevel);
        return obj;
    }

    public static JSONArray ARIS2JsonArray(ArrayList<ActiveRoomInfo> infos) {
        JSONArray arr = new JSONArray();
        int i = 0;
        for(ActiveRoomInfo info : infos) {
            arr.add(i, ARI2Json(info));
        }
        return arr;
    }

    public static JSONObject hallEvent2Json(HallEvent e) {
        JSONObject obj = new JSONObject();
        obj.put("whatHappen", e.whatHappen);
        switch(e.whatHappen) {
            case 1:
                obj.put("failMsg", e.failMsg);
                break;
            case 2:
                obj.put("roomId", e.curRoomInfo.roomID);
                obj.put("roomType", e.curRoomInfo.roomType);
                break;
            case 3:
                break;
            case 4:
                obj.put("activeRoomInfos", ARIS2JsonArray(e.activeRoomInfos));
                break;
        }
        return obj;
    }

    public static  int jint2int(Object jint) {
        Long t = (Long) jint;
        return t.intValue();
    }

    public static JSONArray arrayListCard2JSONAraay(ArrayList<Card> list) {
        if(list == null) return null;
        JSONArray arr = new JSONArray();
        for(int i=0;i<list.size();i++) {
            arr.add(i, list.get(i).toJSONObj());
        }
        return arr;
        
    }

    public static JSONArray arrayListInteger2JSONArray(ArrayList<Integer> list) {
        if(list == null) return null;
        JSONArray arr = new JSONArray();
        for(int i=0;i<list.size();i++) {
            arr.add(i, list.get(i).intValue());
        }
        return arr;
    }

    public static JSONArray arrayListString2JSONArray(ArrayList<String> list) {
        if(list == null) return null;
        JSONArray arr = new JSONArray();
        for(int i=0;i<list.size();i++) {
            arr.add(i, list.get(i));
        }
        return arr;
    }

    public static JSONArray playerPrivateCards2JSONArray(ArrayList<ArrayList<Card>> list) {
        if(list == null) return null;
        JSONArray arr = new JSONArray();
        ArrayList<Card> list2;
        for(int i=0;i<list.size();i++) {
            list2 = list.get(i);
            JSONArray arr2 = arrayListCard2JSONAraay(list2);
            arr.add(i, arr2);
        }

        return arr;

    }




    


}