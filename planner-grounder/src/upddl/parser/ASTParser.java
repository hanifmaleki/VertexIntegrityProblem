package upddl.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.apache.log4j.Logger;

import upddl.*;
import upddl.antlr.UPDDLLexer;
import upddl.antlr.UPDDLParser;


public class ASTParser {
	private static Logger logger = Logger.getLogger(ASTParser.class);
	
	public final Map<String, Integer> objectstringToInteger = new HashMap<String, Integer>();
	public final Map<Integer, String> integerToobjectstring = new HashMap<Integer, String>();
	
	private final String pathToDomainFile;
	private final String pathToProblemFile;
	
	private final String domainFilename;
	private final String problemFilename;
	
	private CommonTree domainRoot;
	private CommonTree problemRoot;
	
	private boolean instanceHasUnknowns = false;
	private boolean instanceHasConditionalEffects = false;
	
	//domain variables
	private Domain domain;
	private String domainName;
	private List<String> domainRequirements;
	private List<Type> types;
	private Map<String, Type> typesForName;
	private List<Constant> constants;
	private List<Predicate> predicates;
	private Map<String, Predicate> predicatesForName;
	private List<Function> functions;
	private Map<String, Function> functionsForName;
	private List<Predicate> nevers;
	private List<Action> actions;
	
	//problem variables
	private Problem problem;
	private String problemName;
	private String problemDomainName;
	private List<String> problemRequirements;
	private List<UPDDLObject> npddlObjects;
	private List<Predicate_Instance> predicateInstances;
	private List<Predicate_Instance> unknownPredicateInstances;
	private List<Function_Instance> functionInstances;
	private List<List<Predicate_Instance>> oneofs;
	private IGoalDescription goal;
	
	public ASTParser(String pathToDomainFile, String pathToProblemFile) throws Exception {
		this.pathToDomainFile = pathToDomainFile;
		this.pathToProblemFile = pathToProblemFile;
		
		File f = new File(pathToDomainFile);
		this.domainFilename = f.getName();
		
		f = new File(pathToProblemFile);
		this.problemFilename = f.getName();
		
		this.domainRoot = this.parse(this.pathToDomainFile);
		
		if (this.domainRoot.getType() == UPDDLParser.DOMAIN) {
			this.domain = this.getDomain(this.domainRoot);
		} else {
			throw new Exception(this.pathToDomainFile + " does not contain a domain description.");
		}
		
		
		this.problemRoot = this.parse(this.pathToProblemFile);
		
		if (this.problemRoot.getType() == UPDDLParser.PROBLEM) {
			this.problem = this.getProblem(this.problemRoot);
		} else {
			throw new Exception(this.pathToProblemFile + " does not contain a problem description.");
		}
		
		this.initObjectsstringMappings();
	}
	
	private CommonTree parse(String filename) throws IOException, RecognitionException, Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("parsing " + filename + "...");
		}
		
		CharStream input = new ANTLRFileStream(filename);
		
		UPDDLLexer lexer = new UPDDLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		UPDDLParser parser = new UPDDLParser(tokens);
		
		CommonTree t = (CommonTree) parser.npddlDoc().getTree();
		
		if(parser.invalidGrammar()) {
			throw new Exception("invalid grammar (" 
					+ parser.getNumberOfSyntaxErrors() + " syntax errors)");
		}
		
		return t;
	}
	
	public Problem getProblem() {
		return this.problem;
	}
	
	public Domain getDomain() {
		return this.domain;
	}
	
	public String getDomainFilename() {
		return this.domainFilename;
	}
	
	public String getProblemFilename() {
		return this.problemFilename;
	}
	
	/*
	 * Problem specific parsing
	 */
	
	private Problem getProblem(CommonTree root) throws Exception {
		for (int i=0; i < root.getChildCount(); i++) {
			Tree child = root.getChild(i);
			
			switch (child.getType()) {
				case UPDDLParser.PROBLEM_NAME:
					this.problemName = child.getChild(0).getText();
					
					if (logger.isDebugEnabled())
						logger.debug("problem-name: " + this.problemName);
					break;
					
				case UPDDLParser.PROBLEM_DOMAIN:
					this.problemDomainName = child.getChild(0).getText();
					
					if (logger.isDebugEnabled())
						logger.debug("problem-domain-name: " + this.problemDomainName);
					break;
					
				case UPDDLParser.REQUIREMENTS:
					this.problemRequirements = this.getRequirements(child);
					
					if (logger.isDebugEnabled())
						logger.debug("requirements: " + this.problemRequirements);
					break;
					
				case UPDDLParser.OBJECTS:
					this.addUPDDLObjects(child);
					
					if (logger.isDebugEnabled())
						logger.debug("objects: " + this.npddlObjects);
					break;
					
				case UPDDLParser.INIT:
					this.addInitElements(child);
					
					if  (logger.isDebugEnabled()) {
						logger.debug("init:\n\tpredicates: " + this.predicateInstances +
								"\n\tunknown predicates: " + this.unknownPredicateInstances + 
								"\n\tfunctions: " + this.functionInstances);
					}
					break;
					
				case UPDDLParser.GOAL:
					this.goal = this.getGoalDescription(child.getChild(0));
					
					if (logger.isDebugEnabled())
						logger.debug("goal: " + this.goal);
					break;
					
				case UPDDLParser.PROBLEM_METRIC:
					if (logger.isDebugEnabled())
						logger.debug("ignoring problem metric in '" + this.problemName + "'.");
					break;
					
				default:
					throw new Exception("unknown type: " + child.getType() 
							+ " (" + child.getText() + ")");
			}
		}
		
		return new Problem(	this.pathToProblemFile,
								this.problemName, 
								this.problemDomainName,
								this.problemRequirements,
								this.npddlObjects,
								this.predicateInstances,
								this.unknownPredicateInstances,
								this.oneofs,
								this.functionInstances,
								this.goal);
	}
	
	private void addInitElements(Tree initRoot) throws Exception {
		this.predicateInstances = new ArrayList<Predicate_Instance>();
		this.unknownPredicateInstances = new ArrayList<Predicate_Instance>();
		this.functionInstances = new ArrayList<Function_Instance>();
		this.oneofs = new LinkedList<List<Predicate_Instance>>();
		
		for (int i=0; i < initRoot.getChildCount(); i++) {
			Tree child = initRoot.getChild(i);
			
			switch (child.getType()) {
				case UPDDLParser.PRED_INST:
					this.predicateInstances.add(this.getPredicateInstance(child));
					break;
					
				case UPDDLParser.UNKNOWN_PRED_INST:
					this.instanceHasUnknowns = true;
					this.unknownPredicateInstances.add(this.getPredicateInstance(child));
					break;
					
				case UPDDLParser.INIT_EQ:
					this.functionInstances.add(this.getFunctionInstance(child));
					break;
				
				//TODO funktioniert noch nicht... bei encodings was ueberlegen!!!
//				case UPDDLParser.ONEOF:
//					this.oneofs.add(this.getOneof(child));
//					break;
					
				default:
					throw new Exception("unknown init-type.");
			}
		}
	}
	
	private Function_Instance getFunctionInstance(Tree initEQRoot) throws Exception {
		int value = 0;
		
		try {
			value = Integer.parseInt(initEQRoot.getChild(1).getText());
		}catch (NumberFormatException e) {
			throw new Exception(e.getMessage());
		}
		
		Tree functionChild = initEQRoot.getChild(0);
		
		String name = functionChild.getChild(0).getText();
		
		Function function = this.functionsForName.get(name);
		List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
		
		if (function == null) {
			throw new Exception("function " + name + " not found");
		}
		
		name = null; //help the garbage collector
		
		for(int j=1; j < functionChild.getChildCount(); j++) {
			Tree t = functionChild.getChild(j);
			arguments.add(new UPDDLObject(t.getText(), null));
		}
		
		Function_Instance functionInstance = new Function_Instance(function, arguments, value);
		return functionInstance;
	}
	
	private Predicate_Instance getPredicateInstance(Tree child) throws Exception {
		String name = child.getChild(0).getText();
		
		Predicate predicate = this.predicatesForName.get(name);
		List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
		
		if (predicate == null) {
			throw new Exception("predicate " + name + " not found");
		}
		
		name = null; //help the garbage collector
		
		for(int j=1; j < child.getChildCount(); j++) {
			Tree t = child.getChild(j);
			arguments.add(new UPDDLObject(t.getText(), null));
		}
		
		Predicate_Instance predicateInstance = new Predicate_Instance(predicate, arguments);
		return predicateInstance;
	}
	
	private void addUPDDLObjects(Tree npddlObjectsRoot) {
		List<UPDDLObject> npddlObjects = new ArrayList<UPDDLObject>();
		
		for (int i=0; i < npddlObjectsRoot.getChildCount(); i++) {
			Tree child = npddlObjectsRoot.getChild(i);
			
			String name = child.getText();
			String typename; 
			
			if (child.getChild(0) == null) {
				typename = "object";
			} else {
				typename = child.getChild(0).getText();
			}
			
			Type type = this.typesForName.get(typename);
			
			UPDDLObject npddlObject = new UPDDLObject(name, type);
			npddlObjects.add(npddlObject);
		}
		
		this.npddlObjects = npddlObjects;
	}
	
	
	
	
	/*
	 * Domain specific parsing
	 */
	
	private Domain getDomain(CommonTree root) throws Exception {
		String objString = "object";
		Type objType = new Type(objString);
		
		this.types = new ArrayList<Type>();
		this.types.add(objType);
		
		this.typesForName = new HashMap<String, Type>();
		this.typesForName.put(objString, objType);
		
		this.actions = new ArrayList<Action>();
		
		this.constants = new ArrayList<Constant>();
		
		for (int i=0; i < root.getChildCount(); i++) {
			Tree child = root.getChild(i);
			
			switch (child.getType()) {
				case UPDDLParser.DOMAIN_NAME:
					this.domainName = child.getChild(0).getText();
					
					if (logger.isDebugEnabled()) 
						logger.debug("domain-name: " + this.domainName);
					break;
					
				case UPDDLParser.REQUIREMENTS:
					this.domainRequirements = this.getRequirements(child);
					
					if (logger.isDebugEnabled()) 
						logger.debug("requirements: " + this.domainRequirements.toString());
					break;
					
				case UPDDLParser.TYPES:
					this.addTypes(child);
					
					if (logger.isDebugEnabled()) 
						logger.debug("types: " + this.types.toString());
					break;
					
				case UPDDLParser.CONSTANTS:
					this.addConstants(child);
					
					if (logger.isDebugEnabled()) 
						logger.debug("constants: " + this.constants.toString());
					break;
					
				case UPDDLParser.PREDICATES:
					this.addPredicates(child);
					
					if (logger.isDebugEnabled()) 
						logger.debug("predicates: " + this.predicates.toString());
					break;
					
				case UPDDLParser.FUNCTIONS:
					this.addFunctions(child);
					
					if (logger.isDebugEnabled()) 
						logger.debug("functions: " + this.functions.toString());
					break;
					
				case UPDDLParser.NEVERS:
					this.addNevers(child);
					
					if (logger.isDebugEnabled())
						logger.info("nevers: " + this.nevers.toString());
					break;
					
				case UPDDLParser.ACTION:
					Action a = this.getAction(child);
					this.actions.add(a);
					
					if (logger.isDebugEnabled()) 
						logger.debug("action: " + a.toString());
					break;
					
				default:
					throw new Exception("unknown type: " + child.getType() 
							+ " (" + child.getText() + ")");
			}
		}
		
		return new Domain(	this.pathToDomainFile,
							this.domainName, 
							this.domainRequirements, 
							this.types,
							this.constants,
							this.predicates, 
							this.functions,
							this.nevers,
							this.actions);
	}
	
	private Action getAction(Tree actionRoot) throws Exception {
		String name = actionRoot.getChild(0).getText();
		List<UPDDLObject> parameters = null;
		IGoalDescription precondition = null;
		IEffect effect = null;
		
		//i=1; skip name
		for (int i=1; i < actionRoot.getChildCount(); i++) {
			Tree child = actionRoot.getChild(i);
			
			switch (child.getType()) {
				case UPDDLParser.NAME:
					//do nothing
					break;
					
				case UPDDLParser.ACTION_PARAMETERS:
					parameters = this.getActionParameters(child);
					break;
					
				case UPDDLParser.PRECONDITION:
					if (child.getChild(0) == null) {
						precondition = null;
					} else {
						precondition = this.getGoalDescription(child.getChild(0));
					}
					break;
					
				case UPDDLParser.EFFECT:
					effect = this.getActionEffect(child.getChild(0), false);
					break;
					
				default:
					throw new Exception("unknown type: " + child.getType() 
							+ " (" + child.getText() + ")");
			}
		}
		
		return new Action(name, parameters, precondition, effect);
	}
	
	private IEffect getActionEffect(Tree actionEffectRoot, boolean conditionalEffectAbove) throws Exception {
		switch (actionEffectRoot.getType()) {
			case UPDDLParser.AND_EFFECT:
				IEffect[] subeffects = new IEffect[actionEffectRoot.getChildCount()];
				
				for(int i=0; i < actionEffectRoot.getChildCount(); i++) {
					subeffects[i] = this.getActionEffect(actionEffectRoot.getChild(i), conditionalEffectAbove);
				}
				
				return new AND_Effect(subeffects);
				
			case UPDDLParser.PRED_HEAD:
				String name = actionEffectRoot.getChild(0).getText();
				
				Predicate predicate = this.predicatesForName.get(name);
				List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
				
				if (predicate == null) {
					throw new Exception("predicate " + name + " not found");
				}
				
				name = null; //help the garbage collector
				
				for(int j=1; j < actionEffectRoot.getChildCount(); j++) {
					Tree t = actionEffectRoot.getChild(j);
					arguments.add(new UPDDLObject(t.getText(), null));
				}
				
				return new Predicate_Effect(predicate, arguments);
				
			case UPDDLParser.NOT_EFFECT:
				return new NOT_Effect(this.getActionEffect(actionEffectRoot.getChild(0), conditionalEffectAbove));
				
			case UPDDLParser.WHEN_EFFECT:
				if (conditionalEffectAbove) {
					throw new Exception("Nesting of conditional effects is not allows (WHEN).");
				}
				this.instanceHasConditionalEffects = true;
				IGoalDescription condition = this.getGoalDescription(actionEffectRoot.getChild(0));
				IEffect effect = this.getActionEffect(actionEffectRoot.getChild(1), true);
				return new CONDITIONAL_Effect(condition, effect);
				
			case UPDDLParser.ASSIGN_EFFECT:
				return this.getAssignEffect(actionEffectRoot);
				
			default:
				throw new Exception("effect type " + actionEffectRoot.getType() + " not known.");
		}
	}
	
	private IEffect getAssignEffect(Tree assignEffectRoot) throws Exception {
		String effectName = assignEffectRoot.getChild(0).getText();
		
		if (!"increase".equals(effectName)) {
			throw new Exception("assign effect different than 'increase' not supported.");
		}
		
		effectName = null; //help the garbage collector
		
		Tree t = assignEffectRoot.getChild(1);
		String functionName = t.getChild(0).getText();
		List<UPDDLObject> arguments = null;
		
		if (t.getChildCount() > 1) {
			arguments = new ArrayList<UPDDLObject>();
			
			for(int i=1; i < t.getChildCount(); i++) {
				arguments.add(new UPDDLObject(t.getChild(i).getText(), null));
			}
		}
		
		Function f = this.functionsForName.get(functionName);
		
		if (f ==null) {
			throw new Exception("function " + functionName + " not found.");
		}
		
		t = assignEffectRoot.getChild(2);
		
		switch (t.getType()) {
			case UPDDLParser.NUMBER:
				float number = Float.parseFloat(t.getText());
				return new IncreaseByNumber_Effect(f, arguments, number);
				
			case UPDDLParser.FUNC_HEAD:
				functionName = t.getChild(0).getText();
				List<UPDDLObject> arguments2 = new ArrayList<UPDDLObject>();
				
				for(int i=1; i < t.getChildCount(); i++) {
					arguments2.add(new UPDDLObject(t.getChild(i).getText(), null));
				}
				
				Function f2 = this.functionsForName.get(functionName);
				
				if (f2 ==null) {
					throw new Exception("function " + functionName + " not found.");
				}
				
				return new IncreaseByFunction_Effect(f, arguments, f2, arguments2);
				
			default:
				throw new Exception("increase effect with type " + t.getType() + " not known.");
		}
	}
	
	private List<UPDDLObject> getActionParameters(Tree actionParametersRoot) {
		List<UPDDLObject> parameters = new ArrayList<UPDDLObject>();
		
		for (int i=0; i < actionParametersRoot.getChildCount(); i++) {
			Tree typeTree = actionParametersRoot.getChild(i).getChild(0);
			
			Type type;
			
			if (typeTree != null) {
				String typeName = actionParametersRoot.getChild(i).getChild(0).getText();
				type = this.typesForName.get(typeName);
			} else {
				type = this.typesForName.get("object");
			}
			
			UPDDLObject a = new UPDDLObject(actionParametersRoot.getChild(i).getText(), type);
			parameters.add(a);
		}
		
		return parameters;
	}
	
	private void addNevers(Tree neversRoot) {
		this.nevers = new ArrayList<Predicate>();
		
		for (int i=0; i < neversRoot.getChildCount(); i++) {
			Tree child = neversRoot.getChild(i);
			List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
			
			for (int j=0; j < child.getChildCount(); j++) {
				String argumentName = child.getChild(j).getText();
				
				Type type = null;
				
				if (argumentName.startsWith("?")) {
					String typeName = child.getChild(j).getChild(0).getText();
					type = this.typesForName.get(typeName);
				}
				
				UPDDLObject a = new UPDDLObject(argumentName, type);
				arguments.add(a);
			}
			
			String name = child.getText();
			Predicate p = new Predicate(name, arguments);
			
			this.nevers.add(p);
		}
	}
	
	private void addFunctions(Tree functionsRoot) {
		this.functions = new ArrayList<Function>();
		this.functionsForName = new HashMap<String, Function>();
		
		for (int i=0; i < functionsRoot.getChildCount(); i++) {
			Tree child = functionsRoot.getChild(i);
			List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
			
			for (int j=0; j < child.getChildCount(); j++) {
				String typeName = child.getChild(j).getChild(0).getText();
				Type type = this.typesForName.get(typeName);
				
				UPDDLObject a = new UPDDLObject(child.getChild(j).getText(), type);
				arguments.add(a);
			}
			
			String name = child.getText();
			Function f = new Function(name, arguments);
			
			this.functions.add(f);
			this.functionsForName.put(name, f);

			if (i < functionsRoot.getChildCount()-1) {
				//if there is a function-type, jump over it... (is 'number' anyway)
				if (functionsRoot.getChild(i+1).getText().equals("-")) {
					i+=2;
				}
			}
		}
	}
	
	private void addPredicates(Tree predicatesRoot) {
		this.predicates = new ArrayList<Predicate>();
		this.predicatesForName = new HashMap<String, Predicate>();
		
		for (int i=0; i < predicatesRoot.getChildCount(); i++) {
			Tree child = predicatesRoot.getChild(i);
			List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
			
			for (int j=0; j < child.getChildCount(); j++) {
				Tree typeTree = child.getChild(j).getChild(0);
				
				Type type;
				
				if (typeTree != null) {
					String typeName = child.getChild(j).getChild(0).getText();
					type = this.typesForName.get(typeName);
				} else {
					type = this.typesForName.get("object");
				}
				
				UPDDLObject a = new UPDDLObject(child.getChild(j).getText(), type);
				arguments.add(a);
			}
			
			String name = child.getText();
			Predicate p = new Predicate(name, arguments);
			
			this.predicates.add(p);
			this.predicatesForName.put(name, p);
		}
	}
	
	private void addConstants(Tree constantsRoot) {
		for (int i=0; i < constantsRoot.getChildCount(); i++) {
			Tree child = constantsRoot.getChild(i);
			
			String name = child.getText();
			String typename = child.getChild(0).getText();
			Type type = this.typesForName.get(typename);
			
			Constant c = new Constant(name, type);
			this.constants.add(c);
		}
	}
	
	private void addTypes(Tree typesRoot) throws Exception {
		for (int i=0; i < typesRoot.getChildCount(); i++) {
			Tree child = typesRoot.getChild(i);
			
			if (child.getChildCount() == 0) { //no parent
				Type t = new Type(child.getText());
				this.types.add(t);
				this.typesForName.put(child.getText(), t);
			} else { //has parent
				String parent = child.getChild(0).getText();
				Type parentType = this.typesForName.get(parent);
				
				if (parentType == null) {
					throw new Exception("parent-type '" + parent 
							+ "' for '" + child.getText() 
							+ "' not found. (is the parent-type defined before?)");
				}
				
				Type t = new Type(child.getText(), parentType);
				parentType.addChild(t);
				this.types.add(t);
				this.typesForName.put(child.getText(), t);
			}
		}
	}
	
	/*
	 * shared methods between domain-specific and problem-specific parsing
	 */
	
	private IGoalDescription getGoalDescription(Tree goalDescriptionRoot) throws Exception {
		switch (goalDescriptionRoot.getType()) {
			case UPDDLParser.AND_GD:
				IGoalDescription[] subgoals = new IGoalDescription[goalDescriptionRoot.getChildCount()];
				
				for (int i=0; i < goalDescriptionRoot.getChildCount(); i++) {
					subgoals[i] = this.getGoalDescription(goalDescriptionRoot.getChild(i));
				}
				
				return new AND_GoalDescription(subgoals);
				
			case UPDDLParser.PRED_HEAD:
				String name = goalDescriptionRoot.getChild(0).getText();
				
				Predicate predicate = this.predicatesForName.get(name);
				List<UPDDLObject> arguments = new ArrayList<UPDDLObject>();
				
				if (predicate == null) {
					throw new Exception("predicate " + name + " not found");
				}
				
				name = null; //help the garbage collector
				
				//j=1: skip name, start at first term
				for (int j=1; j < goalDescriptionRoot.getChildCount(); j++) {
					Tree t = goalDescriptionRoot.getChild(j);
					arguments.add(new UPDDLObject(t.getText(), null));
				}
				
				return new Predicate_GoalDescription(predicate, arguments);
				
			case UPDDLParser.NOT_GD:
				return new NOT_GoalDescription(this.getGoalDescription(goalDescriptionRoot.getChild(0)));
				
			default:
				throw new Exception("goal description " + goalDescriptionRoot.getText()
						+ " (type: " + goalDescriptionRoot.getType() + ") not known.");
		}
	}
	
	private List<String> getRequirements(Tree requirementsRoot) {
		List<String> requirements = new ArrayList<String>();
		
		for (int i=0; i < requirementsRoot.getChildCount(); i++) {
			Tree child = requirementsRoot.getChild(i);
			requirements.add(child.getText());
		}
		
		return requirements;
	}
	
	private void initObjectsstringMappings() {
		for (UPDDLObject o : this.problem.upddlObjects) {
			int i = this.getNextObjectsstringMappingIndex();
			this.integerToobjectstring.put(i, o.getName());
			this.objectstringToInteger.put(o.getName(), i);
		}
		
		for (Constant c : this.domain.constants) {
			int i = this.getNextObjectsstringMappingIndex();
			this.integerToobjectstring.put(i, c.getName());
			this.objectstringToInteger.put(c.getName(), i);
		}
	}
	
	private int getNextObjectsstringMappingIndex() {
		return this.integerToobjectstring.size();
	}
	
	public boolean instanceHasUnknowns() {
		return this.instanceHasUnknowns;
	}
	
	public boolean instanceHasConditionalEffects() {
		return this.instanceHasConditionalEffects;
	}
}
