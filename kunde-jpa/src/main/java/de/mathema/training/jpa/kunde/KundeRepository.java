package de.mathema.training.jpa.kunde;

import jakarta.ejb.Remote;

@Remote
public interface KundeRepository {
	
	void anlegen(Kunde kunde);
	
	void aendern(Kunde geaenderterKunde);
	
	void aendern(Long id, Kunde kunde);

}
