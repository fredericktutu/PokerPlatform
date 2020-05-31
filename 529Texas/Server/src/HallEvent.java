import java.util.ArrayList;

public class HallEvent {
	public int whatHappen; //发生的事件，枚举值如下
	public static final int EVENT_FAILED = 1;  //失败事件，配合failMsg使用
	public static final int EVENT_ENTER_ROOM = 2; //进入房间成功,适用于创建、加入、搜索加入，配合curRoomInfo使用
	public static final int EVENT_EXIT_ROOM = 3;  //退出房间成功
	public static final int EVENT_DISPLAY_ROOM = 4; //展示房间成功，同时返回可用的房间信息列表
	
	public ArrayList<ActiveRoomInfo> activeRoomInfos;    //房间信息列表

	public ActiveRoomInfo curRoomInfo; //如果进入房间成功，则返回房间号

	public String failMsg; //失败的具体原因



}