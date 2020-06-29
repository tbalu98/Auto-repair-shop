package daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerCreator {

    public static EntityManagerFactory emf;

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

}
