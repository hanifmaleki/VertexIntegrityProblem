package at.tuwien.ac.graph.minimumCut;

import at.tuwien.ac.graph.ops.executors.SimpleExecutor;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumMaxComponentExpriment;

/**
 * Created by e1528895 on 8/2/17.
 */
public class SeparationExecution extends SimpleExecutor<SeparationMinimumMaxComponentExpriment> {
    @Override
    public SeparationMinimumMaxComponentExpriment doSimpleExperiment(GraphNew graph) {
        return new SeparationAlgorithm().getProperSeparation(graph);
    }
}
