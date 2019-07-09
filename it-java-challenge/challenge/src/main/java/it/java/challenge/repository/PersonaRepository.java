package it.java.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Persona;

@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer> {
	Page<Persona> findAll(Pageable pageable);
}
