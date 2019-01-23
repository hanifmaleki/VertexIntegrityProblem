package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimpleLPDecisionExpriment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by e1528895 on 9/24/17.
 */
public class DecisionLPDao extends BasicExprimentDao<SimpleLPDecisionExpriment> {
    public SimpleLPDecisionExpriment getBestExprience(Instance instance, Integer c) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        List<SimpleLPDecisionExpriment> resultList = entityManager.createQuery("from SimpleLPDecisionExpriment s where " +
                "s.instance = :instance and s.c= :c order by k ")
                .setParameter("instance", instance).setParameter("c", c).getResultList();
        SimpleLPDecisionExpriment exprience = null ;
        List<VertexEntity> selectedVertices = null ;
        if(!resultList.isEmpty()) {
            exprience = resultList.get(0);
            selectedVertices = exprience.getSelectedVertices();
        }
        if(exprience!=null)
            exprience.setSelectedVertices(selectedVertices);
        entityManager.close();
        return exprience ;
    }

    public void replace(SimpleLPDecisionExpriment newOne, SimpleLPDecisionExpriment oldOne){
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        SimpleLPDecisionExpriment expriment = entityManager.find(SimpleLPDecisionExpriment.class, oldOne.getId());
        newOne.setId(oldOne.getId());
        List<VertexEntity> selectedVertices = expriment.getSelectedVertices();
        for(VertexEntity entity: selectedVertices)
            entityManager.remove(entity);
        entityManager.merge(newOne);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
