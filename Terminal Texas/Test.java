
public class Test {
    public static void main(String args[]) {
        TerminalUI ui = new TerminalUI();

        Hall hall = new Hall();
        HallHelper hallHelper = new HallHelper(hall);

        Player player = new Player(hallHelper);
        player.name = "ÂÞÉñ";
        ui.player = player;
        
        player.setController(new TexasTerminalController(ui, player));
        hallHelper.servePlayer(player);
        

    }
}