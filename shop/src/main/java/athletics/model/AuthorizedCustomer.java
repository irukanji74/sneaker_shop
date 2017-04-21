package athletics.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import athletics.dto.CustomerDto;

public class AuthorizedCustomer extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	private CustomerDto customerDto;

	public AuthorizedCustomer(Customer customer) {
		super(customer.getEmail(), customer.getPassword(), customer.isEnabled(), true, true, true, customer.getRoles());
		System.err.println("In AuthorizedCustomer constructor");
	}
	
	public static AuthorizedCustomer safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedCustomer) ? (AuthorizedCustomer) principal : null;
    }

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

}
