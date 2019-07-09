package it.java.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Carrera;

@Repository
public interface CarreraRepository extends PagingAndSortingRepository<Carrera, Integer> {
	Page<Carrera> findAll(Pageable pageable);
}
