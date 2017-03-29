package athletics.repositories.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import athletics.model.Customer;

@Repository
public class JpaCustomerRepositoryImpl implements CustomerRepository{

	/*
	 * Here we are by default in "Entity Manager per transaction" mode. In this mode, 
	 * if we use this Entity Manager inside a @Transactional method, then the method will run in a single database transaction.
	 * http://blog.jhades.org/how-does-spring-transactional-really-work/*/
	@PersistenceContext
	private EntityManager em;
	  
	@Override
	public Customer findByEmail(String email) {
		Query query = this.em.createQuery("SELECT DISTINCT customer FROM Customer customer WHERE customer.email LIKE :email");
        query.setParameter("email", email + "%");
        return (Customer) query.getSingleResult();
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

	
}
