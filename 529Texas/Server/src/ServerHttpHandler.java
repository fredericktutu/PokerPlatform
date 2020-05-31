import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;  
import com.sun.net.httpserver.HttpContext;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
public class ServerHttpHandler implements HttpHandler{
    public String action;
    Server server;
    JSONParser parser=new JSONParser();
    //public Server server;
    public ServerHttpHandler(String action, Server s) {
        super();
        this.server = s;
        this.action = action;
    }
    public void handle_register(HttpExchange exchange)throws IOException {
        String response = "server/register";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String username = (String)obj.get("username");
            String id = (String)obj.get("id");
            String password = (String)obj.get("password");

            boolean res = server.service_register(id, username, password);
            response = res + "";

            
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            ;
        }
        

    }

    public void handle_login(HttpExchange exchange) throws IOException {
        //System.out.println("into service login");
        String response = "server/login";
        InputStream is = exchange.getRequestBody();
        
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        //System.out.println(s);
        try {
            //System.out.println("parsing");
            JSONObject obj = (JSONObject)parser.parse(s);
            String id = (String)obj.get("id");
            String password = (String)obj.get("password");
            ArrayList<String>res = server.service_login(id, password);
            String token = res.get(0);
            if(token.equals("")) {
                response = "";
            } else if(token.equals("!")){
                response = "!";
            } else {
                response = token + "&" + res.get(1);
            }

            //response = token;
            //System.out.println(token);
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handle_exit(HttpExchange exchange) throws IOException {
        String response = "server/exit";
        InputStream is = exchange.getRequestBody();
        byte[] getData = ServerUtils.readInputStream(is);
        is.read(getData);
        String s = new String(getData);
        
        try {
            JSONObject obj = (JSONObject)parser.parse(s);
            String token = (String) obj.get("token");
            server.service_exit(token);

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }catch(ParseException e) {
            ;
        }


    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(action.equals("register")) {
            handle_register(exchange);
        } else if(action.equals("login")){
            handle_login(exchange);
        } else {
            handle_exit(exchange);
        }


    }
}