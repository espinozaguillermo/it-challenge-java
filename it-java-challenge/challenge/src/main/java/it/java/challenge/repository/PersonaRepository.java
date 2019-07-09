package it.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

}