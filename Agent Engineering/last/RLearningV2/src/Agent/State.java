package Agent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import task.RLearning;

import environment.MDP;
import environment.Square;


public class State {

	/**
	 * 状態名（マス名と同じ）
	 */	
	private String name;
	public String getName(){
		return name;
	}

	/**
	 * 当状態に関するマス
	 */	
	private Square square;

	/**
	 * 当状態での行為集合
	 */
	private List<String> Actions = new ArrayList<String>();

	/**
	 * 方策表
	 */	
	private Map<String, Double> policies = new HashMap<String, Double>();

	/**
	 * Q値表
	 */	
	private Map<String, Double> QValues = new HashMap<String, Double>();

	/**
	 * Q値の更新回数
	 */
	private long updateCount = 1;
	
	/**
	 * 乱数発生器
	 */	
	private static Random randε = new Random();
	private static Random random = new Random();

	/**
	 * Q値
	 */	
	private Double MaxQValue = 0.0;
	public Double getMaxQValue(){
		setMaxQValue();
		return MaxQValue;
	}
	public void setMaxQValue(){

		Collection<Double> values = QValues.values();
		Double maxQ = (values.iterator()).next();

		for (Double value:values) {
			if (value > maxQ) {
				maxQ = value;
			}
		}

		MaxQValue = maxQ;		
	}

	public Double getQValue(String action){
		return QValues.get(action);
	}

	public State(Square square){
		this.square = square;
		name = square.getName();
		setupState();
	}

	/**
	 * 行為、方策、Q値などの集合を準備
	 */
	public void setupState(){

		Map<String,List<MDP>> mdps = square.getMdps();
		Set<String> actions = mdps.keySet();
		for (String action:actions){
			Actions.add(action);
			policies.put(action,0.0);
			QValues.put(action,0.0);			
		}
	}

	/**
	 * 方策、Q値の初期化
	 */	
	public void initializeState(){
		for (String action:Actions){
			policies.put(action,0.0);
			QValues.put(action,0.0);			
		}
	}

	/**
	 * εグリーディーを用いて行為を選択する。
	 * グリーディ手法は指定された状態においてQ値が最大となる行動を選択する
	 */
	public String selectAction(){

		String selectedAction = "";

		if (randε.nextDouble() <= Agent.ε) {
			selectedAction = Actions.get(randε.nextInt(Actions.size()));
		} else {
			Set<String> actions = QValues.keySet();
			Double maxQ = QValues.get((actions.iterator()).next());

			for (String action:actions) {
				if (QValues.get(action)>= maxQ) {
					maxQ = QValues.get(action);
					selectedAction = action;
				}
			}
		}
		return selectedAction;
	}

	/**
	 * softmaxを用いて行為を選択する。
	 */
	public String softMaxSelectAction(){

		String selectAction = "";
		Double randomProbabily = random.nextDouble();		
		Double sumProbability = 0.0;
		Set<String> actions = policies.keySet();

		for (String action:actions){
			sumProbability += policies.get(action);			
			if (sumProbability >= randomProbabily){
				selectAction = action;	
				break;
			}

			selectAction = action;  // 全てのMDPの遷移確率を加えても乱数より小さければ最後のMDPを返す
		}

		return selectAction;
	}


	/**
	 * 方策の更新(ボルツマン分布）
	 */
	public void updatePolicy(){

		Set<String> actions = QValues.keySet();
		Double sum = 0.0;
		Double value = 0.0;

		for (String action:actions) {
			sum += Math.exp(QValues.get(action)/(Agent.TEMPERATURE/updateCount));
		}

		for (String action:actions) {
			value = Math.exp(QValues.get(action)/(Agent.TEMPERATURE/updateCount))/sum;
			policies.put(action, value);			
		}

	}

	/**
	 * Q値の更新
	 */
	public void updateQValue(MDP mdp){

		String action = mdp.getAction();
		
		if(action.equals("N")) {	
			return;
		}
		
		String nextState = mdp.getNextSquare();

		State nextStateP = RLearning.AGENT.getNextState(nextState);
		Double MaxNextQValue = nextStateP.getMaxQValue();

		Double lastQValue;

		Double QValue = lastQValue = QValues.get(action);
		QValue +=
			(Agent.α/(1 + updateCount / 10)) * (mdp.getReward()+Agent.γ*MaxNextQValue - QValue);

		if(Math.abs(QValue - lastQValue) > Math.abs((1.0-Agent.thresthold)*lastQValue)){
			
			QValues.put(action, QValue);
			++updateCount;
			setMaxQValue();
			updatePolicy();

		}
	}



	/**
	 * 方策の表示
	 */	
	public void displayPolicy(String condition){
		
		Set<String> actions = policies.keySet();
		SortedSet<String> sortedActions = new TreeSet<String>();
		
		for(String action:actions){
			sortedActions.add(action);
		}

		for (String action:sortedActions){
			Double policy = policies.get(action);
			System.out.printf("状況,状態：%s 行為:%s 方策値：%7.6f Q値更新回数：%d\n", condition, action, policy, updateCount-1);
		}
	}


	/**
	 * 方策の出力
	 */	
	public void putPolicy(String condition){

		Set<String> actions = policies.keySet();
		SortedSet<String> sortedActions = new TreeSet<String>();
		
		for(String action:actions){
			sortedActions.add(action);
		}		
		
		for (String action:sortedActions){
			Double policy = policies.get(action);
			RLearning.POLICYFILE.printf("%s,%s,%7.6f,%d\n", condition, action, policy, updateCount-1);
		}
	}

	/**
	 * Q値の表示
	 */
	public void displayQValue(String condition){

		Set<String> actions = QValues.keySet();
		SortedSet<String> sortedActions = new TreeSet<String>();
		
		for(String action:actions){
			sortedActions.add(action);
		}

		for (String action:sortedActions){
			Double QValue = QValues.get(action);
			System.out.printf("状況,状態：%s 行為:%s Q値：%7.6f Q値更新回数：%d\n", condition, action, QValue, updateCount-1);
		}
	}

	/**
	 * Q値の出力
	 */
	public void putQValue(String condition){

		Set<String> actions = QValues.keySet();
		SortedSet<String> sortedActions = new TreeSet<String>();
		
		for(String action:actions){
			sortedActions.add(action);
		}
		for (String action:sortedActions){
			Double QValue = QValues.get(action);
			RLearning.QVALUEFILE.printf("%s,%s,%7.6f,%d\n", condition, action, QValue, updateCount-1);
		}
	}

}
