package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimilarVertexEntity;
import at.tuwien.ac.graph.ops.expriments.SimilarVerticesExperiment;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 9/8/17.
 */
public class SimilarVerticesDao extends BasicExprimentDao<SimilarVerticesExperiment> {

    public List<Set<Integer>> getSimilarVertices(SimilarVerticesExperiment experiment){
        Integer bagCount = experiment.getBagCount();
        entityManager = PersistenceManager.getEntityManager();
        List<SimilarVertexEntity> similarVertexEntities = entityManager.createQuery("from SimilarVertexEntity entity where entity.expriment = :experiment")
                .setParameter("experiment", experiment).getResultList();
        entityManager.close();
        List<Set<Integer>> similarVertices = new ArrayList<>(bagCount);
        for(int i=0; i< bagCount; i++)
            similarVertices.add(new HashSet<>());

        for(SimilarVertexEntity entity: similarVertexEntities){
            Integer index = entity.getBagNumber();
            Set<Integer> set = similarVertices.get(index);
            set.add(entity.getVertex());
        }
        return similarVertices;
    }
}
