package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import depqbf4j.DepQBF4J;
//import encoding.IQBFEncoder;
//import encoding.IncDepQBF_Encoder;
//import encoding.QBFEncoder;
import grounder.Grounder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import upddl.Problem;
import upddl.parser.ASTParser;

import common.CsvLine;
//import common.Pair;
//import common.Result;
import common.Stopwatch;
//import common.Worker;
//import common.SolverStreamGobbler;


public class Planner {
	private static Logger logger = Logger.getLogger(Planner.class);
	
	public static final boolean USE_SPLITTING_EFFECTS_IF_BETTER = false; //TODO: need to refactor it for the new grounder before one can use it...
	public static final boolean USE_CONSISTENT_ACTION_INSTANCES_ONLY = true;
	public static final boolean USE_PHASE_GROUNDING_FOR_ACTION_INSTANCES = true;
	public static boolean USE_SIMPLIFY_CLAUSES_IF_POSSIBLE = false;
	public static final boolean CHECK_IF_VARIABLES_ARE_CORRECTLY_QUANTIFIED = false;
	public static final boolean CALCULATE_MIN_PLANLENGTH = false;
	public static final boolean CALCULATE_PLAN_FROM_WITNESS_IF_POSSIBLE = true;
	
//	public static final int FRAME_AXIOMS_ENCODING = QBFEncoder.CLASSICAL_FRAME_AXIOMS;
//	public static final int FRAME_AXIOMS_ENCODING = QBFEncoder.EXPLANATORY_FRAME_AXIOMS;
	
	private static String[] SUPPORTED_SAT_SOLVERS = { "minisat2_core", "minisat2_simp" };
	private static String[] SUPPORTED_QBF_SOLVERS = { "sKizzo", "AQME", "DepQBF", "QuBE", "RAReQS", "Nenofex" };
	private static String[] SUPPORTED_INCREMENTAL_SAT_SOLVERS = {};
	private static String[] SUPPORTED_INCREMENTAL_QBF_SOLVERS = { "incDepQBF" };
//	private static String[] SUPPORTED_SAT_PREPROCESSORS = {};
	private static String[] SUPPORTED_QBF_PREPROCESSORS = { "Bloqqer" };
	
	private static List<String> SUPPORTED_SAT_SOLVERS_LIST = Arrays.asList(Planner.SUPPORTED_SAT_SOLVERS);
	private static List<String> SUPPORTED_QBF_SOLVERS_LIST = Arrays.asList(Planner.SUPPORTED_QBF_SOLVERS);
	private static List<String> SUPPORTED_INCREMENTAL_SAT_SOLVERS_LIST = Arrays.asList(Planner.SUPPORTED_INCREMENTAL_SAT_SOLVERS);
	private static List<String> SUPPORTED_INCREMENTAL_QBF_SOLVERS_LIST = Arrays.asList(Planner.SUPPORTED_INCREMENTAL_QBF_SOLVERS);
//	private static List<String> SUPPORTED_SAT_PREPROCESSORS_LIST = Arrays.asList(Planner.SUPPORTED_SAT_PREPROCESSORS);
	private static List<String> SUPPORTED_QBF_PREPROCESSORS_LIST = Arrays.asList(Planner.SUPPORTED_QBF_PREPROCESSORS);
	
	private static List<String> SUPPORTED_SOLVERS_LIST;
	private static String SOLVER;
	private static final String SOLVER_DEFAULT = "incDepQBF";
	private static String SOLVER_ADDITIONAL_INFO = "";
	private static String QBF_PREPROCESSOR;
	
	private static boolean INC_DEBQBF_DUMP_STATS;
	private static final boolean INC_DEBQBF_DUMP_STATS_DEFAULT = false;
	private static boolean INC_DEBQBF_USE_NON_INCREMENTAL;
	private static final boolean INC_DEBQBF_USE_NON_INCREMENTAL_DEFAULT = false;
	private static final boolean DEBQBF_VERBOSE = false;
	private static String incDepQBFOptions;
	
//	private static final String BINARY_STRING_DEPQBF = "./solvers/qbf/DepQBF/lonsing-depqbf-26cc1a0/depqbf";
	private static final String BINARY_STRING_DEPQBF = "./jni/depqbf/depqbf";
	private static final String BINARY_STRING_AQME = "./solvers/qbf/AQME/AQME/runAqme.pl";
	private static final String BINARY_STRING_SKIZZO = "./solvers/qbf/sKizzo/sKizzo-v0.8.2-beta/sKizzo";
	private static final String BINARY_STRING_QUBE = "./solvers/qbf/QuBE/QuBE7.2";
	private static final String BINARY_STRING_OZZIKS = "./solvers/qbf/sKizzo/ozziKs-v0.3-beta/ozziKs";
	private static final String BINARY_STRING_RAREQS = "./solvers/qbf/rareqs/rareqs-1.1/rareqs";
	private static final String BINARY_STRING_NENOFEX = "./solvers/qbf/nenofex-picosat/nenofex-picosat/nenofex/nenofex";
	private static final String BINARY_STRING_BLOQQER = "./solvers/qbf-preprocessors/bloqqer/bloqqer-035-f899eab-141029/bloqqer";
	
	private static boolean DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE;
	private static final boolean DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE_DEFAULT = false;
	private static String OUTPUT_FOLDER_PREFIX;
	private static final String OUTPUT_FOLDER_PREFIX_DEFAULT = "testinstances-output/";
	private static final String FINISHED_FILE_SUFFIX = ".finished";
	private static final boolean RECALCULATE_IF_FINISHED_FILE_EXISTS = true;
	
	private String errorMessage = null;
	public static final int NO_PLAN_FOUND = -1;
	
	public static final boolean WRITE_CLAUSES_TO_TEMPFILE_IN_INTERMEDIATE_STEPS = true;
	public static final int WRITE_CLAUES_TO_TEMP_FILE_THRESHOLD = 1 * (int) Math.pow(10, 6);
	
	private static final boolean RUN_GROUNDER = true;
	private static final boolean RUN_QBFENCODER = false;
	private static final boolean WRITE_CLAUSES_TO_FILE = true;
	private static int MAX_PLANLENGTH;
	private static final int MAX_PLANLENGTH_DEFAULT = 200;
	public static int MAX_CLAUSES_TO_START_SOLVER;
	private static final int MAX_CLAUSES_TO_START_SOLVER_DEFAULT = 30 * (int) Math.pow(10, 6); 
	
	private static boolean GENERATOR_MODE;
	private static final boolean GENERATOR_MODE_DEFAULT = false;
	
	private static int TIMELIMIT_IN_SECONDS;
	private static final int TIMELIMIT_IN_SECONDS_DEFAULT = 0; //45*60=2700
	private static final int NO_TIMELIMIT = 0;
	
	private static String PATH_TO_DOMAIN_FILE;
	private static String PATH_TO_PROBLEM_FILE;
	private static String PROBLEM_FILENAME;
	private static String PATH_TO_PROBLEM_FILE_FOR_OUTPUT;
	private static String FinishedFilename;
	public static BufferedWriter FINISHEDFILE_BUFFEREDWRITER;
	
	private static Set<Integer> truthValuesOfVariables_DepQBF_or_RAReQS = new HashSet<Integer>();
	
	public static CsvLine csvLine = new CsvLine();
	public static boolean csvWritten = false;
	
	//Reuseables
	private final StringBuilder reuseableStringBuilder = new StringBuilder();
	private final Stopwatch neededTimeForEncoding = new Stopwatch();
	private final Stopwatch neededTimeForSolver = new Stopwatch();
	
	public Planner() {
		Planner.SUPPORTED_SOLVERS_LIST = new LinkedList<String>();
		Planner.SUPPORTED_SOLVERS_LIST.addAll(Planner.SUPPORTED_SAT_SOLVERS_LIST);
		Planner.SUPPORTED_SOLVERS_LIST.addAll(Planner.SUPPORTED_QBF_SOLVERS_LIST);
		Planner.SUPPORTED_SOLVERS_LIST.addAll(Planner.SUPPORTED_INCREMENTAL_SAT_SOLVERS_LIST);
		Planner.SUPPORTED_SOLVERS_LIST.addAll(Planner.SUPPORTED_INCREMENTAL_QBF_SOLVERS_LIST);
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (!Planner.csvWritten) {
					String s = "csv-line written at last shot (shutdown-hook)";
					Planner.csvLine.comment.append(s).append(", ");
					if (Planner.FINISHEDFILE_BUFFEREDWRITER != null) {
						try {
							Planner.FINISHEDFILE_BUFFEREDWRITER.write(Planner.csvLine.toString());
							Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
							Planner.csvWritten = true;
						} catch (IOException e) {
							e.printStackTrace();
						}
						logger.info(s);
					}
				}
			}
		});
		
		DOMConfigurator.configureAndWatch("log4j.xml", 60*1000);
		
		Planner planner = new Planner();
		
		logger.info("QBFPlanner started at: " + (new Date()).toString());
		planner.run(args);
		logger.info("planner finished at: " + (new Date()).toString());
		
		try {
			Planner.FINISHEDFILE_BUFFEREDWRITER.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(String[] args) throws IOException {
		this.parseCommandLineOptions(this.generateCommandLineOptions(), args);
		
		if (this.finishedFileExists()) {
			logger.info("the finished-file '" + Planner.FinishedFilename + "' already exists --> skipped");
			Planner.exitWithStatus(0);
		}
		
		Planner.FINISHEDFILE_BUFFEREDWRITER = new BufferedWriter(new FileWriter(Planner.FinishedFilename, true));
		
		Stopwatch stopwatch1 = new Stopwatch();
		Stopwatch stopwatch2 = new Stopwatch();
		
		stopwatch1.start();
		
		logger.info("================================================================================");
		logger.info("solving instance: " + Planner.PATH_TO_PROBLEM_FILE);
		Planner.csvLine.filename = Planner.PATH_TO_PROBLEM_FILE;
		
		ASTParser astParser = this.callASTParser(stopwatch2);
		
		Problem problem = astParser.getProblem();
		
		if (!Planner.RUN_GROUNDER) {
			logger.info("Planner.RUN_GROUNDER=false --> stop here");
			Planner.exitWithStatus(0);
		}
		
		Grounder grounder = this.callGrounder(astParser, problem, stopwatch1, stopwatch2);
		
		if (!Planner.RUN_QBFENCODER) {
			logger.info("Planner.RUN_QBFENCODER=false --> stop here");
			Planner.exitWithStatus(0);
		}

	}
	
	private ASTParser callASTParser(Stopwatch stopwatch2) {
		ASTParser astParser = null;
		
		try {
			stopwatch2.start();
			astParser = new ASTParser(Planner.PATH_TO_DOMAIN_FILE, Planner.PATH_TO_PROBLEM_FILE);
			stopwatch2.stop();
			Planner.FINISHEDFILE_BUFFEREDWRITER.write("parsing: " + stopwatch2.elapsedTime() + "ms\n\n");
		} catch (Exception e) {
			this.reuseableStringBuilder.setLength(0);
			this.reuseableStringBuilder.append(Planner.csvLine.toString()).append("\n\n");
			this.reuseableStringBuilder.append("exception in parser: ");
			this.reuseableStringBuilder.append(e.getMessage());
			
			String msg = this.reuseableStringBuilder.toString();
			logger.error(msg);
			
			try {
				Planner.FINISHEDFILE_BUFFEREDWRITER.write(msg + "\n");
				Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
			} catch (Exception e1) {
				logger.fatal(e1.getMessage());
				Planner.exitWithStatus(1);
			}
			Planner.exitWithStatus(1);
		}
		
		return astParser;
	}
	
	private Grounder callGrounder(ASTParser astParser, Problem problem, Stopwatch stopwatch1, Stopwatch stopwatch2) throws IOException {
		stopwatch2.start();
		
		Grounder grounder = null;
		
		try {
			grounder = new Grounder(astParser, stopwatch1);
		} catch (OutOfMemoryError oome2) {
			astParser = null;
			
			this.reuseableStringBuilder.setLength(0);
			this.reuseableStringBuilder.append(Planner.csvLine.toString()).append("\n\n");
			this.reuseableStringBuilder.append("grounder: file '");
			this.reuseableStringBuilder.append(problem.fileName);
			this.reuseableStringBuilder.append("' caused OutOfMemoryError.");
			
			this.errorMessage = this.reuseableStringBuilder.toString();
			Planner.csvLine.comment.append(this.errorMessage).append(", ");
			logger.error(this.errorMessage);
			
			try {
				Planner.FINISHEDFILE_BUFFEREDWRITER.write(this.errorMessage + "\n");
				Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
				Planner.csvWritten = true;
			} catch (Exception e) {
				logger.fatal(e.getMessage());
				Planner.exitWithStatus(1);
			}
			Planner.exitWithStatus(1);
		} catch (Exception e) {
			this.reuseableStringBuilder.setLength(0);
			this.reuseableStringBuilder.append(Planner.csvLine.toString()).append("\n\n");
			this.reuseableStringBuilder.append("exception in grounder: ");
			this.reuseableStringBuilder.append(e.getMessage());
			
			String msg = this.reuseableStringBuilder.toString();
			
			logger.error(msg);
			
			try {
				Planner.FINISHEDFILE_BUFFEREDWRITER.write(msg + "\n");
				Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
				Planner.csvWritten = true;
			} catch (Exception e1) {
				logger.fatal(e1.getMessage());
				Planner.exitWithStatus(1);
			}
			Planner.exitWithStatus(1);
		}
		
		stopwatch2.stop();
		
		int groundedActions = grounder.getActionInstancesActions().getFirst().size();
		int numberOfInconsistentActions = grounder.getNumberOfInconsistentActionInstances();
		
		this.reuseableStringBuilder.setLength(0);
		this.reuseableStringBuilder.append(groundedActions);
		this.reuseableStringBuilder.append(" grounded actions (");
		this.reuseableStringBuilder.append(numberOfInconsistentActions);
		this.reuseableStringBuilder.append(" are inconsistent and therefore ignored) in '");
		this.reuseableStringBuilder.append(problem.name);
		this.reuseableStringBuilder.append("' (");
		this.reuseableStringBuilder.append(problem.fileName);
		this.reuseableStringBuilder.append("). calculated in ");
		this.reuseableStringBuilder.append(stopwatch2.toString());
		this.reuseableStringBuilder.append(".");
		
		logger.info(this.reuseableStringBuilder.toString());
		
		this.reuseableStringBuilder.setLength(0);
		this.reuseableStringBuilder.append("grounding: ");
		this.reuseableStringBuilder.append(stopwatch2.elapsedTime());
		this.reuseableStringBuilder.append("ms\n");
		Planner.FINISHEDFILE_BUFFEREDWRITER.write(this.reuseableStringBuilder.toString());
		
		this.reuseableStringBuilder.setLength(0);
		this.reuseableStringBuilder.append("grounding: ");
		this.reuseableStringBuilder.append(groundedActions);
		this.reuseableStringBuilder.append(" grounded actions\n");
		Planner.FINISHEDFILE_BUFFEREDWRITER.write(this.reuseableStringBuilder.toString());
		
		this.reuseableStringBuilder.setLength(0);
		this.reuseableStringBuilder.append("grounding: ");
		this.reuseableStringBuilder.append(numberOfInconsistentActions);
		this.reuseableStringBuilder.append(" inconsistent actions (therefore ignored)\n\n");
		Planner.FINISHEDFILE_BUFFEREDWRITER.write(this.reuseableStringBuilder.toString());
		
		Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
		
		return grounder;
	}
	
	/**
	 * 
	 * @return null if no timeout and some error message if ((stopwatch1.elapsedTime() / 1000) > Planner.TIMELIMIT_IN_SECONDS)
	 */
	public static String breakBecauseOfTimeout(Stopwatch stopwatch1) {
		if (Planner.TIMELIMIT_IN_SECONDS == Planner.NO_TIMELIMIT) {
			return null;
		}
		
		long secs = stopwatch1.elapsedTime() / 1000;
		
		if (secs > Planner.TIMELIMIT_IN_SECONDS) {
			StringBuilder sb = new StringBuilder();
			sb.append(secs);
			sb.append(" seconds elapsed since starting to solve the instance, which is more than Planner.TIMELIMIT_IN_SECONDS=");
			sb.append(Planner.TIMELIMIT_IN_SECONDS);
			sb.append(".");
			return sb.toString();
		}
		
		return null;
	}
	
	private Options generateCommandLineOptions() {
		Options options = new Options();
		Option option;
		
		options.addOption("h", "help", false, "this help message.");
		
		option = new Option("f", "outputfolder-prefix", true, "specifies the prefix of the output folder where the .finished and optionally also the .qdimacs instances are stored. (default: " + Planner.OUTPUT_FOLDER_PREFIX_DEFAULT + ")");
		option.setValueSeparator('=');
		options.addOption(option);
		
		option = new Option("l", "maxplanlength", true, "maximum plan length. (default: " + Planner.MAX_PLANLENGTH_DEFAULT + ")");
		option.setValueSeparator('=');
		options.addOption(option);
		
		option = new Option("cl", "maxclauses", true, "maximum number of clauses to even start the solver. (default: " + Planner.MAX_CLAUSES_TO_START_SOLVER_DEFAULT + ")");
		option.setValueSeparator('=');
		options.addOption(option);
		
		options.addOption("gm", "generator-mode", false, "specifies to only generate qdimacs without calling the solver up to 'maxplanlength' or 'maxclauses'. do not use this option with 'incDepQBF' without 'incDepQBF-useNoninc' (default: " + Planner.GENERATOR_MODE_DEFAULT + ")");
		
		option = new Option("t", "timelimit", true, "timelimit for instance in seconds where 0 means no timeout (default: " + Planner.TIMELIMIT_IN_SECONDS_DEFAULT + ")");
		option.setValueSeparator('=');
		options.addOption(option);
		
		option = new Option("r", "removeqdimacs", true, "delete QDIMACS file if it is not needed anymore i.e. the solvers where started with this instance already. (default: " + Planner.DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE_DEFAULT + ")");
		option.setValueSeparator('=');
		options.addOption(option);
		
		this.reuseableStringBuilder.setLength(0);
		this.reuseableStringBuilder.append("specifies the solver. (default: ");
		this.reuseableStringBuilder.append(Planner.SOLVER_DEFAULT);
		this.reuseableStringBuilder.append(")\npossible values are:\n\n");
		this.reuseableStringBuilder.append("SAT solvers: ");
		this.reuseableStringBuilder.append(Planner.SUPPORTED_SAT_SOLVERS_LIST);
		this.reuseableStringBuilder.append("\n\n");
		this.reuseableStringBuilder.append("Incremental SAT solvers: ");
		this.reuseableStringBuilder.append(Planner.SUPPORTED_INCREMENTAL_SAT_SOLVERS_LIST);
		this.reuseableStringBuilder.append("\n\n");
		this.reuseableStringBuilder.append("QBF solvers: ");
		this.reuseableStringBuilder.append(Planner.SUPPORTED_QBF_SOLVERS_LIST);
		this.reuseableStringBuilder.append("\n\n");
		this.reuseableStringBuilder.append("Incremental QBF solvers: ");
		this.reuseableStringBuilder.append(Planner.SUPPORTED_INCREMENTAL_QBF_SOLVERS_LIST);
		option = new Option("s", "solver", true, this.reuseableStringBuilder.toString());
		option.setValueSeparator('=');
		options.addOption(option);
		
		options.addOption("ni", "incDepQBF-useNoninc", false, "specifies to use incDepQBF in a non-incremental fashion. Use this option only in conjunction with the solver incDepQBF. (default: false)");
		
		options.addOption("ds", "incDepQBF-dumpStats", false, "specifies to dump stats of DeqQBF or incDepQBF. (default: false)");
		
		options.addOption("do", "depQBF-options", true, "options that are directly passed to DepQBF via DepQBF4J.configure(...). The option need to be seperated by a semicolon ';', e.g., --depQBF-options 'o1;option 2;o3'.");
		
		options.addOption("qbfpre", "QBF-preprocessor", true, "specifies which QBF preprocessor is used to simplify the QDIMACS formula before the QBF solver is called. If this option is not given, no preprocessor is used. Available QBF preprocessors are: " + Planner.SUPPORTED_QBF_PREPROCESSORS_LIST);
		
		option = new Option("d", "domain", true, "path to domain file description.");
		option.setRequired(true);
		option.setValueSeparator('=');
		options.addOption(option);
		
		option = new Option("p", "problem", true, "path to problem file description.");
		option.setRequired(true);
		option.setValueSeparator('=');
		options.addOption(option);
		
		return options;
	}
	
	private void parseCommandLineOptions(Options options, String[] args) throws IOException {
		CommandLineParser parser = new DefaultParser();
		CommandLine line = null;
		
		try {
			 line = parser.parse(options, args);
		} catch (ParseException e) {
			for (int i=0; i < args.length; i++) {
				if (args[i].equals("-h") || args[i].equals("--help")) {
					HelpFormatter formatter = new HelpFormatter();
					formatter.printHelp("QBFPlanner", options);
					Planner.exitWithStatus(0);
				}
			}
			
			logger.error(e.getMessage());
			logger.error("will now exit.");
			Planner.exitWithStatus(1);
		}
		
		if (line.hasOption("help")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("QBFPlanner", options);
			Planner.exitWithStatus(0);
		}
		
		if (line.hasOption("outputfolder-prefix")) {
			Planner.OUTPUT_FOLDER_PREFIX = line.getOptionValue("outputfolder-prefix");
			
			if (!Planner.OUTPUT_FOLDER_PREFIX.endsWith("/")) {
				Planner.OUTPUT_FOLDER_PREFIX += "/";
			}
		} else {
			Planner.OUTPUT_FOLDER_PREFIX = Planner.OUTPUT_FOLDER_PREFIX_DEFAULT;
		}
		logger.info("Planner.OUTPUT_FOLDER_PREFIX=" + Planner.OUTPUT_FOLDER_PREFIX);
		
		//maxplanlength
		if (line.hasOption("maxplanlength")) {
			String maxplanlenthAsString = line.getOptionValue("maxplanlength");
			Planner.MAX_PLANLENGTH = Integer.parseInt(maxplanlenthAsString);
		} else {
			Planner.MAX_PLANLENGTH = Planner.MAX_PLANLENGTH_DEFAULT;
		}
		logger.info("Planner.MAX_PLANLENGTH=" + Planner.MAX_PLANLENGTH);
		
		//max. number of clauses to start the solver
		if (line.hasOption("maxclauses")) {
			String maxclausesAsString = line.getOptionValue("maxclauses");
			Planner.MAX_CLAUSES_TO_START_SOLVER = Integer.parseInt(maxclausesAsString);
		} else {
			Planner.MAX_CLAUSES_TO_START_SOLVER = Planner.MAX_CLAUSES_TO_START_SOLVER_DEFAULT;
		}
		logger.info("Planner.MAX_CLAUSES_TO_START_SOLVER=" + Planner.MAX_CLAUSES_TO_START_SOLVER);
		
		// use solver as (q)dimacs generator without calling a solver
		if (line.hasOption("generator-mode")) {
			Planner.GENERATOR_MODE = true;
			logger.info("Planner.GENERATOR_MODE=" + Planner.GENERATOR_MODE);
		} else {
			Planner.GENERATOR_MODE = Planner.GENERATOR_MODE_DEFAULT;
		}
		
		//timelimit
		if (line.hasOption("timelimit")) {
			String timelimitAsString = line.getOptionValue("timelimit");
			Planner.TIMELIMIT_IN_SECONDS = Integer.parseInt(timelimitAsString);
		} else {
			Planner.TIMELIMIT_IN_SECONDS = Planner.TIMELIMIT_IN_SECONDS_DEFAULT;
		}
		logger.info("Planner.TIMELIMIT_PER_INSTANCE_IN_SECONDS=" + Planner.TIMELIMIT_IN_SECONDS);
		
		//removeqdimacs
		if (line.hasOption("removeqdimacs")) {
			String deleteqdimacsAsString = line.getOptionValue("removeqdimacs");
			Planner.DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE = Boolean.parseBoolean(deleteqdimacsAsString);
		} else {
			Planner.DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE = Planner.DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE_DEFAULT;
		}
		logger.info("Planner.DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE=" + Planner.DELETE_QDIMACS_FILE_IF_NOT_NEEDED_ANYMORE);
		
		//QBF preprocessor
		if (line.hasOption("QBF-preprocessor")) {
			String qbfpreprocessor = line.getOptionValue("QBF-preprocessor");
			
			if (Planner.SUPPORTED_QBF_PREPROCESSORS_LIST.contains(qbfpreprocessor)) {
				Planner.QBF_PREPROCESSOR = qbfpreprocessor;
			} else {
				logger.info("option qbfpre can only be " + Planner.SUPPORTED_QBF_PREPROCESSORS_LIST + ".");
				Planner.exitWithStatus(1);
			}
		}
		
		//solver 
		if (line.hasOption("solver")) {
			String solver = line.getOptionValue("solver");
			
			if (Planner.SUPPORTED_SOLVERS_LIST.contains(solver)) {
				Planner.SOLVER = solver;
			} else {
				logger.info("option s can only be " + Planner.SUPPORTED_SOLVERS_LIST + ".");
				Planner.exitWithStatus(1);
			}
		} else {
			Planner.SOLVER = Planner.SOLVER_DEFAULT;
		}
		logger.info("Planner.SOLVER=" + Planner.SOLVER);
		
		Planner.csvLine.solver = Planner.SOLVER;
		
		//use incDepQBF in non-incremental fashion?
		if ("incDepQBF".equals(Planner.SOLVER)) {
			Planner.INC_DEBQBF_USE_NON_INCREMENTAL = line.hasOption("incDepQBF-useNoninc");
			
			if (Planner.INC_DEBQBF_USE_NON_INCREMENTAL) {
				Planner.SOLVER_ADDITIONAL_INFO = "useNoninc";
			}
			
			logger.info("use incDepQBF in non-incremental fashion: " + Planner.INC_DEBQBF_USE_NON_INCREMENTAL);
		} else {
			Planner.INC_DEBQBF_USE_NON_INCREMENTAL = Planner.INC_DEBQBF_USE_NON_INCREMENTAL_DEFAULT;
		}
		
		if (line.hasOption("incDepQBF-dumpStats")) {
			Planner.INC_DEBQBF_DUMP_STATS = true;
			logger.info("dump stats of incDepQBF: " + Planner.INC_DEBQBF_DUMP_STATS);
		} else {
			Planner.INC_DEBQBF_DUMP_STATS = Planner.INC_DEBQBF_DUMP_STATS_DEFAULT;
		}
		
		String s = "";
		if (line.hasOption("depQBF-options")) {
			Planner.incDepQBFOptions = line.getOptionValue("depQBF-options");
			s = Planner.incDepQBFOptions;
		}
		logger.info("DepQBF-options: " + s);
		
		//option "domain" and "problem" is required
		
		Planner.PATH_TO_DOMAIN_FILE = line.getOptionValue("domain");
		logger.info("Planner.DOMAIN_FILE=" + Planner.PATH_TO_DOMAIN_FILE);
		
		Planner.PATH_TO_PROBLEM_FILE = line.getOptionValue("problem");
		logger.info("Planner.PROBLEM_FILE=" + Planner.PATH_TO_PROBLEM_FILE);
		
		Planner.PATH_TO_PROBLEM_FILE_FOR_OUTPUT = Planner.OUTPUT_FOLDER_PREFIX + Planner.PATH_TO_PROBLEM_FILE;
		
		String addionalSolverInfo = "";
		if (!Planner.SOLVER_ADDITIONAL_INFO.equals("")) {
			addionalSolverInfo = "-" + Planner.SOLVER_ADDITIONAL_INFO;
		}
		if (Planner.QBF_PREPROCESSOR != null) {
			Planner.FinishedFilename = Planner.PATH_TO_PROBLEM_FILE_FOR_OUTPUT + "_" + Planner.SOLVER + addionalSolverInfo + "_withPreprocessor_" + Planner.QBF_PREPROCESSOR + Planner.FINISHED_FILE_SUFFIX;
		} else {
			Planner.FinishedFilename = Planner.PATH_TO_PROBLEM_FILE_FOR_OUTPUT + "_" + Planner.SOLVER + addionalSolverInfo + Planner.FINISHED_FILE_SUFFIX;
		}
		
		// constraints
		if (Planner.GENERATOR_MODE && "incDepQBF".equals(Planner.SOLVER) && !Planner.INC_DEBQBF_USE_NON_INCREMENTAL) {
			logger.info("you cannot use incDepQBF without '--incDepQBF-useNoninc' in generator-mode.");
			Planner.exitWithStatus(1);
		}
	}
	
	public boolean finishedFileExists() {
		File f = new File(Planner.PATH_TO_PROBLEM_FILE);
		Planner.PROBLEM_FILENAME = f.getName();
		String problemFilepath;
		
		File parent = f.getParentFile();
		if (parent != null) {
			problemFilepath = parent.getPath();
		} else {
			problemFilepath = "";
		}
		
		(new File(Planner.OUTPUT_FOLDER_PREFIX + problemFilepath)).mkdirs();
		
		//check if problem already finished in a former computation
		File finishedFile = new File(Planner.FinishedFilename);
		
		if (Planner.RECALCULATE_IF_FINISHED_FILE_EXISTS) {
			return false;
		}
		
		return finishedFile.exists();
	}
	
	public static void exitWithStatus(int exitCode) {
		if (!Planner.csvWritten) {
			String s = "csv-line written at Planner.exitWithStatus(" + exitCode + ")";
			Planner.csvLine.comment.append(s).append(", ");
			if (Planner.FINISHEDFILE_BUFFEREDWRITER != null) {
				try {
					Planner.FINISHEDFILE_BUFFEREDWRITER.write(Planner.csvLine.toString());
					Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
					Planner.csvWritten = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
				logger.info(s);
			}
		}
		
		if (Planner.FINISHEDFILE_BUFFEREDWRITER != null) {
			try {
				Planner.FINISHEDFILE_BUFFEREDWRITER.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.flush();
		System.err.flush();
		
		logger.info("exit with status " + exitCode);
		System.exit(exitCode);
	}
}
