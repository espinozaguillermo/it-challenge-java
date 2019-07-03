package ejercicios;

import java.util.Date;
import java.lang.Integer;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A. Crear una clase Persona con los siguientes campos
 * (con sus respectivos getters, setters y constructor)
 * 
 * TipoDocumento - enum (dni/libretacivica) 
 * NroDocumento - Integer
 * Nombre - String
 * Apellido - String
 * FechaNacimiento - Date
 * 
 * B. En el método main de la clase "Ejercicio2" crear una nueva instancia
 * de la clase persona y settearle valores reales con tus datos
 * 
 * 
 * C. En el método main de la clase "Ejercicio 2" imprimir los valores en 
 * consola 
 * (crear método main e imprimir valores) 
 */

enum TipoDocumento
{
	DNI, LIBRETACIVICA;
}

class Persona {
	TipoDocumento tipoDocumento;
	Integer nroDocumento;
	String nombre;
	String apellido;
	Date fechaNacimiento;
	
	public Persona(TipoDocumento newTipoDocumento, Integer newNroDocumento, String newNombre, String newApellido, Date newFechaNacimiento) {
		tipoDocumento = newTipoDocumento;
		nroDocumento = newNroDocumento;
		nombre = newNombre;
		apellido = newApellido;
		fechaNacimiento = newFechaNacimiento;
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
}

/**
 * @author examen
 */
public class Ejercicio2 {

	/**
	 * 
	 */
	public Ejercicio2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpleDateFormat.parse("1986-03-20");
			Persona newPersona = new Persona(TipoDocumento.DNI, 3208682, "Guillermo", "Espinoza", date);
			
			System.out.println(newPersona.getTipoDocumento());
			System.out.println(newPersona.getNroDocumento());
			System.out.println(newPersona.getNombre());
			System.out.println(newPersona.getApellido());
			System.out.println(newPersona.getFechaNacimiento());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
