package upddl;

import java.util.List;
import java.util.Set;

public class IncreaseByNumber_Effect implements IEffect {
	private final Function function_ToIncrease;
	private final List<UPDDLObject> functionArguments_ToIncrease;
	
	private final float number;
	
	public IncreaseByNumber_Effect(		Function function_ToIncrease, 
										List<UPDDLObject> functionArguments_ToIncrease,
										float number) {

		this.function_ToIncrease = function_ToIncrease;
		this.functionArguments_ToIncrease = functionArguments_ToIncrease;
		this.number = number;
	}
	
	public Function getFunction_ToIncrease() {
		return this.function_ToIncrease;
	}
	
	public List<UPDDLObject> getFunctionArguments_ToIncrease() {
		return this.functionArguments_ToIncrease;
	}
	
	public float getNumber() {
		return this.number;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("increase (");
		sb.append(this.function_ToIncrease.getName());
		
		if (this.functionArguments_ToIncrease != null) {
			for (UPDDLObject a : this.functionArguments_ToIncrease) {
				sb.append(" ");
				sb.append(a.toString());
			}
		}
		
		sb.append(") (");
		sb.append(this.number);
		sb.append(")");
		
		return sb.toString();
	}

	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		if (this.functionArguments_ToIncrease != null) {
			for (UPDDLObject n : this.functionArguments_ToIncrease) {
				if (n.isVariable()) {
					involvedVariables.add(n.getName());
				}
			}
		}
	}

	@Override
	public int getSize() {
		return 1;
	}
}
