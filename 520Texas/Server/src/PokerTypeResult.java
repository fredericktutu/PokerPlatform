import java.util.ArrayList;

public class PokerTypeResult {
	public static int STRAIGHT_FLUSH = 1; //同花顺
	public static int FOUR_OF_A_KIND = 2; //四条
	public static int FULL_HOUSE = 3; //葫芦
	public static int FLUSH = 4; //同花
	public static int STRAIGHT =5; //顺子
	public static int THREE_OF_A_KIND = 6;//三条
	public static int TWO_PAIR = 7; //两对
	public static int ONE_PAIR = 8; //一对
	public static int HIGH_CARD =9; //高牌
	
	public int type;//上面九种
	public int whoseCards;//谁的牌，比较时用到
	public int points;//牌型得分，比较时用到
	
	public int straightMaxNumber;//用于1,5

	public int firstSingle; //用于2,6,7,8,9
	public int secondSingle; //用于6,8,9
	public int thirdSingle; //用于8,9
	public int fourthSingle; //用于9
	public int fifthSingle; //用于9

	public int fourNumber; //用于2
	public int threeNumber;//用于3,6
	public int twoNumber;//用于3,7,8
	public int secTwoNumber; //用于7
	
	public ArrayList<Card> finalCards = new ArrayList<Card>();//选出的5张牌
	
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
