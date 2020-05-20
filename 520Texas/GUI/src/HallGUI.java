import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class HallGUI extends UnicastRemoteObject implements IHallGUI {
	HallUI ui;
    IServer server;
    IHallController hallController;
    public HallGUI(String token, HallUI ui) throws RemoteException {
		this.ui = ui;
		try{  
			//rmi ����server
			String server_path = "//localhost:12200/Server";
			this.server  = (IServer)Naming.lookup(server_path);

			//rmi ����hallController
			String hallController_path = "//localhost:12200/HallController";
			this.hallController = (IHallController)Naming.lookup(hallController_path);
			System.out.println("�����ӷ�����rmi");
		} catch(Exception es) {
			es.printStackTrace();
		}
		
		try {
			LocateRegistry.createRegistry(12211);
			String rmi_address = "//localhost:12211/HallUI";
			Naming.bind(rmi_address, this);

			if(server.service_bind_hall(token, rmi_address)) System.out.println("�������Ѱ�ͼ�ν���rmi");
		} catch(Exception er) {
			er.printStackTrace();
		}

    }


    public void update_message(String s) throws RemoteException{
		System.out.println("HAllGUI:������Ϣ");
        ui.update_message(s);
    }

    public void update_switch_page(String roomId, String roomType)throws RemoteException{
        ui.update_switch_page(roomId, roomType);
    }

    public void update_display_rooms(ArrayList<ActiveRoomInfo> rooms) throws RemoteException {
		System.out.println("HallGUI:չʾ����");
		ui.update_display_rooms(rooms);
    }
}