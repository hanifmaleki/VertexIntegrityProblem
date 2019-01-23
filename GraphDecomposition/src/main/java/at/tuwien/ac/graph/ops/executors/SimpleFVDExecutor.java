package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.NewFVDSolver;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 9/1/17.
 */
public class SimpleFVDExecutor extends SimpleExecutor<SimpleFVDExperiment> {
    Logger logger = LoggerFactory.getLogger(SimpleFVDExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    SimpleFVDDao dao = new SimpleFVDDao();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();
    @Override
    public SimpleFVDExperiment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    public SimpleFVDExperiment doSimpleExperiment(GraphNew graph, int c, int k, int timeout) {
        NewFVDSolver newFVDSolver = new NewFVDSolver();
        newFVDSolver.setSimilarPrune(false);
        newFVDSolver.setCoverPrune(false);
        return newFVDSolver.findFVD(graph, k, c,timeout);
    }


    public void executeAndStore(Instance instance, int c, int k, int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        //Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        SimpleFVDExperiment experiment = doSimpleExperiment(graph, c, k, timeout);

        //Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        //experiment.setOperationDuration(period);
        SimpleFVDExperiment ex = dao.getExprimentBy(instance, c, k);
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
            SimpleFVDExperiment ex = dao.getExprimentBy(instance, i, i);
            if(ex!=null)
                if((ex.getFinished()==true)||(ex.getTimeout()>=timeout)){
                    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                    continue;
                }

            executeAndStore(instance, i, i, timeout);
        }
    }

    /*class TimeoutFVD extends Thread{
        GraphNew graph ;
        int c ;
        int k ;
        List<Integer> fvd = null ;
        Long finishedTime = null ;

        public TimeoutFVD(GraphNew graph, int c, int k){
            this.graph = graph ;
            this.c = c ;
            this.k = k;
        }

        @Override
        public void run() {
            //fvd = NewFVDSolver.getFVD(graph, new HashSet<>(graph.getVertices()), k, c, new HashSet<>(), graph.getSize());
            SimpleFVDExperiment fvd = new NewFVDSolver().findFVD(graph, k, c, 3000);
            finishedTime = System.currentTimeMillis();
        }
    }*/
}
