
public class Player {
    public UIController controller; // UI������
    public TexasGameHelper gameHelper; // ��Ϸ����
    public HallHelper hallHelper; // ����������

    public String name;
    public int money;

    public Player(HallHelper hallHelper) {
        this.hallHelper = hallHelper;
    }

    public void setController(UIController controller) {
        this.controller = controller;
    }

    public void setGameHelper(TexasGameHelper gameHelper) {
        this.gameHelper = gameHelper;
        if(this.controller instanceof TexasTerminalController) {
            TexasTerminalController tController = (TexasTerminalController)this.controller;
            tController.helper = gameHelper;
        }
    }

    public void create_room() { // UI����
        hallHelper.createRoom(this);
    }

    public void exit_room() { // UI����
        hallHelper.exitRoom(this);
    }

    public void update(String s) { // ��������������ã�����������
        controller.update(s); // ����
    }
    public void update(boolean gameStart) {
        if(gameStart) {
            controller.update(true);
        }
    }

}