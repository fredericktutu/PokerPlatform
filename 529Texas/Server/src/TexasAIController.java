

import java.util.ArrayList;
import java.util.Random;
/*
德州扑克AI控制器类
用于连接玩家和游戏助理
当游戏助理告诉该控制器牌桌的变化，且轮到该控制器，该控制器将计算出策略

*/
public class TexasAIController{
    private Player player; //玩家
    private int difficulty;  //难度 1 简单   2 中等    3难
	private TexasGameHelper helper;
    public TexasAIController(Player player, int dif) {
        this.player = player;
        this.difficulty = dif;
    }



    /*
    事件句柄
    由于是AI控制器类，所以只会被自己的update()方法调用
    当该控制器被调用update()方法，我们会经过一系列的计算，得出一个策略
    而调用下列的方法就代表选择了某一策略
    句柄由平台逻辑实现，AI部分只需调用
    */
    //加注
    public void handler_add_bet(int addTo) {
      System.out.println("add bet");
    helper = player.texasGameHelper;
        helper.action_add_bet(player, addTo);
    }

    //跟注
    public void handler_follow_bet(int followTo){
      System.out.println("follow bet");
    helper = player.texasGameHelper;
        helper.action_follow_bet(player, followTo);
    }
    //弃牌/盖牌
    public void handler_abort_bet() {
      System.out.println("abort bet");
      helper = player.texasGameHelper;
        helper.action_abort_bet(player);
    }
    //让牌
    public void handler_check() {
      System.out.println("check");
		helper = player.texasGameHelper;
        helper.action_check(player);
    }

    //准备好
    public void handler_ready() {
		helper = player.texasGameHelper;
        helper.action_ready(player);
    }


    //会由游戏助理类调用，msg中会传进来需要的所有信息
    //如果轮到这个AI进行操作，那么：
    //然后update方法将计算出一个策略，并选择调用上面的一个句柄、
    //否则update方法不做任何事
    public void update(TexasMessage msg) {
		if(msg.whatHappen == 3) {
			handler_ready();
			return;
		}
		if(!msg.isYourTurn)return;
        switch(this.difficulty) { //根据不同难度选择不同策略
            case 1: //简单
                strategy_easy(msg);
                break;
            case 2: //中等
                strategy_medium(msg);
                break;
            case 3: //困难
                strategy_hard(msg);
        }
    }

    public void strategy_easy(TexasMessage msg) {
		      System.out.println("into strategy, round" + msg.round);
          int mybet=msg.playerMoney[msg.playerIndex]-msg.bets.get(msg.playerIndex);
		      int minbet=getminbet(msg);
		      int followTo = msg.followTo;
          if(msg.round==1)
          {
            ArrayList<Card> currentcard=msg.private_cards;
	          Card card1=currentcard.get(0);
	          Card card2=currentcard.get(1);
            if((card1.face==card2.face)&&card1.face>=10)
            {
              int diff=mybet-minbet;
              if(msg.add&&diff>0)
              {
                handler_add_bet((int)followTo+diff/20);
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else if(card1.face<6&&card2.face<6&&card1.face!=card2.face&&card1.suit!=card2.suit)
            {
              int diff=mybet-minbet;
              if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else
            {
              int diff=mybet-minbet;
              if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
          }
          else if(msg.round!=1)
          {
            ArrayList<Card> currentcard=(ArrayList<Card>)msg.private_cards.clone();
            currentcard.addAll(msg.public_cards);
            int diff=mybet-minbet;
            if(is_straightflush(currentcard))
            {
              if(msg.add&&diff>0)
              {
                handler_add_bet((int)followTo+diff/2);
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else if(is_fourofakind(currentcard))
            {
              if(msg.add&&diff>0)
              {
                handler_add_bet((int)followTo+diff/3);
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else if(is_fullhouse(currentcard))
            {
              if(msg.add&&diff>0)
              {
                handler_add_bet((int)followTo+diff/4);
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else if(is_flush(currentcard))
            {
              if(msg.add&&diff>0)
              {
                handler_add_bet((int)followTo+diff/5);
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else if(is_straight(currentcard))
            {
              if(msg.add&&diff>0)
              {
                handler_add_bet((int)followTo+diff/8);
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else if(msg.check)
              {
                handler_check();
              }
              else
              {
                handler_abort_bet();
              }
            }
            else
            {
              if(msg.check)
              {
                handler_check();
              }
              else if(msg.follow)
              {
                handler_follow_bet(followTo);
              }
              else
              {
                handler_abort_bet();
              }
            }
          }
    }

    public void strategy_medium(TexasMessage msg) {
      switch (msg.round) {
      case 1:
        preflop(msg);
        break;
      case 2:
      case 3:
      case 4:
        medium_after(msg);
      }
    }

    private void medium_test(TexasMessage msg)
    {
      if(msg.check)
      {
        handler_check();
      }
      else if(msg.abort)
      {
        handler_abort_bet();
      }
    }

    private void preflop(TexasMessage msg) {
      Card card1 = msg.private_cards.get(0);
      Card card2 = msg.private_cards.get(1);

      System.out.println("into strategy, round" + msg.round);
      int remain_bet=msg.playerMoney[msg.playerIndex]-msg.bets.get(msg.playerIndex);
      int num_player = msg.status.size();

      // consider position first
      if (msg.playerIndex < num_player/3) // early position, bad position
      {
        if (card1.face >= 10 && card2.face >= 10 && card1.face == card2.face)
        {
          int limit=600;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face == 0 && card2.face == 12) || (card2.face == 0 && card1.face == 12))
        {
          int limit=800;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face == 0 && card2.face == 11 && card2.suit == 4)
            && (card2.face == 0 && card1.face == 11 && card1.suit == 4))
        {
          int limit=900;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else
        {
          int limit=400;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
      }
      else if (msg.playerIndex < num_player * 2 / 3) // middle position, good position
      {
        if (card1.face >= 10 && card2.face >= 10 && card1.face == card2.face)
        {
          int limit=700;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face == 0 && card2.face == 12) || (card2.face == 0 && card1.face == 12))
        {
          int limit=800;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }

        }
        else if ((card1.face == 0 && card2.face == 11 && card2.suit == 4)
            && (card2.face == 0 && card1.face == 11 && card1.suit == 4))
        {
          int limit=900;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face >= 12) && card2.face >= 11)
        {
          int limit=1000;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if (card1.face >= 8 && card2.face >= 8 && card1.suit == card2.suit)
        {
          int limit=900;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else
        {
          int limit=400;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
      }
      else // later postion, best position
      {
        if (card1.face >= 10 && card2.face >= 10 && card1.face == card2.face)
        {
          int limit=800;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face == 0 && card2.face == 12) || (card2.face == 0 && card1.face == 12))
        {
          int limit=900;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face == 0 && card2.face == 11 && card2.suit == 4)
            && (card2.face == 0 && card1.face == 11 && card1.suit == 4))
        {
          int limit=800;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if ((card1.face >= 11) && card2.face >= 10)
        {
          int limit=700;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if (card1.face >= 7 && card2.face >= 7 && card1.suit == card2.suit)
        {
          int limit=600;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.followTo);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else if (card1.face >= 9 || card2.face >= 9)
        {
          int limit=600;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
        else
        {
          int limit=500;
          if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
          {
            if(msg.add)
            {
              handler_add_bet((int)(msg.followTo+limit)/2);
            }

          }
          else if(msg.followTo>=limit-10&&remain_bet>msg.followTo) // has enough money but reaches limit
          {
            double seed=Math.random();
            if(msg.check)
            {
              handler_check();
            }
            else if(seed>0.5&&msg.follow&&remain_bet>msg.followTo)
            {
              handler_follow_bet(msg.followTo);
            }
            else
            {
              handler_abort_bet();
            }
          }
          else if(msg.followTo>remain_bet||remain_bet<limit) // has no enough money
          {
            if(msg.abort)
            {
              handler_abort_bet();
            }
            else if(msg.addMin==msg.addMax)
            {
              handler_follow_bet(msg.addMin);
              //handler_add_bet(msg.addMin);
            }
          }
        }
      }
    }
    private void default_action(TexasMessage msg)
    {
      if(msg.follow)
      {
        handler_follow_bet(msg.followTo);
      }
      else if(msg.check)
      {
        handler_check();
      }
      else if(msg.add)
      {
        handler_add_bet(msg.addMin);
      }
      else if(msg.abort)
      {
        handler_abort_bet();
      }
    }

    private void m_action(TexasMessage msg, int limit, double prob)
    {
      int remain_bet=msg.playerMoney[msg.playerIndex];
      if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
      {
        if(msg.add)
        {
          handler_add_bet(Math.max(msg.addMin,Math.min((int)((msg.followTo+limit)/2)/100*100,msg.addMax)));
        }
        else
        {
          default_action(msg);
        }

      }
      else if(msg.followTo>=limit-10&&remain_bet>=msg.followTo) // has enough money but reaches limit
      {
        double seed=Math.random();
        if(msg.check)
        {
          handler_check();
        }
        else if(seed>prob&&msg.follow&&remain_bet>msg.followTo)
        {
          handler_follow_bet(msg.followTo);
        }
        else if(msg.abort)
        {
          handler_abort_bet();
        }
        else
        {
          default_action(msg);
        }
      }
      else if(msg.followTo>=remain_bet||remain_bet<limit) // has no enough money
      {
        if(msg.check)
        {
          handler_check();
        }
        else if(msg.addMin==msg.addMax&&msg.follow)
        {
          handler_follow_bet(msg.followTo);
          //handler_add_bet(msg.addMin);
        }
        else if(msg.abort)
        {
          handler_abort_bet();
        }
        else
        {
          default_action(msg);
        }
      }
      else
      {
        default_action(msg);
      }
    }

    private void medium_after(TexasMessage msg)
    {
      	 ArrayList<Card> public_cards=msg.public_cards;
      	 ArrayList<Card> private_cards=msg.private_cards;
          @SuppressWarnings("unchecked")
  		      ArrayList<Card> allcard=(ArrayList<Card>)msg.private_cards.clone();
          allcard.addAll(msg.public_cards);
      	   int minbet=getminbet(msg);

  		System.out.println("into strategy, round" + msg.round);
          int mybet=msg.playerMoney[msg.playerIndex]-msg.bets.get(msg.playerIndex);
  		int followTo = msg.followTo;
  				int diff=mybet-minbet;
      double seed=Math.random();
  		//playing  a strong hand
  		if(msg.playerIndex < msg.status.size()/2)
  		{
        System.out.println("early acting");
  			if(is_straightflush(allcard))
  			{
          m_action(msg,9000,0.1);
  			}
  			else if(is_fourofakind(allcard))
  			{
          m_action(msg,8000,0.1);
  			}
  			else if(is_fullhouse(allcard))
  			{
          m_action(msg,6000,0.1);
  			}
  			else if(is_flush(allcard))
  			{
          m_action(msg,6000,0.1);
  			}
  			else if(is_straight(allcard))
  			{
          m_action(msg,5000,0.1);
  			}
  			else if(haspair(allcard)==2&&haswhatpair(allcard)>9) // has top two pair
  			{
          m_action(msg,5000,0.1);
  			}
  		// playing a medicore hand
  		// high end
  			else if(diff_straightflush(allcard)==1)
  			{
          m_action(msg,3000,0.2);
  			}
  			else if(diff_fourofakind(allcard)==1)
  			{
          m_action(msg,3000,0.3);
  			}
  			else if(diff_fullhouse(allcard)==1)
  			{
          m_action(msg,3000,0.4);
  			}
  			else if(diff_flush(allcard)==1)
  			{
          m_action(msg,2000,0.5);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&hasgoodkicker(private_cards))// has top pair and good kicker
  			{
          m_action(msg,2000,0.7);
  			}
  			// low end
  			else if(haswhatpair(allcard)<10&&haswhatpair(allcard)>4&&haspair(allcard)==1)   // middle pair
  			{
          m_action(msg,3000,0.7);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&!hasgoodkicker(private_cards)) // top pair, weak kicker
  			{
          m_action(msg,3000,0.7);
  			}
  			else if(diff_straightflush(allcard)==2)
  			{
          m_action(msg,5000,0.5);
  			}
  			else if(diff_fourofakind(allcard)==2||diff_fullhouse(allcard)==2||diff_flush(allcard)==2)
  			{
          m_action(msg,3000,0.9);
  			}

  			// playing a weak hand
  			else if(haspair(allcard)==0)// has no pair, fold unless i can see the turn card for free
  			{
          m_action(msg,2000,0.8);
  			}
  			else
  			{
          m_action(msg,4000,0.5);
  			}
  		}
  		else // acting late, in better positions
  		{
        System.out.println("late acting");
  			if(is_straightflush(allcard))
  			{
          m_action(msg,9000,0.01);
  			}
  			else if(is_fourofakind(allcard))
  			{
          m_action(msg,9000,0.01);
  			}
  			else if(is_fullhouse(allcard))
  			{
          m_action(msg,9000,0.1);
  			}
  			else if(is_flush(allcard))
  			{
          m_action(msg,9000,0.2);
  			}
  			else if(is_straight(allcard))
  			{
          m_action(msg,8000,0.2);
  			}
  			else if(haspair(allcard)==2&&haswhatpair(allcard)>9) // has top two pair
  			{
          m_action(msg,6000,0.3);
  			}
  		// playing a medicore hand
  		// high end
  			else if(diff_straightflush(allcard)==1)
  			{
          m_action(msg,5000,0.3);
  			}
  			else if(diff_fourofakind(allcard)==1)
  			{
          m_action(msg,5000,0.4);
  			}
  			else if(diff_fullhouse(allcard)==1)
  			{
          m_action(msg,5000,0.4);
  			}
  			else if(diff_flush(allcard)==1)
  			{
          m_action(msg,5000,0.4);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&hasgoodkicker(private_cards))// has top pair and good kicker
  			{
          m_action(msg,5000,0.4);
  			}
  			// low end
  			else if(haswhatpair(allcard)<10&&haswhatpair(allcard)>4&&haspair(allcard)==1)   // middle pair
  			{
          m_action(msg,5000,0.5);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&!hasgoodkicker(private_cards)) // top pair, weak kicker
  			{
          m_action(msg,5000,0.6);
  			}
  			else if(diff_straightflush(allcard)==2)
  			{
          m_action(msg,4000,0.5);
  			}
  			else if(diff_fourofakind(allcard)==2||diff_fullhouse(allcard)==2||diff_flush(allcard)==2)
  			{
          m_action(msg,4000,0.7);
  			}

  			// playing a weak hand
  			else if(haspair(allcard)==0)// has no pair, fold unless i can see the turn card for free
  			{
          m_action(msg,3000,0.9);
  			}
  			else
  			{
          m_action(msg,3000,0.5);
  			}
  		}
    }

      private boolean acceptable(TexasMessage msg, double prob)
      {
        ArrayList<BetInfo> mylog=msg.logs;
        boolean is=true;
        for(int i=mylog.size()-1;i<mylog.size();i--)
        {
          if(mylog.get(i).money>=mylog.get(i-1).money * 15* prob && mylog.get(i).round==mylog.get(i-1).round&&mylog.get(i).round==msg.round)
          {
            is=false;
          }
        }
        return is;
      }


      private int haswhatpair(ArrayList<Card> pokers)
    	{
    		int face=0;
    		ArrayList<Card> sortedcards = sortbyface(pokers);
    		for(int i=0;i<sortedcards.size()-1;i++)
    		{
    			if(sortedcards.get(i).face==sortedcards.get(i+1).face)
    			{
    				face=sortedcards.get(i).face;
    				break;
    			}
    		}
    		return face;
    	}

      private boolean hasgoodkicker(ArrayList<Card> pokers)
    	{
    		Card card1=pokers.get(0);
    		Card card2=pokers.get(1);
    		if(card1.face>=10&&card2.face>=10)
    		{
    			return true;
    		}
    		else
    			return false;
    	}


    public void strategy_hard(TexasMessage msg) {
      switch (msg.round) {
      case 1:
        preflop(msg);
        break;
      case 2:
        medium_after(msg);
        break;
        //hard_after(msg);
      case 3:
      case 4:
        hard_after(msg);
      }
    }

    private void h_action(TexasMessage msg, int limit, double prob,double bluff, int many)
    {
      //param:
      // limit:
      int remain_bet=msg.playerMoney[msg.playerIndex];
      if(msg.followTo<limit-10&&remain_bet>limit) // has enough money and below limit
      {
        if(msg.add)
        {
          handler_add_bet(Math.max(msg.addMin,Math.min((int)((msg.followTo+limit)/2)/100*100,msg.addMax)));
        }
        else
        {
          default_action(msg);
        }

      }
      else if(msg.followTo>=limit-10&&remain_bet>=msg.followTo) // has enough money but reaches limit
      {
        double seed=Math.random();
        double b_prob=Math.random();
        if(msg.check)
        {
          handler_check();
        }
        else if(seed>prob&&msg.follow&&remain_bet>msg.followTo)
        {
          handler_follow_bet(msg.followTo);
        }
        else if(b_prob>bluff&&msg.follow&&remain_bet>msg.followTo+100*many)
        {
          handler_add_bet(Math.max(msg.addMin,Math.min((int)((msg.followTo+1000*many))/100*100,msg.addMax)));
        }
        else if(msg.abort)
        {
          handler_abort_bet();
        }
        else
        {
          default_action(msg);
        }
      }
      else if(msg.followTo>=remain_bet||remain_bet<limit) // has no enough money
      {
        if(msg.check)
        {
          handler_check();
        }
        else if(msg.addMin==msg.addMax&&msg.follow)
        {
          handler_follow_bet(msg.followTo);
          //handler_add_bet(msg.addMin);
        }
        else if(msg.abort)
        {
          handler_abort_bet();
        }
        else
        {
          default_action(msg);
        }
      }
      else
      {
        default_action(msg);
      }
    }


    private void hard_after(TexasMessage msg)
    {
      ArrayList<Card> public_cards=msg.public_cards;
      ArrayList<Card> private_cards=msg.private_cards;
      @SuppressWarnings("unchecked")
  		ArrayList<Card> allcard=(ArrayList<Card>)msg.private_cards.clone();
      allcard.addAll(msg.public_cards);
      int minbet=getminbet(msg);

  		System.out.println("into strategy, round" + msg.round);
      int mybet=msg.playerMoney[msg.playerIndex]-msg.bets.get(msg.playerIndex);
  		int followTo = msg.followTo;
  		int diff=mybet-minbet;
      double seed=Math.random();
  		//playing  a strong hand
  		if(msg.playerIndex < msg.status.size()/2)
  		{
        // h_action(TexasMessage msg, int limit, double prob,double bluff, int many)
  			if(is_straightflush(allcard))
  			{
          h_action(msg,10000,0.01,0.9,8);
  			}
  			else if(is_fourofakind(allcard))
  			{
          h_action(msg,9000,0.01,0.9,6);
  			}
  			else if(is_fullhouse(allcard))
  			{
          h_action(msg,8000,0.01,0.9,6);
  			}
  			else if(is_flush(allcard))
  			{
          h_action(msg,8000,0.1,0.8,5);
  			}
  			else if(is_straight(allcard))
  			{
          h_action(msg,7000,0.1,0.6,5);
  			}
  			else if(haspair(allcard)==2&&haswhatpair(allcard)>9) // has top two pair
  			{
          h_action(msg,6000,0.3,0.6,4);
  			}
  		// playing a medicore hand
  		// high end
  			else if(diff_straightflush(allcard)==1)
  			{
          h_action(msg,6000,0.3,0.6,5);
  			}
  			else if(diff_fourofakind(allcard)==1)
  			{
          h_action(msg,5000,0.4,0.6,5);
  			}
  			else if(diff_fullhouse(allcard)==1)
  			{
          h_action(msg,6000,0.3,0.6,5);
  			}
  			else if(diff_flush(allcard)==1)
  			{
          h_action(msg,6000,0.3,0.6,5);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&hasgoodkicker(private_cards))// has top pair and good kicker
  			{
          h_action(msg,5000,0.4,0.5,6);
  			}
  			// low end
  			else if(haswhatpair(allcard)<10&&haswhatpair(allcard)>4&&haspair(allcard)==1)   // middle pair
  			{
          h_action(msg,4000,0.7,0.4,10);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&!hasgoodkicker(private_cards)) // top pair, weak kicker
  			{
          h_action(msg,4000,0.7,0.4,10);
  			}
  			else if(diff_straightflush(allcard)==2)
  			{
          h_action(msg,4000,0.7,0.6,20);
  			}
  			else if(diff_fourofakind(allcard)==2||diff_fullhouse(allcard)==2||diff_flush(allcard)==2)
  			{
          h_action(msg,4000,0.8,0.6,20);
  			}

  			// playing a weak hand
  			else if(haspair(allcard)==0)// has no pair, fold unless i can see the turn card for free
  			{
          h_action(msg,3000,0.9,0.6,20);
  			}
  			else
  			{
          h_action(msg,4000,0.5,0.5,10);
  			}
  		}

  		else // acting late, in better positions
  		{
        System.out.println("late acting");
  			if(is_straightflush(allcard))
  			{
          h_action(msg,15000,0.01,1,10);
  			}
  			else if(is_fourofakind(allcard))
  			{
          h_action(msg,13000,0.01,1,10);
  			}
  			else if(is_fullhouse(allcard))
  			{
          h_action(msg,13000,0.01,1,9);
  			}
  			else if(is_flush(allcard))
  			{
          h_action(msg,12000,0.01,1,8);
  			}
  			else if(is_straight(allcard))
  			{
          h_action(msg,10000,0.1,0.8,6);
  			}
  			else if(haspair(allcard)==2&&haswhatpair(allcard)>9) // has top two pair
  			{
          h_action(msg,8000,0.3,0.7,6);
  			}
  		// playing a medicore hand
  		// high end
  			else if(diff_straightflush(allcard)==1)
  			{
          h_action(msg,8000,0.3,0.7,5);
  			}
  			else if(diff_fourofakind(allcard)==1)
  			{
          h_action(msg,8000,0.3,0.7,4);
  			}
  			else if(diff_fullhouse(allcard)==1)
  			{
          h_action(msg,7000,0.3,0.7,4);
  			}
  			else if(diff_flush(allcard)==1)
  			{
          h_action(msg,7000,0.3,0.7,3);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&hasgoodkicker(private_cards))// has top pair and good kicker
  			{
          h_action(msg,6000,0.4,0.7,2);
  			}
  			// low end
  			else if(haswhatpair(allcard)<10&&haswhatpair(allcard)>4&&haspair(allcard)==1)   // middle pair
  			{
          h_action(msg,6000,0.4,0.7,4);
  			}
  			else if(haswhatpair(allcard)>=10&&haspair(allcard)==1&&!hasgoodkicker(private_cards)) // top pair, weak kicker
  			{
          h_action(msg,6000,0.6,0.7,5);
  			}
  			else if(diff_straightflush(allcard)==2)
  			{
          h_action(msg,6000,0.6,0.7,2);
  			}
  			else if(diff_fourofakind(allcard)==2||diff_fullhouse(allcard)==2||diff_flush(allcard)==2)
  			{
          h_action(msg,6000,0.7,0.6,4);
  			}

  			// playing a weak hand
  			else if(haspair(allcard)==0)// has no pair, fold unless i can see the turn card for free
  			{
          h_action(msg,5000,0.9,0.5,8);
  			}
  			else
  			{
          h_action(msg,7000,0.7,0.8,2);
  			}
  		}
    }


    public void update(RoomMessage msg) {
        //do nothing
        return;
    }

    private ArrayList<Card> sortbyface(ArrayList<Card> pokers)
    {
    	Card tempInt;
    	for (int i = 1; i < pokers.size(); i++) {
    		for (int j = i; j > 0 && (pokers.get(j).face < pokers.get(j - 1).face); j--) {
    			tempInt = pokers.remove(j);
    			pokers.add(j - 1, tempInt);
    		}
    	}
    		return pokers;
    }

    private ArrayList<Card> sortbysuit(ArrayList<Card> pokers)
    {
    	Card tempInt;
    	for (int i = 1; i < pokers.size(); i++) {
    		for (int j = i; j > 0 && (pokers.get(j).suit < pokers.get(j - 1).suit); j--) {
    			tempInt = pokers.remove(j);
    			pokers.add(j - 1, tempInt);
    		}
    	}
    		return pokers;
    }


    public boolean is_straightflush(ArrayList<Card> pokers)
    {
    	boolean is=false;
    	if(pokers.size()==5)
    	{
    		is=is_straight(pokers)&&is_flush(pokers);
    	}
    	else if(pokers.size()==6)
    	{
    		Card tempcard;
    		for(int i=0;i<6;i++)
    		{
    			tempcard = pokers.remove(i);
    			if(is_straight(pokers)&&is_flush(pokers))
    			{
    				is=true;
    				break;
    			}
    			pokers.add(i, tempcard);
    		}
    	}
    	else if(pokers.size()==7)
    	{
    		Card tempcard1;
    		Card tempcard2;
    		for(int i=0;i<7;i++)
    		{
    			tempcard1=pokers.remove(i);
    			for(int j=0;j<6;j++)
    			{
    				tempcard2=pokers.remove(j);
        			if(is_straight(pokers)&&is_flush(pokers))
        			{
        				is=true;
        				return is;
        			}
        			pokers.add(j, tempcard2);
    			}
    			pokers.add(i,tempcard1);
    		}
    	}
    	return is;
    }

    public boolean is_fourofakind(ArrayList<Card> pokers)
    {
    	pokers=sortbyface(pokers);
    	boolean is=false;
    	if(pokers.size()==5) // first round, only got 5 cards
    	{
    		for(int i=0;i<2;i++)
    		{
    			if(pokers.get(i).face==pokers.get(i+1).face&&pokers.get(i+1).face==pokers.get(i+2).face&&pokers.get(i+2).face==pokers.get(i+3).face)
    				{
    					is=true;
    					break;
    				}
    		}
    	}
    	else if(pokers.size()==6) // second round got 6 cards
    	{
    		for(int i=0;i<3;i++)
    		{
    			if(pokers.get(i).face==pokers.get(i+1).face&&pokers.get(i+1).face==pokers.get(i+2).face&&pokers.get(i+2).face==pokers.get(i+3).face)
    				{
    					is=true;
    					break;
    				}
    		}
    	}
    	else if(pokers.size()==7) // second round got 7 cards
    	{
    		for(int i=0;i<4;i++)
    		{
    			if(pokers.get(i).face==pokers.get(i+1).face&&pokers.get(i+1).face==pokers.get(i+2).face&&pokers.get(i+2).face==pokers.get(i+3).face)
    				{
    					is=true;
    					break;
    				}
    		}
    	}
		return is;
    }

    public boolean is_fullhouse(ArrayList<Card> pokers)
    {
    	boolean is=false;
    	ArrayList<Card> sortedpokers=sortbyface(pokers);
    	if(pokers.size()==5) // first round, only got 5 cards
    	{
    		if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(1).face==sortedpokers.get(2).face&&sortedpokers.get(3).face==sortedpokers.get(4).face)
    			is=true;
    		else if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(2).face==sortedpokers.get(3).face&&sortedpokers.get(3).face==sortedpokers.get(4).face)
    			is=true;
    	}
    	else if(pokers.size()==6) // second round got 6 cards
    	{
    		for(int i=0;i<6;i++)
    		{
    			Card tempcard=sortedpokers.remove(i);
        		if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(1).face==sortedpokers.get(2).face&&sortedpokers.get(3).face==sortedpokers.get(4).face)
        			is=true;
        		else if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(2).face==sortedpokers.get(3).face&&sortedpokers.get(3).face==sortedpokers.get(4).face)
        			is=true;
        		sortedpokers.add(i, tempcard);
    		}
    	}
    	else if(pokers.size()==7) // second round got 7 cards
    	{
    		for(int i=0;i<7;i++)
    		{
    			Card tempcard1=sortedpokers.remove(i);
    			for(int j=0;j<6;j++)
    			{
    				Card tempcard2=sortedpokers.remove(j);
            		if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(1).face==sortedpokers.get(2).face&&sortedpokers.get(3).face==sortedpokers.get(4).face)
            			is=true;
            		else if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(2).face==sortedpokers.get(3).face&&sortedpokers.get(3).face==sortedpokers.get(4).face)
            			is=true;
            		sortedpokers.add(j, tempcard2);
    			}
    			sortedpokers.add(i,tempcard1);
    		}
    	}
    	return is;
    }

    public boolean is_flush(ArrayList<Card> pokers)
    {
    	boolean is=false;
    	if(pokers.size()==5)
    	{
        		if(pokers.get(0).suit==pokers.get(1).suit&&pokers.get(1).suit==pokers.get(2).suit&&pokers.get(2).suit==pokers.get(3).suit&&pokers.get(3).suit==pokers.get(4).suit)
        			is=true;
    	}
    	else if(pokers.size()==6)
    	{
    		ArrayList<Card> temp=sortbysuit(pokers);
    		for(int i=0;i<2;i++)
    		{
    			if(temp.get(i).suit==temp.get(i+1).suit&&temp.get(i+1).suit==temp.get(i+2).suit&&temp.get(i+2).suit==temp.get(i+3).suit&&temp.get(i+3).suit==temp.get(i+4).suit)
    			{
    				is=true;
    			}
    		}
    	}
    	else if(pokers.size()==7)
    	{
    		ArrayList<Card> temp=sortbysuit(pokers);
    		for(int i=0;i<3;i++)
    		{
    			if(temp.get(i).suit==temp.get(i+1).suit&&temp.get(i+1).suit==temp.get(i+2).suit&&temp.get(i+2).suit==temp.get(i+3).suit&&temp.get(i+3).suit==temp.get(i+4).suit)
    			{
    				is=true;
    			}
    		}
    	}
    	return is;
    }

    public boolean is_straight(ArrayList<Card> pokers)
    {
    	boolean is=false;
		ArrayList<Card> sortedpokers=sortbyface(pokers);
    	if(pokers.size()==5) // first round, only got 5 cards
    	{
    		if(sortedpokers.get(0).face!=sortedpokers.get(1).face&&
    				sortedpokers.get(1).face!=sortedpokers.get(2).face&&
    				sortedpokers.get(2).face!=sortedpokers.get(3).face&&
    				sortedpokers.get(3).face!=sortedpokers.get(4).face&&
    				(sortedpokers.get(4).face-sortedpokers.get(0).face)==4)
    		{
    			is=true;
    		}
    	}
    	else if(pokers.size()==6) // second round got 6 cards
    	{
    		for(int i=0;i<6;i++)
    		{
    			ArrayList<Card> temp=(ArrayList<Card>) sortedpokers.clone();
    			temp.remove(i);
        		if(temp.get(0).face!=temp.get(1).face&&
        				temp.get(1).face!=temp.get(2).face&&
        				temp.get(2).face!=temp.get(3).face&&
        				temp.get(3).face!=temp.get(4).face&&
        				(temp.get(4).face-temp.get(0).face)==4)
        		{
        			is=true;
        			break;
        		}
    		}
    	}
    	else if(pokers.size()==7) // second round got 7 cards
    	{
    		for(int i=0;i<7;i++)
    		{
    			ArrayList<Card> temp=(ArrayList<Card>) sortedpokers.clone();
    			temp.remove(i);
    			for(int j=i;j<6;j++)
    			{
    				Card temp1=temp.remove(j);
            		if(temp.get(0).face!=temp.get(1).face&&
            				temp.get(1).face!=temp.get(2).face&&
            				temp.get(2).face!=temp.get(3).face&&
            				temp.get(3).face!=temp.get(4).face&&
            				(temp.get(4).face-temp.get(0).face)==4)
            		{
            			is=true;
            			break;
    			}
            		temp.add(j, temp1);
    		}
    	}
    }
    	return is;
    }

    public int diff_straightflush(ArrayList<Card> pokers){
    	int diff=-1;
    	ArrayList<Card> sortedpokers=sortbyface(pokers);
    	ArrayList<Card> sortedpokers_color=sortbysuit(pokers);
		for(int i=0;i<pokers.size()-3;i++)
		{
			if(sortedpokers_color.get(i).suit==sortedpokers_color.get(i+1).suit&&sortedpokers_color.get(i+1).suit==sortedpokers_color.get(i+2).suit&&
					sortedpokers_color.get(i+2).suit==sortedpokers_color.get(i+3).suit)
			{
				ArrayList<Card> temp=new ArrayList<Card>();
				temp.add(sortedpokers_color.get(i));
				temp.add(sortedpokers_color.get(i+1));
				temp.add(sortedpokers_color.get(i+2));
				temp.add(sortedpokers_color.get(i+3));
				temp=sortbyface(temp);
        		if(temp.get(0).face!=temp.get(1).face&&
        				temp.get(1).face!=temp.get(2).face&&
        				temp.get(2).face!=temp.get(3).face&&
        				((temp.get(3).face-temp.get(0).face==3)||(temp.get(3).face-temp.get(0).face==4)))
        		{
        			diff=1;
        		}
			}
			// lack two cards
    		for(int j=0;j<pokers.size()-2;j++)
    		{
    			if(sortedpokers_color.get(j).suit==sortedpokers_color.get(j+1).suit&&sortedpokers_color.get(j+1).suit==sortedpokers_color.get(j+2).suit)
    			{
    				ArrayList<Card> temp=new ArrayList<Card>();
    				temp.add(sortedpokers_color.get(j));
    				temp.add(sortedpokers_color.get(j+1));
    				temp.add(sortedpokers_color.get(j+2));
    				temp=sortbyface(temp);
            		if(temp.get(0).face!=temp.get(1).face&&
            				temp.get(1).face!=temp.get(2).face&&
            				((temp.get(2).face-temp.get(0).face>1)||(temp.get(2).face-temp.get(0).face<5))&&diff==-1)
            		{
            			diff=2;
            		}
    			}
    		}
		}
    	return diff;
    }

    public int diff_fourofakind(ArrayList<Card> pokers)
    {
		// if there are 3 cards that the same face, then return 1
		// if there are 2 cards that have the same face, then return 2
    	int diff=-1;
    	ArrayList<Card> sortedpokers=sortbyface(pokers);
    	if(pokers.size()==5) // first round, only got 5 cards
    	{
    		for(int i=0;i<3;i++)
    		{
    			if(!is_fourofakind(pokers)&&sortedpokers.get(i).face==sortedpokers.get(i+1).face&&sortedpokers.get(i+1).face==sortedpokers.get(i+2).face)
    				diff=1;
    		}
    		for(int i=0;i<4;i++)
    		{
    			if(!is_fourofakind(pokers)&&sortedpokers.get(i).face==sortedpokers.get(i+1).face&&diff==-1)
    				diff=2;
    		}
    	}
    	else if(pokers.size()==6) // second round got 6 cards
    	{
    		for(int i=0; i<4;i++)
    		{
    			if(!is_fourofakind(pokers)&&sortedpokers.get(i).face==sortedpokers.get(i+1).face&&sortedpokers.get(i+1).face==sortedpokers.get(i+2).face)
    			{
    				diff=1;
    			}
    		}
    		for(int i=0;i<5;i++)
    		{
    			if(!is_fourofakind(pokers)&&sortedpokers.get(i).face==sortedpokers.get(i+1).face&&diff==-1)
    				diff=2;
    		}
    	}
    	else if(pokers.size()==7) // second round got 7 cards
    	{
    		for(int i=0; i<5;i++)
    		{
    			if(!is_fourofakind(pokers)&&sortedpokers.get(i).face==sortedpokers.get(i+1).face&&sortedpokers.get(i+1).face==sortedpokers.get(i+2).face)
    			{
    				diff=1;
    			}
    		}
    		for(int i=0;i<6;i++)
    		{
    			if(!is_fourofakind(pokers)&&sortedpokers.get(i).face==sortedpokers.get(i+1).face&&diff==-1)
    				diff=2;
    		}
    	}
    	return diff;
    }

    public int diff_fullhouse(ArrayList<Card> pokers) {
  		int diff=-1;
      ArrayList<Card> sortedpokers=sortbyface(pokers);
      if(pokers.size()==5)
      {
        if(sortedpokers.get(0).face==sortedpokers.get(1).face&&sortedpokers.get(1).face==sortedpokers.get(2).face&&sortedpokers.get(2).face!=sortedpokers.get(3).face&&sortedpokers.get(3).face!=sortedpokers.get(4).face)
        {
          diff=1;
        }
        else if(!is_fullhouse(sortedpokers)&&haspair(pokers)==2)
        {
          diff=1;
        }
      }
      else if(pokers.size()==6&&!is_fullhouse(sortedpokers))
      {
        for(int i=0;i<4;i++)
        {
          if(sortedpokers.get(i).face==sortedpokers.get(i+1).face&&sortedpokers.get(i+1).face==sortedpokers.get(i+2).face)
          {
            diff=1;
          }
        }
        if(!is_fullhouse(sortedpokers)&&haspair(pokers)==2)
        {
          diff=1;
        }
      }
      else if(pokers.size()==7)
      {
        for(int i=0;i<5;i++)
        {
          if(sortedpokers.get(i).face==sortedpokers.get(i+1).face&&sortedpokers.get(i+1).face==sortedpokers.get(i+2).face)
          {
            diff=1;
          }
        }
        if(!is_fullhouse(sortedpokers)&&haspair(pokers)==2)
        {
          diff=1;
        }
      }
      return diff;
  	}

    public int diff_flush(ArrayList<Card> pokers)
    {
    	int diff=-1;
    	ArrayList<Card> sortedpokers=sortbysuit(pokers);
    	if(pokers.size()==5) // first round, only got 5 cards
    	{
    		// only need to consider suit
    		for(int i=0;i<2;i++)
    		{
    			if(pokers.get(i).suit==pokers.get(i+1).suit&&pokers.get(i+1).suit==pokers.get(i+2).suit&&
    					pokers.get(i+2).suit==pokers.get(i+3).suit&&(!is_flush(pokers)))
    			{
    				diff=1;
    				break;
    			}
    		}
    		for(int i=0;i<3;i++)
    		{
    			if((!is_flush(pokers))&&pokers.get(i).suit==pokers.get(i+1).suit&&pokers.get(i+1).suit==pokers.get(i+2).suit)
    			{
    				if(diff==-1)
    				{
    					diff=2;
    					break;
    				}
    			}
    		}
    	}
    	else if(pokers.size()==6) // second round got 6 cards
    	{
    		for(int i=0;i<3;i++)
    		{
    			if(pokers.get(i).suit==pokers.get(i+1).suit&&pokers.get(i+1).suit==pokers.get(i+2).suit&&
    					pokers.get(i+2).suit==pokers.get(i+3).suit&&(!is_flush(pokers)))
    			{
    				diff=1;
    				break;
    			}
    		}
    		for(int i=0;i<4;i++)
    		{
    			if((!is_flush(pokers))&&pokers.get(i).suit==pokers.get(i+1).suit&&pokers.get(i+1).suit==pokers.get(i+2).suit)
    			{
    				if(diff==-1)
    				{
    					diff=2;
    					break;
    				}
    			}
    		}
    	}
    	else if(pokers.size()==7) // second round got 7 cards
    	{
    		for(int i=0;i<4;i++)
    		{
    			if(pokers.get(i).suit==pokers.get(i+1).suit&&pokers.get(i+1).suit==pokers.get(i+2).suit&&
    					pokers.get(i+2).suit==pokers.get(i+3).suit&&(!is_flush(pokers)))
    			{
    				diff=1;
    				break;
    			}
    		}
    		for(int i=0;i<5;i++)
    		{
    			if((!is_flush(pokers))&&pokers.get(i).suit==pokers.get(i+1).suit&&pokers.get(i+1).suit==pokers.get(i+2).suit)
    			{
    				if(diff==-1)
    				{
    					diff=2;
    					break;
    				}
    			}
    		}
    	}
		return diff;
    }

    private int getminbet(TexasMessage msg)
    {
    	return msg.addMin;
    }

    public int haspair(ArrayList<Card> pokers)
    {
    	int num=0;
    	ArrayList<Card> sortedcards=sortbyface(pokers);
    	for(int i=0;i<sortedcards.size()-1;)
    	{
    		if(sortedcards.get(i).face==sortedcards.get(i+1).face)
    		{
    			num++;
    			i+=2;
    		}
    		else
    		{
    			i+=1;
    		}
    	}
    	return num;
    }



}
