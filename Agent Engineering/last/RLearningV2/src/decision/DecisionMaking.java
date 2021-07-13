package decision;
import Agent.State;


public interface DecisionMaking {
	
	abstract String selectAction(State state);
	abstract void updatePolicy(State state);

}
