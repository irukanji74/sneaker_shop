package athletics.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import athletics.model.Customer;
import athletics.model.Role;
import athletics.model.ShoppingCart;
import athletics.service.CustomerService;
import athletics.validators.CustomerValidator;

@Controller
public class RegisterLoginController {

	private final CustomerService service;

	@Autowired
	public RegisterLoginController(CustomerService service) {
		this.service = service;
	}
	
	/*@ModelAttribute("customer")
	public Customer customer(){
		System.out.println("adding attribute customer");
		return new Customer();
	}*/
	
	//TODO проверить вариант с передачей из метода attribute of model
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String toRegister(Model model){
		//делает то же, что и верхний метод
		model.addAttribute("customer", new Customer());
		return "register";
	}
	
	/*http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-custom-property-editor/
	 * Если не уточнить("customer") на какой именно атирибут модели или request parameters из jsp стр. будет реагировать initBinder,
	 *  он будет вызываться на любой http request!!!*/
	@InitBinder("customer")
	public void initBinder(WebDataBinder binder) {
		System.err.println("inside initBinder");
		binder.addValidators(new CustomerValidator());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String successRegister( @Valid @ModelAttribute("customer")Customer cust,  Errors errors){
		if(errors.hasErrors()){
			return "register";
		}
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);
		cust.setRoles(roles);
		cust.setCart(new ShoppingCart());
		System.err.println(cust);
		service.save(cust);
		
		return "redirect:customer_account";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLoginPage(){
		return "register";
	}
	
	@RequestMapping(value="/to_login", method=RequestMethod.POST)
	public String loginSuccess(){
		return "customer_account";
	}
	
}
