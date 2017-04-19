package athletics.service;

import athletics.dto.CustomerDto;
import athletics.model.Customer;

public interface CustomerService {

	void save(Customer customer);
	
	void createNewCustomerAccount(CustomerDto customerDto);

	Customer getByEmail(String email);

	Customer getById(Integer id);

	void createVerificationTokenForUser(Customer customer, String token);

	String validateVerificationToken(String token);

	Customer getCustomerByToken(String token);
}
