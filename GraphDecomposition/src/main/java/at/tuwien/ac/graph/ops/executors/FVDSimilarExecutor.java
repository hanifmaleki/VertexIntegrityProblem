package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.NewFVDSolver;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 9/1/17.
 */
public class FVDSimilarExecutor extends SimpleExecutor<FVDSimilarExperiment> {
    Logger logger = LoggerFactory.getLogger(FVDSimilarExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    FVDSimilarDao dao = new FVDSimilarDao();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();
    @Override
    public FVDSimilarExperiment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    public FVDSimilarExperiment doSimpleExperiment(GraphNew graph, int c, int k, int timeout) {
        NewFVDSolver newFVDSolver = new NewFVDSolver();
        newFVDSolver.setCoverPrune(false);
        newFVDSolver.setSimilarPrune(true);
        SimpleFVDExperiment fvd = newFVDSolver.findFVD(graph, k, c, timeout);

        return convert(fvd);
    }


    public void executeAndStore(Instance instance, int c, int k, int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        //Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        FVDSimilarExperiment experiment = doSimpleExperiment(graph, c, k, timeout);

        //Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        //experiment.setOperationDuration(period);
        FVDSimilarExperiment ex = dao.getExprimentBy(instance, c, k);
        //It is assumed that new one is better than old one
        if(ex!=null)
            dao.replace(experiment, ex);
        else
            dao.save(experiment);
    }

    public void runOnSimpleInstances(int timeout){
        List<Instance> allAscending = instanceManager.getAllAscending();
        for(Instance instance : allAscending){
            executeAllForInstance(instance, timeout);

        }
    }

    public void executeAllForInstance(Instance instance, int timeout) {
        int ub = ubDao.getExprimentByInstance(instance).getSize();
        int lb1 = treeDao.getExprimentByInstance(instance).getLowerBound();
        int lb2 = minorDao.getExprimentByInstance(instance).getSize();
        int lb = lb1 ;
        if (lb2>lb)
            lb = lb2 ;
        logger.debug("For instance "+ instance);
        logger.debug("lowerbound is "+ lb + " and upperbound is "+ ub);
        for(int i = lb; i <= ub; i++) {
            logger.debug("Trying for instance "+ instance);
            logger.debug("with c=k="+ i);
            FVDSimilarExperiment ex = dao.getExprimentBy(instance, i, i);
            if(ex!=null)
                if((ex.getFinished()==true)||(ex.getTimeout()>=timeout)){
                    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                    continue;
                }

            executeAndStore(instance, i, i, timeout);
        }
    }


    FVDSimilarExperiment convert(SimpleFVDExperiment simpleExp){
        FVDSimilarExperiment experiment = new FVDSimilarExperiment();
        experiment.setC(simpleExp.getC());
        experiment.setFinished(simpleExp.getFinished());
        experiment.setFvdSize(simpleExp.getFvdSize());
        experiment.setLb(simpleExp.getLb());
        experiment.setK(simpleExp.getK());
        experiment.setSelectedVertices(simpleExp.getSelectedVertices());
        experiment.setTimeout(simpleExp.getTimeout());
        experiment.setUb(simpleExp.getUb());
        //experiment.setExecutionTime(simpleExp.getExecutionTime());
        experiment.setOperationDuration(simpleExp.getOperationDuration());

        return experiment;
    }
}
