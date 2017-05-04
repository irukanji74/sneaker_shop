package athletics.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import athletics.dto.CustomerDto;
import athletics.model.AuthorizedCustomer;
import athletics.model.Customer;
import athletics.model.PasswordResetToken;
import athletics.model.Role;
import athletics.model.ShoppingCart;
import athletics.model.VerificationToken;
import athletics.repositories.jpa.ICustomerRepository;
import athletics.repositories.jpa.IPasswordResetTokenRepository;
import athletics.repositories.jpa.IVerificationTokenRepository;
import athletics.util.PasswordUtil;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

	@Autowired
    private ICustomerRepository repository;
	
	@Autowired
	private IVerificationTokenRepository tokenRepository;
	
	@Autowired
	IPasswordResetTokenRepository resetTokenRepository;
	
	@Override
	public void createNewCustomerAccount(CustomerDto customerDto) {
		final Customer customer = new Customer();
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);// ИЗМЕНИТЬ ПОРЯДОК НАЗНАЧЕНИЯ РОЛЕЙ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//roles.add(Role.ROLE_ADMIN);
		customer.setName(customerDto.getFirstName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(PasswordUtil.encode(customerDto.getPassword()));
		customer.setTelephone("0000000000");//TODO исправить !!!!!!!!!!!!!!!!!!!!!!!!!!
		System.err.println(customer.getPassword());
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = this.repository.findByEmail(email);
		if (customer == null) {
            throw new UsernameNotFoundException("Customer " + email + " is not found");
        }
		return new AuthorizedCustomer(customer);
	}

	@Override
	public boolean checkIfValidOldPassword(Customer customer, String password) {
		
		return PasswordUtil.isMatch(password, customer.getPassword());
	}

	@Override
	public void changeUserPassword(Customer customer, String newPassword) {
		System.err.println(customer + " " + newPassword );
		customer.setPassword(PasswordUtil.encode(newPassword));
		this.repository.saveOrUpdateCustomer(customer);
		
	}

	@Override
	public void resetUserPassword(Customer customer, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPasswordResetTokenForCustomer(Customer customer, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, customer);
        resetTokenRepository.save(myToken);
		
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return resetTokenRepository.findByToken(token);
	}



}
