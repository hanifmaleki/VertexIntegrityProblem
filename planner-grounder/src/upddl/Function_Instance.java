package upddl;

import java.util.List;

public class Function_Instance {
	private Function function;
	private List<UPDDLObject> arguments;
	private int value;

	public Function_Instance(Function function, List<UPDDLObject> arguments, int value) {
		this.function = function;
		this.arguments = arguments;
		this.value = value;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(function.getName()).append(" ");
		
		for (UPDDLObject a : this.arguments) {
			if (a.getType() == null) {
				sb.append(a.getName()).append(" ");
			} else {
				sb.append(a.getName()).append(" - ").append(a.getType().toString()).append(" ");
			}
		}
		
		sb.append("= ").append(this.value);
		
		return sb.toString();
	}
}
