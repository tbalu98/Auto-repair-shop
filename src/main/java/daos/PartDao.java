package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Part;
import entities.QAlkatresz;
import filters.PartFilter;
import org.pmw.tinylog.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public  class PartDao extends BasicDao<Part> {

    public PartDao(EntityManager em) {
        super(Part.class);
        this.em = em;
    }

    public List<Part> find(PartFilter filter){

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QAlkatresz qAlkatresz = QAlkatresz.alkatresz;

        JPAQuery<Part> query = queryFactory.selectFrom(qAlkatresz);

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
