package daos;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Alkatresz;
import entities.OsAlkatresz;
import entities.QAlkatresz;
import filters.AlkatreszFilter;
import org.pmw.tinylog.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public  class AlkatreszDao extends BasicDao<Alkatresz> {

    public AlkatreszDao(EntityManager em) {
        super(Alkatresz.class);
        this.em = em;
    }

    public List<Alkatresz> find(AlkatreszFilter filter){

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QAlkatresz qAlkatresz = QAlkatresz.alkatresz;

        JPAQuery<Alkatresz> query = queryFactory.selectFrom(qAlkatresz);

        if(filter.getCikkszam()!=null){

            query.where(qAlkatresz.cikkszam.eq(filter.getCikkszam()));

        }
        if(filter.getAr()!=null){
            query.where(qAlkatresz.ar.eq(filter.getAr()));
        }
        if(filter.getGaranciaIdotartama()!=null){
            query.where(qAlkatresz.garanciaIdotartama.eq(filter.getGaranciaIdotartama()));
        }
        Logger.info(filter.getGaranciaIdotartama());
        if(!filter.getNev().equals("")){
            query.where(qAlkatresz.nev.eq(filter.getNev()));
        }
        return query.fetch();

    }
}
