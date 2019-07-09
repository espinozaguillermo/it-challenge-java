package it.java.challenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.java.challenge.model.Docente;
import it.java.challenge.repository.DocenteRepository;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class DocenteController {

	@Autowired
	private DocenteRepository docenteRepository;

	@GetMapping("/docentes")
	public List<Docente> getAllDocentes() {
		return docenteRepository.findAll();
	}

	@GetMapping("/docentes/{id}")
	public ResponseEntity<Docente> getDocenteById(@PathVariable(value = "id") Integer docenteId)
			throws ResourceNotFoundException {
		Docente docente = docenteRepository.findById(docenteId)
				.orElseThrow(() -> new ResourceNotFoundException("Docente no encontrada id :: " + docenteId));
		return ResponseEntity.ok().body(docente);
	}

	@PostMapping("/docentes")
	public Docente createDocente(@Valid @RequestBody Docente docente) {
		return docenteRepository.save(docente);
	}

	@PutMapping("/docentes/{id}")
	public ResponseEntity<Docente> updateDocente(@PathVariable(value = "id") Integer docenteId,
			@Valid @RequestBody Docente docenteDetails) throws ResourceNotFoundException {
		Docente docente = docenteRepository.findById(docenteId)
				.orElseThrow(() -> new ResourceNotFoundException("Docente no encontrada id :: " + docenteId));

		docente.setPersona(docenteDetails.getPersona());
		docente.setLegajo(docenteDetails.getLegajo());
		final Docente updatedDocente = docenteRepository.save(docente);
		return ResponseEntity.ok(updatedDocente);
	}

	@DeleteMapping("/docentes/{id}")
	public Map<String, Boolean> deleteDocente(@PathVariable(value = "id") Integer docenteId)
			throws ResourceNotFoundException {
		Docente docente = docenteRepository.findById(docenteId)
				.orElseThrow(() -> new ResourceNotFoundException("Docente no encontrada id :: " + docenteId));

		docenteRepository.delete(docente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

}
