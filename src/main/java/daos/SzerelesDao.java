package daos;

import com.querydsl.jpa.impl.JPAQueryFactory;
import controllers.LezartSzerelesek;
import entities.QSzereles;
import entities.Szereles;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class SzerelesDao extends BasicDao<Szereles> {

        public SzerelesDao(EntityManager em){
            super(Szereles.class);
            this.em = em;
        }

        public List<Szereles> folyamatbanLevoSzerelesek(){
            JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
            QSzereles qSzereles = QSzereles.szereles;
            return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.isNull()).fetch();
    }

    public List<Szereles> getLezartSzerelesek() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QSzereles qSzereles = QSzereles.szereles;
        return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.isNotNull()).fetch();

    }

    public List<Szereles> getLezartSzerelesek(Timestamp tol, Timestamp ig) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QSzereles qSzereles = QSzereles.szereles;
        return queryFactory.selectFrom(qSzereles).where(qSzereles.szerelesVege.between(tol,ig)).fetch();

    }
}
