package athletics.repositories.jpa;

import athletics.model.Customer;

public interface ICustomerRepository {

	Customer findByEmail(String email);
	
	void saveOrUpdateCustomer(Customer customer);
	
	Customer findById(Integer id);
}
