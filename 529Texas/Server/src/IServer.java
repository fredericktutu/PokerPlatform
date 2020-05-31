import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    //注册界面
    boolean service_register(String id, String name, String passWord) throws RemoteException;
    
    //登陆界面
    String service_login(String id, String passWord) throws RemoteException;
    
    //大厅界面
    //调用这个方法，使服务器端的用户知道自己的rmi地址，从而远程更新图形界面
    boolean service_bind_hall(String token, String rmi_address) throws RemoteException;
    boolean service_bind_texas(String token, String rmi_address) throws RemoteException;
}