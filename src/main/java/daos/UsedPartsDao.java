package daos;

import entities.UsedPart;

import javax.persistence.EntityManager;

public class UsedPartsDao extends BasicDao<UsedPart> {
    public UsedPartsDao(EntityManager em) {
        super(UsedPart.class);
        this.em = em;
    }
}
