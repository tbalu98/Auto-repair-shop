package daos;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Alkatresz;
import entities.QAlkatresz;
import entities.QUgyfel;
import entities.Ugyfel;
import org.pmw.tinylog.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class UgyfelDao extends BasicDao<Ugyfel> {
    public UgyfelDao(EntityManager em) {
        super(Ugyfel.class);
        this.em = em;
    }

    public List<Ugyfel> find(Ugyfel ugyfel){

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QUgyfel qUgyfel = QUgyfel.ugyfel;

        JPAQuery<Ugyfel> query = queryFactory.selectFrom(qUgyfel);

        if(!ugyfel.getLakcim().equals("")){
            query.where(qUgyfel.lakcim.like("%"+ugyfel.getLakcim()+"%"));
        }
        if(!ugyfel.getNev().equals("")){
            Logger.info("van név" + ugyfel.getNev());
            query.where(qUgyfel.nev.like("%"+ugyfel.getNev()+"%" ));
        }
        if(!ugyfel.getTelefonszam().equals("")){
        query.where(qUgyfel.telefonszam.like("%"+ugyfel.getTelefonszam()+"%"));
        }
        Logger.info(query.fetch());
        return query.fetch();

    }
}
