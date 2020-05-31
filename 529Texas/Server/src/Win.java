import java.util.ArrayList;

public class Win {
	public ArrayList<Card> Cards = new ArrayList<Card>();//输入的牌
	
	
	public int suitNumber [] = new int[4];
	public int faceNumber [] = new int[13];
	PokerTypeResult pokerTypeResult = new PokerTypeResult();
	
	public void initialize(ArrayList<Card> Cards) {		
		this.Cards = Cards;
		for(int i = 0; i < 4; i++)
		{
			suitNumber[i] = 0;
		}
		for(int i = 0; i < 13; i++)
		{
			faceNumber[i] = 0;
		}
		for(int i = 0; i < Cards.size(); i++)
		{
			suitNumber[Cards.get(i).suit]++;
			faceNumber[Cards.get(i).face]++;
		}
	}
	
	public void pokerType()
	{
		boolean result;
		result = straightFlush();
		if(result == true)
			return;
		
		result = fourOfAKind();
		if(result == true)
			return;
		
		result = fullHouse();
		if(result == true)
			return;
		
		result = flush();
		if(result == true)
			return;
		
		result = straight();
		if(result == true)
			return;
		
		result = threeOfAKind();
		if(result == true)
			return;
		
		result = twoPair();
		if(result == true)
			return;
		
		result = onePair();
		if(result == true)
			return;
		
		result = highCard();
		if(result == true)
			return;
			
		
	}
	
	public boolean straightFlush()
	{
		//判断有无同花
		int whichSuit = 4;
		for(int i = 0; i < 4; i++)
		{
			if(suitNumber[i] >= 5)
			{
				whichSuit = i;
				break;
			}
		}
		if(whichSuit == 4)
		{
			return false;
		}
		
		//挑出同花牌
		ArrayList<Card> newCards = new ArrayList<Card>();
		int newFaceNumber [] = new int[13];
		for(int i = 0; i < Cards.size(); i++)
		{
			if(Cards.get(i).suit == whichSuit)
			{
				newCards.add(Cards.get(i));
			}
		}
		for(int i = 0; i < newCards.size(); i++)
		{
			newFaceNumber[newCards.get(i).face]++;
		}
		
		if(newFaceNumber[0] == 1 && newFaceNumber[12] == 1 && newFaceNumber[11] == 1 && newFaceNumber[10] == 1 && newFaceNumber[9] == 1)
		{
			pokerTypeResult.type = PokerTypeResult.STRAIGHT_FLUSH;
			pokerTypeResult.straightMaxNumber = 14;//其实是A
			pokerTypeResult.finalCards.add(new Card(whichSuit, 0));
			pokerTypeResult.finalCards.add(new Card(whichSuit, 9));
			pokerTypeResult.finalCards.add(new Card(whichSuit, 10));
			pokerTypeResult.finalCards.add(new Card(whichSuit, 11));
			pokerTypeResult.finalCards.add(new Card(whichSuit, 12));
			return true;
		}
		for(int i = 12; i >= 4; i--)
		{
			if(newFaceNumber[i] == 1 && newFaceNumber[i-1] == 1 && newFaceNumber[i-2] == 1 && newFaceNumber[i-3] == 1 && newFaceNumber[i-4] == 1)
			{
				pokerTypeResult.type = PokerTypeResult.STRAIGHT_FLUSH;
				pokerTypeResult.straightMaxNumber = i;
				pokerTypeResult.finalCards.add(new Card(whichSuit, i));
				pokerTypeResult.finalCards.add(new Card(whichSuit, i-1));
				pokerTypeResult.finalCards.add(new Card(whichSuit, i-2));
				pokerTypeResult.finalCards.add(new Card(whichSuit, i-3));
				pokerTypeResult.finalCards.add(new Card(whichSuit, i-4));
				return true;
			}
		}
		return false;	
	}
	
	public boolean flush() {
		int whichSuit = 4;
		for(int i = 0; i < 4; i++)
		{
			if(suitNumber[i] >= 5)
			{
				whichSuit = i;
				break;
			}
		}
		if(whichSuit == 4)//无同花
		{
			return false;
		}
		else//有同花
		{
			ArrayList<Card> newCards = new ArrayList<Card>();
			int newFaceNumber [] = new int[13];
			for(int i = 0; i < Cards.size(); i++)
			{
				if(Cards.get(i).suit == whichSuit)
				{
					newCards.add(Cards.get(i));
				}
			}
			for(int i = 0; i < newCards.size(); i++)
			{
				newFaceNumber[newCards.get(i).face]++;
			}
			if(newFaceNumber[0] == 1)
			{
				pokerTypeResult.finalCards.add(new Card(whichSuit, 0));
				int count = 0;
				for(int i = 12; i > 0; i--)
				{
					if(newFaceNumber[i] == 1)
					{
						pokerTypeResult.finalCards.add(new Card(whichSuit, i));
						count++;						
					}
					if(count == 4)
					{
						break;
					}
				}
			}
			else
			{
				int count = 0;
				for(int i = 12; i > 0; i--)
				{
					if(newFaceNumber[i] == 1)
					{
						pokerTypeResult.finalCards.add(new Card(whichSuit, i));
						count++;						
					}
					if(count == 5)
					{
						break;
					}
				}
			}
			pokerTypeResult.type = PokerTypeResult.FLUSH;
			pokerTypeResult.firstSingle = pokerTypeResult.finalCards.get(0).face;
			if(pokerTypeResult.firstSingle == 0)
			{
				pokerTypeResult.firstSingle = 14;
			}
			pokerTypeResult.secondSingle = pokerTypeResult.finalCards.get(1).face;
			pokerTypeResult.thirdSingle = pokerTypeResult.finalCards.get(2).face;
			pokerTypeResult.fourthSingle = pokerTypeResult.finalCards.get(3).face;
			pokerTypeResult.fifthSingle = pokerTypeResult.finalCards.get(4).face;
			return true;
		}
	}
	
	public boolean straight() {
		if(faceNumber[0] >= 1 && faceNumber[12] >= 1 && faceNumber[11] >= 1 && faceNumber[10] >= 1 && faceNumber[9] >= 1)
		{
			pokerTypeResult.type = PokerTypeResult.STRAIGHT;
			pokerTypeResult.straightMaxNumber = 14;//其实是A
			int chooseOrNot [] = new int[5];
			for(int i = 0; i < 5; i++)
			{
				chooseOrNot[i] = 0;
			}
			for(int i = 0; i < 7; i++)
			{
				int face = Cards.get(i).face;
				switch(face)
			    {
			         case 0 :
			        	 if(chooseOrNot[0] == 0)
			        	 {
			        		 pokerTypeResult.finalCards.add(Cards.get(i));
			        		 chooseOrNot[0] = 1;
			        	 }
			        	 break;
			         case 12 :
			        	 if(chooseOrNot[1] == 0)
			        	 {
			        		 pokerTypeResult.finalCards.add(Cards.get(i));
			        		 chooseOrNot[1] = 1;
			        	 }
			        	 break;
			         case 11 :
			        	 if(chooseOrNot[2] == 0)
			        	 {
			        		 pokerTypeResult.finalCards.add(Cards.get(i));
			        		 chooseOrNot[2] = 1;
			        	 }
			        	 break;
			         case 10 :
			        	 if(chooseOrNot[3] == 0)
			        	 {
			        		 pokerTypeResult.finalCards.add(Cards.get(i));
			        		 chooseOrNot[3] = 1;
			        	 }
			        	 break;
			         case 9 :
			        	 if(chooseOrNot[4] == 0)
			        	 {
			        		 pokerTypeResult.finalCards.add(Cards.get(i));
			        		 chooseOrNot[4] = 1;
			        	 }
			        	 break;
			         default :			        	 
			    }
			}
			return true;
		}
		for(int i = 12; i >= 4; i--)
		{
			if(faceNumber[i] >= 1 && faceNumber[i-1] >= 1 && faceNumber[i-2] >= 1 && faceNumber[i-3] >= 1 && faceNumber[i-4] >= 1)
			{
				int chooseOrNot [] = new int[5];
				for(int j = 0; j < 5; j++)
				{
					chooseOrNot[j] = 0;
				}
				for(int j = 0; j < 7; j++)
				{
					int face = Cards.get(j).face;
					if(face == i)
					{
						if(chooseOrNot[0] == 0)
			        	{
			        		pokerTypeResult.finalCards.add(Cards.get(j));
			        		chooseOrNot[0] = 1;
			        	}
					}
					else if(face == i-1)
					{
						if(chooseOrNot[0] == 0)
			        	{
			        		pokerTypeResult.finalCards.add(Cards.get(j));
			        		chooseOrNot[0] = 1;
			        	}						
					}
					else if(face == i-2)
					{
						if(chooseOrNot[0] == 0)
			        	{
			        		pokerTypeResult.finalCards.add(Cards.get(j));
			        		chooseOrNot[0] = 1;
			        	}
					}
					else if(face == i-3)
					{
						if(chooseOrNot[0] == 0)
			        	{
							pokerTypeResult.finalCards.add(Cards.get(j));
			        		chooseOrNot[0] = 1;
			        	}
					}
					else if(face == i-4)
					{
						if(chooseOrNot[0] == 0)
			        	{
			        		pokerTypeResult.finalCards.add(Cards.get(j));
			        		chooseOrNot[0] = 1;
			        	}
					}
					else//用不到
					{
						
					}
					
				}
				pokerTypeResult.type = PokerTypeResult.STRAIGHT;
				pokerTypeResult.straightMaxNumber = i;
				return true;
			}
		}
		return false;
	}
	
	public boolean fullHouse() {
		int whichFace1 = 15;
		int whichFace2 = 15;
		
		//先确定有三张
		if(faceNumber[0] >= 3)
		{
			pokerTypeResult.threeNumber = 0;
		}
		else
		{
			for(int i = 12; i > 0; i--)
			{
				if(faceNumber[i] >= 3)
				{
					whichFace1 = i;
					pokerTypeResult.threeNumber = i;
					break;
				}
			}
			if(whichFace1 == 15)
			{
				return false;
			}
		}
		
		//再确定有两张
		if(faceNumber[0] >= 3)
		{
			for(int i = 12; i > 0; i--)
			{
				if(faceNumber[i] >= 2)
				{
					whichFace2 = i;
					pokerTypeResult.twoNumber = i;
					break;
				}
			}
		}
		else
		{
			for(int i = 12; i >= 0; i--)
			{
				if(i == whichFace1)
				{
					continue;
				}
				if(faceNumber[i] >= 2)
				{
					if(i == 0) 
					{
						whichFace2 = i;
						pokerTypeResult.twoNumber = 0;
						break;
					}
					else
					{
						whichFace2 = i;
						pokerTypeResult.twoNumber = i;
						break;
					}
					
				}
			}			
		}
		if(whichFace2 == 15)
		{
			return false;
		}
		
		int count = 0;
		for(int j = 0; j < 7; j++)
		{
			int face = Cards.get(j).face;
			if(face == pokerTypeResult.threeNumber)
			{
				pokerTypeResult.finalCards.add(Cards.get(j));
			}
			else if(face == pokerTypeResult.twoNumber)
			{
				if(count < 2)
				{
					pokerTypeResult.finalCards.add(Cards.get(j));
					count++;
				}				
			}
			else
			{
				
			}
		}
		
		if(pokerTypeResult.threeNumber == 0)
		{
			pokerTypeResult.threeNumber = 14;
		}

		
		if(pokerTypeResult.twoNumber == 0)
		{
			pokerTypeResult.twoNumber = 14;
		}
		
		pokerTypeResult.type = PokerTypeResult.FULL_HOUSE;
		return true;
	}
	
	public boolean fourOfAKind(){
		int[] faceNumberCopy = new int [13];
		System.arraycopy(faceNumber,0,faceNumberCopy,0,13);

		int whichFace = 12;

		if(faceNumberCopy[0] == 4){
				whichFace = 0;
				faceNumberCopy[whichFace] = 0; //清0，便于寻找最大单牌
			}

		else{		
			while(whichFace >= 0){
				if(faceNumberCopy[whichFace] == 4)
					{faceNumberCopy[whichFace] = 0; //清0，便于寻找最大单牌
					break;}
				else
					whichFace = whichFace - 1;
			}
			if(whichFace == -1)
				return false;
		}

		int firstSingle = 12;
		if(faceNumberCopy[0] >= 1){
			firstSingle = 0;
			faceNumberCopy[firstSingle] = 0;
		}
		else{
			while(firstSingle >= 0){
				if(faceNumberCopy[firstSingle] >= 1){
					faceNumberCopy[firstSingle] = 0;
					break;
				}
				else
					firstSingle = firstSingle - 1;
			}
		}


		for(int i =0;i<7;i++){
			if(Cards.get(i).face == whichFace)
				{pokerTypeResult.finalCards.add(Cards.get(i));

}
				}
		for(int i =0;i<7;i++){
			 if(Cards.get(i).face == firstSingle)
				{pokerTypeResult.finalCards.add(Cards.get(i));
				
				}
		}
			
		
		pokerTypeResult.type = PokerTypeResult.FOUR_OF_A_KIND;
		if(firstSingle == 0) {
			pokerTypeResult.firstSingle = 14;
		}
		else {
			pokerTypeResult.firstSingle = firstSingle;
		}
		
		if(whichFace == 0) {
			pokerTypeResult.fourNumber = 14;
		}
		else {
			pokerTypeResult.fourNumber = whichFace;
		}
			
		return true;

	}


	public boolean threeOfAKind (){
		int[] faceNumberCopy = new int [13];
		System.arraycopy(faceNumber,0,faceNumberCopy,0,13);

		int whichFace = 12;

		if(faceNumberCopy[0] == 3){
				whichFace = 0;
				faceNumberCopy[whichFace] = 0; //清0，便于寻找单牌
			}

		else{		
			while(whichFace >= 0){
				if(faceNumberCopy[whichFace] == 3)
					{faceNumberCopy[whichFace] = 0; //清0，便于寻找单牌
					break;}
				else
					whichFace = whichFace - 1;
			}
			if(whichFace == -1)
				return false;
		}

		int firstSingle = 12;
		if(faceNumberCopy[0] >= 1){
			firstSingle = 0;
			faceNumberCopy[firstSingle] = 0;
		}
		else{
			while(firstSingle >= 0){
				if(faceNumberCopy[firstSingle] >= 1){
					faceNumberCopy[firstSingle] = 0;
					break;
				}
				else
					firstSingle = firstSingle - 1;
			}
		}

		int secondSingle = 12;
		if(faceNumberCopy[0] >= 1){
			secondSingle = 0;
			faceNumberCopy[secondSingle] = 0;
		}
		else{
			while(secondSingle >= 0){
				if(faceNumberCopy[secondSingle] >= 1){
					faceNumberCopy[secondSingle] = 0;
					break;
				}
				else
					secondSingle = secondSingle - 1;
			}
		}

		for(int i =0;i<7;i++){
			if(Cards.get(i).face == whichFace)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){
			if(Cards.get(i).face == firstSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){
			if(Cards.get(i).face == secondSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}

		pokerTypeResult.type = PokerTypeResult.THREE_OF_A_KIND;
		
		if(firstSingle == 0) {
			pokerTypeResult.firstSingle = 14;
		}
		else {
			pokerTypeResult.firstSingle = firstSingle;
		}
		

		pokerTypeResult.secondSingle = secondSingle;
		
		
		if(whichFace == 0) {
			pokerTypeResult.threeNumber = 14;
		}
		else {
			pokerTypeResult.threeNumber = whichFace;
		}
		
		return true;
	}

	public boolean twoPair(){
		int[] faceNumberCopy = new int [13];
		System.arraycopy(faceNumber,0,faceNumberCopy,0,13);

		int firstPair = 12;

		if(faceNumberCopy[0] == 2){
				firstPair = 0;
				faceNumberCopy[firstPair] = 0; 
			}

		else{		
			while(firstPair >= 0){
				if(faceNumberCopy[firstPair] == 2)
					{faceNumberCopy[firstPair] = 0;
					break;}
				else
					firstPair = firstPair - 1;
			}
			if(firstPair == -1)
				return false;
		}

		int secondPair = 12;

		if(faceNumberCopy[0] == 2){
				secondPair = 0;
				faceNumberCopy[secondPair] = 0; 
			}

		else{		
			while(secondPair >= 0){
				if(faceNumberCopy[secondPair] == 2)
					{faceNumberCopy[secondPair] = 0;
					break;}
				else
					secondPair = secondPair - 1;
			}
			if(secondPair == -1)
				return false;
		}

		int firstSingle = 12;
		if(faceNumberCopy[0] >= 1){
			firstSingle = 0;
			faceNumberCopy[firstSingle] = 0;
		}
		else{
			while(firstSingle >= 0){
				if(faceNumberCopy[firstSingle] >= 1){
					faceNumberCopy[firstSingle] = 0;
					break;
				}
				else
					firstSingle = firstSingle - 1;
			}
		}

		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == firstPair)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == secondPair)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == firstSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		pokerTypeResult.type = PokerTypeResult.TWO_PAIR;
		
		if(firstSingle == 0) {
			pokerTypeResult.firstSingle = 14;
		}
		else {
			pokerTypeResult.firstSingle = firstSingle;
		}
		
		if(firstPair == 0) {
			pokerTypeResult.twoNumber = 14;
		}
		else {
			pokerTypeResult.twoNumber = firstPair;
		}
		
		pokerTypeResult.secTwoNumber = secondPair;
		return true;
	}

	public boolean onePair(){
		int[] faceNumberCopy = new int [13];
		System.arraycopy(faceNumber,0,faceNumberCopy,0,13);

		int firstPair = 12;

		if(faceNumberCopy[0] == 2){
				firstPair = 0;
				faceNumberCopy[firstPair] = 0; 
			}

		else{		
			while(firstPair >= 0){
				if(faceNumberCopy[firstPair] == 2)
					{faceNumberCopy[firstPair] = 0;
					break;}
				else
					firstPair = firstPair - 1;
			}
			if(firstPair == -1)
				return false;
		}

		int firstSingle = 12;
		if(faceNumberCopy[0] >= 1){
			firstSingle = 0;
			faceNumberCopy[firstSingle] = 0;
		}
		else{
			while(firstSingle >= 0){
				if(faceNumberCopy[firstSingle] >= 1){
					faceNumberCopy[firstSingle] = 0;
					break;
				}
				else
					firstSingle = firstSingle - 1;
			}
		}

		int secondSingle = 12;
		if(faceNumberCopy[0] >= 1){
			secondSingle = 0;
			faceNumberCopy[secondSingle] = 0;
		}
		else{
			while(secondSingle >= 0){
				if(faceNumberCopy[secondSingle] >= 1){
					faceNumberCopy[secondSingle] = 0;
					break;
				}
				else
					secondSingle = secondSingle - 1;
			}
		}

		int thirdSingle = 12;
		if(faceNumberCopy[0] >= 1){
			thirdSingle = 0;
			faceNumberCopy[thirdSingle] = 0;
		}
		else{
			while(thirdSingle >= 0){
				if(faceNumberCopy[thirdSingle] >= 1){
					faceNumberCopy[thirdSingle] = 0;
					break;
				}
				else
					thirdSingle = thirdSingle - 1;
			}
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == firstPair)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == firstSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == secondSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == thirdSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}

		pokerTypeResult.type = PokerTypeResult.ONE_PAIR;
		
		if(firstPair == 0) {
			pokerTypeResult.twoNumber = 14;
		}
		else {
			pokerTypeResult.twoNumber = firstPair;
		}
		
		if(firstSingle == 0) {
			pokerTypeResult.firstSingle = 14;
		}
		else {
			pokerTypeResult.firstSingle = firstSingle;
		}
		
		pokerTypeResult.secondSingle = secondSingle;
		pokerTypeResult.thirdSingle = thirdSingle;

		return true;

	}

	public boolean highCard(){
		int firstSingle = 12;
		int[] faceNumberCopy = new int [13];
		System.arraycopy(faceNumber,0,faceNumberCopy,0,13);
		
		if(faceNumberCopy[0] >= 1){
			firstSingle = 0;
			faceNumberCopy[firstSingle] = 0;
		}
		else{
			while(firstSingle >= 0){
				if(faceNumberCopy[firstSingle] >= 1){
					faceNumberCopy[firstSingle] = 0;
					break;
				}
				else
					firstSingle = firstSingle - 1;
			}
		}

		int secondSingle = 12;
		if(faceNumberCopy[0] >= 1){
			secondSingle = 0;
			faceNumberCopy[secondSingle] = 0;
		}
		else{
			while(secondSingle >= 0){
				if(faceNumberCopy[secondSingle] >= 1){
					faceNumberCopy[secondSingle] = 0;
					break;
				}
				else
					secondSingle = secondSingle - 1;
			}
		}

		int thirdSingle = 12;
		if(faceNumberCopy[0] >= 1){
			thirdSingle = 0;
			faceNumberCopy[thirdSingle] = 0;
		}
		else{
			while(thirdSingle >= 0){
				if(faceNumberCopy[thirdSingle] >= 1){
					faceNumberCopy[thirdSingle] = 0;
					break;
				}
				else
					thirdSingle = thirdSingle - 1;
			}
		}

		int fourthSingle = 12;
		if(faceNumberCopy[0] >= 1){
			fourthSingle = 0;
			faceNumberCopy[fourthSingle] = 0;
		}
		else{
			while(fourthSingle >= 0){
				if(faceNumberCopy[fourthSingle] >= 1){
					faceNumberCopy[fourthSingle] = 0;
					break;
				}
				else
					fourthSingle = fourthSingle - 1;
			}
		}

		int fifthSingle = 12;
		if(faceNumberCopy[0] >= 1){
			fifthSingle = 0;
			faceNumberCopy[fifthSingle] = 0;
		}
		else{
			while(fifthSingle >= 0){
				if(faceNumberCopy[fifthSingle] >= 1){
					faceNumberCopy[fifthSingle] = 0;
					break;
				}
				else
					fifthSingle = fifthSingle - 1;
			}
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == firstSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == secondSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == thirdSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == fourthSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		
		for(int i =0;i<7;i++){		
			if(Cards.get(i).face == fifthSingle)
				pokerTypeResult.finalCards.add(Cards.get(i));
		}
		

		pokerTypeResult.type = PokerTypeResult.HIGH_CARD;
		
		if(firstSingle == 0) {
			pokerTypeResult.firstSingle = 14;
		}
		else {
			pokerTypeResult.firstSingle = firstSingle;
		}

		pokerTypeResult.secondSingle = secondSingle;
		pokerTypeResult.thirdSingle = thirdSingle;
		pokerTypeResult.fourthSingle = fourthSingle;
		pokerTypeResult.fifthSingle = fifthSingle;
		return true;

	}
}
