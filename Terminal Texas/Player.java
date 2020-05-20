
public class Player {
    public UIController controller; // UI控制器
    public TexasGameHelper gameHelper; // 游戏助理
    public HallHelper hallHelper; // 大厅助理类

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

    public void create_room() { // UI调用
        hallHelper.createRoom(this);
    }

    public void exit_room() { // UI调用
        hallHelper.exitRoom(this);
    }

    public void update(String s) { // 被大厅助理类调用，更新命令行
        controller.update(s); // 借用
    }
    public void update(boolean gameStart) {
        if(gameStart) {
            controller.update(true);
        }
    }

}