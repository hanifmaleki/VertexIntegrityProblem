package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by e1528895 on 8/29/17.
 */
public class SimpleSatDao extends BasicExprimentDao<SimpleSatExperiment> {

    public SimpleSatExperiment getTimeoutOrderedExprimentBy(Instance instance, int c, int k) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        String queryString = "from SimpleSatExperiment ex where ex.instance = :instance " +
                "and ex.c=:c and ex.k = :k order by ex.timeout";
        List<SimpleSatExperiment> resultList = entityManager.createQuery(queryString).setParameter("instance", instance).setParameter("c", c)
                .setParameter("k", k).getResultList();
        entityManager.close();
        if(resultList.size()==0)
            return null;
        return resultList.get(0);
    }

    public void replace(SimpleSatExperiment newOne, SimpleSatExperiment oldOne) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        SimpleSatExperiment experiment = entityManager.find(SimpleSatExperiment.class, oldOne.getId());
        Instance instance = experiment.getInstance();
        newOne.setInstance(instance);
        newOne.setId(oldOne.getId());
        entityManager.remove(experiment);
        entityManager.merge(newOne);
        /*
        oldOne.setSimilarVertexEntities(newOne.getSimilarVertexEntities());
        oldOne.setTimeout(newOne.getTimeout());
        oldOne.setFinished(newOne.getFinished());
        oldOne.setFvdSize(newOne.getFvdSize());
        oldOne.setExecutionTime(newOne.getExecutionTime());
        oldOne.setOperationDuration(newOne.getOperationDuration());
        entityManager.persist(oldOne);
        */
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
