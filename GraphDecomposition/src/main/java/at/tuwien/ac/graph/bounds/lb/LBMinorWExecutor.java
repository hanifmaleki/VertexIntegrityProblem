package at.tuwien.ac.graph.bounds.lb;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.executors.SimpleExecutor;
import at.tuwien.ac.graph.ops.expriments.LBMinorWidthExpriment;

/**
 * Created by root on 7/28/17.
 */
public class LBMinorWExecutor extends SimpleExecutor<LBMinorWidthExpriment> {
    @Override
    public LBMinorWidthExpriment doSimpleExperiment(GraphNew graph) {
        int lowerbound = getLowerbound(graph);

        LBMinorWidthExpriment expriment = new LBMinorWidthExpriment();
        expriment.setSize(lowerbound);
        return expriment;

    }

    //TODO move to another class
    //TODO Note: This method may exist in other classes ....
    public int getLowerbound(GraphNew graph) {
        int lowerbound = Integer.MIN_VALUE;

        while (graph.getSize() > 0) {

            //Find the vertex with lowest degree
            int min = Integer.MAX_VALUE;
            Integer z = null;
            for (Integer v : graph.getVertices()) {
                int degree = graph.getDegreeof(v);
                if (degree < min && graph.getNeighbors(v).size() > 0) {
                    z = v;
                    min = degree;
                }
            }

            //If there are no edges left in the Graph: we are done
            //TODO
            if (z == null)
                break;


            if (min > lowerbound)
                lowerbound = min;

            //Find the neighbour with lowest degree
            min = Integer.MAX_VALUE;
            Integer contractVertex = null;
            for (Integer other : graph.getNeighbors(z)) {
                int size = graph.getNeighbors(other).size();
                if (size < min) {
                    min = size;
                    contractVertex = other;
                }
            }

            //Contract the Edge from the graph

            graph.contractEdge(z, contractVertex);
        }
        return lowerbound/2;
    }
}
