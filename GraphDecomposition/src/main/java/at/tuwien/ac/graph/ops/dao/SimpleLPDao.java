package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import com.sun.org.apache.bcel.internal.classfile.PMGClass;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by e1528895 on 8/26/17.
 */
public class SimpleLPDao extends BasicExprimentDao<SimpleLPExpriment> {

    public SimpleLPExpriment getBestExprience(Instance instance, Integer c) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        List<SimpleLPExpriment> resultList = entityManager.createQuery("from SimpleLPExpriment s where " +
                "s.instance = :instance and s.c= :c order by k ")
                .setParameter("instance", instance).setParameter("c", c).getResultList();
        SimpleLPExpriment exprience = null ;
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

    public void replace(SimpleLPExpriment newOne, SimpleLPExpriment oldOne){
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        SimpleLPExpriment expriment = entityManager.find(SimpleLPExpriment.class, oldOne.getId());
        newOne.setId(oldOne.getId());
        List<VertexEntity> selectedVertices = expriment.getSelectedVertices();
        for(VertexEntity entity: selectedVertices)
            entityManager.remove(entity);
        entityManager.merge(newOne);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
