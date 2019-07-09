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
import it.java.challenge.model.Persona;
import it.java.challenge.repository.PersonaRepository;
import it.java.challenge.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value="CRUD Persona")
public class PersonaController {
	
	@Autowired
    private PersonaRepository personaRepository;
	
	@GetMapping("/personas")
	@ApiOperation(value = "Listado de personas", response = List.class)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
	
	@GetMapping("/personas/{id}")
	@ApiOperation(value = "Retorna una persona por id")
    public ResponseEntity<Persona> getPersonaById(
    		@ApiParam(value = "Persona id", required = true) @PathVariable(value = "id") Integer personaId)
        throws ResourceNotFoundException {
        Persona persona= personaRepository.findById(personaId)
          .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada id :: " + personaId));
        return ResponseEntity.ok().body(persona);
    }
	
	@PostMapping("/personas")
	@ApiOperation(value = "Agrega una persona")
    public Persona createPersona(@ApiParam(value = "Object Persona", required = true) @Valid @RequestBody Persona persona) {
        return personaRepository.save(persona);
    }
	
	@PutMapping("/personas/{id}")
	@ApiOperation(value = "Actualiza una persona")
    public ResponseEntity<Persona> updatePersona(
    		@ApiParam(value = "Persona id a actualizar", required = true) @PathVariable(value = "id") Integer personaId,
    		@ApiParam(value = "Object Persona", required = true) @Valid @RequestBody Persona personaDetails) throws ResourceNotFoundException {
        Persona persona = personaRepository.findById(personaId)
        .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada id :: " + personaId));

        persona.setNombre(personaDetails.getNombre());
        persona.setApellido(personaDetails.getApellido());
        persona.setFechaNacimiento(personaDetails.getFechaNacimiento());
        persona.setTipoDocumento(personaDetails.getTipoDocumento());
        persona.setNroDocumento(personaDetails.getNroDocumento());
        final Persona updatedPersona = personaRepository.save(persona);
        return ResponseEntity.ok(updatedPersona);
    }
	
	@DeleteMapping("/personas/{id}")
	@ApiOperation(value = "Elimina una persona por id")
    public Map<String, Boolean> deletePersona(
			@ApiParam(value = "Persona id a eliminar", required = true) @PathVariable(value = "id") Integer personaId)
         throws ResourceNotFoundException {
        Persona persona = personaRepository.findById(personaId)
       .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada id :: " + personaId));

        personaRepository.delete(persona);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }

}
