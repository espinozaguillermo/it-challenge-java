package it.java.challenge.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.model.Carrera;
import it.java.challenge.model.Curso;
import it.java.challenge.repository.CarreraRepository;

/**
 * Valida que el anio del curso sea mayor o igual al anio de la fechadesde
 * y que sea menor o igual al anio de la fechahasta de carrera
 * ej: anio=2018 fechadesde=2000-01-01 => true 
 */
public class AnioCursoValidator implements ConstraintValidator<AnioCurso, Curso> {

	@Autowired
	private CarreraRepository carreraRepository;

	@Override
	public void initialize(AnioCurso constraintAnnotation) {
	}

	@Override
	public boolean isValid(Curso curso, ConstraintValidatorContext context) {
		if (curso == null ) {
            return true;
        }
		
		try {
			Integer anio = curso.getAnio();
			Carrera carrera;
			Optional<Carrera> optcarrera = carreraRepository.findById(curso.getCarrera().getId());
			if (optcarrera.isPresent()) {
				carrera = optcarrera.get();
				if (carrera != null) {
					Date fechadesde = carrera.getFechadesde();
					Calendar calendardesde = Calendar.getInstance();
					calendardesde.setTime(fechadesde);
					
					Date fechahasta = null;
					Calendar calendarhasta = null;
					if (carrera.getFechahasta() != null) {
						fechahasta = carrera.getFechahasta();
						calendarhasta = Calendar.getInstance();
						calendarhasta.setTime(fechahasta);
					}
					
					if (fechadesde != null && fechahasta != null) {
						return anio >= calendardesde.get(Calendar.YEAR) && anio <= calendarhasta.get(Calendar.YEAR);
					} else {
						return fechadesde != null && fechahasta == null && anio >= calendardesde.get(Calendar.YEAR);
					}
				}
			}
			return false;
		} catch (final Exception ignore) {
		}
		return true;
	}

}
