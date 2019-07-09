package it.java.challenge.validator;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.model.*;
import it.java.challenge.repository.*;

/**
 * Valida el alumno tenga una inscripcioncarrera en la misma carrera del curso
 */
public class AlumnoInscripcionCursoValidator
		implements ConstraintValidator<AlumnoInscripcionCurso, InscripcionesCurso> {

	@Autowired
	private InscripcionesCarreraRepository inscripcionesCarreraRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void initialize(AlumnoInscripcionCurso constraintAnnotation) {
	}

	@Override
	public boolean isValid(InscripcionesCurso inscripcioncurso, ConstraintValidatorContext context) {
		if (inscripcioncurso == null) {
			return true;
		}

		try {

			Curso curso;
			Optional<Curso> optcurso = cursoRepository.findById(inscripcioncurso.getCurso().getId());
			if (optcurso.isPresent()) {
				curso = optcurso.get();
				if (curso != null) {
					List<InscripcionesCarrera> listinscripcioncarrera = inscripcionesCarreraRepository
							.findByCarreraAndAlumno(curso.getCarrera(), inscripcioncurso.getAlumno());
					return listinscripcioncarrera.size() > 0;
				}
			}
			return false;
		} catch (final Exception ignore) {
		}
		return true;
	}

}