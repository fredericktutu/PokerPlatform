public class Player {
    public Server server;
    public TexasAIController aiController;
    public TexasPlayerController playerController;
    public HallHelper hallHelper;
    public TexasGameHelper texasGameHelper;

    public IHallGUI hallGUI;
    public ITexasGameGUI texasGameGUI;

    public String name;
    public String id;
    public int money;

    public Player(String name, String id, int money) {
        this.name = name;
        this.id = id;
        this.money = money;
    }

 
    

    public  void update_info(int moneyChange, boolean winOrLose) {   //将自己的信息上传到服务器
        this.money += moneyChange; //修改玩家类中的钱数

        server.service_update_money(this.id, this.money); //更新钱数 
        server.service_update_record(id, winOrLose); //更新胜负
    }
    
    
    public boolean is_human() {
        return (this.aiController == null); 
    }

    /*public boolean add_money(int n) {  //给玩家加钱
        this.money += n;
        update_info();
        return true;
    }*/

    public boolean can_cut_money(int n) {  //给玩家减少钱
        if(this.money < n) {
            return false;
        } else {
            return true;
        }
    }
    
    



}