package it.java.challenge.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.model.InscripcionesCarrera;
import it.java.challenge.repository.InscripcionesCarreraRepository;

/**
 * Valida el alumno no tenga una inscripcion a carrera ya abierta 
 */
public class UniqueInscripcionCarreraValidator implements ConstraintValidator<UniqueInscripcionCarrera, InscripcionesCarrera> {

	@Autowired
	private InscripcionesCarreraRepository inscripcionesCarreraRepository;
	
	@Override
	public void initialize(UniqueInscripcionCarrera constraintAnnotation) {
	}

	@Override
	public boolean isValid(InscripcionesCarrera inscripcioncarrera, ConstraintValidatorContext context) {
		if (inscripcioncarrera == null) {
			return true;
		}
		
		try {
			List<InscripcionesCarrera> listinscripcioncarrera = inscripcionesCarreraRepository.findByCarreraAndAlumno(inscripcioncarrera.getCarrera(), inscripcioncarrera.getAlumno());
			
			return listinscripcioncarrera.size() == 0 || (listinscripcioncarrera.size() == 1 && listinscripcioncarrera.get(0).getId() == inscripcioncarrera.getId());
		} catch (final Exception ignore) {
		}
		return true;
	}

}
