package upddl;

import java.util.List;

public class Predicate_Instance {
	public final Predicate predicate;
	public final List<UPDDLObject> arguments;
	
	public Predicate_Instance(Predicate predicate, List<UPDDLObject> arguments) {
		this.predicate = predicate;
		this.arguments = arguments;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(predicate.getName()).append(" ");
		
		for (UPDDLObject a : this.arguments) {
			if (a.getType() == null) {
				sb.append(a.getName()).append(" ");
			} else {
				sb.append(a.getName()).append(" - ").append(a.getType().toString()).append(" ");
			}
		}
		
		return sb.toString();
	}
}
