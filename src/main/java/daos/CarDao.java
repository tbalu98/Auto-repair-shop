package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.*;
import entities.QCar;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * A Dao class for cars.
 *
 * */

public class CarDao extends BasicDao<Car> {

    public CarDao(EntityManager entityManager){
        super(Car.class);
        this.em = entityManager;
    }
    /**
     * This method fetches the cars from the database that you are searching for.
     *
     * @param carFilter This object contains the information about the car we are looking for,
     * @return List<Car> Returns a list of assembly types that best match with the filer.
     * */
    public List<Car> find(Car carFilter){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QCar qCar = QCar.car;
        JPAQuery<Car> query = queryFactory.selectFrom(qCar);

        if(carFilter.getVehicleIdentificationNumber()!=null){

            query.where(qCar.vehicleIdentificationNumber.eq(carFilter.getVehicleIdentificationNumber()));

        }

        if(carFilter.getYear()!=null){

            query.where(qCar.year.eq(carFilter.getYear()));

        }

        return query.fetch();
    }

}

