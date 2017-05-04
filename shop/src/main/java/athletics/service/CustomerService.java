package athletics.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import athletics.dto.CustomerDto;
import athletics.model.Customer;
import athletics.model.PasswordResetToken;

public interface CustomerService extends  UserDetailsService{

	void save(Customer customer);
	
	void createNewCustomerAccount(CustomerDto customerDto);

	Customer getByEmail(String email);

	Customer getById(Integer id);

	void createVerificationTokenForUser(Customer customer, String token);

	String validateVerificationToken(String token);

	Customer getCustomerByToken(String token);

	boolean checkIfValidOldPassword(Customer customer, String password);

	void resetUserPassword(Customer customer, String newPassword);

	void changeUserPassword(Customer customer, String passwordToChange);

	void createPasswordResetTokenForCustomer(Customer customer, String token);

	PasswordResetToken getPasswordResetToken(String token);
}
