package athletics.service;

import athletics.model.Customer;

public interface CustomerService {
	 
	    void save(Customer user);

	    Customer getByEmail(String email);
}
