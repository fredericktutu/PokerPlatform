import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;

public class Server extends UnicastRemoteObject implements IServer{
    HallHelper hallHelper;
    Hall hall;
    TexasPlayerController texasPlayerController;
    HallController hallController;

    public Server() throws RemoteException{
        activePlayers = new HashMap<String, Player>();
    }

    //hash table
    public Map<String, Player> activePlayers;

    public String encipher(String id, String name, int money)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//configure the date fromat
        String key = df.format(new Date())+name;
        Player p = new Player(name, id, money);
        activePlayers.put(key, p);
        return key;
    }

    public Player decipher(String token)
    {
        Player p = activePlayers.get(token);
        return p; 
    }

    /*
        �󶨴����������
        ���û���½�󣬽������������棬�������潫ͨ��rmi���ô˷���
        token�����û�����ݣ�rmi_address������������Լ���rmi��ַ
        �ڸ÷����У������ҵ���Ӧ���û���ͬʱע��һ��Զ�̵Ĵ������棬�������û�����
        ����һ�����û��Ϳ����ҵ��Լ���Զ�̴������棬���и��²���
    */
    public boolean service_bind_hall(String token, String rmi_address)throws RemoteException {
        System.out.println("���������󶨴�������rmi");
        Player p = decipher(token);  //ͨ��token�ҵ�player
        if(p != null) { //player����
            try {
                IHallGUI hallGUI = (IHallGUI)Naming.lookup(rmi_address);  //�����ҵ����������rmi
                p.hallGUI = hallGUI;
                
            }catch(Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean service_bind_texas(String token, String rmi_address) throws RemoteException {
        System.out.println("���������󶨵����˿�ͼ�ν���rmi");
        Player p = decipher(token);  //ͨ��token�ҵ�player

        if(p != null) { //player����
            try {
                ITexasGameGUI texasGameGUI = (ITexasGameGUI)Naming.lookup(rmi_address);  //�����ҵ���Ϸ�����rmi
                p.texasGameGUI = texasGameGUI; //����Ϸ����
                if(p.texasGameHelper.room.option.modeType == RoomOption.MODE_SINGLE) {
                    System.out.println("�޸ķ���");
                    System.out.println("myseat: "+ p.texasGameHelper.room.get_player_index(p) + "cap: "+ p.texasGameHelper.room.option.personType);
                    p.texasGameGUI.updatePlayerPanels(p.texasGameHelper.room.get_player_index(p), p.texasGameHelper.room.option.personType);
                    p.texasGameHelper.action_room_change();
                } else {
                    p.texasGameHelper.action_room_change();
                }                
            }catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("���������󶨵����˿�ͼ�ν���rmi�ɹ�");
            return true;
        }
        return false;
    }



    //User creates an account
    public boolean service_register(String id, String name, String passWord) throws RemoteException{ 
        //Check if the id is unique or not
        Statement stmt = null;
        Connection c = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE ID = '" + id +"';");
            if(!rs.next())
            {
                stmt.close();
                String sql = "INSERT INTO PLAYERS (ID, NAME, PASSWORD, MONEY, WIN, LOSE)" +
                             "VALUES (?, ?, ?, 0, 0, 0);";//Insert Java variable to the table
                PreparedStatement stmt0 = c.prepareStatement(sql);
                stmt0.setString(1, id);
                stmt0.setString(2, name);
                stmt0.setString(3, passWord);
                stmt0.executeUpdate();
                stmt.close();
                c.commit();
                c.close();
                System.out.println("Account created successfully!");
                return true;
            }
            else
            {
                System.out.println("This ID already existed! Please try another one.");
                return false;
            } 
        }catch(Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }
    
    public String service_login(String id, String passWord) throws RemoteException{
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE ID = '" + id +"';");
            if(!rs.next())//If the id is not found in data base
            {
                System.out.println("This account does not exist!");
                stmt.close();
                c.close();
                return "";
            }
            else
            {
                String name = rs.getString("NAME");
                String passwd = rs.getString("PASSWORD");
                int money = rs.getInt("MONEY");
                int win = rs.getInt("WIN");
                int lose = rs.getInt("LOSE");
                stmt.close();
                c.close();
                if(passwd.equals(passWord))
                {
                    System.out.println(name + " " + money + " " + win + " " + lose);
                    return encipher(id, name, money);//return what ???
                }
            }
        }catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return "";
    }

    //Update user's money after each game
    public boolean service_update_money(String id, int money) { 
        Connection c = null;
        PreparedStatement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);
            String sql = "UPDATE PLAYERS SET MONEY = (?) WHERE ID = (?);";//???
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, money);
            stmt.setString(2, id);
            stmt.executeUpdate();
            stmt.close();
            c.commit();
            c.close();
            System.out.println("Money updated successfully!");
            return  true;
        }catch(Exception e)
        {
            System.out.println("This ID is invalid!");
        }
        return false;
    }



    //Update user's wins and loses when he or she exit the room
    public boolean service_update_record(String id, boolean win) { 
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
//???
            ResultSet rs = stmt.executeQuery("SELECT WIN, LOSE FROM PLAYERS WHERE ID = '" + id +"';");
            System.out.println("flag2");
            int w = rs.getInt("WIN");
            int l = rs.getInt("LOSE");
            System.out.println(w+" "+l);
            if(win)
                w = w + 1;
            else
                l = l + 1;
            System.out.println(w+" "+l);
            stmt.close();
            String sql = "UPDATE PLAYERS SET WIN = (?), LOSE = (?) WHERE ID = " + id +";";
            PreparedStatement stmt0 = c.prepareStatement(sql);
            stmt0.setInt(1, w);
            stmt0.setInt(2, l);
            stmt0.executeUpdate();
            stmt0.close();
            c.commit();
            c.close();
            System.out.println("Wins&Loses updated successfully!");
            return true;
        }catch(Exception e)
        {
            System.out.println("This ID is invalid!");
        }
        return false;
    }





    public void service_deploy() {//initialize the data base when server start to work
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Opened database successfully");  
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PLAYERS" +
                         "(ID CHAR(15) PRIMARY KEY    NOT NULL," +
                         " NAME           TEXT   NOT NULL," +
                         " PASSWORD       CHAR(15) NOT NULL," +
                         " MONEY          INT    NOT NULL," +
                         " WIN            INT," +
                         " LOSE           INT)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Players table initialized successfully!");
        //end database deploy

        this.hall = new Hall();
        this.hallHelper = new HallHelper();
        hallHelper.hall = hall;
        

        
    }

    public void rmi_open(){
        try{
            //hallController rmi

            
            String local_ip = "localhost";
            String reg_port = "12200";
            String data_port = "12250";
            /*
            try {
                local_ip = InetAddress.getLocalHost().getHostAddress();
                System.out.println("��������˽��IP��ַ��:"+ local_ip);
            }catch(Exception ukhe){
                ukhe.printStackTrace();
            }
            */
            
            /*
            if (null == System.getSecurityManager()) {
                System.setSecurityManager(new RMISecurityManager());
            }
            */
            System.out.println("java.rmi.server.hostname"+System.getProperty("java.rmi.server.hostname"));
            System.out.println("java.rmi.server.codebase"+System.getProperty("java.rmi.server.codebase"));
            System.out.println("java.rmi.server.useLocalHostname��" + System.getProperty("java.rmi.server.useLocalHostname"));
            System.setProperty("java.rmi.server.useLocalHostname", "true");
            System.setProperty("java.rmi.server.hostname", "101.201.197.43");
            
            

            //RMISocketFactory.setSocketFactory(new TexasSocket()); //�������ݴ���˿� 12250
            LocateRegistry.createRegistry(Integer.parseInt(reg_port));  //RMIע��˿� 12200
            //if(ip_addr == "") {
            //    System.out.println("no ip addr");
            //    System.exit(0);
            //}
            
            this.hallController = new HallController(this, this.hallHelper); //ʵ�ֽӿ�ʾ��
            this.hallHelper.controller = hallController;
            this.texasPlayerController = new TexasPlayerController();
            this.texasPlayerController.server =this;
            this.hallHelper.playerController = this.texasPlayerController;

            Naming.rebind("rmi://"+ local_ip + ":" + reg_port + "/HallController", hallController);  //��
            Naming.rebind("rmi://"+ local_ip + ":" + reg_port  + "/TexasGameController", texasPlayerController);
            Naming.rebind("rmi://"+ local_ip + ":" + reg_port + "/Server", this);
            /*
            String[] sl = Naming.list("rmi://192.168.50.86:12200");
            for(int i=0;i<sl.length;i++) {
                System.out.println(sl[i]);
            }
            */
            System.out.println("RMI is opened");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[]) {
        try {
            Server s = new Server();
            s.service_deploy();//1
            s.rmi_open();//2
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

}