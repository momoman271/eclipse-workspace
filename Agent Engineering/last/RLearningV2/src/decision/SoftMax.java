package decision;
import Agent.State;


public class SoftMax implements DecisionMaking {

	public String selectAction(State state){		
		return state.softMaxSelectAction();		
	}
	
	public void updatePolicy(State state){		
	}
	
	public void updateQValue(State state){		
	}
}
