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

enum TipoDocumento {
	DNI, LIBRETACIVICA;
}

@Entity
@Table(name = "persona")
public class Persona {
	private Integer id;
	@NotNull
	@StringEnumeration(enumClass = TipoDocumento.class, message = "tipodoc tiene que ser DNI o LIBRETACIVICA")
	private String tipodoc;
	@NotNull
	private Integer documento;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechanac;

	public Persona() {
	}

	public Persona(String newTipoDocumento, Integer newNroDocumento, String newNombre, String newApellido,
			Date newFechaNacimiento) {
		tipodoc = newTipoDocumento;
		documento = newNroDocumento;
		nombre = newNombre;
		apellido = newApellido;
		fechanac = newFechaNacimiento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer newId) {
		this.id = newId;
	}

	@Column(name = "tipodoc", nullable = false)
	public String getTipoDocumento() {
		return tipodoc;
	}

	public void setTipoDocumento(String newTipoDocumento) {
		this.tipodoc = newTipoDocumento;
	}

	@Column(name = "documento", nullable = false)
	public Integer getNroDocumento() {
		return documento;
	}

	public void setNroDocumento(Integer newNroDocumento) {
		this.documento = newNroDocumento;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}

	@Column(name = "apellido", nullable = false)
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String newApellido) {
		this.apellido = newApellido;
	}

	@Column(name = "fechanac", nullable = false, columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFechaNacimiento() {
		return fechanac;
	}

	public void setFechaNacimiento(Date newFechaNacimiento) {
		this.fechanac = newFechaNacimiento;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipodoc=" + tipodoc
				+ ", documento=" + documento + ", fechanac=" + fechanac + "]";
	}

}