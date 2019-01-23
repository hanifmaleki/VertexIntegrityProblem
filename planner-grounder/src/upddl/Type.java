package upddl;

import java.util.LinkedList;
import java.util.List;

public class Type {
	private final String name;
	private final Type parent;
	private List<Type> childs;
	
	public Type (String name) {
		this(name, null);
	}
	
	public Type(String name, Type parent) {
		this.name = name;
		this.parent = parent;
		this.childs = new LinkedList<Type>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Type getParent() {
		return this.parent;
	}
	
	public List<Type> getChilds() {
		return childs;
	}
	
	/**
	 * 
	 * @param t 
	 * @return true iff this type is equal to type t or a child of type t
	 */
	public boolean isChildOf(Type t) {
		Type c = this;
		
		if (c.name.equals(t.name)) { //are the same, therefore this is not a child of type t
			return false;
		}
		if (c.parent == null) {
			return false;
		}
		c = c.parent;
		
		while (true) {
			if (c.name.equals(t.name)) {
				return true;
			}
			if (c.parent == null) {
				return false;
			}
			c = c.parent;
		}
	}
	
	public String toString() {
		return this.name;
	}

	public void addChild(Type t) {
		this.childs.add(t);
	}
}
