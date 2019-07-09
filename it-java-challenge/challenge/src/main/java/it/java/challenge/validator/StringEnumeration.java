package it.java.challenge.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida que un campo string este dentro de los valores de una enum
 * se utiliza para validar tipodoc de una Persona
 * ej: tipodoc=DNI enum={DNI, LC, LE} => true 
 */
@Documented
@Target(ElementType.FIELD) //METHOD, CONSTRUCTOR, etc.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringEnumerationValidator.class)
public @interface StringEnumeration {

	String message() default "{com.xxx.bean.validation.constraints.StringEnumeration.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends Enum<?>> enumClass();

}
