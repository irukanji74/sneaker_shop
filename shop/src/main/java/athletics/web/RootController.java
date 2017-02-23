package athletics.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import athletics.model.Customer;
import athletics.model.Role;
import athletics.model.ShoppingCart;
import athletics.service.CustomerService;

@Controller
public class RootController {
	
	private final CustomerService service;
	
	@Autowired
	public RootController(CustomerService service) {
		this.service = service;
	}

	@ModelAttribute("customer")
	public Customer customer(){
		return new Customer();
	}
	
	@RequestMapping(value="/")
	public String mainPage(){
		return "shop_face";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String toRegister(){
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String successRegister(@ModelAttribute("customer") Customer cust){
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
	
	@RequestMapping(value="/customer_account", method=RequestMethod.GET)
	public String toCustomerAccount(){
		return "customer_account";
	}
	
	@RequestMapping(value="/to_login", method=RequestMethod.POST)
	public String loginSuccess(){
		return "customer_account";
	}
}
