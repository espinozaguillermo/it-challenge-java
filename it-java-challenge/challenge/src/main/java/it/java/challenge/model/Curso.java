package it.java.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import it.java.challenge.validator.*;

@Entity
@Table(name = "curso")
@AnioCurso(message="El anio del curso no corresponde con las fechas de la carrera")
public class Curso {
	private Integer id;
	@NotNull
	private Docente docente;
	@NotNull
	private Carrera carrera;
	@NotNull
	private String nombre;
	@NotNull
	private String descripcion;
	@NotNull
	private Integer cupomaximo;
	@NotNull
	private Integer anio;
	@NotNull
	private Integer calificacionaprobada;

	public Curso() {
	}

	public Curso(Docente newDocente, Carrera newCarrera, String newNombre, String newDescripcion, Integer newCupomaximo, Integer newAnio, Integer newCalificacionaprobada) {
		docente = newDocente;
		carrera = newCarrera;
		nombre = newNombre;
		descripcion = newDescripcion;
		cupomaximo = newCupomaximo;
		anio = newAnio;
		calificacionaprobada = newCalificacionaprobada;
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
	@JoinColumn(name = "iddocente")
	public Docente getDocente() {
		return docente;
	}
	
	public void setDocente(Docente newDocente) {
		this.docente = newDocente;
	}
	
	@ManyToOne
	@JoinColumn(name = "idcarrera")
	public Carrera getCarrera() {
		return carrera;
	}
	
	public void setCarrera(Carrera newCarrera) {
		this.carrera = newCarrera;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String newDescripcion) {
		this.descripcion = newDescripcion;
	}
	
	@Column(name = "cupomaximo", nullable = false)
	public Integer getCupomaximo() {
		return cupomaximo;
	}
	
	public void setCupomaximo(Integer newCupomaximo) {
		this.cupomaximo = newCupomaximo;
	}

	@Column(name = "anio", nullable = false)
	public Integer getAnio() {
		return anio;
	}
	
	public void setAnio(Integer newAnio) {
		this.anio = newAnio;
	}

    @Column(name = "calificacionaprobada", nullable = false)
	public Integer getCalificacionaprobada() {
		return calificacionaprobada;
	}
    
	public void setCalificacionaprobada(Integer newCalificacionaprobada) {
		this.calificacionaprobada = newCalificacionaprobada;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", docente=" + docente.toString() + ", carrera=" + carrera.toString() + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cupomaximo=" + cupomaximo + ", anio=" + anio + ", calificacionaprobada=" + calificacionaprobada + "]";
	}

}