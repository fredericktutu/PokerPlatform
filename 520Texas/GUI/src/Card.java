import java.io.Serializable;

public class Card implements Serializable{
	public int suit; //»¨É«
	public int face; //ÅÆÃæ
	
	public Card(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}
	
	public void assginCard(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}
}
