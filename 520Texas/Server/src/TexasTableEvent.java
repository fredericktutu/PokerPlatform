
public class TexasTableEvent {//��Ϸ�߼����ظ�ƽ̨�߼��ķ���ֵ
	//ö��ֵ
	public static int TABLESTATE_DELIVER_HAND_CARD = 1; //������
	public static int TABLESTATE_DELIVER_THREE_PUBLIC_CARD = 2; //��������
	public static int TABLESTATE_GAME_OVER = 3; //һ����Ϸ����
	public static int TABLESTATE_DELIVER_FIRST_EXTRA_PUBLIC_CARD = 4; //���˵�һ�Ŷ���Ĺ�����
	public static int TABLESTATE_DELIVER_SECOND_EXTRA_PUBLIC_CARD =5; //���˵ڶ��Ŷ��⹫����
	public static int TABLESTATE_NOTHING = 6;//û�з��������¼�
	
	
	public int whatHappen; //��ǰ������ʲô����������е�һ��
	public int whoseTurn;  //�ֵ�˭�ˣ������ϵڼ�����
	
	public boolean add;
	public boolean follow;
	public boolean check;
	public boolean abort;
	
	public int addMoney;//addΪtrueʱ�����ã�����ܼӵ����٣�Ҳ�����Լ�����Ǯ����
	public int followMoney;//followΪtrueʱ�����ã���ע��Ҫ�������٣����Ǯ����Ҫall in
	
	public int maxChips;//��ǰ���ע�룬����ͼ�ν��������עѡ��
	
	
}

