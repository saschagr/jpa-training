package de.mathema.training.jpa.kunde;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Dependent
@Transactional(value = TxType.MANDATORY)
public class KundeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Kunde> alleKunden() {
		return entityManager
		.createQuery("select k from Kunde k", Kunde.class)
		.getResultList();
	}
	
	public void delete(Long id) {
		entityManager.remove(find(id));
	}

	public Kunde find(long id) {
		return entityManager.find(Kunde.class, id);
	}

}
