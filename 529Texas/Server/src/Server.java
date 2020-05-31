import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    private ReadWriteLock rwl = new ReentrantReadWriteLock(); //用读写锁来实现
	private Lock r = rwl.readLock();  //读锁，可以支持多个线程同时访问
    private Lock w = rwl.writeLock(); //写锁，和其他的锁互斥
    
    HallHelper hallHelper;
    Hall hall;
    TexasPlayerController texasPlayerController;
    HallController hallController;

    public Server(){
        activePlayers = new HashMap<String, Player>();
        activeIdSet = new HashSet<String>();
    }

    //hash table
    public Map<String, Player> activePlayers;
    public Set<String> activeIdSet;

    public String encipher(String id, String name, int money)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//configure the date fromat
        String key = df.format(new Date()) + id;
        Player p = new Player(name, id, money);
        p.server = this;
        p.token = key;
        activePlayers.put(key, p);
        activeIdSet.add(id);
        return key;
    }

    public Player decipher(String token)
    {
        Player p = activePlayers.get(token);
        //System.out.println(p);
        return p; 
    }





    //User creates an account
    public boolean service_register(String id, String name, String passWord){ 
        //Check if the id is unique or not
        Statement stmt = null;
        Connection c = null;
        String encoded_psw = PasswordEncryption.encrypt(passWord);
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
                             "VALUES (?, ?, ?, 30000, 0, 0);";//Insert Java variable to the table
                PreparedStatement stmt0 = c.prepareStatement(sql);
                stmt0.setString(1, id);
                stmt0.setString(2, name);
                stmt0.setString(3, encoded_psw);
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
    
    public ArrayList<String> service_login(String id, String passWord) {
        Connection c = null;
        Statement stmt = null;
        ArrayList<String>res = new ArrayList<String>();
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //System.out.println("SELECT * FROM PLAYERS WHERE ID = '" + id +"';");
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE ID = '" + id +"';");
            if(!rs.next())//If the id is not found in data base
            {
                System.out.println("This account does not exist!");
                stmt.close();
                c.close();
                res.add("");
                return res;
            }
            else
            {
                if(activeIdSet.contains(id)) {
                    res.add("!");         //all ready login
                    return res;  
                }
                String name = rs.getString("NAME");
                String passwd = rs.getString("PASSWORD");
                int money = rs.getInt("MONEY");
                int win = rs.getInt("WIN");
                int lose = rs.getInt("LOSE");
                stmt.close();
                c.close();
                String ep = PasswordEncryption.encrypt(passWord);
                if(passwd.equals(ep)) {
                    System.out.println(name + " " + money + " " + win + " " + lose);
                    String token  = encipher(id, name, money);
                    res.add(token);
                    res.add(name);
                    //return encipher(id, name, money);
                    return res;
                } else {
                    res.add("");
                }
            }
        }catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return res;
    }



    public void service_exit(String token) {
        Player p = decipher(token);
        String id = "";
        if(p != null) {
            if(p.playing) {
                p.exit = true;
            } else {
                id = p.id;
                activeIdSet.remove(id);
                activePlayers.remove(token);
            }
        } 
    }

    //Update user's money after each game
    public boolean service_update_money(String id, int money) { 
        this.w.lock();
        System.out.println("Server: service update_money");
        Connection c = null;
        PreparedStatement stmt = null;
        try{
            System.out.println("Server: before sql");
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);
            System.out.println("Server: create sql");
            String sql = "UPDATE PLAYERS SET MONEY = (?) WHERE ID = (?);";//???
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, money);
            stmt.setString(2, id);
            stmt.executeUpdate();
            stmt.close();
            c.commit();
            c.close();
            
            System.out.println("Money updated successfully!");
            this.w.unlock();
            return  true;
        }catch(Exception e)
        {
            System.out.println("This ID is invalid!");
        }
        this.w.unlock();
        return false;
    }



    //Update user's wins and loses when he or she exit the room
    public boolean service_update_record(String id, boolean win) { 
        this.w.lock();
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
            this.w.lock();
            return true;
        }catch(Exception e)
        {   
            e.printStackTrace();
            System.out.println("This ID is invalid!");
        }
        this.w.lock();
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

        this.hallController = new HallController(this, this.hallHelper); //实现接口示例
        this.hallHelper.controller = hallController;
        this.texasPlayerController = new TexasPlayerController();
        this.texasPlayerController.server =this;
        this.hallHelper.playerController = this.texasPlayerController;
        

        
    }

    /*
    public void rmi_open(){
        try{
            //hallController rmi

            
            String local_ip = "localhost";
            String reg_port = "12200";
            String data_port = "12250";
            /*
            try {
                local_ip = InetAddress.getLocalHost().getHostAddress();
                System.out.println("服务器的私有IP地址是:"+ local_ip);
            }catch(Exception ukhe){
                ukhe.printStackTrace();
            }*
            
            
            /*
            if (null == System.getSecurityManager()) {
                System.setSecurityManager(new RMISecurityManager());
            }
            
            System.out.println("java.rmi.server.hostname"+System.getProperty("java.rmi.server.hostname"));
            System.out.println("java.rmi.server.codebase"+System.getProperty("java.rmi.server.codebase"));
            System.out.println("java.rmi.server.useLocalHostname：" + System.getProperty("java.rmi.server.useLocalHostname"));
            System.setProperty("java.rmi.server.useLocalHostname", "true");
            System.setProperty("java.rmi.server.hostname", "101.201.197.43");
            *
            

            //RMISocketFactory.setSocketFactory(new TexasSocket()); //定义数据传输端口 12250
            LocateRegistry.createRegistry(Integer.parseInt(reg_port));  //RMI注册端口 12200
            //if(ip_addr == "") {
            //    System.out.println("no ip addr");
            //    System.exit(0);
            //}
            


            Naming.rebind("rmi://"+ local_ip + ":" + reg_port + "/HallController", hallController);  //绑定
            Naming.rebind("rmi://"+ local_ip + ":" + reg_port  + "/TexasGameController", texasPlayerController);
            Naming.rebind("rmi://"+ local_ip + ":" + reg_port + "/Server", this);
            /*
            String[] sl = Naming.list("rmi://192.168.50.86:12200");
            for(int i=0;i<sl.length;i++) {
                System.out.println(sl[i]);
            }
            *
            System.out.println("RMI is opened");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    */


    

    public static void main(String args[]) {
        try {
            Server s = new Server();
            s.service_deploy();//1
            MyHttpService service = new MyHttpService(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

}