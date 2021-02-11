package daos;

import entities.UsedPart;

import javax.persistence.EntityManager;


public class EladottAlkatreszDao extends BasicDao<UsedPart> {
    public EladottAlkatreszDao(EntityManager em) {
        super(UsedPart.class);
        this.em = em;
    }
}
