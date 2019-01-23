package at.tuwien.ac.graph.ops.executors;

import at.tuwien.ac.graph.minimumCut.SeparationExecution;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;
import at.tuwien.ac.graph.ops.dao.BasicExprimentDao;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.utils.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 7/28/17.
 */
public abstract class SimpleExecutor<T extends SimpleExpriment> {

    Logger logger = LoggerFactory.getLogger(SimpleExecutor.class);

    InstanceManager instanceManager = new InstanceManager();

    public abstract T doSimpleExperiment(GraphNew graph);

    public T doSimpleExperiment(Instance instance) {
        logger.debug(instance.toString());
        GraphNew graph = instanceManager.getGraph(instance);

        Long currentTime = System.currentTimeMillis();
        Date date = new Date();
        T result = doSimpleExperiment(graph);
        Long period = System.currentTimeMillis() - currentTime ;

        result.setExecutionTime(date);
        result.setInstance(instance);
        result.setOperationDuration(period);
        logger.debug(result.toString());
        return result;
    }

    public void executeOnAllInstances(){

        List<Instance> resultList = instanceManager.getAllAscending();

        EntityManager em = PersistenceManager.getEntityManager();
        for(Instance instance : resultList) {
            executeAndStore(instance, false);
        }

        em.close();
    }

    //TODO  apply overwrite parameter
    private void executeAndStore(Instance instance, boolean overwrite) {
        T exprimentResult = doSimpleExperiment(instance);
        BasicExprimentDao<T> dao = getDao();
        dao.save(exprimentResult);
    }

    public void executeOnRemainingInstances(){
        List<Instance> instances = instanceManager.getAllAscending();
        BasicExprimentDao<T> dao = getDao();
        int counter=1;
        for(Instance instance: instances){
            T exprimentByInstance = dao.getExprimentByInstance(instance);
            if(exprimentByInstance==null){
                logger.debug(" Computing expriment for instance "+instance);
                executeAndStore(instance, false);

            }
            else{
                logger.debug(counter++ +"Computation has been already done for instance "+ instance);
            }

        }
    }

    private BasicExprimentDao<T> getDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> tClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

        return (BasicExprimentDao<T>) BasicExprimentDao.getDao(tClass);
    }

    //TODO  apply overwrite parameter
    public void executeOnSpecialInstances(String[] fileNames, boolean overwrite){
        for(String fileName:fileNames) {
            Instance instanceByFileName = instanceManager.getInstanceByName(fileName);
            executeAndStore(instanceByFileName, false);
        }
    }





    public static void main(String[] args) {
        //new LBMinorWExecutor().executeOnRemainingInstances();
        //new OneMaxDegreeCalculator().executeOnRemainingInstances();
        new SeparationExecution().executeOnRemainingInstances();
        PersistenceManager.close();

    }
}
