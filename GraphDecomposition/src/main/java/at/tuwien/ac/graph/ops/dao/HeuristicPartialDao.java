package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.HeuristicPartialSolutionExperiment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by e1528895 on 10/15/17.
 */
public class HeuristicPartialDao extends BasicExprimentDao<HeuristicPartialSolutionExperiment> {
    public HeuristicPartialSolutionExperiment getExperiment(Instance instance, int c) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        List<HeuristicPartialSolutionExperiment> resultList = entityManager.createQuery("from HeuristicPartialSolutionExperiment experiment where experiment.instance" +
                "= :instance and experiment.c = :c").setParameter("instance", instance).setParameter("c", c)
                .getResultList();
        entityManager.close();
        if(resultList.isEmpty())
            return null;

        return resultList.get(0);
    }

    //public HeuristicPartialSolutionExperiment getExperiment(HeuristicPartialSolutionExperiment parent, Instance instance, String separationName, int c) {
    /*public HeuristicPartialSolutionExperiment getExperiment(Instance instance, String separationName, int c) {
        EntityManager entityManager = PersistenceManager.getEntityManager();

        String sepType = " ex.separationType= :separationName ";
        if(separationName==null)
            sepType = " ex.separationType is null ";

        String instanceStr = " and ex.instance = :instance ";
        if(instance==null)
            instanceStr = " and ex.instance is null ";

        String parentStr = "ex.parent= :parent";
        if(parent==null)
            parentStr = "ex.parent is null";


        String queryString = "from "+HeuristicPartialSolutionExperiment.class.getSimpleName()+" ex " +
                "where " + parentStr + " and ex.c= :c and" + sepType +
                instanceStr;
        Query query = entityManager.createQuery(queryString)
                .setParameter("c", c);

        if(separationName!=null)
            query.setParameter("separationName", separationName);

        if(instance!=null)
            query.setParameter("instance", instance);

        if(parent!=null)
            query.setParameter("parent", parent);

        List<HeuristicPartialSolutionExperiment> resultList = query.getResultList();

        entityManager.close();
        if(resultList.isEmpty())
            return null ;
        return resultList.get(0);

    }*/

    public void update(HeuristicPartialSolutionExperiment experiment) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(experiment);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /*@Override
    public void save(BasicEntity entity) {
        HeuristicPartialSolutionExperiment experiment = (HeuristicPartialSolutionExperiment) entity;
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        Long id = experiment.getInstance().getId();
        Instance instance = entityManager.find(Instance.class, id);
        experiment.setInstance(instance);
        entityManager.persist(experiment);
        entityManager.getTransaction().commit();
        entityManager.close();
    }*/

    public static void main(String[] args) {
        InstanceManager instanceManager = new InstanceManager();
        Instance instance = instanceManager.getInstanceByName("Grid-15");
        HeuristicPartialSolutionExperiment experiment = new HeuristicPartialSolutionExperiment();
        experiment.setInstance(instance);
        experiment.setOperationDuration(189L*60L*1000L);
        experiment.setC(36);
        experiment.setLb(22);
        experiment.setExecutionTime(new Date());
        experiment.setUb(33);
        int[] vertices = {36, 106, 78, 50, 22, 64, 6, 120, 92, 54, 38, 118, 102, 86, 134, 70, 108, 116, 124, 130, 140, 141, 143, 144, 157, 172, 186, 188, 200, 204, 214, 220};
        List<VertexEntity> entities = new ArrayList<>();
        for(int i= 0; i < vertices.length; i++){
            VertexEntity entity = new VertexEntity();
            entity.setVertex(vertices[i]);
            entity.setExpriment(experiment);
            entities.add(entity);
        }
        experiment.setSelectedVertices(entities);
        new HeuristicPartialDao().save(experiment);
        PersistenceManager.close();

    }
}
