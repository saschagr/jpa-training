package de.mathema.training.jpa.kunde;

import java.sql.SQLException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.database.rider.cdi.api.DBUnitInterceptor;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.CompareOperation;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.util.EntityManagerProvider;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@DBUnitInterceptor
@ExtendWith(DBUnitExtension.class)
@ExtendWith(WeldJunit5Extension.class)
class KundeRepositoryTest {

	@Inject
	private KundeRepository kundeRepository;
	
	
	private EntityManager entityManager = Persistence.createEntityManagerFactory("kunde").createEntityManager();

	//private ConnectionHolder connectionHolder = () -> EntityManagerProvider.instance("kunde").connection();

	@WeldSetup
	WeldInitiator weldInitiator = 
		WeldInitiator
		.from(WeldInitiator.createWeld().enableDiscovery())
		.setPersistenceContextFactory(
				ip -> entityManager)
		.build();

	@Test
	@DataSet(value = "kunde.yml", cleanBefore = true, transactional = true)
	@ExpectedDataSet(value = "expectedKunde.yml", compareOperation = CompareOperation.CONTAINS)
	void alleKunden() {
		List<Kunde> kunden = kundeRepository.alleKunden();

		Assertions.assertThat(kunden).hasSize(2);
		
		Assertions
		.assertThat(kunden)
		.contains(
				new Kunde(-1L, "Sascha"),
				new Kunde(-2L, "Max"));
	}
	
	@Test
	@DataSet(value = "kunde.yml", cleanBefore = true, transactional = true)
	@ExpectedDataSet(value = "expectedKundeDelete.yml", compareOperation = CompareOperation.EQUALS)
	void delete() {
		entityManager.getTransaction().begin();
		kundeRepository.delete(-1L);
		entityManager.getTransaction().commit();
	}

	@Test
	@DataSet(value = "kunde.yml", cleanBefore = true, transactional = true)
	void find() {
		Kunde kunde = kundeRepository.find(-1L);
		
		Assertions
		.assertThat(kunde)
		.hasFieldOrPropertyWithValue("id", -1L)
		.hasFieldOrPropertyWithValue("name", "Sascha");

		kunde = kundeRepository.find(-2L);
		
		Assertions
		.assertThat(kunde)
		.hasFieldOrPropertyWithValue("id", -2L)
		.hasFieldOrPropertyWithValue("name", "Max");
	}

}
