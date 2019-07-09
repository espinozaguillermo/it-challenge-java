package it.java.challenge.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida que la fecha de inscripcion a la carrera este entre la fechadesde
 * y la fechahasta de carrera
 * ej: fechainscripcion=2018-01-01 fechadesde=2000-01-01 => true 
 */
@Documented
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE }) // METHOD, CONSTRUCTOR, etc.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FechaInscripcionCarreraValidator.class)
public @interface FechaInscripcionCarrera {

	String message() default "{com.xxx.bean.validation.constraints.Date.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}