package athletics.listeners;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import athletics.model.Customer;
import athletics.service.CustomerService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	 private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	 
    @Autowired
    private CustomerService service;

    /*@Autowired
    private MessageSource messages;*/

    /*@Autowired
    private JavaMailSenderImpl mailSender;*/

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final Customer customer = event.getCustomer();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(customer, token);

        final SimpleMailMessage email = constructEmailMessage(event, customer, token);
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost"); 
        //System.out.println(env.getProperty("smtp.host"));
        mailSender.setPort(2525); 
        mailSender.setProtocol("smtp"); 
        mailSender.setUsername("test"); 
        mailSender.setPassword("test");
        mailSender.send(email);
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event
    		                                              , final Customer customer
    		                                              , final String token) {
        final String recipientAddress = customer.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        //final String message = messages.getMessage("message.regSucc", null, event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText( " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
