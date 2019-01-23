package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.heuristic.HeuristicBackdoorCalculator;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.FinalHeuristicSolutionExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;

/**
 * Created by e1528895 on 10/15/17.
 */
public class FinalHeuristicExecutor extends SimpleExecutor<FinalHeuristicSolutionExperiment> {
    @Override
    public FinalHeuristicSolutionExperiment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    @Override
    public FinalHeuristicSolutionExperiment doSimpleExperiment(Instance instance) {
        FinalHeuristicSolutionExperiment heuristicBackdoorOf = new HeuristicBackdoorCalculator().getHeuristicBackdoorOf(instance);
        return heuristicBackdoorOf;
    }
}
