package upddl;

public class Constant {
	private final String name;
	private final Type type;
	
	public Constant(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public String toString() {
		if (this.type == null) {
			return this.name;
		} else {
			return this.name + " - " + this.type.toString();
		}
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
}
