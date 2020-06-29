package daos;

import entities.FelhasznaltAlkatresz;

import javax.persistence.EntityManager;

public class FelhasznaltAlkatreszDao extends BasicDao<FelhasznaltAlkatresz> {
    public FelhasznaltAlkatreszDao(EntityManager em) {
        super(FelhasznaltAlkatresz.class);
        this.em = em;
    }
}
