package it.java.challenge.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.model.InscripcionesCurso;
import it.java.challenge.repository.InscripcionesCursoRepository;

/**
 * Valida el alumno no tenga una inscripcion a curso ya abierta 
 */
public class UniqueInscripcionCursoValidator implements ConstraintValidator<UniqueInscripcionCurso, InscripcionesCurso> {

	@Autowired
	private InscripcionesCursoRepository inscripcionesCursoRepository;
	
	@Override
	public void initialize(UniqueInscripcionCurso constraintAnnotation) {
	}

	@Override
	public boolean isValid(InscripcionesCurso inscripcioncurso, ConstraintValidatorContext context) {
		if (inscripcioncurso == null) {
			return true;
		}
		
		try {
			List<InscripcionesCurso> listinscripcioncurso = inscripcionesCursoRepository.findByCursoAndAlumno(inscripcioncurso.getCurso(), inscripcioncurso.getAlumno());
			
			return listinscripcioncurso.size() == 0 || (listinscripcioncurso.size() == 1 && listinscripcioncurso.get(0).getId() == inscripcioncurso.getId());
		} catch (final Exception ignore) {
		}
		return true;
	}

}