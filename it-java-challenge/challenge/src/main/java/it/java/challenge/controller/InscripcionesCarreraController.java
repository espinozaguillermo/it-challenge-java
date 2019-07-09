package it.java.challenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.java.challenge.model.*;
import it.java.challenge.repository.*;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class InscripcionesCarreraController {

	@Autowired
	private InscripcionesCarreraRepository inscripcionescarreraRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@GetMapping("/inscripcionescarreras")
	public List<InscripcionesCarrera> getAllInscripcionesCarreras(@RequestParam Integer alumnoId) {
		if (alumnoId != null) {
			Optional<Alumno> optalumno = alumnoRepository.findById(alumnoId);
			if (optalumno.isPresent()) {
				Alumno alumno = optalumno.get();
				
				return inscripcionescarreraRepository.findByAlumno(alumno);
			}
		}
		return inscripcionescarreraRepository.findAll();
	}

	@GetMapping("/inscripcionescarreras/{id}")
	public ResponseEntity<InscripcionesCarrera> getInscripcionesCarreraById(
			@PathVariable(value = "id") Integer inscripcionescarreraId) throws ResourceNotFoundException {
		InscripcionesCarrera inscripcionescarrera = inscripcionescarreraRepository.findById(inscripcionescarreraId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"InscripcionesCarrera no encontrada id :: " + inscripcionescarreraId));
		return ResponseEntity.ok().body(inscripcionescarrera);
	}

	@PostMapping("/inscripcionescarreras")
	public InscripcionesCarrera createInscripcionesCarrera(
			@Valid @RequestBody InscripcionesCarrera inscripcionescarrera) {
		return inscripcionescarreraRepository.save(inscripcionescarrera);
	}

	@PutMapping("/inscripcionescarreras/{id}")
	public ResponseEntity<InscripcionesCarrera> updateInscripcionesCarrera(
			@PathVariable(value = "id") Integer inscripcionescarreraId,
			@Valid @RequestBody InscripcionesCarrera inscripcionescarreraDetails) throws ResourceNotFoundException {
		InscripcionesCarrera inscripcionescarrera = inscripcionescarreraRepository.findById(inscripcionescarreraId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"InscripcionesCarrera no encontrada id :: " + inscripcionescarreraId));

		inscripcionescarrera.setAlumno(inscripcionescarreraDetails.getAlumno());
		inscripcionescarrera.setCarrera(inscripcionescarreraDetails.getCarrera());
		inscripcionescarrera.setFechainscripcion(inscripcionescarreraDetails.getFechainscripcion());
		final InscripcionesCarrera updatedInscripcionesCarrera = inscripcionescarreraRepository
				.save(inscripcionescarrera);
		return ResponseEntity.ok(updatedInscripcionesCarrera);
	}

	@DeleteMapping("/inscripcionescarreras/{id}")
	public Map<String, Boolean> deleteInscripcionesCarrera(@PathVariable(value = "id") Integer inscripcionescarreraId)
			throws ResourceNotFoundException {
		InscripcionesCarrera inscripcionescarrera = inscripcionescarreraRepository.findById(inscripcionescarreraId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"InscripcionesCarrera no encontrada id :: " + inscripcionescarreraId));

		inscripcionescarreraRepository.delete(inscripcionescarrera);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

}
