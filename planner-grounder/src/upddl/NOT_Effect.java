package upddl;

import java.util.Set;

public class NOT_Effect implements IEffect {
	private final IEffect effect;
	
	public NOT_Effect(IEffect effect) {
		this.effect = effect;
	}
	
	public IEffect getEffect() {
		return this.effect;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NOT (");
		sb.append(this.effect.toString());
		sb.append(")");
		return sb.toString();
	}

	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		this.effect.addInvolvedVariablesTo(involvedVariables);
	}

	@Override
	public int getSize() {
		return this.effect.getSize();
	}
}
