import java.util.ArrayList;

public class PokerTypeResult {
	public static int STRAIGHT_FLUSH = 1; //ͬ��˳
	public static int FOUR_OF_A_KIND = 2; //����
	public static int FULL_HOUSE = 3; //��«
	public static int FLUSH = 4; //ͬ��
	public static int STRAIGHT =5; //˳��
	public static int THREE_OF_A_KIND = 6;//����
	public static int TWO_PAIR = 7; //����
	public static int ONE_PAIR = 8; //һ��
	public static int HIGH_CARD =9; //����
	
	public int type;//�������
	public int whoseCards;//˭���ƣ��Ƚ�ʱ�õ�
	public int points;//���͵÷֣��Ƚ�ʱ�õ�
	
	public int straightMaxNumber;//����1,5

	public int firstSingle; //����2,6,7,8,9
	public int secondSingle; //����6,8,9
	public int thirdSingle; //����8,9
	public int fourthSingle; //����9
	public int fifthSingle; //����9

	public int fourNumber; //����2
	public int threeNumber;//����3,6
	public int twoNumber;//����3,7,8
	public int secTwoNumber; //����7
	
	public ArrayList<Card> finalCards = new ArrayList<Card>();//ѡ����5����
	
	public void calculatePoints() {
		switch(type)
	    {
	    	case 1 :
	    		points = straightMaxNumber;
	    		break;
	    	case 2 :
	    		points = fourNumber*100 + firstSingle;
	    		break;
	    	case 3 :
	    		points = threeNumber*100 + twoNumber;
	    		break;
	    	case 4 :
	    		points = firstSingle*100000000 + secondSingle*1000000 + thirdSingle*10000 + fourthSingle*100 + fifthSingle;
	    		break;
	    	case 5 :
	    		points = straightMaxNumber;
	    		break;
	    	case 6 :
	    		points = threeNumber*10000 + firstSingle*100 + secondSingle;
	    		break;
	    	case 7 :
	    		points = twoNumber*10000 + secTwoNumber*100 + firstSingle;
	    		break;
	    	case 8 :
	    		points = twoNumber*1000000 + firstSingle*10000 + secondSingle*100 + thirdSingle;
	    		break;
	    	case 9 :
	    		points = firstSingle*100000000 + secondSingle*1000000 + thirdSingle*10000 + fourthSingle*100 + fifthSingle;
	    		break;
	    	default :
	    		points = 0;
	    }
		
	}
}
