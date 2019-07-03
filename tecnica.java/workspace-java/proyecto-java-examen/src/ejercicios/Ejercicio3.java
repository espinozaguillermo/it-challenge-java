/**
 * 
 */
package ejercicios;

/**
 * A. Crear una clase Alumnno con los siguientes campos
 * (con sus respectivos getters, setters y constructor)
 * 
 * Persona
 * Legajo - Integer
 * 
 *  
 * @author examen
 *
 */

class Alumno {
	Persona persona;
	Integer legajo;
	
	public Alumno(Persona newPersona, Integer newLegajo) {
		persona = newPersona;
		legajo = newLegajo;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona newPersona) {
		this.persona = newPersona;
	}
	
	public Integer getLegajo() {
		return legajo;
	}
	
	public void setLegajo(Integer newLegajo) {
		this.legajo = newLegajo;
	}
	
}

public class Ejercicio3 {

	/**
	 * 
	 */
	public Ejercicio3() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
