package it.java.challenge.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Alumno;
import it.java.challenge.model.Curso;
import it.java.challenge.model.InscripcionesCurso;

@Repository
public interface InscripcionesCursoRepository extends PagingAndSortingRepository<InscripcionesCurso, Integer> {
	Page<InscripcionesCurso> findAll(Pageable pageable);
	List<InscripcionesCurso> findByCursoAndAlumno(Curso carreraId, Alumno alumnoId);
	List<InscripcionesCurso> findByCurso(Curso carreraId);
	List<InscripcionesCurso> findByAlumno(Alumno alumnoId);
	List<InscripcionesCurso> findByAlumnoAndCalificacion(Alumno alumnoId, Integer calificacion);
}
