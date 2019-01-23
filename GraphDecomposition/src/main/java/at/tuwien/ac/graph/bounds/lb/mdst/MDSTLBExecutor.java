package at.tuwien.ac.graph.bounds.lb.mdst;

import at.tuwien.ac.graph.ops.executors.SimpleExecutor;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.expriments.MDSTLBExpriment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/28/17.
 */
public class MDSTLBExecutor extends SimpleExecutor<MDSTLBExpriment>{
    Logger logger = LoggerFactory.getLogger(MDSTLBExecutor.class);

    MinimumDegreeSpanningTreeLB calculator = new MinimumDegreeSpanningTreeLB();

    @Override
    public MDSTLBExpriment doSimpleExperiment(GraphNew graph) {
        List<GraphNew> spanningTrees = getSpanningTrees(graph);
        int max = 0 ;
        for(GraphNew tree: spanningTrees){
            int maxDegree = tree.getMaxDegree();
            if(maxDegree> max)
                max = maxDegree ;
        }
        int c = 1;
        while(true){
            int lowerbound = 0;
            for(GraphNew tree: spanningTrees){
                lowerbound+= calculator.getLBFromDegree(tree.getMaxDegree(), tree.getSize(), c);
            }
            logger.debug("The lowerbound for c="+c + " is "+lowerbound);
            if(lowerbound<=c){
                MDSTLBExpriment expriment = new MDSTLBExpriment();
                expriment.setLowerBound(lowerbound);
                expriment.setC(c);
                expriment.setMaximumDegree(max);
                return expriment;
            }
            c++ ;
        }
    }

    private List<GraphNew> getSpanningTrees(GraphNew graph){
        List<GraphNew> trees = new ArrayList<>();
        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);
        logger.debug("Component count is "+ connectedComponentsHS.size());

        for(GraphNew component : connectedComponentsHS){
            if(component.getSize()<2)
                continue;
            GraphNew minimumDegreeSpanningTreeEff = calculator.getMinimumDegreeSpanningTreeEff(component);
            trees.add(minimumDegreeSpanningTreeEff);
        }
        return trees ;
    }
}
