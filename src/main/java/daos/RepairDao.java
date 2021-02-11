package daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.QSzereles;
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
            QSzereles qSzereles = QSzereles.szereles;
            return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.isNull()).fetch();
    }

    public List<Repair> getLezartSzerelesek() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QSzereles qSzereles = QSzereles.szereles;
        return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.isNotNull()).fetch();

    }

    public List<Repair> getLezartSzerelesek(Timestamp tol, Timestamp ig) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QSzereles qSzereles = QSzereles.szereles;
        return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.between(tol,ig)).fetch();

    }
}
