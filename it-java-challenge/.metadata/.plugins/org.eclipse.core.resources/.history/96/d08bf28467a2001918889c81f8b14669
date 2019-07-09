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

import it.java.challenge.model.Carrera;
import it.java.challenge.repository.CarreraRepository;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class CarreraController {

	@Autowired
	private CarreraRepository carreraRepository;

	@GetMapping("/carreras")
	public List<Carrera> getAllCarreras() {
		return carreraRepository.findAll();
	}

	@GetMapping("/carreras/{id}")
	public ResponseEntity<Carrera> getCarreraById(@PathVariable(value = "id") Integer carreraId)
			throws ResourceNotFoundException {
		Carrera carrera = carreraRepository.findById(carreraId)
				.orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada id :: " + carreraId));
		return ResponseEntity.ok().body(carrera);
	}

	@PostMapping("/carreras")
	public Carrera createCarrera(@Valid @RequestBody Carrera carrera) {
		return carreraRepository.save(carrera);
	}

	@PutMapping("/carreras/{id}")
	public ResponseEntity<Carrera> updateCarrera(@PathVariable(value = "id") Integer carreraId,
			@Valid @RequestBody Carrera carreraDetails) throws ResourceNotFoundException {
		Carrera carrera = carreraRepository.findById(carreraId)
				.orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada id :: " + carreraId));

		carrera.setNombre(carreraDetails.getNombre());
		carrera.setDescripcion(carreraDetails.getDescripcion());
		carrera.setFechadesde(carreraDetails.getFechadesde());
		carrera.setFechahasta(carreraDetails.getFechahasta());
		final Carrera updatedCarrera = carreraRepository.save(carrera);
		return ResponseEntity.ok(updatedCarrera);
	}

	@DeleteMapping("/carreras/{id}")
	public Map<String, Boolean> deleteCarrera(@PathVariable(value = "id") Integer carreraId)
			throws ResourceNotFoundException {
		Carrera carrera = carreraRepository.findById(carreraId)
				.orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada id :: " + carreraId));

		carreraRepository.delete(carrera);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

}
