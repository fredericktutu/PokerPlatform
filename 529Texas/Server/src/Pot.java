import java.util.ArrayList;
public class Pot{

	/*
		�������أ������������Ϸָý��ص���ұ��List�뽱�������Ǯ��
	*/

	ArrayList<Integer> potPlayer = new ArrayList<Integer>(); //������������ҵ���ұ��
	int potMoney = 0; //�����غ��е���Ǯ��

	public void addPlayer(int playerNumber){ //�򽱳ز�������м���ĳ��Ŵ�������
		this.potPlayer.add(playerNumber);
	}

	public void addMoney(int money){ //���ý��ؽ��
		this.potMoney = this.potMoney + money;
	}

}