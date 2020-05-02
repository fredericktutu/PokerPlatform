
import java.util.ArrayList;

public interface UIController {
    void update(String s);
    void update(String s, boolean isYourTurn);
    void update(boolean enterGame);

    void updatePublicCards(ArrayList<Card> publicCards);
}