package it.java.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Alumno;

@Repository
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Integer>{
	Page<Alumno> findAll(Pageable pageable);
}
