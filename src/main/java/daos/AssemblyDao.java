package daos;

import entities.Assembly;

import javax.persistence.EntityManager;

/**
 * Dao class for assemblies.
 *
 * */

public class AssemblyDao extends BasicDao<Assembly> {
    public AssemblyDao(EntityManager em) {
        super(Assembly.class);
        this.em = em;
    }
}
