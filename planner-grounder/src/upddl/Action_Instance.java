package upddl;

import java.util.Map;

public class Action_Instance {
	
	private static String getQbfName(final Action action, final int[] instanciatedParametersForAction, final Map<Integer, String> integerToobjectstring) {
		StringBuilder qbfNameWithoutTimeStamp = new StringBuilder();
		qbfNameWithoutTimeStamp.append(action.getName()).append("(");
		
		for (int i=0; i < instanciatedParametersForAction.length; i++) {
			int objCode = instanciatedParametersForAction[i];
			qbfNameWithoutTimeStamp.append(integerToobjectstring.get(objCode));
			
			if (i+1 < instanciatedParametersForAction.length) {
				qbfNameWithoutTimeStamp.append(",");
			}
		}
		
		qbfNameWithoutTimeStamp.append(")");
		
		return qbfNameWithoutTimeStamp.toString();
	}
	
	public static String getQbfNameForTime(final int time, final Action action, final int[] instanciatedParametersForAction, final Map<Integer, String> integerToobjectstring) {
		return Action_Instance.getQbfName(action, instanciatedParametersForAction, integerToobjectstring) + "_" + time;
	}
	
	public static String toString(final Action action, final int[] instanciatedParametersForAction, final Map<Integer, String> integerToobjectstring) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(action.getName());
		
		sb.append("\n\t(parameters: ");
		
		for (int i=0; i < instanciatedParametersForAction.length; i++) {
			sb.append(action.getParameters().get(i))
			.append("=")
			.append(integerToobjectstring.get(instanciatedParametersForAction[i]));
			
			if (i < instanciatedParametersForAction.length-1) 
				sb.append(", ");
		}
		
		sb.append(")");
		
		sb.append("\n\t(precondition: ");
		sb.append(action.getPrecondition().toString());
		sb.append(")");
		
		sb.append("\n\t(effects: ");
		sb.append(action.getEffect().toString());
		sb.append(")");
		
		return sb.toString();
	}
}
