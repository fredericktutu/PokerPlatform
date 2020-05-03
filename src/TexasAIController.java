import java.util.ArrayList;

public class TexasAIController implements UIController {
    public Player player;
    public TexasGameHelper helper;

    public TexasAIController(Player player, TexasGameHelper helper) {
        this.player = player;
        this.helper = helper;
    }

    public void update(String s, boolean isYourTurn) { // ��Ϸ������ã�����������
        if (isYourTurn) {
            if(s.equals("��׼��:")){
                //System.out.println("AI����׼����");
                Player2TexasGameHelperCommand command = new Player2TexasGameHelperCommand();
                command.whatHappen = Player2TexasGameHelperCommand.I_AM_READY;
                helper.call(player, command);
            } else {
                //System.out.println("AI���ύ����Ϣ");
                Player2TexasGameHelperCommand command = new Player2TexasGameHelperCommand();
                command.whatHappen = Player2TexasGameHelperCommand.I_BET;
                command.action = Player2TexasGameHelperCommand.ACTION_ABORT_BET;
                helper.call(player, command);
            }

        }
        


    }

    public void update(String s) { // ��ҵ��ã����������У��й�ƽ̨����Ϣ��
        return;
    }

    public void update(boolean enterGame) {
        return;
    }

	@Override
	public void updatePublicCards(ArrayList<Card> publicCards) {
		// TODO Auto-generated method stub
		
	}

}