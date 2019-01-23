package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.lp.NSquareformulation;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.LBMinorLPDao;
import at.tuwien.ac.graph.ops.expriments.LBMinorLPExpriment;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 8/28/17.
 */
public class LBMinorLPExecutor extends SimpleExecutor<LBMinorLPExpriment> {
    Logger logger = LoggerFactory.getLogger(LBMinorLPExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    LBMinorLPDao dao = new LBMinorLPDao();
    @Override
    public LBMinorLPExpriment doSimpleExperiment(GraphNew graph) {

        return null;
    }


    public LBMinorLPExpriment doSimpleExperiment(Instance instance, int c, int vertexCount, int timeout) {
        logger.debug(instance.toString());
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();
        LBMinorLPExpriment result = calculateExperiment(graph, c, vertexCount, timeout);
        Long period = System.currentTimeMillis() - currentTime ;

        result.setExecutionTime(date);
        result.setInstance(instance);
        result.setOperationDuration(period);

        return result;
    }

    public LBMinorLPExpriment calculateExperiment(GraphNew graph, int c, int vertexCount, int timeout) {
        GraphNew  shuffle = new GraphNew(graph);
        while(shuffle.getSize()>vertexCount){
            Integer vertex = shuffle.getMinDegreeList().get(0);
            int max = -1 ;
            Integer maxNeighbor = null ;
            List<Integer> neighbors = shuffle.getNeighbors(vertex);
            for(Integer neighbor : neighbors){
                int degree = shuffle.getDegreeof(neighbor);
                if(degree >max){
                    max = degree;
                    maxNeighbor = neighbor ;
                }
            }
            shuffle.contractEdge(vertex, maxNeighbor);
        }

        shuffle = shuffle.shuffle();
        SimpleLPExpriment expriment = new NSquareformulation(shuffle, c).solveModel(timeout);

        LBMinorLPExpriment lpExpriment = new LBMinorLPExpriment();
        lpExpriment.setC(c);
        lpExpriment.setK(expriment.getLb());
        lpExpriment.setTimeout(timeout);
        lpExpriment.setVertexCount(vertexCount);
        if(expriment.getLb()==expriment.getK())
            lpExpriment.setExact(true);
        else
            lpExpriment.setExact(false);
        return lpExpriment;
    }

    public void executeAndStore(Instance instance, int c, int vertexCount, int timeout) {
        LBMinorLPExpriment lpExpriment = doSimpleExperiment(instance, c, vertexCount, timeout);
        dao.save(lpExpriment);
    }


}
