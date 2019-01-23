package upddl;

import java.util.Set;

public interface IEffect {
	public void addInvolvedVariablesTo(Set<String> involvedVariables);
	public int getSize();
}
