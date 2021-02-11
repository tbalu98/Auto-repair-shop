package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.CarParameter;
import entities.QGepjarmuparameter;

import javax.persistence.EntityManager;
import java.util.List;

public class AutoParameterDao extends BasicDao<CarParameter>{
    public AutoParameterDao(EntityManager em) {
        super(CarParameter.class);
        this.em = em;
    }

    public List<CarParameter> find(CarParameter carParameter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QGepjarmuparameter qGepjarmuparameter = QGepjarmuparameter.gepjarmuparameter;

        JPAQuery<CarParameter> query = queryFactory.selectFrom(qGepjarmuparameter);

        if(carParameter.getMotorterfogat()!=null){
            query.where(qGepjarmuparameter.motorterfogat.like("%"+ carParameter.getMotorterfogat()+"%"));
        }
        if(!carParameter.getTipus().equals("")){
            query.where(qGepjarmuparameter.tipus.like("%" + carParameter.getTipus()+"%"));
        }
        if(carParameter.getTeljesitmeny()!=null){
            query.where(qGepjarmuparameter.teljesitmeny.like("%"+ carParameter.getTeljesitmeny() +"%"));
        }

        return query.fetch();
    }
}