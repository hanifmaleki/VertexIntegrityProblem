package upddl;

import java.util.Set;

public interface IGoalDescription {
	public void addInvolvedVariablesTo(Set<String> involvedVariables);
	public int getSize();
}
