package it.java.challenge.validator;

import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.java.challenge.model.InscripcionesCarrera;
import it.java.challenge.repository.CarreraRepository;
import it.java.challenge.model.Carrera;

/**
 * Valida que la fecha de inscripcion a la carrera este entre la fechadesde
 * y la fechahasta de carrera
 * ej: fechainscripcion=2018-01-01 fechadesde=2000-01-01 => true 
 */
public class FechaInscripcionCarreraValidator implements ConstraintValidator<FechaInscripcionCarrera, InscripcionesCarrera> {

	@Autowired
	private CarreraRepository carreraRepository;
	
	@Override
	public void initialize(FechaInscripcionCarrera constraintAnnotation) {
	}

	@Override
	public boolean isValid(InscripcionesCarrera inscripcioncarrera, ConstraintValidatorContext context) {
		if (inscripcioncarrera == null) {
			return true;
		}
		
		try {
			Date fechainscripcion = inscripcioncarrera.getFechainscripcion();
			Carrera carrera;
			Optional<Carrera> optcarrera = carreraRepository.findById(inscripcioncarrera.getCarrera().getId());
			if (optcarrera.isPresent()) {
				carrera = optcarrera.get();
				if (carrera != null) {
					Date fechadesde = carrera.getFechadesde();
					Date fechahasta = null;
					if (carrera.getFechahasta() != null) {
						fechahasta = carrera.getFechahasta();	
					}
					if (fechadesde != null && fechahasta != null) {
						return fechainscripcion.compareTo(fechadesde) >= 0 && fechainscripcion.compareTo(fechahasta) <= 0;
					} else {
						return fechadesde != null && fechahasta == null && fechainscripcion.compareTo(fechadesde) >= 0;
					}
				}
			}
			return false;
		} catch (final Exception ignore) {
		}
		return true;
	}

}
