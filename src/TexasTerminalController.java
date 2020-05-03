
//ͼ�ν���-������

//������ -������

import java.util.ArrayList;

//AI -�� ����
public class TexasTerminalController implements UIController { // ������ ��-�� ��Ϸ������
    TerminalUI ui;
    Player player;
    TexasGameHelper helper;

    public TexasTerminalController(TerminalUI ui, Player player) {
        this.ui = ui;
        this.player = player;
    }

    public void user_input_handler(int[] input) { // �����н������ң������ң������ȥ����Ϸ����
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

    public void update(String s, boolean isYourTurn) { // ��Ϸ�������ã�����������
        ui.show(s); // ͼ�ν������
        if (isYourTurn) {
            ui.user_input_event(); // �����У�����һ������
        }
    }

    public void update(String s) { // ��ҵ��ã����������У��й�ƽ̨����Ϣ��
        ui.show(s);
    }

    public void update(boolean enterGame) {
        if(enterGame) {
            ui.show("�Ѿ�Ϊ�������������,�봴������");
            ui.user_input_event(); //�����н������룬�ڴ��û�������Ϸ
        }
    }

    public void updatePublicCards(ArrayList<Card> publicCards){}

    
}
