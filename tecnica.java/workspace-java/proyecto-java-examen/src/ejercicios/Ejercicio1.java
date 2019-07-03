package ejercicios;
/**
 * Ejercicio: analizar la siguente clase y realizar las correcciones
 * necesarias para que compile  
 * @author examen
 *
 */
public class Ejercicio1 {
	
	  	int count1;
	    static int count2;  

	    public Ejercicio1() {
	    	count1 = 0;
	    	count2 ++;
	    }

	    public void incrementa1() {
	    	count1++;
	    }

	    public void incrementa2() {
	    	count2++;
	    }
	    
	    //CAMBIO: saque la palabra static del metodo
	    //ya que count1 no es static
	    public void incrementa() {
	    	count1++;
	    }
	    
	    /**
	     * CAMBIO: Agregué método main
		 * @param args
		 */
		public static void main(String[] args) {
			Ejercicio1 ejercicio = new Ejercicio1();
			System.out.println(ejercicio.count1);
			System.out.println(ejercicio.count2);
		}

}
