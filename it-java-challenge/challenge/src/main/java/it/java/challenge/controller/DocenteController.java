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
import it.java.challenge.model.Docente;
import it.java.challenge.repository.DocenteRepository;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD Docente")
public class DocenteController {

	@Autowired
	private DocenteRepository docenteRepository;

	@GetMapping("/docentes")
	@ApiOperation(value = "Listado de docentes", response = List.class)
	public List<Docente> getAllDocentes(
			@ApiParam(value = "Página de resultados. Por defecto se retorna la primer página", required = false) @RequestParam(value = "page", required = false) Integer page) {
		// Se retornan páginas de 20 resultados
		Integer size = 20;
		if (page == null) {
			page = 0;
		} else {
			page--;
		}
		Pageable pageable = PageRequest.of(page, size);
		return docenteRepository.findAll(pageable).getContent();
	}

	@GetMapping("/docentes/{id}")
	@ApiOperation(value = "Retorna un docente por id")
	public ResponseEntity<Docente> getDocenteById(
			@ApiParam(value = "Docente id", required = true) @PathVariable(value = "id") Integer docenteId)
			throws ResourceNotFoundException {
		Docente docente = docenteRepository.findById(docenteId)
				.orElseThrow(() -> new ResourceNotFoundException("Docente no encontrada id :: " + docenteId));
		return ResponseEntity.ok().body(docente);
	}

	@PostMapping("/docentes")
	@ApiOperation(value = "Agrega un docente")
	public Docente createDocente(
			@ApiParam(value = "Object Docente", required = true) @Valid @RequestBody Docente docente) {
		return docenteRepository.save(docente);
	}

	@PutMapping("/docentes/{id}")
	@ApiOperation(value = "Actualiza un docente")
	public ResponseEntity<Docente> updateDocente(
			@ApiParam(value = "Docente id a actualizar", required = true) @PathVariable(value = "id") Integer docenteId,
			@ApiParam(value = "Object Docente", required = true) @Valid @RequestBody Docente docenteDetails)
			throws ResourceNotFoundException {
		Docente docente = docenteRepository.findById(docenteId)
				.orElseThrow(() -> new ResourceNotFoundException("Docente no encontrada id :: " + docenteId));

		docente.setPersona(docenteDetails.getPersona());
		docente.setLegajo(docenteDetails.getLegajo());
		final Docente updatedDocente = docenteRepository.save(docente);
		return ResponseEntity.ok(updatedDocente);
	}

	@DeleteMapping("/docentes/{id}")
	@ApiOperation(value = "Elimina un docente por id")
	public Map<String, Boolean> deleteDocente(
			@ApiParam(value = "Docente id a eliminar", required = true) @PathVariable(value = "id") Integer docenteId)
			throws ResourceNotFoundException {
		Docente docente = docenteRepository.findById(docenteId)
				.orElseThrow(() -> new ResourceNotFoundException("Docente no encontrada id :: " + docenteId));

		docenteRepository.delete(docente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

}
