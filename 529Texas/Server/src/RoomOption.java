import java.io.Serializable;

public class RoomOption implements Serializable {
	public String roomPassport = "";
	public static int TWO_PERSON = 2;
	public static int THREE_PERSON = 3;
	public static int FOUR_PERSON = 4;
	public static int FIVE_PERSON = 5;
	public int personType;

	public static int GAME_TEXAS = 1;
	public static int GAME_OTHER = 2;
	public int gameType;
	
	public static int MODE_SINGLE = 1;
	public static int MODE_ONLINE = 2; 
	public int modeType;

	public static int DIF_EASY = 1;
	public static int DIF_MEDIUM = 2;
	public static int DIF_HARD = 3;
	public int  difficultyType;

	public static int LEVEL_PRIMARY = 1;
	public static int LEVEL_MEDIUM = 2;
	public static int LEVEL_ADVANCE = 3;
	public int levelType;
}