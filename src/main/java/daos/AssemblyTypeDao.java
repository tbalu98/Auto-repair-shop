package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;
import filters.AssemblyTypeFilter;

import javax.persistence.EntityManager;
import java.util.List;

public class AssemblyTypeDao extends BasicDao<HourlyPricedAssemblyType> {
    public AssemblyTypeDao(EntityManager em) {
        super(HourlyPricedAssemblyType.class);
        this.em = em;
    }
/*
    public List<Szereles> folyamatbanLevoSzerelesek(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QSzereles qSzereles = QSzereles.szereles;
        return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.isNull()).fetch();
    }*/
    public List<HourlyPricedAssemblyType> findOradijasJavitasTipus(AssemblyTypeFilter assemblyTypeFilter){
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

    public List<FixPricedAssemblyType> findFixaruJavitasTipusok(AssemblyTypeFilter assemblyTypeFilter){

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
