package athletics.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import athletics.model.Customer;

@Repository
public class JpaCustomerRepositoryImpl implements ICustomerRepository{

	/*
	 * Here we are by default in "Entity Manager per transaction" mode. In this mode, 
	 * if we use this Entity Manager inside a @Transactional method, then the method will run in a single database transaction.
	 * http://blog.jhades.org/how-does-spring-transactional-really-work/*/
	@PersistenceContext
	private EntityManager em;
	  
	/*Бросает исключение ! а не null---http://stackoverflow.com/questions/2002993/jpa-getsingleresult-or-null?noredirect=1&lq=1*/
	@Override
	@Transactional
	public Customer findByEmail(String email) {
		TypedQuery<Customer> query = this.em.createQuery("SELECT DISTINCT customer FROM Customer customer WHERE customer.email LIKE :email"
				                                         , Customer.class);
        query.setParameter("email", email + "%");
        Customer customer = getSingleResult(query);
        return customer;
	}

	@Override
	@Transactional
	public void saveOrUpdateCustomer(Customer customer) {
		 if (customer.getId() == null) {
	            this.em.persist(customer);
	        } else {
	            this.em.merge(customer);
	        }
	}

	@Override
	@Transactional(readOnly=true)
	public Customer findById(Integer id) {
		Query query = this.em.createQuery("SELECT customer FROM customer WHERE id like :id");
		return (Customer)query.getSingleResult();
	}

	public static <T> T getSingleResult(TypedQuery<T> query) {
	    query.setMaxResults(1);
	    List<T> list = query.getResultList();
	    if (list == null || list.isEmpty()) {
	        return null;
	    }

	    return list.get(0);
	}
	
}
