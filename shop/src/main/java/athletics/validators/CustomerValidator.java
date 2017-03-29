package athletics.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import athletics.model.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Customer customer = (Customer)obj;
		/*if(customer.getEmail().isEmpty()){
			errors.rejectValue("email", "customer.email", "email field cannot be empty");
		}*/
		/*the same as if customer.getEmail().isEmpty() -> errors.rejectValue...*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "myProperties.mesage","email is empty");
		if(customer.getName().isEmpty()){
			errors.rejectValue("name", "customer.name", "name field cannot be empty");
		}
		if(customer.getPassword().isEmpty()){
			errors.rejectValue("password", "customer.password", "password field cannot be empty");
		}
		
	}

}
