import java.util.ArrayList;
public class TexasCommand { //��Ϸ������������������
	//ö��ֵ
	public ArrayList<String> nameList ;
	
	public static final int HAPPEN_EVERYONE_READY = 1; //ÿ���˶�׼������
	public static final int HAPPEN_SOMEONE_BET = 2; //������ע

	public static final int ACTION_ADD_BET = 1; //��ע
	public static final int ACTION_FOLLOW_BET = 2; //��ע
    public static final int ACTION_ABORT_BET = 3; //����
    public static final int ACTION_CHECK = 4; //����
	
	
	public int whatHappen; //������ʲô�������������е�һ��ֵ

	public int who; //˭��list�����˳��

	public int action; //����

	public int chips;  //������
	

	//������ÿ�������ִ���ȥ

	public TexasCommand(int who, Player2TexasGameHelperCommand p2gCommand) {
		this.whatHappen = 2;
		this.who = who;
		this.action = p2gCommand.action;
		this.chips = p2gCommand.chips;
	}
	
	public TexasCommand(ArrayList<Player> players){
		nameList = new ArrayList<String>();
		for(Player p:players){
			nameList.add(p.name);
		}
		this.whatHappen = 1;
	}
	
	
}