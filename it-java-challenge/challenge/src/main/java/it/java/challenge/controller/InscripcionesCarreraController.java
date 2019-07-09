package it.java.challenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import it.java.challenge.model.*;
import it.java.challenge.repository.*;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD Inscripciones a Carrera")
public class InscripcionesCarreraController {

	@Autowired
	private InscripcionesCarreraRepository inscripcionescarreraRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@GetMapping("/inscripcionescarreras")
	@ApiOperation(value = "Listado de inscripciones a carrera. Si se pasa un id de alumno retorna inscripciones actuales a carreras", response = List.class)
	public List<InscripcionesCarrera> getAllInscripcionesCarreras(
			@ApiParam(value = "Alumno id", required = false) @RequestParam(value = "id", required = false) Integer alumnoId,
			@ApiParam(value = "Página de resultados. Por defecto se retorna la primer página", required = false) @RequestParam(value = "page", required = false) Integer page) {
		if (alumnoId != null) {
			Optional<Alumno> optalumno = alumnoRepository.findById(alumnoId);
			if (optalumno.isPresent()) {
				Alumno alumno = optalumno.get();

				return inscripcionescarreraRepository.findByAlumno(alumno);
			}
		}
		// Se retornan páginas de 20 resultados
		Integer size = 20;
		if (page == null) {
			page = 0;
		} else {
			page--;
		}
		Pageable pageable = PageRequest.of(page, size);
		return inscripcionescarreraRepository.findAll(pageable).getContent();
	}

	@GetMapping("/inscripcionescarreras/{id}")
	@ApiOperation(value = "Retorna una inscripcion a carrera por id")
	public ResponseEntity<InscripcionesCarrera> getInscripcionesCarreraById(
			@ApiParam(value = "InscripcionesCarrera id", required = true) @PathVariable(value = "id") Integer inscripcionescarreraId)
			throws ResourceNotFoundException {
		InscripcionesCarrera inscripcionescarrera = inscripcionescarreraRepository.findById(inscripcionescarreraId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"InscripcionesCarrera no encontrada id :: " + inscripcionescarreraId));
		return ResponseEntity.ok().body(inscripcionescarrera);
	}

	@PostMapping("/inscripcionescarreras")
	@ApiOperation(value = "Agrega una inscripcion a carrera")
	public InscripcionesCarrera createInscripcionesCarrera(
			@ApiParam(value = "Object InscripcionesCarrera", required = true) @Valid @RequestBody InscripcionesCarrera inscripcionescarrera) {
		return inscripcionescarreraRepository.save(inscripcionescarrera);
	}

	@PutMapping("/inscripcionescarreras/{id}")
	@ApiOperation(value = "Actualiza una inscripcion a carrera")
	public ResponseEntity<InscripcionesCarrera> updateInscripcionesCarrera(
			@ApiParam(value = "InscripcionesCarrera id a actualizar", required = true) @PathVariable(value = "id") Integer inscripcionescarreraId,
			@ApiParam(value = "Object InscripcionesCarrera", required = true) @Valid @RequestBody InscripcionesCarrera inscripcionescarreraDetails)
			throws ResourceNotFoundException {
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
	@ApiOperation(value = "Elimina una inscripcion a carrera por id")
	public Map<String, Boolean> deleteInscripcionesCarrera(
			@ApiParam(value = "InscripcionesCarrera id a eliminar", required = true) @PathVariable(value = "id") Integer inscripcionescarreraId)
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
