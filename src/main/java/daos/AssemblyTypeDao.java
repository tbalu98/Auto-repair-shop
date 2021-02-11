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
        QOradijasJavitasTipus qOradijasJavitasTipus = QOradijasJavitasTipus.oradijasJavitasTipus;

            JPAQuery<HourlyPricedAssemblyType> query =  queryFactory.selectFrom(qOradijasJavitasTipus);
            if(assemblyTypeFilter.getGaranciIdotartama()!=null){
                query.where(qOradijasJavitasTipus.garanciaIdotartama.eq(assemblyTypeFilter.getGaranciIdotartama()));
            }
            if(!assemblyTypeFilter.getLeiras().equals("")){
                query.where(qOradijasJavitasTipus.leiras.eq(assemblyTypeFilter.getLeiras()));
            }
            return query.fetch();


    }

    public List<FixPricedAssemblyType> findFixaruJavitasTipusok(AssemblyTypeFilter assemblyTypeFilter){

            JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
            QFixAruJavitasTipus qFixAruJavitasTipus = QFixAruJavitasTipus.fixAruJavitasTipus;

            JPAQuery<FixPricedAssemblyType> query = queryFactory.selectFrom(qFixAruJavitasTipus);

            query.where(qFixAruJavitasTipus.fixAr.eq(assemblyTypeFilter.getFixar()));

            if(assemblyTypeFilter.getGaranciIdotartama()!=null){
                query.where(qFixAruJavitasTipus.garanciaIdotartama.eq(assemblyTypeFilter.getGaranciIdotartama()));
            }
            if(!assemblyTypeFilter.getLeiras().equals("")){
                query.where(qFixAruJavitasTipus.leiras.eq(assemblyTypeFilter.getLeiras()));
            }

            return query.fetch();

    }
}
