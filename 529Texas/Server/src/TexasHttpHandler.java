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

public class TexasHttpHandler implements HttpHandler{
    public String action;
    Server server;
    JSONParser parser=new JSONParser();
    //public Server server;
    public TexasHttpHandler(String action, Server s) {
        super();
        this.server = s;
        this.action = action;
    }


    public void handle_add(HttpExchange exchange) throws IOException {
        String response = "texas/add";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            int addTo = ServerUtils.jint2int(obj.get("addto"));
            server.texasPlayerController.handler_add_bet(token, addTo);
            
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
    }
    public void handle_follow(HttpExchange exchange) throws IOException {
        String response = "texas/follow";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            int followTo = ServerUtils.jint2int(obj.get("followto"));
            server.texasPlayerController.handler_follow_bet(token, followTo);
            
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
    }
    public void handle_check(HttpExchange exchange) throws IOException {
        String response = "texas/check";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");

            server.texasPlayerController.handler_check(token);
            
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
    }
    public void handle_abort(HttpExchange exchange) throws IOException {
        System.out.println("abort");
        String response = "texas/abort";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            server.texasPlayerController.handler_abort_bet(token);
            System.out.println("TexasHttpHandler: ready to answer");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
    }
    public void handle_ready(HttpExchange exchange) throws IOException {
        String response = "texas/ready";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            server.texasPlayerController.handler_ready(token);
            
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
    }

    public void handle_croom(HttpExchange exchange) throws IOException {
        String response = "texas/croom";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            RoomMessage msg = server.texasPlayerController.handler_croom(token);
            
            JSONObject msgobj = msg.toJSONObj();
            response = msgobj.toString();
            //System.out.println(response);

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handle_cgame(HttpExchange exchange) throws IOException {
        String response = "texas/cgame";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            TexasEnvelope te = server.texasPlayerController.handler_cgame(token);
            response = te.toJSONObj().toString();
            //System.out.println(response);
            
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch(action) {
            case "add":
                handle_add(exchange);
                break;
            case "follow":
                handle_follow(exchange);
                break;
            case "check" :
                handle_check(exchange);
                break;
            case "abort":
                handle_abort(exchange);
                break;
            case "ready":
                handle_ready(exchange);
                break;
            case "croom":
                handle_croom(exchange);
                break;
            case "cgame":
                handle_cgame(exchange);
                break;
        }


    }
}