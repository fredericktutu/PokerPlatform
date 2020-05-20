
import java.util.ArrayList;

public class PrivateCards {
	public String username;
	public ArrayList<Card> privateCards = new ArrayList<Card> ();

	public PrivateCards(String username, Card card1, Card card2){
		this.username = username;
		this.privateCards.add(card1);
		this.privateCards.add(card2);
 }
}
