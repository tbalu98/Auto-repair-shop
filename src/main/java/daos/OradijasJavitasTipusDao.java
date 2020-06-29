package daos;

import entities.OradijasJavitasTipus;

import javax.persistence.EntityManager;

public class OradijasJavitasTipusDao extends BasicDao<OradijasJavitasTipus> {
    public OradijasJavitasTipusDao(EntityManager em) {
        super(OradijasJavitasTipus.class);
        this.em = em;
    }
}
