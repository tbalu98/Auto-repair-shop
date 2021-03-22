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

    /**
     * This method fetches the hourly priced assembly types from the database that you are searching for.
     *
     * @param partFilter This object contains the information about the assembly type we are looking for,
     * @return List<Part> Returns a list of parts that best match with the filer.
     * */

    public List<Part> find(PartFilter partFilter){

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QPart qPart = QPart.part;

        JPAQuery<Part> query = queryFactory.selectFrom(qPart);

        if(partFilter.getCikkszam()!=null){

            query.where(qPart.partCode.eq(partFilter.getCikkszam()));

        }
        if(partFilter.getAr()!=null){
            query.where(qPart.price.eq(partFilter.getAr()));
        }
        if(partFilter.getGaranciaIdotartama()!=null){
            query.where(qPart.guarantee.eq(partFilter.getGaranciaIdotartama()));
        }
        Logger.info(partFilter.getGaranciaIdotartama());
        if(!partFilter.getNev().equals("")){
            query.where(qPart.name.eq(partFilter.getNev()));
        }
        return query.fetch();

    }
}
