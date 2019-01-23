package upddl;

import java.util.Set;

public class AND_Effect implements IEffect {
	private final IEffect[] effects;
	
	private String stringRepresentation;
	private boolean stringRepresentationCalculated = false;
	
	private int size;
	private boolean sizeCalculated = false;
	
	public AND_Effect(IEffect[] effects) {
		this.effects = effects;
	}
	
	public IEffect[] getEffects() {
		return this.effects;
	}
	
	public String toString() {
		if (!this.stringRepresentationCalculated) {
			StringBuilder sb = new StringBuilder();
			sb.append("(AND");
			
			for  (IEffect effect : this.effects) {
				sb.append(" (");
				sb.append(effect.toString());
				sb.append(")");
			}
			sb.append(")");
			
			this.stringRepresentation = sb.toString();
			this.stringRepresentationCalculated = true;
		}
		return this.stringRepresentation;
	}
	
	@Override
	public void addInvolvedVariablesTo(Set<String> involvedVariables) {
		for (int i=0; i < this.effects.length; i++) {
			this.effects[i].addInvolvedVariablesTo(involvedVariables);
		}
	}
	
	@Override
	public int getSize() {
		if (!this.sizeCalculated) {
			this.size = 0;
			
			for (int i=0; i < this.effects.length; i++) {
				this.size += this.effects[i].getSize();
			}
			
			this.sizeCalculated = true;
		}
		
		return this.size;
	}
}
