package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.minimumCut.SeparationSecondBiggestComp;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.SeparationSecondExpriment;

/**
 * Created by e1528895 on 9/25/17.
 */
public class SeparationSecondBiggestExecutor extends SimpleExecutor<SeparationSecondExpriment> {
    @Override
    public SeparationSecondExpriment doSimpleExperiment(GraphNew graph) {
        return new SeparationSecondBiggestComp().getProperSeparation(graph);
    }
}
