package grounder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import common.Pair;
import common.Stopwatch;

import main.Planner;
import upddl.AND_Effect;
import upddl.AND_GoalDescription;
import upddl.Action;
import upddl.CONDITIONAL_Effect;
import upddl.Constant;
import upddl.Domain;
import upddl.IEffect;
import upddl.IGoalDescription;
import upddl.IncreaseByFunction_Effect;
import upddl.IncreaseByNumber_Effect;
import upddl.NOT_Effect;
import upddl.NOT_GoalDescription;
import upddl.UPDDLObject;
import upddl.Predicate;
import upddl.Predicate_Effect;
import upddl.Predicate_GoalDescription;
import upddl.Predicate_Instance;
import upddl.Problem;
import upddl.Type;
import upddl.parser.ASTParser;

public class Grounder {
	private static Logger logger = Logger.getLogger(Grounder.class);
	
	private static final int ARGUMENT_IS_CONSTANT = -1;
	
	private static final int MIN_PLANLENGTH_NOT_CALCULATED = -1;
	private int minPlanlength = Grounder.MIN_PLANLENGTH_NOT_CALCULATED;
	
	private static final int FIXPOINT_NOT_REACHED = -1;
	private static int STEP_GROUNDING_FIXPOINT_AT_TIME = Grounder.FIXPOINT_NOT_REACHED;
	
	/* grounded actions
	 * ================ 
	 * an action together with the instantiated parameters is an action-instance
	 * where an action and an array of instantiated parameters belong together iff they are in the
	 * same position in the list
	 */
	private final int numberOfActions;
	private List<Action> actionInstancesActions;
	private List<int[]> instanciatedParametersForActionInstances;
	private int numberOfInconsistentActionInstances = 0;
	
	private BitSet[] actionInstancesPositivePredicatesInPrecondition;
	private BitSet[] actionInstancesNegativePredicatesInPrecondition;
	private BitSet[] actionInstancesPositivePredicatesInEffects;
	private BitSet[] actionInstancesNegativePredicatesInEffects; //used for inconsistency checks and if also negative Preconditions occur
	
	private int timeOfCalculatedActionInstances;
	private BitSet currentActiveActionInstancesBitSet;
	private BitSet currentActivePositivePredicatesBitSet;
	private BitSet currentActiveUnknownPredicatesBitSet;
	private BitSet currentActiveNegativePredicatesBitSet;
	public final boolean useNegativePredicatesForPhaseGrounding;
	private Pair<List<Action>,List<int[]>> actionInstancesAtFixpoint;
	
	private final Map<String, Integer> predicateIdentification;
	private final Map<Integer, String> predicateIdentificationInverse;
	
	private final int numberOfAllFluents;
	private Set<String> groundedFluentsAsString;
	private Set<String> groundedNeversAsString;
	
	private final ASTParser astParser;
	private final Domain domain;
	private final Problem problem;
	
	private final Map<String, List<Integer>> objectsForTypename;
	
	private final Stopwatch stopwatch1;
	
	//Reuseables
	private final StringBuilder reuseableStringBuilder;
	private final Pair<List<Action>, List<int[]>> reuseablePairOfActionsAndInstantiatedParameters;
	private final List<Action> reuseableActions;
	private final List<int[]> reuseableInstanciatedParametersOfAction;
	
	/**
	 * 
	 * @param useVersion
	 * <code>
	 * = {@link Grounder.GROUND_WITH_SPLITTING_ACTION_EFFECTS}<br>
	 *	a causes f1 if ...<br>
	 *	a causes f2 if ...<br>
	 * <br>
	 *	size of grounding:<br>
	 *	|grounding(f1)| + |grounding(f2)|<br>
	 * <br>
	 * = {@link Grounder.GROUND_WITHOUT_SPLITTING_ACTION_EFFECTS}<br>
	 *	a causes f1, f2 if ...<br>
	 * <br>
	 *	size of grounding:<br>
	 * 	|grounding(f1)| * |grounding(f2)|
	 * </code>
	 * @param domain the domain
	 * @param problem the problem
	 */
	public Grounder(ASTParser astParser, Stopwatch stopwatch1) throws Exception {
		this.astParser = astParser;
		this.domain = astParser.getDomain();
		this.problem = astParser.getProblem();
		this.stopwatch1 = stopwatch1;
		
//		boolean negationInPrecondition = this.containsNegationInPrecondition(this.domain.actions);
//		boolean negationInEffect = this.containsNegationInEffect(this.domain.actions);
//		this.useNegativePredicatesForPhaseGrounding = negationInPrecondition || negationInEffect;
		this.useNegativePredicatesForPhaseGrounding = true; //because of explanatory framing axioms...
		
		this.actionInstancesActions = new ArrayList<Action>();
		this.instanciatedParametersForActionInstances = new ArrayList<int[]>();
		
		this.predicateIdentification = new HashMap<String, Integer>();
		this.predicateIdentificationInverse = new HashMap<Integer, String>();
		
		this.objectsForTypename = new HashMap<String, List<Integer>>();
		
		this.reuseableStringBuilder = new StringBuilder();
		this.reuseablePairOfActionsAndInstantiatedParameters = new Pair<List<Action>, List<int[]>>(null, null);
		this.reuseableActions = new ArrayList<Action>();
		this.reuseableInstanciatedParametersOfAction = new ArrayList<int[]>();
		
		this.calcObjectsForTypename();
		System.out.println("After calcObjectsForTypename");
		
		this.groundFluentsAsString();
		System.out.println("After groundFluentsAsString");

		this.groundNeversAsString();
		this.numberOfAllFluents = this.groundedFluentsAsString.size();
		
		if (Planner.USE_SPLITTING_EFFECTS_IF_BETTER) {
			this.groundActionsAndSplitIfBetter(this.domain.actions);
		} else {
			this.groundActionsWithoutSplitting(this.domain.actions);
		}
		System.out.println("After ground and (Splitting)");


		this.numberOfActions = this.actionInstancesActions.size(); //after grounding actions
		
		if (!this.useNegativePredicatesForPhaseGrounding) {
			this.actionInstancesNegativePredicatesInEffects = null; //then only used for inconsistency checks, therefore not need anymore
		}
		
//		if (Planner.USE_PHASE_GROUNDING_FOR_ACTION_INSTANCES) {
			this.timeOfCalculatedActionInstances = -1;
			this.currentActiveActionInstancesBitSet = new BitSet(this.numberOfActions);
			this.currentActivePositivePredicatesBitSet = new BitSet(this.numberOfAllFluents);
			this.currentActiveUnknownPredicatesBitSet = new BitSet(this.numberOfAllFluents);

			this.initActionInstancesPredicatesInPrecondition();

			this.initActionInstancesPredicatesInEffects();


		if (this.useNegativePredicatesForPhaseGrounding) {
				this.currentActiveNegativePredicatesBitSet = new BitSet(this.numberOfAllFluents);
			}
			
//			this.reuseableStringBuilder.setLength(0);
//			this.reuseableStringBuilder.append("useActiveNegativePredicatesBitSet = ");
//			this.reuseableStringBuilder.append(this.useActiveNegativePredicatesBitSet);
//			this.reuseableStringBuilder.append(" (negPred=");
//			this.reuseableStringBuilder.append(negPrecondition);
//			this.reuseableStringBuilder.append(", negEff=");
//			this.reuseableStringBuilder.append(negEffect);
//			this.reuseableStringBuilder.append(")");
//			logger.info(this.reuseableStringBuilder.toString());
//		} else {
//			this.timeOfCalculatedActionInstances = -2;
//			this.currentActiveActionInstancesBitSet = null;
//			this.currentActivePositivePredicatesBitSet = null;
//			this.currentActiveUnknownPredicatesBitSet = null;
//			this.currentActiveNegativePredicatesBitSet = null;
////			this.useNegativePredicatesForPhaseGrounding = false;
//		}

		/*int counter = 1 ;
		for(String string: groundedFluentsAsString){
			System.out.println(counter++ +"\t"+string);
		}*/

		makeCausalGraphFile("graph.gf");
	}

	private void makeCausalGraphFile(String filename) {
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		try {
			fileWriter = new FileWriter(filename);
			writer = new BufferedWriter(fileWriter);
			int i= 0;
			Object[] objects = groundedFluentsAsString.toArray();
			for(Action action :this.actionInstancesActions){
				System.out.println(action.getName()+ "\t"+ action.getParameters().size());
				int[] ints = this.instanciatedParametersForActionInstances.get(i);
				for(int j = 0; j < ints.length; j++)
					System.out.print(ints[j]+"\t");
				System.out.println();

				List<String> preconditions = new ArrayList<>();
				BitSet bitSet = actionInstancesPositivePredicatesInPrecondition[i];
				//System.out.print("Preconditions positive ");
				for(int j =0; j < bitSet.length(); j++)
					if(bitSet.get(j)==true) {
						//System.out.print(predicateIdentificationInverse.get(new Integer(j)) +"\t");
						preconditions.add(predicateIdentificationInverse.get(new Integer(j)));
					}
				//System.out.println();

				BitSet preNeg = actionInstancesNegativePredicatesInPrecondition[i];
				//System.out.print("Preconditions Neg");
				for(int j =0; j < preNeg.length(); j++)
					if(preNeg.get(j)==true) {
						//System.out.print(predicateIdentificationInverse.get(new Integer(j)) +"\t");
					}
				//System.out.println();

				List<String> effects = new ArrayList<>();
				BitSet effect = actionInstancesPositivePredicatesInEffects[i];
				//System.out.print("Effects positive");
				for(int j = 0; j < effect.length(); j++) {
					if (effect.get(j) == true)
						//System.out.print(predicateIdentificationInverse.get(new Integer(j)) + "\t");
						effects.add(predicateIdentificationInverse.get(new Integer(j)));
				}
				//System.out.println();

				BitSet negEffect = actionInstancesNegativePredicatesInEffects[i];
				//System.out.print("Effects Negative");
				for(int j = 0; j < effect.length(); j++) {
					if (negEffect.get(j) == true)
						//System.out.print(predicateIdentificationInverse.get(new Integer(j)) + "\t");
						effects.add(predicateIdentificationInverse.get(new Integer(j)));
				}
				//System.out.println();
				//System.out.println();

				for(String precond : preconditions)
					for(String eff : effects)
						writer.write(precond + " " + eff + " " + i + "\n");

				for(String eff : effects)
					for(String eff2 : effects)
						if(!eff.equals(eff2))
							writer.write(eff + " " + eff2 + " " + i + "\n");

				//For new type of incidence graph
				for(String precond : preconditions)
					for(String precond2 : preconditions)
						if(!precond.equals(precond2))
							writer.write(precond+ " "+ precond2 + " "+ i + "\n");


					i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean containsNegationInPrecondition(List<Action> actions) throws Exception {
		for (Action action : actions) {
			if (this.containsNegativePrecondition(action.getPrecondition())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean containsNegativePrecondition(IGoalDescription goal) throws Exception {
		if (goal instanceof AND_GoalDescription) {
			AND_GoalDescription andGoal = (AND_GoalDescription) goal;
			
			for (IGoalDescription subgoal : andGoal.getGoals()) {
				if (subgoal instanceof NOT_GoalDescription) {
					return true;
				} else if (subgoal instanceof Predicate_GoalDescription) {
					//do nothing
				} else {
					throw new Exception("IGoalDescription not known here.");
				}
			}
			return false;
		} else if (goal instanceof NOT_Effect) {
			return true;
		} else if (goal instanceof Predicate_GoalDescription) {
			return false;
		} else {
			throw new Exception("IGoalDescription not known here.");
		}
	}
	
	private boolean containsNegationInEffect(List<Action> actions) throws Exception {
		for (Action action : actions) {
			if (this.containsNegativeEffect(action.getEffect())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean containsNegativeEffect(IEffect effect) throws Exception {
		if (effect instanceof AND_Effect) {
			AND_Effect andEffect = (AND_Effect) effect;
			
			for (IEffect subeffect : andEffect.getEffects()) {
				if (subeffect instanceof NOT_Effect) {
					return true;
				} else if (subeffect instanceof CONDITIONAL_Effect) {
					CONDITIONAL_Effect condEffect = (CONDITIONAL_Effect) subeffect;
					return this.containsNegativeEffect(condEffect.getEffect()) || this.containsNegativePrecondition(condEffect.getCondition());
				} else if (subeffect instanceof Predicate_Effect
							|| subeffect instanceof IncreaseByFunction_Effect
							|| subeffect instanceof IncreaseByNumber_Effect) {
					
					//do nothing
				} else {
					throw new Exception("IEffect not known here.");
				}
			}
			return false;
		} else if (effect instanceof NOT_Effect) {
			return true;
		} else if (effect instanceof CONDITIONAL_Effect) {
			CONDITIONAL_Effect condEffect = (CONDITIONAL_Effect) effect;
			return this.containsNegativeEffect(condEffect.getEffect()) || this.containsNegativePrecondition(condEffect.getCondition());
		} else if (effect instanceof Predicate_Effect
					|| effect instanceof IncreaseByFunction_Effect
					|| effect instanceof IncreaseByNumber_Effect) {
			
			return false;
		} else {
			throw new Exception("IEffect not known here.");
		}
	}
	
	private void calcObjectsForTypename() {
		for (Type type : this.domain.types) {
			List<Integer> objects = this.getObjectsForType(type, new ArrayList<Integer>());
			
			if (logger.isDebugEnabled())
				logger.debug("objects for type '" + type.getName() + "' (" + objects.size() + ") " + objects);
		}
	}
	
	private List<Integer> getObjectsForType(Type type, List<Integer> objectsSoFar) {
		
		List<Integer> objectsForType_type_Only = this.objectsForTypename.get(type.getName());
		
		if (objectsForType_type_Only != null) {
			return objectsForType_type_Only;
		}
		
		objectsForType_type_Only = new ArrayList<Integer>();
		
		for (UPDDLObject o : problem.upddlObjects) {
			if (type.getName().equals(o.getType().getName())) {
				objectsForType_type_Only.add(this.astParser.objectstringToInteger.get(o.getName()));
			}
		}
		
		for (Constant c : this.domain.constants) {
			Type constantType = c.getType();
			
			if (type.getName().equals(constantType.getName())) {
				objectsForType_type_Only.add(this.astParser.objectstringToInteger.get(c.getName()));
			}
		}
		
		for (Type child : type.getChilds()) {
			this.getObjectsForType(child, objectsForType_type_Only);
		}
		
		this.objectsForTypename.put(type.getName(), objectsForType_type_Only);
		
		objectsSoFar.addAll(objectsForType_type_Only);
		return objectsSoFar;
	}
	
	private void groundActionsAndSplitIfBetter(List<Action> actions) throws Exception {
		List<Action> actionsToGround = new ArrayList<Action>();
		
		HashSet<String> varsPrecondition = new HashSet<String>();
		HashSet<String> varsEffect = new HashSet<String>();
		
		int predSize;
		int effectSize;
		
		for (Action action : actions) {
			varsPrecondition.clear();
			varsEffect.clear();
			
			action.getPrecondition().addInvolvedVariablesTo(varsPrecondition);
			action.getEffect().addInvolvedVariablesTo(varsEffect);
			
			predSize = varsPrecondition.size();
			effectSize = varsEffect.size();
			
			if (predSize == effectSize) {
				actionsToGround.add(action);
			} else if (predSize < effectSize) { //TODO split
				actionsToGround.add(action);
				logger.info(action.getName() + ": " + varsPrecondition.size() + " < " + varsEffect.size());
			} else if (predSize > effectSize) { //TODO hypertree decomposition...
				actionsToGround.add(action);
				logger.info(action.getName() + ": TODO make hypertree decomposition for grounding...");
			}
		}
		
		this.groundActionsWithoutSplitting(actionsToGround);
	}
	
//	private void groundActionsAndSplitIfBetter() throws Exception {
//		List<Action> actionSplitted = new ArrayList<Action>();
//		
//		int numGroundedActionsWithSplitting;
//		int numGroundedActionsWithoutSplitting;
//		
//		boolean doCompare;
//		int numParameters;
//		
//		logger.info("number of actions: " + this.domain.actions.size());
//		
//		for (Action action : this.domain.actions) {
//			doCompare = true;
//			actionSplitted.clear();
//			
//			IEffect effect = action.getEffect();
//			
//			if (effect instanceof AND_Effect) {
//				AND_Effect andEffect = (AND_Effect) effect;
//				IEffect[] effects = andEffect.getEffects();
//				
//				for (int i=0; i < effects.length; i++) {
//					actionSplitted.add(new Action(action.getName(), action.getParameters(), action.getPrecondition(), effects[i]));
//				}
//				
//			} else {
//				logger.info("asdfasfadf: " + effect.toString());
//				doCompare = false;
//			}
//			
//			if (doCompare) {
//				numParameters = action.getParameters().size();
//				
//				//TODO anders... siehe bei without splitting
//				
//				List<Action> actionsListToAddWithoutSplitting = new LinkedList<Action>();
//				List<int[]> instantiatedParametersListToAddWithoutSplitting = new LinkedList<int[]>();
//				
//				this.groundAction(actionsListToAddWithoutSplitting, instantiatedParametersListToAddWithoutSplitting, action, new int[numParameters], 0);
//				numGroundedActionsWithoutSplitting = actionsListToAddWithoutSplitting.size();
//				
//				List<Action> actionsListToAddWithSplitting = new LinkedList<Action>();
//				List<int[]> instantiatedParametersListToAddWithSplitting = new LinkedList<int[]>();
//				
//				for (Action a : actionSplitted) {
//					this.groundAction(actionsListToAddWithSplitting, instantiatedParametersListToAddWithSplitting, a, new int[numParameters], 0);
//				}
//				numGroundedActionsWithSplitting = actionsListToAddWithSplitting.size();
//				
//				if (numGroundedActionsWithoutSplitting <= numGroundedActionsWithSplitting) {
//					this.actionInstancesActions.addAll(actionsListToAddWithoutSplitting);
//					this.instanciatedParametersForActionInstances.addAll(instantiatedParametersListToAddWithoutSplitting);
//				} else {
//					this.actionInstancesActions.addAll(actionsListToAddWithSplitting);
//					this.instanciatedParametersForActionInstances.addAll(instantiatedParametersListToAddWithSplitting);
//				}
//			}
//		}
//	}
	
	private void groundActionsWithoutSplitting(List<Action> actions) throws Exception {
		int numParameters;
		
		for (Action action : actions) {
			numParameters = action.getParameters().size();
			
			if (numParameters == 0) {
				throw new Exception("action is already grounded. TODO build Action_Instance and add it...");
//				this.actionInstancesActions.add(action);
//				this.instanciatedParametersForActionInstances.add(null);
			} else {
				this.groundAction(this.actionInstancesActions, this.instanciatedParametersForActionInstances, action, new int[numParameters], 0);
			}
		}
	}
	
	private void groundAction(List<Action> actionsListToAdd, List<int[]> instantiatedParametersListToAdd, Action action, int[] parameterInstancesForLevel, int level) throws Exception {
		//check if timeout
		String msg = Planner.breakBecauseOfTimeout(this.stopwatch1);
		if (msg != null) {
			throw new Exception(msg);
		}
		
		UPDDLObject argument = action.getParameters().get(level);
		List<Integer> possibleValues = this.objectsForTypename.get(argument.getType().getName());
		
		int numParameters = action.getParameters().size();
		
		for (Integer value : possibleValues) {
			parameterInstancesForLevel[level] = value;
			
			if (level == numParameters - 1) { //instantiated all parameters -> create grounded action
				int[] instanciatedVariables = new int[numParameters];
				
				for (int i=0; i < numParameters; i++) {
					instanciatedVariables[i] = parameterInstancesForLevel[i];
				}
				
				if (Planner.USE_CONSISTENT_ACTION_INSTANCES_ONLY) {
					if (this.isConsistent(action, instanciatedVariables)) {
						actionsListToAdd.add(action);
						instantiatedParametersListToAdd.add(instanciatedVariables);
					} else {
						this.numberOfInconsistentActionInstances++;
						instanciatedVariables = null; //help the garbage collector
					}
				} else {
					actionsListToAdd.add(action);
					instantiatedParametersListToAdd.add(instanciatedVariables);
				}
				
				//logger.info(actionInstance);
			} else {
				this.groundAction(actionsListToAdd, instantiatedParametersListToAdd, action, parameterInstancesForLevel, level+1);
			}
		}
	}
	
	public Pair<List<Action>, List<int[]>> getActionInstancesActions() {
		this.reuseablePairOfActionsAndInstantiatedParameters.setFirst(this.actionInstancesActions);
		this.reuseablePairOfActionsAndInstantiatedParameters.setSecond(this.instanciatedParametersForActionInstances);
		
		return this.reuseablePairOfActionsAndInstantiatedParameters;
	}
	
//	public Pair<List<Action>, List<int[]>> getActionInstancesForTime(int time) throws Exception {
//		BitSet newActionInstancesForTimeBitSet = this.activeActionInstancesBitSetForTime.get(time);
//		
//		if (newActionInstancesForTimeBitSet == null) {
////			if (time < 0) {
////				throw new Exception("provide positive time!");
////			}
//			
//			newActionInstancesForTimeBitSet = new BitSet(this.numberOfActions);
//			
//			if (time == 0) {
//				BitSet initiallyTruePredicates = new BitSet(this.numberOfAllFluents);
//				List<Predicate_Instance> initiallyTruePredicatesInstances = this.astParser.getProblem().predicateInstances;
//				
//				//calculate initially true predicates
//				for (Predicate_Instance pi : initiallyTruePredicatesInstances) {
//					this.reuseableStringBuilder.setLength(0);
//					this.addPredicateVariableStringToStringBuilder(pi.predicate, pi.arguments, this.reuseableStringBuilder);
//					Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
//					initiallyTruePredicates.set(var);
//				}
//				
//				this.activePositivePredicatesBitSetForTime.put(0, initiallyTruePredicates);
//				
//				//calculate actions that can be active at time=0
//				
//				BitSet bs = new BitSet(this.numberOfAllFluents);
//				int oldNumTrueBits;
//				int newNumTrueBits;
//				
//				for (int i=0; i < this.numberOfActions; i++) {
//					BitSet preconditionPredicates = this.actionInstancesPositivePredicatesInPrecondition[i];
//					
//					bs.clear();
//					bs.or(preconditionPredicates);
//					oldNumTrueBits = bs.cardinality();
//					bs.and(initiallyTruePredicates);
//					newNumTrueBits = bs.cardinality();
//					
//					if (oldNumTrueBits == newNumTrueBits) {
//						newActionInstancesForTimeBitSet.set(i);
//					}
//				}
//				
//				this.activeActionInstancesBitSetForTime.put(0, newActionInstancesForTimeBitSet);
//				
//				return this.getActionInstanceForBitset(newActionInstancesForTimeBitSet);
//				
//			} else {//time > 0 and not calculated so far
//				if (Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME != Grounder.FIXPOINT_NOT_REACHED) {
//					BitSet actionInstancesBitSetAtFixpoint = this.activeActionInstancesBitSetForTime.get(Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME);
//					return this.getActionInstanceForBitset(actionInstancesBitSetAtFixpoint);
//				}
//				
//				Pair<List<Action>,List<int[]>> oldActionInstances = this.getActionInstancesForTime(time-1); //recursion
//				
//				if (Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME != Grounder.FIXPOINT_NOT_REACHED) {
//					return oldActionInstances;
//				}
//				
//				//calculated for (time-1) and there is no fixpoint at (time-2) i.e. at time-1 the fixpoint was not found
//				
//				
//				//calculate new possibly active predicates for time=time
//				//-> all former possibly active predicates + all positive effects from all action instances at time = time-1
//				
//				BitSet oldActivePredicates = this.activePositivePredicatesBitSetForTime.get(time-1);
//				int numOldActivePredicates = oldActivePredicates.cardinality();
//				
//				BitSet newActivePredicates = new BitSet(this.numberOfAllFluents);
//				
//				//all old possibly active predicates are possible active in the new time-step
//				newActivePredicates.or(oldActivePredicates);
//				
//				//add all positive effects of all actions that could so far be active
//				
//				BitSet oldActionInstancesBitSet = this.activeActionInstancesBitSetForTime.get(time-1);
//				
//				for (int i = oldActionInstancesBitSet.nextSetBit(0); i >= 0; i = oldActionInstancesBitSet.nextSetBit(i+1)) {
//					BitSet bs = this.actionInstancesPositivePredicatesInEffects[i];
//					newActivePredicates.or(bs);
//				}
//				
//				int numNewActivePredicates = newActivePredicates.cardinality();
//				
//				//check if there are no new possibly active predicates --> fixpoint
//				if (numOldActivePredicates == numNewActivePredicates) {
//					newActionInstancesForTimeBitSet = null;
//					newActivePredicates = null;
//					
//					Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME = time-1;
//					
//					this.reuseableStringBuilder.setLength(0);
//					this.reuseableStringBuilder.append("step-grounding fixpoint reached at timestep: ");
//					this.reuseableStringBuilder.append(Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME);
//					this.reuseableStringBuilder.append(" (");
//					this.reuseableStringBuilder.append(numOldActivePredicates);
//					this.reuseableStringBuilder.append(" active actions)");
//					logger.info(this.reuseableStringBuilder.toString());
//					
//					//not needed anymore
//					this.actionInstancesPositivePredicatesInPrecondition = null;
//					this.actionInstancesPositivePredicatesInEffects = null;
//					
//					return oldActionInstances;
//				}
//				
//				//no fixpoint yet...
//				
//				this.activePositivePredicatesBitSetForTime.put(time, newActivePredicates);
//				
//				//possible active actions at time=time are the old possible active actions or...
//				newActionInstancesForTimeBitSet.or(oldActionInstancesBitSet);
//				
//				//...the new ones
//				BitSet bs = new BitSet(this.numberOfAllFluents);
//				int oldNumTrueBits;
//				int newNumTrueBits;
//				
//				for (int i = oldActionInstancesBitSet.nextClearBit(0); i >= 0 && i < this.numberOfActions; i = oldActionInstancesBitSet.nextClearBit(i+1)) {
//					BitSet precondition = this.actionInstancesPositivePredicatesInPrecondition[i];
//					
//					bs.clear();
//					bs.or(precondition);
//					oldNumTrueBits = bs.cardinality();
//					bs.and(newActivePredicates);
//					newNumTrueBits = bs.cardinality();
//					
//					if (oldNumTrueBits == newNumTrueBits) {//precondition satisfied
//						newActionInstancesForTimeBitSet.set(i);
//					}
//				}
//				
//				this.activeActionInstancesBitSetForTime.put(time, newActionInstancesForTimeBitSet);
//				
//				return this.getActionInstanceForBitset(newActionInstancesForTimeBitSet);
//			}
//		} else {
//			return this.getActionInstanceForBitset(newActionInstancesForTimeBitSet);
//		}
//	}
	
	public int getNextTimeStepForNextActionInstances() {
		return this.timeOfCalculatedActionInstances + 1;
	}
	
	public Pair<List<Action>, List<int[]>> getNextActionInstancesForTime() throws Exception {
		if (this.timeOfCalculatedActionInstances == -1) { //old values not yet initialized
			//calculate initially true predicates
			List<Predicate_Instance> initiallyTruePredicatesInstances = this.astParser.getProblem().predicateInstances;
			
			for (Predicate_Instance pi : initiallyTruePredicatesInstances) {
				this.reuseableStringBuilder.setLength(0);
				this.addPredicateVariableStringToStringBuilder(pi.predicate, pi.arguments, this.reuseableStringBuilder);
				Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
				this.currentActivePositivePredicatesBitSet.set(var);
			}
			
			//calculate initially unknown predicates
			List<Predicate_Instance> initiallyUnknownPredicatesInstances = this.astParser.getProblem().unknownPredicateInstances;
			
			for (Predicate_Instance pi : initiallyUnknownPredicatesInstances) {
				this.reuseableStringBuilder.setLength(0);
				this.addPredicateVariableStringToStringBuilder(pi.predicate, pi.arguments, this.reuseableStringBuilder);
				Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
				this.currentActiveUnknownPredicatesBitSet.set(var);
			}
			
			if (this.useNegativePredicatesForPhaseGrounding) {
				//calculate initially false predicates
				this.currentActiveNegativePredicatesBitSet.flip(0, this.numberOfAllFluents);
				this.currentActiveNegativePredicatesBitSet.xor(this.currentActivePositivePredicatesBitSet);
				this.currentActiveNegativePredicatesBitSet.xor(this.currentActiveUnknownPredicatesBitSet);
			}
			
			//TODO
			//add all predicates from oneofs to initially true and false predicates
//			for(List<Predicate_Instance> oneof : this.astParser.getProblem().oneofs) {
//				for(Predicate_Instance pi : oneof) {
//					this.reuseableStringBuilder.setLength(0);
//					this.addPredicateVariableStringToStringBuilder(pi.predicate, pi.arguments, this.reuseableStringBuilder);
//					Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
//					this.currentActivePositivePredicatesBitSet.set(var);
//					this.currentActiveNegativePredicatesBitSet.set(var);
//				}
//			}
			
			
			if (Planner.USE_PHASE_GROUNDING_FOR_ACTION_INSTANCES) {
				//calculate actions that can be active at time=0
				
				BitSet bs = new BitSet(this.numberOfAllFluents);
				int oldNumTrueBits;
				int newNumTrueBits;
				int oldNumFalseBits;
				int newNumFalseBits;
				
				for (int i=0; i < this.numberOfActions; i++) {
					BitSet truePreconditionPredicates = this.actionInstancesPositivePredicatesInPrecondition[i];
					
					oldNumTrueBits = truePreconditionPredicates.cardinality();
					bs.clear();
					if (this.astParser.instanceHasUnknowns()) bs.or(this.currentActiveUnknownPredicatesBitSet);
					bs.or(this.currentActivePositivePredicatesBitSet);
					bs.and(truePreconditionPredicates);
					newNumTrueBits = bs.cardinality();
					
					if (this.useNegativePredicatesForPhaseGrounding) {
						BitSet falsePreconditionPredicates = this.actionInstancesNegativePredicatesInPrecondition[i];
						
						oldNumFalseBits = falsePreconditionPredicates.cardinality();
						bs.clear();
						if (this.astParser.instanceHasUnknowns()) bs.or(this.currentActiveUnknownPredicatesBitSet);
						bs.or(this.currentActiveNegativePredicatesBitSet);
						bs.and(falsePreconditionPredicates);
						newNumFalseBits = bs.cardinality();
						
						if (oldNumTrueBits == newNumTrueBits && oldNumFalseBits == newNumFalseBits) {
							this.currentActiveActionInstancesBitSet.set(i);
						}
					} else if (oldNumTrueBits == newNumTrueBits) {
						this.currentActiveActionInstancesBitSet.set(i);
					}
				}
				
				bs = null;
			} else { // Planner.USE_PHASE_GROUNDING_FOR_ACTION_INSTANCES == false
				this.currentActiveActionInstancesBitSet.set(0,this.numberOfActions-1);
			}
			
			this.timeOfCalculatedActionInstances = 0;
			
			return this.getActionInstanceForBitset(this.currentActiveActionInstancesBitSet);
		} else {//time > 0 and not calculated so far
			if (Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME != Grounder.FIXPOINT_NOT_REACHED) {
				return this.actionInstancesAtFixpoint;
			}
			
			int oldNumTrueBits;
			int newNumTrueBits;
			int oldNumFalseBits;
			int newNumFalseBits;
			
			//calculate new possibly active predicates
			//-> all former possibly active predicates + all positive effects from all action instances at time = time-1
			
			BitSet bs = new BitSet(this.numberOfAllFluents);
			
			if (this.astParser.instanceHasUnknowns()) bs.or(this.currentActiveUnknownPredicatesBitSet);
			bs.or(this.currentActivePositivePredicatesBitSet);
			
			oldNumTrueBits = bs.cardinality();
			
			for (int i = this.currentActiveActionInstancesBitSet.nextSetBit(0); i >= 0; i = this.currentActiveActionInstancesBitSet.nextSetBit(i+1)) {
				bs.or(this.actionInstancesPositivePredicatesInEffects[i]);
				this.currentActivePositivePredicatesBitSet.or(this.actionInstancesPositivePredicatesInEffects[i]);
			}
			
			newNumTrueBits = bs.cardinality();
			
			//check if there are no new possibly active predicates --> fixpoint
			if (this.useNegativePredicatesForPhaseGrounding) {
				bs.clear();
				if (this.astParser.instanceHasUnknowns()) bs.or(this.currentActiveUnknownPredicatesBitSet);
				bs.or(this.currentActiveNegativePredicatesBitSet);
				
				oldNumFalseBits = bs.cardinality();
				
				for (int i = this.currentActiveActionInstancesBitSet.nextSetBit(0); i >= 0; i = this.currentActiveActionInstancesBitSet.nextSetBit(i+1)) {
					bs.or(this.actionInstancesNegativePredicatesInEffects[i]);
					this.currentActiveNegativePredicatesBitSet.or(this.actionInstancesNegativePredicatesInEffects[i]);
				}
				
				newNumFalseBits = bs.cardinality();
				
				if (oldNumTrueBits == newNumTrueBits && oldNumFalseBits == newNumFalseBits) {
					Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME = this.timeOfCalculatedActionInstances;
					
					this.reuseableStringBuilder.setLength(0);
					this.reuseableStringBuilder.append("optimistic-state-grounding: fixpoint reached at time=");
					this.reuseableStringBuilder.append(Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME);
					this.reuseableStringBuilder.append(" (");
					this.reuseableStringBuilder.append(this.currentActiveActionInstancesBitSet.cardinality());
					this.reuseableStringBuilder.append(" active actions, ");
					this.reuseableStringBuilder.append(oldNumTrueBits + oldNumFalseBits);
					this.reuseableStringBuilder.append(" active predicates (");
					this.reuseableStringBuilder.append(oldNumTrueBits);
					this.reuseableStringBuilder.append(" positive + ");
					this.reuseableStringBuilder.append(oldNumFalseBits);
					this.reuseableStringBuilder.append(" negative))");
					
					String msg = this.reuseableStringBuilder.toString();
					logger.info(msg);
					Planner.FINISHEDFILE_BUFFEREDWRITER.write(msg + "\n\n");
					
					//not needed anymore
//					this.actionInstancesPositivePredicatesInPrecondition = null;
//					this.actionInstancesPositivePredicatesInEffects = null;
					
					this.actionInstancesAtFixpoint = this.getActionInstanceForBitset(this.currentActiveActionInstancesBitSet);
					
//					this.currentActiveActionInstancesBitSet = null;
//					this.currentActivePositivePredicatesBitSet = null;
//					this.currentActiveNegativePredicatesBitSet = null;
					
					return this.actionInstancesAtFixpoint;
				}
			} else {
				if (oldNumTrueBits == newNumTrueBits) {
					Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME = this.timeOfCalculatedActionInstances;
					
					this.reuseableStringBuilder.setLength(0);
					this.reuseableStringBuilder.append("optimistic-state-grounding: fixpoint reached at time=");
					this.reuseableStringBuilder.append(Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME);
					this.reuseableStringBuilder.append(" (");
					this.reuseableStringBuilder.append(this.currentActiveActionInstancesBitSet.cardinality());
					this.reuseableStringBuilder.append(" active actions, ");
					this.reuseableStringBuilder.append(oldNumTrueBits);
					this.reuseableStringBuilder.append(" active predicates)");
					
					String msg = this.reuseableStringBuilder.toString();
					logger.info(msg);
					Planner.FINISHEDFILE_BUFFEREDWRITER.write(msg + "\n\n");
					
					//not needed anymore
	//				this.actionInstancesPositivePredicatesInPrecondition = null;
	//				this.actionInstancesPositivePredicatesInEffects = null;
					
					this.actionInstancesAtFixpoint = this.getActionInstanceForBitset(this.currentActiveActionInstancesBitSet);
					
//					this.currentActiveActionInstancesBitSet = null;
//					this.currentActivePositivePredicatesBitSet = null;
//					this.currentActiveNegativePredicatesBitSet = null;
					
					return this.actionInstancesAtFixpoint;
				}
			}
			
			//no fixpoint yet...
			
			//possible active actions at time=time are the old possible active actions or...
			//...the new ones
			
			for (int i = this.currentActiveActionInstancesBitSet.nextClearBit(0); i >= 0 && i < this.numberOfActions; i = this.currentActiveActionInstancesBitSet.nextClearBit(i+1)) {
				BitSet posPrecondition = this.actionInstancesPositivePredicatesInPrecondition[i];
				
				oldNumTrueBits = posPrecondition.cardinality();
				bs.clear();
				if (this.astParser.instanceHasUnknowns()) bs.or(this.currentActiveUnknownPredicatesBitSet);
				bs.or(this.currentActivePositivePredicatesBitSet);
				bs.and(posPrecondition);
				newNumTrueBits = bs.cardinality();
				
				if (this.useNegativePredicatesForPhaseGrounding) {
					BitSet negPrecondition = this.actionInstancesNegativePredicatesInPrecondition[i];
					
					oldNumFalseBits = negPrecondition.cardinality();
					bs.clear();
					if (this.astParser.instanceHasUnknowns()) bs.or(this.currentActiveUnknownPredicatesBitSet);
					bs.or(this.currentActiveNegativePredicatesBitSet);
					bs.and(negPrecondition);
					newNumFalseBits = bs.cardinality();
					
					if (oldNumTrueBits == newNumTrueBits && oldNumFalseBits == newNumFalseBits) {//precondition satisfied
						this.currentActiveActionInstancesBitSet.set(i);
					}
				} else {
					if (oldNumTrueBits == newNumTrueBits) {//precondition satisfied
						this.currentActiveActionInstancesBitSet.set(i);
					}
				}
			}
			
			this.timeOfCalculatedActionInstances += 1;
			
			return this.getActionInstanceForBitset(this.currentActiveActionInstancesBitSet);
		}
	}
	
	private Pair<List<Action>,List<int[]>> getActionInstanceForBitset(BitSet bs) {
		this.reuseableActions.clear();
		this.reuseableInstanciatedParametersOfAction.clear();
		
		for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1)) {
			this.reuseableActions.add(this.actionInstancesActions.get(i));
			this.reuseableInstanciatedParametersOfAction.add(this.instanciatedParametersForActionInstances.get(i));
		}
		
		this.reuseablePairOfActionsAndInstantiatedParameters.setFirst(this.reuseableActions);
		this.reuseablePairOfActionsAndInstantiatedParameters.setSecond(this.reuseableInstanciatedParametersOfAction);
		
		return this.reuseablePairOfActionsAndInstantiatedParameters;
	}
	
	public void addPredicateVariableStringToStringBuilder(Predicate predicate, List<UPDDLObject> arguments, StringBuilder sb) {
		sb.append(predicate.getName()).append("(");
		
		for (int i=0; i < arguments.size(); i++) {
			String arg = arguments.get(i).getName();
			sb.append(arg);
			
			if (i+1 < arguments.size()) {
				sb.append(",");
			}
		}
		
		sb.append(")");
	}
	
	private void initActionInstancesPredicatesInEffects() throws Exception {
		this.actionInstancesPositivePredicatesInEffects = new BitSet[this.numberOfActions];
		this.actionInstancesNegativePredicatesInEffects = new BitSet[this.numberOfActions];
		
		BitSet positivePredicates;
		BitSet negativePredicates;
		
		for (int i=0; i < this.numberOfActions; i++) {
			Action action = this.actionInstancesActions.get(i);
			
			positivePredicates = new BitSet(this.numberOfAllFluents);
			negativePredicates = new BitSet(this.numberOfAllFluents);
			
			this.addActionInstanceSetOfEffects(action, this.instanciatedParametersForActionInstances.get(i), action.getEffect(), positivePredicates, negativePredicates, false);
			
			this.actionInstancesPositivePredicatesInEffects[i] = positivePredicates;
			this.actionInstancesNegativePredicatesInEffects[i] = negativePredicates;
		}
	}
	
	private void initActionInstancesPredicatesInPrecondition() throws Exception {
		this.actionInstancesPositivePredicatesInPrecondition = new BitSet[this.numberOfActions];
		
		if (this.useNegativePredicatesForPhaseGrounding) {
			this.actionInstancesNegativePredicatesInPrecondition = new BitSet[this.numberOfActions];
		}
		
		BitSet positivePredicates;
		BitSet negativePredicates;

		System.out.println("Actions "+ numberOfActions+"\t"+ "Fluents "+ numberOfAllFluents);
		for (int i=0; i < this.numberOfActions; i++) {
			//System.out.println(i);
			Action action = this.actionInstancesActions.get(i);
			positivePredicates = new BitSet(this.numberOfAllFluents);
			negativePredicates = null;
			
			if (this.useNegativePredicatesForPhaseGrounding) {
				negativePredicates = new BitSet(this.numberOfAllFluents);
			}
			
			this.addActionInstanceSetOfPreconditions(action, this.instanciatedParametersForActionInstances.get(i), action.getPrecondition(), positivePredicates, negativePredicates);
			
			this.actionInstancesPositivePredicatesInPrecondition[i] = positivePredicates;
			
			if (this.useNegativePredicatesForPhaseGrounding) {
				this.actionInstancesNegativePredicatesInPrecondition[i] = negativePredicates;
			}
		}
	}
	
	private void addActionInstanceSetOfPreconditions(Action action, 
														int[] instanciatedParametersOfAction, 
														IGoalDescription goal, 
														BitSet requiredTruePredicates,
														BitSet requiredFalsePredicates
														) throws Exception {
		
		if (goal == null) { 
			return;
		}
	
		if (goal instanceof AND_GoalDescription) {
			AND_GoalDescription andGoal = (AND_GoalDescription) goal;
			
			for (IGoalDescription subgoal : andGoal.getGoals()) {
				if (subgoal instanceof NOT_GoalDescription) {
					NOT_GoalDescription notGoal = (NOT_GoalDescription) subgoal;
					IGoalDescription g = notGoal.getGoal();
					
					if (g instanceof Predicate_GoalDescription) {
						Predicate_GoalDescription pg = (Predicate_GoalDescription) g;
						
						this.reuseableStringBuilder.setLength(0);
						this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pg.getPredicate(), pg.getArguments(), this.reuseableStringBuilder);
						Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
						
						requiredFalsePredicates.set(var);
					} else {
						throw new Exception("IGoalDescription not known here.");
					}
				} else if (subgoal instanceof Predicate_GoalDescription) {
					Predicate_GoalDescription pg = (Predicate_GoalDescription) subgoal;
					
					this.reuseableStringBuilder.setLength(0);
					this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pg.getPredicate(), pg.getArguments(), this.reuseableStringBuilder);
					Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
					requiredTruePredicates.set(var);
				} else {
					throw new Exception("IGoalDescription not known here.");
				}
			}
		} else if (goal instanceof NOT_GoalDescription) {
			NOT_GoalDescription notGoal = (NOT_GoalDescription) goal;
			IGoalDescription g = notGoal.getGoal();
			
			if (g instanceof Predicate_GoalDescription) {
				Predicate_GoalDescription pg = (Predicate_GoalDescription) g;
				
				this.reuseableStringBuilder.setLength(0);
				this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pg.getPredicate(), pg.getArguments(), this.reuseableStringBuilder);
				Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
				
				requiredFalsePredicates.set(var);
			} else {
				throw new Exception("IGoalDescription not known here.");
			}
		} else if (goal instanceof Predicate_GoalDescription) {
			Predicate_GoalDescription pg = (Predicate_GoalDescription) goal;
			
			this.reuseableStringBuilder.setLength(0);
			this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pg.getPredicate(), pg.getArguments(), this.reuseableStringBuilder);
			Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
			requiredTruePredicates.set(var);
		} else {
			throw new Exception("IGoalDescription not known here.");
		}
	}
	
	public Set<String> getGroundedNeversAsString() {
		return this.groundedNeversAsString;
	}
	
	private void groundNeversAsString() {
		this.groundedNeversAsString = new HashSet<String>();
		
		if (this.domain.nevers != null) {
			for (Predicate predicate : this.domain.nevers) {
				int numArguments = predicate.getArguments().size();
				
				if (numArguments > 0) {
					int[] parameterInstancesForLevel = new int[numArguments];
					this.addGroundedPredicatesToSet(predicate, this.groundedNeversAsString, parameterInstancesForLevel, new HashMap<String, Integer>(), 0);
				} else {
					this.groundedNeversAsString.add(predicate.getName());
				}
			}
			
			if (logger.isDebugEnabled()) {
				logger.info("groundedNeversAsString: " + this.groundedNeversAsString);
			}
		}
	}
	
	private void addGroundedPredicatesToSet(	Predicate pred, 
												Set<String> setToAdd,
												int[] parameterInstancesForLevel,
												Map<String, Integer> currentParameterInstanceForVariablename,
												int level) {
		
		UPDDLObject argument = pred.getArguments().get(level);
		
		List<Integer> possibleValues;
		
		if (argument.isVariable()) {
			String variableName = argument.getName();
			Integer instantiatedValue = currentParameterInstanceForVariablename.get(variableName);
			
			if (instantiatedValue == null) {
				possibleValues = this.objectsForTypename.get(argument.getType().getName());
			} else {
				possibleValues = new ArrayList<Integer>();
				possibleValues.add(instantiatedValue);
			}
			
		} else {
			possibleValues = new ArrayList<Integer>();
			possibleValues.add(Grounder.ARGUMENT_IS_CONSTANT);
		}
		
		int numParameters = pred.getArguments().size();
		
		for (Integer value : possibleValues) {
			parameterInstancesForLevel[level] = value;
			
			if (level == numParameters - 1) { //instantiated all parameters -> create grounded predicate
				this.reuseableStringBuilder.setLength(0);
				this.reuseableStringBuilder.append(pred.getName());
				this.reuseableStringBuilder.append("(");
				
				for (int i=0; i < numParameters; i++) {
					int paramaterInstance = parameterInstancesForLevel[i];
					String s;
					
					if (paramaterInstance == Grounder.ARGUMENT_IS_CONSTANT) {
						s = pred.getArguments().get(i).getName();
					} else {
						s = this.astParser.integerToobjectstring.get(paramaterInstance);
					}
					
					this.reuseableStringBuilder.append(s);
					
					if (i+1 < numParameters) {
						this.reuseableStringBuilder.append(",");
					}
				}
				
				this.reuseableStringBuilder.append(")");
				
				String name = this.reuseableStringBuilder.toString();
				setToAdd.add(name);
				this.putPredicatenameToIdentificationMapping(name);
			} else {
				currentParameterInstanceForVariablename.put(argument.getName(), value);
				this.addGroundedPredicatesToSet(pred, setToAdd, parameterInstancesForLevel, currentParameterInstanceForVariablename, level+1);
			}
		}
	}
	
	public Set<String> getGroundedFluentsAsString() {
		return this.groundedFluentsAsString;
	}
	
	private void groundFluentsAsString() {
		this.groundedFluentsAsString = new HashSet<String>();
		
		for (Predicate pred : this.domain.predicates) {
			int numArguments = pred.getArguments().size();
			
			if (numArguments > 0) {
				int[] parameterInstancesForLevel = new int[numArguments];
				this.addGroundedPredicatesToSet(pred, this.groundedFluentsAsString, parameterInstancesForLevel, new HashMap<String, Integer>(), 0);
			} else {
				String name = pred.getName();
				this.groundedFluentsAsString.add(name);
				this.putPredicatenameToIdentificationMapping(name);
			}
		}
	}
	
	private boolean isConsistent(Action action, int[] instanciatedParametersOfAction) throws Exception {
		BitSet positiveEffects = new BitSet(this.numberOfAllFluents);
		BitSet negativeEffects = new BitSet(this.numberOfAllFluents);
		
		this.addActionInstanceSetOfEffects(action, instanciatedParametersOfAction, action.getEffect(), positiveEffects, negativeEffects, false);
		
		positiveEffects.and(negativeEffects);
		
		int i = positiveEffects.cardinality();
		
		positiveEffects = null;
		negativeEffects = null;
		
		if (i > 0) {
			return false;
		}
		
		return true;
	}
	
	private void addActionInstanceSetOfEffects(Action action, 
												int[] instanciatedParametersOfAction, 
												IEffect effect, 
												BitSet positiveEffects, 
												BitSet negativeEffects,
												boolean partOfConditionalEffect) 
											throws Exception {
		
		if (effect instanceof AND_Effect) {
			AND_Effect andEffect = (AND_Effect) effect;
			
			for (IEffect subeffect : andEffect.getEffects()) {
				if (subeffect instanceof NOT_Effect) {
					NOT_Effect notEffect = (NOT_Effect) subeffect;
					IEffect e = notEffect.getEffect();
					
					if (e instanceof Predicate_Effect) {
						Predicate_Effect pe = (Predicate_Effect) e;
						
						this.reuseableStringBuilder.setLength(0);
						this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pe.getPredicate(), pe.getArguments(), this.reuseableStringBuilder);
						Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
						
						negativeEffects.set(var);
					} else {
						throw new Exception("IEffect not known here.");
					}
				} else if (subeffect instanceof CONDITIONAL_Effect && !partOfConditionalEffect) {
					CONDITIONAL_Effect condEffect = (CONDITIONAL_Effect) subeffect;
					this.addActionInstanceSetOfEffects(action, instanciatedParametersOfAction, condEffect.getEffect(), positiveEffects, negativeEffects, true);
				} else if (subeffect instanceof Predicate_Effect) {
					Predicate_Effect pe = (Predicate_Effect) subeffect;
					
					this.reuseableStringBuilder.setLength(0);
					this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pe.getPredicate(), pe.getArguments(), this.reuseableStringBuilder);
					Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
					
					positiveEffects.set(var);
				} else if (subeffect instanceof IncreaseByFunction_Effect) {
					if (logger.isDebugEnabled()) {
						logger.debug("ignoring effect: " + subeffect.toString());
					}
				} else if (subeffect instanceof IncreaseByNumber_Effect) {
					if (logger.isDebugEnabled()) {
						logger.debug("ignoring effect: " + subeffect.toString());
					}
				} else {
					throw new Exception("IEffect not known here.");
				}
			}
		} else if (effect instanceof NOT_Effect) {
			NOT_Effect notEffect = (NOT_Effect) effect;
			IEffect e = notEffect.getEffect();
			
			if (e instanceof Predicate_Effect) {
				Predicate_Effect pe = (Predicate_Effect) e;
				
				this.reuseableStringBuilder.setLength(0);
				this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pe.getPredicate(), pe.getArguments(), this.reuseableStringBuilder);
				Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
				
				negativeEffects.set(var);
			} else {
				throw new Exception("IEffect not known here.");
			}
		} else if (effect instanceof CONDITIONAL_Effect && !partOfConditionalEffect) {
			CONDITIONAL_Effect condEffect = (CONDITIONAL_Effect) effect;
			this.addActionInstanceSetOfEffects(action, instanciatedParametersOfAction, condEffect.getEffect(), positiveEffects, negativeEffects, true);
		} else if (effect instanceof Predicate_Effect) {
			Predicate_Effect pe = (Predicate_Effect) effect;
			
			this.reuseableStringBuilder.setLength(0);
			this.addPredicateStringToStringBuilder(action, instanciatedParametersOfAction, pe.getPredicate(), pe.getArguments(), this.reuseableStringBuilder);
			Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
			
			positiveEffects.set(var);
		} else if (effect instanceof IncreaseByFunction_Effect) {
			if (logger.isDebugEnabled()) {
				logger.debug("ignoring effect: " + effect.toString());
			}
		} else if (effect instanceof IncreaseByNumber_Effect) {
			if (logger.isDebugEnabled()) {
				logger.debug("ignoring effect: " + effect.toString());
			}
		} else {
			throw new Exception("IEffect not known here.");
		}
	}
	
	public void addPredicateStringToStringBuilder(Action action, 
													int[] instanciatedParametersOfAction, 
													Predicate predicate, 
													List<UPDDLObject> predicateArguments, 
													StringBuilder sb) 
												throws Exception {
		
		sb.append(predicate.getName()).append("(");
		
		List<UPDDLObject> arguments = predicateArguments;
		List<UPDDLObject> actionParameters = action.getParameters();
		
		int numArguments = arguments.size();
		int numActionParameters = actionParameters.size();
		
		for (int i=0; i < numArguments; i++) {
			String arg = arguments.get(i).getName();
			
			if (arguments.get(i).isVariable()) {
				int indexFound = -1;
				
				for (int j=0; j < numActionParameters; j++) {
					if (arg.equals(actionParameters.get(j).getName())) {
						indexFound = j;
						break;
					}
				}
				
				if (indexFound == -1) {
					throw new Exception(arg + " not found in action's parameters.");
				}
				
				int value = instanciatedParametersOfAction[indexFound];
				String v = this.astParser.integerToobjectstring.get(value);
				
				sb.append(v);
			} else {
				sb.append(arg);
			}
			
			if (i+1 < arguments.size()) {
				sb.append(",");
			}
		}
		
		sb.append(")");
	}
	
	private Integer putPredicatenameToIdentificationMapping(String name) {
		Integer result = this.predicateIdentification.get(name);
		
		if (result == null) {
			Integer nextIndex = this.predicateIdentification.size();// + 1; start with 0, so no "+1"
			this.predicateIdentification.put(name, nextIndex);
			this.predicateIdentificationInverse.put(nextIndex, name);
			
			return nextIndex;
		}
		
		return result;
	}
	
	public int getNumberOfInconsistentActionInstances() {
		return this.numberOfInconsistentActionInstances;
	}
	
	public int calculateMinPlanlength(int maxPlanlength) throws Exception {
		this.resetNextActionInstancesForTime();
		
		BitSet bs = new BitSet(this.numberOfAllFluents);
		BitSet pos = new BitSet(this.numberOfAllFluents);
		BitSet neg = new BitSet(this.numberOfAllFluents);
		
		this.calcGoalBitSet(pos, neg);
		
		int maxPlanlengthToTry = maxPlanlength + 1;
		
		int oldNumTrueBits;
		int newNumTrueBits;
		int oldNumFalseBits = 0;
		int newNumFalseBits = 0;
		
		for (int planlength = 0; planlength <= maxPlanlengthToTry; planlength++) {
			this.getNextActionInstancesForTime();
			
			if (planlength == 0) {
				int initiallyFulfilledPositiveGoalPredicates = 0;
				int initiallyFulfilledNegativeGoalPredicates = 0;
				
				bs.clear();
				bs.or(this.currentActivePositivePredicatesBitSet);
				bs.and(pos);
				initiallyFulfilledPositiveGoalPredicates = bs.cardinality();
				
				if (this.useNegativePredicatesForPhaseGrounding) {
					bs.clear();
					bs.or(this.currentActiveNegativePredicatesBitSet);
					bs.and(neg);
					initiallyFulfilledNegativeGoalPredicates = bs.cardinality();
				}
				
				int maxGoalFluentsInEffect = 0;
				int temp;
				
				for (int i=0; i < this.numberOfActions; i++) {
					temp = 0;
					bs.clear();
					bs.or(this.actionInstancesPositivePredicatesInEffects[i]);
					bs.and(pos);
					temp = bs.cardinality();
					
					bs.clear();
					bs.or(this.actionInstancesNegativePredicatesInEffects[i]);
					bs.and(neg);
					temp += bs.cardinality();
					
					if (temp > maxGoalFluentsInEffect) {
						maxGoalFluentsInEffect = temp;
					}
				}
				
				if (maxGoalFluentsInEffect <= 0) {
					throw new Exception("There is no action that actually can make the goal true (maxGoalFluentsInEffect == 0 in Grounder.calculateMinPlanlength()).");
				}
				
				temp = pos.cardinality() + neg.cardinality() - initiallyFulfilledPositiveGoalPredicates - initiallyFulfilledNegativeGoalPredicates;
				double x = (double) temp / maxGoalFluentsInEffect;
				int y = (int) Math.ceil(x);
				
				if (y > maxPlanlength) {
					return y;
				}
				
				logger.info("minimum planlength according to maxGoalFluentsInEffect: " + y);
				
				while (planlength != y) {
					this.getNextActionInstancesForTime();
					planlength++;
				}
			}
			
			if (this.timeOfCalculatedActionInstances > planlength) {
				throw new Exception("error in Grounder.calculateMinPlanlength(...)");
			}
			
			bs.clear();
			bs.or(pos);
			oldNumTrueBits = bs.cardinality();
			bs.and(this.currentActivePositivePredicatesBitSet);
			newNumTrueBits = bs.cardinality();
			
			if (this.useNegativePredicatesForPhaseGrounding) {
				bs.clear();
				bs.or(neg);
				oldNumFalseBits = bs.cardinality();
				bs.and(this.currentActiveNegativePredicatesBitSet);
				newNumFalseBits = bs.cardinality();
			}
			
			if (oldNumTrueBits == newNumTrueBits && oldNumFalseBits == newNumFalseBits) {
				this.minPlanlength = planlength;
				return this.minPlanlength;
			}
		}
		
		this.minPlanlength = maxPlanlengthToTry;
		return this.minPlanlength;
	}
	
	private void calcGoalBitSet(BitSet pos, BitSet neg) throws Exception {
		IGoalDescription goal = this.problem.goal;
		
		if (goal instanceof AND_GoalDescription) {
			AND_GoalDescription andGoal = (AND_GoalDescription) goal;
			
			for (IGoalDescription subgoal : andGoal.getGoals()) {
				if (subgoal instanceof Predicate_GoalDescription) {
					Predicate_GoalDescription predGoal = (Predicate_GoalDescription) subgoal;
					
					this.reuseableStringBuilder.setLength(0);
					this.addPredicateVariableStringToStringBuilder(predGoal.getPredicate(), predGoal.getArguments(), this.reuseableStringBuilder);
					Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
					pos.set(var);
				} else if (subgoal instanceof NOT_GoalDescription) {
					NOT_GoalDescription notGoal = (NOT_GoalDescription) subgoal;
					IGoalDescription g = notGoal.getGoal();
					
					if (g instanceof Predicate_GoalDescription) {
						Predicate_GoalDescription predGoal = (Predicate_GoalDescription) g;
						
						this.reuseableStringBuilder.setLength(0);
						this.addPredicateVariableStringToStringBuilder(predGoal.getPredicate(), predGoal.getArguments(), this.reuseableStringBuilder);
						Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
						neg.set(var);
					} else {
						throw new Exception("IGoaldescription not known here.");
					}
				} else {
					throw new Exception("IGoaldescription not known here.");
				}
			}
		} else if (goal instanceof Predicate_GoalDescription) {
			Predicate_GoalDescription predGoal = (Predicate_GoalDescription) goal;
			
			this.reuseableStringBuilder.setLength(0);
			this.addPredicateVariableStringToStringBuilder(predGoal.getPredicate(), predGoal.getArguments(), this.reuseableStringBuilder);
			Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
			pos.set(var);
		} else if (goal instanceof NOT_GoalDescription) {
			NOT_GoalDescription notGoal = (NOT_GoalDescription) goal;
			IGoalDescription g = notGoal.getGoal();
			
			if (g instanceof Predicate_GoalDescription) {
				Predicate_GoalDescription predGoal = (Predicate_GoalDescription) g;
				
				this.reuseableStringBuilder.setLength(0);
				this.addPredicateVariableStringToStringBuilder(predGoal.getPredicate(), predGoal.getArguments(), this.reuseableStringBuilder);
				Integer var = this.putPredicatenameToIdentificationMapping(this.reuseableStringBuilder.toString());
				neg.set(var);
			} else {
				throw new Exception("IGoaldescription not known here.");
			}
		} else {
			throw new Exception("IGoaldescription not known here.");
		}
	}
	
	public void resetNextActionInstancesForTime() {
		this.timeOfCalculatedActionInstances = -1;
		if (this.currentActiveActionInstancesBitSet != null) this.currentActiveActionInstancesBitSet.clear();
		if (this.currentActivePositivePredicatesBitSet != null) this.currentActivePositivePredicatesBitSet.clear();
		if (this.currentActiveUnknownPredicatesBitSet != null) this.currentActiveUnknownPredicatesBitSet.clear();
		if (this.currentActiveNegativePredicatesBitSet != null) this.currentActiveNegativePredicatesBitSet.clear();
		Grounder.STEP_GROUNDING_FIXPOINT_AT_TIME = Grounder.FIXPOINT_NOT_REACHED;
	}
	
	public ExplanatoryFrameAxiomsVariablePackage getExplanatoryFrameAxiomsVariablePackage() {
		return new ExplanatoryFrameAxiomsVariablePackage(this.actionInstancesPositivePredicatesInPrecondition,
															this.actionInstancesNegativePredicatesInPrecondition,
															this.actionInstancesPositivePredicatesInEffects,
															this.actionInstancesNegativePredicatesInEffects,
															this.currentActiveActionInstancesBitSet,
															this.actionInstancesActions,
															this.instanciatedParametersForActionInstances,
															this.currentActivePositivePredicatesBitSet,
															this.currentActiveUnknownPredicatesBitSet,
															this.currentActiveNegativePredicatesBitSet,
															this.predicateIdentification,
															this.predicateIdentificationInverse,
															this.numberOfAllFluents);
	}
	
	public class ExplanatoryFrameAxiomsVariablePackage {
		public BitSet[] actionInstancesPositivePredicatesInPrecondition;
		public BitSet[] actionInstancesNegativePredicatesInPrecondition;
		public BitSet[] actionInstancesPositivePredicatesInEffects;
		public BitSet[] actionInstancesNegativePredicatesInEffects;
		
		public BitSet currentActiveActionInstancesBitSet;
		public List<Action> actionInstancesActions;
		public List<int[]> instanciatedParametersForActionInstances;
		public BitSet currentActivePositivePredicatesBitSet;
		public BitSet currentActiveUnknownPredicatesBitSet;
		public BitSet currentActiveNegativePredicatesBitSet;
		
		public Map<String, Integer> predicateIdentification;
		public Map<Integer, String> predicateIdentificationInverse;
		
		public int numberOfAllFluents;
		
		public ExplanatoryFrameAxiomsVariablePackage(BitSet[] actionInstancesPositivePredicatesInPrecondition,
														BitSet[] actionInstancesNegativePredicatesInPrecondition,
														BitSet[] actionInstancesPositivePredicatesInEffects,
														BitSet[] actionInstancesNegativePredicatesInEffects,
														BitSet currentActiveActionInstancesBitSet,
														List<Action> actionInstancesActions,
														List<int[]> instanciatedParametersForActionInstances,
														BitSet currentActivePositivePredicatesBitSet,
														BitSet currentActiveUnknownPredicatesBitSet,
														BitSet currentActiveNegativePredicatesBitSet,
														Map<String, Integer> predicateIdentification,
														Map<Integer, String> predicateIdentificationInverse,
														int numberOfAllFluents) {
			
			this.actionInstancesPositivePredicatesInPrecondition = actionInstancesPositivePredicatesInPrecondition;
			this.actionInstancesNegativePredicatesInPrecondition = actionInstancesNegativePredicatesInPrecondition;
			this.actionInstancesPositivePredicatesInEffects = actionInstancesPositivePredicatesInEffects;
			this.actionInstancesNegativePredicatesInEffects = actionInstancesNegativePredicatesInEffects;
			this.currentActiveActionInstancesBitSet = currentActiveActionInstancesBitSet;
			this.actionInstancesActions = actionInstancesActions;
			this.instanciatedParametersForActionInstances = instanciatedParametersForActionInstances;
			this.currentActivePositivePredicatesBitSet = currentActivePositivePredicatesBitSet;
			this.currentActiveUnknownPredicatesBitSet = currentActiveUnknownPredicatesBitSet;
			this.currentActiveNegativePredicatesBitSet = currentActiveNegativePredicatesBitSet;
			this.predicateIdentification = predicateIdentification;
			this.predicateIdentificationInverse = predicateIdentificationInverse;
			this.numberOfAllFluents = numberOfAllFluents;
		}
	}
}
