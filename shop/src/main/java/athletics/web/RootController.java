package athletics.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import athletics.service.CustomerService;

@Controller
public class RootController {

	private final CustomerService service;

	@Autowired
	public RootController(CustomerService service) {
		this.service = service;
	}

	@RequestMapping(value = "/")
	public String mainPage() {
		return "shop_face";
	}

	/*@RequestMapping(value = "/customer_account", method = RequestMethod.GET)
	public String toCustomerAccount() {
		return "customer_account";
	}*/

}
