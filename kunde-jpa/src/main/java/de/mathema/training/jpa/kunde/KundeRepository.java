package de.mathema.training.jpa.kunde;

import java.util.List;

import jakarta.ejb.Remote;

@Remote
public interface KundeRepository {
	
	void anlegen(Kunde kunde);
	
	void aendern(Kunde geaenderterKunde);
	
	void aendern(Long id, Kunde kunde);
	
	List<Kunde> findKundenByName(String name);

}
