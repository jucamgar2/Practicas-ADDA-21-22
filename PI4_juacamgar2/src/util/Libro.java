package util;
// record para el objeto libro, vertice del ejercicio 2
public record Libro(String libro) {
	public static Libro ofFormat(String [] a) {
		return new Libro(a[0]);
	}
	public static Libro ofFormat(String a) {
		return new Libro(a);
	}

}
