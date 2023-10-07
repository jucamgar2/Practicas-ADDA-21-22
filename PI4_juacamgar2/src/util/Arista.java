package util;
//Record para la arista dirigida del ejercicio2
public record Arista(Libro source,Libro destino) {
	public static Arista ofFormat(String [] p) {
		return new Arista(Libro.ofFormat(p[0]) , Libro.ofFormat(p[1]));
	}
}
