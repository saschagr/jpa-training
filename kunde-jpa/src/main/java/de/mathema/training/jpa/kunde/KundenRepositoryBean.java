package de.mathema.training.jpa.kunde;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Stateless
//EJB variante @TransactionAttribute(TransactionAttributeType.REQUIRED)
/* general available */
@Transactional(value = TxType.REQUIRED)
public class KundenRepositoryBean implements KundeRepository {
	
	@PersistenceContext(unitName = "kundeManagement")
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
	
	@Override
	public List<Kunde> findKundenByName(String name) {
		TypedQuery<Kunde> query = entityManager
				.createNamedQuery(Kunde.QUERY_FIND_KUNDEN_BY_NAME, Kunde.class);
		/*
		entityManager.createQuery(
				"select k from KundenEntity k where k.name = :name",
				Kunde.class);
		*/
		
		
		query.setParameter(Kunde.PARAMETER_NAME, name);
		
		return query.getResultList();
	}
}
