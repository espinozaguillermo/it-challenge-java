package it.java.challenge.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.model.InscripcionesCurso;
import it.java.challenge.repository.CursoRepository;
import it.java.challenge.model.Curso;

/**
 * Valida que el anio de la fecha de inscripcion al curso sea igual al anio del curso
 * ej: fechainscripcion=2018-01-01 anio=2018 => true 
 */
public class FechaInscripcionCursoValidator implements ConstraintValidator<FechaInscripcionCurso, InscripcionesCurso> {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public void initialize(FechaInscripcionCurso constraintAnnotation) {
	}

	@Override
	public boolean isValid(InscripcionesCurso inscripcioncurso, ConstraintValidatorContext context) {
		if (inscripcioncurso == null) {
			return true;
		}
		
		try {
			Date fechainscripcion = inscripcioncurso.getFechainscripcion();
			Curso curso;
			Optional<Curso> optcurso = cursoRepository.findById(inscripcioncurso.getCurso().getId());
			if (optcurso.isPresent()) {
				curso = optcurso.get();
				if (curso != null) {
					Integer anio = curso.getAnio();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fechainscripcion);
					System.out.println("fechainscripcion" + anio + "-" + calendar.get(Calendar.YEAR));
					return anio == calendar.get(Calendar.YEAR);
				}
			}
			return false;
		} catch (final Exception ignore) {
		}
		return true;
	}

}