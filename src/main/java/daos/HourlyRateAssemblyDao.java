package daos;

import entities.HourlyPricedAssemblyType;

import javax.persistence.EntityManager;

public class HourlyRateAssemblyDao extends BasicDao<HourlyPricedAssemblyType> {
    public HourlyRateAssemblyDao(EntityManager em) {
        super(HourlyPricedAssemblyType.class);
        this.em = em;
    }
}
