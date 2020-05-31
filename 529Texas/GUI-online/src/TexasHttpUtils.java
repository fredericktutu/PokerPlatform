import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;



public class TexasHttpUtils {
    public static JSONParser parser = new JSONParser();

    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {

        StringBuffer buffer = null;
        try {
            final URL url = new URL(requestUrl);

            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            
            // 往服务器端写内容 也就是发起http请求需要带的参数
            if (null != outputStr) {
                final OutputStream os = conn.getOutputStream();
                //os.write(outputStr.getBytes("utf-8"));
                os.write(outputStr.getBytes("GBK"));
                os.close();
            }
            //Thread.sleep(3000);
            // 读取服务器端返回的内容
            final InputStream is = conn.getInputStream();

            //final InputStreamReader isr = new InputStreamReader(is, "utf-8");
            final InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            final BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            //System.out.println(buffer.toString());
            return buffer.toString();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "";
       
    }



    public static String request(String ipaddr, String controller, String action, String param) {
        ipaddr = "101.201.197.43:8888";
        String method = "POST";
        if(action.equals("display") || action.equals("connect")) {
            method = "GET";
        }
        
        
        String url;
        url = "http://" + ipaddr +  "/" + controller + "/"+ action;
        
        return httpRequest(url, method, param);
    }


    public static void updateByJEvent(String json, HallUI ui) {
        try {
            JSONObject obj = (JSONObject)parser.parse(json);
            int whatHappen = jint2int(obj.get("whatHappen"));
            switch(whatHappen) {
                case 1:
                    String failMsg = (String) obj.get("failMsg");
                    ui.update_message(failMsg);
                    break;
                case 2:
                    String roomId = (String)obj.get("roomId");
                    String roomType = (String)obj.get("roomType");
                    ui.update_message("进入房间成功");
                    ui.update_switch_page(roomId, roomType);
                    break;
                case 3:
                    ui.update_message("退出房间成功");
                    break;
                case 4:
                    ArrayList<ActiveRoomInfo> infos = jarr2ARIS((JSONArray)obj.get("activeRoomInfos"));
                    ui.update_display_rooms(infos);
    
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static  int jint2int(Object jint) {
        Long t = (Long) jint;
        return t.intValue();
    }


    public static  ArrayList<ActiveRoomInfo> jarr2ARIS(JSONArray arr) {
        ArrayList<ActiveRoomInfo> infos = new ArrayList<ActiveRoomInfo>();
        for(int i=0;i<arr.size();i++) {
            infos.add(json2ARI((JSONObject)arr.get(i)));
        }
        return infos;
    }

    public static ActiveRoomInfo json2ARI(JSONObject obj) {
        String roomID = (String)obj.get("roomId");
        String roomType = (String)obj.get("roomType");
        int roomNum = jint2int(obj.get("roomNum"));
        int tableSize =jint2int(obj.get("tableSize"));
        String gameLevel = (String)obj.get("gameLevel");
        
        ActiveRoomInfo info = new ActiveRoomInfo();
        info.roomID = roomID;
        info.roomType = roomType;
        info.roomNum = roomNum;
        info.tableSize =tableSize;
        info.gameLevel = gameLevel;

        return info;  
    }

    public static void main( String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("username", "zbw");
        obj.put("id", "1234");
        obj.put("password", "123");
        System.out.println(request("localhost:8888", "server", "register", obj.toString()));
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


    public static ArrayList<ArrayList<Card>> JSONArray2PlayerPrivateCards(JSONArray arr) {
        if(arr ==null)return null;
        JSONArray arr2;
        ArrayList<ArrayList<Card>> res = new ArrayList<ArrayList<Card>>();
        for(int i=0;i<arr.size();i++) {
            arr2 = (JSONArray)arr.get(i);
            res.add(JSONArray2ArrayListCard(arr2));
        }
        return res;
    }

    public static ArrayList<Card> JSONArray2ArrayListCard(JSONArray arr) {
        if(arr == null) return null;
        ArrayList<Card> list = new ArrayList<Card>();
        for(int i=0;i<arr.size();i++) {
            Card c = Card.json2Card((JSONObject) arr.get(i));
            list.add(c);
        }

        return list;
    }

    public static ArrayList<Integer> JSONArray2ArrayListInteger(JSONArray arr) {
        if(arr == null) return null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<arr.size();i++) {
            Integer in = Integer.valueOf(TexasHttpUtils.jint2int(arr.get(i)));
            list.add(in);
        }
        return list;
    }

    public static ArrayList<String> JSONArray2ArrayListString(JSONArray arr) {
        if(arr == null) return null;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<arr.size();i++) {
            String s = (String)arr.get(i);
            list.add(s);
        }
        return list;
    }

}