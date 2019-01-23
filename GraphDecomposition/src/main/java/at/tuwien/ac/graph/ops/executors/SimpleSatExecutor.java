package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.sat.NewSatProducer;
import at.tuwien.ac.graph.sat.SatAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 8/29/17.
 */
public class SimpleSatExecutor extends SimpleExecutor<SimpleSatExperiment>{

    Logger logger = LoggerFactory.getLogger(SimpleSatExecutor.class);
    InstanceManager instanceManager = new InstanceManager();
    SimpleSatDao dao = new SimpleSatDao();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();

    @Override
    public SimpleSatExperiment doSimpleExperiment(GraphNew graph) {
        return null;
    }

    public SimpleSatExperiment doSimpleExperiment(GraphNew graph, int c, int k, int timeout) {

        SatAnswer fvdBySAT = NewSatProducer.getFVDBySAT(graph, c, k, timeout);

        SimpleSatExperiment experiment = new SimpleSatExperiment();
        experiment.setC(c);
        experiment.setK(k);
        experiment.setTimeout(timeout);
        experiment.setFinished(fvdBySAT.getFinished());
        experiment.setSatDuration(fvdBySAT.getCpuTime());
        if(fvdBySAT.getFvd()!=null) {
            experiment.setFvdSize(fvdBySAT.getFvd().size());
            List<VertexEntity> entities = new ArrayList<>();
            for (Integer vertex : fvdBySAT.getFvd()) {
                VertexEntity entity = new VertexEntity();
                entity.setExpriment(experiment);
                entity.setVertex(vertex);
                entities.add(entity);
            }
            experiment.setSelectedVertices(entities);
        }

        return experiment;
    }


    public void executeAndStore(Instance instance, int c, int k,int timeout) {
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();

        SimpleSatExperiment experiment = doSimpleExperiment(graph, c, k, timeout);

        logger.debug(experiment.toString());

        Long period = System.currentTimeMillis() - currentTime ;

        experiment.setExecutionTime(date);
        experiment.setInstance(instance);
        experiment.setOperationDuration(period);
        SimpleSatExperiment ex = dao.getTimeoutOrderedExprimentBy(instance, c, k);
        //It is assumed that new one is better than old one
        if(ex!=null)
            dao.replace(experiment, ex);
        else
            dao.save(experiment);
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
        //for(int i = lb; i <= ub; i++) {
        for(int i = ub; i >= lb; i--) {
            logger.debug("Trying for instance "+ instance);
            logger.debug("with c=k="+ i);
            SimpleSatExperiment ex = dao.getTimeoutOrderedExprimentBy(instance, i, i);
            if(ex!=null)
                if((ex.getFinished()==true)||(ex.getTimeout()>=timeout)){
                    logger.debug("there is done expriment for this instance with timeout "+ ex.getTimeout());
                    continue;
                }

            executeAndStore(instance, i, i, timeout);
        }
    }

/*    class TimeoutSAT extends Thread{
        GraphNew graph ;
        int c ;
        int k ;
        SatAnswer fvdBySAT = null ;
        boolean finished = false;

        public TimeoutSAT(GraphNew graph, int c, int k){
            this.graph = graph ;
            this.c = c ;
            this.k = k;
        }

        @Override
        public void run() {
            fvdBySAT = NewSatProducer.getFVDBySAT(graph, c, k);
            finished = true;
        }
    }

    public SimpleSatExperiment doSimpleExperiment(GraphNew graph, int c, int k, int timeout) {

        TimeoutSAT timeoutSAT = new TimeoutSAT(graph, c, k);
        long startTime = System.currentTimeMillis();
        boolean finished = true ;
        timeoutSAT.start();
        while(true){
            try {
                Thread.sleep(1000* 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(timeoutSAT.finished==true){
                break;
            }
            long currentTime = System.currentTimeMillis();
            long deltaTime = (currentTime - startTime)/1000;
            if(timeout<=deltaTime){
                finished = false ;
                timeoutSAT.interrupt();
                break;
            }
        }
        SimpleSatExperiment experiment = new SimpleSatExperiment();
        experiment.setC(c);
        experiment.setK(k);
        experiment.setTimeout(timeout);
        experiment.setFinished(finished);
        if(timeoutSAT.fvdBySAT!=null){
            experiment.setSatDuration(timeoutSAT.fvdBySAT.getCpuTime());
            if(timeoutSAT.fvdBySAT.getFvd()!=null) {
                experiment.setFvdSize(timeoutSAT.fvdBySAT.getFvd().size());
                List<VertexEntity> entities = new ArrayList<>();
                for (Integer vertex : timeoutSAT.fvdBySAT.getFvd()) {
                    VertexEntity entity = new VertexEntity();
                    entity.setExpriment(experiment);
                    entity.setVertex(vertex);
                    entities.add(entity);
                }
                experiment.setSimilarVertexEntities(entities);
            }
        }

        return experiment;
    }*/
}
