package it.java.challenge.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.exception.ResourceNotFoundException;
import it.java.challenge.model.*;
import it.java.challenge.repository.*;

/**
 * Valida el cupomaximo de inscripciones de un curso 
 */
public class CupomaximoInscripcionCursoValidator implements ConstraintValidator<CupomaximoInscripcionCurso, InscripcionesCurso> {

	@Autowired
	private InscripcionesCursoRepository inscripcionesCursoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public void initialize(CupomaximoInscripcionCurso constraintAnnotation) {
	}

	@Override
	public boolean isValid(InscripcionesCurso inscripcioncurso, ConstraintValidatorContext context) {
		if (inscripcioncurso == null) {
			return true;
		}
		
		try {
			Curso curso = cursoRepository.findById(inscripcioncurso.getCurso().getId()).orElseThrow(
					() -> new ResourceNotFoundException("Curso no encontrado id :: " + inscripcioncurso.getCurso().getId()));
			List<InscripcionesCurso> listinscripcioncurso = inscripcionesCursoRepository.findByCursoAndAlumno(curso, inscripcioncurso.getAlumno());
			return listinscripcioncurso.size() < curso.getCupomaximo();
		} catch (final Exception ignore) {
		}
		return true;
	}

}