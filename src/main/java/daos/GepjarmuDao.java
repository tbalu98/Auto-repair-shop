package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;
import entities.QGepjarmu;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.selectFrom;

public class GepjarmuDao extends BasicDao<Gepjarmu> {

    public GepjarmuDao(EntityManager entityManager){
        super(Gepjarmu.class);
        this.em = entityManager;
    }

    public List<Gepjarmu> find(Gepjarmu gepjarmuFilter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QGepjarmu qGepjarmu = QGepjarmu.gepjarmu;
        JPAQuery<Gepjarmu> query = queryFactory.selectFrom(qGepjarmu);

        if(gepjarmuFilter.getAlvazszam()!=null){

            query.where(qGepjarmu.alvazszam.eq(gepjarmuFilter.getAlvazszam()));

        }

        if(gepjarmuFilter.getEvjarat()!=null){

            query.where(qGepjarmu.evjarat.eq(gepjarmuFilter.getEvjarat()));

        }

        return query.fetch();
    }

}

