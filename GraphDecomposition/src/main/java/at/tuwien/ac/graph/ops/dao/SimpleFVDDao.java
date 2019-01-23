package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;

import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;

/**
 * Created by e1528895 on 9/1/17.
 */
public class SimpleFVDDao extends BasicExprimentDao<SimpleFVDExperiment> {

    public void replace(SimpleFVDExperiment newOne, SimpleFVDExperiment oldOne) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        SimpleFVDExperiment experiment = entityManager.find(SimpleFVDExperiment.class, oldOne.getId());
        Instance instance = experiment.getInstance();
        newOne.setInstance(instance);
        newOne.setId(oldOne.getId());
        entityManager.remove(experiment);
        entityManager.merge(newOne);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
