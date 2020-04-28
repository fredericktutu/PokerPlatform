
import java.util.ArrayList;

import java.util.Random;

public class Deal {
	public int [][] pile = new int[4][13];  //�ƶ�
	public ArrayList<Card> publicCards = new ArrayList<Card>(); //�����ƣ��Ե��ſ�ΪԪ�ص�arraylist
	public  ArrayList<PrivateCards> privateCardsList = new ArrayList<PrivateCards>(); //˽����arraylist���Ե�����ҵ�˽����ΪԪ��
	
	public void initPrivate(int playernumber) {
		for(int i =1; i<=playernumber;i++) {
			dealPrivate(Integer.toString(i));
		}
	}
	
	public PrivateCards dealPrivate(String username) { //��һ����ҷ���
		Card card1 = new Card(0,0); //��ʼ�����ſտ�
		Card card2 = new Card(0,0);
		while(true) {
			Random rand = new Random();
			int suit1 = rand.nextInt(4); //���ɴ���ɫ������������
			int face1 = rand.nextInt(13);
			if(pile[suit1][face1] != 1) {	//������ſ������ƶ���		
					card1.assginCard(suit1, face1);
					pile[suit1][face1] = 1; //�����ſ����ƶ����޳�
					break;
		  }
			else
				continue;
		}
		
		while(true) { //��������ķ���ѡ���ڶ�������
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
	
	public void dealPublic(){//�����Ź�������
		Card card1 = new Card(0,0); //��ʼ��3�ſտ�
		Card card2 = new Card(0,0);
		Card card3 = new Card(0,0);
		while(true) {
			Random rand = new Random();
			int suit1 = rand.nextInt(4) ; //���ɴ���ɫ������������
			int face1 = rand.nextInt(13) ;
			if(pile[suit1][face1] != 1) {	//������ſ������ƶ���		
					card1.assginCard(suit1, face1);
					pile[suit1][face1] = 1; //�����ſ����ƶ����޳�
					break;
		  }
			else
				continue;
		}
		
		
		while(true) {
			Random rand = new Random();
			int suit2 = rand.nextInt(4) ; //���ɴ���ɫ������������
			int face2 = rand.nextInt(13) ;
			if(pile[suit2][face2] != 1) {	//������ſ������ƶ���		
					card2.assginCard(suit2, face2);
					pile[suit2][face2] = 1; //�����ſ����ƶ����޳�
					break;
		  }
			else
				continue;
		}
		

		
		while(true) {
			Random rand = new Random();
			int suit3 = rand.nextInt(4) ; //���ɴ���ɫ������������
			int face3 = rand.nextInt(13) ;
			if(pile[suit3][face3] != 1) {	//������ſ������ƶ���		
					card3.assginCard(suit3, face3);
					pile[suit3][face3] = 1; //�����ſ����ƶ����޳�
					break;
		  }
			else
				continue;
		}
		

		
		this.publicCards.add(card1);
		this.publicCards.add(card2);
		this.publicCards.add(card3);

	} 
	
	public void dealOneRound(){ //���ַ������ƣ���һ��
		Card card1 = new Card(0,0); //��ʼ��1�ſտ�
		
		while(true) {
			Random rand = new Random();
			int suit1 = rand.nextInt(4) ; //���ɴ���ɫ������������
			int face1 = rand.nextInt(13) ;
			if(pile[suit1][face1] != 1) {	//������ſ������ƶ���		
					card1.assginCard(suit1, face1);
					pile[suit1][face1] = 1; //�����ſ����ƶ����޳�
					break;
		  }
			else
				continue;
		}
		this.publicCards.add(card1);		
	}

}
