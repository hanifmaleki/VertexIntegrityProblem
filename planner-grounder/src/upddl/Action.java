package upddl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Action {
	private final String name;
	private final List<UPDDLObject> parameters;
	private final Map<String,Integer> parameterIndexForName;
	private final IGoalDescription precondition;
	private final IEffect effect;
	
	public Action(	String name, 
					List<UPDDLObject> parameters,
					IGoalDescription precondition,
					IEffect effect) {
		
		this.name = name;
		this.parameters = parameters;
		this.precondition = precondition;
		this.effect = effect;
		
		this.parameterIndexForName = new HashMap<String, Integer>();
		
		for (int i=0; i < parameters.size(); i++) {
			UPDDLObject a = parameters.get(i);
			this.parameterIndexForName.put(a.getName(), i);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<UPDDLObject> getParameters() {
		return this.parameters;
	}
	
	public Integer getparameterIndexForName(String name) {
		return this.parameterIndexForName.get(name);
	}
	
	public IGoalDescription getPrecondition() {
		return this.precondition;
	}
	
	public IEffect getEffect() {
		return this.effect;
	}
	
	public String toString() {
		return this.name
				+ "\n\t(parameters: " + this.parameters.toString() + ")"
				+ "\n\t(precondition: " + this.precondition.toString() + ")"
				+ "\n\t(effects: " + this.effect.toString() + ")";
	}
}
