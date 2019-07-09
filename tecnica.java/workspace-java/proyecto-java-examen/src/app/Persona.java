package app;

import java.util.Date;

enum TipoDocumento {
	DNI, LIBRETACIVICA;
}

public class Persona {
	Integer identificador;
	TipoDocumento tipoDocumento;
	Integer nroDocumento;
	String nombre;
	String apellido;
	Date fechaNacimiento;

	public Persona(TipoDocumento newTipoDocumento, Integer newNroDocumento, String newNombre, String newApellido,
			Date newFechaNacimiento) {
		tipoDocumento = newTipoDocumento;
		nroDocumento = newNroDocumento;
		nombre = newNombre;
		apellido = newApellido;
		fechaNacimiento = newFechaNacimiento;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer newIdentificador) {
		this.identificador = newIdentificador;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento newTipoDocumento) {
		this.tipoDocumento = newTipoDocumento;
	}

	public Integer getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Integer newNroDocumento) {
		this.nroDocumento = newNroDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String newApellido) {
		this.apellido = newApellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date newFechaNacimiento) {
		this.fechaNacimiento = newFechaNacimiento;
	}

	public Persona() {
		// TODO Auto-generated constructor stub
	}

}