
public class TexasTableEvent {
	// ö��ֵ
	public static final int TABLESTATE_DELIVER_HAND_CARD = 1; // ������
	public static final int TABLESTATE_DELIVER_THREE_PUBLIC_CARD = 2; // ��������
	public static final int TABLESTATE_GAME_OVER = 3; // һ����Ϸ����
	public static final int TABLESTATE_DELIVER_FIRST_EXTRA_PUBLIC_CARD = 4; // ���˵�һ�Ŷ���Ĺ�����
	public static final int TABLESTATE_DELIVER_SECOND_EXTRA_PUBLIC_CARD = 5; // ���˵ڶ��Ŷ��⹫����
	public static final int TABLESTATE_NOTHING = 6;
	public int whatHappen; // ��ǰ������ʲô����������е�һ��
	public int whoseTurn; // �ֵ�˭�ˣ������ϵڼ�����


}