import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;



import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;  
import com.sun.net.httpserver.HttpContext;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

public class HallHttpHandler implements HttpHandler{
    public String action;
    Server server;
    JSONParser parser=new JSONParser();



    public HallHttpHandler(String action, Server s) throws IOException{
        super();
        this.action = action;
        this.server = s;
    }
    public void handle_create(HttpExchange exchange)throws IOException{
        String response = "hall/create";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String)obj.get("token");
            JSONObject opt = (JSONObject)obj.get("option");
            
            String passWord = (String)opt.get("passWord");

            int gameType = ServerUtils.jint2int(opt.get("gameType"));
            int levelType =  ServerUtils.jint2int(opt.get("levelType"));
            int personType =  ServerUtils.jint2int(opt.get("personType"));
            int modeType =  ServerUtils.jint2int(opt.get("modeType"));
            int difType =  ServerUtils.jint2int(opt.get("difType"));

            RoomOption option = new RoomOption();
            option.personType= personType; 
            option.roomPassport = passWord; 
            option.gameType = gameType; 
            option.levelType = levelType;
            option.difficultyType = difType;
            option.modeType = modeType;

            HallEvent e = server.hallController.handler_create_room(token, option); 
            JSONObject res =  ServerUtils.hallEvent2Json(e);
            response = res.toString();

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            //os.write(response.getBytes("UTF-8"));
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handle_enter(HttpExchange exchange) throws IOException{
        String response = "hall/enter";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String)obj.get("token");
            String roomId = (String)obj.get("roomId");
            String password = (String)obj.get("password");
            System.out.println("HallHttpHandler:roomId is " + roomId);
            HallEvent e = server.hallController.handler_enter_room(token, roomId, password); 
            JSONObject res =  ServerUtils.hallEvent2Json(e);
            response = res.toString();

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            //os.write(response.getBytes("UTF-8"));
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handle_exit(HttpExchange exchange) throws IOException{
        String response = "hall/exit";

        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String)obj.get("token");
            
            HallEvent e = server.hallController.handler_exit_room(token); 
            JSONObject res =  ServerUtils.hallEvent2Json(e);
            response = res.toString();

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            //os.write(response.getBytes("UTF-8"));
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handle_search(HttpExchange exchange)throws IOException {
        handle_enter(exchange);
    }

    public void handle_display(HttpExchange exchange)throws IOException {
        String response = "hall/display";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String)obj.get("token");
            
            HallEvent e = server.hallController.handler_display_rooms(token); 
            JSONObject res =  ServerUtils.hallEvent2Json(e);
            response = res.toString();

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            //os.write(response.getBytes("UTF-8"));
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handle(HttpExchange exchange)throws IOException {
        switch(action) {
            case "create":
                handle_create(exchange);
                break;
            case "enter":
                handle_enter(exchange);
                break;
            case "exit":
                handle_exit(exchange);
                break;
            case "search":
                handle_search(exchange);
                break;
            case "display":
                handle_display(exchange);
                break;
        }
    }
}