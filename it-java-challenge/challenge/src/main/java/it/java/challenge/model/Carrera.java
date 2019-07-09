package it.java.challenge.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.java.challenge.validator.*;

@Entity
@Table(name = "carrera")
@GreaterDate.List({
	@GreaterDate(first = "fechadesde", second = "fechahasta", message = "fechahasta debe ser mayor a fechadesde")
})
public class Carrera {
	private Integer id;
	@NotNull
	private String nombre;
	@NotNull
	private String descripcion;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechadesde;
	@Temporal(TemporalType.DATE)
	private Date fechahasta;

	public Carrera() {
	}

	public Carrera(String newNombre, String newDescripcion, Date newFechadesde, Date newFechaHasta) {
		nombre = newNombre;
		descripcion = newDescripcion;
		fechadesde = newFechadesde;
		fechahasta = newFechaHasta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer newId) {
		this.id = newId;
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

	@Column(name = "fechadesde", nullable = false, columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date newFechadesde) {
		this.fechadesde = newFechadesde;
	}

	@Column(name = "fechahasta", columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(Date newFechahasta) {
		this.fechahasta = newFechahasta;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechadesde="
				+ fechadesde.toString() + ", fechahasta=" + fechahasta != null ? fechahasta.toString() : "" + "]";
	}

}