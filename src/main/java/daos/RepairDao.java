package daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.QRepair;
import entities.Repair;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class RepairDao extends BasicDao<Repair> {

        public RepairDao(EntityManager em){
            super(Repair.class);
            this.em = em;
        }

    /**
     * This method fetches the ongoing repairs from the database that you are looking for.
     * */

        public List<Repair> onGoingRepairs(){
            JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
            QRepair qRepair = QRepair.repair;
            return queryFactory.selectFrom(qRepair).where(qRepair.endOfRepair.isNull()).fetch();
    }

    /**
     * @return All of the finished repairs from the database.
     * */

    public List<Repair> getFinishedRepairs() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QRepair qRepair = QRepair.repair;
        return queryFactory.selectFrom(qRepair).where(qRepair.endOfRepair.isNotNull()).fetch();

    }

    /**
     * This method fetches the cars from the database that you are searching for.
     *
     * @param from The searched repairs are made after this date
     * @param until The searched repairs are made before this date
     * @return List<Car> Returns a list of assembly types that best match with the filer.
     * */

    public List<Repair> getFinishedRepairs(Timestamp from, Timestamp until) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QRepair qRepair = QRepair.repair;
        return queryFactory.selectFrom(qRepair).where(qRepair.endOfRepair.between(from,until)).fetch();

    }
}
