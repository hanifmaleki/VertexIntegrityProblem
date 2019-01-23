package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.lp.NSquareformulation;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 8/26/17.
 */
public class SimpleLPExecutor extends SimpleExecutor<SimpleLPExpriment> {
    Logger logger = LoggerFactory.getLogger(SimpleLPExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    SimpleLPDao dao = new SimpleLPDao();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();
    @Override
    public SimpleLPExpriment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    /*
    public SimpleLPExpriment doSimpleExprimentAndStore(Instance instance, int c, int timeout){
        GraphNew graph = instanceManager.getGraph(instance);
        SimpleLPExpriment expriment = doSimpleExperiment(graph, c, timeout);
        expriment.setInstance(instance);
        dao.save(expriment);
        return expriment;
    }
    */

    public SimpleLPExpriment doSimpleExperiment(GraphNew graph, int c, int timeout) {
        SimpleLPExpriment expriment = new NSquareformulation(graph, c).solveModel(timeout);
        return expriment;
    }

    public SimpleLPExpriment executeAndStore(Instance instance, int c, int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        SimpleLPExpriment experiment = doSimpleExperiment(graph, c, timeout);

        Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        experiment.setOperationDuration(period);
        SimpleLPExpriment ex = dao.getBestExprience(instance, c);
        //It is assumed that new one is better than old one
        if(ex!=null)
            dao.replace(experiment, ex);
        else
            dao.save(experiment);

        return experiment;
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
        //for(int i = lb; i <= ub; i++) {
        for(int i = ub; i >= lb; i--) {
            logger.debug("Trying for instance "+ instance);
            logger.debug("with c=k="+ i);
            SimpleLPExpriment ex = dao.getBestExprience(instance, i);
            if(ex!=null)
                if((ex.getLb()==ex.getK())||(ex.getTimeout()>=timeout)){
                    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                    continue;
                }

            executeAndStore(instance, i, timeout);
        }
    }
}
