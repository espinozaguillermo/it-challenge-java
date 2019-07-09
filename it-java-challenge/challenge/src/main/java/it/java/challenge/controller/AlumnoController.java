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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.java.challenge.model.*;
import it.java.challenge.repository.*;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD Alumno")
public class AlumnoController {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@GetMapping("/alumnos")
	@ApiOperation(value = "Listado de alumnos", response = List.class)
	public List<Alumno> getAllAlumnos() {
		return alumnoRepository.findAll();
	}

	@GetMapping("/alumnos/{id}")
	@ApiOperation(value = "Retorna un alumno por id")
	public ResponseEntity<Alumno> getAlumnoById(
			@ApiParam(value = "Alumno id", required = true) @PathVariable(value = "id") Integer alumnoId)
			throws ResourceNotFoundException {
		Alumno alumno = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrada id :: " + alumnoId));
		return ResponseEntity.ok().body(alumno);
	}

	@PostMapping("/alumnos")
	@ApiOperation(value = "Agrega una alumno")
	public Alumno createAlumno(@ApiParam(value = "Object Alumno", required = true) @Valid @RequestBody Alumno alumno) {
		return alumnoRepository.save(alumno);
	}

	@PutMapping("/alumnos/{id}")
	@ApiOperation(value = "Actualiza un alumno")
	public ResponseEntity<Alumno> updateAlumno(
			@ApiParam(value = "Alumno id a actualizar", required = true) @PathVariable(value = "id") Integer alumnoId,
			@ApiParam(value = "Object Alumno", required = true) @Valid @RequestBody Alumno alumnoDetails)
			throws ResourceNotFoundException {
		Alumno alumno = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrada id :: " + alumnoId));

		alumno.setPersona(alumnoDetails.getPersona());
		alumno.setLegajo(alumnoDetails.getLegajo());
		final Alumno updatedAlumno = alumnoRepository.save(alumno);
		return ResponseEntity.ok(updatedAlumno);
	}

	@DeleteMapping("/alumnos/{id}")
	@ApiOperation(value = "Elimina un alumno por id")
	public Map<String, Boolean> deleteAlumno(
			@ApiParam(value = "Alumno id a eliminar", required = true) @PathVariable(value = "id") Integer alumnoId)
			throws ResourceNotFoundException {
		Alumno alumno = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrada id :: " + alumnoId));

		alumnoRepository.delete(alumno);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

}