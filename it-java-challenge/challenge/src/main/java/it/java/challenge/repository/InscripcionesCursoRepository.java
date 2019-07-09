package it.java.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Alumno;
import it.java.challenge.model.Curso;
import it.java.challenge.model.InscripcionesCurso;

@Repository
public interface InscripcionesCursoRepository extends JpaRepository<InscripcionesCurso, Integer> {
	List<InscripcionesCurso> findByCursoAndAlumno(Curso carreraId, Alumno alumnoId);
	List<InscripcionesCurso> findByCurso(Curso carreraId);
	List<InscripcionesCurso> findByAlumno(Alumno alumnoId);
	List<InscripcionesCurso> findByAlumnoAndCalificacion(Alumno alumnoId, Integer calificacion);
}