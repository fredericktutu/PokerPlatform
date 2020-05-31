
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;





public class MyHttpService {


    Server s;
    public MyHttpService(Server s) {

        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8888), 0);
            this.s = s;
            //创建http服务器，绑定本地8888端口*
            
            httpServer.createContext("/server/register", new ServerHttpHandler("register", s));
            httpServer.createContext("/server/login", new ServerHttpHandler("login",s));
            httpServer.createContext("/server/exit", new ServerHttpHandler("exit",s));
            
            httpServer.createContext("/hall/create", new HallHttpHandler("create",s));
            httpServer.createContext("/hall/enter", new HallHttpHandler("enter",s));
            httpServer.createContext("/hall/exit", new HallHttpHandler("exit",s));
            httpServer.createContext("/hall/display", new HallHttpHandler("display",s));
            httpServer.createContext("/hall/search", new HallHttpHandler("search",s));
            
            httpServer.createContext("/texas/add", new TexasHttpHandler("add", s));
            httpServer.createContext("/texas/follow", new TexasHttpHandler("follow", s));
            httpServer.createContext("/texas/check", new TexasHttpHandler("check", s));
            httpServer.createContext("/texas/abort", new TexasHttpHandler("abort", s));
            httpServer.createContext("/texas/ready", new TexasHttpHandler("ready", s));
            httpServer.createContext("/texas/croom", new TexasHttpHandler("croom", s));
            httpServer.createContext("/texas/cgame", new TexasHttpHandler("cgame", s));

            httpServer.start();
            System.out.println("http service start");
        }catch(Exception e) {
            e.printStackTrace();
        }

    }



}




