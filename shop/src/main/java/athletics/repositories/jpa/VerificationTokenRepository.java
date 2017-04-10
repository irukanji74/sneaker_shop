package athletics.repositories.jpa;

import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import athletics.model.Customer;
import athletics.model.VerificationToken;

@Repository
public class VerificationTokenRepository implements IVerificationTokenRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public VerificationToken findByToken(String token) {
		TypedQuery<VerificationToken> query = this.entityManager.createQuery("SELECT DISTINCT vt FROM VerificationToken vt WHERE vt.token LIKE :token"
                , VerificationToken.class);
		query.setParameter("token", token + "%");
		VerificationToken verificationToken = query.getSingleResult();
		return verificationToken;
	}

	@Override
	public VerificationToken findByUser(Customer customer) {
		return null;
	}

	@Override
	public Stream<VerificationToken> findAllByExpiryDateLessThan(Date now) {
		return null;
	}

	@Override
	public void deleteByExpiryDateLessThan(Date now) {
		
	}

	@Override
	@Transactional
	public void save(VerificationToken token) {
		this.entityManager.persist(token);
		
	}

	@Override
	@Transactional
	public void delete(VerificationToken verificationToken) {
		this.entityManager.remove(verificationToken);
		
		
	}

	
	
}
