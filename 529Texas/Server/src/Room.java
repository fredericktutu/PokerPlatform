import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class Room 
{ 
	private ReadWriteLock rwl = new ReentrantReadWriteLock(); //用读写锁来实现
	private Lock r = rwl.readLock();  //读锁，可以支持多个线程同时访问
	private Lock w = rwl.writeLock(); //写锁，和其他的锁互斥

	public RoomOption option;
	public String RoomID;
	public String password;
    public Player[] players = null;
    public int[] playersReady = null;
    public boolean gameOn;

    public TexasGameHelper texasGameHelper;

    
    public Room(RoomOption roomOption)
    {
        this.option= roomOption;
        switch(this.option.personType)
        {
            case 2: 
                this.players = new Player[2];
                this.playersReady = new int[2];
                fill_zero(playersReady, 2);
                break;
            case 3: 
                this.players = new Player[3];
                this.playersReady = new int[3];
                fill_zero(playersReady, 3);
                break;
            case 4: 
                this.players = new Player[4];
                this.playersReady = new int[4];
                fill_zero(playersReady, 4);
                break;
            default: 
                this.players = new Player[5];
                this.playersReady = new int[5];
                fill_zero(playersReady, 5);
        }
        this.password = roomOption.roomPassport;
    }

    public void fill_zero(int[] arr,int n) {
        for(int i=0;i<n;i++) {
            arr[i] = 0;
        }
    }
    
    public int get_player_index(Player p) {
        for(int i=0;i<players.length;i++) {
            if(players[i] == p) return i;
        } 
        return -1;
    }

    public int playersInGame()  
    {
    	int num = 0;
    	for(int i = 0; i < this.option.personType; i++)
    	{
    		if(this.players[i] != null)
    			num += 1;
    	}
    	return num;
    }
    
    public boolean addPlayer(Player p)
    {
        r.lock();
        int maxnum = this.option.personType;
        if(this.playersInGame() + 1 > maxnum)
        {
        	
            r.unlock();
            return false;
        }
        else
        {
            p.playing = true;
        	int newplayer = this.nextSeat();
            this.players[newplayer] = p;
            r.unlock();
        	return true;
        }
        
    }

    
    public void removePlayer(Player p)//leave when the game not start
    {
        w.lock();
        if(this.option.modeType == RoomOption.MODE_SINGLE) {
            this.gameOn = false;
        }
        for(int i = 0; i < this.players.length; i++)
        {
            if(this.players[i] == null) continue;
            if(this.players[i].id.equals(p.id))
            {
                if(gameOn) {
                    players[i].aiController = new TexasAIController(players[i], 1);
                    players[i].playerController = null;
                } else {
                    p.playing = false;
                    p.texasGameHelper = null;
                    players[i].update_exit();
                    players[i] = null;
                    playersReady[i] = 0;
                }

            }
        }
        w.unlock();
    }


    public void playerReady(Player p) {
        r.lock();
        int index;
		for(index=0;players[index]!=p;index++) ;
        playersReady[index] = 1;
        
        int i = 0;
        while(i<playersReady.length&&playersReady[i]==1) i++;
        if(i >= playersReady.length) {
            gameOn = true;
        }
        
        r.unlock();
    }

    public  void clearReady() {
        Arrays.fill(playersReady, 0);
    }

    public boolean empty() {
        boolean flag = true;
        for(int i=0;i<players.length;i++) {
            if(players[i] != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public int nextSeat() {
        for(int i=0;i<players.length;i++) {
            if(players[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    
}   

    
