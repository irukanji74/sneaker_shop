package athletics.web;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

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

import athletics.dto.CustomerDto;
import athletics.model.Customer;
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
	
	/*@ModelAttribute("customer")
	public Customer customer(){
		System.out.println("adding attribute customer");
		return new Customer();
	}*/
	
	//TODO проверить вариант с передачей из метода attribute of model
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String toRegister(Model model){
		model.addAttribute("customerDto", new CustomerDto());
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
	public String successRegister( @Valid @ModelAttribute("customerDto")final CustomerDto customerDto,  final Errors errors
			                       , final Model modelMap , final HttpServletRequest request){
		
		if(errors.hasErrors()){
			System.err.println("Has errors!!!!");
			return "register";
		}
		/*if(service.getByEmail(customerDto.getEmail()) != null){
			modelMap.addAttribute("email_exists", "User exists with this email! Try another email!");
			return "register";
		}*/
		this.service.createNewCustomerAccount(customerDto);
		
		//eventPublisher.publishEvent(new OnRegistrationCompleteEvent(cust, getAppUrl(request), request.getLocale()));
		return "redirect:customer_account";
	}
	
	//TODO доделать TOKEN_INVALID, TOKEN_EXPIRED
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token)
			throws UnsupportedEncodingException {
		final String result = service.validateVerificationToken(token);
		if (result.equals("valid")) {
			final Customer customer = this.service.getCustomerByToken(token);
			System.out.println(customer);
		}
		model.addAttribute("Welcome_Message", "Добро пожаловать, ваш аккаунт подтвержден");
		return "register";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLoginPage(){
		return "register";
	}
	
	//SecurityContext берет на себя работу по переходу на login-processing-url="/to_login"
	/*@RequestMapping(value="/to_login", method=RequestMethod.POST)
	public String loginSuccess(){
		return "customer_account";//изза security настроек перебрасывает на главную страницу TODO!!!!
	}*/
	
	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
}
