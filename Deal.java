
import java.util.ArrayList;

import java.util.Random;

public class Deal {
	public int [][] pile = new int[4][13];  //牌堆
	public ArrayList<Card> publicCards = new ArrayList<Card>(); //公共牌，以单张卡为元素的arraylist
	public  ArrayList<PrivateCards> privateCardsList = new ArrayList<PrivateCards>(); //私有牌arraylist，以单个玩家的私有牌为元素
	
	public void initPrivate(int playernumber) {
		for(int i =1; i<=playernumber;i++) {
			dealPrivate(Integer.toString(i));
		}
	}
	
	public PrivateCards dealPrivate(String username) { //给一个玩家发牌
		Card card1 = new Card(0,0); //初始化两张空卡
		Card card2 = new Card(0,0);
		while(true) {
			Random rand = new Random();
			int suit1 = rand.nextInt(4); //生成代表花色和牌面的随机数
			int face1 = rand.nextInt(13);
			if(pile[suit1][face1] != 1) {	//如果这张卡还在牌堆里		
					card1.assginCard(suit1, face1);
					pile[suit1][face1] = 1; //将这张卡从牌堆中剔除
					break;
		  }
			else
				continue;
		}
		
		while(true) { //仿照上面的方法选出第二张手牌
			Random rand = new Random();
			int suit2 = rand.nextInt(4) ;
			int face2 = rand.nextInt(13) ;

			if(pile[suit2][face2] != 1) {			
					card2.assginCard(suit2, face2);
					pile[suit2][face2] = 1;
					break;
		  }
			else
				continue;
		}
		
		PrivateCards ret = new PrivateCards(username, card1, card2);
		this.privateCardsList.add(ret);
		return ret;
	}
	
	public void dealPublic(){//发三张公共底牌
		Card card1 = new Card(0,0); //初始化3张空卡
		Card card2 = new Card(0,0);
		Card card3 = new Card(0,0);
		while(true) {
			Random rand = new Random();
			int suit1 = rand.nextInt(4) ; //生成代表花色和牌面的随机数
			int face1 = rand.nextInt(13) ;
			if(pile[suit1][face1] != 1) {	//如果这张卡还在牌堆里		
					card1.assginCard(suit1, face1);
					pile[suit1][face1] = 1; //将这张卡从牌堆中剔除
					break;
		  }
			else
				continue;
		}
		
		
		while(true) {
			Random rand = new Random();
			int suit2 = rand.nextInt(4) ; //生成代表花色和牌面的随机数
			int face2 = rand.nextInt(13) ;
			if(pile[suit2][face2] != 1) {	//如果这张卡还在牌堆里		
					card2.assginCard(suit2, face2);
					pile[suit2][face2] = 1; //将这张卡从牌堆中剔除
					break;
		  }
			else
				continue;
		}
		

		
		while(true) {
			Random rand = new Random();
			int suit3 = rand.nextInt(4) ; //生成代表花色和牌面的随机数
			int face3 = rand.nextInt(13) ;
			if(pile[suit3][face3] != 1) {	//如果这张卡还在牌堆里		
					card3.assginCard(suit3, face3);
					pile[suit3][face3] = 1; //将这张卡从牌堆中剔除
					break;
		  }
			else
				continue;
		}
		

		
		this.publicCards.add(card1);
		this.publicCards.add(card2);
		this.publicCards.add(card3);

	} 
	
	public void dealOneRound(){ //单轮发公共牌，发一张
		Card card1 = new Card(0,0); //初始化1张空卡
		
		while(true) {
			Random rand = new Random();
			int suit1 = rand.nextInt(4) ; //生成代表花色和牌面的随机数
			int face1 = rand.nextInt(13) ;
			if(pile[suit1][face1] != 1) {	//如果这张卡还在牌堆里		
					card1.assginCard(suit1, face1);
					pile[suit1][face1] = 1; //将这张卡从牌堆中剔除
					break;
		  }
			else
				continue;
		}
		this.publicCards.add(card1);		
	}

}
