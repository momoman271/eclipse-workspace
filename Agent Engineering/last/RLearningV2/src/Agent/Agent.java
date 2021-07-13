package Agent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import task.RLearning;

import decision.DecisionMaking;
import environment.Environment;
import environment.Grid;
import environment.MDP;
import environment.Square;

/**
 * 
 * Reinforcement-Learning Agent
 * 
 * @author shiro@info.kindai.ac.jp
 * @version 2.0, 07/18/2011
 */

public class Agent {
	/**
	 * 学習率
	 * ただし学習率は Stateクラスにて、各状態におけるQ値の更新回数に反比例して減少する
	 */
	public static Double α = 0.1;

	/**
	 * 割引率
	 */
	public static Double γ = 0.9;

	/**
	 * 学習回数
	 */
	private int cycle;
	public static int MAX_CYCLE = 1000;

	/**
	 * 収束閾値
	 * Q値が収束したと看做す閾値 --> StateクラスのupdateQValue(MDP)
	 */	
	public static final Double thresthold = 0.98;

	/**
	 * ε値
	 */
	public static final Double ε = 0.2;
	
	/**
	 * ボルツマン分布　温度
	 */
	public static final Double TEMPERATURE = 200.0;

	/**
	 * 初期状態
	 */
	private static final String START_STATE = "START";

	/**
	 * 現状況,現状態
	 */
	private static final String GOAL = "GOAL"; 

	/**
	 * 環境アクセス変数
	 */
	private Environment environment;
	
	/**
	 * 意思決定インターフェースへの参照変数
	 */
	protected DecisionMaking decisionMaking;
	
	/**
	 * 現状況,現状態
	 */	
	private String currentCondition;
	private String currentState;
	private State state;
	
	/**
	 * 状況(currentCondition+","+currentState)をKeyとする状態（State）表
	 */
	private static int SQUARES_NUMBER = 400;
	private Map<String,State> states = new HashMap<String,State>(SQUARES_NUMBER);

	
	public Agent(Environment environment){
		this.environment = environment;
		createStates();
	}

	/**
	 * エージェントの内部状態を準備
	 */	
	private void createStates(){

		Map<String,Grid> grids = environment.getGrids();
		Set<String> conditions = grids.keySet();

		for (String condition:conditions){
			Map<String,Square> Squares = (grids.get(condition)).getSquares();
			Set<String> squares = Squares.keySet();
			for (String square:squares){
				states.put(condition+","+square, new State(Squares.get(square)));
			}
		}
	}

	/**
	 * 各状態の方策、Q値の初期化
	 */	
	private void initializeState(){

		Collection<State> States = states.values();
		for (State state:States){
			state.initializeState();
		}
	}

	/**
	 *　Q学習のループ
	 */
	public void Qlearning(){
		
		
		/* エージェントの内部状態を初期化（方策、Q値などの表をゼロクリア） */
		initializeState();

		/**
		 * 指定学習回数だけ学習する
		 */
		for (int cycle = 1; cycle <= MAX_CYCLE ; cycle++) {
			
			/* 環境(Environment)の状況をランダムに更新する。（環境を並行に実行させるべきであるが）*/
			environment.setCondition();
			/* 環境認知を行う。*/
			currentCondition = environment.getCondition();
//			System.out.printf("\n---Cycle:%d 現在の状況：%s\n", cycle, currentCondition);
			
			/* スタート位置につく */
			currentState = START_STATE;

			while(!currentState.equals(GOAL)){
				/* 状態表から現マスに対応する状態のインスタンスを得る */
				state = states.get(currentCondition + "," + currentState);
				/* εグリーディで現状態の意思決定を行う。*/
				String action = decisionMaking.selectAction(state);
				
				/* 意思決定した行為に関する環境からの相互作用としてMDPを得る */
				MDP mdp = environment.getMDP(currentState, action); 			
				
				/* MDPを基にしてQ値を更新する。*/
				state.updateQValue(mdp);
				/* 次の状態をMDPから得る */
				String nextState = mdp.getNextSquare();
//				System.out.printf("状態：%s 行為：%s 次の状態：%s Q値：%7.6f\n",
//						cycle, currentState, action, nextState, state.getQValue(action));
				
				RLearning.LOGFILE.printf("%d,%s,%s,%s,%s,%7.6f\n",
						cycle, currentCondition, currentState, action, nextState, state.getQValue(action));
				
				currentState = nextState;
			}
			
			this.cycle = cycle;
		}
		
		System.out.printf("\n\nAgent.Qlearning Terminated --- 学習回数:%d\n\n", cycle);

	}
	



	/**
	 * 同一状況下での次の状態を得る
	 **/	
	public State getNextState(String state){
		return states.get(currentCondition+","+state);
	}

	/**
	 * 方策の表示
	 */	
	public void displayPolicy(){

		SortedSet<String> SortedConditions = new TreeSet<String>();
		Set<String> conditionsKeys = states.keySet();
		for (String condition:conditionsKeys){
			SortedConditions.add(condition);
		}

		for (String conditionState:SortedConditions){
			(states.get(conditionState)).displayPolicy(conditionState);
		}
	}
	
	/**
	 * 方策の出力
	 */	
	public void putPolicy(){

		SortedSet<String> SortedConditions = new TreeSet<String>();
		Set<String> conditionsKeys = states.keySet();
		for (String condition:conditionsKeys){
			SortedConditions.add(condition);
		}
		
		for (String conditionState:SortedConditions){
			(states.get(conditionState)).putPolicy(conditionState);
		}
	}

	/**
	 * Q値の表示
	 */
	public void displayQValue(){

		SortedSet<String> SortedConditions = new TreeSet<String>();
		Set<String> conditionsKeys = states.keySet();
		for (String condition:conditionsKeys){
			SortedConditions.add(condition);
		}
		
		for (String conditionState:SortedConditions){
			(states.get(conditionState)).displayQValue(conditionState);
		}

	}
	
	/**
	 * Q値の表示
	 */
	public void putQValue(){

		SortedSet<String> SortedConditions = new TreeSet<String>();
		Set<String> conditionsKeys = states.keySet();
		for (String condition:conditionsKeys){
			SortedConditions.add(condition);
		}
		
		for (String conditionState:SortedConditions){
			(states.get(conditionState)).putQValue(conditionState);
		}

	}
}


