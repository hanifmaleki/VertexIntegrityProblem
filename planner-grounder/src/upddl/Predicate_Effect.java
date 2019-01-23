package upddl;

import java.util.List;
import java.util.Set;

public class Predicate_Effect implements IEffect {
	private final Predicate predicate;
	private final List<UPDDLObject> arguments;
	
	public Predicate_Effect(Predicate predicate, List<UPDDLObject> arguments) {
		this.predicate = predicate;
		this.arguments = arguments;
	}
	
	public Predicate getPredicate() {
		return this.predicate;
	}
	
	public List<UPDDLObject> getArguments() {
		return this.arguments;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.predicate.getName());
		
		for(UPDDLObject a : this.arguments) {
			sb.append(" ");
			sb.append(a.toString());
		}
		
		return sb.toString();
	}
	
	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		for (UPDDLObject argument : this.arguments) {
			if (argument.isVariable()) {
				involvedVariables.add(argument.getName());
			}
		}
	}
	
	@Override
	public int getSize() {
		return 1;
	}
}
