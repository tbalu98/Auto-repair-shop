package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;
import filters.JavitasTipusFilter;
import nezetek.JavitasTipusNezet;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.selectFrom;

public class JavitasTipusDao extends BasicDao<OradijasJavitasTipus> {
    public JavitasTipusDao(EntityManager em) {
        super(OradijasJavitasTipus.class);
        this.em = em;
    }
/*
    public List<Szereles> folyamatbanLevoSzerelesek(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QSzereles qSzereles = QSzereles.szereles;
        return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.isNull()).fetch();
    }*/
    public List<OradijasJavitasTipus> findOradijasJavitasTipus(JavitasTipusFilter javitasTipusFilter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QOradijasJavitasTipus qOradijasJavitasTipus = QOradijasJavitasTipus.oradijasJavitasTipus;

            JPAQuery<OradijasJavitasTipus> query =  queryFactory.selectFrom(qOradijasJavitasTipus);
            if(javitasTipusFilter.getGaranciIdotartama()!=null){
                query.where(qOradijasJavitasTipus.garanciaIdotartama.eq(javitasTipusFilter.getGaranciIdotartama()));
            }
            if(!javitasTipusFilter.getLeiras().equals("")){
                query.where(qOradijasJavitasTipus.leiras.eq(javitasTipusFilter.getLeiras()));
            }
            return query.fetch();


    }

    public List<FixAruJavitasTipus> findFixaruJavitasTipusok(JavitasTipusFilter javitasTipusFilter){

            JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
            QFixAruJavitasTipus qFixAruJavitasTipus = QFixAruJavitasTipus.fixAruJavitasTipus;

            JPAQuery<FixAruJavitasTipus> query = queryFactory.selectFrom(qFixAruJavitasTipus);

            query.where(qFixAruJavitasTipus.fixAr.eq(javitasTipusFilter.getFixar()));

            if(javitasTipusFilter.getGaranciIdotartama()!=null){
                query.where(qFixAruJavitasTipus.garanciaIdotartama.eq(javitasTipusFilter.getGaranciIdotartama()));
            }
            if(!javitasTipusFilter.getLeiras().equals("")){
                query.where(qFixAruJavitasTipus.leiras.eq(javitasTipusFilter.getLeiras()));
            }

            return query.fetch();

    }
}
