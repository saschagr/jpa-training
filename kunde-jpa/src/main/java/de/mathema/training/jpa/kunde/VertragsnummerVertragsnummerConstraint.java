package de.mathema.training.jpa.kunde;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VertragsnummerVertragsnummerConstraint
		implements ConstraintValidator<VertragsnummerValidation, Vertragsnummer> {

	@Override
	public void initialize(VertragsnummerValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(Vertragsnummer value, ConstraintValidatorContext context) {
		return value.getNummer().contains("-");
	}
}
