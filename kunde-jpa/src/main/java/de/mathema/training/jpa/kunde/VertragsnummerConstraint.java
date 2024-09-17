package de.mathema.training.jpa.kunde;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VertragsnummerConstraint 
	implements ConstraintValidator<Vertragsnummer, String> {

	@Override
	public void initialize(Vertragsnummer constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.contains("-");
	}

}
