package Agent;
import decision.SoftMax;
import environment.Environment;


public class SoftMaxRacer extends Agent {

	public SoftMaxRacer(Environment environment){
		super(environment);
		decisionMaking = new SoftMax();		
	}
	
}
