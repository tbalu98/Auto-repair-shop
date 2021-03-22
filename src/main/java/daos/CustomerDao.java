package daos;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.Customer;
import entities.QCustomer;

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
        QCustomer qCustomer = QCustomer.customer;

        JPAQuery<Customer> query = queryFactory.selectFrom(qCustomer);

        if(!customer.getAddress().equals("")){
            query.where(qCustomer.address.like("%"+ customer.getAddress()+"%"));
        }
        if(!customer.getName().equals("")){
            Logger.info("van név" + customer.getName());
            query.where(qCustomer.name.like("%"+ customer.getName()+"%" ));
        }
        if(!customer.getTelephoneNumber().equals("")){
        query.where(qCustomer.telephoneNumber.like("%"+ customer.getTelephoneNumber()+"%"));
        }
        Logger.info(query.fetch());
        return query.fetch();

    }
}
