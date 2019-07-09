package it.java.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.*;

import java.util.List;

@Repository
public interface InscripcionesCarreraRepository extends PagingAndSortingRepository<InscripcionesCarrera, Integer> {
	Page<InscripcionesCarrera> findAll(Pageable pageable);
	List<InscripcionesCarrera> findByCarreraAndAlumno(Carrera carreraId, Alumno alumnoId);
	List<InscripcionesCarrera> findByAlumno(Alumno alumnoId);
}
