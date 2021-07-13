package environment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * グリッドのマス(Square)
 * 
 * マス（枡）はエージェントの移動場所の単位（粒度）である。
 * マス単位、行為単位にMDPを複数持つ。
 * 
 * @author shiro@info.kindai.ac.jp
 * @version 1.0, 09/02/2010
 */

public class Square{
	/**
	 * マスの名前
	 */
	private String name;

	public String getName(){
		return name;
	}

	/**
	 *　同じ行為のMDP集合
	 */
	private Map<String,List<MDP>> mdps = new HashMap<String,List<MDP>>();
	public Map<String,List<MDP>> getMdps(){
		return mdps;
	}

	/**
	 * 乱数発生器
	 */	
	private static Random random = new Random();


	public Square(String name){
		this.name = name;
	}

	/**
	 *　同じ行為のMDPの追加
	 */
	public void addMDP(String mdpText){

		List<MDP> mdpList;

		String[] mdpItem = mdpText.split(",");
		String actionKey = mdpItem[2];

		if (!mdps.containsKey(actionKey)){
			mdps.put(actionKey, mdpList = new ArrayList<MDP>());
		}else{
			mdpList = mdps.get(actionKey);
		}

		mdpList.add(new MDP(mdpText));
	}

	/**
	 * 指定行為に該当するMDPを遷移確率に従って得る
	 */	
	public MDP getMDP(String action){		

		MDP mdpP = null;

		List<MDP> Mdp = mdps.get(action);
		Double randomProbabily = random.nextDouble();		
		Double sumProbability = 0.0;

		for (MDP mdp:Mdp){
			sumProbability += mdp.getprobability();			
			if (sumProbability >= randomProbabily){
				mdpP = mdp;	
				break;
			}

			mdpP = mdp;  // 全てのMDPの遷移確率を加えても乱数より小さければ最後のMDPを返す
		}

		return mdpP;
	}

	/**
	 *　行為単位のMDP表示
	 */
	public void displayMDP(Collection<Square> SQUAREs){

		List<MDP> MDPs;
		Set<String> actions = mdps.keySet();
		SortedSet<String> sortedMdps = new TreeSet<String>();
		
		for(String action:actions){
			sortedMdps.add(action);
		}
		
		for (String action:sortedMdps){
			MDPs = mdps.get(action);   
			Double sumProbability = 0.0;
			for (MDP mdp:MDPs) {
				sumProbability += mdp.getprobability();
				Boolean exist = false;
				existCheck: for (Square square:SQUAREs){
					if (square.getName().equals(mdp.getNextSquare())){
						exist = true;
						break existCheck;
					}
				}
				if (!exist){
					Environment.DEFECTMDP = true;
					System.out.printf("\n*** 右のMDPの次のマスに関するMDPが定義されていません=>");
				}
				mdp.displayMDP(); 
			}

			if (sumProbability != 1.0){
				Environment.DEFECTMDP = true;
				System.out.printf("*** 上記の行為：%s の遷移確率の合計が1.0ではありません。\n\n", action);
			}

		}
	}
}

