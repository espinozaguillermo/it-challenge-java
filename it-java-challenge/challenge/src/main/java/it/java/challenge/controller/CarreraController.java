package it.java.challenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.java.challenge.model.Carrera;
import it.java.challenge.repository.CarreraRepository;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD Carrera")
public class CarreraController {

	@Autowired
	private CarreraRepository carreraRepository;

	@GetMapping("/carreras")
	@ApiOperation(value = "Listado de carreras", response = List.class)
	public List<Carrera> getAllCarreras(
			@ApiParam(value = "Página de resultados. Por defecto se retorna la primer página", required = false) @RequestParam(value = "page", required = false) Integer page) {
		// Se retornan páginas de 20 resultados
		Integer size = 20;
		if (page == null) {
			page = 0;
		} else {
			page--;
		}
		Pageable pageable = PageRequest.of(page, size);
		return carreraRepository.findAll(pageable).getContent();
	}

	@GetMapping("/carreras/{id}")
	@ApiOperation(value = "Retorna una carrera por id")
	public ResponseEntity<Carrera> getCarreraById(
			@ApiParam(value = "Carrera id", required = true) @PathVariable(value = "id") Integer carreraId)
			throws ResourceNotFoundException {
		Carrera carrera = carreraRepository.findById(carreraId)
				.orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada id :: " + carreraId));
		return ResponseEntity.ok().body(carrera);
	}

	@PostMapping("/carreras")
	@ApiOperation(value = "Agrega una carrera")
	public Carrera createCarrera(
			@ApiParam(value = "Object Carrera", required = true) @Valid @RequestBody Carrera carrera) {
		return carreraRepository.save(carrera);
	}

	@PutMapping("/carreras/{id}")
	@ApiOperation(value = "Actualiza una carrera")
	public ResponseEntity<Carrera> updateCarrera(
			@ApiParam(value = "Carrera id a actualizar", required = true) @PathVariable(value = "id") Integer carreraId,
			@ApiParam(value = "Object Carrera", required = true) @Valid @RequestBody Carrera carreraDetails)
			throws ResourceNotFoundException {
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
	@ApiOperation(value = "Elimina una carrera por id")
	public Map<String, Boolean> deleteCarrera(
			@ApiParam(value = "Carrera id a eliminar", required = true) @PathVariable(value = "id") Integer carreraId)
			throws ResourceNotFoundException {
		Carrera carrera = carreraRepository.findById(carreraId)
				.orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada id :: " + carreraId));

		carreraRepository.delete(carrera);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

}
