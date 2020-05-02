
import java.util.Scanner;

public class TerminalUI { // 命令行用户界面
    public Scanner scanner;
    public Player player;

    public TerminalUI() {
        scanner = new Scanner(System.in);
    }

    public void show(String s) { // 更新界面中的文字
        System.out.println(s);
    }

    public int[] get_user_input() { // 用户输入了操作
        // 0 创建房间 1 退出房间
        // 2 盖牌 3 跟注 4 让牌 5 加注 6准备好
        // 如果是加注，还需要输入加多少
        int[] res = new int[2];
        res[0] = Integer.parseInt(scanner.nextLine());
        if (res[0] == 5) {
            res[1] = Integer.parseInt(scanner.nextLine());
        }
        return res;
    }

    public void user_input_event() { // 用户输入事件
        int[] pair = get_user_input(); // 获取输入
        
        if (pair[0] == 0) { // 关于大厅助理类有关操作
            player.create_room();
        } else if (pair[0] == 1) {
            player.exit_room();
        } else { // 关于游戏逻辑类相关的
            TexasTerminalController controller = (TexasTerminalController)player.controller;
            controller.user_input_handler(pair);
            
        }
    }



}