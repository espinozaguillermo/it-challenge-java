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
@Table(name = "inscripciones_curso")
@FechaInscripcionCurso(message="La fecha de inscripcion distinta al anio del curso")
@UniqueInscripcionCurso(message="El alumno ya posee una inscripcioncurso")
@AlumnoInscripcionCurso(message="El alumno no esta inscripto a la carrera del curso")
@CupomaximoInscripcionCurso(message="El alumno no puede inscribirse ya que se supero el cupomaximo del curso")
public class InscripcionesCurso {
	private Integer id;
	@NotNull
	private Alumno alumno;
	@NotNull
	private Curso curso;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechainscripcion;
	private Integer calificacion;

	public InscripcionesCurso() {
	}

	public InscripcionesCurso(Alumno newAlumno, Curso newCurso, Date newFechainscripcion, Integer newCalificacion) {
		alumno = newAlumno;
		curso = newCurso;
		fechainscripcion = newFechainscripcion;
		calificacion = newCalificacion;
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
	@JoinColumn(name = "idcurso")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso newCurso) {
		this.curso = newCurso;
	}

	@Column(name = "fechainscripcion", nullable = false, columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFechainscripcion() {
		return fechainscripcion;
	}

	public void setFechainscripcion(Date newFechainscripcion) {
		this.fechainscripcion = newFechainscripcion;
	}

	@Column(name = "calificacion", nullable = true)
	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer newCalificacion) {
		this.calificacion = newCalificacion;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", curso=" + curso.toString() + ", alumno=" + alumno.toString()
				+ ", fechainscripcion=" + fechainscripcion.toString() + ", calificacion=" + calificacion + "]";
	}

}