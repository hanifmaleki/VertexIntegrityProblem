package upddl;

import java.util.List;

public class Predicate {
	private final String name;
	private final List<UPDDLObject> arguments;
	
	public Predicate(String name, List<UPDDLObject> arguments) {
		this.name = name;
		this.arguments = arguments;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<UPDDLObject> getArguments() {
		return this.arguments;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(name).append(" ");
		
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
