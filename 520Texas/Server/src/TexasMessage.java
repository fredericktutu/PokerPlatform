import java.util.ArrayList;

/*
msg中应包含的信息:
1. *发生的事件
2. *轮到哪个用户了
3. *目前每个人下注的钱数
4. *增加的日志
5. *当前所有用户的状态
6.a 若轮到你，那么你的可选操作是让牌还是跟牌
7. 手牌
8. 公共牌
其中，1234应该是每次都需要的，567是可选
*/
public class TexasMessage {
    /*public boolean fullTrans;  //是全量传输吗? 
    //false： 传给用户      true: 传给AI、或许断连的用户需要这个?

     // 1 发生的事件,这个是给TexasPlayerController用的
    public int whatHappen;   //同Event中的枚举值 //had
    
     // 1第几轮，给TexasAIController用
    public int round;      //table.whichTurn

    //2 轮到哪个用户,int值代表他在牌桌的序号
    public int whoseTurn;	//event
    //2 的附加信息，代表轮到你了     
    public boolean isYourTurn;  //gh-self
    
    //05172329
    public int playerIndex;

     //3每个人目前下注的钱数 
    public ArrayList<Integer> bets;   //getBets

    //4 日志信息，具体定义在BetInfo.java
    public ArrayList<BetInfo> logs; //table.myBet.betLog(.get(size()-1))

    //5 活跃玩家，就是没有弃牌的玩家
    public ArrayList<Integer> status; //index

    //public boolean checkOrFollow;  // 6 让牌或跟牌
	public boolean add;
	public boolean follow;
	public boolean check;
	public boolean abort;
    
    public boolean canAddChips;
    
    public ArrayList<Card> private_cards;  //7手牌 根据whoseTurn给 table
    public ArrayList<Card> public_cards;  //8公共牌
	//table.myDeal.balabalbaa
	public int maxChips; //9最大筹码
	
	public int[] playerMoney;
	public int[] moneyChange;
	
	//加注需要
	public int addMin;
	public int addMax;*/
	
    //public boolean fullTrans;  //是全量传输吗? 
    //false： 传给用户      true: 传给AI、或许断连的用户需要这个?

     // 1 发生的事件,这个是给TexasPlayerController用的
    public int whatHappen;   //同Event中的枚举值 //had
    
     // 1第几轮，给TexasAIController用
    public int round;      //table.whichTurn

    //2 轮到哪个用户,int值代表他在牌桌的序号
    public int whoseTurn;	//event
    //2 的附加信息，代表轮到你了     
    public boolean isYourTurn;  //gh-self
    
    //05172329
    public int playerIndex;

      


    //4 日志信息，具体定义在BetInfo.java
    public ArrayList<BetInfo> logs; //table.myBet.betLog(.get(size()-1))

    //5 活跃玩家，就是没有弃牌的玩家
    public ArrayList<Integer> status; //index


    //public boolean checkOrFollow;  // 6 让牌或跟牌
	public boolean add;
	public boolean follow;
	public boolean check;
	public boolean abort;
    

    
    public ArrayList<Card> private_cards;  //7手牌 根据whoseTurn给 table
    public ArrayList<Card> public_cards;  //8公共牌


	//public int maxChips; //9最大筹码 X不要了

	public int addMin;  //最少需要下注多少 
	public int addMax; //最多需要下注多少 
	public int followTo; //如果跟注需要跟多少

	//每个人目前下注的钱数
	public ArrayList<Integer> bets;   //getBets  60 70
	public int[] playerMoney; 
	public int[] moneyChange;
	
	boolean isSingle; //true为单机，false为联机
	
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