import java.io.Serializable;

public class Card implements Serializable{
	public int suit; //��ɫ
	public int face; //����
	
	public Card(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}
	
	public void assginCard(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}
}
