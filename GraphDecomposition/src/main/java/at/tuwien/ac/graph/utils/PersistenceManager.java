package at.tuwien.ac.graph.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by root on 8/1/17.
 */
public class PersistenceManager {
    private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("backdoor");
    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager() {
        //if(entityManager==null)
          //  entityManager = emFactory.createEntityManager();
        //return entityManager;
        return emFactory.createEntityManager();

    }

    public static void close() {

        emFactory.close();

    }
}
