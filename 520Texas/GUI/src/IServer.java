import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    //ע�����
    boolean service_register(String id, String name, String passWord) throws RemoteException;
    
    //��½����
    String service_login(String id, String passWord) throws RemoteException;
    
    //��������
    //�������������ʹ�������˵��û�֪���Լ���rmi��ַ���Ӷ�Զ�̸���ͼ�ν���
    boolean service_bind_hall(String token, String rmi_address) throws RemoteException;
    boolean service_bind_texas(String token, String rmi_address) throws RemoteException;
}