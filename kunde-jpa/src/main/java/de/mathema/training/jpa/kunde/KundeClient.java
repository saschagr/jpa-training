/**********************************************************
* Begleitmaterial zum Buch "Enterprise JavaBeans 3.1"
* Das EJB-Praxisbuch fuer Ein- und Umsteiger
* Von Werner Eberling und Jan Lessner
* Hanser Fachbuchverlag Muenchen, 2011
* http://www.hanser.de/buch.asp?isbn=3-446-42259-5
* Feedback an ejb3buch@werner-eberling.de
**********************************************************/
package de.mathema.training.jpa.kunde;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.naming.InitialContext;

import com.github.javafaker.Faker;

public class KundeClient {
	
	private static Faker faker = new Faker(Locale.GERMANY);

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();

		KundeRepository kundeRepository = (KundeRepository) context
				.lookup("kunde-jpa/KundenRepositoryBean!de.mathema.training.jpa.kunde.KundeRepository");

		anlegen(kundeRepository, "Sascha");
		anlegen(kundeRepository, "Fritz");
		anlegen(kundeRepository, "Max");
		anlegen(kundeRepository, "Heike");
		anlegen(kundeRepository, "Heiko");
		anlegen(kundeRepository, "Maxi");
		
		aendern(kundeRepository, 1L, faker.name().firstName());

		context.close();
	}
	
	private static void aendern(KundeRepository kundeRepository, Long id, String name) {
		Kunde kunde = new Kunde();
		kunde.setName(name);
		
		kundeRepository.aendern(id, kunde);
	}
	
	private static void anlegen(KundeRepository kundeRepository, String name) {
		List<Telefon> telefons = new ArrayList<>();
		telefons
			.add(
					Telefon
					.builder()
					.nummer(
							faker.phoneNumber().phoneNumber()
							).build());
		telefons
			.add(
					Telefon
					.builder()
					.nummer(
							faker.phoneNumber().phoneNumber()
							).build());
		
		Kunde kunde = 
				Kunde
				.builder()
				.name(name)
				.anrede(faker.options().option(Anrede.class))
				.geburtsdatum(faker.date().birthday())
				.telefons(telefons)
				.build();
		
		addVertraege(kunde);

		kundeRepository.anlegen(kunde);
	}
	
	private static void addVertraege(Kunde kunde) {
		int anzahl = faker.random().nextInt(10);
		while (anzahl > -1 ) {
			anzahl--;
			Vertrag vertrag =
					Vertrag
					.builder()
					.vertragsnummer(faker.regexify("[0-9]{4}-[0-9]{9}"))
					.build();
					
			kunde.addVertrag(vertrag);
			vertrag.setKunde(kunde);
		}
		
	}
	
	
}
