package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.lp.NSquareformulation;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 9/1/17.
 */
public abstract class ComplexExecutor<T extends SimpleExpriment>  {
    Logger logger = LoggerFactory.getLogger(ComplexExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    BasicExprimentDao<T> dao = null;
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();
    public abstract T doSimpleExperiment(GraphNew graph, int c, int k, int timeout) ;

    public ComplexExecutor(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        dao = (BasicExprimentDao<T>) BasicExprimentDao.getDao((Class<T>) genericSuperclass.getClass());
    }

    public void executeAndStore(Instance instance, int c, int k, int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        T experiment = doSimpleExperiment(graph, c, k, timeout);

        Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        experiment.setOperationDuration(period);
        T ex = dao.getExprimentBy(instance, c, k);
        //It is assumed that new one is better than old one
        //if(ex!=null)
        //    dao.replace(experiment, ex);
        //else
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
            logger.debug("Trying for instance "+ instance);
            logger.debug("with c=k="+ i);
            T ex = dao.getExprimentBy(instance, i, i);
            //if(ex!=null)
                //if((ex.getSolutionSize()==ex.getK())||(ex.getTimeout()>=timeout)){
                //    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                //    continue;
                //}

            //executeAndStore(instance, i, timeout);
        }
    }
}
