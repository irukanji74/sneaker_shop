package athletics.repositories.jpa;

import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import athletics.model.Customer;
import athletics.model.PasswordResetToken;
import athletics.model.VerificationToken;

@Repository
public class PasswordResetTokenRepository implements IPasswordResetTokenRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public PasswordResetToken findByToken(String token) {
		System.err.println("В методе findByToken " + token);
		TypedQuery<PasswordResetToken> query = 
				this.em.createQuery("SELECT DISTINCT prt FROM PasswordResetToken prt WHERE prt.token LIKE :token"
                                    , PasswordResetToken.class);
		query.setParameter("token", token + "%");
		PasswordResetToken passwordResetToken = query.getSingleResult();
		System.err.println("В методе findByToken " + passwordResetToken);
		return passwordResetToken;
	}

	@Override
	public PasswordResetToken findByUser(Customer user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByExpiryDateLessThan(Date now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllExpiredSince(Date now) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void save(PasswordResetToken myToken) {
		this.em.persist(myToken);
	}

}
