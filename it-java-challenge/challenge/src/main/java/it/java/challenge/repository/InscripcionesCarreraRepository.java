package it.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.*;
import it.java.challenge.model.InscripcionesCarrera;
import java.util.List;

@Repository
public interface InscripcionesCarreraRepository extends JpaRepository<InscripcionesCarrera, Integer> {
	List<InscripcionesCarrera> findByCarreraAndAlumno(Carrera carreraId, Alumno alumnoId);
	List<InscripcionesCarrera> findByAlumno(Alumno alumnoId);
}