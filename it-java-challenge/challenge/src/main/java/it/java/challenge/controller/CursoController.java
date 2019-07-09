package it.java.challenge.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@Api(value = "CRUD Curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private InscripcionesCursoRepository inscripcionesCursoRepository;

	@GetMapping("/cursos")
	@ApiOperation(value = "Listado de cursos", response = List.class)
	public List<Curso> getAllCursos() {
		return cursoRepository.findAll();
	}

	@GetMapping("/cursos/{id}")
	@ApiOperation(value = "Retorna un curso por id")
	public ResponseEntity<Curso> getCursoById(
			@ApiParam(value = "Curso id", required = true) @PathVariable(value = "id") Integer cursoId)
			throws ResourceNotFoundException {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado id :: " + cursoId));
		return ResponseEntity.ok().body(curso);
	}

	@PostMapping("/cursos")
	@ApiOperation(value = "Agrega un curso")
	public Curso createCurso(@ApiParam(value = "Object Curso", required = true) @Valid @RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}

	@PutMapping("/cursos/{id}")
	@ApiOperation(value = "Actualiza un curso")
	public ResponseEntity<Curso> updateCurso(
			@ApiParam(value = "Curso id a actualizar", required = true) @PathVariable(value = "id") Integer cursoId,
			@ApiParam(value = "Object Curso", required = true) @Valid @RequestBody Curso cursoDetails)
			throws ResourceNotFoundException {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado id :: " + cursoId));

		curso.setDocente(cursoDetails.getDocente());
		curso.setCarrera(cursoDetails.getCarrera());
		curso.setNombre(cursoDetails.getNombre());
		curso.setDescripcion(cursoDetails.getDescripcion());
		curso.setCupomaximo(cursoDetails.getCupomaximo());
		curso.setAnio(cursoDetails.getAnio());
		curso.setCalificacionaprobada(cursoDetails.getCalificacionaprobada());
		final Curso updatedCurso = cursoRepository.save(curso);
		return ResponseEntity.ok(updatedCurso);
	}

	@DeleteMapping("/cursos/{id}")
	@ApiOperation(value = "Elimina un curso por id")
	public Map<String, Boolean> deleteCurso(
			@ApiParam(value = "Curso id a eliminar", required = true) @PathVariable(value = "id") Integer cursoId)
			throws ResourceNotFoundException {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado id :: " + cursoId));

		cursoRepository.delete(curso);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return response;
	}

	@GetMapping("/cursos/{id}/inscripciones")
	@ApiOperation(value = "Retorna para una asignatura(curso) dado, los alumnos inscriptos y el docente correspondiente")
	public List<Object> getInscripcionesCursoById(@ApiParam(value = "Curso id a detallar", required = true) @PathVariable(value = "id") Integer cursoId)
			throws ResourceNotFoundException {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado id :: " + cursoId));
		List<Object> list = new ArrayList<Object>();
		list.add(curso);
		List<InscripcionesCurso> listinscripcionescurso = inscripcionesCursoRepository.findByCurso(curso);
		for (InscripcionesCurso item : listinscripcionescurso) {
			List<Object> tmplist = new ArrayList<Object>();
			tmplist.add(item.getAlumno());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			tmplist.add(formatter.format(item.getFechainscripcion()));
			list.add(tmplist);
		}
		return list;
	}

}