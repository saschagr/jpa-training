/**********************************************************
* Begleitmaterial zum Buch "Enterprise JavaBeans 3.1"
* Das EJB-Praxisbuch fuer Ein- und Umsteiger
* Von Werner Eberling und Jan Lessner
* Hanser Fachbuchverlag Muenchen, 2011
* http://www.hanser.de/buch.asp?isbn=3-446-42259-5
* Feedback an ejb3buch@werner-eberling.de
**********************************************************/
package de.mathema.training.jpa.kunde;

import javax.naming.InitialContext;

public class KundeClient {

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
		
		Kunde kunde = new Kunde();
		kunde.setName("Fritzi");
		
		kundeRepository.aendern(1L, kunde);

		context.close();
	}
	
	private static void anlegen(KundeRepository kundeRepository, String name) {
		Kunde kunde = new Kunde();
		kunde.setName(name);

		kundeRepository.anlegen(kunde);
	}
	
	
}
