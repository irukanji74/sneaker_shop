package athletics.repositories.jpa;

import java.util.Date;
import java.util.stream.Stream;

import athletics.model.Customer;
import athletics.model.VerificationToken;

public interface IVerificationTokenRepository {

	    VerificationToken findByToken(String token);

	    VerificationToken findByUser(Customer customer);

	    Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);

	    void deleteByExpiryDateLessThan(Date now);
	    
	    void save(VerificationToken token);
	    
	    void delete(VerificationToken verificationToken);

}
