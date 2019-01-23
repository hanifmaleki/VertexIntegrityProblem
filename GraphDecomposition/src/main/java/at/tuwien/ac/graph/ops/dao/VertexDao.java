package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 8/3/17.
 */
public class VertexDao extends BasicDao<VertexEntity> {

    public List<VertexEntity> getVerticesOf(SimpleExpriment expriment){
        EntityManager em = PersistenceManager.getEntityManager();
        List<VertexEntity> exprimentList = em.createQuery("from VertexEntity v where v.expriment = :expriment")
                .setParameter("expriment", expriment).getResultList();
        em.close();
        return exprimentList;

    }

    public static List<VertexEntity> toVertexEntity(List<Integer> vertices, SimpleExpriment expriment) {
        List<VertexEntity> entities = new ArrayList<>();
        for(Integer vertex : vertices){
            VertexEntity entity = new VertexEntity();
            entity.setVertex(vertex);
            entity.setExpriment(expriment);
            entities.add(entity);
        }
        return entities;
    }


}
