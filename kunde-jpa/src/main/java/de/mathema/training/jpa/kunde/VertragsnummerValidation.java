package de.mathema.training.jpa.kunde;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(
		validatedBy = { 
				VertragsnummerConstraint.class, 
				VertragsnummerVertragsnummerConstraint.class })
@NotNull
@Size(min = 4)
@ReportAsSingleViolation
public @interface VertragsnummerValidation {

	String message() default "{de.mathema.training.jpa.beanvalidation.vertragsnummer}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
