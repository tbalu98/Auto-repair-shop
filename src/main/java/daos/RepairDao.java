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

        public List<Repair> folyamatbanLevoSzerelesek(){
            JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
            QRepair qRepair = QRepair.repair;
            return queryFactory.selectFrom(qRepair).where(qRepair.endOfRepair.isNull()).fetch();
    }

    public List<Repair> getLezartSzerelesek() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QRepair qRepair = QRepair.repair;
        return queryFactory.selectFrom(qRepair).where(qRepair.endOfRepair.isNotNull()).fetch();

    }

    public List<Repair> getLezartSzerelesek(Timestamp tol, Timestamp ig) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QRepair qRepair = QRepair.repair;
        return queryFactory.selectFrom(qRepair).where(qRepair.endOfRepair.between(tol,ig)).fetch();

    }
}
