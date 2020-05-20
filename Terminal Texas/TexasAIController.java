
public class TexasAIController implements UIController {
    public Player player;
    public TexasGameHelper helper;

    public TexasAIController(Player player, TexasGameHelper helper) {
        this.player = player;
        this.helper = helper;
    }

    public void update(String s, boolean isYourTurn) { // 游戏助理调用，更新命令行
        if (isYourTurn) {
            if(s.equals("请准备:")){
                //System.out.println("AI：已准备好");
                Player2TexasGameHelperCommand command = new Player2TexasGameHelperCommand();
                command.whatHappen = Player2TexasGameHelperCommand.I_AM_READY;
                helper.call(player, command);
            } else {
                //System.out.println("AI：提交了信息");
                Player2TexasGameHelperCommand command = new Player2TexasGameHelperCommand();
                command.whatHappen = Player2TexasGameHelperCommand.I_BET;
                command.action = Player2TexasGameHelperCommand.ACTION_ABORT_BET;
                helper.call(player, command);
            }

        }
        


    }

    public void update(String s) { // 玩家调用，更新命令行，有关平台的消息。
        return;
    }

    public void update(boolean enterGame) {
        return;
    }

}