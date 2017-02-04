package athletics.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

	@RequestMapping(value="/")
	public String mainPage(){
		return "shop_face";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String toRegister(){
		return "register";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLoginPage(){
		return "register";
	}
	
	@RequestMapping(value="/customer_account", method=RequestMethod.GET)
	public String toCustomerAccount(){
		return "customer_account";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String loginSuccess(){
		return "customer_account";
	}
}
