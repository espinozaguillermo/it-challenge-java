package it.java.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Carrera;
import it.java.challenge.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	List<Curso> findByCarrera(Carrera carreraId);
}
