import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class TestServer {
    public static void main(String args[]) {
        try {
            String path = "//localhost:12200/Server";
            IServer s  = (IServer)Naming.lookup(path);
    
            System.out.println("going to register, the result is:");
            System.out.println(s.service_register("1036715079", "zbw", "zbw"));
            
            System.out.println("going to login, the token is:");
            System.out.println(s.service_login("1036715079", "zbw"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}