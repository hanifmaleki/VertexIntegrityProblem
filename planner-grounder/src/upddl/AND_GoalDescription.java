package upddl;

import java.util.Set;

public class AND_GoalDescription implements IGoalDescription {
	private final IGoalDescription[] goals;
	
	private String stringRepresentation;
	private boolean stringRepresentationCalculated = false;
	
	private int size;
	private boolean sizeCalculated = false;
	
	public AND_GoalDescription(IGoalDescription[] goals) {
		this.goals = goals;
	}
	
	public IGoalDescription[] getGoals() {
		return this.goals;
	}
	
	public String toString() {
		if (!this.stringRepresentationCalculated) {
			StringBuilder sb = new StringBuilder("AND");
			
			for (int i=0; i < this.goals.length; i++) {
				sb.append(" (");
				sb.append(this.goals[i].toString());
				sb.append(")");
			}
			
			this.stringRepresentation = sb.toString();
			this.stringRepresentationCalculated = true;
		}
		
		return this.stringRepresentation;
	}
	
	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		for (int i=0; i < this.goals.length; i++) {
			this.goals[i].addInvolvedVariablesTo(involvedVariables);
		}
	}
	
	@Override
	public int getSize() {
		if (!this.sizeCalculated) {
			this.size = 0;
			
			for (int i=0; i < this.goals.length; i++) {
				this.size += this.goals[i].getSize();
			}
			
			this.sizeCalculated = true;
		}
		
		return this.size;
	}
}
