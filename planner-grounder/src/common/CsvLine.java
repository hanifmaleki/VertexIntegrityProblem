package common;

public class CsvLine {
	private static final String SEPERATOR = ";";
	
	public String filename = "no file specified";
	public String solver = "no solver specified";
	public String qbfpreprocessor = this.qbfpreprocessorDefault;
	public int numUnknowns = -1;
	public int currentPlanLength = -1;
	public int numClauses = -1;
	public int numVariables = -1;
	public int minPlanLength = -1;
	public double timeEncodingInSec = -1;
	public double timeSolverInSec = -1;
	public double timeTotalInSec = -1;
	public int optimalPlanLength = -1;
	public StringBuilder comment = new StringBuilder();
	
	private String qbfpreprocessorDefault = "no QBF preprocessor speciefied";
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("csv-line: ");
		sb.append(this.filename);
		sb.append(SEPERATOR);
		
		sb.append(this.solver);
		if (!qbfpreprocessorDefault.equals(this.qbfpreprocessor)) {
			sb.append(" (QBF preprocessor: ");
			sb.append(qbfpreprocessor);
			sb.append(")");
		}
		sb.append(SEPERATOR);
		
		if (this.numUnknowns != -1)
			sb.append(this.numUnknowns);
		
		sb.append(SEPERATOR);
		
		if (this.currentPlanLength != -1)
			sb.append(this.currentPlanLength);
		
		sb.append(SEPERATOR);
		
		if (this.numClauses != -1)
			sb.append(this.numClauses);
		
		sb.append(SEPERATOR);
		
		if (this.numVariables != -1)
			sb.append(this.numVariables);
		
		sb.append(SEPERATOR);
		
		
		if (this.minPlanLength != -1)
			sb.append(this.minPlanLength);
		
		sb.append(SEPERATOR);
		
		if (this.timeEncodingInSec != -1)
			sb.append(this.timeEncodingInSec);
		
		sb.append(SEPERATOR);
		
		if (this.timeSolverInSec != -1)
			sb.append(this.timeSolverInSec);
		
		sb.append(SEPERATOR);
		
		if (this.timeTotalInSec != -1)
			sb.append(this.timeTotalInSec);
		
		sb.append(SEPERATOR);
		
		if (this.optimalPlanLength != -1)
			sb.append(this.optimalPlanLength);
		
		sb.append(SEPERATOR);
		
		String replaceBy = ";";
		if (SEPERATOR.equals(";"))
			replaceBy = ",";
		
		String temp = this.comment.toString().replace(SEPERATOR, replaceBy);
		sb.append(temp);
		
		return sb.toString();
	}
}
