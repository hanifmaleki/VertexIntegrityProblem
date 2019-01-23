package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.FVDSimilarExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;

/**
 * Created by e1528895 on 9/1/17.
 */
public class FVDSimilarDao extends BasicExprimentDao<FVDSimilarExperiment> {

    public void replace(FVDSimilarExperiment newOne, FVDSimilarExperiment oldOne) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        FVDSimilarExperiment experiment = entityManager.find(FVDSimilarExperiment.class, oldOne.getId());
        Instance instance = experiment.getInstance();
        newOne.setInstance(instance);
        newOne.setId(oldOne.getId());
        entityManager.remove(experiment);
        entityManager.merge(newOne);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
