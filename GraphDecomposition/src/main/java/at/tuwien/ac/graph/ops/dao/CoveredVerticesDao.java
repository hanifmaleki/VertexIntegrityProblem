package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.CoveredVerticesExperiment;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;

/**
 * Created by e1528895 on 9/20/17.
 */
public class CoveredVerticesDao extends BasicExprimentDao<CoveredVerticesExperiment> {

    public void replace(CoveredVerticesExperiment newOne, CoveredVerticesExperiment oldOne) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        CoveredVerticesExperiment experiment = entityManager.find(CoveredVerticesExperiment.class, oldOne.getId());
        Instance instance = experiment.getInstance();
        newOne.setInstance(instance);
        newOne.setId(oldOne.getId());
        entityManager.remove(experiment);
        entityManager.merge(newOne);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
