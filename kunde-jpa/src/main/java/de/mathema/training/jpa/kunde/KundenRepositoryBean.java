package de.mathema.training.jpa.kunde;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
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
		//return findKundenByNameWithQuery(name);
		return findKundenByNameWithCriteriaApi(name);
		
	}

	
	private List<Kunde> findKundenByNameWithQuery(String name) {
		TypedQuery<Kunde> query = entityManager
				.createNamedQuery(Kunde.QUERY_FIND_KUNDEN_BY_NAME, Kunde.class);
		/*
		entityManager.createQuery(
				"select k from KundenEntity k where k.name = :name",
				Kunde.class);
		*/
		
		
		query.setParameter(Kunde.PARAMETER_NAME, name);
		query.setHint(
				"jakarta.persistence.loadgraph", 
				entityManager.getEntityGraph("KUNDE_ALL"));
		
		return query.getResultList();
	}
	
	private List<Kunde> findKundenByNameWithCriteriaApi(String name) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Kunde> criteriaQuery = criteriaBuilder.createQuery(Kunde.class);
		Root<Kunde> kunde = criteriaQuery.from(Kunde.class);
		Path<String> namePath = kunde.get(Kunde_.name);
		
		criteriaQuery.select(kunde).
	      where(criteriaBuilder.equal(namePath, name));
		
		TypedQuery<Kunde> query = entityManager.createQuery(criteriaQuery);
		
		query.setHint(
				"jakarta.persistence.loadgraph", 
				entityManager.getEntityGraph("KUNDE_ALL"));
		
		return query.getResultList();
	}

	public Kunde findKundeById(Long id) {
		return entityManager
		.find(
				Kunde.class, 
				id, 
				Map.of("jakarta.persistence.fetchgraph", 
						entityManager.getEntityGraph("KUNDE_ALL")));
	}
}
