package it.java.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Docente;

@Repository
public interface DocenteRepository extends PagingAndSortingRepository<Docente, Integer> {
	Page<Docente> findAll(Pageable pageable);
}
