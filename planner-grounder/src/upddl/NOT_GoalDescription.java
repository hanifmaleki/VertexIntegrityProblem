package upddl;

import java.util.Set;

public class NOT_GoalDescription implements IGoalDescription {
	private final IGoalDescription goal;
	
	public NOT_GoalDescription(IGoalDescription goal) {
		this.goal = goal;
	}
	
	public IGoalDescription getGoal() {
		return this.goal;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NOT (");
		sb.append(goal.toString());
		sb.append(")");
		return sb.toString();
	}

	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		goal.addInvolvedVariablesTo(involvedVariables);
	}

	@Override
	public int getSize() {
		return this.goal.getSize();
	}
}
