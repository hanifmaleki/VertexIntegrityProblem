package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.lp.SimpleLPDecision;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.SimpleLPDecisionExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 8/26/17.
 */
public class SimpleLPDecisionExecutor extends SimpleExecutor<SimpleLPDecisionExpriment> {
    Logger logger = LoggerFactory.getLogger(SimpleLPDecisionExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    DecisionLPDao dao = new DecisionLPDao();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();
    @Override
    public SimpleLPDecisionExpriment doSimpleExperiment(GraphNew graph) {
        return null;
    }


    public SimpleLPDecisionExpriment doSimpleExperiment(GraphNew graph, int c, int k, int timeout) {
        SimpleLPDecision simpleLPDecision = new SimpleLPDecision(graph, c, k);
        SimpleLPDecisionExpriment expriment = simpleLPDecision.solveModel(timeout);
        return expriment;
    }

    public void executeAndStore(Instance instance, int c, int k, int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        SimpleLPDecisionExpriment experiment = doSimpleExperiment(graph, c, k, timeout);

        Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        experiment.setOperationDuration(period);
        SimpleLPDecisionExpriment ex = dao.getBestExprience(instance, c);
        //It is assumed that new one is better than old one
        if(ex!=null)
            dao.replace(experiment, ex);
        else
            dao.save(experiment);
    }

    public void runOnSimpleInstances(int timeout){
        List<Instance> allAscending = instanceManager.getAllAscending();
        for(Instance instance : allAscending){
            executeAllLPForInstance(instance, timeout);

        }
    }

    public void executeAllLPForInstance(Instance instance, int timeout) {
        int ub = ubDao.getExprimentByInstance(instance).getSize();
        int lb1 = treeDao.getExprimentByInstance(instance).getLowerBound();
        int lb2 = minorDao.getExprimentByInstance(instance).getSize();
        int lb = lb1 ;
        if (lb2>lb)
            lb = lb2 ;
        logger.debug("For instance "+ instance);
        logger.debug("lowerbound is "+ lb + " and upperbound is "+ ub);
        for(int i = lb; i <= ub; i++) {
        //for(int i = ub; i >= lb; i--) {
            logger.debug("Trying for instance "+ instance);
            logger.debug("with c=k="+ i);
            SimpleLPDecisionExpriment ex = dao.getBestExprience(instance, i);
            if(ex!=null)
                if((ex.getSolutionSize()!=null)||(ex.getTimeout()>=timeout)){
                    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                    continue;
                }

            executeAndStore(instance, i, i, timeout);
        }
    }
}
