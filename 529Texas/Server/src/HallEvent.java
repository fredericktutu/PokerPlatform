import java.util.ArrayList;

public class HallEvent {
	public int whatHappen; //�������¼���ö��ֵ����
	public static final int EVENT_FAILED = 1;  //ʧ���¼������failMsgʹ��
	public static final int EVENT_ENTER_ROOM = 2; //���뷿��ɹ�,�����ڴ��������롢�������룬���curRoomInfoʹ��
	public static final int EVENT_EXIT_ROOM = 3;  //�˳�����ɹ�
	public static final int EVENT_DISPLAY_ROOM = 4; //չʾ����ɹ���ͬʱ���ؿ��õķ�����Ϣ�б�
	
	public ArrayList<ActiveRoomInfo> activeRoomInfos;    //������Ϣ�б�

	public ActiveRoomInfo curRoomInfo; //������뷿��ɹ����򷵻ط����

	public String failMsg; //ʧ�ܵľ���ԭ��



}