import java.util.ArrayList;
public class TexasCommand { //��Ϸ������������������
	//ö��ֵ
	/*public ArrayList<String> nameList ;
	
	public static final int HAPPEN_EVERYONE_READY = 1; //ÿ���˶�׼������
	public static final int HAPPEN_SOMEONE_BET = 2; //������ע

	public static final int ACTION_ADD_BET = 1; //��ע
	public static final int ACTION_FOLLOW_BET = 2; //��ע
    public static final int ACTION_ABORT_BET = 3; //����
    public static final int ACTION_CHECK = 4; //����
	
	
	public int whatHappen; //������ʲô�������������е�һ��ֵ

	public int who; //˭��list�����˳��

	public int action; //����

	public int chips;  //������*/
	
	public static int HAPPEN_EVERYONE_READY = 1; //ÿ���˶�׼������
	public static int HAPPEN_SOMEONE_BET = 2; //������ע

	public static int ACTION_ADD_BET = 1; //��ע
	public static int ACTION_FOLLOW_BET = 2; //��ע
    public static int ACTION_ABORT_BET = 3; //����
    public static int ACTION_CHECK = 4; //����
	
	public int whatHappen; //������ʲô���������һ��ֵ

	public int who; //˭

	public int action; //����

	public int chips;  //������
	
	//public int maxChipsLimit;//���ޣ�05162338����
	public int minChipsLimit;//��ע����Room/RoomMessage�õ�
	
	public ArrayList<String> nameList = new ArrayList<String>();
	public int [] allMoney; 
	
	

	//������ÿ�������ִ���ȥ

	/*public TexasCommand(int who, Player2TexasGameHelperCommand p2gCommand) {
		this.whatHappen = HAPPEN_SOMEONE_BET;
		this.who = who;
		this.action = p2gCommand.action;
		this.chips = p2gCommand.chips;
	}*/ //0516����
	
	//command.nameList, command.allMoney, command.minChipsLimit
	//��ʼ��
	
	public TexasCommand(boolean single,Player[] players,int minChipsLimitType){
		this.nameList = new ArrayList<String>();
		this.allMoney = new int[players.length];
		int i = 0;
		for(Player p:players){
			this.nameList.add(p.name);
			if(!single) {
				this.allMoney[i++] = p.money;
			} else {
				this.allMoney[i++] = 20000;
			}
		}
		this.minChipsLimit = (int) Math.pow(10, minChipsLimitType) *10;
		this.whatHappen = HAPPEN_EVERYONE_READY;
	}
	
	//��ʽ��Ϸ��ʱ����
	public TexasCommand(int who, int action, int chips){
		this.whatHappen = HAPPEN_SOMEONE_BET;
		this.who = who;
		this.action = action;
		this.chips = chips;
	}
	
	
	
}