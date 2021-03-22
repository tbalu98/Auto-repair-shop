package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Part;
import entities.QPart;
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
        QPart qPart = QPart.part;

        JPAQuery<Part> query = queryFactory.selectFrom(qPart);

        if(filter.getCikkszam()!=null){

            query.where(qPart.partCode.eq(filter.getCikkszam()));

        }
        if(filter.getAr()!=null){
            query.where(qPart.price.eq(filter.getAr()));
        }
        if(filter.getGaranciaIdotartama()!=null){
            query.where(qPart.guarantee.eq(filter.getGaranciaIdotartama()));
        }
        Logger.info(filter.getGaranciaIdotartama());
        if(!filter.getNev().equals("")){
            query.where(qPart.name.eq(filter.getNev()));
        }
        return query.fetch();

    }
}
