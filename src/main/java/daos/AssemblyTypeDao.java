package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;
import filters.AssemblyTypeFilter;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * Dao class for assembly types.
 * */


public class AssemblyTypeDao extends BasicDao<HourlyPricedAssemblyType> {
    public AssemblyTypeDao(EntityManager em) {
        super(HourlyPricedAssemblyType.class);
        this.em = em;
    }

    /**
     * This method fetches the hourly priced assembly types from the database that you are searching for.
     *
     * @param assemblyTypeFilter This object contains the information about the assembly type we are looking for,
     * @return List<HourlyPricedAssemblyType> Returns a list of assembly types that best match with the filer.
     * */


    public List<HourlyPricedAssemblyType> findHourlyPricedAssemblyType(AssemblyTypeFilter assemblyTypeFilter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QHourlyPricedAssemblyType qHourlyPricedAssemblyType = QHourlyPricedAssemblyType.hourlyPricedAssemblyType;

            JPAQuery<HourlyPricedAssemblyType> query =  queryFactory.selectFrom(qHourlyPricedAssemblyType);
            if(assemblyTypeFilter.getGaranciIdotartama()!=null){
                query.where(qHourlyPricedAssemblyType.guarantee.eq(assemblyTypeFilter.getGaranciIdotartama()));
            }
            if(!assemblyTypeFilter.getLeiras().equals("")){
                query.where(qHourlyPricedAssemblyType.decription.eq(assemblyTypeFilter.getLeiras()));
            }
            return query.fetch();


    }

    /**
     * This method returns the fix priced assembly types that you are searching for.
     *
     * @param assemblyTypeFilter This object contains the information about the assembly type we are looking for,
     * @return List<FixPricedAssemblyType> Returns a list of assembly types that best match with the filer.
     * */


    public List<FixPricedAssemblyType> findFixPricedAssemblyType(AssemblyTypeFilter assemblyTypeFilter){

            JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
            QFixPricedAssemblyType qFixPricedAssemblyType = QFixPricedAssemblyType.fixPricedAssemblyType;

            JPAQuery<FixPricedAssemblyType> query = queryFactory.selectFrom(qFixPricedAssemblyType);

            query.where(qFixPricedAssemblyType.fixAr.eq(assemblyTypeFilter.getFixar()));

            if(assemblyTypeFilter.getGaranciIdotartama()!=null){
                query.where(qFixPricedAssemblyType.guarantee.eq(assemblyTypeFilter.getGaranciIdotartama()));
            }
            if(!assemblyTypeFilter.getLeiras().equals("")){
                query.where(qFixPricedAssemblyType.decription.eq(assemblyTypeFilter.getLeiras()));
            }

            return query.fetch();

    }
}
