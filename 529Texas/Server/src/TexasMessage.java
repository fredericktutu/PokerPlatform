import java.util.ArrayList;

/*
msg��Ӧ��������Ϣ:
1. *�������¼�
2. *�ֵ��ĸ��û���
3. *Ŀǰÿ������ע��Ǯ��
4. *���ӵ���־
5. *��ǰ�����û���״̬
6.a ���ֵ��㣬��ô��Ŀ�ѡ���������ƻ��Ǹ���
7. ����
8. ������
���У�1234Ӧ����ÿ�ζ���Ҫ�ģ�567�ǿ�ѡ
*/
public class TexasMessage {
    /*public boolean fullTrans;  //��ȫ��������? 
    //false�� �����û�      true: ����AI������������û���Ҫ���?

     // 1 �������¼�,����Ǹ�TexasPlayerController�õ�
    public int whatHappen;   //ͬEvent�е�ö��ֵ //had
    
     // 1�ڼ��֣���TexasAIController��
    public int round;      //table.whichTurn

    //2 �ֵ��ĸ��û�,intֵ�����������������
    public int whoseTurn;	//event
    //2 �ĸ�����Ϣ�������ֵ�����     
    public boolean isYourTurn;  //gh-self
    
    //05172329
    public int playerIndex;

     //3ÿ����Ŀǰ��ע��Ǯ�� 
    public ArrayList<Integer> bets;   //getBets

    //4 ��־��Ϣ�����嶨����BetInfo.java
    public ArrayList<BetInfo> logs; //table.myBet.betLog(.get(size()-1))

    //5 ��Ծ��ң�����û�����Ƶ����
    public ArrayList<Integer> status; //index

    //public boolean checkOrFollow;  // 6 ���ƻ����
	public boolean add;
	public boolean follow;
	public boolean check;
	public boolean abort;
    
    public boolean canAddChips;
    
    public ArrayList<Card> private_cards;  //7���� ����whoseTurn�� table
    public ArrayList<Card> public_cards;  //8������
	//table.myDeal.balabalbaa
	public int maxChips; //9������
	
	public int[] playerMoney;
	public int[] moneyChange;
	
	//��ע��Ҫ
	public int addMin;
	public int addMax;*/
	
    //public boolean fullTrans;  //��ȫ��������? 
    //false�� �����û�      true: ����AI������������û���Ҫ���?

     // 1 �������¼�,����Ǹ�TexasPlayerController�õ�
    public int whatHappen;   //ͬEvent�е�ö��ֵ //had
    
     // 1�ڼ��֣���TexasAIController��
    public int round;      //table.whichTurn

    //2 �ֵ��ĸ��û�,intֵ�����������������
    public int whoseTurn;	//event
    //2 �ĸ�����Ϣ�������ֵ�����     
    public boolean isYourTurn;  //gh-self
    
    //05172329
    public int playerIndex;

      


    //4 ��־��Ϣ�����嶨����BetInfo.java
    public ArrayList<BetInfo> logs; //table.myBet.betLog(.get(size()-1))

    //5 ��Ծ��ң�����û�����Ƶ����
    public ArrayList<Integer> status; //index


    //public boolean checkOrFollow;  // 6 ���ƻ����
	public boolean add;
	public boolean follow;
	public boolean check;
	public boolean abort;
    

    
    public ArrayList<Card> private_cards;  //7���� ����whoseTurn�� table
    public ArrayList<Card> public_cards;  //8������


	//public int maxChips; //9������ X��Ҫ��

	public int addMin;  //������Ҫ��ע���� 
	public int addMax; //�����Ҫ��ע���� 
	public int followTo; //�����ע��Ҫ������

	//ÿ����Ŀǰ��ע��Ǯ��
	public ArrayList<Integer> bets;   //getBets  60 70
	public int[] playerMoney; 
	public int[] moneyChange;
	
	boolean isSingle; //trueΪ������falseΪ����
	
	public int[] winNumber;
	public ArrayList<ArrayList<Card>> allCards;
	
	
	
	public TexasMessage(int whatHappen, int round, 
			int whoseTurn, boolean isYourTurn, int playerIndex,
			ArrayList<BetInfo> logs,ArrayList<Integer> status, 
			boolean add, boolean follow, boolean check, boolean abort,  
			ArrayList<Card> prc, ArrayList<Card> puc,
			int addMin,int addMax,int followTo,
			ArrayList<Integer> bets, int[] playerMoney,int[] moneyChange,
			boolean isSingle, int[] winNumber, ArrayList<ArrayList<Card>> allCards
	){
	//public TexasMessage(int whatHappen, int round, int whoseTurn, boolean isYourTurn, ArrayList<Integer> bets, ArrayList<BetInfo> logs,ArrayList<Integer> status, boolean cof, boolean cac, ArrayList<Card> prc, ArrayList<Card> puc,int maxChips,int[] moneyChange){
		//this.fullTrans = fullTrans;
		this.whatHappen = whatHappen;
		this.round = round;
		
		this.whoseTurn = whoseTurn;
		this.isYourTurn = isYourTurn;
		this.playerIndex = playerIndex;
		
		
		this.logs = logs;
		this.status = status;
		
		this.add = add;
		this.follow = follow;
		this.check = check;
		this.abort = abort;
		
		
		//this.canAddChips = cac;
		this.private_cards = prc;
		this.public_cards = puc;
		//this.maxChips = maxChips;
		
		this.addMin = addMin;
		this.addMax = addMax;
		this.followTo = followTo;
		
		this.bets = bets;
		this.playerMoney = playerMoney;
		this.moneyChange = moneyChange;
		
		this.isSingle = isSingle;
		
		this.winNumber = winNumber;
		this.allCards = allCards;
		

	}
    
}