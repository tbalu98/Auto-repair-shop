package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.CarParameter;
import entities.QCarParameter;

import javax.persistence.EntityManager;
import java.util.List;

public class AutoParameterDao extends BasicDao<CarParameter>{
    public AutoParameterDao(EntityManager em) {
        super(CarParameter.class);
        this.em = em;
    }

    public List<CarParameter> find(CarParameter carParameter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QCarParameter qCarParameter = QCarParameter.carParameter;

        JPAQuery<CarParameter> query = queryFactory.selectFrom(qCarParameter);

        if(carParameter.getEngineVolume()!=null){
            query.where(qCarParameter.engineVolume.like("%"+ carParameter.getEngineVolume()+"%"));
        }
        if(!carParameter.getType().equals("")){
            query.where(qCarParameter.type.like("%" + carParameter.getType()+"%"));
        }
        if(carParameter.getPower()!=null){
            query.where(qCarParameter.power.like("%"+ carParameter.getPower() +"%"));
        }

        return query.fetch();
    }
}