package it.java.challenge.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida que el anio del curso sea mayor o igual al anio de la fechadesde
 * y que sea menor o igual al anio de la fechahasta de carrera
 * ej: anio=2018 fechadesde=2000-01-01 => true 
 */
@Documented
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE }) // METHOD, CONSTRUCTOR, etc.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnioCursoValidator.class)
public @interface AnioCurso {

	String message() default "{com.xxx.bean.validation.constraints.Date.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
