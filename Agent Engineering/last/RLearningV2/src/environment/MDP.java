package environment;
/**
 * MDP
 * 
 * 異なる状態（状況）を考慮したMDPのクラス
 * 
 * MDPは環境に属する付与情報とし、
 * エージェントの学習時の状態は、エージェント内に持つものとするため、
 * 一般的なMDPの状態を、ここでは、マスとする。
 * 
 * @author shiro@info.kindai.ac.jp
 * @version 1.0, 09/02/2010
 */

/**
 * @author たかた
 *
 */
public class MDP {

	private String	condition;		//　環境状況（状態）
	public String getCondition(){
		return condition;
	}
	
	private String	square;			// 行為前のマスの名前
	public String getSquare(){
		return square;
	}

	private String	action;			// 行為
	public String getAction(){
		return action;
	}
	
	private String	nextSquare;		// 行為後のマスの名前
	public String getNextSquare(){
		return nextSquare;
	}
	
	private Double	probability;	// 遷移確率
	public Double getprobability(){
		return probability;
	}
	
	private int		reward;			// 報酬
	public int getReward(){
		return reward;
	}

	public MDP(String mdpText) {

		String[] mdpItems = mdpText.split(",");
		
		condition = mdpItems[0];
		square = mdpItems[1];
		action = mdpItems[2];
		nextSquare = mdpItems[3];
		probability = Double.valueOf(mdpItems[4]);
		reward = Integer.valueOf(mdpItems[5]);
	}
	

	public void displayMDP(){

		System.out.printf("条件：%s 現マス：%S 行為：%s 次のマス：%s 遷移確率：%5.4f 報酬：%d\n",
				condition, square, action, nextSquare, probability, reward);		
	}
	
}
