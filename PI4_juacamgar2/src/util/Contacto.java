package util;
// record creado para la arista del ejercicio 4 e implementar un metodo que calcule la distancia de las personas de la arista
public record Contacto(Persona p1, Persona p2) {
	public static Double getDistancia(Persona p1,Persona p2,Double dFilas,Double dColumnas) {
		return Math.sqrt(Math.pow((p2.x()-p1.x())*dFilas, 2) + Math.pow((p2.y()-p1.y())*dColumnas, 2));
	}

	
	
}
