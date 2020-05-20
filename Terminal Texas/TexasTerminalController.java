
//图形界面-》德扑

//命令行 -》德扑

//AI -》 德扑
public class TexasTerminalController implements UIController { // 命令行 《-》 游戏助理类
    TerminalUI ui;
    Player player;
    TexasGameHelper helper;

    public TexasTerminalController(TerminalUI ui, Player player) {
        this.ui = ui;
        this.player = player;
    }

    public void user_input_handler(int[] input) { // 命令行界面调用遥控器，遥控器会去找游戏助理
        Player2TexasGameHelperCommand command = new Player2TexasGameHelperCommand();
        command.whatHappen = Player2TexasGameHelperCommand.I_BET;
        switch (input[0]) {
            case 2:
                command.action = Player2TexasGameHelperCommand.ACTION_ABORT_BET;
                break;

            case 3:
                command.action = Player2TexasGameHelperCommand.ACTION_FOLLOW_BET;
                break;

            case 4:
                command.action = Player2TexasGameHelperCommand.ACTION_ADD_BET;
                command.chips = input[1];
                break;

            case 5:
                command.action = Player2TexasGameHelperCommand.ACTION_CHECK;
                break;
            case 6:
                command.whatHappen = Player2TexasGameHelperCommand.I_AM_READY;
        }

        helper.call(player, command);
    }

    public void update(String s, boolean isYourTurn) { // 游戏助理调用，更新命令行
        ui.show(s); // 图形界面更新
        if (isYourTurn) {
            ui.user_input_event(); // 命令行，接受一个输入
        }
    }

    public void update(String s) { // 玩家调用，更新命令行，有关平台的消息。
        ui.show(s);
    }

    public void update(boolean enterGame) {
        if(enterGame) {
            ui.show("已经为您分配大厅助理,请创建房间");
            ui.user_input_event(); //命令行接受输入，期待用户创建游戏
        }
    }
}
