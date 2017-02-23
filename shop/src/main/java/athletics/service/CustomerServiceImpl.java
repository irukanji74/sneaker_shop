package athletics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import athletics.model.Customer;
import athletics.repositories.jpa.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private CustomerRepository repository;
	
	@Override
	public void save(Customer cust) {
		Assert.notNull(cust, "customer must not be null");
		repository.saveOrUpdateCustomer(cust);;
	}

	@Override
	public Customer getByEmail(String email) {
		Assert.notNull(email, "email must not be null");
		return repository.findByEmail(email);
	}

}
