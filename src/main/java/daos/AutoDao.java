package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;
import entities.QGepjarmu;

import javax.persistence.EntityManager;
import java.util.List;

public class AutoDao extends BasicDao<Car> {

    public AutoDao(EntityManager entityManager){
        super(Car.class);
        this.em = entityManager;
    }

    public List<Car> find(Car carFilter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QGepjarmu qGepjarmu = QGepjarmu.gepjarmu;
        JPAQuery<Car> query = queryFactory.selectFrom(qGepjarmu);

        if(carFilter.getAlvazszam()!=null){

            query.where(qGepjarmu.alvazszam.eq(carFilter.getAlvazszam()));

        }

        if(carFilter.getEvjarat()!=null){

            query.where(qGepjarmu.evjarat.eq(carFilter.getEvjarat()));

        }

        return query.fetch();
    }

}

