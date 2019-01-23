package at.tuwien.ac.graph.ops.dao;

import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by root on 8/1/17.
 */
public class BasicDao<T extends BasicEntity> implements BasicDaoInterface<T> {


    protected Class<T> entityClass;

    protected EntityManager entityManager;
    private Logger logger= LoggerFactory.getLogger(BasicDao.class);

    public BasicDao() {

            ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            //logger.debug(genericSuperclass.toString()+ "\tget Type Name "+ genericSuperclass.getTypeName());

            entityClass = (Class<T>) genericSuperclass
                    .getActualTypeArguments()[0];
    }


    @Override
    public List<T> getAll(){
        EntityManager entityManager = PersistenceManager.getEntityManager();
        String name = entityClass.getName();
        //logger.debug(name);
        List<T> resultList = entityManager.createQuery(" from " + name+ " e").getResultList();
        entityManager.close();
        return resultList ;
    }

    @Override
    public T getById(Long id) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        T t = entityManager.find(entityClass, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return t;
    }

    @Override
    public void removeAll(){
        EntityManager em =PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM "+entityClass.getName()).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(BasicEntity entity) {
        EntityManager em =PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        //em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
        em.close();
    }

    public void save(BasicEntity entity) {
        EntityManager em =PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public T getAttached(BasicEntity detached) {
        try {
            Method getId = detached.getClass().getDeclaredMethod("getId");
            Long id = (Long) getId.invoke(detached);
            entityManager = PersistenceManager.getEntityManager();
            T entity = entityManager.find((Class<T>) detached.getClass(), id);
            return entity;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        //detached.getId();
        return null;
    }

    /*public void update(BasicEntity entity) {
        EntityManager em =PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }*/
}
