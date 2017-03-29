package athletics.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import athletics.model.Customer;
import athletics.model.PasswordChangeForm;
import athletics.service.CustomerService;
import athletics.validators.CustomerValidator;
import athletics.validators.PasswordChangeValidator;

@Controller

public class CustomerAccountController {

	CustomerService service;

	@Autowired
	public CustomerAccountController(CustomerService service) {
		this.service = service;
	}
	
	@InitBinder("changePassForm")
	public void initPasswordChange(WebDataBinder binder){
		binder.addValidators(new PasswordChangeValidator());;
	}
	
	@ModelAttribute("changePassForm")
	public PasswordChangeForm passwordChange(){
		return new PasswordChangeForm();
	}
	
	@ModelAttribute("customerToAlter")
	public Customer customerToAlter(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = this.service.getByEmail(auth.getName());
		System.err.println(customer);
		return customer;
	}
	
	@RequestMapping(value="/alterCustomer", method=RequestMethod.POST )
	public String alterCustomer(@ModelAttribute("customerToAlter")Customer customer){
		this.service.save(customer);
		return "customer_account";
	}
	
	@RequestMapping(value="/customer_account", method=RequestMethod.GET )
	public String customerAccountPage(){
		return "customer_account";
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String changePassword(Model model, @Valid @ModelAttribute("changePassForm") PasswordChangeForm changePassword, Errors errors){
		if(errors.hasErrors()){
			return "customer_account";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = this.service.getByEmail(auth.getName());
		String password = customer.getPassword();
		String passwordToChange = changePassword.getNewPassword();
		String oldPassword = changePassword.getOldPassword();
		if(password.equals(oldPassword)){
			customer.setPassword(passwordToChange);
			this.service.save(customer);
		} else{
			model.addAttribute("wrong_password", "Wrong password, try again");
			return "customer_account";
		}
		
		return "redirect:customer_account";
	}

}
