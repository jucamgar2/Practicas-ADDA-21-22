package util;
// record creado para el objeto persona, vertice del ejercicio 4
public record Persona(Integer x, Integer y, Integer estado) {
	//Estado = 1, significa positivo, cualquier otro valor significaría que está sano, esta propiedad del objeto me permitirá colorear bien el grafo del ejercicio 4
	
	public static Persona Positivo(String s) {
		s.replace('+', ' ').strip();
		String [] res = s.split(",");
		return new Persona (Integer.valueOf(res[0]),Integer.valueOf(res[1]),1 );
	}
	public static Persona Negativo(String s) {
		s.replace('-', ' ').strip();
		String [] res = s.split(",");
		return new Persona (Math.abs(Integer.valueOf(res[0])),Integer.valueOf(res[1]),3 );
	}
	
}