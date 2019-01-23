package upddl;

public class UPDDLObject {
	private final String name;
	private final Type type;
	
	private final boolean isVariable;
	
	public UPDDLObject(String name, Type type) {
		this.name = name;
		this.type = type;
		
		if (this.name.startsWith("?")) {
			isVariable = true;
		} else {
			isVariable = false;
		}
	}
	
	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
	
	public boolean isVariable() {
		return this.isVariable;
	}
	
	public String toString() {
		if (this.type == null) {
			return this.name;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(this.name);
			sb.append(" - ");
			sb.append(this.type.toString());
			return sb.toString();
		}
	}
}
