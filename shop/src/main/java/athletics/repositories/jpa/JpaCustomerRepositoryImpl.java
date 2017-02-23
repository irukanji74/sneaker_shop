package athletics.repositories.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import athletics.model.Customer;

@Repository
public class JpaCustomerRepositoryImpl implements CustomerRepository{

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

	
}
