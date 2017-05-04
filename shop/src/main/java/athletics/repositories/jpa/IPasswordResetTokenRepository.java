package athletics.repositories.jpa;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import athletics.model.Customer;
import athletics.model.PasswordResetToken;

public interface IPasswordResetTokenRepository {

	    PasswordResetToken findByToken(String token);

	    PasswordResetToken findByUser(Customer user);

	    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

	    void deleteByExpiryDateLessThan(Date now);

	    @Modifying
	    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
	    void deleteAllExpiredSince(Date now);

		void save(PasswordResetToken myToken);
}
