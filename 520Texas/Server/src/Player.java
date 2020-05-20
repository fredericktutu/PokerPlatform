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

 
    

    public  void update_info(int moneyChange, boolean winOrLose) {   //���Լ�����Ϣ�ϴ���������
        this.money += moneyChange; //�޸�������е�Ǯ��

        server.service_update_money(this.id, this.money); //����Ǯ�� 
        server.service_update_record(id, winOrLose); //����ʤ��
    }
    
    
    public boolean is_human() {
        return (this.aiController == null); 
    }

    /*public boolean add_money(int n) {  //����Ҽ�Ǯ
        this.money += n;
        update_info();
        return true;
    }*/

    public boolean can_cut_money(int n) {  //����Ҽ���Ǯ
        if(this.money < n) {
            return false;
        } else {
            return true;
        }
    }
    
    



}