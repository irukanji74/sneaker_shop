package athletics.web;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import athletics.dto.CustomerDto;
import athletics.model.Customer;
import athletics.model.PasswordChangeForm;
import athletics.model.PasswordResetToken;
import athletics.service.CustomerService;
import athletics.validators.PasswordChangeValidator;

@Controller
public class CustomerAccountController {

	 private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	 
	public CustomerService service;

	@Autowired
	public CustomerAccountController(CustomerService service) {
		this.service = service;
	}
	
	@InitBinder("changePassForm")
	public void initPasswordChange(WebDataBinder binder){
		binder.addValidators(new PasswordChangeValidator());
	}
	
	@ModelAttribute("updatePassForm")
	public PasswordChangeForm passwordChange(){
		return new PasswordChangeForm();
	}
	
	@ModelAttribute("customerToAlter")
	public Customer customerToAlter(){
		//есть 3 варианта достать principal из метода при помощи разных аргументов
		//http://www.baeldung.com/get-user-in-spring-security
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = this.service.getByEmail(auth.getName());
		System.err.println(customer);
		return customer;
	}
	
	@RequestMapping(value="/alterCustomer", method=RequestMethod.POST )
	public String alterCustomer(@Valid @ModelAttribute("customerToAlter")Customer customer, BindingResult result){
		if(result.hasErrors()){
			return "customer_account";
		}
		this.service.save(customer);
		return "customer_account";
	}
	
	@RequestMapping(value="/customer_account", method=RequestMethod.GET )
	public String customerAccountPage(){
		return "customer_account";
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	public String changePassword(Model model, @Valid @ModelAttribute("updatePassForm") PasswordChangeForm changePassword, Errors errors){
		
		if(errors.hasErrors()){
			System.err.println("has errors!!!!");
			return "customer_account";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = this.service.getByEmail(auth.getName());
		if(!this.service.checkIfValidOldPassword(customer, changePassword.getOldPassword())){
			System.err.println("Внутри валидации PasswordEncoder!!!");
			model.addAttribute("wrong_password", "Wrong password, try again");
			return "customer_account";
		}
		/*String password = customer.getPassword();
		String passwordToChange = changePassword.getNewPassword();
		String oldPassword = changePassword.getOldPassword();
		if(password.equals(oldPassword)){
			customer.setPassword(passwordToChange);
			this.service.save(customer);
		} else{
			model.addAttribute("wrong_password", "Wrong password, try again");
			return "customer_account";
		}*/
		String passwordToChange = changePassword.getNewPassword();
		
		this.service.changeUserPassword(customer, passwordToChange);
		
		return "redirect:customer_account";
	}
	
	@RequestMapping(value="/register/forget_password", method=RequestMethod.GET )
	public String forgetPassword(){
		return "forget_password";
	}
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST )
	public String sendEmail(HttpServletRequest request, final Model model,
	                         @RequestParam(value="inputEmail", required=false) String inputEmail){
		Customer customer = this.service.getByEmail(inputEmail);
		if(customer == null){
			model.addAttribute("customerNotFound", "Пользователь с таким мейлом не найден");
			return "forget_password";
		}
		
		final String token = UUID.randomUUID().toString();
		this.service.createPasswordResetTokenForCustomer(customer, token);
		try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            System.err.println(appUrl);
            final SimpleMailMessage email = constructResetTokenEmail(appUrl, request.getLocale(), token, customer);
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("localhost"); 
            //System.out.println(env.getProperty("smtp.host"));
            mailSender.setPort(2525); 
            mailSender.setProtocol("smtp"); 
            mailSender.setUsername("test"); 
            mailSender.setPassword("test");
            mailSender.send(email);
        }   catch (final MailAuthenticationException e) {
            LOGGER.debug("MailAuthenticationException", e);
            return "redirect:forget_password";
        }
		model.addAttribute("check_mail", "Проверьте свою почту, и перейдите по ссылке для восстановления пароля!");
		return "forget_password";
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.GET )
	public String getResetPasswordForm(final HttpServletRequest request, final Model model, @RequestParam("id") final long id,
                                        @RequestParam("token") final String token){
		final Locale locale = request.getLocale();
		final PasswordResetToken passResetToken = service.getPasswordResetToken(token);
		System.err.println(passResetToken.toString());
		final Customer customer  = passResetToken.getCustomer();
		if(passResetToken == null || (customer.getId() != id)){
			model.addAttribute("token_invalid", "Токен ваш, батенька, фуфел натуральный!");
			LOGGER.debug("Токен ваш, батенька, фуфел натуральный!");
			return "redirect:register";
		  }
		
		final Calendar cal = Calendar.getInstance();
		if ((passResetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("token_expired", "Токен ваш, батенька, испортился и воняет!");
			LOGGER.debug("Токен ваш, батенька, испортился и воняет!");
			return "redirect:register";
		}
		
		Authentication auth = new UsernamePasswordAuthenticationToken( customer, null
				                               , service.loadUserByUsername(customer.getEmail()).getAuthorities());
			    SecurityContextHolder.getContext().setAuthentication(auth);
		
		return "redirect:resetPasswordFormPage";
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String resetNewPassword(Model modelMap, @RequestParam("password") final String password
			                                      , @RequestParam("matchingPassword") final String matchingPassword){
		if(password.equals(matchingPassword)){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.err.println("in method -- resetNewPassword " + auth.getName());
			Customer customer = (Customer)auth.getPrincipal();
			//Customer customer = this.service.getByEmail(auth.getName());
			System.err.println("after customer got " + customer);
			this.service.changeUserPassword(customer, password);
			return "redirect:/";
		}
	      modelMap.addAttribute("passwordsNotMatch", "Пароли не совпадают");
		return "resetPasswordFormPage";
	}
	
	@RequestMapping(value="/resetPasswordFormPage", method=RequestMethod.GET )
	public String getResetPasswordFormPage(Model model){
		model.addAttribute("customerDto", new CustomerDto());
		System.err.println(SecurityContextHolder.getContext().getAuthentication());
		return "resetPasswordFormPage";
	}
	
	//---------Non-API
	
	 private final SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final Customer user) {
	        final String url = contextPath + "/changePassword?id=" + user.getId() + "&token=" + token;
	        //final String message = messages.getMessage("message.resetPassword", null, locale);
	        final SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(user.getEmail());
	        email.setSubject("Reset Password");
	        email.setText("BLABLABLA" + " \r\n" + url);
	        email.setFrom("myemail@mail.ru");
	        return email;
	    }

}
