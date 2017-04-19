package athletics.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import athletics.dto.CustomerDto;
import athletics.model.Customer;
import athletics.model.Role;
import athletics.model.ShoppingCart;
import athletics.model.VerificationToken;
import athletics.repositories.jpa.CustomerRepository;
import athletics.repositories.jpa.IVerificationTokenRepository;
import athletics.util.PasswordUtil;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

	@Autowired
    private CustomerRepository repository;
	
	@Autowired
	private IVerificationTokenRepository tokenRepository;
	
	@Autowired
    private PasswordUtil  passwordEncoder;
	
	
	@Override
	public void createNewCustomerAccount(CustomerDto customerDto) {
		final Customer customer = new Customer();
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);// ИЗМЕНИТЬ ПОРЯДОК НАЗНАЧЕНИЯ РОЛЕЙ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		roles.add(Role.ROLE_ADMIN);
		customer.setName(customerDto.getFirstName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		customer.setCart(new ShoppingCart());
		customer.setRoles(roles);
		this.save(customer);
		
	}
	
	@Override
	public void save(Customer customer) {
		repository.saveOrUpdateCustomer(customer);;
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
