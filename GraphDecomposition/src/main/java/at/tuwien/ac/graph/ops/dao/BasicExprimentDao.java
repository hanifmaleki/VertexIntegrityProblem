package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by e1528895 on 8/3/17.
 */
public abstract class BasicExprimentDao<T extends SimpleExpriment>  extends BasicDao<T>{
    protected Class<T> entityClass;

    private Logger logger= LoggerFactory.getLogger(BasicDao.class);
    ParameterizedType genericSuperclass = null ;

    //BasicDao<T> basicDao = new BasicDao<T>();

    public BasicExprimentDao(){
        genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        //logger.debug(genericSuperclass.toString()+ "\tget Type Name "+ genericSuperclass.getTypeName());
        entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    public T getExprimentByInstance(Instance instance){
        List<T> resultList = getAllExprimentsByInstance(instance);
        if(resultList.isEmpty())
            return null;
        return resultList.get(0) ;
    }

    public static <T extends SimpleExpriment> Object getDao(Class<T> type){
        T t = null;
        try {
            t = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(t.getClass()==SeparationMinimumMaxComponentExpriment.class)
            return new SeparationMinMaxCompDao();
        if(t.getClass()==SeparationMinimumSizeMinimumMaxExpriment.class)
            return new SeparationMMDao();
        if(t.getClass()==SeparationSecondExpriment.class)
            return new SeparationSecondDao();
        if(t.getClass()==SeparationThresholdExperiment.class)
            return new SeparationThresholdDao();
        if(t.getClass()==SeparationMaximumDegreeExperiment.class)
            return new SeparationMaxDegreeDao();

        if(t.getClass() == OneMaxDegreeUB.class)
            return new OneMaxDegDao();
        if(t.getClass() == LBMinorWidthExpriment.class)
            return new LBMinorWidthDao();
        if(t.getClass() == MDSTLBExpriment.class)
            return new MDSTLBDao();
        if(t.getClass() == SimilarVerticesExperiment.class)
            return new SimilarVerticesDao();
        if(t.getClass() == FVDSimilarExperiment.class)
            return new FVDSimilarDao();
        if(t.getClass() == FVDSymetryExperiment.class)
            return new FVDSymetryDao();
        if(t.getClass() == HeuristicPartialSolutionExperiment.class)
            return new HeuristicPartialDao();
        if(t.getClass() == FinalHeuristicSolutionExperiment.class)
            return new FinalHeuristicDao();
        return null;
    }

    public List<T> getAllExprimentsByInstance(Instance instance) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        String name = entityClass.getName();
        List<T> resultList = entityManager.createQuery(" from " + name+ " e where e.instance = :instance")
                .setParameter("instance", instance).getResultList();
        entityManager.close();
        return resultList ;
    }

    public T getExprimentBy(Instance instance, int c, Integer k) {
        EntityManager entityManager = PersistenceManager.getEntityManager();

        String kString = "and ex.c=:c and ex.k = :k";
        String queryString = "from "+ entityClass.getSimpleName() + " ex where ex.instance = :instance " ;
        if(k!=null)
                queryString+= kString;
        Query query = entityManager.createQuery(queryString).setParameter("instance", instance).setParameter("c", c);
        if(k!=null)
            query.setParameter("k", k);
        List<T> resultList = query.getResultList();
        entityManager.close();
        if(resultList.size()==0)
            return null;
        return resultList.get(0);
    }

    /*
    public void replace(T newOne, T oldOne){
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        Long id = oldOne.getId();
        Class<T> aClass = (Class<T>) genericSuperclass.getClass();
        SimpleExpriment simpleExpriment = entityManager.find(oldOne.getClass(), id);
        newOne.setId(id);
        List<VertexEntity> selectedVertices = expriment.getSimilarVertexEntities();
        for(VertexEntity entity: selectedVertices)
            entityManager.remove(entity);
        entityManager.update(newOne);
        entityManager.getTransaction().commit();
        entityManager.close();
    }*/

    @Override
    public void removeAll() {
        List<T> all = getAll();
        for(T experiment: all)
            remove(experiment);
    }

    public void remove(T experiment){
        EntityManager em =PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        SimpleExpriment simpleExpriment = em.find(experiment.getClass(), experiment.getId());
        em.createQuery("DELETE FROM VertexEntity ve where ve.expriment = :expriment").setParameter("expriment", simpleExpriment)
                .executeUpdate();
        em.remove(simpleExpriment);
        em.getTransaction().commit();
        em.close();
    }

}
