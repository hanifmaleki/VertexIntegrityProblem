package upddl;

import java.util.Set;

public class CONDITIONAL_Effect implements IEffect {
	IGoalDescription condition;
	IEffect effect;
	
	public CONDITIONAL_Effect(IGoalDescription condition, IEffect effect) {
		this.condition = condition;
		this.effect = effect;
	}
	
	public IGoalDescription getCondition() {
		return this.condition;
	}
	
	public IEffect getEffect() {
		return this.effect;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(WHEN ");
		sb.append(this.condition.toString());
		sb.append(" THEN ");
		sb.append(this.effect.toString());
		sb.append(")");
		
		return sb.toString();
	}
	
	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		this.condition.addInvolvedVariablesTo(involvedVariables);
		this.effect.addInvolvedVariablesTo(involvedVariables);
	}

	@Override
	public int getSize() {
		return this.condition.getSize() + this.effect.getSize();
	}
}
