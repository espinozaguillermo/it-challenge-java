package it.java.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alumno")
public class Alumno {
	private Integer id;
	@NotNull
	private Persona persona;
	@NotNull
	private Integer legajo;

	public Alumno() {
	}

	public Alumno(Persona newPersona, Integer newLegajo) {
		persona = newPersona;
		legajo = newLegajo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer newId) {
		this.id = newId;
	}

	@OneToOne
	@JoinColumn(name = "idpersona", unique = true)
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona newPersona) {
		this.persona = newPersona;
	}

	@Column(name = "legajo", nullable = false)
	public Integer getLegajo() {
		return legajo;
	}
	public void setLegajo(Integer newLegajo) {
		this.legajo = newLegajo;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", persona=" + persona.toString() + ", legajo=" + legajo + "]";
	}

}