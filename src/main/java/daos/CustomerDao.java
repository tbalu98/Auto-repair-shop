package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Customer;
import entities.QUgyfel;
import org.pmw.tinylog.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDao extends BasicDao<Customer> {
    public CustomerDao(EntityManager em) {
        super(Customer.class);
        this.em = em;
    }

    public List<Customer> find(Customer customer){

        JPAQueryFactory queryFactory = new JPAQueryFactory(this.em);
        QUgyfel qUgyfel = QUgyfel.ugyfel;

        JPAQuery<Customer> query = queryFactory.selectFrom(qUgyfel);

        if(!customer.getLakcim().equals("")){
            query.where(qUgyfel.lakcim.like("%"+ customer.getLakcim()+"%"));
        }
        if(!customer.getNev().equals("")){
            Logger.info("van név" + customer.getNev());
            query.where(qUgyfel.nev.like("%"+ customer.getNev()+"%" ));
        }
        if(!customer.getTelefonszam().equals("")){
        query.where(qUgyfel.telefonszam.like("%"+ customer.getTelefonszam()+"%"));
        }
        Logger.info(query.fetch());
        return query.fetch();

    }
}
