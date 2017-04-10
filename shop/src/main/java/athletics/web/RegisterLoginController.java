package athletics.web;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import athletics.listeners.OnRegistrationCompleteEvent;
import athletics.model.Customer;
import athletics.model.Role;
import athletics.model.ShoppingCart;
import athletics.service.CustomerService;
import athletics.validators.CustomerValidator;

@Controller
public class RegisterLoginController {

	private final CustomerService service;
	
	ApplicationEventPublisher eventPublisher;

	@Autowired
	public RegisterLoginController(CustomerService service, ApplicationEventPublisher eventPublisher) {
		this.service = service;
		this.eventPublisher = eventPublisher;
	}
	
	@ModelAttribute("customer")
	public Customer customer(){
		System.out.println("adding attribute customer");
		return new Customer();
	}
	
	//TODO проверить вариант с передачей из метода attribute of model
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String toRegister(Model model){
		//делает то же, что и верхний метод
		//model.addAttribute("customer", new Customer());
		return "register";
	}
	
	/*http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-custom-property-editor/
	 * Если не уточнить("customer") на какой именно атрибут модели или request parameters из jsp стр. будет реагировать initBinder,
	 *  он будет вызываться на любой http request!!!*/
	@InitBinder("customer")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CustomerValidator());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String successRegister( @Valid @ModelAttribute("customer")final Customer cust,  final Errors errors, final Model modelMap
			, final HttpServletRequest request){
		if(errors.hasErrors()){
			return "register";
		}
		if(service.getByEmail(cust.getEmail()) != null){
			modelMap.addAttribute("email_exists", "User exists with this email! Try another email!");
			return "register";
		}
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);
		cust.setRoles(roles);
		cust.setCart(new ShoppingCart());
		service.save(cust);
		System.err.println(((Customer)service.getByEmail(cust.getEmail())).getId());
		
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(cust, getAppUrl(request), request.getLocale()));
		return "redirect:customer_account";
	}
	
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token)
			throws UnsupportedEncodingException {
		final String result = service.validateVerificationToken(token);
		if (result.equals("valid")) {
			final Customer customer = this.service.getCustomerByToken(token);
			System.out.println(customer);
		}
		model.addAttribute("message", "Добро пожаловать, ваш аккаунт подтвержден");
		return "register";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLoginPage(){
		return "register";
	}
	
	@RequestMapping(value="/to_login", method=RequestMethod.POST)
	public String loginSuccess(){
		return "customer_account";//изза security настроек перебрасывает на главную страницу TODO!!!!
	}
	
	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
}
