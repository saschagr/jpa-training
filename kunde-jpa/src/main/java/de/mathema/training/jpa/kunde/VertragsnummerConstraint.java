package de.mathema.training.jpa.kunde;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VertragsnummerConstraint 
	implements ConstraintValidator<VertragsnummerValidation, String> {

	@Override
	public void initialize(VertragsnummerValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.contains("-");
	}

}

