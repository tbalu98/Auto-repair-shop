package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.CarParameter;
import entities.QCarParameter;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * A dao for CarParameter objects.
 * */

public class CarParameterDao extends BasicDao<CarParameter>{
    public CarParameterDao(EntityManager em) {
        super(CarParameter.class);
        this.em = em;
    }

    /**
     * This method fetches the car parameters from the database that you are searching for.
     *
     * @param carParameter This object contains the information about the car we are looking for,
     * @return List<Car> Returns a list of assembly types that best match with the filer.
     * */
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