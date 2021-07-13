package environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import task.RLearning;


public class Environment {

	/**
	 * 乱数発生器
	 */	
	private Random random = new Random();

	/**
	 * 現在の状況
	 */	
	private String currentCondition;
	public String getCondition(){
		return currentCondition;
	}
	
	/**
	 * 状況の集合
	 */	
	private List<String> conditions;
	
	/**
	 * 状況をランダムにセットする
	 */
	public void setCondition(){
		currentCondition = conditions.get(random.nextInt(conditions.size()));
	}

	/**
	 * 状況単位のGrid表
	 */
	private Map<String,Grid> grids = new HashMap<String,Grid>();
	public Map<String, Grid> getGrids(){
		return grids;
	}

	/**
	 * MDP定義の不備
	 */
	public static Boolean DEFECTMDP = false;


	public Environment(){
		setup();
	}

	/**
	 * csv形式のMDPデータからMDPを準備
	 */		
	private void setup(){

		while(RLearning.MDPFILE.hasNextLine()){
			addMDP(RLearning.MDPFILE.nextLine());
		}

		conditions = new ArrayList<String>(grids.keySet());		
	}

	/**
	 * 状況単位にGridを構築する。
	 */	
	private void addMDP(String mdpText){

		Grid grid;

		String[] mdpItem = mdpText.split(",");
		String conditionKey = mdpItem[0];

		if (!grids.containsKey(conditionKey)){
			grids.put(conditionKey, grid = new Grid(conditionKey));
		}else{
			grid = grids.get(conditionKey);
		}

		grid.addMDP(mdpText);
	}

	/**
	 * マスの位置（現状）と次の行為に関するMDPを取得する。
	 */	
	public MDP getMDP(String square, String action){

		Grid grid = grids.get(currentCondition);		
		return grid.getMDP(square, action);

	}

	/**
	 * MDPの表示
	 * なお、全MDPの次マスに関するMDPが定義されていなければ異常終了する。
	 */
	public void displayMDP(){

		SortedSet<String> SortedConditions = new TreeSet<String>();
		Set<String> conditionsKeys = grids.keySet();
		for (String condition:conditionsKeys){
			SortedConditions.add(condition);
		}

		for (String condition:SortedConditions) {
			System.out.printf("\n---Condition:%s\n", condition);
			grids.get(condition).displayMDP(); 
		}

		if (DEFECTMDP){
			System.out.printf("\n*** MDPの定義不備のため異常終了します。\n");
			System.exit(9);
		}
	}

}
