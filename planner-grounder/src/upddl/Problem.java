package upddl;

import java.util.List;

public class Problem {
	public final String fileName;
	public final String name;
	public final String problemDomainName;
	public final List<String> problemRequirements; 
	public final List<UPDDLObject> upddlObjects;
	public final List<Predicate_Instance> predicateInstances;
	public final List<Predicate_Instance> unknownPredicateInstances;
	public final List<List<Predicate_Instance>> oneofs;
	public final List<Function_Instance> functionInstances; 
	public final IGoalDescription goal;
	
	public Problem(String fileName,
					String name, 
					String problemDomainName, 
					List<String> problemRequirements, 
					List<UPDDLObject> npddlObjects, 
					List<Predicate_Instance> predicateInstances, 
					List<Predicate_Instance> unknownPredicateInstances, 
					List<List<Predicate_Instance>> oneofs,
					List<Function_Instance> functionInstances, 
					IGoalDescription goal) {
		
		this.fileName = fileName;
		this.name = name;
		this.problemDomainName = problemDomainName;
		this.problemRequirements = problemRequirements;
		this.upddlObjects = npddlObjects;
		this.predicateInstances = predicateInstances;
		this.unknownPredicateInstances = unknownPredicateInstances;
		this.oneofs = oneofs;
		this.functionInstances = functionInstances;
		this.goal = goal;
	}
}
