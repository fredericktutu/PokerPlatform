import java.rmi.server.*;
import java.io.*;
import java.net.*;


public class TexasSocket extends RMISocketFactory {
    public  Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public ServerSocket createServerSocket(int port) throws IOException {
        if(port==0)port = 12250;
    
        System.out.println("Socket:RMI服务器的注册与数据传输端口 ="+port);
        return new ServerSocket(port);
      }
}