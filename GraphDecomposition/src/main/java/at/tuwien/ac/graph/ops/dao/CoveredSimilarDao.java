package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.FVDSimilarCoveredExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;

/**
 * Created by e1528895 on 9/20/17.
 */
public class CoveredSimilarDao extends BasicExprimentDao<FVDSimilarCoveredExperiment> {

    public void replace(FVDSimilarCoveredExperiment newOne, FVDSimilarCoveredExperiment oldOne) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        FVDSimilarCoveredExperiment experiment = entityManager.find(FVDSimilarCoveredExperiment.class, oldOne.getId());
        Instance instance = experiment.getInstance();
        newOne.setInstance(instance);
        newOne.setId(oldOne.getId());
        entityManager.remove(experiment);
        entityManager.merge(newOne);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
