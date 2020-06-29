package daos;

import entities.Javitas;

import javax.persistence.EntityManager;

public class JavitasDao extends BasicDao<Javitas> {
    public JavitasDao(EntityManager em) {
        super(Javitas.class);
        this.em = em;
    }
}
