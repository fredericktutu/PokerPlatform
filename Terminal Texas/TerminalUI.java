
import java.util.Scanner;

public class TerminalUI { // �������û�����
    public Scanner scanner;
    public Player player;

    public TerminalUI() {
        scanner = new Scanner(System.in);
    }

    public void show(String s) { // ���½����е�����
        System.out.println(s);
    }

    public int[] get_user_input() { // �û������˲���
        // 0 �������� 1 �˳�����
        // 2 ���� 3 ��ע 4 ���� 5 ��ע 6׼����
        // ����Ǽ�ע������Ҫ����Ӷ���
        int[] res = new int[2];
        res[0] = Integer.parseInt(scanner.nextLine());
        if (res[0] == 5) {
            res[1] = Integer.parseInt(scanner.nextLine());
        }
        return res;
    }

    public void user_input_event() { // �û������¼�
        int[] pair = get_user_input(); // ��ȡ����
        
        if (pair[0] == 0) { // ���ڴ����������йز���
            player.create_room();
        } else if (pair[0] == 1) {
            player.exit_room();
        } else { // ������Ϸ�߼�����ص�
            TexasTerminalController controller = (TexasTerminalController)player.controller;
            controller.user_input_handler(pair);
            
        }
    }



}