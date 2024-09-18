package de.mathema.training.jpa.kunde;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class VertragsnummerPrefixConverter 
	implements AttributeConverter<Vertragsnummer, String> {
	
	private static final String PREFIX = "VERSICHERUNGSNUMMER-";

	@Override
	public String convertToDatabaseColumn(Vertragsnummer vertragsnummer) {
		return PREFIX + vertragsnummer.getNummer();
	}

	@Override
	public Vertragsnummer convertToEntityAttribute(String nummer) {
		return Vertragsnummer.builder()
				.nummer(nummer.substring(PREFIX.length())).build();
	}

}
