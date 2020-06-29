package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Gepjarmuparameter;
import entities.QGepjarmuparameter;

import javax.persistence.EntityManager;
import java.util.List;

public class GepjarmuparameterDao extends BasicDao<Gepjarmuparameter>{
    public GepjarmuparameterDao(EntityManager em) {
        super(Gepjarmuparameter.class);
        this.em = em;
    }

    public List<Gepjarmuparameter> find(Gepjarmuparameter gepjarmuparameter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QGepjarmuparameter qGepjarmuparameter = QGepjarmuparameter.gepjarmuparameter;

        JPAQuery<Gepjarmuparameter> query = queryFactory.selectFrom(qGepjarmuparameter);

        if(gepjarmuparameter.getMotorterfogat()!=null){
            query.where(qGepjarmuparameter.motorterfogat.like("%"+gepjarmuparameter.getMotorterfogat()+"%"));
        }
        if(!gepjarmuparameter.getTipus().equals("")){
            query.where(qGepjarmuparameter.tipus.like("%" + gepjarmuparameter.getTipus()+"%"));
        }
        if(gepjarmuparameter.getTeljesitmeny()!=null){
            query.where(qGepjarmuparameter.teljesitmeny.like("%"+gepjarmuparameter.getTeljesitmeny() +"%"));
        }

        return query.fetch();
    }
}