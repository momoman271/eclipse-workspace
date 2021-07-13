package decision;
import Agent.State;


public class eGreedy implements DecisionMaking {

	public String selectAction(State state){
		return state.selectAction();
	}
	
	public void updatePolicy(State state){		
	}
	
	public void updateQValue(State state){
	}
}
