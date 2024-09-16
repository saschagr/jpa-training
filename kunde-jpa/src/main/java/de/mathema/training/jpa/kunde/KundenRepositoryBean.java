package de.mathema.training.jpa.kunde;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Stateless
//EJB variante @TransactionAttribute(TransactionAttributeType.REQUIRED)
/* general available */
@Transactional(value = TxType.REQUIRED)
public class KundenRepositoryBean implements KundeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void anlegen(Kunde kunde) {
		entityManager.persist(kunde);
	}

	@Override
	public void aendern(Long id, Kunde kunde) {
		Kunde kundeGeladen = entityManager.find(Kunde.class, id);
		kundeGeladen.setName(kunde.getName());
	}

	@Override
	public void aendern(Kunde geaenderterKunde) {
		entityManager.merge(geaenderterKunde);
		
	}

	/*
	public void aendern(Kunde geaenderterKunde) {
		Kunde kundeGeladen =
				entityManager.find(Kunde.class, geaenderterKunde.getId());
		kundeGeladen.setName(geaenderterKunde.getName());
		
	}
	*/
}
