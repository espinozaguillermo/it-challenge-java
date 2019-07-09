package it.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.java.challenge.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer>{

}
