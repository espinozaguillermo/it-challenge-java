package it.java.challenge.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Carrera;
import it.java.challenge.model.Curso;

@Repository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Integer> {
	Page<Curso> findAll(Pageable pageable);
	List<Curso> findByCarrera(Carrera carreraId);
}
