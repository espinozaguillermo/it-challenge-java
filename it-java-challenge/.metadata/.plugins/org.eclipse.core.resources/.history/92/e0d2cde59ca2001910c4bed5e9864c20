package it.java.challenge.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.java.challenge.model.*;
import it.java.challenge.repository.*;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD Inscripciones a Curso")
public class InscripcionesCursoController {

	@Autowired
	private InscripcionesCursoRepository inscripcionescursoRepository;

	@Autowired
	private InscripcionesCarreraRepository inscripcionescarreraRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private CarreraRepository carreraRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping("/inscripcionescursos")
	@ApiOperation(value = "Listado de inscripciones a curso", response = List.class)
	public List<InscripcionesCurso> getAllInscripcionesCursos() {
		return inscripcionescursoRepository.findAll();
	}

	@GetMapping("/inscripcionescursos/{id}")
	@ApiOperation(value = "Retorna una inscripcion a curso por id")
	public ResponseEntity<InscripcionesCurso> getInscripcionesCursoById(
			@ApiParam(value = "InscripcionesCurso id", required = true) @PathVariable(value = "id") Integer inscripcionescursoId)
			throws ResourceNotFoundException {
		InscripcionesCurso inscripcionescurso = inscripcionescursoRepository.findById(inscripcionescursoId).orElseThrow(
				() -> new ResourceNotFoundException("InscripcionesCurso no encontrado id :: " + inscripcionescursoId));
		return ResponseEntity.ok().body(inscripcionescurso);
	}

	@PostMapping("/inscripcionescursos")
	@ApiOperation(value = "Agrega una inscripcion a curso")
	public InscripcionesCurso createInscripcionesCurso(
			@ApiParam(value = "Object InscripcionesCurso", required = true) @Valid @RequestBody InscripcionesCurso inscripcionescurso) {
		return inscripcionescursoRepository.save(inscripcionescurso);
	}

	@PutMapping("/inscripcionescursos/{id}")
	@ApiOperation(value = "Actualiza una inscripcion a curso")
	public ResponseEntity<InscripcionesCurso> updateInscripcionesCurso(
			@ApiParam(value = "InscripcionesCurso id a actualizar", required = true) @PathVariable(value = "id") Integer inscripcionescursoId,
			@ApiParam(value = "Object InscripcionesCurso", required = true) @Valid @RequestBody InscripcionesCurso inscripcionescursoDetails)
			throws ResourceNotFoundException {
		InscripcionesCurso inscripcionescurso = inscripcionescursoRepository.findById(inscripcionescursoId).orElseThrow(
				() -> new ResourceNotFoundException("InscripcionesCurso no encontrado id :: " + inscripcionescursoId));

		inscripcionescurso.setAlumno(inscripcionescursoDetails.getAlumno());
		inscripcionescurso.setCurso(inscripcionescursoDetails.getCurso());
		inscripcionescurso.setFechainscripcion(inscripcionescursoDetails.getFechainscripcion());
		inscripcionescurso.setCalificacion(inscripcionescursoDetails.getCalificacion());
		final InscripcionesCurso updatedInscripcionesCurso = inscripcionescursoRepository.save(inscripcionescurso);
		return ResponseEntity.ok(updatedInscripcionesCurso);
	}

	@DeleteMapping("/inscripcionescursos/{id}")
	@ApiOperation(value = "Elimina una inscripcion a curso por id")
	public Map<String, Boolean> deleteInscripcionesCurso(
			@ApiParam(value = "InscripcionesCurso id a eliminar", required = true) @PathVariable(value = "id") Integer inscripcionescursoId)
			throws ResourceNotFoundException {
		InscripcionesCurso inscripcionescurso = inscripcionescursoRepository.findById(inscripcionescursoId).orElseThrow(
				() -> new ResourceNotFoundException("InscripcionesCurso no encontrado id :: " + inscripcionescursoId));

		inscripcionescursoRepository.delete(inscripcionescurso);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

	@GetMapping("/inscripcionescursosactuales/{id}")
	@ApiOperation(value = "Retorna las inscripciones a curso actuales por alumno id")
	public List<InscripcionesCurso> getAllInscripcionesCursosActualesByAlumno(
			@ApiParam(value = "Alumno id a detallar", required = true) @PathVariable(value = "id") Integer alumnoId) {
		if (alumnoId != null) {
			Optional<Alumno> optalumno = alumnoRepository.findById(alumnoId);
			if (optalumno.isPresent()) {
				Alumno alumno = optalumno.get();

				return inscripcionescursoRepository.findByAlumnoAndCalificacion(alumno, null);
			}
		}
		return inscripcionescursoRepository.findAll();
	}

	@GetMapping("/promediogeneral/{id}")
	@ApiOperation(value = "Retorna el promedio general por carrera por alumno id, tomando los cursos ya aprobados")
	public List<Object> getPromedioGralInscripcionesCursosByAlumno(
			@ApiParam(value = "Alumno id a detallar", required = true) @PathVariable(value = "id") Integer alumnoId)
			throws ResourceNotFoundException {
		List<Object> list = new ArrayList<Object>();
		if (alumnoId != null) {
			Alumno alumno = alumnoRepository.findById(alumnoId)
					.orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado id :: " + alumnoId));
			List<InscripcionesCarrera> listinscripcionescarrera = inscripcionescarreraRepository.findByAlumno(alumno);
			for (InscripcionesCarrera item : listinscripcionescarrera) {
				List<Curso> listcurso = cursoRepository.findByCarrera(item.getCarrera());
				Carrera carrera = carreraRepository.findById(item.getCarrera().getId())
						.orElseThrow(() -> new ResourceNotFoundException(
								"Carrera no encontrada id :: " + item.getCarrera().getId()));
				List<Object> tmplist = new ArrayList<Object>();
				tmplist.add(carrera.getNombre());
				Integer tmpSum = 0;
				Integer tmpCursosAprobados = 0;
				for (Curso curso : listcurso) {
					List<InscripcionesCurso> listinscripcionescurso = inscripcionescursoRepository
							.findByCursoAndAlumno(curso, alumno);
					for (InscripcionesCurso inscripcioncurso : listinscripcionescurso) {
						if (inscripcioncurso.getCalificacion() != null
								&& inscripcioncurso.getCalificacion() >= curso.getCalificacionaprobada()) {
							tmpSum += inscripcioncurso.getCalificacion();
							tmpCursosAprobados++;
						}
					}
				}
				if (tmpSum != 0 && tmpCursosAprobados != 0) {
					tmplist.add(tmpSum / tmpCursosAprobados);
				}
				list.add(tmplist);
			}
		} else {
			list.addAll(inscripcionescursoRepository.findAll());
		}
		return list;
	}

	@GetMapping("/estadoinscripcionescursos/{id}")
	@ApiOperation(value = "Retorna un listado con el estado de inscripciones a cursos por alumno id y el estado de cursos anteriores")
	public List<Object> getEstadoInscripcionesCursosByAlumno(
			@ApiParam(value = "Alumno id a detallar", required = true) @PathVariable(value = "id") Integer alumnoId) {
		List<Object> list = new ArrayList<Object>();
		if (alumnoId != null) {
			Optional<Alumno> optalumno = alumnoRepository.findById(alumnoId);
			if (optalumno.isPresent()) {
				Alumno alumno = optalumno.get();
				list.add(alumno);
				List<InscripcionesCurso> listinscripcionescurso = inscripcionescursoRepository.findByAlumno(alumno);
				for (InscripcionesCurso item : listinscripcionescurso) {
					List<Object> tmplist = new ArrayList<Object>();
					tmplist.add(item.getCurso().getNombre());
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					tmplist.add(formatter.format(item.getFechainscripcion()));
					if (item.getCalificacion() != null) {
						tmplist.add(item.getCalificacion());
						if (item.getCalificacion() >= item.getCurso().getCalificacionaprobada()) {
							tmplist.add(item.getCalificacion());
							tmplist.add("APROBADO");
						} else {
							tmplist.add(item.getCalificacion());
							tmplist.add("NO APROBADO");
						}
					} else {
						tmplist.add("NO TERMINADO");
					}
					list.add(tmplist);
				}
			}
		} else {
			list.addAll(inscripcionescursoRepository.findAll());
		}
		return list;
	}

}
