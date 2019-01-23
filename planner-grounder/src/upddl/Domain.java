package upddl;

import java.util.List;


/**
 * NPDDL Domain
 * 
 * @author Martin Kronegger
 *
 */
public class Domain {
	public final String fileName;
	public final String name;
	public final List<String> requirements;
	public final List<Type> types;
	public final List<Constant> constants;
	public final List<Predicate> predicates;
	public final List<Function> functions;
	public final List<Predicate> nevers;
	public final List<Action> actions;
	
	public Domain(	String fileName,
					String name, 
					List<String> requirements, 
					List<Type> types,
					List<Constant> constants,
					List<Predicate> predicates, 
					List<Function> functions,
					List<Predicate> nevers,
					List<Action> actions) {
		
		this.fileName = fileName;
		this.name = name;
		this.requirements = requirements;
		this.types = types;
		this.constants = constants;
		this.predicates = predicates;
		this.functions = functions;
		this.nevers = nevers;
		this.actions = actions;
	}
}
