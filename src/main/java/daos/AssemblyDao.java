package daos;

import entities.Assembly;

import javax.persistence.EntityManager;

public class AssemblyDao extends BasicDao<Assembly> {
    public AssemblyDao(EntityManager em) {
        super(Assembly.class);
        this.em = em;
    }
}
