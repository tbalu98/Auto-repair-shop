package daos;

import entities.FelhasznaltAlkatresz;

import javax.persistence.EntityManager;


public class EladottAlkatreszDao extends BasicDao<FelhasznaltAlkatresz> {
    public EladottAlkatreszDao(EntityManager em) {
        super(FelhasznaltAlkatresz.class);
        this.em = em;
    }
}
