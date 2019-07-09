package it.java.challenge.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida el alumno no tenga una inscripcion a curso ya abierta 
 */
@Documented
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE }) // METHOD, CONSTRUCTOR, etc.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueInscripcionCursoValidator.class)
public @interface UniqueInscripcionCurso {

	String message() default "{com.xxx.bean.validation.constraints.Date.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}