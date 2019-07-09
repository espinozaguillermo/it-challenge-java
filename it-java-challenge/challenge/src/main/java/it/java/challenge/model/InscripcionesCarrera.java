package it.java.challenge.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.java.challenge.validator.*; 

@Entity
@Table(name = "inscripciones_carrera")
@FechaInscripcionCarrera(message="La fechainscipcion no corresponde con las fechas de la carrera")
@UniqueInscripcionCarrera(message="El alumno ya posee una inscripcioncarrera para la carrera")
public class InscripcionesCarrera {
	private Integer id;
	@NotNull
	private Alumno alumno;
	@NotNull
	private Carrera carrera;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechainscripcion;

	public InscripcionesCarrera() {
	}

	public InscripcionesCarrera(Alumno newAlumno, Carrera newCarrera, Date newFechainscripcion) {
		alumno = newAlumno;
		carrera = newCarrera;
		fechainscripcion = newFechainscripcion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer newId) {
		this.id = newId;
	}

	@ManyToOne
	@JoinColumn(name = "idalumno")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno newAlumno) {
		this.alumno = newAlumno;
	}

	@ManyToOne
	@JoinColumn(name = "idcarrera")
	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera newCarrera) {
		this.carrera = newCarrera;
	}

	@Column(name = "fechainscripcion", nullable = false, columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFechainscripcion() {
		return fechainscripcion;
	}

	public void setFechainscripcion(Date newFechainscripcion) {
		this.fechainscripcion = newFechainscripcion;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", carrera=" + carrera.toString() + ", alumno=" + alumno.toString()
				+ ", fechainscripcion=" + fechainscripcion.toString() + "]";
	}

}