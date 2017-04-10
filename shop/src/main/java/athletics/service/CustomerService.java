package athletics.service;

import athletics.model.Customer;

public interface CustomerService {

	void save(Customer user);

	Customer getByEmail(String email);

	Customer getById(Integer id);

	void createVerificationTokenForUser(Customer customer, String token);

	String validateVerificationToken(String token);

	Customer getCustomerByToken(String token);
}
