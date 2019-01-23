package upddl;

import java.util.List;
import java.util.Set;

public class Predicate_GoalDescription implements IGoalDescription {
	private final Predicate predicate;
	private final List<UPDDLObject> arguments;
	
	public Predicate_GoalDescription(Predicate predicate, List<UPDDLObject> arguments) {
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
		
		for (int i=0; i < this.arguments.size(); i++) {
			sb.append(" ");
			sb.append(this.arguments.get(i).toString());
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
