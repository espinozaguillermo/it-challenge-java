package it.java.challenge.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;

/**
 * Valida entre dos fechas si la primera es menor que la segunda fecha
 * se utiliza para validar que fechadesde sea menor o igual que fechahasta de una Carrera
 * ej: fechadesde=2018-01-01 fechahasta=2018-02-02 => true 
 */
public class GreaterDateValidator implements ConstraintValidator<GreaterDate, Object> {

	private String firstDate;
	private String secondDate;

	@Override
	public void initialize(GreaterDate constraintAnnotation) {
		firstDate = constraintAnnotation.first();
		secondDate = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		try {
			Date firstObj = (Date) BeanUtils.getPropertyDescriptor(value.getClass(), firstDate).getReadMethod()
					.invoke(value);
			Date secondObj = (Date) BeanUtils.getPropertyDescriptor(value.getClass(), secondDate).getReadMethod()
					.invoke(value);
			return firstObj == null && secondObj == null
					|| firstObj != null && secondObj != null && firstObj.compareTo(secondObj) < 0;
		} catch (final Exception ignore) {
			// ignore
		}
		return true;
	}

}