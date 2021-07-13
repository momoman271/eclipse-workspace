package Agent;
import decision.eGreedy;
import environment.Environment;


public class eGreedyRacer extends Agent {

	public eGreedyRacer(Environment environment){
		super(environment);
		decisionMaking = new eGreedy();		
	}
	
}
