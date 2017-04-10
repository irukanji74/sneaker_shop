package athletics.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import athletics.model.Customer;
import athletics.model.VerificationToken;
import athletics.repositories.jpa.CustomerRepository;
import athletics.repositories.jpa.IVerificationTokenRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

	@Autowired
    private CustomerRepository repository;
	
	@Autowired
	private IVerificationTokenRepository tokenRepository;
	
	@Override
	public void save(Customer cust) {
		//Assert.notNull(cust, "customer must not be null");
		
		repository.saveOrUpdateCustomer(cust);;
	}

	@Override
	public Customer getByEmail(String email) {
		//Assert.notNull(email, "email must not be null");
		   return repository.findByEmail(email);
		
	}

	@Override
	public Customer getById(Integer id) {
		//Assert.notNull(id, "id must not be null");
		return repository.findById(id);
	}

	@Override
	public void createVerificationTokenForUser(Customer customer, String token) {
		VerificationToken verificationToken = new VerificationToken(token, customer);
		this.tokenRepository.save(verificationToken);
	}

	@Override
	public String validateVerificationToken(String token) {
		System.err.println(token);
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
		if (verificationToken == null) {
            return TOKEN_INVALID;
        }
		final Customer customer = verificationToken.getCustomer();
		final Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }
		
		customer.setEnabled(true);
		repository.saveOrUpdateCustomer(customer);
		return TOKEN_VALID;
	}

	@Override
	public Customer getCustomerByToken(String token) {
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
		 if (verificationToken != null) {
	            return verificationToken.getCustomer();
	        }
		return null;
	}

}
