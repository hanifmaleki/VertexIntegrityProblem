package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.ModernFVDCaller;
import at.tuwien.ac.graph.newgraph.ModernFVDSolver;
import at.tuwien.ac.graph.newgraph.NewFVDSolver;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarCoveredExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 9/1/17.
 */
public class FVDBothExecutor extends SimpleExecutor<FVDSimilarCoveredExperiment> {
    Logger logger = LoggerFactory.getLogger(FVDBothExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    CoveredSimilarDao dao = new CoveredSimilarDao();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();
    @Override
    public FVDSimilarCoveredExperiment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    public FVDSimilarCoveredExperiment doSimpleExperiment(GraphNew graph, int c, int k, int timeout) {
        //NewFVDSolver newFVDSolver = new NewFVDSolver();
        //ModernFVDSolver newFVDSolver = new ModernFVDSolver();
        ModernFVDCaller newFVDSolver = new ModernFVDCaller();

        newFVDSolver.setCoverPrune(true);
        newFVDSolver.setSimilarPrune(true);
        FVDSimilarCoveredExperiment fvd = convert(newFVDSolver.findFVD(graph, k, c, timeout));


        return fvd;
    }


    public boolean executeAndStore(Instance instance, int c, int k, int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        //Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        FVDSimilarCoveredExperiment experiment = doSimpleExperiment(graph, c, k, timeout);

        //Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        //experiment.setOperationDuration(period);
        FVDSimilarCoveredExperiment ex = dao.getExprimentBy(instance, c, k);
        //It is assumed that new one is better than old one
        if(ex!=null)
            dao.replace(experiment, ex);
        else
            dao.save(experiment);

        boolean exitConditions = (experiment.getUb() <= experiment.getC())||((ex!=null)&&(ex.getUb()<=ex.getC()));
        if(exitConditions)
            return true;
        return false;
    }

    public void runOnSimpleInstances(int timeout){
        List<Instance> allAscending = instanceManager.getAllAscending();
        for(Instance instance : allAscending){
            executeAllSatForInstance(instance, timeout);

        }
    }

    public void executeAllSatForInstance(Instance instance, int timeout) {
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
            FVDSimilarCoveredExperiment ex = dao.getExprimentBy(instance, i, i);
            if(ex!=null)
                if((ex.getFinished()==true)||(ex.getTimeout()>=timeout)){
                    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                    if(ex.getUb()<=ex.getC())
                        break;
                    continue;
                }

            boolean exitCondition = executeAndStore(instance, i, i, timeout);

            if(exitCondition)
                break;

        }
    }


    FVDSimilarCoveredExperiment convert(SimpleFVDExperiment simpleExp){
        FVDSimilarCoveredExperiment experiment = new FVDSimilarCoveredExperiment();
        experiment.setC(simpleExp.getC());
        experiment.setFinished(simpleExp.getFinished());
        experiment.setFvdSize(simpleExp.getFvdSize());
        experiment.setLb(simpleExp.getLb());
        experiment.setK(simpleExp.getK());
        experiment.setSelectedVertices(simpleExp.getSelectedVertices());
        experiment.setTimeout(simpleExp.getTimeout());
        experiment.setUb(simpleExp.getUb());
        experiment.setOperationDuration(simpleExp.getOperationDuration());

        return experiment;
    }
}
