package upddl;

import java.util.List;
import java.util.Set;

public class IncreaseByFunction_Effect implements IEffect {
	private final Function function_ToIncrease;
	private final List<UPDDLObject> functionArguments_ToIncrease;
	
	private final Function function_increaseBy;
	private final List<UPDDLObject> functionArguments_increaseBy;
	
	public IncreaseByFunction_Effect(	Function function_ToIncrease, 
										List<UPDDLObject> functionArguments_ToIncrease,
										Function function_increaseBy,
										List<UPDDLObject> functionArguments_increaseBy) {
		
		this.function_ToIncrease = function_ToIncrease;
		this.functionArguments_ToIncrease = functionArguments_ToIncrease;
		this.function_increaseBy = function_increaseBy;
		this.functionArguments_increaseBy = functionArguments_increaseBy;
	}
	
	public Function getFunction_ToIncrease() {
		return function_ToIncrease;
	}
	
	public List<UPDDLObject> getFunctionArguments_ToIncrease() {
		return this.functionArguments_ToIncrease;
	}
	
	public Function getFunction_increaseBy() {
		return function_increaseBy;
	}
	
	public List<UPDDLObject> getFunctionArguments_increaseBy() {
		return this.functionArguments_increaseBy;
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
		sb.append(this.function_increaseBy.getName());
		
		for (UPDDLObject a : this.functionArguments_increaseBy) {
			sb.append(" ");
			sb.append(a.toString());
		}
		
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
		
		if (this.functionArguments_increaseBy != null) { 
			for (UPDDLObject n : this.functionArguments_increaseBy) {
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
