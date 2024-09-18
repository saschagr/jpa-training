package de.mathema.training.jpa.kunde;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VertragsnummerConverter 
	implements AttributeConverter<Vertragsnummer, String> {

	@Override
	public String convertToDatabaseColumn(Vertragsnummer vertragsnummer) {
		return vertragsnummer.getNummer();
	}

	@Override
	public Vertragsnummer convertToEntityAttribute(String nummer) {
		return Vertragsnummer.builder().nummer(nummer).build();
	}

}
