package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.minimumCut.SeparationMinSepMinCom;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumSizeMinimumMaxExpriment;

import java.util.Comparator;
import java.util.List;

/**
 * Created by e1528895 on 8/2/17.
 */
public class Separation2Executor extends SimpleExecutor<SeparationMinimumSizeMinimumMaxExpriment> {
    @Override
    public SeparationMinimumSizeMinimumMaxExpriment doSimpleExperiment(GraphNew graph) {
        //TODO !!!!!
        List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);
        if(connectedComponents.size()>1){
            connectedComponents.sort(new Comparator<GraphNew>() {
                @Override
                public int compare(GraphNew o1, GraphNew o2) {
                    return Integer.compare(o2.getSize(), o1.getSize());
                }
            });
            graph = connectedComponents.get(0);
        }

        return new SeparationMinSepMinCom().getProperSeparation(graph);
    }
}
