package athletics.listeners;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import athletics.model.Customer;

public class OnRegistrationCompleteEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private final String appUrl;
	private final Locale locale;
    private final Customer customer;
    
    public OnRegistrationCompleteEvent(final Customer customer, final String appUrl, final Locale locale ) {
    	super(customer);
    	this.appUrl = appUrl;
    	this.locale = locale;
    	this.customer = customer;
    }
    public String getAppUrl() {
    	return appUrl;
    }
    
    public Locale getLocale() {
    	return locale;
    }
    
    public Customer getCustomer() {
    	return customer;
    }
}
