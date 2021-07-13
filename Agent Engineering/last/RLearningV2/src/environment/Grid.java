package environment;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * 状況別のGrid
 * 
 * @author shiro@info.kindai.ac.jp
 * @version 1.0, 09/03/2010
 */

public class Grid {

	/**
	 * 状況
	 */	
	private String condition;
	
	public String getCondition(){
		return condition;
	}
	
	/**
	 * 同じマス(Square)の集合
	 */
	private static int SQUARES_NUMBER = 200;
	private Map<String,Square> squares = new HashMap<String,Square>(SQUARES_NUMBER);
	
	public Map<String,Square> getSquares(){
		return squares;
	}

	public Grid(String condition){
		this.condition = condition;
	}

	public void addMDP(String mdpText){
		
		Square square;
		
		String[] mdpItem = mdpText.split(",");
		String squareKey = mdpItem[1];
		if (!squares.containsKey(squareKey)){
			squares.put(squareKey, square = new Square(squareKey));
		}else{
			square = squares.get(squareKey);
		}

		square.addMDP(mdpText);
	}

	
	public MDP getMDP(String square, String action){		
		return (squares.get(square)).getMDP(action);		
	}
		
	/**
	 * MDPの表示
	 */
	public void displayMDP(){

		SortedSet<String> SQUAREs = new TreeSet<String>();
		Set<String> keys = squares.keySet();
		Collection<Square> Squares = squares.values();
		
		for(String square:keys){
			SQUAREs.add(square);
		}
		
		for (String name:SQUAREs) {
			System.out.printf("---square:%s\n", name);
			squares.get(name).displayMDP(Squares); 
		} 
	}

}